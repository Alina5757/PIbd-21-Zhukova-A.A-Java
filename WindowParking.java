import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;


public class WindowParking {

    Queue<Bus> busCollection = new LinkedList<Bus>();
    public Queue<Bus> GetbusCollection(){
        return busCollection;
    }
    private ParkingCollection parkingCollection;
    CanvasPark canvas;
    JList ListBox;

    public ParkingCollection GetParkingCollection() {
        return parkingCollection;
    }

    WindowParking() {
        canvas = new CanvasPark();
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
        parkingCollection = new ParkingCollection(canvas.getHeight(), canvas.getWidth());

        JLabel parklabel = new JLabel("Parking");
        parklabel.setBounds(955, 80, 150, 25);
        panel.add(parklabel);

        JTextField nameParking = new JTextField(3);
        nameParking.setBounds(905, 110, 150, 25);
        panel.add(nameParking);

        JButton AddParking = new JButton("Add Parking");
        AddParking.setBounds(905, 140, 150, 25);
        panel.add(AddParking);

        JButton FormBus = new JButton("Form Bus");
        FormBus.setBounds(905, 600, 150, 25);
        panel.add(FormBus);

        JPanel listPanel = new JPanel();
        DefaultListModel listModel = new DefaultListModel();
        ListBox = new JList(listModel);
        ListBox.setSize(100, 100);


        listPanel.setBounds(905, 180, 150, 150);
        ListBox.setBounds(0, 0, 150, 50);
        JScrollPane centerScroll = new JScrollPane(ListBox);
        centerScroll.setPreferredSize(new Dimension(150, 120));
        centerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listPanel.add(centerScroll, BorderLayout.WEST);
        panel.add(listPanel);

        ListBox.addListSelectionListener(new ListSelectionListener() {  //ReloadLevels
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try {
                        int index = listModel.indexOf(ListBox.getSelectedValue().toString());
                        if (listModel.getSize() > 0 && (index == -1 || index >= listModel.getSize())) {
                            ListBox.setSelectedIndex(0);
                        } else if (listModel.getSize() > 0 && index > -1 && index < listModel.getSize()) {
                            ListBox.setSelectedIndex(index);
                        }
                        canvas.DrawParking(parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()), frame);
                    }
                    catch (Exception exc){
                        ListBox.setSelectedIndex(0);
                    }
                }
            }
        });

        JButton DeleteParking = new JButton("Delete Parking");
        DeleteParking.setBounds(905, 340, 150, 25);
        panel.add(DeleteParking);


        JButton ButtonParkBus = new JButton("Park Bus");
        ButtonParkBus.setBounds(905, 450, 150, 25);
        panel.add(ButtonParkBus);

        JButton ButtonParkTwoFloorBus = new JButton("Park TwoFloorBus");
        ButtonParkTwoFloorBus.setBounds(905, 480, 150, 25);
        panel.add(ButtonParkTwoFloorBus);

        JTextField numberPark = new JTextField(3);
        numberPark.setBounds(905, 530, 150, 25);
        panel.add(numberPark);

        JButton ButtonPickUpBus = new JButton("Pick up bus");
        ButtonPickUpBus.setBounds(905, 560, 150, 25);
        panel.add(ButtonPickUpBus);

        AddParking.addActionListener(e -> {
            if(nameParking.getText() == ""){
                JOptionPane.showMessageDialog(frame, "Parking must have a name!");
            }
            else if(!listModel.contains(nameParking.getText())){
                listModel.addElement(nameParking.getText());
                parkingCollection.AddParking(nameParking.getText());
            }
        });

        DeleteParking.addActionListener(e -> {
            if (ListBox.getSelectedIndex() > -1) {
                String value = ListBox.getSelectedValue().toString();
                listModel.remove(ListBox.getSelectedIndex());
                parkingCollection.DelParking(value);

            }
        });

        ButtonParkBus.addActionListener(e -> {
            Color color = JColorChooser.showDialog(panel, "Choose Color", Color.cyan);
            Random random = new Random();
            Bus bus = new Bus(100 + Math.abs(random.nextInt() % 101), Math.abs(1000 + random.nextInt() % 2001), color);
            if (parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()).plus(parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()), bus) == 1) {
                parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()).Draw(canvas.g2d, frame);
            }
        });

        ButtonParkTwoFloorBus.addActionListener(e -> {
            Color osncolor = JColorChooser.showDialog(panel, "Choose Color", Color.cyan);
            Color dopcolor = JColorChooser.showDialog(panel, "Choose Color", Color.cyan);
            Random random = new Random();
            Bus bus = new TwoFloorBus(100 + Math.abs(random.nextInt() % 101), 1000 + Math.abs(random.nextInt() % 2001), osncolor, dopcolor, true, true);
            if (parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()).plus(parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()), bus) == 1) {
                parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()).Draw(canvas.g2d, frame);
            }
        });

        ButtonPickUpBus.addActionListener(e -> {
            int number = 0;
            boolean correct = true;
            try {
                number = Integer.parseInt(numberPark.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Parking space number must contain only digits!");
                correct = false;
            }
            if (correct) {
                Bus bus = (Bus) parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()).minus(parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()), number);
                if (bus != null) {
                    busCollection.add(bus);
                }
                parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()).Draw(canvas.g2d, frame);
                frame.repaint();
                canvas.DrawParking(parkingCollection.GetParkingInd(ListBox.getSelectedValue().toString()), frame);
            }
        });

        FormBus.addActionListener(e -> {
            WindowBus form = new WindowBus(frame, this);
            if (form.bus_exist == false){
                JOptionPane.showMessageDialog(frame, "There are no buses in the queue!");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        frame.setState(JFrame.ICONIFIED);
        frame.setLocationRelativeTo(null);
    }
}

class CanvasPark extends JComponent {
    ParkingBus<Vehicle, InterDop> parking;
    JFrame frame;
    public Graphics2D g2d;

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;
        if(frame != null) {
            DrawParking(parking, frame);
        }
    }

    void DrawParking(ParkingBus<Vehicle, InterDop> Parking, JFrame Frame){
        frame = Frame;
        parking = Parking;
        parking.Draw(g2d ,frame);
    }
}

