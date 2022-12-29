package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.SimpleTypeDto;
import pottertech.autoauctions.entity.CarManufacturer;
import pottertech.autoauctions.exception.CarException;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.repository.CarManufacturerRepository;
import pottertech.autoauctions.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarManufacturerRepository carManufacturerRepository;

    @Autowired
    CarMapper carMapper;

    @Override
    public CarManufacturer addManufacturer(SimpleTypeDto simpleTypeDto) {
        if(this.carManufacturerRepository.findOneByName(simpleTypeDto.getName()) != null)
            throw new CarException(Constants.CAR_MANUFACTURER_ALREADY_EXISTS);

        return this.carManufacturerRepository.save(this.carMapper.typeToCarManufacturer(simpleTypeDto));
    }
}
