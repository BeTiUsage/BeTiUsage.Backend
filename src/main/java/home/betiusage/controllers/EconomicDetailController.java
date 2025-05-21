package home.betiusage.controllers;

import home.betiusage.services.EconomicDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/EconomicDetails")
public class EconomicDetailController {
    EconomicDetailService economicDetailService;

    public EconomicDetailController(EconomicDetailService economicDetailService) {
        this.economicDetailService = economicDetailService;
    }
}
