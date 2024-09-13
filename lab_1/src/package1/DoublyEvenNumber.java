package package1;

import java.util.Scanner;

public class DoublyEvenNumber {
    int number;
    Scanner in = new Scanner(System.in);

    public void setNumber() {
        this.number = in.nextInt();
    }

    public boolean isDoublyEven() {
        int digits_sum = 0;
        int digits_mult = 1;

        int current_num = number;
        while (current_num != 0) {
            digits_sum += current_num % 10;
            digits_mult *= current_num % 10;
            current_num /= 10;
        }
        return digits_sum % 2 == 0 && digits_mult % 2 == 0;
    }
}
