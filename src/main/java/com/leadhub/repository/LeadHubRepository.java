package com.leadhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadhub.entities.LeadHub;

@Repository
public interface LeadHubRepository extends JpaRepository<LeadHub, Long> {

	Optional<LeadHub> findByLeadId(Integer leadId);
    List<LeadHub> findByMobileNumber(String mobileNumber);
    boolean existsByEmail(String email);
}
