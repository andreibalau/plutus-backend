package com.finance.plutus.service.user.impl;

import com.finance.plutus.exception.ServiceException;
import com.finance.plutus.model.address.Address;
import com.finance.plutus.model.user.Settings;
import com.finance.plutus.model.user.User;
import com.finance.plutus.model.user.dto.RegistrationDto;
import com.finance.plutus.repository.address.AddressRepository;
import com.finance.plutus.repository.partner.PartnerRepository;
import com.finance.plutus.repository.user.UserRepository;
import com.finance.plutus.service.user.EmailChecker;
import com.finance.plutus.service.user.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Plutus
 * Created by catalin on 21.09.2019
 */
@Service
@RequiredArgsConstructor
public class RegisterImpl implements Register {

	private final UserRepository userRepository;
	private final AddressRepository addressRepository;
	private final EmailChecker emailChecker;
	private final PasswordEncoder passwordEncoder;
	private final PartnerRepository partnerRepository;

	@Override
	public void register(RegistrationDto registrationDto) {
		if (emailChecker.exists(registrationDto.getEmail().toLowerCase())) {
			throw ServiceException.emailAlreadyExists();
		}
		List<Address> addresses = addressRepository.findAllById(registrationDto.getAddresses());
		User user = registrationDto.toUser();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAddresses(new HashSet<>(addresses));
		user.setCreatedOn(System.currentTimeMillis());
		user.setUpdatedOn(System.currentTimeMillis());

		Settings settings = registrationDto.getSettings().toSettings();
		settings.setMyPartner(partnerRepository.getOne(registrationDto.getSettings().getPartnerId()));
		user.setSettings(settings);

		userRepository.save(user);
	}

}
