package pottertech.autoauctions.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.*;
import pottertech.autoauctions.exception.BadPayloadException;
import pottertech.autoauctions.exception.ReportException;
import pottertech.autoauctions.exception.UserException;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.mapper.ReportMapper;
import pottertech.autoauctions.repository.CarDetailsRepository;
import pottertech.autoauctions.repository.CarRepository;
import pottertech.autoauctions.repository.ReportRepository;
import pottertech.autoauctions.repository.TokenRepository;

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
    TokenRepository tokenRepository;

    @Mock
    ReportMapper reportMapper;

    @Mock
    CarMapper carMapper;

    @InjectMocks
    ReportServiceImpl reportService;

    @Test
    void getAllReportsSuccess() {
        List<Report> reports = new ArrayList<>();
        reports.add(new Report());

        when(this.reportRepository.findAll()).thenReturn(reports);

        List<Report> reports1 = this.reportService.getAllReports();

        assertNotNull(reports1);
    }

    @Test
    void getAllReportsFail() {
        when(this.reportRepository.findAll()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(UserException.class, () -> this.reportService.getAllReports());

        assertEquals(Constants.NO_REPORT_FOUND, exception.getMessage());
    }

    @Test
    void getReportSuccess() {
        Report report = new Report();

        when(this.reportRepository.findOneById(any())).thenReturn(report);

        Report report1 = this.reportService.getReport(any());

        assertNotNull(report1);
    }

    @Test
    void getReportFail() {
        when(this.reportRepository.findOneById(any())).thenReturn(null);

        Exception exception = assertThrows(UserException.class, () -> this.reportService.getReport(any()));
        assertEquals(Constants.NO_REPORT_FOUND, exception.getMessage());
    }

    @Test
    void getAllReportsShortSuccess() {
        List<Report> reports = new ArrayList<>();
        reports.add(new Report());

        when(this.reportRepository.findAll()).thenReturn(reports);
        when(this.reportMapper.reportToShortReport(any())).thenReturn(new ShortReportDto());

        List<ShortReportDto> reports1 = this.reportService.getAllReportsShort(PageRequest.of(0, 5));

        assertNotNull(reports1);
    }

    @Test
    void getAllReportsShortFail() {
        List<Report> reports = new ArrayList<>();
        when(this.reportRepository.findAll()).thenReturn(reports);

        Exception exception = assertThrows(UserException.class, () -> this.reportService.getAllReportsShort(PageRequest.of(0, 5)));

        assertEquals(Constants.NO_REPORT_FOUND, exception.getMessage());
    }

    @Test
    void getFilteredReportsSuccess() {
        List<Report> reports = new ArrayList<>();
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

        reports.add(fullReport);

        when(this.reportRepository.findAll()).thenReturn(reports);
        when(this.reportMapper.reportToShortReport(any())).thenReturn(new ShortReportDto());

        List<ShortReportDto> reports1 = this.reportService.getFilteredReports(
                FilterDto.builder()
                        .gearbox(TransmissionType.AUTO)
                        .maxKilometrage(22222L)
                        .maxPower(2222L)
                        .maxPrice(2222L)
                        .minYear(11L)
                        .minYear(1999L)
                        .tractionType(TractionType.AWD)
                        .build());

        assertNotNull(reports1);
    }

    @Test
    void getFilteredReportsFail() {
        List<Report> reports = new ArrayList<>();
        when(this.reportRepository.findAll()).thenReturn(reports);

        Exception exception = assertThrows(UserException.class, () -> this.reportService.getFilteredReports(new FilterDto()));

        assertEquals(Constants.NO_REPORT_FOUND, exception.getMessage());
    }

    @Test
    void addReportSuccess() {
        ReportDto reportDto = ReportDto.builder().userToken("bla-bla").build();

        when(this.tokenRepository.findOneByName(any())).thenReturn(Token.builder().user(User.builder().isAdmin(true).build()).build());
        when(this.carMapper.carDtoToCar(any())).thenReturn(Car.builder().carModel(CarModel.builder().build()).build());
        when(this.carRepository.findOneByCarModelAndEngineAndDrivetrain(any(), any(), any())).thenReturn(new Car());
        when(this.carMapper.reportDtoToCarDetails(any())).thenReturn( CarDetails.builder().car(Car.builder().build()).build());
        when(this.carDetailsRepository.findOneByCarAndKilometrageAndYearAndPrice(any(), any(), any(), any())).thenReturn(new CarDetails());
        when(this.reportRepository.save(any())).thenReturn(new Report());

        Report report = this.reportService.addReport(reportDto, "admin");

        assertNotNull(report);
    }

    @Test
    void addReportSuccess2() {
        ReportDto reportDto = ReportDto.builder().userToken("bla-bla").build();

        when(this.tokenRepository.findOneByName(any())).thenReturn(Token.builder().user(User.builder().isAdmin(true).build()).build());
        when(this.carMapper.carDtoToCar(any())).thenReturn(Car.builder().carModel(CarModel.builder().build()).build());
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

        when(this.tokenRepository.findOneByName(any())).thenReturn(Token.builder().user(User.builder().isAdmin(true).build()).build());
        when(this.reportRepository.findOneById(any())).thenReturn(Report.builder().isApproved(false).build());
        when(this.reportRepository.save(any())).thenReturn(Report.builder().isApproved(true).build());

        this.reportService.approveReport(reportApprovalDto);
    }

    @Test
    void approveReportFail() {
        ReportApprovalDto reportApprovalDto = ReportApprovalDto.builder().build();

        when(this.tokenRepository.findOneByName(any())).thenReturn(Token.builder().user(User.builder().isAdmin(false).build()).build());

        Exception exception = assertThrows(BadPayloadException.class, () ->  this.reportService.approveReport(reportApprovalDto));

        assertEquals(Constants.NOT_ADMIN_USER_TOKEN, exception.getMessage());
    }

    @Test
    void buyCarSuccess() {
        ReportApprovalDto reportApprovalDto = ReportApprovalDto.builder().build();

        when(this.tokenRepository.findOneByName(any())).thenReturn(Token.builder().user(User.builder().isAdmin(true).build()).build());
        when(this.reportRepository.findOneById(any())).thenReturn(Report.builder().isBought(false).build());
        when(this.reportRepository.save(any())).thenReturn(Report.builder().isBought(true).build());

        this.reportService.buyCar(reportApprovalDto);
    }

    @Test
    void buyCarFail() {
        ReportApprovalDto reportApprovalDto = ReportApprovalDto.builder().build();

        when(this.tokenRepository.findOneByName(any())).thenReturn(Token.builder().user(User.builder().isAdmin(true).build()).build());
        when(this.reportRepository.findOneById(any())).thenReturn(Report.builder().isBought(true).build());

        Exception exception = assertThrows(ReportException.class, () ->  this.reportService.buyCar(reportApprovalDto));

        assertEquals(Constants.CAR_AlREADY_BOUGHT, exception.getMessage());
    }

    @Test
    void buyCarFail2() {
        ReportApprovalDto reportApprovalDto = ReportApprovalDto.builder().build();

        when(this.tokenRepository.findOneByName(any())).thenReturn(null);

        Exception exception = assertThrows(BadPayloadException.class, () ->  this.reportService.buyCar(reportApprovalDto));

        assertEquals(Constants.WRONG_USER_TOKEN, exception.getMessage());
    }
}