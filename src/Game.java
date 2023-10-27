// Håndterer selve spillets logik

public class Game {
    java.util.Scanner scanner;
    static boolean runTheTest = false;

    Field[] fields;

    Player[] players;
    private int playerTurn;
    Player currentPlayer;

    public Game() {
        scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("RunTest1")) {
            new WalletIllegalArgumentExceptionTest1().walletTest();
        } else if (input.equals("RunTest2")) {
            new WalletCalculatorTrueTest2().walletTest();
        } else if (input.equals("Start") || input.equals("start")) {
            if (runTheTest)
                runTest();
            else {
                initializeGame();
            }
        }
    }

    public void initializeGame() {
        // runTest();
        initializePlayers();
        initializeFields();

        startGame();
    }

    private void initializePlayers() {
        players = new Player[Dicegame.noOfPlayers];
        for (int i = 0; i < Dicegame.noOfPlayers; i++) {
            print("Please enter the name for Player " + (i + 1) + ".");
            players[i] = new Player(scanner.nextLine());

        }
    }

    private void initializeFields() {
        fields = Field.initializeFields();
    }

    private void startGame() {
        takePlayerTurn();
    }

    private void takePlayerTurn() { // Alt hvad der sker på en tur, sker her fra.
        currentPlayer = players[playerTurn];
        // Der bliver holdt styr på, hvis tur det er, i variablen playerTurn.
        print("\n" + currentPlayer.getPlayerName() + "'s turn. Type 'roll' to throw the dice.");

        waitForRollInput(); // Her venter koden til spilleren har givet inputet 'roll'.

        // Der er nu blevet rullet med terninger.

        showPlayerRoll(); // Vi viser hvad spilleren har slået i konsollen.
        fieldLogic(); // Vi kører logikken for, hvad der sker på det felt spilleren har landet på.
        playerStats(); // Vi viser spillerens nuværende penge.

        nextPlayerTurn(); // Gå videre til næste persons tur.
    }

    private void fieldLogic() {
        for (Field field : fields) {
            if (field.getSpace() == currentPlayer.getSumOfDice()) { // Hvis dette er sandt, så har spilleren landet på
                                                                    // dette felt.
                                                                    // Print hvad feltet har som beskrivelse
                print("You landed on " + field.getName());
                print(field.getLandingDescription());
                // Påfør ændring i spillers penge, alt efter hvilken effekt feltet har, angivet
                // i initialiseringen af feltet i Field.java.
                switch (field.getFieldEffect()) {
                    case neutral:
                        break;
                    case positive:
                        currentPlayer.wallet().addMoney(field.getValue());
                        break;
                    case negative:
                        currentPlayer.wallet().substractMoney(field.getValue());
                        print("You lost " + field.getValue() + " money =(\n");
                        break;
                }
                // Hvis vi fandt feltet, som spilleren landede på, så retunerer vi ud af
                // fieldLogic(). Ellers bliver fejlbeskeden nedenfor vist.
                return;
            }
        }
        throw new Error("Kunne ikke finde feltet: '" + currentPlayer.getSumOfDice() + "'.");
    }

    // #region Funktioner i turn-logikken - Nok lidt ligegyldigt at kigge særligt
    // meget mere på.
    private void waitForRollInput() {
        String input = scanner.nextLine();
        if (input.toLowerCase().equals("roll"))
            currentPlayer.rollDice();
        else {
            print("Could not find a command for input: '" + input + "'. Type 'roll' to throw the dice.");
            waitForRollInput();
        }
    }

    private void showPlayerRoll() {
        String diceValuesString = new String();
        for (int i = 0; i < Dicegame.noOfDice; i++) {
            if (i > 0)
                diceValuesString += "\n";
            diceValuesString += "Dice " + (i + 1) + ": " + currentPlayer.getDieValue(i);
        }
        print("\n" + diceValuesString);
        print("Dice Sum: " + currentPlayer.getSumOfDice() + "\n");
    }

    private void playerStats() {
        // Vis spillerens stats
        print(currentPlayer.getPlayerName() + ", you now have " + currentPlayer.wallet().getMoney()
                + " money.");
        // Check om spilleren har vundet
        if (currentPlayer.checkForMoneyToWin())
            playerWon();
    }

    private void nextPlayerTurn() {
        // Forbliver på samme playerTurn, hvis spiller har bonustur, eller plus med 1,
        // så det bliver den næstes tur.
        if (playerGetsBonusTurn())
            print("You got an extra turn =)");
        else
            incrementPlayerTurn();
        // Næste tur starter
        takePlayerTurn();
    }

    private void incrementPlayerTurn() {
        playerTurn++;
        // Hvis playerTurn er større end antallet af spillere, så gå tilbage til
        // spilleren på plads 0.
        if (playerTurn >= Dicegame.noOfPlayers)
            playerTurn = 0;
    }

    private boolean playerGetsBonusTurn() {
        for (Field field : fields) {
            if (field.getSpace() == currentPlayer.getSumOfDice()) {
                return field.grantsBonusTurn();
            }
        }
        throw new Error("Kunne ikke finde feltet: '" + currentPlayer.getSumOfDice() + "'.");
    }

    private void playerWon() {
        print(currentPlayer.getPlayerName() + " won! You got the required amount of money to win!");
        EndMessage();
    }

    private void EndMessage() {
        print("--- Game Stats ---");

        for (Player player : players) {
            print(player.getStats());
        }

        print("\nType 'play' to play again. Type 'quit' to quit the game.");

        String input = scanner.nextLine();
        if (input.toLowerCase().equals("play"))
            initializeGame();
        else if (input.toLowerCase().equals("quit"))
            scanner.close();
        else {
            print("Could not find a command for input: '" + input + "'.");
            EndMessage();
        }
    }
    // #endregion

    public void runTest() {
        var test = new Test();
        print(test.testThrow(1000));
        print(test.alike(1000));
    }

    public static void print(String string) {
        System.out.println(string);
    }

}
