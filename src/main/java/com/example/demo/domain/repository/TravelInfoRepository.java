package com.example.demo.domain.repository;

import com.example.demo.domain.entity.TravelInfo;
import com.example.demo.dto.TravelDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TravelInfoRepository extends JpaRepository<TravelInfo, Long>, JpaSpecificationExecutor<TravelInfo> {

//    SELECT * FROM travel_info
//    WHERE departdate='2008-11-11'AND deapartlocation='경기' AND arrivallocation='울산'
//    @Modifying
//    @Query(value = "select f from TravelInfo f where f.departdate = :departdate and f.departlocation = :departlocation and f.arrivallocation = :arrivallocation")
//    List<Object[]> searchOnewayTicket(String departdate, String departlocation, String arrivallocation);
    @Transactional
    @Modifying
    @Query("SELECT f FROM TravelInfo f where f.departdate = :departdate and f.departlocation = :departlocation and f.arrivallocation = :arrivallocation order by f.price")
    List<Object> searchOnewayTicket(@Param("departdate") String departdate, @Param("departlocation") String departlocation, @Param("arrivallocation") String arrivallocation);

    @Query("SELECT f FROM TravelInfo f where f.departdate = :departdate and f.departlocation = :departlocation and f.arrivallocation = :arrivallocation order by f.flighthour asc ")
    List<Object> searchOnewayTicketbyFlighthour(@Param("departdate") String departdate, @Param("departlocation") String departlocation, @Param("arrivallocation") String arrivallocation);


    @Query("SELECT f FROM TravelInfo f where f.departdate = :returndate and f.departlocation = :arrivallocation and f.arrivallocation = :departlocation order by f.price")
    List<Object> searchRoundTicket(@Param("returndate") String returndate, @Param("departlocation") String departlocation, @Param("arrivallocation") String arrivallocation);

    @Query("SELECT f FROM TravelInfo f where f.departdate = :returndate and f.departlocation = :arrivallocation and f.arrivallocation = :departlocation order by f.flighthour asc ")
    List<Object> searchRoundTicketbyflighthour(@Param("returndate") String returndate, @Param("departlocation") String departlocation, @Param("arrivallocation") String arrivallocation);

    List<Object> findTravelInfoById(long id);
}
