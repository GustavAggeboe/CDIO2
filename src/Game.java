// Håndterer selve spillets logik

public class Game {
    public static Game Singleton;
    java.util.Scanner scanner;
    public static Field[] fields;

    Player[] players;
    private int playerTurn;
    Player currentPlayer;
    int currentPlayerSetupCounter = 1;

    public enum GameStates {
        Welcome,
        PlayerSetup,
        PlayerBeginTurn,
        PlayerRolling,
        PlayerShowResult
    }
    GameStates gameState = GameStates.Welcome;

    Game() {
        if (Singleton == null)
            Singleton = this;
        else
            return;

        welcomeMessage();
    }

    private void setGameState(GameStates newGameState) {
        gameState = newGameState;
        GamePanel.Singleton.UpdateUI();
    }

    private void welcomeMessage() {
        setGameState(GameStates.Welcome);
    }

    public void setupGame() {
        currentPlayerSetupCounter = 0;
        initializePlayers();
        initializeFields();

        setGameState(GameStates.PlayerSetup);
    }

    private void initializePlayers() {
        players = new Player[Dicegame.NO_OF_PLAYERS];
    }
    private void initializeFields() {
        fields = Field.initializeFields();
    }

    public void createPlayer(String playerName) {
        players[currentPlayerSetupCounter] = new Player(playerName);

        if (currentPlayerSetupCounter >= Dicegame.NO_OF_PLAYERS - 1) {
            startGame();
        } else {
            currentPlayerSetupCounter++;
        }
    }

    private void startGame() {
        setupTurn();
    }

    private void setupTurn() {
        // Der bliver holdt styr på, hvis tur det er, i variablen currentPlayer.
        currentPlayer = players[playerTurn];

        setGameState(GameStates.PlayerBeginTurn);
    }

    public void playerRoll() {
        setGameState(GameStates.PlayerRolling);

        /*try {
            wait(Dicegame.ROLL_WAIT_TIME_MILLIS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        currentPlayer.rollDice();
        playerResult();
    }

    private void playerResult() {
        showPlayerRoll(); // Vi viser hvad spilleren har slået i konsollen.
        fieldLogic(); // Vi kører logikken for, hvad der sker på det felt spilleren har landet på.
        playerStats(); // Vi viser spillerens nuværende penge.

        setGameState(GameStates.PlayerShowResult);
    }

    private void fieldLogic() {
        for (Field field : fields) {
            if (field.getSpace() == currentPlayer.getSumOfDice()) { // Hvis dette er sandt, så har spilleren landet på
                                                                    // dette felt.
                                                                    // Print hvad feltet har som beskrivelse

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
                // Hvis vi fandt feltet, som spilleren landede på, så returnerer vi ud af
                // fieldLogic(). Ellers bliver fejlbeskeden nedenfor vist.
                return;
            }
        }
        throw new Error("Kunne ikke finde feltet: '" + currentPlayer.getSumOfDice() + "'.");
    }

    // #region Funktioner i turn-logikken - Nok lidt ligegyldigt at kigge særligt
    // meget mere på.
    private void showPlayerRoll() {
        String diceValuesString = "";
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
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

    public void nextPlayerTurn() {
        if (!playerGetsBonusTurn()) {
            playerTurn++;
            // Hvis playerTurn er større end antallet af spillere, så gå tilbage til
            // spilleren på plads 0.
            if (playerTurn >= Dicegame.NO_OF_PLAYERS)
                playerTurn = 0;
        }

        setupTurn();
    }

    public boolean playerGetsBonusTurn() {
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
        if (input.equalsIgnoreCase("play"))
            setupGame();
        else if (input.equalsIgnoreCase("quit"))
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
