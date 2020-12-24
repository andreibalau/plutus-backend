package com.finance.plutus.dashboard.controller.payload;

import com.finance.plutus.dashboard.model.BestPartnerDto;
import com.finance.plutus.dashboard.model.ExpenseDto;
import com.finance.plutus.dashboard.model.IncomeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Plutus Created by Catalin on 12/23/2020 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
  private IncomeDto incomes;
  private ExpenseDto expenses;
  private BestPartnerDto bestPartner;
}
