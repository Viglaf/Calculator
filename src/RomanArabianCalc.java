import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class RomanArabianCalc {
    static char operat;
    static int num1;
    static int num2;
    static int result;
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение арабскими или римскими цифрами: ");// Считываем строку userInput которую ввёл пользователь
        String userInput = scanner.nextLine();// Создаём пустой символьный массив длиной 10 символов:  under_char
        char[] under_char = new char[10];//Заполняем символьный массив символами строки которую ввел пользователь и по ходу ловим знак операции
        for (int i = 0; i < userInput.length(); i++) {
            under_char[i] = userInput.charAt(i);
            if (under_char[i] == '+') {
                operat = '+';
            }
            if (under_char[i] == '-') {
                operat = '-';
            }
            if (under_char[i] == '*') {
                operat = '*';
            }
            if (under_char[i] == '/') {
                operat = '/';
            }
        }
        String under_charString = String.valueOf(under_char);  //возвращает char в строку
        String[] blacks = under_charString.split("[+-/*]"); // делим по знаку операции
        if (blacks.length>2) { // если длина массива больше 2 -> кидаем исключение и завершение работы программы
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
                System.exit(1);
            }
        }
        String stable00 = blacks[0]; // первый операнд
        String stable01 = blacks[1].trim(); // второй операнд
        String[] roman0 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};    //проверяем на соответсвие римским цифрам
        if (Arrays.asList(roman0).contains(stable00)&Arrays.asList(roman0).contains(stable00)) {
            num1 = romanToNumber(stable00); // конвертация в арабские 1го
            num2 = romanToNumber(stable01); // конвертация в арабские 2го
            result = calculated(num1, num2, operat);
            if (result <= 0) {
                throw new ArithmeticException("Не может быть меньше либо равно нулю");
            }
            String resultRoman = convertNumToRoman(result); // конвертируем результат из арабских в римские
            System.out.println(resultRoman);

        }else {
            try {
                num1 = Integer.parseInt(stable00); // ели числа арабские, то переводим из строки в число
                num2 = Integer.parseInt(stable01);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат данных");
                System.exit(1);
            }
            if (num1<=10 & num2<=10) {
                result = calculated(num1, num2, operat);
                System.out.println(result);
            } else{
                System.out.println("Принимаются числа от 1 до 10");
            }
        }
    }

     static String convertNumToRoman(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[numArabian];
    }

    static int romanToNumber (String roman) {
        try {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            System.out.println("Неверный формат данных");
            e.printStackTrace();
        }
        return -1;
    }

    static int calculated (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("На ноль делить нельзя");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный знак операции");
        }
        return result;
    }
}
