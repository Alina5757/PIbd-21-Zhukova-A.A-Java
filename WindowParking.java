import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WindowParking {

    private ParkingBus<Bus, InterDop> parking;
    public ParkingBus<Bus, InterDop> GetParkingBus() { return parking; }

    WindowParking() {
        CanvasPark canvas = new CanvasPark();
        JFrame frame = new JFrame("Window Parking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        Container container = frame.getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        BorderLayout blayot = new BorderLayout();
        container.setLayout(blayot);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        canvas.setBounds(5, 5, 1000, 600);
        frame.add(canvas, BorderLayout.CENTER);
        parking = new ParkingBus<>(canvas.getHeight(), canvas.getWidth());

        JButton ButtonParkBus = new JButton("Park Bus");
        ButtonParkBus.setBounds(905, 130, 150, 25);
        panel.add(ButtonParkBus);

        JButton ButtonParkTwoFloorBus = new JButton("Park TwoFloorBus");
        ButtonParkTwoFloorBus.setBounds(905, 160, 150, 25);
        panel.add(ButtonParkTwoFloorBus);

        JButton ButtonPickUpBus = new JButton("Pick up bus");
        ButtonPickUpBus.setBounds(905, 290, 150, 25);
        panel.add(ButtonPickUpBus);

        JTextField numberPark = new JTextField(3);
        numberPark.setBounds(905, 260, 150, 25);
        panel.add(numberPark);

        ButtonParkBus.addActionListener(e -> {
            Color color = JColorChooser.showDialog(panel, "Choose Color", Color.cyan);
            Random random = new Random();
            Bus bus = new Bus(100 + Math.abs(random.nextInt() % 101), Math.abs(1000 + random.nextInt() % 2001), color);
            if (parking.plus(parking, bus) == 1){
                parking.Draw(canvas.g2d, frame);
            }
        });

        ButtonParkTwoFloorBus.addActionListener(e -> {
            Color osncolor = JColorChooser.showDialog(panel, "Choose Color", Color.cyan);
            Color dopcolor = JColorChooser.showDialog(panel, "Choose Color", Color.cyan);
            Random random = new Random();
            Bus bus = new TwoFloorBus(100 + Math.abs(random.nextInt() % 101), 1000 + Math.abs(random.nextInt() % 2001), osncolor, dopcolor, true, true);
            if (parking.plus(parking, bus) == 1){
                parking.Draw(canvas.g2d, frame);
            }
        });

        ButtonPickUpBus.addActionListener(e -> {
            int number = 0;
            boolean correct = true;
            try{
                number = Integer.parseInt(numberPark.getText());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(frame, "Parking space number must contain only digits!");
                correct = false;
            }
            if (correct) {
                Bus bus = parking.minus(parking, number);
                if (bus != null) {
                    WindowBus form = new WindowBus(frame);
                    form.SetBus(bus);
                }
                parking.Draw(canvas.g2d, frame);
                frame.repaint();
                parking.lessOrEquals(parking, 3.5);
                parking.moreOrEquals(parking, 4.5);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        frame.setState(JFrame.ICONIFIED);
        frame.setLocationRelativeTo(null);
        canvas.DrawParking(parking, frame);
    }
}

class CanvasPark extends JComponent {
    ParkingBus<Bus, InterDop> parking;
    JFrame frame;
    public Graphics2D g2d;

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;
        if(frame != null) {
            DrawParking(parking, frame);
        }
    }

    void DrawParking(ParkingBus<Bus, InterDop> Parking, JFrame Frame){
        frame = Frame;
        parking = Parking;
        parking.Draw(g2d ,frame);
    }
}

