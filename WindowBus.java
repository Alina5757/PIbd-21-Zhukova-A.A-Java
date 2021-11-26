import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WindowBus {
    ITransport bus;
    JFrame frame;
    JFrame parkingframe;
    CanvasBus canvas;
    private boolean bus_exist = false;
    WindowBus(JFrame park) {
        parkingframe = park;
        canvas = new CanvasBus();
        frame = new JFrame("Window Bus");

        frame.setSize(1000, 900);
        Container container = frame.getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        BorderLayout blayot = new BorderLayout();
        container.setLayout(blayot);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.add(canvas,BorderLayout.CENTER);

        Icon iconUp = new ImageIcon("C:\\Up.JPG");
        JButton ButtonUp = new JButton(iconUp);
        ButtonUp.setBounds(860, 730, 50, 50);
        panel.add(ButtonUp);

        Icon iconDown = new ImageIcon("C:\\Down.JPG");
        JButton ButtonDown = new JButton(iconDown);
        ButtonDown.setBounds(860, 790, 50, 50);
        panel.add(ButtonDown);

        Icon iconLeft = new ImageIcon("C:\\Left.JPG");
        JButton ButtonLeft = new JButton(iconLeft);
        ButtonLeft.setBounds(800, 790, 50, 50);
        panel.add(ButtonLeft);

        Icon iconRight = new ImageIcon("C:\\Right.JPG");
        JButton ButtonRight = new JButton(iconRight);
        ButtonRight.setBounds(920, 790, 50, 50);
        panel.add(ButtonRight);


        JButton ButtonCreateBus = new JButton("Create Bus");
        ButtonCreateBus.setBounds(805, 690, 150, 25);
        panel.add(ButtonCreateBus);

        JButton ButtonCreateTwoFloorBus = new JButton("Create TwoFloorBus");
        ButtonCreateTwoFloorBus.setBounds(805, 660, 150, 25);
        panel.add(ButtonCreateTwoFloorBus);

        frame.add(panel);

        ButtonCreateBus.addActionListener(e -> {
            Random random = new Random();
            bus = new Bus(100 + random.nextInt() % 101, 1000 + random.nextInt() % 2001, Color.RED);
            bus.SetPosition(100 + Math.abs(random.nextInt() % 701), 100 + Math.abs(random.nextInt() % 601), frame.getHeight(), frame.getWidth());
            bus_exist = true;
            canvas.DrawBus(bus, frame);
        });

        ButtonCreateTwoFloorBus.addActionListener(e -> {
            Random random = new Random();
            bus = new TwoFloorBus(100 + random.nextInt() % 101,1000 + random.nextInt()%2001, Color.RED, Color.YELLOW, true, true);
            bus.SetPosition(100 + Math.abs(random.nextInt() % 701), 100 + Math.abs(random.nextInt() % 601), frame.getHeight(), frame.getWidth());
            bus_exist = true;
            canvas.DrawBus(bus, frame);
        });

        ButtonUp.addActionListener(e -> {
            if (bus_exist) {
                bus.MoveTransport(Direction.Up);
                canvas.repaint();
            }
        });
        ButtonDown.addActionListener(e -> {
            if (bus_exist) {
                bus.MoveTransport(Direction.Down);
                canvas.repaint();
            }
        });
        ButtonLeft.addActionListener(e -> {
            if (bus_exist) {
                bus.MoveTransport(Direction.Left);
                canvas.repaint();
            }
        });
        ButtonRight.addActionListener(e -> {
            if (bus_exist) {
                bus.MoveTransport(Direction.Right);
                canvas.repaint();
            }
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parkingframe.setEnabled(true);
            }
        });

        canvas.setBounds(0,0, 1000,900);
        frame.setState(JFrame.ICONIFIED);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void SetBus(ITransport bus) {
        this.bus = bus;
        bus_exist = true;
        canvas.bus = bus;
        canvas.bus_exist = true;
        canvas.frame = frame;
    }
}

class CanvasBus extends JComponent {
    JFrame frame;
    ITransport bus;
    Graphics2D g2d;
    boolean bus_exist = false;

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;
        if (bus_exist) {
            DrawBus(bus, frame);
        }
    }

    public void DrawBus(ITransport Bus, JFrame Frame) {
        frame = Frame;
        bus = Bus;
        Bus.DrawTransport(g2d, Frame);
        bus_exist = true;
    }
}