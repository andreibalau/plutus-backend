package com.finance.plutus.model.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operation {

    @Id
    @GeneratedValue
    private Long id;
    @Version
    @Column(nullable = false, name = "version")
    private Long version;
    @NotBlank
    @Column(nullable = false, name = "name")
    private String name;
    @NotBlank
    @Column(nullable = false, name = "user")
    private String user;
    @NotNull
    @Column(nullable = false, name = "datetime")
    private Long datetime;

}
