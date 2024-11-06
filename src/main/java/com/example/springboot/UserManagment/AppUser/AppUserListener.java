package com.example.springboot.UserManagment.AppUser;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppUserListener implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppUserListener.applicationContext = applicationContext;
    }

    @PrePersist
    public void prePersist(AppUser appUser) {
        if(appUser.getEmail() == null){
            throw new RuntimeException("Email is required");
        }
        if (appUser.getUsername() == null) {
            throw new RuntimeException("Username is required");
        }
        checkDuplicates(appUser);
    }

    @PreUpdate
    public void preUpdate(AppUser appUser) {
        if(appUser.getEmail() == null){
            throw new RuntimeException("Email is required");
        }
        if (appUser.getUsername() == null) {
            throw new RuntimeException("Username is required");
        }
    }

    @PreRemove
    public void preRemove(AppUser appUser) {
        if (appUser.getId() == 1) {
            throw new RuntimeException("Id 1 cannot be deleted");
        }
    }

    private void checkDuplicates(AppUser appUser) {
        AppUserService appUserService = applicationContext.getBean(AppUserService.class);
        if (appUserService.getByUserName(appUser.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        if (appUserService.getByEmail(appUser.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
    }
}
