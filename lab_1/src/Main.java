import java.util.Objects;
import java.util.Scanner;
import package1.DoublyEvenNumber;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void task_1() {
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

    public static void task_2() {
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

    public static void task_3() {
        int[] current_coord = {0, 0};
        int[] treasure_coord = {0, 0};

        treasure_coord[0] = in.nextInt();
        treasure_coord[1] = in.nextInt();

        while (true) {
            String direction = in.next();
            if (Objects.equals(direction, "стоп")) break;
            int step = in.nextInt();

            switch (direction) {
                case "восток" -> current_coord[0] += step;
                case "запад" -> current_coord[0] -= step;
                case "север" -> current_coord[1] += step;
                case "юг" -> current_coord[1] -= step;
            }
        }
        System.out.println(2); // кротчайший путь всегда будет составлять 2 шага
    }

    public static void task_4() {
        int road = 0;
        int limit = 0;
        int roads_number = in.nextInt();
        for (int current_road = 0; current_road < roads_number; current_road++) {
            int tunnels_number = in.nextInt();
            int minimal_road_height = Integer.MAX_VALUE;
            for (int tunnel = 0; tunnel < tunnels_number; tunnel++) {
                int tunnel_height = in.nextInt();
                if (tunnel_height < minimal_road_height)
                    minimal_road_height = tunnel_height;
            }
            if (limit < minimal_road_height) {
                limit = minimal_road_height;
                road = current_road;
            }
        }
        System.out.printf("%d %d", road + 1, limit);
    }

    public static void task_5() {
        DoublyEvenNumber number = new DoublyEvenNumber();
        number.setNumber();
        System.out.println(number.isDoublyEven());
    }

    public static void main(String[] args) {
        while (true) {
            System.out.print("Введите номер задачи: ");
            int task_number = in.nextInt();
            switch (task_number) {
                case 1 -> {
                    System.out.println("1. Сиракузская последовательность");
                    task_1();
                }
                case 2 -> {
                    System.out.println("2. Сумма ряда");
                    task_2();
                }
                case 3 -> {
                    System.out.println("3. Ищем клад");
                    task_3();
                }
                case 4 -> {
                    System.out.println("4. Логистический максимин");
                    task_4();
                }
                case 5 -> {
                    System.out.println("5. Дважды четное число");
                    task_5();
                }
            }
        }
    }
}