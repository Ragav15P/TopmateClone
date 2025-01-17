package com.Telusko.TopmateApplication.Web;

import com.Telusko.TopmateApplication.model.User;
import com.Telusko.TopmateApplication.service.EmailService;
import com.Telusko.TopmateApplication.service.ITopmateImplementation;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController 
{

    @Autowired
    private ITopmateImplementation obj2;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) 
    {
        obj2.registerUser(user);
       // emailService.sendRegistrationEmail(user.getEmail(), user.getName());
        try {
            String htmlMessage = """
                <h1>Welcome to Topmate Application!</h1>
                <p>Dear <b>%s</b>,</p>
                <p>Thank you for registering with us. We're excited to have you on board!</p>
                <a href="https://your-app-link.com/login">Click here to log in</a>
                <img src="https://i.postimg.cc/bv1hk4xp/Topmate.webp" alt="Topmate App Interface" width="600" height="300" style="border-radius:10px;" />

                <br><br>
                <p>Best regards,<br>The Topmate Team</p>
            """.formatted(user.getName());

            emailService.sendHtmlEmail(user.getEmail(), "Welcome to Topmate!", htmlMessage);
        } catch (MessagingException e) {
            return "User registered, but failed to send email: " + e.getMessage();
        }

        return "User registered successfully!,Confirmation Email Has Been Sent To Registerd Email";
    }
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) 
    {
        return obj2.findUserByEmail(email);
    }
    @GetMapping("/id/{id}")
    public User getUserByTheirId(@PathVariable Long id)
    {
    	return obj2.findUserById(id);
    }
    @GetMapping("/All")
    public ResponseEntity<Iterable<User>>getAll()
    {
    	Iterable<User>users=obj2.findAllUsers();
    	return ResponseEntity.ok(users);
    }
    @PutMapping("/update/{id1}")
    public ResponseEntity<User>update(@PathVariable Long id1,@RequestBody User us)
    {
    	User usernew=obj2.updateUser(id1, us);
    	return ResponseEntity.ok(usernew);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
    	obj2.deleteUser(id);
    	return ResponseEntity.ok("User with Id " +id + "Deleted");
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<User>> filterAndSortUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String role,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) 
    {
        Iterable<User> users = obj2
        		.filterAndSort(name, email, status, role, sortOrder, sortOrder);
        return ResponseEntity.ok(users);
    }
    @PutMapping("/softDeleteapi/{id}")
    public ResponseEntity<String>softRemove(@PathVariable Long id)
    {
    	obj2.softDelete(id);
    	return ResponseEntity.ok("Soft Deletion Successful");
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<Iterable<User>> getUsersByStatus(@PathVariable String status) 
    {
        Iterable<User> users = obj2.findByStatus(status);
        return ResponseEntity.ok(users);
    }



   
}