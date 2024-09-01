package com.ecom.service;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {

    public void removeSessionMessage();     // remove session data after each run

}
