package com.bjworld.groupware.common.service.impl;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.springframework.core.io.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class CustomMessageSource extends ReloadableResourceBundleMessageSource {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomMessageSource.class);

    /**
     * Gets all messages for presented Locale.
     * @param locale user request's locale
     * @return all messages
     */
    public Properties getMessages(Locale locale){
        return getMergedProperties(locale).getProperties();
    }

}