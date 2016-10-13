package com.dobydigital.wildflyswarm;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.transaction.Transactional;

@Transactional
public class JmsMessageProducer
{
    @Inject
    private JMSContext jmsContext;
    @Resource( lookup = "com.dobydigital.wildflyswarm.TestEntity" )
    private Queue queue;

    public void send( TestEntity apiTestEntity )
    {
        jmsContext.createProducer().send( queue, apiTestEntity );
    }
}
