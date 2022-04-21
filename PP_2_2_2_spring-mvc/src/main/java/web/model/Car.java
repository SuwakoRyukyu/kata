package web.model;

import java.util.ArrayList;
import java.util.List;

public class Car {

    private String model;
    private int series;
    private int quantity;

    public static List<Car> createCars() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("car1", 1, 4));
        carList.add(new Car("car2", 2, 5));
        carList.add(new Car("car3", 3, 7));
        carList.add(new Car("car4", 4, 15));
        carList.add(new Car("car5", 5, 32));
        return carList;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Car(String model, int series, int quantity) {
        this.model = model;
        this.series = series;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Car{" +
                "fieldString='" + model + '\'' +
                ", fieldInt=" + series +
                ", fieldBool=" + quantity +
                '}';
    }
}
