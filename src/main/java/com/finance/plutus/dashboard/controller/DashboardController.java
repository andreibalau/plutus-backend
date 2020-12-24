package com.finance.plutus.dashboard.controller;

import com.finance.plutus.dashboard.controller.payload.DashboardResponse;
import com.finance.plutus.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Plutus Created by Catalin on 12/23/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/dashboard")
public class DashboardController {

  private final DashboardService dashboardService;

  @GetMapping
  public DashboardResponse fetch() {
    return dashboardService.fetchStatistics();
  }
}
