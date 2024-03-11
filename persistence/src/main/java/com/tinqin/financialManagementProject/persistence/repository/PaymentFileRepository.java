package com.tinqin.financialManagementProject.persistence.repository;


import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentFileRepository extends JpaRepository<PaymentsFile, UUID>, PagingAndSortingRepository<PaymentsFile,UUID> {

    List<PaymentsFile> findAllByDateOfCreation(Timestamp timestamp);
    List<PaymentsFile> findAllByOrderByDateOfCreation();
}
