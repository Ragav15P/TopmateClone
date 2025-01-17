package com.Telusko.TopmateApplication.service;

import com.Telusko.TopmateApplication.model.User;

public interface ITopmateService 
{
    public String registerUser(User user);
    public User findUserByEmail(String email);
    public Iterable<User> findByStatus(String status);
    public User findUserById(Long id);
    public Iterable<User>findAllUsers();
    User updateUser(Long id,User userDetails);
    void deleteUser(Long id);
    Iterable<User>filterAndSort(String name,String email,String status,String role,String sortBy,String sortOrder);
    public void softDelete(Long id);
}
