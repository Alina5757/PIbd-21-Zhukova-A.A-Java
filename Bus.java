import java.awt.*;
import java.util.Random;

class Bus extends Vehicle {
    private int busWidth = 95;
    public int GetBusWidth() { return busWidth; }

    protected int busHeight = 35;
    public int GetBusHeight() { return busHeight; }

    protected String separator = ";";

    InterDop dopdoors;
    public void SetDoors(InterDop inter){
        dopdoors = inter;
    }

    public Bus(int Maxspeed, int Weight, Color MainColor) {
        maxSpeed = Maxspeed;
        weight = Weight;
        mainColor = MainColor;

        Random random = new Random();
        int TypeInter = random.nextInt() % 3;
        if(TypeInter == 0){
            dopdoors = new ClassDopDoorsNormal();
        }
        else if(TypeInter == 1){
            dopdoors = new ClassDopDoorsOval();
        }
        else {
            dopdoors = new ClassDopDoorsSkos();
        }
        dopdoors.SetDoors(3 + Math.abs(random.nextInt() % 3));  //от 3 до 5 включительно
    }

    protected Bus(int Maxspeed, int Weight, Color MainColor, int BusWidth, int BusHeight) {
        maxSpeed = Maxspeed;
        weight = Weight;
        mainColor = MainColor;
        this.busWidth = BusWidth;
        this.busHeight = BusHeight;

        Random random = new Random();
        int TypeInter = random.nextInt() % 3;
        if(TypeInter == 0){
            dopdoors = new ClassDopDoorsNormal();
        }
        else if(TypeInter == 1){
            dopdoors = new ClassDopDoorsOval();
        }
        else {
            dopdoors = new ClassDopDoorsSkos();
        }
        dopdoors.SetDoors(3 + (random.nextInt() % 3));  //от 3 до 5 включительно
    }

    public Bus(String info) {  //для загрузки с файла
        String[] str = info.split(separator);
        if (str.length == 5) {
            maxSpeed = Integer.parseInt(str[0]);
            weight = Integer.parseInt(str[1]);

            if(str[2].equals("Doors-Normal")) {
                dopdoors = new ClassDopDoorsNormal();
            }
            else if(str[2].equals("Doors-Oval")) {
                dopdoors = new ClassDopDoorsOval();
            }
            else if(str[2].equals("Doors-Skos")) {
                dopdoors = new ClassDopDoorsSkos();
            }
            else {return;}
            int kolvoDoor = Integer.parseInt(str[3]);
            if(kolvoDoor < 6 && kolvoDoor > 2){
                dopdoors.SetDoors(kolvoDoor);
            }
            mainColor = Color.decode(str[4]);
        }
    }

    public @Override
    void MoveTransport(Direction direction) {
        float step = Math.abs(maxSpeed * 100 / weight);
        if (step < 10 || step > 50){
            step = 20;
        }

        if (direction == Direction.Up) {
            if (y_koor - busHeight - step + 20 >= 0) {
                y_koor -= step;
            }
        }
        if (direction == Direction.Down) {
            if (y_koor + busHeight + step <= 860) {
                y_koor += step;
            }
        }
        if (direction == Direction.Left) {
            if (x_koor - busWidth - step >= 0) {
                x_koor -= step;
            }
        }
        if (direction == Direction.Right) {
            if (x_koor + busWidth + step <= 980) {
                x_koor += step;
            }
        }
    }

    public @Override
    void DrawTransport(Graphics g, Frame frame) {
        Color blueColor = new Color(192, 249, 255);
        Color greyColor = new Color(120, 120, 120);

        if(g!= null) {
            g.setColor(mainColor);
            g.fillRect((int) x_koor - 100, (int) y_koor - 30, 200, 70);  //корпус первый этаж
            g.setColor(Color.BLACK);
            g.drawRect((int) x_koor - 100, (int) y_koor - 30, 200, 70);

            g.setColor(blueColor);
            g.fillRect((int) x_koor - 60, (int) y_koor - 20, 25, 25);  //окна первый этаж
            g.fillRect((int) x_koor - 30, (int) y_koor - 20, 25, 25);
            g.fillRect((int) x_koor + 38, (int) y_koor - 20, 25, 25);
            g.fillRect((int) x_koor + 90, (int) y_koor - 20, 10, 25);

            g.setColor(Color.BLACK);
            g.drawRect((int) x_koor - 60, (int) y_koor - 20, 25, 25);
            g.drawRect((int) x_koor - 30, (int) y_koor - 20, 25, 25);
            g.drawRect((int) x_koor + 38, (int) y_koor - 20, 25, 25);
            g.drawRect((int) x_koor + 90, (int) y_koor - 20, 10, 25);

            g.setColor(greyColor);
            g.fillOval((int) x_koor - 60, (int) y_koor + 20, 35, 35);   //колеса
            g.fillOval((int) x_koor + 35, (int) y_koor + 20, 35, 35);

            g.setColor(Color.BLACK);
            g.drawOval((int) x_koor - 60, (int) y_koor + 20, 35, 35);
            g.drawOval((int) x_koor + 35, (int) y_koor + 20, 35, 35);

            dopdoors.DrowDoors(g, x_koor, y_koor);
        }
        frame.repaint();
    }

    public @Override
    String toString(){
        String str ="";
        str += maxSpeed + separator + weight + separator;
        if(dopdoors instanceof ClassDopDoorsNormal){
            str += "Doors-Normal" + separator + (((ClassDopDoorsNormal) dopdoors).GetDoors().ordinal()+3) + separator;
        }
        else if(dopdoors instanceof ClassDopDoorsOval){
            str += "Doors-Oval" + separator + (((ClassDopDoorsOval) dopdoors).GetDoors().ordinal()+3) + separator;
        }
        else if(dopdoors instanceof ClassDopDoorsSkos){
            str += "Doors-Skos" + separator + (((ClassDopDoorsSkos) dopdoors).GetDoors().ordinal()+3) + separator;
        }
        str += mainColor.getRGB();
        return str;
    }
}