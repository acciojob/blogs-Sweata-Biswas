package com.driver.repositories;

import com.driver.models.Blog;
import com.driver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("update user u set u.username= :#{#user.username},"
            + "u.password = :#{#user.password},"
            + "u.firstName = :#{#user.firstName},"
            + "u.lastName = :#{#user.lastName}"
            + "where u.id = :#{#user.id}")
    void updateUserDetail(User user);


    User findByUsername(String username);
}
