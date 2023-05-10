package com.sehphirry.users.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GenericService {
    public int generateOtp(){
        Random random = new Random();
        return random.nextInt(900000);
    }
}

