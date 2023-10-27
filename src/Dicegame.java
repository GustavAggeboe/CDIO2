// Er starten på programmet, da den indeholder main(). Her definerer vi spillets attributter, som er af simpel type.
// Vi definerer altså ikke felterne her, men i Field.java.
public class Dicegame {
    static Game game;
    static int noOfPlayers = 2;
    static int noOfDice = 2;
    static int startMoney = 1000;
    static int moneyToWin = 3000;
    static int noOfFields = 11;

    public static void main(String[] args) {
        System.out.print("\nWELCOME TO THE NEW GAME \n\nType 'Start' to begin or 'quit' to quit\n");
        game = new Game();
    }

}

// ----------- Mangler -----------
/*
 * 
 * Beskrivelser for felter i Field.initializeFields() i Field.java.
 * 
 * Tests
 * 
 * 
 */
