// Er starten på programmet, da den indeholder main(). Her definerer vi spillets attributter, som er af simpel type.
// Vi definerer altså ikke felterne her, men i Field.java.
public class Dicegame {
    static final int NO_OF_PLAYERS = 2;
    static final int NO_OF_DICE = 2;
    static final int START_MONEY = 1000;
    static final int MONEY_TO_WIN = 3000;
    static final int NO_OF_FIELDS = 11;
    static final int ROLL_WAIT_TIME_MILLIS = 2000;

    public static void main(String[] args) {
        //System.out.print("\nWELCOME TO THE NEW GAME \n\nType 'Start' to begin or 'quit' to quit\n");
        new GameFrame();
        new Game();
    }
}

/* ----------- TODO ----------- */
// *
