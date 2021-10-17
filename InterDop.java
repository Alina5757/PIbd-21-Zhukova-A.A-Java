import java.awt.*;

public interface InterDop {
    //      свойство для установки кол-ва дверей
    //с условием (2 < kolvo < 6)
    void SetDoors(int value);

    //      отрисовка дверей
    //графика g
    //координата объекта x
    //координата объекта y
    void DrowDoors(Graphics g, float x_koor, float y_koor);
}
