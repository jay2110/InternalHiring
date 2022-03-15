package com.hiringApp.internalhiringservice;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hiringApp.internalhiringservice.entity.Candidate;
import com.hiringApp.internalhiringservice.service.CandidateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {
	
	
	
	
	@Autowired
	private CandidateService candidateService;
	
	//candidate Service Testing
	@Test
	void testToAddCandidate() {
		Candidate expected = new Candidate();
		expected.setId(1);
		expected.setName("Ajay");
		expected.getSkillSets().add("java");
		expected.getSkillSets().add("microService");
		expected.getSkillSets().add("Aws");
		expected.getSkillSets().add("Mysql");
		
		boolean result = candidateService.addEmployee(expected);
		assertTrue(result);
	}
	

}
