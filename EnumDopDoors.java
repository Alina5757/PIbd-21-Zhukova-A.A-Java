import java.io.Console;

public enum EnumDopDoors {
    ThreeDoors(3),
    FourDoors(4),
    FiveDoors(5);

    private int kolvodoor;
    private EnumDopDoors(int Kolvodoor) {
        this.kolvodoor = Kolvodoor;
    }
    
    public boolean KolvoDoorExist(int kolvo){
        boolean flag = false;
        for (EnumDopDoors mass : EnumDopDoors.values()){
            if (mass.ordinal() + 3 == kolvo){
                flag = true;
            }
        }
        return flag;
    }

    public EnumDopDoors GetDoors(int ind){
        for (EnumDopDoors mass : EnumDopDoors.values()){
            if (mass.ordinal() + 3 == ind){
                return mass;
            }
        }
        return null;
    }
}
