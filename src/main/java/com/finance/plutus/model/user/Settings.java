package com.finance.plutus.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.finance.plutus.model.exchange.Currency;
import com.finance.plutus.model.partner.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false, name = "version")
    private Long version;
    @NotNull
    @Column(nullable = false, name = "created_on")
    private Long createdOn;
    @NotNull
    @Column(nullable = false, name = "updated_on")
    private Long updatedOn;
    @ManyToOne
    private Partner myPartner;
    @NotNull
    @Column(nullable = false, name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

}
