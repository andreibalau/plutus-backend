package com.finance.plutus.user.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/** Plutus Created by catalin on 7/2/2020 */
@Getter
@Setter
public class UserDto {
  private String email;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;

  public static UserDto mapFromEntity(User user) {
    UserDto userDto = new UserDto();
    userDto.setCreatedOn(user.getCreatedOn());
    userDto.setUpdatedOn(user.getUpdatedOn());
    userDto.setEmail(user.getEmail());
    return userDto;
  }
}
