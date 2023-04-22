package pottertech.autoauctions.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pottertech.autoauctions.dto.*;
import pottertech.autoauctions.entity.*;
import pottertech.autoauctions.service.implementation.CarServiceImpl;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CarController.class)
@ActiveProfiles("h2")
class CarControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CarServiceImpl carService;

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void showManufacturerForm() throws Exception {
        mockMvc.perform(get("/car/manufacturer"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-manufacturer"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void addManufacturer() throws Exception {
        SimpleTypeDto simpleTypeDto = new SimpleTypeDto();
        simpleTypeDto.setName("Test Manufacturer");

        mockMvc.perform(post("/car/manufacturer")
                        .flashAttr("simpleTypeDto", simpleTypeDto)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car/manufacturer?success"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void showTransmissionForm() throws Exception {
        mockMvc.perform(get("/car/transmission"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-transmission"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void addTransmission() throws Exception {
        TransmissionDto simpleTypeDto = new TransmissionDto();
        simpleTypeDto.setName("Test transmission");
        simpleTypeDto.setType(TransmissionType.AUTO);
        simpleTypeDto.setSpeeds(6L);

        mockMvc.perform(post("/car/transmission")
                        .flashAttr("transmissionDto", simpleTypeDto)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car/transmission?success"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void showDrivetrainForm() throws Exception {
        mockMvc.perform(get("/car/drivetrain"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-drivetrain"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void addDrivetrain() throws Exception {
        DrivetrainDto drivetrainDto = DrivetrainDto.builder().tractionType(TractionType.AWD).transmissionName("MAN6").build();

        mockMvc.perform(post("/car/drivetrain")
                        .flashAttr("drivetrainDto", drivetrainDto)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car/drivetrain?success"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void showEngineForm() throws Exception {
        mockMvc.perform(get("/car/engine"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-engine"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void addEngine() throws Exception {
        EngineDto engineDto = EngineDto.builder()
                .capacity(1.9)
                .fuelType(FuelType.DIESEL)
                .name("ARL")
                .power(100L)
                .torque(200L)
                .build();

        mockMvc.perform(post("/car/engine")
                        .flashAttr("engineDto", engineDto)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car/engine?success"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void showCarModelForm() throws Exception {
        mockMvc.perform(get("/car/model"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-car-model"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void addCarModel() throws Exception {
        CarModelDto carModelDto = CarModelDto.builder()
                .chassisType(ChassisType.COUPE)
                .endYear(2022L)
                .startYear(2021L)
                .manufacturer("Volkswagen")
                .name("Golf GTI")
                .generation("IV")
                .build();

        mockMvc.perform(post("/car/model")
                        .flashAttr("carModelDto", carModelDto)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car/model?success"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void showCarForm() throws Exception {
        mockMvc.perform(get("/car"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-car"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void addCar() throws Exception {
        mockMvc.perform(post("/car")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/car?success"));
    }
}