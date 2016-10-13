package com.dobydigital.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageListener
{
    @Autowired
    private TestEntityRepository testEntityRepository;

    @JmsListener( destination = "com.dobydigital.springboot.TestEntity" )
    public void onMessage( TestEntity apiTestEntity )
    {
        TestEntity testEntity = new TestEntity( apiTestEntity.getTestString() );
        testEntityRepository.save( testEntity );
    }
}
