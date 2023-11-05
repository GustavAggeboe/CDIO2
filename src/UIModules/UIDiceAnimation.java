package UIModules;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UIDiceAnimation extends JLabel {
    public static List<JLabel> list = new ArrayList<>();
    public UIDiceAnimation(String path) {
        setIcon(new ImageIcon(path));
        list.add(this);
        UIUtils.addToJList(this);
    }
}
