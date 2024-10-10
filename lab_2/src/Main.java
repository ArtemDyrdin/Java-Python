import java.util.Arrays;
import java.lang.String;
import java.util.Scanner;

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

    static int[] task_2(int[] ar1, int[] ar2) {
        int a_l = ar1.length;
        int b_l = ar2.length;
        int[] result = new int[a_l + b_l];
        int result_idx = 0;
        for (int i: ar1) {
            result[result_idx] = i;
            result_idx++;
        }
        for (int j: ar2) {
            result[result_idx] = j;
            result_idx++;
        }
        Arrays.sort(result);
        return result;
    }

    static int task_3(int [] sequence) {
        System.out.print("Введите длину последовательности: ");
        int n = in.nextInt();
        int max_res = 0;
        int start = 0;
        while (start != sequence.length - n) {
            int res = 0;
            for (int i = start; i < start + n; i++)
                res += sequence[i];
            if (res > max_res) max_res = res;
            start++;
        }
        return max_res;
    }

    public static void main(String[] args) {
        System.out.print("Введите номер задания: ");
        int task_number = in.nextInt();
        in.nextLine();
        switch (task_number) {
            case 1 -> {
                System.out.println("1. Найти наибольшую подстроку без повторяющихся символов");
                System.out.println(task_1());
            }
            case 2 -> {
                System.out.println("2. Объединить два отсортированных массива");
                int[] a = {6 ,9, 3, 0, 7};
                int[] b = {8, 1, 5, 2 ,4};
                int[] result = task_2(a, b);
                for (int j : result) System.out.print(j + " ");
            }
            case 3 -> {
                System.out.println("3. Найти максимальную сумму подмассива");
                int[] a = {6, 8, 9, 1, 0, 7};
                System.out.println(task_3(a));
            }
            case 4 -> System.out.println("4. Повернуть массив на 90 градусов по часовой стрелке");
            case 5 -> System.out.println("5. Найти пару элементов в массиве, сумма которых равна заданному числу");
            case 6 -> System.out.println("6. Найти сумму всех элементов в двумерном массиве");
            case 7 -> System.out.println("7. Найти максимальный элемент в каждой строке двумерного массива");
            case 8 -> System.out.println("8. Повернуть двумерный массив на 90 градусов против часовой стрелке");
        }
    }
}