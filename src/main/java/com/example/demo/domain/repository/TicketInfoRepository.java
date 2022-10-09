package com.example.demo.domain.repository;



import com.example.demo.domain.entity.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketInfoRepository extends JpaRepository<TicketInfo, Long> {
}
