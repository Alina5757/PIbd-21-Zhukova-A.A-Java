import java.awt.*;

class TwoFloorBus extends Bus {
    public Color dopColor;
    private void SetDopColor(Color DopColor) { dopColor = DopColor; }
    public Color GetDopColor() { return dopColor; }

    public boolean polosa;
    private void SetPolosa(boolean Polosa) { polosa = Polosa; }
    public boolean GetPolosa() { return polosa; }

    public boolean secondFloor;
    private void SetSecondFloor(boolean SecondFloor) { secondFloor = SecondFloor; }
    public boolean GetSecondFloor() { return secondFloor; }

    public TwoFloorBus(int Maxspeed, int Weight, Color MainColor, Color DopColor, boolean Polosa, boolean SecondFloor) {
        super(Maxspeed, Weight, MainColor, 100, 70);
        dopColor = DopColor;
        polosa = Polosa;
        secondFloor = SecondFloor;
    }

    public @Override
    void DrawTransport(Graphics g, Frame frame) {
        super.DrawTransport(g, frame);

        Color blueColor = new Color(192, 249, 255);

        if(g!=null) {
            if (secondFloor) {
                g.setColor(dopColor);
                g.fillRect((int) x_koor - 100, (int) y_koor - 80, 200, 50); //корпус второй этаж
                g.setColor(Color.BLACK);
                g.drawRect((int) x_koor - 100, (int) y_koor - 80, 200, 50);

                g.setColor(blueColor);
                g.fillRect((int) x_koor - 100, (int) y_koor - 65, 10, 25);  //окна второй этаж
                g.fillRect((int) x_koor - 80, (int) y_koor - 65, 25, 25);
                g.fillRect((int) x_koor - 45, (int) y_koor - 65, 25, 25);
                g.fillRect((int) x_koor - 12, (int) y_koor - 65, 25, 25);
                g.fillRect((int) x_koor + 20, (int) y_koor - 65, 25, 25);
                g.fillRect((int) x_koor + 55, (int) y_koor - 65, 25, 25);
                g.fillRect((int) x_koor + 90, (int) y_koor - 65, 10, 25);

                g.setColor(Color.BLACK);
                g.drawRect((int) x_koor - 100, (int) y_koor - 65, 10, 25);
                g.drawRect((int) x_koor - 80, (int) y_koor - 65, 25, 25);
                g.drawRect((int) x_koor - 45, (int) y_koor - 65, 25, 25);
                g.drawRect((int) x_koor - 12, (int) y_koor - 65, 25, 25);
                g.drawRect((int) x_koor + 20, (int) y_koor - 65, 25, 25);
                g.drawRect((int) x_koor + 55, (int) y_koor - 65, 25, 25);
                g.drawRect((int) x_koor + 90, (int) y_koor - 65, 10, 25);
            }

            if (polosa) {
                g.setColor(dopColor);
                g.fillRect((int) x_koor - 99, (int) y_koor + 30, 40, 3);
                g.fillRect((int) x_koor - 25, (int) y_koor + 30, 62, 3);
                g.fillRect((int) x_koor + 70, (int) y_koor + 30, 29, 3);
            }
        }
        frame.repaint();
    }
}