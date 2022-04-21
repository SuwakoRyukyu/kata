package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.Service.CarService;
import web.Service.CarServiceImpl;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @GetMapping(value = "/cars")
    public String showCars(ModelMap modelMap, @RequestParam(required = false) Integer count) {
        if (count == null) {
            count = 5;
        }
        CarService carService = new CarServiceImpl();
        modelMap.addAttribute("cars", carService.getCars(count));
        return "cars";
    }
}