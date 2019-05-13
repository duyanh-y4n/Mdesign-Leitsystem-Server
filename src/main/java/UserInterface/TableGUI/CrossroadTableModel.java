package UserInterface.TableGUI;

import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import TrafficSystemLogic.Crossroad;
import TrafficSystemLogic.CrossroadList;
import UserInterface.*;
import java.util.ArrayList;
import java.util.List;
import TrafficSystemLogic.Trafficsystem;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;



import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CrossroadTableModel extends AbstractTableModel {
    String[] columnNames = {"",
            "",
            "",
            "",
            "",
            "",
            "",
            ""};
    String[][] RowData= new String[8][8];



    public CrossroadTableModel() {
        this.updateTableModel();
    }

    protected void updateTableModel() {
        this.fireTableDataChanged();
    }

    public int getRowCount() {
        return 8;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (rowIndex){
            case 0:
                if (columnIndex>=3){
                    return  "4";
                }
                else {
                    return "3";
                }
            case 1:
                if (columnIndex>=3){
                    return  "4";
                }
                else {
                    return "3";
                }
            case 2:
                if (columnIndex>=3&&columnIndex!=5){
                    return  "4";
                }
                if (columnIndex==5){
                    return "2";
                }
                else {
                    return "3";
                }

            case 3:
                if (columnIndex<3||columnIndex==5){
                    return  "4";
                }
                else if(columnIndex==3){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(0);
                }
                else if(columnIndex==4){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(3);
                }
                else if(columnIndex==6){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(0);
                }
                else if(columnIndex==7){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(3);
                }
                else {
                    return "3";
                }
            case 4:
                if (columnIndex<3||columnIndex==5){
                    return  "4";
                }
                else if(columnIndex==3){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(1);
                }
                else if(columnIndex==4){
                    return CrossroadList.Crossroad_A.getArea_occupied().getField(2);
                }
                else if(columnIndex==6){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(1);
                }
                else if(columnIndex==7){
                    return CrossroadList.Crossroad_B.getArea_occupied().getField(2);
                }
                else {
                    return "3";
                }
            case 5:
                if (columnIndex!=2&&columnIndex!=5){
                    return  "4";
                }
                if (columnIndex==2||columnIndex==5){
                    return "2";
                }
                else {
                    return "3";
                }
            case 6:
                if (columnIndex!=3&&columnIndex!=4){
                    return  "4";
                }
                else if(columnIndex==3){
                    return CrossroadList.Crossroad_C.getArea_occupied().getField(0);
                }
                else if(columnIndex==4){
                    return CrossroadList.Crossroad_C.getArea_occupied().getField(3);
                }
                else {
                    return "3";
                }
            case 7:
                if (columnIndex!=3&&columnIndex!=4){
                    return  "4";
                }
                else if(columnIndex==3){
                    return CrossroadList.Crossroad_C.getArea_occupied().getField(0);
                }
                else if(columnIndex==4){
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
