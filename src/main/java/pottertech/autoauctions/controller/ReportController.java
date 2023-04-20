package pottertech.autoauctions.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.service.implementation.ReportServiceImpl;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    ReportServiceImpl reportService;

    @GetMapping("/")
    public String getAllReportsShort(Model model) {
        try{
            List<ShortReportDto> cars = this.reportService.getAllReportsShort();
            model.addAttribute("cars", cars);
        } catch (Exception ignored) {}

        return "home";
    }

    @GetMapping("/report/add")
    public String addReport() {
        return "add-report";
    }

    @PostMapping("/report/add")
    public String addReport(HttpServletRequest request, @ModelAttribute ReportDto reportDto) {
        String username = request.getUserPrincipal().getName();
        this.reportService.addReport(reportDto, username);

        return "redirect:/report/add?success";
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<ShortReportDto>> getFilteredReports(@ModelAttribute FilterDto parameters) {
        return ResponseEntity.ok(this.reportService.getFilteredReports(parameters));
    }


    @RequestMapping("/approve")
    public String approveReport(HttpServletRequest request, @ModelAttribute ReportApprovalDto reportApprovalDto) {
        System.out.println("entered");
        this.reportService.approveReport(reportApprovalDto);
        return"redirect:/";
    }

    @RequestMapping("/buy")
    public String buyCar(HttpServletRequest request, @ModelAttribute ReportApprovalDto reportApprovalDto) {
        this.reportService.buyCar(reportApprovalDto);
        return"redirect:/";
    }
}
