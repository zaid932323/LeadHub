package com.leadhub.service;

import org.springframework.http.ResponseEntity;

import com.leadhub.entities.LeadHub;

public interface LeadHubService {

	ResponseEntity<Object> createLead(LeadHub lead);

	ResponseEntity<Object> fetchLeadByMobileNumber(String mobileNumber);

}
