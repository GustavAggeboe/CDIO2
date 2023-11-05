// Håndterer UI-elementer

import UIModules.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    public static GamePanel Singleton;
    static final int SCREEN_WIDTH = 1024, SCREEN_HEIGHT = 720, UNIT_SIZE = 50, GAME_UNITS = SCREEN_WIDTH*SCREEN_HEIGHT/UNIT_SIZE;
    static final Color backgroundColor = Color.darkGray;

    //region UIModules

    // - UIText
    UIText titleText, instructionText, fieldDescriptionText, playerTurnNameText;
    // - UIButton
    UIButton startButton, submitPlayerButton, rollButton, nextPlayerTurnButton;
    // - UITextField
    JTextField playerNameTextField;
    // - UIDiceAnimation
    JLabel diceAnimation = new UIDiceAnimation("diceroll.gif");

    //endregion

    GamePanel() {
        // ** Sæt singleton **
        if (Singleton == null)
            Singleton = this;
        else
            return;

        // ** Indstillinger **
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(backgroundColor);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        UIUtils.setAL(this);

        // ** Moduler ** - Der SKAL først initialiseres hernede, efter UIUtils.setAL(this); er blevet kaldt.
        titleText = new UIText();
        instructionText = new UIText();
        fieldDescriptionText = new UIText();
        playerTurnNameText = new UIText();
        startButton = new UIButton("Start", "start");
        submitPlayerButton = new UIButton("Submit player", "submitPlayer");
        rollButton = new UIButton("Roll dice", "roll");
        nextPlayerTurnButton = new UIButton("Next player", "nextPlayerTurn");
        playerNameTextField = new UIInputField();
        diceAnimation = new UIDiceAnimation("diceroll.gif");

        for (JComponent jComponent : UIUtils.getJComponents()) {
            this.add(jComponent);
        }
    }

    public void UpdateUI () {
        clearUI(); // Start med at rydde UI'en
        switch (Game.Singleton.gameState) { // Derefter vis kun det der skal vises
            case Welcome:
                titleText.setText("Welcome to Dice Game!");
                instructionText.setText("Press start to begin.");

                startButton.setVisible(true);
                break;

            case PlayerSetup:
                titleText.setText("Player setup");
                instructionText.setText("Please enter a name for player " + (Game.Singleton.currentPlayerSetupCounter + 1) + ", and press 'Submit player'.");

                playerNameTextField.setVisible(true);
                submitPlayerButton.setVisible(true);
                break;

            case PlayerBeginTurn:
                playerTurnNameText.setText(Game.Singleton.currentPlayer.getPlayerName() + "'s turn.");
                instructionText.setText("Press the Roll button to roll the dice.");

                rollButton.setVisible(true);
                break;

            case PlayerRolling:
                titleText.setText("Rolling...");

                diceAnimation.setVisible(true);
                break;

            case PlayerShowResult:
                for (Field field : Game.fields) {
                    if (field.getSpace() == Game.Singleton.currentPlayer.getSumOfDice()) {
                        fieldDescriptionText.setText("You landed on " + field.getName() + ". " + field.getLandingDescription());
                    }
                }
                // HUSK AT VIS HVAD TERNINGEN VISER
                if (Game.Singleton.playerGetsBonusTurn())
                    rollButton.setVisible(true);
                else
                    nextPlayerTurnButton.setVisible(true);
                break;
        }
    }

    private void clearUI() { // Rydder UI'en, inden der bliver valgt, hvad der skal være på skærmen
        for (JLabel module : UIText.list) {
            module.setText("");
        }
        for (JButton module : UIButton.list) {
            module.setVisible(false);
        }
        for (JTextField module : UIInputField.list) {
            module.setText("");
            module.setVisible(false);
        }
        for (JLabel module : UIDiceAnimation.list) {
            module.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Bliver kaldt når brugeren trykker på en knap
        switch (e.getActionCommand()) {
            case "submitPlayer":
                Game.Singleton.createPlayer(playerNameTextField.getText());
                UpdateUI();
                break;
            case "roll":
                Game.Singleton.playerRoll();
                UpdateUI();
                break;
            case "start":
                Game.Singleton.setupGame();
                UpdateUI();
                break;
            case "nextPlayerTurn":
                Game.Singleton.nextPlayerTurn();
                UpdateUI();
                break;
        }
    }

    public void paintComponent(Graphics g) { // Indbygget funktion til at tegne UI'en på skærmen
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) { // Ligesom ovenfor... bare... anderledes...
        /*if (Game.fields != null) {
            g.setColor(Color.lightGray);
            int fieldSize = Field.scale * UNIT_SIZE;
            for (int i = 0; i < Game.fields.length; i++) {
                g.fillRoundRect(i * fieldSize, SCREEN_HEIGHT/2,fieldSize,fieldSize, fieldSize / 2, fieldSize / 2);
            }
        }*/
    }

    public class MyKeyAdapter extends KeyAdapter { // Bliver kaldt når der bliver trykket på tastaturet
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (Game.Singleton.gameState == Game.GameStates.PlayerSetup) { // Virker vidst ikke endnu
                if (key == KeyEvent.VK_ENTER) {
                    Game.Singleton.createPlayer(playerNameTextField.getText());
                    UpdateUI();
                }
            }
        }
    }
}
