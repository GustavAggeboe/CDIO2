public class Wallet {
    private int money;

    public Wallet() {
        money = Dicegame.startMoney;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int moneyToAdd) { // Vi vil kun modtage et positivt tal
        if (moneyToAdd < 0) {
            throw new IllegalArgumentException(
                    "Fejl i method call. Når der skal tilføjes penge, skal argumentet være højere end 0. Argument modtaget: "
                            + moneyToAdd);
        } else {
            money += moneyToAdd;
            money = Math.min(money, Dicegame.moneyToWin); // Begrænser til maxMoneyToWin
        }
    }

    public void substractMoney(int moneyToSubtract) { // Vi vil kun modtage et positivt tal
        if (moneyToSubtract < 0) {
            throw new IllegalArgumentException(
                    "Fejl i method call. Når der skal fratages penge, skal argumentet være højere end 0. Argument modtaget: "
                            + moneyToSubtract);
        } else {
            money -= moneyToSubtract;
            money = Math.max(money, 0); // Begrænser til 0
        }
    }
}
