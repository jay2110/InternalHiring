package com.hiringApp.internalhiringservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiringApp.internalhiringservice.entity.Candidate;

@Repository
public interface Candidaterepo extends JpaRepository<Candidate, Integer>{

}
