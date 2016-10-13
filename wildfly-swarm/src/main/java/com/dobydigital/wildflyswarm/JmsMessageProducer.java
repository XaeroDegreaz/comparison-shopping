package com.dobydigital.wildflyswarm;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class JmsMessageProducer
{
    @Inject
    private JMSContext jmsContext;
    @Resource( lookup = "jms/queue/com.dobydigital.wildflyswarm.TestEntity" )
    private Queue queue;

    public void send( TestEntity apiTestEntity )
    {
        jmsContext.createProducer().send( queue, apiTestEntity );
    }
}
