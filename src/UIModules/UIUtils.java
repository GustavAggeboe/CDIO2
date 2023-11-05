package UIModules;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UIUtils {
    protected static ActionListener AL;
    public static void setAL(ActionListener newAL) {
        AL = newAL;
    }
    protected static List<JComponent> jComponents = new ArrayList<>();
    public static void addToJList(JComponent jComponent) {
        jComponents.add(jComponent);
    }
    public static List<JComponent> getJComponents() {
        return jComponents;
    }
}
