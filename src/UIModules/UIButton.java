package UIModules;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIButton extends JButton {
    public static List<JButton> list = new ArrayList<>();
    public UIButton(String buttonText, String actionCommand) {
        setText(buttonText);
        setBackground(Color.lightGray);

        setActionCommand(actionCommand);
        setPreferredSize(new Dimension(100, 50));
        addActionListener(UIUtils.AL);
        list.add(this);
        UIUtils.addToJList(this);
    }

}
