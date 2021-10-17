import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
//0000

public class WindowBus {
    BusTwoFloor Bus;
    private boolean bus_exist = false;
    WindowBus() {
        Canvas canvas = new Canvas();
        JFrame frame = new JFrame("Window Bus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);
        Container container = frame.getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        GridBagLayout layout = new GridBagLayout();
        BorderLayout blayot = new BorderLayout();
        container.setLayout(blayot);
        GridBagConstraints constraints = new GridBagConstraints();

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


        JButton ButtonCreate = new JButton("Create");
        ButtonCreate.setBounds(805, 690, 150, 25);
        panel.add(ButtonCreate);

        frame.add(panel);

        ButtonCreate.addActionListener(e -> {
            Random random = new Random();
            Bus = new BusTwoFloor();
            Bus.Init(100 + Math.abs(random.nextInt() % 101), 1000 + Math.abs(random.nextInt() % 2001), true, true, Color.RED, Color.YELLOW);
            Bus.SetPosition(100 + Math.abs(random.nextInt() % 701), 100 + Math.abs(random.nextInt() % 601));
            bus_exist = true;
            canvas.DrawBus(Bus, frame);
        });

        ButtonUp.addActionListener(e -> {
            if (bus_exist) {
                Bus.MoveTransport(Direction.Up);
                canvas.repaint();
            }
        });
        ButtonDown.addActionListener(e -> {
            if (bus_exist) {
                Bus.MoveTransport(Direction.Down);
                canvas.repaint();
            }
        });
        ButtonLeft.addActionListener(e -> {
            if (bus_exist) {
                Bus.MoveTransport(Direction.Left);
                canvas.repaint();
            }
        });
        ButtonRight.addActionListener(e -> {
            if (bus_exist) {
                Bus.MoveTransport(Direction.Right);
                canvas.repaint();
            }
        });

        canvas.setBounds(0,0, 1000,900);
        panel.add(canvas);
        frame.add(panel);
        frame.setVisible(true);
        frame.setState(JFrame.ICONIFIED);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Bus = new BusTwoFloor();
    }
}



class Canvas extends JComponent {
    JFrame frame;
    BusTwoFloor bus;
    Graphics2D g2d;
    boolean bus_exist = false;

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;
        if (bus_exist) {
            DrawBus(bus, frame);
        }
    }


    public void DrawBus(BusTwoFloor Bus, JFrame Frame) {
        frame = Frame;
        bus = Bus;
        Bus.DrowBus(g2d, Frame);
        bus_exist = true;
    }
}