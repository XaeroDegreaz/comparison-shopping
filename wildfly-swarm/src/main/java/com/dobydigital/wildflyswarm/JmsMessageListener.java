package com.dobydigital.wildflyswarm;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXB;
import java.io.ByteArrayInputStream;

@MessageDriven(
    name = "TestJmsMessageListener",
    activationConfig = {
        @ActivationConfigProperty( propertyName = "destinationLookup", propertyValue = "jms/queue/com.dobydigital.wildflyswarm.TestEntity" ),
        @ActivationConfigProperty( propertyName = "destinationType", propertyValue = "javax.jms.Queue" )
    } )
@Stateless
public class JmsMessageListener implements MessageListener
{
    @Inject
    private TestEntityRepository testEntityRepository;

    @Override
    public void onMessage( Message message )
    {
        TextMessage textMessage = (TextMessage) message;
        try
        {
            ByteArrayInputStream in = new ByteArrayInputStream( textMessage.getText().getBytes() );
            TestEntity apiTestEntity = JAXB.unmarshal( in, TestEntity.class );
            TestEntity testEntity = new TestEntity( apiTestEntity.getTestString() );
            testEntityRepository.save( testEntity );
        }
        catch ( JMSException e )
        {
            throw new RuntimeException( e );
        }
    }
}
