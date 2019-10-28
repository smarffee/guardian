package com.lin.service.impl;

import com.lin.dao.LogEventStatusDao;
import com.lin.service.LogEventStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogEventStatusServiceImpl implements LogEventStatusService {

    @Autowired
    private LogEventStatusDao logEventStatusDao;



}
