package org.homework.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.homework.model.BaseModel;
import org.homework.util.DatabaseConnection;
import org.homework.util.PropertiesLoader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Closeable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CrudRepositoryImpl<T extends BaseModel<ID>, ID> implements Closeable, Serializable, CrudRepository<T, ID> {

    private static final long serialVersionUID = 10000000025L;

//    @Getter(AccessLevel.PROTECTED)
    private final Connection connection;
    private final ObjectMapper mapper;
    private final Class<T> modelClass;
    private final Map<String, String> columnFieldName;
    private final PreparedStatement findAllPreparedStatement;
    private final PreparedStatement findByIDPreparedStatement;
    private final PreparedStatement deletePreparedStatement;
    private final PreparedStatement createPreparedStatement;
    private final PreparedStatement updatePreparedStatement;

    @SneakyThrows
    public CrudRepositoryImpl(Class<T> modelClass) {
        this.connection = DatabaseConnection.getInstance().getConnection();
        String databaseSchemaName = PropertiesLoader.getProperties("db.schemaName");

        this.mapper = new ObjectMapper();

        this.modelClass = modelClass;//(Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.columnFieldName = Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toMap(field -> getColumnName(field), field -> field.getName()));
        String generatedColumns[] = {getColumnName(Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(field -> field.getAnnotation(Id.class) != null)
                .findAny().orElseThrow(() -> new RuntimeException("Entity must contains ID")))};

        String tableName = modelClass.getAnnotation(Entity.class) != null
                ? modelClass.getAnnotation(Entity.class).name() : modelClass.getSimpleName().toLowerCase();

        String countValues = IntStream.range(0, columnFieldName.size()).mapToObj(i -> "?").collect(Collectors.joining(","));
        String fieldsForCreate = String.join(",", columnFieldName.keySet());
        String fieldsForUpdate = columnFieldName.keySet().stream().map(v -> v + "=?").collect(Collectors.joining(","));

        this.findAllPreparedStatement = connection.prepareStatement("SELECT * FROM " + databaseSchemaName + "." +
                tableName, generatedColumns);
        this.findByIDPreparedStatement = connection.prepareStatement("SELECT * FROM " + databaseSchemaName + "." +
                tableName + " WHERE id=?", generatedColumns);
        this.deletePreparedStatement = connection.prepareStatement("DELETE FROM " + databaseSchemaName + "." +
                tableName + " WHERE id=?;", generatedColumns);
        this.createPreparedStatement = connection.prepareStatement("INSERT INTO " + databaseSchemaName + "." +
                tableName + "(" + fieldsForCreate + ")" + " VALUES (" + countValues + ")", generatedColumns);
        this.updatePreparedStatement = connection.prepareStatement("UPDATE " + databaseSchemaName + "." + tableName
                + " SET " + fieldsForUpdate + " WHERE id=?", generatedColumns);
    }

    @SneakyThrows
    private String getColumnName(Field field) {
        return field.getAnnotation(Column.class) == null ? field.getName() : field.getAnnotation(Column.class).name();
    }

    @SneakyThrows
    @Override
    public List<T> findAll() {
        return parse(findAllPreparedStatement.executeQuery());
    }

    @SneakyThrows
    @Override
    public Optional<T> findById(ID id) {
        findByIDPreparedStatement.setObject(1, id);
        final List<T> result = parse(findByIDPreparedStatement.executeQuery());
        if (result.isEmpty()) return Optional.empty();
        if (result.size() > 1) throw new RuntimeException("Method 'find by id' returned more than one result");
        return Optional.of(result.get(0));
    }

    @SneakyThrows
    @Override
    public void deleteById(ID id) {
        deletePreparedStatement.setObject(1, id);
        deletePreparedStatement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public T create(T t) {
        return executeStatement(createPreparedStatement, t);
    }

    @SneakyThrows
    @Override
    public T update(T t) {
        updatePreparedStatement.setObject(columnFieldName.size() + 1, t.getId());
        return executeStatement(updatePreparedStatement, t);
    }

    @SneakyThrows
    private T executeStatement(PreparedStatement statement, T t) {
        int count = 1;
        for (String fieldName : columnFieldName.values()) {
            Field declaredField = modelClass.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            statement.setObject(count++, declaredField.get(t));
        }
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        return findById(rs.next() ? (ID) rs.getObject(1) : t.getId()).get();
    }

    @SneakyThrows
    private List<T> parse(ResultSet resultSet) {
        final List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            final Map<String, Object> objectMap = new HashMap<>();
            for (String fieldName : columnFieldName.keySet()) {
                objectMap.put(columnFieldName.get(fieldName), resultSet.getObject(fieldName));
            }
            result.add(mapper.convertValue(objectMap, modelClass));
        }
        return result;
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }
}
