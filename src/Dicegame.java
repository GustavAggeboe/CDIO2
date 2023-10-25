// Er starten på programmet, da den indeholder main(). Her definerer vi spillets attributter, som er af simpel type.
// Vi definerer altså ikke felterne her, men i Field.java.
class Dicegame {
    static java.util.Scanner scanner;
    static Game game;
    static int noOfPlayers = 2;
    static int noOfDice = 2;
    static int startMoney = 1000;
    static int moneyToWin = 3000;
    static int noOfFields = 11;

    public static void main(String[] args) {
        System.out.print("\nWELCOME TO THE NEW GAME \n\nType 'Start' to begin or 'quit' to quit\n");
        Dicegame.scanner = new java.util.Scanner(System.in);
        String input = Dicegame.scanner.nextLine();
        if (input.equals("RunTest1")) {
            new walletIllegalArgumentException_Test1().walletTest();
        }
        else if (input.equals("RunTest2")) {
            new walletCalculatorTrue_Test2().walletTest();
        }
        else if (input.equals("Start") || input.equals("start")) {
            game = new Game();
            game.initializeGame();
            runTest();
        }
    }

    static boolean runTheTest = false;

    private static void runTest() {
        if (runTheTest)
            game.runTest();
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
