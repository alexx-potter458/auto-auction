package pottertech.autoauctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pottertech.autoauctions.dto.SimpleTypeDto;
import pottertech.autoauctions.entity.CarManufacturer;
import pottertech.autoauctions.service.implementation.CarServiceImpl;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @PostMapping("/manufacturer")
    public ResponseEntity<CarManufacturer> addManufacturer(@RequestBody SimpleTypeDto simpleTypeDto) {
        return ResponseEntity.ok(this.carService.addManufacturer(simpleTypeDto));
    }
}
