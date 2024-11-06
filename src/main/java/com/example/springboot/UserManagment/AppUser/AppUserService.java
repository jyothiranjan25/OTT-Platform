package com.example.springboot.UserManagment.AppUser;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<AppUserDTO> get(AppUserDTO appUserDTO) {
        List<AppUser> appUser = appUserRepository.findAll();
        return appUserMapper.map(appUser);
    }

    @Transactional
    public AppUserDTO create(AppUserDTO appUserDTO) {
        try {
            AppUser appUser = appUserMapper.map(appUserDTO);
            appUser = appUserRepository.save(appUser);
            return appUserMapper.map(appUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public AppUserDTO update(AppUserDTO appUserDTO) {
        try {
            AppUser appUser = getById(appUserDTO.getId());
            if (appUserDTO.getUsername() != null) {
                appUser.setUsername(appUserDTO.getUsername());
            }
            if (appUserDTO.getEmail() != null) {
                appUser.setEmail(appUserDTO.getEmail());
            }
            if (appUserDTO.getPassword() != null) {
                appUser.setPassword(appUserDTO.getPassword());
            }
            appUser = appUserRepository.save(appUser);
            return appUserMapper.map(appUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String delete(AppUserDTO appUserDTO) {
        try{
            if(appUserRepository.existsById(appUserDTO.getId())){
                appUserRepository.deleteById(appUserDTO.getId());
                return "User deleted successfully";
            }else{
                return "User not found";
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public AppUser getById(Long id) {
        return appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public AppUser getByUserName(String name) {
        return (AppUser) appUserRepository.findByUsername(name).orElse(null);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public AppUser getByEmail(String email) {
        return (AppUser) appUserRepository.findByEmail(email).orElse(null);
    }

    @PostConstruct
    @Transactional
    public void init() {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setUsername("admin");
        appUserDTO.setPassword("admin");
        appUserDTO.setEmail("admin123@gmail.com");
        appUserDTO.setUserType(AppUserEnum.ADMIN);
        appUserRepository.findByUsername(appUserDTO.getUsername()).ifPresentOrElse(
                appUser -> {
                    System.out.println("Admin user already exists");
                },
                () -> {
                    create(appUserDTO);
                    System.out.println("Admin user created successfully");
                }
        );
    }
}
