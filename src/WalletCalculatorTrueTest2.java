public class WalletCalculatorTrueTest2 {
    Wallet testWallet;

    public void walletTest() {
        testWallet = new Wallet();
        System.out.print("Wallet = " + testWallet.getMoney());
        testWallet.addMoney(500);
        System.out.print("\nYou got 500 \n");
        System.out.print("New Wallet value = " + testWallet.getMoney() + "\n");

        if (testWallet.getMoney() == 1500) {
            System.out.print("\n1. Test succes\n\n");
            System.out.print("\nYou got 345 \n");
            testWallet.addMoney(345);
            System.out.print("New Wallet value = " + testWallet.getMoney() + "\n");

            if (testWallet.getMoney() == 1845) {
                System.out.print("\n2. Test succes\n\n");
                System.out.print("\nYou lost 710 \n");
                testWallet.substractMoney(710);
                System.out.print("New Wallet value = " + testWallet.getMoney() + "\n");
                if (testWallet.getMoney() == 1135) {
                    System.out.print("\n3. Test succes\n\n");
                    System.out.print("\nwalletCalculatorTrue_Test2 succes\n\n");
                } else {
                    System.out.print("nwalletCalculatorTrue_Test2 Failed \n");
                }
            } else {
                System.out.print("nwalletCalculatorTrue_Test2 Failed \n");
            }
        } else {
            System.out.print("nwalletCalculatorTrue_Test2 Failed \n");
        }
    }
}
