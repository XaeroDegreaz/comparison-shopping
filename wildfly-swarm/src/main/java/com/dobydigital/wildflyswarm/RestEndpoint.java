package com.dobydigital.wildflyswarm;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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

    @PUT
    @Path( "/{id}" )
    public void update( @PathParam( "id" ) Integer id, TestEntity apiTestEntity )
    {
        TestEntity testEntity = testEntityRepository.findOne( id );
        testEntity.setTestString( apiTestEntity.getTestString() );
        testEntityRepository.save( testEntity );
    }

    @DELETE
    @Path( "/{id}" )
    public void delete( @PathParam( "id" ) Integer id )
    {
        testEntityRepository.delete( id );
    }

    @GET
    @Path( "/search" )
    public Iterable<TestEntity> search( @QueryParam( "testString" ) String testString )
    {
        return testEntityRepository.findByTestString( testString );
    }
}