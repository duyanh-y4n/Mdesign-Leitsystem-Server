package UserInterface.TableGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableShowLogAction implements ActionListener {
    private LogOutputUI logOutputUI;

    public TableShowLogAction(LogOutputUI logOutputUI){
        this.logOutputUI = logOutputUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(this.logOutputUI);
    }
}
