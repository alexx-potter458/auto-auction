package pottertech.autoauctions.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.repository.ReportRepository;
import pottertech.autoauctions.service.ReportService;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<Report> getAllReports() {
        return this.reportRepository.findAll();
    }
}
