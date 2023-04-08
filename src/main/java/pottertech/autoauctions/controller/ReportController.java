package pottertech.autoauctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.service.implementation.ReportServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportServiceImpl reportService;

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(this.reportService.getAllReports());
    }

    @GetMapping("/short")
    public ResponseEntity<List<ShortReportDto>> getAllReportsShort() {
        return ResponseEntity.ok(this.reportService.getAllReportsShort());
    }

    @GetMapping("{id}")
    public ResponseEntity<Report> getReport(@PathVariable String id) {
        return ResponseEntity.ok(this.reportService.getReport(Long.parseLong(id)));
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<ShortReportDto>> getFilteredReports(@ModelAttribute FilterDto parameters) {
        return ResponseEntity.ok(this.reportService.getFilteredReports(parameters));
    }


    @PostMapping
    public ResponseEntity<Report> addReport(@RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(this.reportService.addReport(reportDto));
    }

    @PatchMapping
    public ResponseEntity<String> approveReport(@RequestBody ReportApprovalDto reportApprovalDto) {
        this.reportService.approveReport(reportApprovalDto);

        return ResponseEntity.ok(Constants.REPORT_APPROVED);
    }

    @PatchMapping("/buy")
    public ResponseEntity<String> buyCar(@RequestBody ReportApprovalDto reportApprovalDto) {
        this.reportService.buyCar(reportApprovalDto);

        return ResponseEntity.ok(Constants.CAR_BOUGHT);
    }
}
