package com.challenge.challenge.repository;

import com.challenge.challenge.model.Report;
import jdk.vm.ci.meta.Local;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReportsRepository extends CrudRepository<Report, Integer> {

    List<Report> findAllByDateBetweenAndClientId(LocalDateTime date1, LocalDateTime date2, Integer clientId);
}
