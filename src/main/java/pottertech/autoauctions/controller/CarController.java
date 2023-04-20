package pottertech.autoauctions.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.service.implementation.CarServiceImpl;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/manufacturer")
    public String showManufacturerForm(Model model) {
        return "add-manufacturer";
    }

    @PostMapping("/manufacturer")
    public String addManufacturer(@Valid @ModelAttribute SimpleTypeDto simpleTypeDto) {
        try {
            this.carService.addManufacturer(simpleTypeDto);

        } catch(Exception exception) {
            return "redirect:/car/manufacturer?error";
        }

        return "redirect:/car/manufacturer?success";
    }

    @GetMapping("/transmission")
    public String showTransmissionForm(Model model) {
        return "add-transmission";
    }

    @PostMapping("/transmission")
    public String addTransmission(@Valid @ModelAttribute TransmissionDto simpleTypeDto) {
        try {
            this.carService.addTransmission(simpleTypeDto);
        } catch(Exception exception) {
            return "redirect:/car/transmission?error";
        }

        return "redirect:/car/transmission?success";
    }

    @GetMapping("/drivetrain")
    public String showDrivetrainForm(Model model) {
        return "add-drivetrain";
    }

    @PostMapping("/drivetrain")
    public String addDrivetrain(@Valid @ModelAttribute DrivetrainDto drivetrainDto) {
        try {
            this.carService.addDrivetrain(drivetrainDto);
        } catch(Exception exception) {
            return "redirect:/car/drivetrain?error";
        }

        return "redirect:/car/drivetrain?success";
    }

    @GetMapping("/engine")
    public String showEngineForm(Model model) {
        return "add-engine";
    }

    @PostMapping("/engine")
    public String addEngine(@Valid @ModelAttribute EngineDto engineDto) {
        try {
            this.carService.addEngine(engineDto);
        } catch(Exception exception) {
            return "redirect:/car/engine?error";
        }

        return "redirect:/car/engine?success";
    }

    @GetMapping("/model")
    public String showCarModelForm(Model model) {
        return "add-car-model";
    }

    @PostMapping("/model")
    public String addCarModel(@Valid @ModelAttribute CarModelDto carModelDto) {
        try {
            this.carService.addCarModel(carModelDto);
        } catch(Exception exception) {
            return "redirect:/car/model?error";
        }

        return "redirect:/car/model?success";
    }

    @GetMapping
    public String showCarForm(Model model) {
        return "add-car";
    }

    @PostMapping
    public String addCar(@Valid @ModelAttribute CarDto carDto) {
        try {
            this.carService.addCar(carDto);
        } catch(Exception exception) {
            return "redirect:/car?error";
        }

        return "redirect:/car?success";
    }
}
