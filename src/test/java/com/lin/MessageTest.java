package com.lin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * Created by Lin on 2019/10/26.
 */
public class MessageTest extends BaseTest {

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @Test
    public void messageTest() {

        String cnMsg = messageSource.getMessage("response.success", new Object[]{}, Locale.SIMPLIFIED_CHINESE);
        System.out.println(cnMsg);

        String usMsg = messageSource.getMessage("response.success", new Object[]{}, Locale.ENGLISH);
        System.out.println(usMsg);

    }

}
