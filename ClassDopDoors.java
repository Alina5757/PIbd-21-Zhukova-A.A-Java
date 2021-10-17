import java.awt.*;
import java.util.Random;

public class ClassDopDoors {
    private EnumDopDoors doors;

    public void SetDoors(int value) {
        if (EnumDopDoors.ThreeDoors.KolvoDoorExist(value)) {
            doors = EnumDopDoors.ThreeDoors.GetDoors(value);
        } else {
            Random rand = new Random();
            doors = EnumDopDoors.ThreeDoors.GetDoors(3 + (rand.nextInt() % 4));
        }
    }

    public void DrowDoors(Graphics g, Color dopcolor, float x_koor, float y_koor) {

        g.setColor(dopcolor);
        g.fillRect((int) x_koor, (int) y_koor, 16, 45);
        g.fillRect((int) x_koor + 16, (int) y_koor, 16, 45);
        g.fillRect((int) x_koor + 68, (int) y_koor, 16, 45);

        g.setColor(Color.BLACK);
        g.drawRect((int) x_koor, (int) y_koor, 16, 45);
        g.drawRect((int) x_koor + 16, (int) y_koor, 16, 45);
        g.drawRect((int) x_koor + 68, (int) y_koor, 16, 45);

        if (doors == EnumDopDoors.FourDoors) {
            g.setColor(dopcolor);
            g.fillRect((int) x_koor - 96, (int) y_koor, 16, 45);
            g.setColor(Color.BLACK);
            g.drawRect((int) x_koor - 96, (int) y_koor, 16, 45);
        }
        if (doors == EnumDopDoors.FiveDoors) {
            g.setColor(dopcolor);
            g.fillRect((int) x_koor - 96, (int) y_koor, 16, 45);
            g.fillRect((int) x_koor - 80, (int) y_koor, 16, 45);

            g.setColor(Color.BLACK);
            g.drawRect((int) x_koor - 96, (int) y_koor, 16, 45);
            g.drawRect((int) x_koor - 80, (int) y_koor, 16, 45);
        }
    }
}
