package UserInterface.PrototypGUI;

import javax.swing.*;
import java.awt.*;

public class LogOutputUI implements Runnable{
    private JFrame frame;
    private JTextArea logText;

    public LogOutputUI(JTextArea logText) {
        this.logText = logText;
        this.logText.setEditable(false);
//        DefaultCaret caret = (DefaultCaret)this.logText.getCaret();
//        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(800, 300));
        JScrollPane logScrollPanel = new JScrollPane(this.logText);
        container.add(logScrollPanel);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Work log");
        this.frame.setPreferredSize(new Dimension(800, 300));

        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setResizable(true);
    }

}
