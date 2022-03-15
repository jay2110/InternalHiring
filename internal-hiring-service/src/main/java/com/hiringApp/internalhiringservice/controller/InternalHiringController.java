package com.hiringApp.internalhiringservice.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hiringApp.internalhiringservice.entity.Candidate;
import com.hiringApp.internalhiringservice.exception.InvalidFieldException;
import com.hiringApp.internalhiringservice.service.CandidateService;
import com.hiringApp.internalhiringservice.vo.InternalRequirement;





@RestController
@CrossOrigin
@RequestMapping(path = "/employee")
public class InternalHiringController {

	@Autowired
	private CandidateService empService;


	// http://localhost:9008/employee/addEmployee/{candidate}
	@PostMapping(path = "/addEmployee")                         //API to save employee data for internal-hiring 
	public ResponseEntity<String> addcandidate(@RequestBody Candidate candidate)  {
		ResponseEntity<String> response = null;
		try{if (empService.addEmployee(candidate)) 
			response = new ResponseEntity<String>(
					"employee with id " + candidate.getId() + " is added", HttpStatus.CREATED);
		return response;
		}catch(InvalidFieldException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMsg());
		}

	}

	// http://localhost:9008/employee/internalRequirement
	@PostMapping(path = "/internalRequirement")                //API to Match eligible candidate for internal-hiring
	public ResponseEntity<List<Candidate>> getMatchedCandidates(@RequestBody InternalRequirement internalRequirement) throws InvalidFieldException {
		try {	List<Candidate> result = empService.matchemplyees(internalRequirement);
		ResponseEntity<List<Candidate>> response = new ResponseEntity<>(result, HttpStatus.OK);
		return response;
		}catch(InvalidFieldException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMsg());
		}
	}

}
