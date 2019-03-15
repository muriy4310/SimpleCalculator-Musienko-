package utils;

import java.util.ArrayList;

/*класс с расчетными функциями*/
public class CalculateFunction {
    /*Константные значение операторов*/
    public enum Operator{
        Sum,
        Difference,
        Multiply,
        Divide;
        private int priority = this.ordinal();
        public int getPriority(){
            return this.priority;
        }
    }
    /*
    *Работает с приоритетностью операторов
    *@param operator массив операторов
    *@return возвращает массив приоритетов
    * */
    private static ArrayList<Number> getOperatorsPriority(ArrayList<Operator> operators){
        ArrayList<Number> result = new ArrayList<Number>();
        for (Operator operator:operators) {
            result.add(operator.getPriority());
        }
        return result;
    }
    /*
    *@param numbers массив чисел
    *@return возвращает индекс найбольшего числа массива
    * */
    private static int getMaxIndex(ArrayList<Number> numbers){
        int max = 0;
        int maxIndex = 0;
        int currentNum = 0;
        for (int i = 0; i < numbers.size(); i++){
            currentNum = (int)numbers.get(i);
            if(max < currentNum){
                max = currentNum;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    /*
    *Подсчитвывает выражениеи состоящее из двух операндов и одного оператора
    *@param operand1 первый операнд
    *@param operand2 второй операнд
    *@param operator оператор
    *@return возвращает результат вычисления
    * */
    public static float calculate(float operand1, float opeand2, Operator operator){
        Operator operator_ = operator;
        switch (operator_){
            case Sum:
                return operand1 + opeand2;
            case Difference:
                return operand1 - opeand2;
            case Multiply:
                return operand1 * opeand2;
            case Divide:
                return operand1 / opeand2;
        }
        return 0;
    }
    /*
    *Вычисляет выражение состоящее из операторов и операднов
    *@param operands массив операнднов
    *@param operators массив операторов
    *@return возвращает результат вычисления выражения
    * */
    public static float calculateExpression(ArrayList<Number> operands, ArrayList<Operator> operators){
        float result = 0;
        int curentOperationIndex;
        float operand1;
        float operand2;
        Operator operator;
        float temp = 0;
        ArrayList<Number>operatorsPriority = getOperatorsPriority(operators);
        while (operators.size() > 0){
            curentOperationIndex = getMaxIndex(operatorsPriority);
            operand1 = (float)operands.get(curentOperationIndex);
            operand2 = (float)operands.get(curentOperationIndex + 1);
            operator = operators.get(curentOperationIndex);
            temp = calculate(operand1,operand2,operator);
            operands.add(curentOperationIndex,temp);
            operands.remove(curentOperationIndex + 1);
            operands.remove(curentOperationIndex + 1);
            operators.remove(curentOperationIndex);
            operatorsPriority.remove(curentOperationIndex);
        }
        result = (float)operands.get(0);
        operands.remove(0);
        return result;
    }

}
