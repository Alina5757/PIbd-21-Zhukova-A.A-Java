import java.awt.*;

abstract public class Vehicle implements ITransport {
    protected float x_koor;
    protected float y_koor;
    protected int widthScreen;
    protected int heightScreen;
    public int maxSpeed;

    protected void SetMaxSpeed(int maxspeed) { maxSpeed = maxspeed; }
    public int GetMaxSpeed() { return maxSpeed; }

    public int weight;
    protected void SetWeight(int Weight) { weight = Weight; }
    public int GetWeight() { return weight; }

    public Color mainColor;

    public void SetPosition(float x, float y, int HeightScreen, int WidhtScreen) {
        if (((x > 0) && (x < WidhtScreen)) &&
                ((y > 0) && (y < HeightScreen))) {
            heightScreen = HeightScreen;
            widthScreen = WidhtScreen;
            x_koor = x;
            y_koor = y;
        }
    }

    public abstract void DrawTransport(Graphics g, Frame frame);
    public abstract void MoveTransport(Direction direction);
}