public class Player {
    private String name;
    private Dice[] dice;
    private Wallet wallet;

    public Player(String name) {
        this.name = name;
        this.wallet = new Wallet();

        initializePlayerDice();
        Game.print("Created player: " + this.name);
    }

    private void initializePlayerDice() {
        dice = new Dice[Dicegame.NO_OF_DICE];
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            dice[i] = new Dice();
        }
    }

    public void rollDice() {
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            dice[i].rollDie();
        }
    }

    public boolean checkForMoneyToWin() {
        return wallet.getMoney() == Dicegame.MONEY_TO_WIN;
    }

    public int getDieValue(int die) {
        return dice[die].getValue();
    }

    public int getSumOfDice() {
        int sum = 0;
        for (int i = 0; i < Dicegame.NO_OF_DICE; i++) {
            sum += dice[i].getValue();
        }
        return sum;
    }

    public String getPlayerName() {
        return name;
    }

    public String getStats() {
        return name + " got " + wallet.getMoney() + " money.";
    }

    // Player wallet
    public Wallet wallet() {
        return wallet;
    }
}
