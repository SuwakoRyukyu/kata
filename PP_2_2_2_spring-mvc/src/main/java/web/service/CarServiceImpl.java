package web.service;

import web.model.Car;
import web.util.Util;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCars(int index) {
        List<Car> tempCarList = Util.createCars();
        List<Car> carList = new ArrayList<>();
        for (int i = index - 1; i >= 0; i--) {
            carList.add(tempCarList.get(i));
        }
        return carList;
    }
}
