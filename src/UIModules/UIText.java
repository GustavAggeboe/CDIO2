package UIModules;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIText extends JLabel {
    public static List<JLabel> list = new ArrayList<>();
    public UIText() {
        setHorizontalTextPosition(JLabel.CENTER);
        setForeground(Color.lightGray);
        setFont(new Font("Verdana", Font.PLAIN,32));
        setPreferredSize(new Dimension(200, 50));
        list.add(this);
        UIUtils.addToJList(this);
    }
}
