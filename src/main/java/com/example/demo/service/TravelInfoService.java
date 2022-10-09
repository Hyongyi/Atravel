package com.example.demo.service;


import com.example.demo.domain.repository.TravelInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Validated
public class TravelInfoService {
//    @Autowired
//    TravelInfoRepository travelInfoRepository;
//
//    public List<Object> searchOnewayTicket(@RequestParam String departdate, @RequestParam String departlocation, @RequestParam String arrivallocation) throws Exception {
//        List<Object> travelDTO = travelInfoRepository.searchOnewayTicket(departdate,departlocation,arrivallocation);
//        System.out.println("-----"+travelDTO);
//        return travelDTO;
//    }


}
