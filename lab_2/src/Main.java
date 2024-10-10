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
        System.out.print("Введите длину подмассива: ");
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

    static int[][] task_4(int[][] a) {
        int a_strs = a.length;
        int a_cols = a[0].length;
        int[][] res = new int[a_cols][a_strs];
        for (int i = a_strs - 1; i >= 0; i--)
            for (int j = 0; j < a_cols; j++)
                res[j][a_strs - i - 1] = a[i][j];
        return res;
    }

    static int[] task_5(int[] a, int target) {
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++)
                if (i != j)
                    if (a[i] + a[j] == target)
                        return new int[] {a[i], a[j]};
        return null;
    }

    static int task_6(int[][] a) {
        int sum = 0;
        for (int[] str: a)
            for (int el: str)
                sum += el;
        return sum;
    }

    static int[] task_7(int[][] a) {
        int[] result = new int[a.length];
        for (int str = 0; str < a.length; str++) {
            int max_el = Integer.MIN_VALUE;
            for (int el = 0; el < a[str].length; el++)
                if (max_el < a[str][el])
                    max_el = a[str][el];
            result[str] = max_el;
        }
        return result;
    }

    static int[][] task_8(int[][] a) {
        int a_strs = a.length;
        int a_cols = a[0].length;
        int[][] res = new int[a_cols][a_strs];
        for (int i = 0; i < a_strs; i++)
            for (int j = a_cols - 1; j >= 0; j--)
                res[a_cols - j - 1][i] = a[i][j];
        return res;
    }

    public static void main(String[] args) {
        while (true) {
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
                    int[] a = {6, 9, 3, 0, 7};
                    int[] b = {8, 1, 5, 2, 4};
                    System.out.println(Arrays.toString(task_2(a, b)));
                }
                case 3 -> {
                    System.out.println("3. Найти максимальную сумму подмассива");
                    int[] a = {6, 8, 9, 1, 0, 7};
                    System.out.println(task_3(a));
                }
                case 4 -> {
                    System.out.println("4. Повернуть массив на 90 градусов по часовой стрелке");
                    int[][] a = {
                            {3, 6, 9, 12},
                            {2, 5, 8, 11},
                            {1, 4, 7, 10}};
                    int[][] result = task_4(a);
                    for (int[] str : result) {
                        for (int el : str)
                            System.out.print(el + " ");
                        System.out.println();
                    }
                }
                case 5 -> {
                    System.out.println("5. Найти пару элементов в массиве, сумма которых равна заданному числу");
                    int[] a = {1, 10, 3, 14, 5};
                    System.out.print("Введите искомую сумму: ");
                    int target = in.nextInt();
                    System.out.println(Arrays.toString(task_5(a, target)));
                }
                case 6 -> {
                    System.out.println("6. Найти сумму всех элементов в двумерном массиве");
                    int[][] a = {
                            {1, 2, 3, 4, 5},
                            {6, 7, 8, 9, 10}};
                    System.out.println(task_6(a));
                }
                case 7 -> {
                    System.out.println("7. Найти максимальный элемент в каждой строке двумерного массива");
                    int[][] a = {
                            {5, 3, 2, 4, 1},
                            {6, 10, 9, 8, 7},
                            {13, 14, 11, 12}};
                    System.out.println(Arrays.toString(task_7(a)));
                }
                case 8 -> {
                    System.out.println("8. Повернуть двумерный массив на 90 градусов против часовой стрелке");
                    int[][] a = {
                            {10, 7, 4, 1},
                            {11, 8, 5, 2},
                            {12, 9, 6, 3}};
                    int[][] result = task_8(a);
                    for (int[] str : result) {
                        for (int el : str)
                            System.out.print(el + " ");
                        System.out.println();
                    }
                }
            }
        }
    }
}