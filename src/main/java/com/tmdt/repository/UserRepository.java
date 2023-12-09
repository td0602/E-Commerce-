package com.tmdt.repository;

import com.tmdt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName); // viết như này JPA tự hiểu

}
