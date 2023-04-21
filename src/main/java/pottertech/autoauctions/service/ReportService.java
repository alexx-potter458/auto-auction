package pottertech.autoauctions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.Report;

public interface ReportService {
    Page<ShortReportDto> getAllReportsShort(Pageable pageable);
    Page<ShortReportDto> getFilteredReports(FilterDto parameters, Pageable pageable);
    Report addReport(ReportDto reportDto, String user);
    void approveReport(ReportApprovalDto reportApprovalDto);
    void buyCar(ReportApprovalDto reportApprovalDto);
}
