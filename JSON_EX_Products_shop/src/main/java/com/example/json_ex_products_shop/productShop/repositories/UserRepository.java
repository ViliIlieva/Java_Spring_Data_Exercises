package com.example.json_ex_products_shop.productShop.repositories;

import com.example.json_ex_products_shop.productShop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u join u.sellingItems p " +
            "where p.buyer is not null ")
    List<User> findAllWithSoldProducts();

    @Query("select u from User u join u.sellingItems p " +
            "where p.buyer is not null " +
            "order by size(u.sellingItems)desc , u.lastName asc")
    List<User> findAllWithSoldProductsOrderByCount();
}
