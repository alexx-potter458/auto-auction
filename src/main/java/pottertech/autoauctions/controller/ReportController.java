package pottertech.autoauctions.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "🗄️Report controller", description = "All the reports related endpoints can be found here.")
public class ReportController {
    @Autowired
    ReportServiceImpl reportService;

    @GetMapping
    @Operation(summary = "🗃️ Get all the reports in full format", description = "Here you can get a list of all car reports from database in full format, having all the details.")
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(this.reportService.getAllReports());
    }

    @GetMapping("/short")
    @Operation(summary = "🗃️ Get all reports in partial format", description = "Here you can get a list of all car reports from database in partial format, i.e. a report will contain only the key attributes of a car.")
    public ResponseEntity<List<ShortReportDto>> getAllReportsShort() {
        return ResponseEntity.ok(this.reportService.getAllReportsShort());
    }

    @GetMapping("{id}")
    @Operation(summary = "🗃️ Get a single report by id", description = "In order to get only one specific report, you can get it from this endpoint by providing the id.")
    public ResponseEntity<Report> getReport(@Parameter(description = "The id of the report.") @PathVariable String id) {
        return ResponseEntity.ok(this.reportService.getReport(Long.parseLong(id)));
    }

    @GetMapping("/filtered")
    @Operation(summary = "🗃️ Get filtered reports in partial format", description = "Here you can get a list of filtered car reports from database in partial format by providing a set of parameters in the URL.")
    public ResponseEntity<List<ShortReportDto>> getFilteredReports(@Parameter(description = "It contains all the query parameters from the URL") @ModelAttribute FilterDto parameters) {
        return ResponseEntity.ok(this.reportService.getFilteredReports(parameters));
    }


    @PostMapping
    @Operation(summary = "🗃️ Add report", description = "Here you can add a report by providing your token and the key attributes of the car then the service will take care of all the data management in order to record your car report.")
    public ResponseEntity<Report> addReport(@Parameter(description = "It contains all the data necessary to record a report.") @RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(this.reportService.addReport(reportDto));
    }

    @PatchMapping
    @Operation(summary = "🗃️ Approve report", description = "Here you can approve a report by providing you token and the report you want to approve, the token must be of an admin otherwise you cannot approve it.")
    public ResponseEntity<String> approveReport(@Parameter(description = "Contains the report id and the user token.") @RequestBody ReportApprovalDto reportApprovalDto) {
        this.reportService.approveReport(reportApprovalDto);

        return ResponseEntity.ok(Constants.REPORT_APPROVED);
    }

    @PatchMapping("/buy")
    @Operation(summary = "🗃️ Buy car", description = "Here you can buy a car by providing the token and the report id. You have to provide a correct token in order to make the changes.")
    public ResponseEntity<String> buyCar(@Parameter(description = "Contains the report id and the user token.") @RequestBody ReportApprovalDto reportApprovalDto) {
        this.reportService.buyCar(reportApprovalDto);

        return ResponseEntity.ok(Constants.CAR_BOUGHT);
    }
}
