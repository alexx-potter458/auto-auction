package pottertech.autoauctions.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.entity.*;
import pottertech.autoauctions.service.implementation.CarServiceImpl;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @PostMapping()
    public ResponseEntity<Car> addCar(@Valid  @RequestBody CarDto carDto) {
        return ResponseEntity.ok(this.carService.addCar(carDto));
    }

    @PostMapping("/manufacturer")
    public ResponseEntity<CarManufacturer> addManufacturer(@Valid @RequestBody SimpleTypeDto simpleTypeDto) {
        return ResponseEntity.ok(this.carService.addManufacturer(simpleTypeDto));
    }

    @PostMapping("/transmission")
    public ResponseEntity<Transmission> addTransmission(@Valid @RequestBody TransmissionDto simpleTypeDto) {
        return ResponseEntity.ok(this.carService.addTransmission(simpleTypeDto));
    }

    @PostMapping("/model")
    public ResponseEntity<CarModel> addCarModel(@Valid @RequestBody CarModelDto carModelDto) {
        return ResponseEntity.ok(this.carService.addCarModel(carModelDto));
    }

    @PostMapping("/drivetrain")
    public ResponseEntity<Drivetrain> addDrivetrain(@Valid @RequestBody DrivetrainDto drivetrainDto) {
        return ResponseEntity.ok(this.carService.addDrivetrain(drivetrainDto));
    }

    @PostMapping("/engine")
    public ResponseEntity<Engine> addEngine(@Valid @RequestBody EngineDto engineDto) {
        return ResponseEntity.ok(this.carService.addEngine(engineDto));
    }
}
