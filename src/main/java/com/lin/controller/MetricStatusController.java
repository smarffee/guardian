package com.lin.controller;

import com.lin.exception.GuardianException;
import com.lin.model.EmergencyLevelEnum;
import com.lin.model.GuardianResponse;
import com.lin.model.MessageEnum;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.HeartBeatService;
import com.lin.util.MessageUtility;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lin on 2019/10/27.
 */
@RestController
public class MetricStatusController {

    private static final Logger logger = LoggerFactory.getLogger(MetricStatusController.class);

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    private HeartBeatService heartBeatService;

    @RequestMapping(value = "/metric/status", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public GuardianResponse metricStatus(@RequestBody MetricStatusRequest metricStatusRequest) {

        logger.info("====> metricStatus: start to process metric status. the request params is {}", metricStatusRequest);

        GuardianResponse guardianResponse;

        try {

            if (StringUtils.isBlank(metricStatusRequest.getMetricKey())) {
                throw new GuardianException(MessageEnum.METRIC_KEY_IS_NULL);
            }

            if (!EmergencyLevelEnum.validCode(metricStatusRequest.getLevel())) {
                throw new GuardianException(MessageEnum.METRIC_LEVEL_ILLEGAL);
            }

            heartBeatService.doHeartBeat(metricStatusRequest);

            guardianResponse = MessageUtility.getSuccessResponse(messageSource);

        } catch (GuardianException e) {
            logger.error("metricStatus: happen an guardian exception.", e);
            guardianResponse = MessageUtility.getErrorResponse(e.getMessageEnum(), messageSource);
        } catch (Exception e) {
            logger.error("metricStatus: happen an unknow exception while process metric status.", e);
            guardianResponse = MessageUtility.getErrorResponse(MessageEnum.UNKNOW_ERROR, messageSource);
        }

        logger.info("<==== metricStatus: end to process metric status. the response is {}", guardianResponse);

        return guardianResponse;
    }

}
