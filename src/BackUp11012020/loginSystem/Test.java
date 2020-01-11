package BackUp11012020.loginSystem;

class TimerVerificationCoder extends Thread {

    RandomNumberGenerator x = new RandomNumberGenerator();
    public void run() {
        int i = 0;
        int rand = x.randomGenerator();
        System.out.println(rand);
        try {

            while(true) {
                i++;
                System.out.println(i);
                Thread.sleep(1000);

                if(i >=9) {
                    rand = x.randomGenerator();
                    System.out.println("Your code: "+ rand);
                    i=0;
                }

            }
        }
        catch(InterruptedException e) {
            System.out.println("Error - " +e.getMessage());
        }
    }
}

public class Test {
    public static void main(String args[]) {
        TimerVerificationCoder tcv = new TimerVerificationCoder();
        tcv.start();
    }
}
