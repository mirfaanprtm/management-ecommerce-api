package com.example.livecodeecommerce.repository;

import com.example.livecodeecommerce.models.requests.DailyReportRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyReportRepository extends JpaRepository<DailyReportRequest, Long> {

}
