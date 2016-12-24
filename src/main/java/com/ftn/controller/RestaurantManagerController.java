package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.Manager;
import com.ftn.model.User;
import com.ftn.repository.RestaurantDao;
import com.ftn.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/restaurantManagers")
public class RestaurantManagerController {

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RestaurantDao restaurantDao;

    @PreAuthorize("hasAuthority('SYSTEM_MANAGER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity read(){
        return new ResponseEntity<>(userDao.findByRole(User.Role.MANAGER), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SYSTEM_MANAGER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Manager manager) {
        if (manager.getRestaurant() == null) {
            throw new BadRequestException();
        }
        restaurantDao.findById(manager.getRestaurant().getId()).orElseThrow(BadRequestException::new);
        manager.setRole(User.Role.MANAGER);
        manager.setPassword(encoder.encode(manager.getPassword()));
        manager.setEnabled(true);
        //manager.setConfirmationCode(UUID.randomUUID().toString());
        userDao.save(manager);
        //mailService.sendVerificationMail(request, manager.getEmail(), manager.getConfirmationCode());
        return new ResponseEntity<>(manager, HttpStatus.CREATED);
    }
}
