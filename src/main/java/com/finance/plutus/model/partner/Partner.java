package com.finance.plutus.model.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Getter
@Setter
@Entity
@Table(name = "partners")
public class Partner {

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
	@NotBlank
	@Column(nullable = false, name = "firstname")
	private String firstName;
	@NotBlank
	@Column(nullable = false, name = "lastname")
	private String lastName;
	@NotBlank
	@Column(nullable = false, unique = true, name = "email")
	private String email;
	@NotBlank
	@Column(nullable = false, name = "phone")
	private String phone;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type")
	@Builder.Default
	private Type type = Type.BUSINESS;
	@Column(name = "bank")
	private String bank;
	@Column(name = "bank_account")
	private String bankAccount;
	@Column(name = "cif")
	private String cif;
	@Column(name = "reg_com")
	private String regCom;
	@Column(name = "address")
	private String address;

}
