package com.exam.service;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User fetchedUser = userRepository.findByUserName(user.getUserName());
        if (fetchedUser != null) {
            throw new Exception("User Already Present");
        }
//        for (UserRole userRole : userRoles) {
//            roleRepository.save(userRole.getRole());
//        }
        user.setUserRoles(userRoles);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        //userRepository.deleteById(userId);
        userRepository.findById(userId)
                .ifPresentOrElse(user -> userRepository.delete(user),()-> System.out.println("User Not Present"));
    }
}
