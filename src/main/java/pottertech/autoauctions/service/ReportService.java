package pottertech.autoauctions.service;

import java.util.List;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.Report;

public interface ReportService {
    List<Report> getAllReports();
    List<ShortReportDto> getAllReportsShort();
    List<ShortReportDto> getFilteredReports(FilterDto parameters);
    Report addReport(ReportDto reportDto);
    Report getReport(Long id);
    void approveReport(ReportApprovalDto reportApprovalDto);
    void buyCar(ReportApprovalDto reportApprovalDto);
}
