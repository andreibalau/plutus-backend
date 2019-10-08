package com.finance.plutus.model.user.dto;

import com.finance.plutus.model.user.Settings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@Getter
@Setter
@NoArgsConstructor
public class SettingsDto {
    private Long partnerId;
    private Boolean useAccounts;

    public Settings toSettings() {
        return Settings
                .builder()
                .useAccounts(useAccounts)
                .build();
    }
}
