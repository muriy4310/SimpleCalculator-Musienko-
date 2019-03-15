package parsers;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.CalculateFunction.Operator;
import static utils.StringFunction.*;
/*класс парсер вычисляемого выражения*/
public class ExpressionParser {
    /*Масив чисел исходного выражения*/
    private  ArrayList<Number> numbers = new ArrayList<Number>();
    /*Масив операторов исходного выражения в виде строковых знаков*/
    private  ArrayList<String> operators = new ArrayList<String>();

    /*
    * Разбивает строку на массив чисел и массив операторов
    * @param str исходное выражение
    * */
    public void parseExpresion(String str){
        numbers = new ArrayList<Number>();
        operators = new ArrayList<String>();
        Pattern pattern = Pattern.compile(MAIN_OPERATORS);
        Matcher matcher = pattern.matcher(str);
        int endIndex = 0;
        int startIndex = 0;
        while (matcher.find()) {
            startIndex = matcher.start();
            numbers.add(stringToNumber(str.substring(endIndex,startIndex)));
            endIndex = matcher.end();
            operators.add(str.substring(startIndex, endIndex));
        }
        numbers.add(stringToNumber(str.substring(endIndex)));
    }
    /*
    * Преобразовывает строку в число
    * @param str десятичное или римское число записанное в строку
    * @return взвращает число с плавающей запятой
    * */
    public float stringToNumber(String str){
        float result = 0;
        try {
            result = Float.parseFloat(str);
        }
        catch (NumberFormatException e){
            result = romanToDecimal(str);
        }
        return result;
    }
    /*
    * Преобразовывает римское число в десятичное
    * @param romanNumber римское число записанное в строку
    * @return возвращает десятичное число с плаваюей запятой
    * */
    public float romanToDecimal(String romanNumber) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase();
        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        return decimal;
    }
    private int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
    /*
    * Преобразовывает операторы в виде строки в константные значения
    * @param operators массив операторов в виде строки
    * @return возвращает массив константных операторов
    * */
    public ArrayList<Operator> stringToOperaor(ArrayList<String> operators){
        ArrayList<Operator> result = new ArrayList<Operator>();
        for(int i = 0; i < operators.size();i++){
            switch (operators.get(i)){
                case ("+") :
                    result.add(Operator.Sum);
                    break;
                case ("-") :
                    result.add(Operator.Difference);
                    break;
                case ("*") :
                    result.add(Operator.Multiply);
                    break;
                case ("/") :
                    result.add(Operator.Divide);
                    break;
            }
        }
        return result;
    }
    /*
    *@return возвращает масив чисел исходного выражения
    * */
    public ArrayList<Number> getNumbers() {
        return numbers;
    }
    /*
     *@return возвращает масив операторов исходного выражения, в виде строковых значений
     * */
    public ArrayList<String> getOperators() {
        return operators;
    }

}
