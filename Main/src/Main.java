import java.util.Scanner;

public class Main {
    static int a;
    static int b;
    static char arif_action;
    static int result_arab;
    static boolean is_roman;

    public static boolean isArabic(String expression) {
        try {
            Integer.parseInt(expression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String calc(String input) throws Exception {
        try {
            String[] split = input.split("[+\\- */]");
            if (split.length != 2) throw new Exception("Должно быть введено 2 числа");

            if (isArabic(split[0])) {
                a = Integer.parseInt(split[0]);
                b = Integer.parseInt(split[1]);
                is_roman = false;
            } else {
                a = convertToArabic(split[0]);
                b = convertToArabic(split[1]);
                is_roman = true;
            }

            if (isArabic(split[1])) {
                a = Integer.parseInt(split[0]);
                b = Integer.parseInt(split[1]);
                is_roman = false;
            } else {
                a = convertToArabic(split[0]);
                b = convertToArabic(split[1]);
                is_roman = true;
            }
            if ((a > 10 || b > 10) || (a < 1 || b < 1)) {
                throw new Exception("Числа должны быть не больше 10 и не меньше 1");
            }
            char[] znak = new char[input.length()];
            for (int i = 0; i < input.length(); i++) {
                znak[i] = input.charAt(i);
                if (znak[i] == '+') {
                    arif_action = '+';
                }
                if (znak[i] == '-') {
                    arif_action = '-';
                }
                if (znak[i] == '*') {
                    arif_action = '*';
                }
                if (znak[i] == '/') {
                    arif_action = '/';
                }
            }

            switch (arif_action) {

                case '+':
                    result_arab = a + b;
                    if (is_roman) {
                        return convertToRoman(result_arab);
                    } else {
                        return String.valueOf(result_arab);
                    }
                case '-':
                    result_arab = a - b;
                    if (is_roman) {
                        return convertToRoman(result_arab);
                    } else {
                        return String.valueOf(result_arab);
                    }
                case '*':
                    result_arab = a * b;
                    if (is_roman) {
                        return convertToRoman(result_arab);
                    } else {
                        return String.valueOf(result_arab);
                    }
                case '/':
                    if (a == 0 || b == 0) {
                        System.out.println("Деление на ноль недопустимо");
                    } else {
                        result_arab = a / b;
                        if (is_roman) {
                            return convertToRoman(result_arab);
                        } else {
                            return String.valueOf(result_arab);
                        }
                    }
            }
        } catch (NumberFormatException e) {
            System.out.println("Используются одновременно разные системы счисления");
            System.out.println("Введите два арабских числа, либо два римских");
        }
        return input;
    }

    public static int getArabian(char roman) {
        if ('I' == roman) return 1;
        else if ('V' == roman) return 5;
        else if ('X' == roman) return 10;
        else if ('L' == roman) return 50;
        else if ('C' == roman) return 100;
        return 0;
    }

    public static int convertToArabic(String s) {

        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arabian;
        int result_rim = getArabian(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = getArabian(arr[i]);

            if (arabian < getArabian(arr[i + 1])) {
                result_rim -= arabian;
            } else {
                result_rim += arabian;
            }
        }
        return result_rim;
    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("в римской системе нет отрицательных чисел и 0");
        }

        String[] romanSymbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder romanNumeral = new StringBuilder();
        for (int i = 0; i < arabicValues.length; i++) {
            while (number >= arabicValues[i]) {
                romanNumeral.append(romanSymbols[i]);
                number -= arabicValues[i];
            }
        }
        return romanNumeral.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("ввведите пример с арабскими или римскими числами от 1 до 10, без пробелов");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(calc(input));
    }
}