package com.finance.plutus.model.partner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotNull
	@Column(nullable = false, name = "updated_on")
	private Long updatedOn;
	@NotBlank
	@Column(nullable = false, name = "name")
	private String name;
	@Column(unique = true, name = "email")
	private String email;
	@Column(name = "phone")
	private String phone;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "type")
	private Type type;
	@Column(name = "iban")
	private String iban;
	@Column(name = "cif")
	private String cif;
	@Column(name = "reg_com")
	private String regCom;
	@Column(name = "address")
	private String address;
	@NotBlank
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@NotBlank
	@Column(name = "country")
	private String country;

}
