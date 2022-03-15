package com.hiringApp.internalhiringservice.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.hiringApp.internalhiringservice.entity.Candidate;
import com.hiringApp.internalhiringservice.exception.InvalidFieldException;
import com.hiringApp.internalhiringservice.repository.Candidaterepo;
import com.hiringApp.internalhiringservice.vo.InternalRequirement;


@Service
public class CandidateServiceImpl implements CandidateService{
	
	Logger logger= LoggerFactory.getLogger(CandidateServiceImpl.class);

	@Autowired
	private Candidaterepo empRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean addEmployee(Candidate candidate) throws InvalidFieldException {
		if(!candidate.getName().isEmpty() && !candidate.getSkillSets().isEmpty() && candidate.getId()!=0) {
			empRepo.save(candidate);
			logger.info("Employee data saved successfully");
			return true;
		}
		logger.info("Invalid field");
		throw new InvalidFieldException("Invalid field");

	}

	@Override
	public List<Candidate> matchemplyees(InternalRequirement internalRequirement) throws InvalidFieldException {
		List<Candidate> allEmp= empRepo.findAll();
		List<Candidate> shortListed=new ArrayList<Candidate>();
	if(internalRequirement.getId()!=0 &&  !internalRequirement.getDesignation().isEmpty() && !internalRequirement.getOptionalSkills().isEmpty() && !internalRequirement.getRequiredSkills().isEmpty()) {
			for(Candidate e: allEmp) {
	
			if(e.getSkillSets().containsAll(internalRequirement.getRequiredSkills()) && e.getSkillSets().containsAll(internalRequirement.getOptionalSkills())) {
				shortListed.add(e);
			}
		}
		if(!shortListed.isEmpty()) {
			logger.info("got the Requirement matched internally");
			return shortListed;
		}else {
			for(Candidate e0: allEmp) {
				if(e0.getSkillSets().containsAll(internalRequirement.getRequiredSkills())){
					shortListed.add(e0);
				}
			}
		}
		if(!shortListed.isEmpty()) {
			logger.info("got the Requirement matched internally without optional skills");
			return shortListed;

		}else {

			ResponseEntity<Candidate[]> res  =  (restTemplate.postForEntity("http://localhost:9009/candidate/InternalRequest/",internalRequirement,Candidate[].class));
			shortListed= Arrays.asList(res.getBody());
		}

		logger.info("got the Requirement matched externally");
		return shortListed;
	}
	throw new InvalidFieldException("Invalid field");
	}
}


