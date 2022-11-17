package com.example.json_ex_products_shop.productShop.services;

import com.example.json_ex_products_shop.productShop.entities.users.User;
import com.example.json_ex_products_shop.productShop.entities.users.UserWithSoldProductsDTO;
import com.example.json_ex_products_shop.productShop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUserWithSoldProducts() {
        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts ();

      return   allWithSoldProducts.stream ()
                .map (user -> this.modelMapper.map (user, UserWithSoldProductsDTO.class))
                .collect (Collectors.toList ());


    }

    @Override
    @Transactional
    public List<User> getUserWithSoldProductsOrderByCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOrderByCount();
            all.get(0).getSellingItems().size();
            return null;
    }
}

