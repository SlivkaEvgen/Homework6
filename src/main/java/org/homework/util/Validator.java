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
//return name.equalsIgnoreCase("/^[a-z0-9_-]{3,16}$/");
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

    public static boolean validLength(String parameter) {
        return parameter.length() >= 12;
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
        return !(gender.equalsIgnoreCase("Male")
                |gender.equalsIgnoreCase("Female"));
    }

    public static boolean validEmail(String email) {
//        return email.equalsIgnoreCase("/^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i");
        return email.contains("@");
    }

    public static boolean onlyNumbers(String numbers) {
        return numbers.equalsIgnoreCase("/^\\d{1,}$/");
        //re.test('13'); // true
        //re.test('23yy'); // false
    }

    public static boolean validNumberPhone(String numberPhone) {
        return numberPhone.equalsIgnoreCase(
                "/^\\+?(\\d{1,3})?[- .]?\\(?(?:\\d{2,3})\\)?[-" +
                        " .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$/");
        //re.test('(212) 348-2626'); // true
        //re.test('+1 832-393-1000'); // true
        //re.test('+1 202-456-11-11'); // false
    }

    public static boolean goodPassword(String password) {
        return password.equalsIgnoreCase(
                "/^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])" +
                        "(?=.*[a-z].*[a-z].*[a-z]).{8,}$/");
        //re.test('qwerty'); // false
        //re.test('qwertyuiop'); // false
        //re.test('abcABC123$'); // true
    }

    public static boolean goodDate(String date) {
        return date.equalsIgnoreCase("/^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)" +
                "(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)" +
                "?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)" +
                "(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$/");
        //re.test('29-02-2000'); // true
        //re.test('29-02-2001'); // false
    }

    public static boolean urlOrNot(String url) {
        return url.equalsIgnoreCase("/[-a-zA-Z0-9@:%_\\+.~#?&\\/=]{2,256}\\.[a-z]{2,4}\\b(\\/" +
                "[-a-zA-Z0-9@:%_\\+.~#?&\\/=]*)?/gi");
        //re.test('https://yandex.ru'); // true
        //re.test('yandex.ru'); // true
        //re.test('hello world'); // false
    }

    public static boolean takeOnlyDomain(String domain) {
        String getDomainName = "/https?:\\/\\/(?:[-\\w]+\\.)?([-\\w]+)\\.\\w+(?:\\.\\w+)?\\/?.*/i";

        boolean matches = domain.matches(getDomainName);
        return domain.equalsIgnoreCase(
                "/https?:\\/\\/(?:[-\\w]+\\.)?([-\\w]+)\\.\\w+(?:\\.\\w+)?\\/?.*/i");
        //let domain = 'https://proglib.io'.match(re);
        //console.log(domain[1]); // proglib
    }
}


