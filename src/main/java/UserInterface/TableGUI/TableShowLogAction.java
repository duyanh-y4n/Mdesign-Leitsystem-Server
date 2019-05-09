package UserInterface.TableGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableShowLogAction implements ActionListener {
    private JTextArea logText;
    private LogOutputUI logOutputUI;

    public TableShowLogAction(JTextArea logText, LogOutputUI logOutputUI){
        this.logText = logText;
        this.logOutputUI = logOutputUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(this.logOutputUI);
    }
}
