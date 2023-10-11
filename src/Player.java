class Player {
    private String name;
    private Dice[] dice;
    private Wallet wallet;

    public Player(String name) {
        this.name = name;
        this.wallet = new Wallet();

        initializePlayerDice();
    }

    private void initializePlayerDice() {
        dice = new Dice[Dicegame.noOfDice];
        for (int i = 0; i < Dicegame.noOfDice; i++) {
            dice[i] = new Dice();
        }
    }

    public void rollDice() {
        for (int i = 0; i < Dicegame.noOfDice; i++) {
            dice[i].rollDie();
        }
    }

    public boolean checkForMoneyToWin() {
        return wallet.getMoney() == Dicegame.moneyToWin;
    }

    public int getDieValue(int die) {
        return dice[die].getValue();
    }

    public int getSumOfDice() {
        int sum = 0;
        for (int i = 0; i < Dicegame.noOfDice; i++) {
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
