package com.so.wallet.controller;

import com.so.wallet.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/solde")
    public double getSoldeTotal() {
        return dashboardService.getSoldeTotal();  // Appel au service pour récupérer le solde total
    }

    @GetMapping("/statistiques")
    public Object getStatistiques() {
        return dashboardService.getStatistiques();  // Appel au service pour récupérer les statistiques
    }

    @GetMapping("/repartition")
    public Object getRepartition() {
        return dashboardService.getRepartition();  // Appel au service pour récupérer la répartition des dépenses
    }
}
