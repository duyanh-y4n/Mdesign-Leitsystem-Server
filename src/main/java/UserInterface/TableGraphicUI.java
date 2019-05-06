package UserInterface;

import CentralServer.DataServer.VehicleDatabaseDAO;

import javax.swing.*;
import java.awt.*;

public class TableGraphicUI implements Runnable, UserInterface{
    private JFrame frame;
    private VehicleTableModel vehicleTableModel;
    private VehicleDatabaseDAO vehicleDatabaseDAO;

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

        container.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton updateBtn = new JButton("update");
        updateBtn.addActionListener(new TableUpdateAction(this.vehicleTableModel));
        container.add(updateBtn, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Title");
        this.frame.setPreferredSize(new Dimension(800, 300));

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