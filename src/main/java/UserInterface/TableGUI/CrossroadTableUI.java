package UserInterface.TableGUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;



public class CrossroadTableUI implements Runnable{
    private JFrame frame;
    private CrossroadTableModel crossroadTableModel;
    private CrossroadTableProperties crossroadTableProperties= new CrossroadTableProperties();
    //ImageIcon VehicleIcon = new ImageIcon("Auto.png");

    public CrossroadTableUI(CrossroadTableModel crossroadTableModel) {
        this.crossroadTableModel = crossroadTableModel;

    }



    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(800, 750));
        JTable table=new JTable(this.crossroadTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(75);





        DefaultTableCellRenderer ren = new CrossroadTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                JLabel lbl = new JLabel();
                if (value.toString().equals("0")) {
                    setBackground(Color.GREEN);
                    setForeground(Color.GREEN);
                    setIcon(null);


                } else if(value.toString().equals("1"))
                    {
                        setBackground(Color.RED);
                        setForeground(Color.RED);
                        setIcon(null);

                    }

                else if(value.toString().equals("Schwarz"))
                {
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    setIcon(null);


                }
                else if(value.toString().equals("Grau"))
                {

                    setBackground(Color.GRAY);
                    setForeground(Color.GRAY);
                    setIcon(null);



                }

                else if (value.equals("Auto links")){
                    setBackground(Color.GRAY);
                    setForeground(Color.GRAY);
                    this.setVerticalAlignment(TOP);
                    setHorizontalTextPosition(TRAILING);
                    setIcon(crossroadTableProperties.VehicleIcon_left);


                }
                else if (value.equals("Auto unten")){
                    setBackground(Color.GRAY);
                    setForeground(Color.GRAY);
                    setHorizontalTextPosition(TRAILING);
                    setIcon(crossroadTableProperties.VehicleIcon_down);


                }
                else if (value.equals("Auto rechts")){
                    setBackground(Color.GRAY);
                    setForeground(Color.GRAY);
                    this.setVerticalAlignment(BOTTOM);
                    setHorizontalTextPosition(TRAILING);
                    setIcon(crossroadTableProperties.VehicleIcon_right);


                }
                else if (value.equals("Auto oben")){
                    setBackground(Color.GRAY);
                    setForeground(Color.GRAY);
                    setHorizontalTextPosition(LEADING);
                    setIcon(crossroadTableProperties.VehicleIcon_up);


                }
                else if (value.equals("Vorfahrt_oben_links")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    this.setVerticalAlignment(TOP);
                    this.setHorizontalTextPosition(TRAILING);
                    setIcon(crossroadTableProperties.Vohrfahrt);



                }
                else if (value.equals("Vorfahrt_oben_rechts")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    this.setVerticalAlignment(TOP);
                    this.setHorizontalTextPosition(LEADING);
                    setIcon(crossroadTableProperties.Vohrfahrt);



                }
                else if (value.equals("Vorfahrt_unten_links")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    this.setVerticalAlignment(BOTTOM);
                    this.setHorizontalTextPosition(TRAILING);
                    setIcon(crossroadTableProperties.Vohrfahrt);



                }
                else if (value.equals("Vorfahrt_unten_rechts")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    this.setVerticalAlignment(BOTTOM);
                    this.setHorizontalTextPosition(LEADING);
                    setIcon(crossroadTableProperties.Vohrfahrt);



                }

                else if (value.equals("keine Vorfahrt_links")){
                    this.setIcon(crossroadTableProperties.KeineVohrfahrt_left);

                    this.setVerticalAlignment(TOP);
                    this.setHorizontalTextPosition(LEADING);
                    this.setBackground(Color.BLACK);
                    this.setForeground(Color.BLACK);
                    this.add(lbl);
                    return this;






                }
                else if (value.equals("keine Vorfahrt_unten")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    setVerticalAlignment(TOP);
                    setHorizontalTextPosition(TRAILING);
                    setIcon(crossroadTableProperties.KeineVohrfahrt_down);


                }
                else if (value.equals("keine Vorfahrt_oben")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    setVerticalAlignment(BOTTOM);
                    setHorizontalTextPosition(LEADING);
                    setIcon(crossroadTableProperties.KeineVohrfahrt_up);


                }
                else if (value.equals("Haw links")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    setVerticalAlignment(BOTTOM);
                    setHorizontalAlignment(LEFT);
                    setHorizontalTextPosition(TRAILING);
                    setIcon(crossroadTableProperties.HawIcon);


                }else if (value.equals("Haw rechts")){
                    setBackground(Color.BLACK);
                    setForeground(Color.BLACK);
                    setVerticalAlignment(BOTTOM);
                    setHorizontalAlignment(RIGHT);
                    setHorizontalTextPosition(LEADING);
                    setIcon(crossroadTableProperties.HawIcon);


                }
                else{
                    setBackground(Color.GRAY);
                    setForeground(Color.BLACK);
                    setFont(new Font("Serif", Font.BOLD, 15));
                    setHorizontalTextPosition(CENTER);
                    setVerticalTextPosition(CENTER);
                    setHorizontalAlignment(CENTER);
                    setVerticalAlignment(CENTER);
                    setIcon(null);
                }

                table.setShowGrid(false);
                table.setIntercellSpacing(new Dimension(0, 0));
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
        this.frame.setPreferredSize(new Dimension(960, 930));
        this.frame.setResizable(false);


        this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        createComponents(this.frame.getContentPane());

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
