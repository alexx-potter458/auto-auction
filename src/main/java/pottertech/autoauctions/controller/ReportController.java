package pottertech.autoauctions.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pottertech.autoauctions.dto.FilterDto;
import pottertech.autoauctions.dto.ReportApprovalDto;
import pottertech.autoauctions.dto.ReportDto;
import pottertech.autoauctions.dto.ShortReportDto;
import pottertech.autoauctions.service.implementation.ReportServiceImpl;
import java.util.Optional;

@Controller
public class ReportController {
    @Autowired
    ReportServiceImpl reportService;

    Logger log = LoggerFactory.getLogger(ReportController.class);

    @GetMapping("/")
    public String getAllReportsShort(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        log.info("---> Auctions page...");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

        try{
            Page<ShortReportDto> cars = this.reportService.getAllReportsShort(PageRequest.of(currentPage - 1, pageSize));
            model.addAttribute("cars", cars);
        } catch (Exception ignored) {}

        return "home";
    }

    @GetMapping("/report/add")
    public String addReport() {
        log.info("---> Add auction page...");
        return "add-report";
    }

    @PostMapping("/report/add")
    public String addReport(HttpServletRequest request, @ModelAttribute ReportDto reportDto) {
        String username = request.getUserPrincipal().getName();
        this.reportService.addReport(reportDto, username);

        return "redirect:/report/add?success";
    }

    @GetMapping("/filter")
    public String getFilteredReports(Model model, @ModelAttribute FilterDto parameters, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

        try{
            Page<ShortReportDto> cars = this.reportService.getFilteredReports(parameters, PageRequest.of(currentPage - 1, pageSize));
            model.addAttribute("cars", cars);
        } catch (Exception ignored) {}
        return "home";
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
