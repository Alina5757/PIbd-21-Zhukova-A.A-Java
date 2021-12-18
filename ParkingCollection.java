import java.io.*;
import java.util.*;

public class ParkingCollection {
    WindowParking windowParking;
    public Map<String, ParkingBus<Vehicle, InterDop>> parkingStages;

    public ArrayList<String> Keys(){
        ArrayList<String> keys = new ArrayList<>();
        for (String key: parkingStages.keySet()) {
            keys.add(key);
        }
        return keys;
    }
    private int pictureHeight;
    public int GetpictureHeight() { return pictureHeight; }
    private int pictureWidth;
    public int GetpictureWidth() { return pictureWidth; }
    String separator = ":";

    public ParkingCollection(int PictureHeight, int PictureWidth, WindowParking windowParking) {
        this.windowParking = windowParking;
        parkingStages = new HashMap<String, ParkingBus<Vehicle, InterDop>>();
        pictureHeight = PictureHeight;
        pictureWidth = PictureWidth;
    }

    public void AddParking(String name) {
        if (!parkingStages.containsKey(name))
        {
            parkingStages.put(name, new ParkingBus<Vehicle, InterDop>(pictureHeight, pictureWidth));
        }
    }

    public void DelParking(String name) {
        if (parkingStages.containsKey(name))
        {
            parkingStages.remove(name);
        }
    }

    public void Clear(String key){
        this.parkingStages.get(key).GetPlaces().clear();
    }

    public ParkingBus<Vehicle, InterDop> GetParkingInd(String ind)
    {
        if (parkingStages.containsKey(ind)){
            return parkingStages.get(ind);
        }
        return null;
    }

    public Boolean SafeData(File file, String nameParking, ParkingBus Selectparking) {
        FileWriter fwriter = null;
        BufferedWriter writer = null;
        if (file != null) {
            if(!file.delete()){
                return false;
            }
        }
        try {
            fwriter = new FileWriter(file);
            writer = new BufferedWriter(fwriter);

            writer.write("ParkingCollection");
            writer.newLine();
            if (nameParking == null) {
                for (Map.Entry<String, ParkingBus<Vehicle, InterDop>> level : parkingStages.entrySet()) {
                    writer.write("Parking" + separator + level.getKey());
                    writer.newLine();
                    ITransport bus = null;
                    for (int i = 0; (bus = level.getValue().GetBusOfInd(i)) != null; i++) {
                        if (bus != null) {
                            if (bus instanceof TwoFloorBus) {
                                writer.write("TwoFloorBus" + separator);
                                writer.newLine();
                            }
                            else if (bus instanceof Bus) {
                                writer.write("Bus" + separator);
                                writer.newLine();
                            }
                            writer.write(bus.toString());
                            writer.newLine();
                        }
                    }
                }
            } else {
                if(Selectparking == null || nameParking == null){
                    return false;
                }
                writer.write("Parking" + separator + nameParking);
                writer.newLine();
                ITransport bus = null;
                for (int i = 0; (bus = Selectparking.GetBusOfInd(i)) != null; i++) {
                    if (bus != null) {
                        if (bus instanceof TwoFloorBus) {
                            writer.write("TwoFloorBus" + separator);
                            writer.newLine();
                        }
                        else if (bus instanceof Bus) {
                            writer.write("Bus" + separator);
                            writer.newLine();
                        }
                        writer.write(bus.toString());
                        writer.newLine();
                    }
                }
            }
        }
        catch (Exception e){
            return false;
        }
        finally{
            try {
                writer.flush();
                fwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public Boolean LoadData(File file, String reshim) {
        FileReader freader;
        BufferedReader reader;
        if (file == null) {
            return false;
        }
        try {
            freader = new FileReader(file);
            reader = new BufferedReader(freader);

            String line = reader.readLine();
            if (line.contains("ParkingCollection") && reshim.equals("collection")) {
                parkingStages.clear();
                windowParking.listModel.clear();
            } else if (line.contains("Parking")) {
            } else {
                return false;
            }
            Vehicle bus = null;
            String key = "";

            while (!line.equals("")) {
                line = reader.readLine();
                if (!line.equals("")) {
                    if (line.contains("Parking")) {

                        key = line.split(separator)[1];
                        int index = windowParking.listModel.indexOf(key);
                        if(reshim.equals("parking") && index >= 0){
                            Clear(key);
                        }
                        else {
                            parkingStages.put(key, new ParkingBus<Vehicle, InterDop>(pictureHeight, pictureWidth));
                            this.AddParking(key);
                            windowParking.listModel.addElement(key);
                            continue;
                        }
                    }

                    if (line.split(separator)[0].contains("TwoFloorBus")) {
                        line = reader.readLine();
                        bus = new TwoFloorBus(line);
                    } else if (line.split(separator)[0].contains("Bus")) {
                        line = reader.readLine();
                        bus = new Bus(line);
                    }
                    if (line != "") {
                        int result = parkingStages.get(key).plus(parkingStages.get(key), bus);
                        if (result != 1) {
                            return false;
                        }
                    }
                }
            }
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


    public Bus GetBusFromParking(String key, int index)
    {
        ParkingBus parkingBus;
        if (parkingStages.containsKey(key)){
            parkingBus = parkingStages.get(key);
            if(index < parkingBus.GetPlaces().size()){
                return (Bus)parkingBus.GetPlaces().get(index);
            }
        }
        return null;
    }
}
