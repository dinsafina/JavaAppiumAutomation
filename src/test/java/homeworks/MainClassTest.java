package homeworks;

import org.junit.jupiter.api.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetClassString() {
        String classString = getClassString();
        if(classString.matches(".*\\b(hello|Hello\\b.*)")) {
            System.out.println("В предложении есть 'hello' или 'Hello'");
        } else {
            System.out.println("В предложении нет нужного слова");
        }
    }

    @Test
    public void testGetLocalNumber() {
        int num = getLocalNumber();
        if (num == 14) {
            System.out.println("Метод возвращает правильное число: 14");
        } else {
            System.out.println("Метод не возвращает нужное число: 14. Текущее число: " + num);
        }
    }

    @Test
    public void testGetClassNumber() {
        Object value = getClassNumber();
        checkInvalidValue(value);
        int result = (Integer) value;
        if (result > 45) {
            System.out.println("Введенное число > 45");
        } else {
            System.out.println("Введенное число < 45");
        }
    }

    private String checkInvalidValue(Object value) {
        if (!(value instanceof Integer)) {
            System.out.println("Введенное значение не целое число");
            if (!(value instanceof Number)) {
                System.out.println("Введенное значение не число");
            }
        }
        return null;
    }
}
