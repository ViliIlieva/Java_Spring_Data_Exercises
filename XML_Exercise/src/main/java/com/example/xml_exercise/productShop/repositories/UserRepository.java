package com.example.xml_exercise.productShop.repositories;

import com.example.xml_exercise.productShop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where(select count(p) from Product p " +
            "where p.seller = u and p.buyer is not null) > 0 " +
            "order by u.lastName, u.firstName")
    List<User> findAllWithSoldProducts();
}
