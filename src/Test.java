class Test {
    private int one = 0;
    private int two = 0;
    private int three = 0;
    private int four = 0;
    private int five = 0;
    private int six = 0;

    public String testThrow(int testCases) {
        var dicevalue = new Dice();
        for (int i = 0; i < testCases; i++) {
            switch (dicevalue.getValue()) {

                case 1:
                    one++;
                    break;

                case 2:
                    two++;
                    break;

                case 3:
                    three++;
                    break;

                case 4:
                    four++;
                    break;

                case 5:
                    five++;
                    break;

                case 6:
                    six++;
                    break;

                default:
                    break;

            }
        }
        return "Testcases: " + testCases + "\nOnes: " + one + "\ntwos: " + two + "\nthrees: " + three + "\nfour: "
                + four + "\nfive: " + five + "\nsix: " + six;
    }

    public String alike(int testCases) {
        var dice1 = new Dice();
        var dice2 = new Dice();
        double alike = 0;
        double notalike = 0;

        for (int i = 0; i < testCases; i++) {

            if (dice1.getValue() == dice2.getValue()) {
                alike++;
            } else {
                notalike++;
            }

        }
        double percentAlike = alike * 100 / testCases;
        double percentNotalike = notalike * 100 / testCases;
        return "Two identical dice: " + alike + " Percentage: " + percentAlike + "%" + "\nNot identical dice: "
                + notalike + " Percentage: " + percentNotalike + "%";
    }
}

class walletIllegalArgumentException_Test1 {
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

class walletCalculatorTrue_Test2 {
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
