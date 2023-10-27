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

    // UI Moduler
    final JLabel titleText = new JLabel(),
            instructionText = new JLabel(),
            fieldDescriptionText = new JLabel(),
            playerTurnNameText = new JLabel();
    final JTextField playerNameField = new JTextField();
    final JButton button_submitPlayer = new JButton("Submit player"),
            button_roll = new JButton("Roll dice"),
            button_start = new JButton("Start"),
            button_nextPlayerTurn = new JButton("Next player");
    JLabel diceRollAnimationLabel = new JLabel();
    ImageIcon diceRollAnimation = new ImageIcon();

    enum DiceStates {
        Hidden,
        Rolling,
        Showing
    }
    DiceStates diceState = DiceStates.Hidden;

    GamePanel() {
        // Sæt singleton
        if (Singleton == null)
            Singleton = this;
        else
            return;

        // Indstillinger
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(backgroundColor);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        // Moduler
        setupJLabel(titleText);
        setupJLabel(instructionText);
        setupJLabel(fieldDescriptionText);
        setupJLabel(playerTurnNameText);
        setupJTextField(playerNameField);
        setupJButton(button_submitPlayer, "submitPlayer");
        setupJButton(button_roll, "roll");
        setupJButton(button_start, "start");
        setupJButton(button_nextPlayerTurn, "nextPlayerTurn");
        setupDiceAnimation();
    }

    private void setupJLabel(JLabel jLabel) {
        jLabel.setAlignmentX(0.5f);
        jLabel.setAlignmentY(0f);
        jLabel.setForeground(Color.lightGray);
        jLabel.setFont(new Font("Verdana", Font.PLAIN,32));
        jLabel.setPreferredSize(new Dimension(200, 50));
        this.add(jLabel);
        jLabel.setVisible(true);
    }
    private void setupJTextField(JTextField textField) {
        textField.setBackground(Color.lightGray);
        textField.setPreferredSize(new Dimension(200, 50));
        this.add(textField);
        textField.setVisible(false);
    }
    private void setupJButton(JButton jbutton, String actionCommand) {
        jbutton.setBackground(Color.lightGray);
        jbutton.setActionCommand(actionCommand);
        jbutton.setPreferredSize(new Dimension(100, 50));
        jbutton.addActionListener(this);
        this.add(jbutton);
        jbutton.setVisible(false);
    }

    private void setupDiceAnimation() {
        diceRollAnimation = new ImageIcon(this.getClass().getResource("diceroll.gif"));
        diceRollAnimationLabel.setIcon(diceRollAnimation);
        this.add(diceRollAnimationLabel);
        diceRollAnimationLabel.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Text
        // Fields
        if (Game.fields != null) {
            g.setColor(Color.lightGray);
            int fieldSize = Field.scale * UNIT_SIZE;
            for (int i = 0; i < Game.fields.length; i++) {
                g.fillRoundRect(i * fieldSize, SCREEN_HEIGHT/2,fieldSize,fieldSize, fieldSize / 2, fieldSize / 2);
            }
        }
        // Players
    }

    private void setTitleText(String text) {
        titleText.setText(text);
    }

    private void setInstructionText(String text) {
        instructionText.setText(text);
    }

    private void setFieldDescriptionText(String text) {
        fieldDescriptionText.setText(text);
    }

    private void setPlayerTurnNameText(String text) {
        playerTurnNameText.setText(text);
    }

    private void setTextFieldVisible(String textField, boolean isVisible) {
        switch (textField) {
            case "playerNameField":
                playerNameField.setVisible(isVisible);
                break;
        }
    }

    private void setButtonVisible(String button, boolean isVisible) {
        switch (button) {
            case "submitPlayer":
                button_submitPlayer.setVisible(isVisible);
                break;
            case "roll":
                button_roll.setVisible(isVisible);
                break;
            case "start":
                button_start.setVisible(isVisible);
            case "nextPlayerTurn":
                button_nextPlayerTurn.setVisible(isVisible);
                break;
        }
    }

    private void setDiceAnimationVisible(boolean isVisible) {
        diceRollAnimationLabel.setVisible(isVisible);
    }

    public void UpdateUI () {
        switch (Game.Singleton.gameState) {
            case Welcome:
                Game.print("yo");
                setTitleText("Welcome to Dice Game!");
                setInstructionText("Press start to begin.");
                setButtonVisible("start", true);
                setButtonVisible("nextPlayerTurn", false);
                break;
            case PlayerSetup:
                setTitleText("Player setup");
                setButtonVisible("start", false);
                setButtonVisible("submitPlayer", true);
                setTextFieldVisible("playerNameField", true);
                setInstructionText("Please enter a name for player " + (Game.Singleton.currentPlayerSetupCounter + 1) + ", and press 'Submit player'.");
                break;
            case PlayerBeginTurn:
                setButtonVisible("nextPlayerTurn", false);
                setTitleText("");
                setPlayerTurnNameText(Game.Singleton.currentPlayer.getPlayerName() + "'s turn.");
                setButtonVisible("submitPlayer", false);
                setTextFieldVisible("playerNameField", false);
                setInstructionText("Press the Roll button to roll the dice.");
                setButtonVisible("roll", true);
                setFieldDescriptionText("");
                break;
            case PlayerRolling:
                setButtonVisible("roll", false);
                setInstructionText("");
                setTitleText("Rolling...");
                diceState = DiceStates.Rolling;
                break;
            case PlayerShowResult:
                setInstructionText("");
                setTitleText("");
                for (Field field : Game.fields) {
                    if (field.getSpace() == Game.Singleton.currentPlayer.getSumOfDice()) {
                        setFieldDescriptionText("You landed on " + field.getName() + ". " + field.getLandingDescription());
                    }
                }
                diceState = DiceStates.Showing;

                if (Game.Singleton.playerGetsBonusTurn())
                    setButtonVisible("roll", true);
                else
                    setButtonVisible("nextPlayerTurn", true);

                break;
        }
        switch (diceState) {
            case Hidden:
                setDiceAnimationVisible(false);
                break;
            case Rolling:
                setDiceAnimationVisible(true);
                break;
            case Showing:
                setDiceAnimationVisible(false);
                break;
        }
    }

    public void submitPlayer () {
        Game.Singleton.createPlayer(playerNameField.getText());
        Singleton.playerNameField.setText("");
        UpdateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "submitPlayer":
                submitPlayer();
                break;
            case "roll":
                Game.Singleton.playerRoll();
                setButtonVisible("roll", false);
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

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (Game.Singleton.gameState == Game.GameStates.PlayerSetup) {
                if (key == KeyEvent.VK_ENTER) {
                    GamePanel.Singleton.submitPlayer();
                }
            }
        }
    }
}
