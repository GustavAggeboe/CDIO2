import java.util.Random;
class Dice {
    private int faceValue;
    
    //initialize random number generator
	Random random = new Random();

    public void rollDie() {
        faceValue = random.nextInt(6) + 1;
    }

    public int getValue() {
        return faceValue;
    }
}
