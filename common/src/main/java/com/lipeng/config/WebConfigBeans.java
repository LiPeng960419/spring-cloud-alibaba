package com.lipeng.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @Author: lipeng 910138
 * @Date: 2020/5/20 13:08
 */
/*
由于Feign中默认时区用的是美国时间
所以会导致服务端接收的Date与实际会相差14小时。
 */
@Configuration
@Slf4j
public class WebConfigBeans {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
                .getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer
                    .getConversionService();
            genericConversionService
                    .addConverter(String.class, Date.class, new String2DateConverter());
        }
    }

    class String2DateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String source) {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
            try {
                return sdf.parse(source);
            } catch (ParseException e) {
                log.error("String2DateConverter ParseException", e);
            }
            return null;
        }
    }

}