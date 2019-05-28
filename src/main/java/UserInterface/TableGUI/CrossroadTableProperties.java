package UserInterface.TableGUI;

import TrafficSystemLogic.CrossroadList;
import UserInterface.TableGUI.*;

import javax.imageio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.*;

public class CrossroadTableProperties {
    ImageIcon HawIcon;
    ImageIcon VehicleIcon_left;
    RotatedIcon VehicleIcon_down;
    RotatedIcon VehicleIcon_right;
    RotatedIcon VehicleIcon_up;
    ImageIcon Vohrfahrt;
    ImageIcon KeineVohrfahrt_left;
    RotatedIcon KeineVohrfahrt_down;
    RotatedIcon KeineVohrfahrt_up;

    public CrossroadTableProperties() {
        this.HawIcon = this.getImg("/logo.png", 157, 75);

        this.VehicleIcon_left = this.getImg("/Auto.png", 70, 50);
        this.VehicleIcon_down = new RotatedIcon(VehicleIcon_left, RotatedIcon.Rotate.UP);
        this.VehicleIcon_right = new RotatedIcon(VehicleIcon_left, RotatedIcon.Rotate.UPSIDE_DOWN);
        this.VehicleIcon_up = new RotatedIcon(VehicleIcon_left, RotatedIcon.Rotate.DOWN);

        this.Vohrfahrt = this.getImg("/Vorfahrtsstrasse.png", 30, 30);

        this.KeineVohrfahrt_left = this.getImg("/Vohrfahrtgewaehren.png", 30, 30);
        this.KeineVohrfahrt_down = new RotatedIcon(KeineVohrfahrt_left, RotatedIcon.Rotate.UP);
        this.KeineVohrfahrt_up = new RotatedIcon(KeineVohrfahrt_left, RotatedIcon.Rotate.DOWN);
    }

    public ImageIcon getImg(String path, int width, int height) {
        try {
            BufferedImage VehicleImage = ImageIO.read(getClass().getResourceAsStream(path));
            Image smallImage = VehicleImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            ImageIcon VehicleIcon = new ImageIcon(smallImage);

            return VehicleIcon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    public Object CalculateStatic(int rowIndex, int columnIndex) {
        rowIndex = rowIndex - 1;
        columnIndex = columnIndex - 1;
        switch (rowIndex) {
            case 0:
                if (columnIndex == 0) {
                    return "Schwarz";
                }
                if (columnIndex == 1) {
                    return "Schwarz";
                }
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }
                if (columnIndex >= 4) {
                    return "Grau";
                } else {
                    return "Schwarz";
                }
            case 1:
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }
                if (columnIndex >= 4) {
                    return "Grau";
                } else {
                    return "Schwarz";
                }
            case 2:
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }
                if (columnIndex >= 4 && columnIndex != 6 && columnIndex != 7) {
                    return "Grau";
                }
                if (columnIndex == 6 || columnIndex == 7) {
                    return "Schwarz";
                } else {
                    return "Schwarz";
                }
            case 3:
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }
                if (columnIndex >= 4 && columnIndex != 6 && columnIndex != 7) {
                    return "Grau";
                }
                if (columnIndex == 7) {
                    return "Vorfahrt_unten_rechts";
                }
                if (columnIndex == 6) {
                    return "Vorfahrt_unten_links";
                }
                if (columnIndex == 3) {
                    return "keine Vorfahrt_oben";
                } else {
                    return "Schwarz";
                }

