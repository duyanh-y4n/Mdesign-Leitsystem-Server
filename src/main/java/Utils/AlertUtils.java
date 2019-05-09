package Utils;

import javax.swing.*;

public class AlertUtils {
    public static void alertExceptionStackTrace(Exception e) {
        String alertMessage = "Error message: " + (e.getMessage() == null ? "None" : e.getMessage())
                + "\n" + e.getClass().getName();
        for (StackTraceElement stackTraceElement :
                e.getStackTrace()) {
            alertMessage = alertMessage.concat("\nat " + stackTraceElement);
        }
        JOptionPane.showMessageDialog(null, alertMessage);
    }
}
