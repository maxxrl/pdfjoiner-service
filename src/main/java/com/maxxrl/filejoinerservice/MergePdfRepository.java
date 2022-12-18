package com.maxxrl.filejoinerservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MergePdfRepository extends JpaRepository<MergePdf, Long> {

    @Query("SELECT pdf FROM MergePdf pdf WHERE pdf.mergeId = ?1")
    List<MergePdf> findAllByMergeId(final String mergeId);
}
