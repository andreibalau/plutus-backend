package com.finance.plutus.app.configuration;

import java.util.UUID;

/** Plutus Created by Catalin on 7/1/2020 */
public final class Api {

  private Api() {}

  public static final String APPLICATION_VND_PLUTUS_FINANCE_JSON =
      "application/vnd.plutus.finance+json";

  public static final UUID USER_ID = UUID.fromString("04067b90-996e-4615-8c7e-b0c664ce69ac");
}
