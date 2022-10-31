package com.example.demo.service;

import com.example.demo.domain.repository.TicketInfoRepository;
import com.example.demo.domain.repository.UserInfoRepository;
import com.example.demo.dto.TicketDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class TicketInfoService {
    @Autowired
    private TicketInfoRepository ticketInfoRepository;


}
