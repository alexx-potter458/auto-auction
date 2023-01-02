package pottertech.autoauctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.ReportDto;
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

    @PostMapping
    public ResponseEntity<Report> addReport(@RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(this.reportService.addReport(reportDto));
    }
}
