package pottertech.autoauctions.service;

import java.util.List;

import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.entity.Report;

public interface ReportService {
    List<Report> getAllReports();
    Report addReport(ReportDto reportDto);
}
