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

    public static GuardianResponse getGuardianResponse(Object content, ReloadableResourceBundleMessageSource messageSource) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        return getResponse(MessageEnum.SUCCESS, content, null, messageSource, locale);
    }

    public static GuardianResponse getSuccessResponse(ReloadableResourceBundleMessageSource messageSource) {
        return getErrorResponse(MessageEnum.SUCCESS, null, null, messageSource);
    }

    public static GuardianResponse getErrorResponse(MessageEnum messageEnum, ReloadableResourceBundleMessageSource messageSource) {
        return getErrorResponse(messageEnum, null, null, messageSource);
    }

    public static GuardianResponse getErrorResponse(MessageEnum messageEnum, Object content, Objects[] args, ReloadableResourceBundleMessageSource messageSource) {
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        return getResponse(messageEnum, null, args, messageSource, locale);
    }

    public static GuardianResponse getResponse(MessageEnum messageEnum, Object content, Object[] args, ReloadableResourceBundleMessageSource messageSource, Locale locale) {
        GuardianResponse res = new GuardianResponse();
        res.setCode(messageEnum.getCode());
        res.setMsg(messageSource.getMessage(messageEnum.getKey(), args, locale));
        res.setContent(content);
        return res;
    }

}
