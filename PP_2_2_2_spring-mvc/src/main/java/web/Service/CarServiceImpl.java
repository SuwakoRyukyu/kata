package web.Service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCars(int index) {
        List<Car> tempCarList = Car.createCars();
        List<Car> carList = new ArrayList<>();
        for (int i = index - 1; i >= 0; i--) {
            carList.add(tempCarList.get(i));
        }
        return carList;
    }
}
