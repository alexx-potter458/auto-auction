package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.mapper.ReportMapper;
import pottertech.autoauctions.repository.CarDetailsRepository;
import pottertech.autoauctions.repository.ReportRepository;
import pottertech.autoauctions.service.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    CarDetailsRepository carDetailsRepository;

    @Autowired
    ReportMapper reportMapper;

    @Override
    public List<Report> getAllReports() {
        return this.reportRepository.findAll();
    }

    @Override
    public Report addReport(ReportDto reportDto) {

        return null;
    }
}
