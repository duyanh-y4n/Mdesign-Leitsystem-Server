package UserInterface.TableGUI;

import TrafficSystemLogic.CrossroadList;
import javax.swing.table.AbstractTableModel;

public class CrossroadTableModel extends AbstractTableModel {
    String[] columnNames = {"",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""};
    String[][] RowData= new String[10][10];



    public CrossroadTableModel() {
        this.updateTableModel();
    }

    protected void updateTableModel() {
        this.fireTableDataChanged();
    }

    public int getRowCount() {
        return 10;
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (rowIndex){
            case 0:
                if (columnIndex>=4){
                    return  "4";
                }
                else {
                    return "3";
                }
            case 1:
                if (columnIndex>=4){
                    return  "4";
                }
                else {
                    return "3";
                }
            case 2:
                if (columnIndex>=4&&columnIndex!=6&&columnIndex!=7){
                    return  "4";
                }
                if (columnIndex==6||columnIndex==7){
                    return "2";
                }
                else {
                    return "3";
                }
            case 3:
                if (columnIndex>4&&columnIndex!=6&&columnIndex!=7){
                    return  "4";
                }
                if (columnIndex==6||columnIndex==7){
                    return "2";
                }
                if(columnIndex==4){
                    return CrossroadList.Crossroad_A.getArea_parked().getField(0);
                }
                else {
                    return "3";
                }

            case 4:
                if (columnIndex<4||columnIndex==6||columnIndex==7){
                    return  "4";
                }
                else if(columnIndex==4){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(0);
                }
                else if(columnIndex==5){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(3);
                }
                else if(columnIndex==8){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(0);
                }
                else if(columnIndex==9){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(3);
                }
                else {
                    return "3";
                }
            case 5:
                if (columnIndex<4||columnIndex==6||columnIndex==7){
                    return  "4";
                }
                else if(columnIndex==4){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(1);
                }
                else if(columnIndex==5){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(2);
                }
                else if(columnIndex==8){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(1);
                }
                else if(columnIndex==9){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(2);
                }
                else {
                    return "3";
                }
            case 6:
                if (columnIndex!=2&&columnIndex!=3&&columnIndex!=6&&columnIndex!=7){
                    return  "4";
                }
                if (columnIndex==2||columnIndex==3||columnIndex==6||columnIndex==7){
                    return "2";
                }
                else {
                    return "3";
                }
            case 7:
                if (columnIndex!=2&&columnIndex!=3&&columnIndex!=6&&columnIndex!=7){
                    return  "4";
                }
                if (columnIndex==2||columnIndex==3||columnIndex==6||columnIndex==7){
                    return "2";
                }
                else {
                    return "3";
                }
            case 8:
                if (columnIndex!=4&&columnIndex!=5){
                    return  "4";
                }
                else if(columnIndex==4){
                    return CrossroadList.Crossroad_C.getArea_occupied().getField(0);
                }
                else if(columnIndex==5){
                    return CrossroadList.Crossroad_C.getArea_occupied().getField(3);
                }
                else {
                    return "3";
                }
            case 9:
                if (columnIndex!=4&&columnIndex!=5){
                    return  "4";
                }
                else if(columnIndex==4){
                    return CrossroadList.Crossroad_C.getArea_occupied().getField(0);
                }
                else if(columnIndex==5){
                    return CrossroadList.Crossroad_C.getArea_occupied().getField(3);
                }
                else {
                    return "3";
                }
                default:
                    return "5";
        }

    }

    @Override
    public String getColumnName(int col) {
        return this.columnNames[col];
    }



}
