package UserInterface.PrototypGUI;

import DataServer.VehicleDatabaseDAO;
import UserInterface.UserInterface;

import javax.swing.*;
import java.awt.*;

public class TableGraphicUI implements Runnable, UserInterface {
    private JFrame frame;
    private VehicleTableModel vehicleTableModel;
    private CrossroadTableModel crossroadTableModel;
    private VehicleDatabaseDAO vehicleDatabaseDAO;
    private JTextArea logTextArea;
    private LogOutputUI logOutputUI;
    private CrossroadTableUI crossroadTableUI;


    public TableGraphicUI(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
        this.vehicleTableModel = new VehicleTableModel(this.vehicleDatabaseDAO);
        this.crossroadTableModel = new CrossroadTableModel();
        this.logTextArea = new JTextArea();
        this.logOutputUI = new LogOutputUI(this.logTextArea);
        this.crossroadTableUI = new CrossroadTableUI(crossroadTableModel);
        updateData();
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());

        this.vehicleTableModel.updateTableModel();
        JTable table = new JTable();
        table.setModel(this.vehicleTableModel);

        ImageIcon HAWIcon = new ImageIcon(getClass().getResource("/logo.png"));
        JLabel HAWIconLabel = new JLabel(HAWIcon, JLabel.CENTER);
        container.add(HAWIconLabel, BorderLayout.NORTH);


        container.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton updateBtn = new JButton("Straßenplan");
        updateBtn.addActionListener(new TableShowCrossroadAction(this.crossroadTableUI));
        JButton logBtn = new JButton("Log");
        logBtn.addActionListener(new TableShowLogAction(this.logOutputUI));
        JButton resetAllCrossroadBtn = new JButton("Alle freigeben");
        resetAllCrossroadBtn.addActionListener(new ResetCrossroadAction());

        Container buttonMenu = new JPanel();
        buttonMenu.setLayout(new GridLayout(1, 2));
        buttonMenu.add(updateBtn);
        buttonMenu.add(logBtn);
        buttonMenu.add(resetAllCrossroadBtn);
        container.add(buttonMenu, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Leitsystem - Registrierte Fahrzeuge");
        this.frame.setPreferredSize(new Dimension(850, 300));

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }

    @Override
    public void updateData() {
        this.vehicleTableModel.updateTableModel();
        this.crossroadTableModel.updateTableModel();
    }

    @Override
    public void log(String message) {
        this.logTextArea.append(message + "\n");
        this.logTextArea.setCaretPosition(this.logTextArea.getDocument().getLength());
    }
}