package com.example.demo.domain.repository;



import com.example.demo.domain.entity.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketInfoRepository extends JpaRepository<TicketInfo, Long> {

    List<TicketInfo> findByUserinfoId(String userinfo_id);

    @Query("  SELECT TR\n" +
            "    FROM TravelInfo TR\n" +
            "    inner join TicketInfo TI\n" +
            "    on TR.id = TI.travelinfoBoookingno\n" +
            "    where TI.userinfoId = :userinfoId")
    List<Object> searchBooking(@Param("userinfoId") String userinfoId);


}
