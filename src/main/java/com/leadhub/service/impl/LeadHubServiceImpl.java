package com.leadhub.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.leadhub.entities.LeadHub;
import com.leadhub.repository.LeadHubRepository;
import com.leadhub.response.ApiResponse;
import com.leadhub.response.ErrorResponse;
import com.leadhub.service.LeadHubService;

@Service
public class LeadHubServiceImpl implements LeadHubService {

    @Autowired
    private LeadHubRepository leadHubRepository;

    @Override
    public ResponseEntity<Object> createLead(LeadHub lead) {

    	if (emailIsInvalid(lead.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse("E10001", Collections.singletonList("Email is not valid"))
            );
        }

        // Check for duplicate email
        if (emailIsDuplicate(lead.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse("E10002", Collections.singletonList("Email is already in use"))
            );
        }
    	
        Optional<LeadHub> existingLead = leadHubRepository.findByLeadId(lead.getLeadId());
        if (existingLead.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse("E10010", Collections.singletonList("Lead Already Exists in the database with the lead id"))
            );
        }

        LeadHub save = leadHubRepository.save(lead); 
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Created Leads Successfully"));
    }

    @Override
    public ResponseEntity<Object> fetchLeadByMobileNumber(String mobileNumber) {
        List<LeadHub> leads = leadHubRepository.findByMobileNumber(mobileNumber);

        if (leads.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse("E10011", Collections.singletonList("No Lead found with the Mobile Number " + mobileNumber))
            );
        }

        return ResponseEntity.ok(new ApiResponse(leads));
    }

    private boolean emailIsInvalid(String email) {
       
        return !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
    }

    private boolean emailIsDuplicate(String email) {
        // Check for duplicate emails in the database
        return leadHubRepository.existsByEmail(email);
    }
}
