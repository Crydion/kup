package com.crydion.blog.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crydion.blog.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);

}
