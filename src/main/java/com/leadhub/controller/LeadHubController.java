package com.leadhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leadhub.entities.LeadHub;
import com.leadhub.service.LeadHubService;


@RestController
@RequestMapping("/leadhub")
public class LeadHubController {

	@Autowired
    private LeadHubService leadService;

    @PostMapping("/createLead")
    public ResponseEntity<Object> createLead(@RequestBody LeadHub lead) {
    	return leadService.createLead(lead);
    }

    @GetMapping("/mobileNumber")
    public ResponseEntity<Object> fetchLeadByMobileNumber(@RequestParam String mobileNumber) {
        return leadService.fetchLeadByMobileNumber(mobileNumber);
    }
	
}
