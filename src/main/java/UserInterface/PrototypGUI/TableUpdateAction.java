package UserInterface.PrototypGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableUpdateAction implements ActionListener {
    private VehicleTableModel vehicleTableModel;

    public TableUpdateAction(VehicleTableModel vehicleTableModel){
        this.vehicleTableModel = vehicleTableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.vehicleTableModel.updateTableModel();
    }
}
