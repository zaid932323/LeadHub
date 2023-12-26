package com.leadhub.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.leadhub.entities.LeadHub;
import com.leadhub.repository.LeadHubRepository;
import com.leadhub.response.ApiResponse;
import com.leadhub.response.ErrorResponse;

@ExtendWith(MockitoExtension.class)
class LeadHubServiceImplTest {

	/*
	 * @Test void test() { fail("Not yet implemented"); }
	 */

	@Mock
    private LeadHubRepository leadHubRepository;

    @InjectMocks
    private LeadHubServiceImpl leadHubService;

    @Test
    public void testCreateLead_Success() {
        LeadHub lead = createSampleLead();

        when(leadHubRepository.findByLeadId(eq(lead.getLeadId()))).thenReturn(Optional.empty());
        when(leadHubRepository.existsByEmail(eq(lead.getEmail()))).thenReturn(false);
        when(leadHubRepository.save(any(LeadHub.class))).thenReturn(lead);

        ResponseEntity<Object> response = leadHubService.createLead(lead);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        ApiResponse apiResponse = (ApiResponse) response.getBody();
        assertEquals("success", apiResponse.getStatus());
        assertEquals("Created Leads Successfully", apiResponse.getData());
    }



    @Test
    public void testCreateLead_InvalidEmail() {
        LeadHub lead = createSampleLead();
        lead.setEmail("invalidEmail");

        ResponseEntity<Object> response = leadHubService.createLead(lead);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("E10001", ((ErrorResponse) response.getBody()).getErrorResponse().getCode());
        assertEquals("Email is not valid", ((ErrorResponse) response.getBody()).getErrorResponse().getMessages().get(0));
    }


    @Test
    public void testCreateLead_DuplicateLeadId() {
        LeadHub lead = createSampleLead();

        when(leadHubRepository.findByLeadId(eq(lead.getLeadId()))).thenReturn(Optional.of(lead));

        ResponseEntity<Object> response = leadHubService.createLead(lead);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("E10010", ((ErrorResponse) response.getBody()).getErrorResponse().getCode());
        assertEquals("Lead Already Exists in the database with the lead id", ((ErrorResponse) response.getBody()).getErrorResponse().getMessages().get(0));
    }

    @Test
    public void testFetchLeadByMobileNumber_Success() {
        String mobileNumber = "8877887788";
        LeadHub lead = createSampleLead();

        when(leadHubRepository.findByMobileNumber(eq(mobileNumber))).thenReturn(Collections.singletonList(lead));

        ResponseEntity<Object> response = leadHubService.fetchLeadByMobileNumber(mobileNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lead, ((List<LeadHub>) ((ApiResponse) response.getBody()).getData()).get(0));
    }

    @Test
    public void testFetchLeadByMobileNumber_NoLeadFound() {
        String mobileNumber = "8877887788";

        when(leadHubRepository.findByMobileNumber(eq(mobileNumber))).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = leadHubService.fetchLeadByMobileNumber(mobileNumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("E10011", ((ErrorResponse) response.getBody()).getErrorResponse().getCode());
        assertEquals("No Lead found with the Mobile Number " + mobileNumber, ((ErrorResponse) response.getBody()).getErrorResponse().getMessages().get(0));
    }

    private LeadHub createSampleLead() {
        LeadHub lead = new LeadHub();
        lead.setLeadId(7039);
        lead.setFirstName("bushra_zainab");
        lead.setLastName("siddiqui");
        lead.setMobileNumber("8169179187");
        lead.setGender("FeMale");
        lead.setDob(new Date());
        lead.setEmail("bushrazainab60@gmail.com");
        return lead;
    }
}
