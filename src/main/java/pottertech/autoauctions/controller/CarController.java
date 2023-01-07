package pottertech.autoauctions.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "🗄️Car controller", description = "All the car related endpoints can be found here.")
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @PostMapping()
    @Operation(summary = "🗃️ Add car", description = "Here you can add a car to the database giving the car model, engine code, traction type and transmission name.")
    public ResponseEntity<Car> addCar(@Valid  @RequestBody CarDto carDto) {
        return ResponseEntity.ok(this.carService.addCar(carDto));
    }

    @PostMapping("/manufacturer")
    @Operation(summary = "🗃️Add manufacturer", description = "Here you can add a new manufacturer.")
    public ResponseEntity<CarManufacturer> addManufacturer(@Valid @RequestBody SimpleTypeDto simpleTypeDto) {
        return ResponseEntity.ok(this.carService.addManufacturer(simpleTypeDto));
    }

    @PostMapping("/transmission")
    @Operation(summary = "🗃️ Add transmission", description = "Here you can add a new transmission giving the number of speeds, transmission type and a representative name.")
    public ResponseEntity<Transmission> addTransmission(@Valid @RequestBody TransmissionDto simpleTypeDto) {
        return ResponseEntity.ok(this.carService.addTransmission(simpleTypeDto));
    }

    @PostMapping("/model")
    @Operation(summary = "🗃️ Add car model", description = "Here you can add a new car model providing a manufacturer, chassis type, model name, generation and the start and end years of productions.")
    public ResponseEntity<CarModel> addCarModel(@Valid @RequestBody CarModelDto carModelDto) {
        return ResponseEntity.ok(this.carService.addCarModel(carModelDto));
    }

    @PostMapping("/drivetrain")
    @Operation(summary = "🗃️ Add drivetrain", description = "Here you can add a new drivetrain providing a transmission name and a traction type.")
    public ResponseEntity<Drivetrain> addDrivetrain(@Valid @RequestBody DrivetrainDto drivetrainDto) {
        return ResponseEntity.ok(this.carService.addDrivetrain(drivetrainDto));
    }

    @PostMapping("/engine")
    @Operation(summary = "🗃️ Add engine", description = "Here you can add the main component of a car - the engine by providing a engine code, power, torque, capacity and fuel type.")
    public ResponseEntity<Engine> addEngine(@Valid @RequestBody EngineDto engineDto) {
        return ResponseEntity.ok(this.carService.addEngine(engineDto));
    }
}
