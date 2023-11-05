package UIModules;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIInputField extends JTextField {
    public static List<JTextField> list = new ArrayList<>();
    public UIInputField() {
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension(200, 50));
        addActionListener(UIUtils.AL);
        list.add(this);
        UIUtils.addToJList(this);
    }
}
