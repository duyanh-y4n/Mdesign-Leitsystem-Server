package UserInterface.TableGUI;

import CentralServer.DataServer.VehicleDatabaseDAO;
import Client.Vehicle;
import TrafficSystemLogic.Crossroad;
import TrafficSystemLogic.CrossroadList;
import UserInterface.*;
import java.util.ArrayList;
import java.util.List;
import TrafficSystemLogic.Trafficsystem;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.Icon;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;



import java.awt.*;
import java.util.ArrayList;
import java.util.List;


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
