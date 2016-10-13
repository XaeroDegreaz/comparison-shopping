package com.dobydigital.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageProducer
{
    @Autowired
    private JmsTemplate jmsTemplate;

    @Bean
    public MessageConverter jacksonMessageConverter()
    {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType( MessageType.TEXT );
        converter.setTypeIdPropertyName( "_type" );
        return converter;
    }

    public void send( TestEntity apiTestEntity )
    {
        jmsTemplate.convertAndSend( TestEntity.class.getName(), apiTestEntity );
    }
}
