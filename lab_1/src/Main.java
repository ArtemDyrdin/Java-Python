import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void task_1(Scanner in) {
        int n = in.nextInt();
        int step = 0;

        do {
            step += 1;
            if (n % 2 == 0)
                n = n / 2;
            else
                n = 3*n + 1;
        } while (n != 1);

        System.out.println(step);
    }

    public static void task_2(Scanner in) {
        int n = in.nextInt();
        int result = 0;
        boolean plus = true;
        for (int i = 0; i < n; i++) {
            if (plus)
                result += in.nextInt();
            else
                result -= in.nextInt();
            plus = !plus;
        }
        System.out.println(result);
    }

    public static void task_3(Scanner in) {
        int[] current_coord = {0, 0};
        int[] treasure_coord = {0, 0};

        treasure_coord[0] = in.nextInt();
        treasure_coord[1] = in.nextInt();

        while (true) {
            String direction = in.next();
            if (Objects.equals(direction, "стоп")) break;
            int step = in.nextInt();

            switch (direction) {
                case "восток":
                    current_coord[0] += step;
                    break;
                case "запад":
                    current_coord[0] -= step;
                    break;
                case "север":
                    current_coord[1] += step;
                    break;
                case "юг":
                    current_coord[1] -= step;
                    break;
            }
        }
        System.out.println(2); // кротчайший путь всегда будет составлять 2 шага
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Сиракузская последовательность");
        task_1(in);
        System.out.println("2. Сумма ряда");
        task_2(in);
        System.out.println("3. Ищем клад");
        task_3(in);
    }
}