import java.util.Random;

public class Dice {
    private int faceValue;

    // initialize random number generator
    Random random = new Random();
    final int MIN_VALUE = 1;
    final int MAX_VALUE = 6;

    public void rollDie() {
        faceValue = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
    }

    public int getValue() {
        return faceValue;
    }
}
