package com.finance.plutus.service.impl;

import com.finance.plutus.exception.EmailAlreadyExistsException;
import com.finance.plutus.model.dto.CreateAddressDto;
import com.finance.plutus.model.dto.CreateBusinessDto;
import com.finance.plutus.model.dto.CreateUserDto;
import com.finance.plutus.model.entity.Address;
import com.finance.plutus.model.entity.Business;
import com.finance.plutus.model.entity.County;
import com.finance.plutus.model.entity.User;
import com.finance.plutus.repository.CountyRepository;
import com.finance.plutus.repository.UserRepository;
import com.finance.plutus.service.CheckEmailService;
import com.finance.plutus.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/** Plutus Created by Catalin on 7/1/2020 */
@Service
@RequiredArgsConstructor
public class RegisterUserServiceImpl implements RegisterUserService {

  private final UserRepository userRepository;
  private final CheckEmailService checkEmailService;
  private final CountyRepository countyRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void register(CreateUserDto createUserDto, CreateBusinessDto createBusinessDto) {
    checkEmail(createUserDto.getEmail());
    Address address = createAddress(createBusinessDto.getCreateAddressDto());
    Business business = createBusiness(createBusinessDto, address);
    User user = createUser(createUserDto, business);
    userRepository.save(user);
  }

  private void checkEmail(String email) {
    boolean exists = checkEmailService.exists(email);
    if (exists) {
      throw new EmailAlreadyExistsException();
    }
  }

  private User createUser(CreateUserDto createUserDto, Business business) {
    User user = new User();
    user.setCreatedOn(LocalDateTime.now());
    user.setUpdatedOn(LocalDateTime.now());
    user.setFirstName(createUserDto.getFirstName());
    user.setLastName(createUserDto.getLastName());
    user.setEmail(createUserDto.getEmail());
    user.setPhone(createUserDto.getPhone());
    user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
    user.setBusiness(business);
    return user;
  }

  private Business createBusiness(CreateBusinessDto createBusinessDto, Address address) {
    Business business = new Business();
    business.setCreatedOn(LocalDateTime.now());
    business.setUpdatedOn(LocalDateTime.now());
    business.setCui(createBusinessDto.getCui());
    business.setType(createBusinessDto.getType());
    business.setIban(createBusinessDto.getIban());
    business.setName(createBusinessDto.getName());
    business.setRegCom(createBusinessDto.getRegCom());
    business.setAddress(address);
    return business;
  }

  private Address createAddress(CreateAddressDto createAddressDto) {
    County county = countyRepository.getOne(createAddressDto.getCountyId());
    Address address = new Address();
    address.setCity(createAddressDto.getCity());
    address.setCounty(county);
    address.setName(createAddressDto.getName());
    address.setZip(createAddressDto.getZip());
    return address;
  }
}
