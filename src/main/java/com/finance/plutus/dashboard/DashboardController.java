package com.finance.plutus.dashboard;

import com.finance.plutus.app.payload.PlutusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Plutus Created by Catalin on 12/23/2020 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/dashboard")
public class DashboardController {

  private final DashboardService dashboardService;

  @GetMapping
  public PlutusResponse<List<Stat>> fetch() {
    return dashboardService.fetchStatistics();
  }
}
