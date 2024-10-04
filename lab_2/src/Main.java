import java.util.Scanner;
import java.lang.String;

public class Main {
    static Scanner in = new Scanner(System.in);

    static String task_1() {
        System.out.print("Строка: ");
        String text = in.nextLine();
        String max_substr = "";

        boolean stop = false;
        int last_idx = 0;
        while (!stop) {
            String current_substr = "";
            for (int i = last_idx; i < text.length(); i++) {
                if (current_substr.contains(String.valueOf(text.charAt(i)))) {
                    if (max_substr.length() < current_substr.length())
                        max_substr = current_substr;
                    last_idx++; // после каждой подстроки продолжаем цикл со следующего символа
                    break;
                }
                else {
                    current_substr += text.charAt(i);
                    // если дошли до последнего элемента текста
                    if (i == text.length() - 1) {
                        if (max_substr.length() < current_substr.length())
                            max_substr = current_substr;
                        stop = true;
                    }
                }
            }
        }
        return max_substr;
    }

    public static void main(String[] args) {
        System.out.print("Введите номер задания: ");
        int task_number = in.nextInt();
        in.nextLine();
        switch (task_number) {
            case 1 -> {
                System.out.println("1. Найти наибольшую подстроку без повторяющихся символов");
                String a = task_1();
                System.out.println(a);
            }
            case 2 -> {
                System.out.println("2. Объединить два отсортированных массива");
            }
            case 3 -> System.out.println("3. Найти максимальную сумму подмассива");
            case 4 -> System.out.println("4. Повернуть массив на 90 градусов по часовой стрелке");
            case 5 -> System.out.println("5. Найти пару элементов в массиве, сумма которых равна заданному числу");
            case 6 -> System.out.println("6. Найти сумму всех элементов в двумерном массиве");
            case 7 -> System.out.println("7. Найти максимальный элемент в каждой строке двумерного массива");
            case 8 -> System.out.println("8. Повернуть двумерный массив на 90 градусов против часовой стрелке");
        }
    }
}