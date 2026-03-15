public class MathHelper {

    public int simpleInt = 7;
    public static int staticInt = 7;

    public int calc(int a, int b, char action) {
        if (action == '+') {
            return plus(a, b);
        } else if (action == '-') {
            return minus(a, b);
        } else if (action == '*') {
            return multiply(a, b);
        } else if (action == '/') {
            return divide(a, b);
        } else {
            return typeAnErrorAndReturnDefaultValue("Wrong action: " + action);
        }
    }

    private int plus(int a, int b) {
        return a + b;
    }

    private int minus(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private int divide(int number, int divider) {
        if (divider == 0) {
            return typeAnErrorAndReturnDefaultValue("Cannot divide by zero");
        } else {
            return number / divider;
        }
    }

    private int typeAnErrorAndReturnDefaultValue(String error_message){
        System.out.println(error_message);
        return 0;
    }
        private int multiply (int number){
            return number * 2;
        }

//    private int multiply(int number, int multiplayer) {
//        return number * multiplayer;
//    }
    }
