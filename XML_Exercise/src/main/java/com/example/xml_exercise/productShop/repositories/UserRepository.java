package com.example.xml_exercise.productShop.repositories;

import com.example.xml_exercise.productShop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
