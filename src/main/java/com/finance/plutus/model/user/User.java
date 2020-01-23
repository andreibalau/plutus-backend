package com.finance.plutus.model.user;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

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
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable = false, name = "created_on")
	private Long createdOn;
	@NotNull
	@Column(nullable = false, name = "updated_on")
	private Long updatedOn;
	@Email
	@NotBlank
	@Column(nullable = false, unique = true, name = "email")
	private String email;
	@NotBlank
	@Column(nullable = false, name = "phone")
	private String phone;
	@NotBlank
	@Column(nullable = false, name = "password")
	private String password;
	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private Set<Role> roles = Collections.singleton(Role.ADMIN);
	@NotBlank
	@Column(nullable = false, name = "firstname")
	private String firstName;
	@NotBlank
	@Column(nullable = false, name = "lastname")
	private String lastName;
	@NotBlank
	@Column(nullable =  false, name = "address")
	private String address;
	@NotBlank
	@Column(nullable =  false, name = "city")
	private String city;
	@NotBlank
	@Column(nullable =  false, name = "state")
	private String state;
	@NotBlank
	@Column(nullable =  false, name = "zip")
	private String zip;

}
