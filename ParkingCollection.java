import java.util.*;

public class ParkingCollection {
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

    public ParkingCollection(int PictureHeight, int PictureWidth) {
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

    public ParkingBus<Vehicle, InterDop> GetParkingInd(String ind)
    {
        if (parkingStages.containsKey(ind)){
            return parkingStages.get(ind);
        }
        return null;
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
