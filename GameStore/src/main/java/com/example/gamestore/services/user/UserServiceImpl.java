package com.example.gamestore.services.user;

import com.example.gamestore.domain.dtos.UserLoginDTO;
import com.example.gamestore.domain.dtos.UserRegisterDTO;
import com.example.gamestore.domain.entities.User;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Validations.*;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private User loggedInUser;
    private final ModelMapper modelMapper = new ModelMapper ();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] args) {
        final String email = args[1];
        final String password = args[2];
        final String confirmPassword = args[3];
        final String fullName = args[4];

        UserRegisterDTO userRegisterDTO;

        try {
            userRegisterDTO = new UserRegisterDTO (email, password, confirmPassword, fullName);
        }catch (IllegalArgumentException exception){
            return exception.getMessage ();
        }

        final User user = this.modelMapper.map (userRegisterDTO, User.class);

        if (this.userRepository.count () == 0) {
            user.setIsAdmin (true);
        }
        //проверяваме дали такъв потребител вече съществува по имейла му защото е уникален
        boolean doesUserExist = this.userRepository.findByEmail (userRegisterDTO.getEmail ()).isPresent ();

        if (doesUserExist) {
//                throw new IllegalArgumentException (EMAIL_EXISTS);
                return EMAIL_EXISTS;
        }

        this.userRepository.save (user);

        return userRegisterDTO.successfulRegisterFormat();
    }

    @Override
    public String loginUser(String[] args) {
        final String email = args[1];
        final String password = args[2];

        final UserLoginDTO userLoginDTO;

        try {
            userLoginDTO = new UserLoginDTO(email, password);
        }catch (IllegalArgumentException exception){
            return exception.getMessage ();
        }
        Optional<User> user = this.userRepository.findByEmail (userLoginDTO.getEmail ());

        if(user.isPresent () &&
                this.loggedInUser == null &&
                user.get ().getPassword ().equals (userLoginDTO.getPassword ())){
            //ако присъства в нашата база, и нямаме логнат никой, и ако паролата му е същата като в базата
            this.loggedInUser = this.userRepository.findByEmail(userLoginDTO.getEmail ()).get ();
            return String.format (SUCCSESSFUL_LOGGED_FORMAT, this.loggedInUser.getFullName ());
        }

        return PASSWORD_NOT_VALID_MESSAGE;
    }

    @Override
    public String logoutUser() {
        if(this.loggedInUser == null) {
            return LOGOUT_NOT_LOGGED_IN_USER;
        }
        String output = String.format (SUCCSESSFUL_LOGOUT, this.loggedInUser.getFullName ());
        this.loggedInUser = null;
        return output;
    }

    @Override
    public User getLoggedInUser() {
        return this.loggedInUser;
    }
}
