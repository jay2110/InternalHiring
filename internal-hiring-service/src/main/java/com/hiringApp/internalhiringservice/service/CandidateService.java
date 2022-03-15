package com.hiringApp.internalhiringservice.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.hiringApp.internalhiringservice.entity.Candidate;
import com.hiringApp.internalhiringservice.vo.InternalRequirement;

@Service
public interface CandidateService {

	boolean addEmployee(Candidate candidate);

	List<Candidate> matchemplyees(InternalRequirement internalRequirement);

}
