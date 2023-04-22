package pottertech.autoauctions.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.*;
import pottertech.autoauctions.exception.UserException;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.mapper.ReportMapper;
import pottertech.autoauctions.repository.CarDetailsRepository;
import pottertech.autoauctions.repository.CarRepository;
import pottertech.autoauctions.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @Mock
    ReportRepository reportRepository;

    @Mock
    CarDetailsRepository carDetailsRepository;

    @Mock
    CarRepository carRepository;


    @Mock
    ReportMapper reportMapper;

    @Mock
    CarMapper carMapper;

    @InjectMocks
    ReportServiceImpl reportService;

    @Test
    void getAllReportsShortSuccess() {
        List<Report> reportArrayList = new ArrayList<>();
        reportArrayList.add(new Report());

        Page<Report> reports = new PageImpl<>(reportArrayList);

        when(this.reportRepository.findAll(PageRequest.of(0, 5))).thenReturn(reports);
        when(this.reportMapper.reportToShortReport(any())).thenReturn(new ShortReportDto());

        Page<ShortReportDto> reports1 = this.reportService.getAllReportsShort(PageRequest.of(0, 5));

        assertNotNull(reports1);
    }

    @Test
    void getAllReportsShortFail() {
        List<Report> reportArrayList = new ArrayList<>();
        Page<Report> reports = new PageImpl<>(reportArrayList);

        when(this.reportRepository.findAll(PageRequest.of(0, 5))).thenReturn(reports);

        Exception exception = assertThrows(UserException.class, () -> this.reportService.getAllReportsShort(PageRequest.of(0, 5)));

        assertEquals(Constants.NO_REPORT_FOUND, exception.getMessage());
    }

    @Test
    void getFilteredReportsSuccess() {
        List<Report> reportArrayList = new ArrayList<>();
        Report fullReport = Report.builder()
                .isBought(false)
                .isApproved(false)
                .carDetails(
                        CarDetails.builder()
                                .price(Long.parseLong("1000"))
                                .year(Long.parseLong("2000"))
                                .id(Long.parseLong("1"))
                                .car(
                                        Car.builder()
                                                .engine(
                                                        Engine.builder()
                                                                .torque(300L)
                                                                .fuelType(FuelType.DIESEL)
                                                                .capacity(1.9)
                                                                .id(1L)
                                                                .name("ARL")
                                                                .power(300L)
                                                                .build()
                                                )
                                                .drivetrain(
                                                        Drivetrain.builder()
                                                                .tractionType(TractionType.AWD)
                                                                .transmission(
                                                                        Transmission.builder()
                                                                                .speeds(7L)
                                                                                .name("AUT7")
                                                                                .type(TransmissionType.AUTO)
                                                                                .build()).build()).build()).build()).build();

        reportArrayList.add(fullReport);
        Page<Report> reports = new PageImpl<>(reportArrayList);


        when(this.reportRepository.findAll(PageRequest.of(0, 5))).thenReturn(reports);
        when(this.reportMapper.reportToShortReport(any())).thenReturn(new ShortReportDto());

        Page<ShortReportDto> reports1 = this.reportService.getFilteredReports(
                FilterDto.builder()
                        .gearbox(TransmissionType.AUTO)
                        .maxKilometrage(22222L)
                        .maxPower(2222L)
                        .maxPrice(2222L)
                        .minYear(11L)
                        .minYear(1999L)
                        .tractionType(TractionType.AWD)
                        .build(), PageRequest.of( 0, 5));

        assertNotNull(reports1);
    }

    @Test
    void getFilteredReportsFail() {
        List<Report> reportArrayList = new ArrayList<>();
        Page<Report> reports = new PageImpl<>(reportArrayList);
        when(this.reportRepository.findAll(PageRequest.of( 0, 3))).thenReturn(reports);

        Exception exception = assertThrows(UserException.class, () -> this.reportService.getFilteredReports(new FilterDto(), PageRequest.of( 0, 3)));

        assertEquals(Constants.NO_REPORT_FOUND, exception.getMessage());
    }

    @Test
    void addReportSuccess() {
        ReportDto reportDto = ReportDto.builder().build();

        when(this.carMapper.reportDtoDtoToCar(any())).thenReturn(Car.builder().carModel(CarModel.builder().build()).build());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(new Car());
        when(this.carMapper.reportDtoToCarDetails(any())).thenReturn( CarDetails.builder().car(Car.builder().build()).build());
        when(this.carDetailsRepository.findOneByCarAndKilometrageAndYearAndPrice(any(), any(), any(), any())).thenReturn(new CarDetails());
        when(this.reportRepository.save(any())).thenReturn(new Report());

        Report report = this.reportService.addReport(reportDto, "admin");

        assertNotNull(report);
    }

    @Test
    void addReportSuccess2() {
        ReportDto reportDto = ReportDto.builder().build();

        when(this.carMapper.reportDtoDtoToCar(any())).thenReturn(Car.builder().carModel(CarModel.builder().build()).build());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(null);
        when(this.carMapper.reportDtoToCarDetails(any())).thenReturn( CarDetails.builder().car(Car.builder().build()).build());
        when(this.carDetailsRepository.findOneByCarAndKilometrageAndYearAndPrice(any(), any(), any(), any())).thenReturn(null);
        when(this.reportRepository.save(any())).thenReturn(new Report());

        Report report = this.reportService.addReport(reportDto, "admin");

        assertNotNull(report);
    }

    @Test
    void approveReportSuccess() {
        ReportApprovalDto reportApprovalDto = ReportApprovalDto.builder().build();

        when(this.reportRepository.findOneById(any())).thenReturn(Report.builder().isApproved(false).build());
        when(this.reportRepository.save(any())).thenReturn(Report.builder().isApproved(true).build());

        this.reportService.approveReport(reportApprovalDto);
    }

    @Test
    void buyCarSuccess() {
        ReportApprovalDto reportApprovalDto = ReportApprovalDto.builder().build();

        when(this.reportRepository.findOneById(any())).thenReturn(Report.builder().isBought(false).build());
        when(this.reportRepository.save(any())).thenReturn(Report.builder().isBought(true).build());

        this.reportService.buyCar(reportApprovalDto);
    }
}