package BackUp07012020.loginSystem;

import java.util.Random;

public class RandomNumberGenerator {

    static Random rand = new Random();

    public static int randomGenerator() {
        int x = rand.nextInt(999999)+100000;
        return x;
    }

    public static void main(String args[]) {
        System.out.println(RandomNumberGenerator.randomGenerator());
    }

}
