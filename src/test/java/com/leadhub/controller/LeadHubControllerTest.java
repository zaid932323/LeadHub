package com.leadhub.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.leadhub.entities.LeadHub;
import com.leadhub.response.ApiResponse;
import com.leadhub.service.LeadHubService;

@ExtendWith(MockitoExtension.class)
class LeadHubControllerTest {

	/*
	 * @Test void test() { fail("Not yet implemented"); }
	 */

	@Mock
	private LeadHubService leadHubService;

	@InjectMocks
	private LeadHubController leadHubController;

	@Test
	public void testCreateLead() {
		LeadHub lead = new LeadHub();

		when(leadHubService.createLead(lead)).thenReturn(
				ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Created Lead Successfully")));

		ResponseEntity<Object> response = leadHubController.createLead(lead);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testFetchLeadByMobileNumber() {
		String mobileNumber = "8877887788"; // replace with an actual mobile number for testing

		when(leadHubService.fetchLeadByMobileNumber(mobileNumber))
				.thenReturn(ResponseEntity.ok(new ApiResponse(Arrays.asList(new LeadHub()))));

		ResponseEntity<Object> response = leadHubController.fetchLeadByMobileNumber(mobileNumber);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
