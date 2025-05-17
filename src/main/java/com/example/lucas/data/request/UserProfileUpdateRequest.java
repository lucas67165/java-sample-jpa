package com.example.lucas.data.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Request object for updating user profile information
 * 
 * @author Lucas
 * @since 1.0
 */
@Data
public class UserProfileUpdateRequest {
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;
    
    @Size(max = 100, message = "English name must be less than 100 characters")
    private String nameEn;
    
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;
    
    // Gender: 1 - Male, 2 - Female, 3 - Other
    private Integer gender;
    
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;
    
    @Size(max = 50, message = "ID card number must be less than 50 characters")
    private String idCardNo;
    
    private LocalDate idCardIssueDate;
    
    // Marital status: 1 - Single, 2 - Married, 3 - Divorced, 4 - Widowed
    private Integer maritalStatus;
    
    @Size(max = 255, message = "Photo URL must be less than 255 characters")
    private String photo;
}