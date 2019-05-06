package UserInterface;

import CentralServer.DataServer.VehicleDatabaseDAO;

import javax.swing.*;
import java.awt.*;

public class TableGraphicUI implements Runnable, UserInterface{
    private JFrame frame;
    private VehicleTableModel vehicleTableModel;
    private VehicleDatabaseDAO vehicleDatabaseDAO;

    Object[][] data = {
            {"Kathy", "Smith",
                    "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
                    "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
                    "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
                    "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
                    "Pool", new Integer(10), new Boolean(false)}
    };

    public TableGraphicUI(VehicleDatabaseDAO vehicleDatabaseDAO) {
        this.vehicleDatabaseDAO = vehicleDatabaseDAO;
        this.vehicleTableModel = new VehicleTableModel(this.vehicleDatabaseDAO);
        updateData();
    }

    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        JLabel text = new JLabel("Leitsystem - Vehicle status");
        container.add(text, BorderLayout.NORTH);

        this.vehicleTableModel.updateTableModel();
        JTable table = new JTable();
        table.setModel(this.vehicleTableModel);

        container.add(table, BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Title");
        this.frame.setPreferredSize(new Dimension(200, 100));

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }

    @Override
    public void updateData() {
        this.vehicleTableModel.updateTableModel();
    }
}