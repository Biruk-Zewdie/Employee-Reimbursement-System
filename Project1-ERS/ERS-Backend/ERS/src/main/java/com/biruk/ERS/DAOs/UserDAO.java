package com.biruk.ERS.DAOs;

import com.biruk.ERS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// used to perform CRUD operations without write one because JpaRepository extends them from CrudRepository

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    //In order to Authenticate a user, the user username and password should exist in our User table.
    //if the user username and the corresponding password exist in our user table, the user login to his specific role,
    //since the username with corresponding password may or may not exist in our database and we need to check
    // both username and password at the same time, we use customized Optional <user > container to avoid NullPointerException.
//
//    public Optional <User> findByUsernameAndPassword (String username, String password);
//
//
    public Optional <User> findByUserName (String userName);
}