            case 4:
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }
                return "Grau";
            case 5:
                if (columnIndex == -1) {
                    return "Schwarz";
                }
                if (columnIndex == 10) {
                    return "Schwarz";
                }
                return "Grau";
            case 6:
                if (columnIndex == -1) {
                    return "Schwarz";
                }
                if (columnIndex != 2 && columnIndex != 3 && columnIndex != 6 && columnIndex != 7 && columnIndex != 10) {
                    return "Grau";
                }
                if (columnIndex == 2) {
                    return "Schwarz";
                }
                if (columnIndex == 7) {
                    return "keine Vorfahrt_links";
                }
                if (columnIndex == 3) {
                    return "Vorfahrt_oben_rechts";
                }
                if (columnIndex == 10) {
                    return "Vorfahrt_oben_links";
                }
                if (columnIndex == 6) {
                    return "keine Vorfahrt_unten";
                } else {
                    return "Schwarz";
                }
            case 7:
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }
                if (columnIndex != 2 && columnIndex != 3 && columnIndex != 6 && columnIndex != 7) {
                    return "Grau";
                }
                if (columnIndex == 2 || columnIndex == 7) {
                    return "Schwarz";
                }
                if (columnIndex == 3) {
                    return "keine Vorfahrt_oben";
                }
                if (columnIndex == 6) {
                    return "Vorfahrt_unten_links";
                } else {
                    return "Schwarz";
                }
            case 8:
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }
                return "Grau";

            case 9:
                if (columnIndex == -1 || columnIndex == 10) {
                    return "Schwarz";
                }

                return "Grau";
            case 10:
                if (columnIndex == 3) {
                    return "Vorfahrt_oben_rechts";
                }

            default:

                return "Schwarz";
        }
    }

    public Object CalculateDynamic(int rowIndex, int columnIndex, Object staticValue) {
        rowIndex = rowIndex - 1;
        columnIndex = columnIndex - 1;
        //Fahrzeuge
        //A0
        if (rowIndex == 2 && columnIndex == 4) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(0) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_A.getArea_parked().getName(0);
            }
        }
        if (rowIndex == 3 && columnIndex == 4) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(0) == "0") {
                return "Grau";
            } else {
                return "Auto unten";
            }
        }
        //A1
        if (rowIndex == 5 && columnIndex == 2) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(1) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_A.getArea_parked().getName(1);
            }
        }
        if (rowIndex == 5 && columnIndex == 3) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(1) == "0") {
                return "Grau";
            } else {
                return "Auto rechts";
            }
        }
        //A2
        if (rowIndex == 7 && columnIndex == 5) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(2) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_A.getArea_parked().getName(2);
            }
        }
        if (rowIndex == 6 && columnIndex == 5) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(2) == "0") {
                return "Grau";
            } else {
                return "Auto oben";
            }
        }
        //A3
        if (rowIndex == 4 && columnIndex == 7) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(3) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_A.getArea_parked().getName(3);
            }
        }
        if (rowIndex == 4 && columnIndex == 6) {
            if (CrossroadList.Crossroad_A.getArea_parked().getName(3) == "0") {
                return "Grau";
            } else {
                return "Auto links";
            }
        }
        //B0
        if (rowIndex == 2 && columnIndex == 8) {
            if (CrossroadList.Crossroad_B.getArea_parked().getName(0) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_B.getArea_parked().getName(0);
            }
        }
        if (rowIndex == 3 && columnIndex == 8) {
            if (CrossroadList.Crossroad_B.getArea_parked().getName(0) == "0") {
                return "Grau";
            } else {
                return "Auto unten";
            }
        }
        //B1
        if (rowIndex == 5 && columnIndex == 6) {
            if (CrossroadList.Crossroad_B.getArea_parked().getName(1) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_B.getArea_parked().getName(1);
            }
        }
        if (rowIndex == 5 && columnIndex == 7) {
            if (CrossroadList.Crossroad_B.getArea_parked().getName(1) == "0") {
                return "Grau";
            } else {
                return "Auto rechts";
            }
        }
        //B2
        if (rowIndex == 7 && columnIndex == 9) {
            if (CrossroadList.Crossroad_B.getArea_parked().getName(2) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_B.getArea_parked().getName(2);
            }
        }
        if (rowIndex == 6 && columnIndex == 9) {
            if (CrossroadList.Crossroad_B.getArea_parked().getName(2) == "0") {
                return "Grau";
            } else {
                return "Auto oben";
            }
        }

        //C0
        if (rowIndex == 6 && columnIndex == 4) {
            if (CrossroadList.Crossroad_C.getArea_parked().getName(0) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_C.getArea_parked().getName(0);
            }
        }
        if (rowIndex == 7 && columnIndex == 4) {
            if (CrossroadList.Crossroad_C.getArea_parked().getName(0) == "0") {
                return "Grau";
            } else {
                return "Auto unten";
            }
        }
        //C1
        if (rowIndex == 9 && columnIndex == 2) {
            if (CrossroadList.Crossroad_C.getArea_parked().getName(1) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_C.getArea_parked().getName(1);
            }
        }
        if (rowIndex == 9 && columnIndex == 3) {
            if (CrossroadList.Crossroad_C.getArea_parked().getName(1) == "0") {
                return "Grau";
            } else {
                return "Auto rechts";
            }
        }

        //C3
        if (rowIndex == 8 && columnIndex == 7) {
            if (CrossroadList.Crossroad_C.getArea_parked().getName(3) == "0") {
                return "Grau";
            } else {
                return "" + CrossroadList.Crossroad_C.getArea_parked().getName(3);
            }
        }
        if (rowIndex == 8 && columnIndex == 6) {
            if (CrossroadList.Crossroad_C.getArea_parked().getName(3) == "0") {
                return "Grau";
            } else {
                return "Auto links";
            }
        }
        //Kreuzungen
        if (rowIndex == 4) {

            if (columnIndex == 4) {
                return CrossroadList.Crossroad_A.getArea_occupied().getField(0);
            } else if (columnIndex == 5) {
                return CrossroadList.Crossroad_A.getArea_occupied().getField(3);
            } else if (columnIndex == 8) {
                return CrossroadList.Crossroad_B.getArea_occupied().getField(0);
            } else if (columnIndex == 9) {
                return CrossroadList.Crossroad_B.getArea_occupied().getField(3);
            }
        }
        if (rowIndex == 5) {
            if (columnIndex == 4) {
                return CrossroadList.Crossroad_A.getArea_occupied().getField(1);
            } else if (columnIndex == 5) {
                return CrossroadList.Crossroad_A.getArea_occupied().getField(2);
            } else if (columnIndex == 8) {
                return CrossroadList.Crossroad_B.getArea_occupied().getField(1);
            } else if (columnIndex == 9) {
                return CrossroadList.Crossroad_B.getArea_occupied().getField(2);
            }
        }
        if (rowIndex == 8) {
            if (columnIndex == 4) {
                return CrossroadList.Crossroad_C.getArea_occupied().getField(0);
            } else if (columnIndex == 5) {
                return CrossroadList.Crossroad_C.getArea_occupied().getField(3);
            }
        }
        if (rowIndex == 9) {
            if (columnIndex == 4) {
                return CrossroadList.Crossroad_C.getArea_occupied().getField(1);
            } else if (columnIndex == 5) {
                return CrossroadList.Crossroad_C.getArea_occupied().getField(2);
            }
        }
        return staticValue;
    }
}
