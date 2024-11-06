package com.example.springboot.UserManagment.AppUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<AppUserDTO> get(AppUserDTO appUserDTO) {
        return appUserService.get(appUserDTO);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public AppUserDTO create(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.create(appUserDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public AppUserDTO update(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.update(appUserDTO);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.delete(appUserDTO);
    }
}
