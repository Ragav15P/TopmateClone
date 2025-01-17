package com.Telusko.TopmateApplication.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Size(min = 10, max = 15)
    private String phone;

    private LocalDateTime createdAt;

    private String role; // User, Admin, Professional, etc.

    private String profilePicture; // URL or base64 string for profile image

    private String status = "ACTIVE"; // ACTIVE, INACTIVE, or BANNED

    @Pattern(regexp = "https?://.*", message = "Invalid URL format")
    private String linkedInUrl;

    @Pattern(regexp = "https?://.*", message = "Invalid URL format")
    private String twitterUrl;

    @OneToMany(mappedBy = "user")
    private List<Service> services = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Book> bookings = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Book> getBookings() {
		return bookings;
	}

	public void setBookings(List<Book> bookings) {
		this.bookings = bookings;
	}

	public User(Long id, @Email String email, @NotBlank String password, @NotBlank String name,
			@Size(min = 10, max = 15) String phone, LocalDateTime createdAt, String role, String profilePicture,
			String status, @Pattern(regexp = "https?://.*", message = "Invalid URL format") String linkedInUrl,
			@Pattern(regexp = "https?://.*", message = "Invalid URL format") String twitterUrl, List<Service> services,
			List<Book> bookings) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.createdAt = createdAt;
		this.role = role;
		this.profilePicture = profilePicture;
		this.status = status;
		this.linkedInUrl = linkedInUrl;
		this.twitterUrl = twitterUrl;
		this.services = services;
		this.bookings = bookings;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", createdAt=" + createdAt + ", role=" + role + ", profilePicture=" + profilePicture + ", status="
				+ status + ", linkedInUrl=" + linkedInUrl + ", twitterUrl=" + twitterUrl + "]";
	}

  
}
