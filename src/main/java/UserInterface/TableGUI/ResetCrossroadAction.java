package UserInterface.TableGUI;

import TrafficSystemLogic.CrossroadList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetCrossroadAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("works");
        CrossroadList.Crossroad_A.getArea_occupied().setField(0,(byte)0);
        CrossroadList.Crossroad_A.getArea_occupied().setField(1,(byte)0);
        CrossroadList.Crossroad_A.getArea_occupied().setField(2,(byte)0);
        CrossroadList.Crossroad_A.getArea_occupied().setField(3,(byte)0);

        CrossroadList.Crossroad_B.getArea_occupied().setField(0,(byte)0);
        CrossroadList.Crossroad_B.getArea_occupied().setField(1,(byte)0);
        CrossroadList.Crossroad_B.getArea_occupied().setField(2,(byte)0);
        CrossroadList.Crossroad_B.getArea_occupied().setField(3,(byte)0);

        CrossroadList.Crossroad_C.getArea_occupied().setField(0,(byte)0);
        CrossroadList.Crossroad_C.getArea_occupied().setField(1,(byte)0);
        CrossroadList.Crossroad_C.getArea_occupied().setField(2,(byte)0);
        CrossroadList.Crossroad_C.getArea_occupied().setField(3,(byte)0);
    }
}
