package pottertech.autoauctions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pottertech.autoauctions.Constants;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.entity.Report;
import pottertech.autoauctions.service.implementation.ReportServiceImpl;
import java.util.List;

@Controller
//@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportServiceImpl reportService;

//    @GetMapping
//    public ResponseEntity<List<Report>> getAllReports() {
//        return ResponseEntity.ok(this.reportService.getAllReports());
//    }

    @GetMapping("/")
    public String getAllReportsShort(Model model) {
        List<ShortReportDto> cars =  this.reportService.getAllReportsShort();
        model.addAttribute(cars);

        return "home";
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
