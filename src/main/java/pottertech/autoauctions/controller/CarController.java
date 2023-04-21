package pottertech.autoauctions.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(CarController.class);

    @GetMapping("/manufacturer")
    public String showManufacturerForm(Model model) {
        log.info("---> Add manufacturer page...");
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
        log.info("---> Add transmission page...");
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
        log.info("---> Add drivetrain page...");
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
        log.info("---> Add engine page...");
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
        log.info("---> Add car model page...");
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
        log.info("---> Add car page...");
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
