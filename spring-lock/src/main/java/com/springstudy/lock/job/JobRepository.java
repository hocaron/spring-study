package com.springstudy.lock.job;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {

    Job findByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select j from Job j where j.name = :name")
    Job findByNameWithPessimisticLock(@Param("name") String name);
}
