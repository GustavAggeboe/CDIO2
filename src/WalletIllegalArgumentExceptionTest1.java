public class WalletIllegalArgumentExceptionTest1 {
    Wallet testWallet;

    public void walletTest() {
        testWallet = new Wallet();
        System.out.print("Wallet = " + testWallet.getMoney());
        testWallet.substractMoney(900);
        System.out.print("\nYou lost 900 \n");
        System.out.print("New Wallet value = " + testWallet.getMoney() + "\n");
        testWallet.substractMoney(500);
        System.out.print("\nYou lost 500 \n");
        System.out.print("New Wallet value = " + testWallet.getMoney() + "\n");
        if (testWallet.getMoney() == 0) {
            System.out.print("walletIllegalArgumentException_Test1 succes");

        } else {
            System.out.print("walletIllegalArgumentException_Test1 failed");
        }
    }
}
