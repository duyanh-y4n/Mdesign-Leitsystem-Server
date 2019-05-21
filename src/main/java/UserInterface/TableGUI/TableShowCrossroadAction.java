package UserInterface.TableGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TableShowCrossroadAction implements ActionListener{
    private CrossroadTableUI crossroadTableUI;

    public TableShowCrossroadAction(CrossroadTableUI crossroadTableUI){
        this.crossroadTableUI = crossroadTableUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(this.crossroadTableUI);
    }
}
