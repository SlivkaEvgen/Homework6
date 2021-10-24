package org.homework.util;

import org.homework.model.BaseModel;
import org.homework.service.CrudService;
import org.homework.service.ServiceFactory;

public class Validator<T extends BaseModel<ID>, ID> {

    private final CrudService<T, ID> crudService;

    public Validator(Class<T> classModel) {
        crudService = ServiceFactory.of(classModel);
    }

    public static boolean validNumber(String hasNumbers) {
        if (!validString(hasNumbers)) {
            return true;
        } else return hasNumbers.matches("\\d+");
    }

    public static boolean validString(String hasLetters) {
        return !hasLetters.matches("\\d+");
    }

    private static boolean empty(String id) {
        return !id.isEmpty();
    }

    public static boolean validId(String id) {
        if (!empty(id)) {
            return false;
        }
        if (!validNumber(id)) {
            return false;
        }
        return Long.parseLong(id) != 0;
    }

    public static boolean validName(String name) {
        if (name.isEmpty()) {
            return false;
        }
        if (name.equalsIgnoreCase("null")) {
            return false;
        }
        if (name.length() > 10) {
            return false;
        }
        if (Validator.validNumber(name)) {
            return false;
        }
        return validString(name);
    }

    public static boolean validActivity(String activity) {
        return (!activity.equalsIgnoreCase("java")
                & !activity.equalsIgnoreCase("js")
                & !activity.equalsIgnoreCase("c+")
                & !activity.equalsIgnoreCase("c#"));
    }

    public static boolean validLevel(String level) {
        return (!level.equalsIgnoreCase("junior")
                & !level.equalsIgnoreCase("middle")
                & !level.equalsIgnoreCase("senior"));
    }

    public boolean validUniqueId(String id) {
        return crudService.findById((ID) Long.valueOf(id)).isPresent() & Long.parseLong(id) != 0;
    }

    public static boolean validGender(String gender) {
        return (gender.equalsIgnoreCase("Male")
                |gender.equalsIgnoreCase("Female"));
    }

    public static boolean validEmail(String email) {
        return email.contains("@");
    }

    public static boolean onlyNumbers(String numbers) {
        return numbers.equalsIgnoreCase("/^\\d{1,}$/");
    }
}


