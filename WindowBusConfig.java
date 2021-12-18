import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WindowBusConfig {
    Vehicle bus;
    JFrame frame;
    JFrame parkingframe;
    CanvasBusConfig canvas;
    public WindowParking Wparking;
    public boolean bus_exist = false;

    Object selectObj;
    JPanel panelBus;
    JLabel panelMainColor;
    JLabel panelDopColor;
    JSpinner SpinnerSpeed;
    JSpinner SpinnerWeight;
    JCheckBox checkSecondFloor;
    JCheckBox checkStripe;
    JLabel panelDoorNormal;
    JLabel panelDoorOval;
    JLabel panelDoorSkos;
    JLabel DoorSelect;

    JPanel panelColor1;
    JPanel panelColor2;
    JPanel panelColor3;
    JPanel panelColor4;
    JPanel panelColor5;
    JPanel panelColor6;
    JPanel panelColor7;
    JPanel panelColor8;

    DropListener activedroplistener = null;

    WindowBusConfig(JFrame park, WindowParking Wparking) {
        this.Wparking = Wparking;
        parkingframe = park;
        canvas = new CanvasBusConfig();
        frame = new JFrame("Window Bus Config");
        frame.setSize(1000, 900);
        Container container = frame.getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        BorderLayout blayot = new BorderLayout();
        container.setLayout(blayot);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.add(canvas, BorderLayout.CENTER);

        SpinnerSpeed = new JSpinner(new SpinnerNumberModel(100, 100, 1000, 1));
        SpinnerSpeed.setBounds(130, 740, 100, 25);
        panel.add(SpinnerSpeed);

        JLabel spinSpeed = new JLabel("Speed");
        spinSpeed.setBounds(80, 740, 100, 25);
        panel.add(spinSpeed);

        SpinnerWeight = new JSpinner(new SpinnerNumberModel(100, 100, 1000, 1));
        SpinnerWeight.setBounds(130, 780, 100, 25);
        panel.add(SpinnerWeight);

        JLabel spinWeight = new JLabel("Weight");
        spinWeight.setBounds(80, 780, 100, 25);
        panel.add(spinWeight);


        checkSecondFloor = new JCheckBox("Second Floor");
        checkSecondFloor.setBounds(400, 740, 100, 25);
        panel.add(checkSecondFloor);

        checkStripe = new JCheckBox("Stripe");
        checkStripe.setBounds(400, 780, 100, 25);
        panel.add(checkStripe);


        DoorSelect = new JLabel("Select Door", SwingConstants.CENTER);
        DoorSelect.setFont(new Font("Calibri", Font.PLAIN, 15));
        DoorSelect.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        DoorSelect.setBounds(530, 620, 150, 50);
        panel.add(DoorSelect);

        panelDoorNormal = new JLabel("Normal Door", SwingConstants.CENTER);
        panelDoorNormal.setName("DoorNormal");
        panelDoorNormal.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelDoorNormal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDoorNormal.setBounds(400, 570, 125, 40);
        panel.add(panelDoorNormal);

        panelDoorOval = new JLabel("Oval Door", SwingConstants.CENTER);
        panelDoorOval.setName("DoorOval");
        panelDoorOval.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelDoorOval.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDoorOval.setBounds(400, 620, 125, 40);
        panel.add(panelDoorOval);

        panelDoorSkos = new JLabel("Skos Door", SwingConstants.CENTER);
        panelDoorSkos.setName("DoorSkos");
        panelDoorSkos.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelDoorSkos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDoorSkos.setBounds(400, 670, 125, 40);
        panel.add(panelDoorSkos);


        JLabel LabelBus = new JLabel("Bus", SwingConstants.CENTER);
        LabelBus.setFont(new Font("Calibri", Font.PLAIN, 25));
        LabelBus.setBounds(30, 130, 200, 100);
        LabelBus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panel.add(LabelBus);

        JLabel LabelTwoFloorBus = new JLabel("Two-Floor Bus", SwingConstants.CENTER);
        LabelTwoFloorBus.setFont(new Font("Calibri", Font.PLAIN, 30));
        LabelTwoFloorBus.setBounds(30, 250, 200, 100);
        LabelTwoFloorBus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panel.add(LabelTwoFloorBus);

        panelBus = new JPanel();
        panelBus.setBounds(280, 80, 390, 440);
        panelBus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.add(panelBus);

        panelMainColor = new JLabel("Main Color", SwingConstants.CENTER);
        panelMainColor.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelMainColor.setBounds(700, 80, 100, 80);
        panelMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panel.add(panelMainColor);

        panelDopColor = new JLabel("Dop Color", SwingConstants.CENTER);
        panelDopColor.setFont(new Font("Calibri", Font.PLAIN, 20));
        panelDopColor.setBounds(830, 80, 100, 80);
        panelDopColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panel.add(panelDopColor);

        panelColor1 = new JPanel();
        panelColor1.setBounds(700, 190, 50, 50);
        panelColor1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor1.setBackground(Color.WHITE);
        panelColor1.setName("panelColor");
        panel.add(panelColor1);

        panelColor2 = new JPanel();
        panelColor2.setBounds(760, 190, 50, 50);
        panelColor2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor2.setBackground(Color.DARK_GRAY);
        panelColor2.setName("panelColor");
        panel.add(panelColor2);

        panelColor3 = new JPanel();
        panelColor3.setBounds(820, 190, 50, 50);
        panelColor3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor3.setBackground(Color.RED);
        panelColor3.setName("panelColor");
        panel.add(panelColor3);

        panelColor4 = new JPanel();
        panelColor4.setBounds(880, 190, 50, 50);
        panelColor4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor4.setBackground(Color.YELLOW);
        panelColor4.setName("panelColor");
        panel.add(panelColor4);

        panelColor5 = new JPanel();
        panelColor5.setBounds(700, 260, 50, 50);
        panelColor5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor5.setBackground(Color.PINK);
        panelColor5.setName("panelColor");
        panel.add(panelColor5);

        panelColor6 = new JPanel();
        panelColor6.setBounds(760, 260, 50, 50);
        panelColor6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor6.setBackground(Color.BLUE);
        panelColor6.setName("panelColor");
        panel.add(panelColor6);

        panelColor7 = new JPanel();
        panelColor7.setBounds(820, 260, 50, 50);
        panelColor7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor7.setBackground(Color.CYAN);
        panelColor7.setName("panelColor");
        panel.add(panelColor7);

        panelColor8 = new JPanel();
        panelColor8.setBounds(880, 260, 50, 50);
        panelColor8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelColor8.setBackground(Color.GREEN);
        panelColor8.setName("panelColor");
        panel.add(panelColor8);

        JButton ButtonAddBus = new JButton("Add Bus");
        ButtonAddBus.setBounds(700, 750, 100, 50);
        panel.add(ButtonAddBus);

        JButton ButtonCansel = new JButton("Cansel");
        ButtonCansel.setBounds(845, 750, 100, 50);
        panel.add(ButtonCansel);

        frame.add(panel);

        LabelBus.addMouseListener(new SelectListener());
        LabelTwoFloorBus.addMouseListener(new SelectListener());
        panelColor1.addMouseListener(new SelectListener());
        panelColor2.addMouseListener(new SelectListener());
        panelColor3.addMouseListener(new SelectListener());
        panelColor4.addMouseListener(new SelectListener());
        panelColor5.addMouseListener(new SelectListener());
        panelColor6.addMouseListener(new SelectListener());
        panelColor7.addMouseListener(new SelectListener());
        panelColor8.addMouseListener(new SelectListener());
        panelDoorNormal.addMouseListener(new SelectListener());
        panelDoorOval.addMouseListener(new SelectListener());
        panelDoorSkos.addMouseListener(new SelectListener());

        panelBus.addMouseListener(new DropListener());
        panelMainColor.addMouseListener(new DropListener());
        panelDopColor.addMouseListener(new DropListener());
        DoorSelect.addMouseListener(new DropListener());


        ButtonAddBus.addActionListener(e -> {
            Wparking.AddBus(bus);
            this.frame.dispose();
        });

        ButtonCansel.addActionListener(e -> {
            this.frame.dispose();
        });


        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parkingframe.setEnabled(true);
            }
        });

        canvas.setBounds(300, 100, 350, 400);
        frame.setState(JFrame.ICONIFIED);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.repaint();
    }

    public class SelectListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {}

        public void mousePressed(MouseEvent e) {
            selectObj = e.getSource();
        }

        public void mouseReleased(MouseEvent e) {
            if (selectObj != null && activedroplistener.enter) {
                if ((activedroplistener.event.getSource() instanceof JPanel) &&
                        (((JPanel) activedroplistener.event.getSource()).equals(panelBus))) {
                    if (selectObj instanceof JLabel && (((JLabel) selectObj).getText().equals("Bus"))) {
                        bus = new Bus((Integer) SpinnerSpeed.getValue(), (Integer) SpinnerWeight.getValue(), Color.WHITE);
                        bus.SetPosition(160, 150, frame.getHeight(), frame.getWidth());
                        bus_exist = true;
                        canvas.DrawBus(bus, frame);
                        frame.repaint();
                    }
                    if (selectObj instanceof JLabel && (((JLabel) selectObj).getText().equals("Two-Floor Bus"))) {
                        bus = new TwoFloorBus((Integer) SpinnerSpeed.getValue(), (Integer) SpinnerWeight.getValue(),
                                Color.WHITE, Color.BLACK, checkStripe.isSelected(), checkSecondFloor.isSelected());
                        bus.SetPosition(160, 150, frame.getHeight(), frame.getWidth());
                        bus_exist = true;
                        canvas.DrawBus(bus, frame);
                        frame.repaint();
                    }
                }


                else if (selectObj instanceof JPanel && (((JPanel) selectObj).getName().contains("panelColor"))){
                    if ((activedroplistener.event.getSource() instanceof JLabel) &&
                            (((JLabel) activedroplistener.event.getSource()).equals(panelMainColor))) {
                        bus.mainColor = ((JPanel)selectObj).getBackground();
                        frame.repaint();
                    }
                    else if ((activedroplistener.event.getSource() instanceof JLabel) &&
                            (((JLabel) activedroplistener.event.getSource()).equals(panelDopColor))) {
                        if(bus instanceof TwoFloorBus) {
                            ((TwoFloorBus) bus).dopColor = ((JPanel) selectObj).getBackground();
                            frame.repaint();
                        }
                    }
                }

                else if (selectObj instanceof JLabel && (((JLabel) selectObj).getName().contains("Door"))){
                    if ((activedroplistener.event.getSource() instanceof JLabel) &&
                            (((JLabel) activedroplistener.event.getSource()).equals(DoorSelect))) {
                        int kolvo = ((Bus)bus).dopdoors.GetDoorsInt();
                        if(((JLabel) selectObj).getName().contains("DoorNormal")){
                            ((Bus)bus).SetDoors(new ClassDopDoorsNormal());
                            ((Bus)bus).dopdoors.SetDoors(kolvo);
                        }
                        else if(((JLabel) selectObj).getName().contains("DoorOval")){
                            ((Bus)bus).SetDoors(new ClassDopDoorsOval());
                            ((Bus)bus).dopdoors.SetDoors(kolvo);
                        }
                        else{
                            ((Bus)bus).SetDoors(new ClassDopDoorsSkos());
                            ((Bus)bus).dopdoors.SetDoors(kolvo);
                        }
                        frame.repaint();
                    }
                    else if ((activedroplistener.event.getSource() instanceof JLabel) &&
                            (((JLabel) activedroplistener.event.getSource()).equals(panelDopColor))) {
                        if(bus instanceof TwoFloorBus) {
                            ((TwoFloorBus) bus).dopColor = ((JPanel) selectObj).getBackground();
                            frame.repaint();
                        }
                    }
                }
            }
            selectObj = null;
        }

        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public class DropListener implements MouseListener {
        public MouseEvent event;
        boolean enter = false;
        @Override
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            activedroplistener = this;
            event = e;
            enter = true;
        }
        @Override
        public void mouseExited(MouseEvent e) {
            enter = false;
        }
    }


    class CanvasBusConfig extends JComponent {
        JFrame frame;
        ITransport bus;
        Graphics2D g2d;
        public boolean bus_exist = false;

        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            g2d = (Graphics2D) g;
            if (bus_exist) {
                DrawBus(bus, frame);
            }
            g2d.drawRect(0, 0, 349, 399);
        }

        public void DrawBus(ITransport Bus, JFrame Frame) {
            frame = Frame;
            bus = Bus;
            Bus.DrawTransport(g2d, Frame);
            bus_exist = true;
        }
    }
}
