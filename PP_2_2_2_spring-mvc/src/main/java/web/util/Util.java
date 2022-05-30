package web.util;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Car> createCars() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("car1", 1, 4));
        carList.add(new Car("car2", 2, 5));
        carList.add(new Car("car3", 3, 7));
        carList.add(new Car("car4", 4, 15));
        carList.add(new Car("car5", 5, 32));
        return carList;
    }

}
