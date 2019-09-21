package com.finance.plutus.model.partner;

import com.finance.plutus.model.address.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Partner {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false)
	private Long version;
	@NotBlank
	@Column(nullable = false)
	private String firstName;
	@NotBlank
	@Column(nullable = false)
	private String lastName;
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;
	@NotBlank
	@Column(nullable = false)
	private String phone;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private Type type = Type.BUSINESS;
	@Column
	private String bank;
	@Column
	private String bankAccount;
	@Column
	private String cif;
	@Column
	private String regCom;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "partner_address",
			joinColumns = {@JoinColumn(name = "partner_id")},
			inverseJoinColumns = {@JoinColumn(name = "address_id")})
	private Set<Address> addresses;
	@NotNull
	@Column(nullable = false)
	private Long createdOn;
	@NotNull
	@Column(nullable = false)
	private Long updatedOn;
}
