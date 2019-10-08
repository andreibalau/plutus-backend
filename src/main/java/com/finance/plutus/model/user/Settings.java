package com.finance.plutus.model.user;

import com.finance.plutus.model.partner.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Plutus
 * Created by catalin on 08.10.2019
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Settings {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    @Column(nullable = false)
    private Long version;
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Partner myPartner;
    @NotNull
    @Column(nullable = false)
    private Boolean useAccounts;
}
