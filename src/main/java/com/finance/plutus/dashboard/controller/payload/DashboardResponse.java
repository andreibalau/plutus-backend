package com.finance.plutus.dashboard.controller.payload;

import com.finance.plutus.dashboard.model.Stat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Plutus Created by Catalin on 12/23/2020 */
@Getter
@Setter
@AllArgsConstructor
public class DashboardResponse {
  private List<Stat> statistics;
}
