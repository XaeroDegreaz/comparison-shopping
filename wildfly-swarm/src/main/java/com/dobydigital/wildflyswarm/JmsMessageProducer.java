package com.dobydigital.wildflyswarm;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.transaction.Transactional;
import javax.xml.bind.JAXB;
import java.io.ByteArrayOutputStream;

@Transactional
@ApplicationScoped
public class JmsMessageProducer
{
    @Inject
    private JMSContext jmsContext;
    @Resource( lookup = "jms/queue/com.dobydigital.wildflyswarm.TestEntity" )
    private Queue queue;

    public void send( TestEntity apiTestEntity )
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JAXB.marshal( apiTestEntity, out );
        TextMessage message = jmsContext.createTextMessage( out.toString() );
        jmsContext.createProducer().send( queue, message );
    }
}
