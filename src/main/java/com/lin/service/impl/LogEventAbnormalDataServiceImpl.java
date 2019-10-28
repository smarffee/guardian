package com.lin.service.impl;

import com.lin.dao.LogEventAbnormalDataDao;
import com.lin.service.LogEventAbnormalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogEventAbnormalDataServiceImpl implements LogEventAbnormalDataService {

    @Autowired
    private LogEventAbnormalDataDao logEventAbnormalDataDao;



}
