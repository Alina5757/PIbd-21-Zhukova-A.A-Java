import java.awt.*;
import java.util.Random;

public class ClassDopDoorsSkos implements InterDop{
    private EnumDopDoors doors;

    public void SetDoors(int value) {
        if (EnumDopDoors.ThreeDoors.KolvoDoorExist(value)) {
            doors = EnumDopDoors.ThreeDoors.GetDoors(value);
        } else {
            Random rand = new Random();
            doors = EnumDopDoors.ThreeDoors.GetDoors(3 + (rand.nextInt() % 4));
        }
    }

    public void DrowDoors(Graphics g, float x_koor, float y_koor) {
        Color greyColor = new Color(120, 120, 120);

        DrawDoor(g, (int) x_koor, (int) y_koor - 20, greyColor);
        DrawDoor(g, (int) x_koor + 16, (int) y_koor - 20, greyColor);
        DrawDoor(g, (int) x_koor + 68, (int) y_koor - 20, greyColor);

        if (doors == EnumDopDoors.FourDoors) {
            DrawDoor(g, (int) x_koor - 96, (int) y_koor - 20, greyColor);
        }

        if (doors == EnumDopDoors.FiveDoors) {
            DrawDoor(g, (int) x_koor - 96, (int) y_koor - 20, greyColor);
            DrawDoor(g, (int) x_koor - 80, (int) y_koor - 20, greyColor);
        }
    }
    void DrawDoor(Graphics g, int x, int y, Color greyColor) {
        int[] xPoints = {x + 4, x + 12, x + 16, x + 16, x + 12, x + 4, x, x};
        int[] yPoints = {y, y, y + 4, y + 41, y + 45, y + 45, y + 41, y + 4};

        g.setColor(greyColor);
        g.fillPolygon(xPoints, yPoints, 8);

        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 8);
    }
}
