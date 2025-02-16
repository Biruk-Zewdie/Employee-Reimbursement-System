package com.biruk.ERS.DAOs;

import com.biruk.ERS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// used to perform CRUD operations without write one because JpaRepository extends them from CrudRepository

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

}
