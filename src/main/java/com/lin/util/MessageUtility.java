package com.lin.util;

import com.lin.model.GuardianResponse;
import com.lin.model.MessageEnum;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by Lin on 2019/10/27.
 */
public class MessageUtility {

    public static GuardianResponse getErrorResponse(MessageEnum messageEnum, ReloadableResourceBundleMessageSource messageSource) {
        return getErrorResponse(messageEnum, null, messageSource);
    }

    public static GuardianResponse getErrorResponse(MessageEnum messageEnum, Objects[] args, ReloadableResourceBundleMessageSource messageSource) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        return getErrorResponse(messageEnum, args, messageSource, locale);
    }

    public static GuardianResponse getErrorResponse(MessageEnum messageEnum, Object[] args, ReloadableResourceBundleMessageSource messageSource, Locale locale) {
        GuardianResponse res = new GuardianResponse();
        res.setCode(messageEnum.getCode());
        res.setMsg(messageSource.getMessage(messageEnum.getKey(), args, locale));
        res.setContent(null);
        return res;
    }

}
