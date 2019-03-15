package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

    /*класс для работы со строками
     *{@value #ROMAN_NUMBER} регулярное выражение которе соответствует всем римским числам
     *{@value #MAIN_OPERATORS} регулярное выражение которое соответствует математическим операторам
     *{@value #ROMAN_EXPSPRESSION} регулярное выражение которое соответствует математическому выражению с использованием РИМСКИХ цифр
     *{@value #DECIMAL_EXPRESSION} регулярное выражение которое соответствует математическому выражению с использованием ДЕСЯТИЧНЫХ цифр
     * */
public class StringFunction {
    public static final String ROMAN_NUMBER = "(?i)M{0,3}(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])";
    public static final String MAIN_OPERATORS = "[/*\\.+\\-]";
    public static final String ROMAN_EXPSPRESSION = ROMAN_NUMBER + "?(" + MAIN_OPERATORS + "?" + ROMAN_NUMBER + "?)+";
    public static final String DECIMAL_EXPRESSION = "[0-9]([0-9]*" + MAIN_OPERATORS + "?[0-9]+)+";

    /*
     *Проверка строки
     * @param str строка которую нужно проверить
     * @param regExp регулярное выражение для проверки
     * @return возвращает true если строка соответствует регулярному выражению
     * */
    public static boolean validateString(String str, String regExp) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
