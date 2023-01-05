package pottertech.autoauctions.service.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pottertech.autoauctions.mapper.CarMapper;
import pottertech.autoauctions.mapper.ReportMapper;
import pottertech.autoauctions.repository.CarDetailsRepository;
import pottertech.autoauctions.repository.CarRepository;
import pottertech.autoauctions.repository.ReportRepository;
import pottertech.autoauctions.repository.TokenRepository;

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
    void getAllReports() {
    }

    @Test
    void getReport() {
    }

    @Test
    void getAllReportsShort() {
    }

    @Test
    void getFilteredReports() {
    }

    @Test
    void addReport() {
    }

    @Test
    void approveReport() {
    }

    @Test
    void buyCar() {
    }
}