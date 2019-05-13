package UserInterface.TableGUI;

import java.awt.*;
import javax.swing.table.*;

public class CrossroadTableCellRenderer extends DefaultTableCellRenderer {
    public void setValue( Object value ){
        if ( value instanceof String )
        {
            setForeground( Integer.parseInt(value.toString()) % 2 == 0 ? Color.BLUE : Color.GRAY );
            setText( value.toString() );
        }
        else
            super.setValue( value );
    }
}

