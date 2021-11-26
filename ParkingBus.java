import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingBus<T extends ITransport, E extends InterDop> {

    JFrame frame;

    private List<T> places = new ArrayList<T>();
    public List <T> GetPlaces() { return places; }
    private int kolvoPlaces;
    private int pictureHeight;
    public int GetPictureHeight() { return pictureHeight; }
    private int pictureWidth;
    public int GetPictureWidth() { return pictureWidth; }
    private int placeHeight = 170;
    public int GetPlaceHeight() { return placeHeight; }
    private int placeWidth = 300;
    public int GetPlaceWidth() { return placeWidth; }
    private int height;
    public int GetHeight() { return height; }
    private int width;
    public int GetWidth() { return width; }

    public ParkingBus(int parkingHeight, int parkingWidth)
    {
        height = parkingHeight / placeHeight;
        width = parkingWidth / placeWidth;
        kolvoPlaces = height * width;
        pictureHeight = parkingHeight;
        pictureWidth = parkingWidth;
    }

    public int plus(ParkingBus<T, E> p, T bus)
    {
        for (int i = 0; i < kolvoPlaces; i++)
        {
            try {
               if(p.places.get(i) == null){
                   p.places.remove(i);
                   p.places.add(i, bus);
                   bus.SetPosition(110 + (i % p.height) * p.placeWidth, 100 + ((i) / (p.width)) * p.placeHeight, p.pictureHeight, p.pictureWidth);
                   return 1;
               }
            }
            catch (Exception e){
                p.places.add(bus);
                bus.SetPosition(110 + (i % p.height) * p.placeWidth, 100 + ((i) / (p.width)) * p.placeHeight, p.pictureHeight, p.pictureWidth);
                return 1;
            }
        }
        JOptionPane.showMessageDialog(frame,"Parking is full!");
        return -1;
    }

    public T minus(ParkingBus<T, E> p, int index)
    {
        if ((index <= p.places.size()) && (index >= 0)) {
            try {
                if (p.places.get(index) != null) {
                    T bus = p.places.get(index);
                    p.places.remove(index);
                    p.places.add(index, null);
                    frame.setEnabled(false);
                    return bus;
                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,"Place is empty");
                return null;
            }
        }
        JOptionPane.showMessageDialog(frame,"Place is empty");
        return null;
    }


    public boolean moreOrEquals(ParkingBus<T, E> p, double SpaceNeed) {
        //Определяет сумму объемов салонов автобусов на парковке, считая что объем обычного автобуса - 1, а объем двухэтажного автобуса - 1.5
        // возвращает true если суммарный объем больше или равен указанному числу

        double salonSpace = 0;
        int i = 0;
        for (; i < places.size(); i++) {
            T bus = places.get(i);
            if(bus!= null) {
                if (bus instanceof TwoFloorBus) {
                    salonSpace += 1.5;
                } else if (bus instanceof Bus) {
                    salonSpace += 1;
                }
            }
        }
        return salonSpace >= SpaceNeed;
    }


    public boolean lessOrEquals(ParkingBus<T, E> p, double Space) {
        //Определяет сумму объемов салонов автобусов на парковке, считая что объем обычного автобуса - 1, а объем двухэтажного автобуса - 1.5
        // возвращает true если суммарный объем меньше или равен указанному числу

        double salonSpace = 0;
        int i = 0;
        for (; i < places.size(); i++) {
            T bus = places.get(i);
            if(bus!= null) {
                if (bus instanceof TwoFloorBus) {
                    salonSpace += 1.5;
                } else if (bus instanceof Bus) {
                    salonSpace += 1;
                }
            }
        }
        return salonSpace <= Space;
    }


    public void Draw(Graphics g, JFrame frame){
        this.frame = frame;
        DrawMapking(g, frame);
        for (int i = 0; i < places.size(); i++) {
            if(places.get(i) != null){
            places.get(i).DrawTransport(g, frame);
            }
        }
        frame.repaint();
    }

    void DrawMapking(Graphics g, JFrame frame){
        if(g != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(3));
            for (int i = 0; i < pictureWidth / placeWidth; i++) {
                for (int j = 0; j < pictureHeight / placeHeight + 1; j++) {
                    g2d.drawLine(i * placeWidth, j * placeHeight, i * placeWidth + placeWidth / 10 * 7, j * placeHeight);
                    g2d.drawLine(i * placeWidth, 0, i * placeWidth, (pictureHeight / placeHeight) * placeHeight);
                }
            }
            g2d.setStroke(new BasicStroke(1));
        }
    }
}
