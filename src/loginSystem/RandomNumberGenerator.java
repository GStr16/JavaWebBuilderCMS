package loginSystem;

import java.util.Random;
import java.util.Scanner;
public class RandomNumberGenerator {

    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);
    public static int randomGenerator() {
        int x = rand.nextInt(999999)+100000;
        return x;
    }

    public static void main(String args[]) {

        int codeRequest = RandomNumberGenerator.randomGenerator();
        System.out.println(codeRequest);
        System.out.print("Enter your code here:");
        boolean verified = false;
        while(true) {
            int code = sc.nextInt();
            if (code == codeRequest) {
                System.out.println("Your code is correct");
                verified = true;
                System.out.println(verified);
                break;
            }
            else
                System.out.println("Please try again");
        }
    }
}