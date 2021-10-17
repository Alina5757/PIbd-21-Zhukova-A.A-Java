import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BusTwoFloor {
    int maxspeed;  //Макс скорость
    int weight;   //Вес
    boolean polosa;  //полоса на корпусе
    boolean secondFloor;  // второй этаж
    float x_koor;   //x центра отрисовки
    float y_koor;   //y центра отрисовки
    int x_lenght = 100;   //длина рисунка от центра
    int y_lenght = 75;   //высота рисунка от центра
    public Color mainColor;   //основной цвет
    public Color dopColor;   //дополнительный цвет

    ClassDopDoors dopdoors;  //класс отрисовки дверей

    public void Init(int Maxspeed, int Weight, boolean SecondFloor, boolean Polosa, Color MainColor, Color DopColor) {
        maxspeed = Maxspeed;
        weight = Weight;
        polosa = Polosa;
        secondFloor = SecondFloor;
        mainColor = MainColor;
        dopColor = DopColor;
        dopdoors = new ClassDopDoors();
        Random random = new Random();
        dopdoors.SetDoors(3 + (random.nextInt() % 3));  //от 3 до 5 включительно
    }

    public void SetPosition(int X_koor, int Y_koor) {
        x_koor = X_koor;
        y_koor = Y_koor;
    }

    public void MoveTransport(Direction direction) {
        float step = maxspeed * 100 / weight;
        if (direction == Direction.Up) {
            if (y_koor - y_lenght - step + 20 >= 0) {
                y_koor -= step;
            }
        }
        if (direction == Direction.Down) {
            if (y_koor + y_lenght + step <= 860) {
                y_koor += step;
            }
        }
        if (direction == Direction.Left) {
            if (x_koor - x_lenght - step >= 0) {
                x_koor -= step;
            }
        }
        if (direction == Direction.Right) {
            if (x_koor + x_lenght + step <= 980) {
                x_koor += step;
            }
        }
    }


    public void DrowBus(Graphics2D g, JFrame frame) {
        Color colorBlue = new Color(160, 220, 255);
        Color colorGrey = new Color(120, 120, 120);

        g.setColor(mainColor);
        g.fillRect((int) x_koor - 100, (int) y_koor - 10, 200, 70);  //корпус первый этаж
        if (polosa) {
            g.setColor(dopColor);
            g.fillRect((int) x_koor - 100, (int) y_koor + 50, 200, 3);
        }
        g.setColor(Color.BLACK);
        g.drawRect((int) x_koor - 100, (int) y_koor - 10, 200, 70);
        if (secondFloor) {
            g.setColor(dopColor);
            g.fillRect((int) x_koor - 100, (int) y_koor - 60, 200, 50); //корпус второй этаж
            g.setColor(Color.BLACK);
            g.drawRect((int) x_koor - 100, (int) y_koor - 60, 200, 50);

            g.setColor(colorBlue);
            g.fillRect((int) x_koor - 100, (int) y_koor - 50, 10, 25);  //окна второй этаж
            g.fillRect((int) x_koor - 80, (int) y_koor - 50, 25, 25);
            g.fillRect((int) x_koor - 45, (int) y_koor - 50, 25, 25);
            g.fillRect((int) x_koor - 12, (int) y_koor - 50, 25, 25);
            g.fillRect((int) x_koor + 20, (int) y_koor - 50, 25, 25);
            g.fillRect((int) x_koor + 55, (int) y_koor - 50, 25, 25);
            g.fillRect((int) x_koor + 90, (int) y_koor - 50, 10, 25);

            g.setColor(Color.BLACK);
            g.drawRect((int) x_koor - 100, (int) y_koor - 50, 10, 25);
            g.drawRect((int) x_koor - 80, (int) y_koor - 50, 25, 25);
            g.drawRect((int) x_koor - 45, (int) y_koor - 50, 25, 25);
            g.drawRect((int) x_koor - 12, (int) y_koor - 50, 25, 25);
            g.drawRect((int) x_koor + 20, (int) y_koor - 50, 25, 25);
            g.drawRect((int) x_koor + 55, (int) y_koor - 50, 25, 25);
            g.drawRect((int) x_koor + 90, (int) y_koor - 50, 10, 25);
        }

        dopdoors.DrowDoors(g, dopColor, x_koor, y_koor);

        g.setColor(colorBlue);
        g.fillRect((int) x_koor - 60, (int) y_koor, 25, 25);  //окна первый этаж
        g.fillRect((int) x_koor - 30, (int) y_koor, 25, 25);
        g.fillRect((int) x_koor + 38, (int) y_koor, 25, 25);
        g.fillRect((int) x_koor + 90, (int) y_koor, 10, 25);

        g.setColor(Color.BLACK);
        g.drawRect((int) x_koor - 60, (int) y_koor, 25, 25);
        g.drawRect((int) x_koor - 30, (int) y_koor, 25, 25);
        g.drawRect((int) x_koor + 38, (int) y_koor, 25, 25);
        g.drawRect((int) x_koor + 90, (int) y_koor, 10, 25);


        g.setColor(colorGrey);
        g.fillOval((int) x_koor - 60, (int) y_koor + 40, 35, 35);   //колеса
        g.fillOval((int) x_koor + 35, (int) y_koor + 40, 35, 35);

        g.setColor(Color.BLACK);
        g.drawOval((int) x_koor - 60, (int) y_koor + 40, 35, 35);
        g.drawOval((int) x_koor + 35, (int) y_koor + 40, 35, 35);


        frame.repaint();
    }
}
