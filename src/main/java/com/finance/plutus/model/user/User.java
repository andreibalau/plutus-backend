package com.finance.plutus.model.user;

import com.finance.plutus.model.address.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
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
public class User {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false)
	private Long version;
	@Email
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;
	@NotBlank
	@Column(nullable = false)
	private String phone;
	@NotBlank
	@Column(nullable = false)
	private String password;
	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private Set<Role> roles = Collections.singleton(Role.ADMIN);
	@NotBlank
	@Column(nullable = false)
	private String firstName;
	@NotBlank
	@Column(nullable = false)
	private String lastName;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_address",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "address_id")})
	private Set<Address> addresses;
	@NotNull
	@Column(nullable = false)
	private Long createdOn;
	@NotNull
	@Column(nullable = false)
	private Long updatedOn;
}
