package com.example.springboot.UserManagment.AppUser;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUserDTO map(AppUser appUser);

    @InheritConfiguration
    List<AppUserDTO> map(List<AppUser> appUsers);

    @InheritInverseConfiguration
    AppUser map(AppUserDTO appUserDTO);
}
