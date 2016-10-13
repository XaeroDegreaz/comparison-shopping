package com.dobydigital.wildflyswarm;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path( "/test-entity" )
@Produces( "application/json" )
public class RestEndpoint
{
    @Inject
    private TestEntityRepository testEntityRepository;
    @Inject
    private JmsMessageProducer jmsMessageProducer;

    @POST
    @Path( "/" )
    public void create( TestEntity apiTestEntity )
    {
        jmsMessageProducer.send( apiTestEntity );
    }

    @GET
    @Path( "/" )
    public Iterable<TestEntity> readAll()
    {
        return testEntityRepository.findAll();
    }

    @GET
    @Path( "/{id}" )
    public TestEntity readId( @PathParam( "id" ) Integer id )
    {
        return testEntityRepository.findOne( id );
    }
}