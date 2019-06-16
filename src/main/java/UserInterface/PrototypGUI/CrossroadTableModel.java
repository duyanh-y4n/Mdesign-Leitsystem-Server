package UserInterface.PrototypGUI;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;


public class CrossroadTableModel extends AbstractTableModel {
    //ImageIcon VehicleIcon = new ImageIcon("Auto.png");

    CrossroadTableProperties crossroadTableProperties = new CrossroadTableProperties();
    String[] columnNames = {"",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""};
    Object[][] RowData= new Object[12][12];



    public CrossroadTableModel() {

        this.updateTableModel();
    }


    protected void updateTableModel() {
        this.fireTableDataChanged();
    }

    public int getRowCount() {
        return 12;
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object Value = crossroadTableProperties.CalculateStatic(rowIndex,columnIndex);
        Value = crossroadTableProperties.CalculateDynamic(rowIndex,columnIndex,Value);
        return Value;


    }

    @Override
    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    public RotatedIcon RotateIcon(ImageIcon icon){
         RotatedIcon rotatedIcon= new RotatedIcon(icon, RotatedIcon.Rotate.DOWN);
        return rotatedIcon;
    }

}
