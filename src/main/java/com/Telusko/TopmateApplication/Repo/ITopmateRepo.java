package com.Telusko.TopmateApplication.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.Telusko.TopmateApplication.model.User;

public interface ITopmateRepo extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User>
{
    User findByEmail(String email);
    Iterable<User> findByStatus(String status);
    
}
