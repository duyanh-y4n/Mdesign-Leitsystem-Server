package UserInterface.TableGUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;


public class CrossroadTableUI implements Runnable{
    private JFrame frame;
    private CrossroadTableModel crossroadTableModel;

    public CrossroadTableUI(CrossroadTableModel crossroadTableModel) {
        this.crossroadTableModel = crossroadTableModel;

    }



    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(800, 800));
        JTable table=new JTable(this.crossroadTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(100);

        DefaultTableCellRenderer ren = new CrossroadTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);

                if (value.toString().equals("0")) {
                    setBackground(Color.GREEN);
                    setForeground(Color.GREEN);

                } else if(value.toString().equals("1"))
                    {
                        setBackground(Color.RED);
                        setForeground(Color.RED);

                    }
                else if(value.toString().equals("2"))
                {
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);


                }
                else if(value.toString().equals("3"))
                {
                    setBackground(Color.WHITE);
                    setForeground(Color.WHITE);

                }
                else if(value.toString().equals("4"))
                {
                    setBackground(Color.GRAY);
                    setForeground(Color.GRAY);

                }
                else{
                }

                table.repaint();
                return this;
            }
        };
        table.setDefaultRenderer  ( Object.class, ren );
        container.add(new ScrollPane().add(table));

    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Straßenplan");
        this.frame.setPreferredSize(new Dimension(800, 840));

        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
