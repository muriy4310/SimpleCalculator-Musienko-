package com;
import java.util.ArrayList;
import java.util.Scanner;
import static utils.StringFunction.*;
import parsers.ExpressionParser;
import utils.CalculateFunction.Operator;
import static utils.CalculateFunction.*;
/*главный класс*/
public class Main {
    /*Выражение для подсчета(вводится с клавиатуры в консоли)*/
    private static String expression = "";
    /*Используется для парсинга выражения*/
    private static ExpressionParser parser = new ExpressionParser();
    /*массив операторов*/
    private static ArrayList<Operator> operators = new ArrayList<Operator>();
    /*массив операндов*/
    private static ArrayList<Number> operands = new ArrayList<Number>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String welcomeS = "Введите выражение для подсчета";
        String description = "Вы можете использовать испозовать римские или греческие цифры(но не одновременно).\n" +
                "Доступные операции '/*+-'\n" +
                "Для выхода из программы введите 'out'";
        System.out.println(welcomeS);
        System.out.println(description);
        while (true) {
            expression = s.next();
            System.out.println(expression);
            if(validateString(expression.toUpperCase(), "OUT")) {
                break;
            }
            if (validateString(expression, ROMAN_EXPSPRESSION) || validateString(expression, DECIMAL_EXPRESSION)) {
                parser.parseExpresion(expression);
                operands = parser.getNumbers();
                operators = parser.stringToOperaor(parser.getOperators());
                System.out.print(expression + " = ");
                System.out.println(calculateExpression(operands, operators));

            }
            else {
                System.out.println("Вы ввели некорекстное выражение,попробуйте еще раз\n");
            }
            System.out.println(description);
        }
    }
}
