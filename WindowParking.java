import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;


public class WindowParking {

    Queue<Bus> busCollection = new LinkedList<Bus>();
    public Queue<Bus> GetbusCollection(){
        return busCollection;
    }
    private ParkingCollection parkingCollection;
    JFrame frame;
    CanvasPark canvas;
    JList ListBoxwind;
    DefaultListModel listModel;

    public ParkingCollection GetParkingCollection() {
        return parkingCollection;
    }

    WindowParking() {
        canvas = new CanvasPark();
        frame = new JFrame("Window Parking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 700);
        Container container = frame.getContentPane();
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        BorderLayout blayot = new BorderLayout();
        container.setLayout(blayot);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenu saveMenu = new JMenu("Save");
        fileMenu.add(saveMenu);

        JMenuItem saveParkingColl = new JMenuItem("Save Parking Collection");
        saveMenu.add(saveParkingColl);

        JMenuItem saveParking = new JMenuItem("Save Selected Parking");
        saveMenu.add(saveParking);

        JMenu loadMenu = new JMenu("Load");
        fileMenu.add(loadMenu);

        JMenuItem loadParking = new JMenuItem("Load Parking from File");
        loadMenu.add(loadParking);

        JMenuItem loadCollection = new JMenuItem("Load Parkings Collection from File");
        loadMenu.add(loadCollection);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        saveParking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                fileopen.setFileFilter(filter);
                int ret = fileopen.showDialog(null, "Open File");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    if(parkingCollection.SafeData(file, ListBoxwind.getSelectedValue().toString(),
                            parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString()))){
                        JOptionPane.showMessageDialog(frame, "Save complete successfully");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame, "No file selected");
                }
            }
        });

        saveParkingColl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                fileopen.setFileFilter(filter);
                int ret = fileopen.showDialog(null, "Open File");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    if(parkingCollection.SafeData(file, null, null)){
                        JOptionPane.showMessageDialog(frame, "Save complete successfully");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(frame, "No file selected");
                }
            }
        });

        loadParking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                fileopen.setFileFilter(filter);
                int ret = fileopen.showDialog(null, "Load from File");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    if(parkingCollection.LoadData(file, "parking")){
                        JOptionPane.showMessageDialog(frame, "Load complete successfully");
                    }

                }
                else {
                    JOptionPane.showMessageDialog(frame, "No file selected");
                }
            }
        });

        loadCollection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
                fileopen.setFileFilter(filter);
                int ret = fileopen.showDialog(null, "Load from File");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    if(parkingCollection.LoadData(file, "collection")){
                        JOptionPane.showMessageDialog(frame, "Load complete successfully");
                    }

                }
                else {
                    JOptionPane.showMessageDialog(frame, "No file selected");
                }
            }
        });


        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        canvas.setBounds(5, 5, 1000, 600);
        frame.add(canvas, BorderLayout.CENTER);
        parkingCollection = new ParkingCollection(canvas.getHeight(), canvas.getWidth(), this);

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
        listModel = new DefaultListModel();
        ListBoxwind = new JList(listModel);
        ListBoxwind.setSize(100, 100);


        listPanel.setBounds(905, 180, 150, 150);
        ListBoxwind.setBounds(0, 0, 150, 50);
        JScrollPane centerScroll = new JScrollPane(ListBoxwind);
        centerScroll.setPreferredSize(new Dimension(150, 120));
        centerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listPanel.add(centerScroll, BorderLayout.WEST);
        panel.add(listPanel);

        ListBoxwind.addListSelectionListener(new ListSelectionListener() {  //ReloadLevels
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try {
                        int index = listModel.indexOf(ListBoxwind.getSelectedValue().toString());
                        if (listModel.getSize() > 0 && (index == -1 || index >= listModel.getSize())) {
                            ListBoxwind.setSelectedIndex(0);
                        } else if (listModel.getSize() > 0 && index > -1 && index < listModel.getSize()) {
                            ListBoxwind.setSelectedIndex(index);
                        }
                        canvas.DrawParking(parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString()), frame);
                    }
                    catch (Exception exc){
                        ListBoxwind.setSelectedIndex(0);
                    }
                }
            }
        });

        JButton DeleteParking = new JButton("Delete Parking");
        DeleteParking.setBounds(905, 340, 150, 25);
        panel.add(DeleteParking);

        JButton ButtonCreateBus = new JButton("Create Bus");
        ButtonCreateBus.setBounds(905, 450, 150, 50);
        panel.add(ButtonCreateBus);

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
                try {
                    canvas.DrawParking(parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString()), frame);
                }
                catch (Exception ex){}
            }
        });

        DeleteParking.addActionListener(e -> {
            if (ListBoxwind.getSelectedIndex() > -1) {
                String value = ListBoxwind.getSelectedValue().toString();
                listModel.remove(ListBoxwind.getSelectedIndex());
                parkingCollection.DelParking(value);
            }
        });

        ButtonCreateBus.addActionListener(e -> {
            WindowBusConfig form = new WindowBusConfig(frame, this);
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
                Bus bus = (Bus) parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString()).minus(parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString()), number);
                if (bus != null) {
                    busCollection.add(bus);
                }
                parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString()).Draw(canvas.g2d, frame);
                frame.repaint();
                canvas.DrawParking(parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString()), frame);
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

    public void AddBus(Vehicle bus){
        if (bus != null && ListBoxwind.getSelectedIndex() > -1) {
            ParkingBus p = parkingCollection.GetParkingInd(ListBoxwind.getSelectedValue().toString());
            if (p.GetPlaces().size() < 9){
                p.plus(p, bus);
                frame.repaint();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Error, the bus could not be place");
            }
        }
    }
}

class CanvasPark extends JComponent {
    ParkingBus<Vehicle, InterDop> parking;
    JFrame frame;
    public Graphics2D g2d;

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;
        if(frame != null && parking != null) {
            DrawParking(parking, frame);
        }
    }

    void DrawParking(ParkingBus<Vehicle, InterDop> Parking, JFrame Frame){
        frame = Frame;
        parking = Parking;
        parking.Draw(g2d ,frame);
    }
}

