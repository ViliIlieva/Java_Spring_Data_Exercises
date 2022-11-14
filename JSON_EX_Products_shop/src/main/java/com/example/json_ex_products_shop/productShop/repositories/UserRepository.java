package com.example.json_ex_products_shop.productShop.repositories;

import com.example.json_ex_products_shop.productShop.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



}
