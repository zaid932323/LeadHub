package com.leadhub.entities;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "leads")
@Entity
public class LeadHub {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private long id;
	
    @Column(nullable = false, unique = true)
    private Integer leadId;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName; 

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String gender;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false, unique = true)
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Email is not valid")
    private String email;

  
    
}

