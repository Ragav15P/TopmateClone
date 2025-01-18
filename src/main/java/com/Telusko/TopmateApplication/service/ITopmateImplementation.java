package com.Telusko.TopmateApplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Telusko.TopmateApplication.Repo.ITopmateRepo;
import com.Telusko.TopmateApplication.Repo.ITopmateRepo1;
import com.Telusko.TopmateApplication.model.User;

@Service
public class ITopmateImplementation implements ITopmateService
{
	@Autowired
	private ITopmateRepo obj1;
	@Autowired
	private ITopmateRepo1 obj3;
	@Autowired
	private PasswordEncoder object;

	@Override
	public String registerUser(User user) {
	    user.setPassword(object.encode(user.getPassword()));
		User uss=obj3.save(user);
		//emailService.sendRegistrationEmail(user.getEmail(), user.getName());
		return "user has Been Registered Successsfully ,Confirmation Email Has Been Sent....";

	}

	@Override
	public User findUserByEmail(String email) {
		
		return obj1.findByEmail(email);
	}
	

	@Override
	public User findUserById(Long id) {
		
		Optional<User>op=obj3.findById(id);
		return op.get();
	}

	@Override
	public Iterable<User> findAllUsers() 
	{
		
		return obj3.findAll();
	}

	@Override
	public User updateUser(Long id, User userDetails) 
	{
		Optional<User>op=obj3.findById(id);
		User userNew=op.get();
		
		if(userDetails.getName()!=null)
		{
			userNew.setName(userDetails.getName());
		}
		if(userDetails.getEmail()!=null)
		{
			userNew.setEmail(userDetails.getEmail());
		}
		if(userDetails.getPhone()!=null)
		{
			userNew.setPhone(userDetails.getPhone());
		}
		if(userDetails.getName()!=null)
		{
			userNew.setName(userDetails.getName());
		}
		if (userDetails.getStatus() != null) {
	        userNew.setStatus(userDetails.getStatus());
	    }
	    if (userDetails.getRole() != null) {
	        userNew.setRole(userDetails.getRole());
	    }
	    if (userDetails.getProfilePicture() != null) {
	        userNew.setProfilePicture(userDetails.getProfilePicture());
	    }
	    if (userDetails.getLinkedInUrl() != null) {
	        userNew.setLinkedInUrl(userDetails.getLinkedInUrl());
	    }
	    if (userDetails.getTwitterUrl() != null) {
	        userNew.setTwitterUrl(userDetails.getTwitterUrl());
	    }

	    return obj3.save(userNew);
	}
	
	


	@Override
	public void deleteUser(Long id) {
		Optional<User>op=obj3.findById(id);
		obj3.delete(op.get());
		
		
	}

	@Override
	public Iterable<User> filterAndSort(String name,String email,String status,String role, String sortBy, String sortOrder) 
	{
		Specification<User>spec=Specification.where(null);
		if(name!=null && !name.isEmpty())
		{
			spec=spec.and((root,query,criteriaBuilder)->criteriaBuilder.like(root.get("name"), "%" +name + "%"));
		}
		if(email!=null && !email.isEmpty())
		{
			spec=spec.and((root,query,criteriaBuilder)->criteriaBuilder.like(root.get("email"), "%" +email));
		}
		if (status != null && !status.isEmpty()) {
		    spec = spec.and((root, query, criteriaBuilder) -> 
		        criteriaBuilder.equal(root.get("status"), status));
		}
		if (role != null && !role.isEmpty()) {
		    spec = spec.and((root, query, criteriaBuilder) -> 
		        criteriaBuilder.equal(root.get("role"), role));
		}
		Sort sort=sortOrder.equalsIgnoreCase("desc")
				? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
	
		   return obj1.findAll(spec,sort);
	}

	@Override
	public void softDelete(Long id) 
	{
	   Optional<User> op1=obj3.findById(id);
	   User userTemp=op1.get();
	   userTemp.setStatus("INACTIVE");
	   obj3.save(userTemp);
	}

	@Override
	public Iterable<User> findByStatus(String status) 
	{
		Iterable<User> it=obj1.findByStatus(status);
		return it;
	}
	
	

	

	

}
