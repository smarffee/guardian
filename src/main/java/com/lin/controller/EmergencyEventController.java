package com.lin.controller;

import com.lin.exception.GuardianException;
import com.lin.model.GuardianResponse;
import com.lin.model.MessageEnum;
import com.lin.model.event.EmergencyEventListResponse;
import com.lin.service.MetricEmergencyEventService;
import com.lin.util.MessageUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmergencyEventController {

    private static final Logger logger = LoggerFactory.getLogger(EmergencyEventController.class);

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private MetricEmergencyEventService metricEmergencyEventService;

    @RequestMapping(value = "/event/list", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public GuardianResponse emergencyEventList() {

        logger.info("====> emergencyEventList: start to get emergency event list");

        GuardianResponse guardianResponse;

        try {
            EmergencyEventListResponse emergencyEventListResponse = metricEmergencyEventService.selectAllUnSolvedEvent();

            guardianResponse = MessageUtility.getGuardianResponse(emergencyEventListResponse, messageSource);

        } catch (GuardianException e) {
            logger.error("emergencyEventList: happen an guardian exception.", e);
            guardianResponse = MessageUtility.getErrorResponse(e.getMessageEnum(), messageSource);
        } catch (Exception e) {
            logger.error("emergencyEventList: happen an unknow exception while select event list.", e);
            guardianResponse = MessageUtility.getErrorResponse(MessageEnum.UNKNOW_ERROR, messageSource);
        }

        logger.info("<==== emergencyEventList: end to get emergency event list. the response is {}", guardianResponse);

        return guardianResponse;
    }

}
