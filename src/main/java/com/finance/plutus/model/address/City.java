package com.finance.plutus.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Created by catalin on 21.09.2019
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City {
	@Id
	@GeneratedValue
	private Long id;
	@Version
	@Column(nullable = false, name = "version")
	private Long version;
	@NotBlank
	@Column(nullable = false, name = "name")
	private String name;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Country country;
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private County county;
}
