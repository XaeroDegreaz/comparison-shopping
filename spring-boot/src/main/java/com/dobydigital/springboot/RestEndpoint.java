package com.dobydigital.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping( "/test-entity" )
public class RestEndpoint
{
    @Autowired
    private TestEntityRepository testEntityRepository;
    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @PostMapping
    public void create( @RequestBody TestEntity apiTestEntity )
    {
        jmsMessageProducer.send( apiTestEntity );
    }

    @GetMapping
    public Iterable<TestEntity> readAll()
    {
        return testEntityRepository.findAll();
    }

    @GetMapping( "/{id}" )
    public TestEntity readId( @PathVariable Integer id )
    {
        return testEntityRepository.findOne( id );
    }

    @PutMapping( "/{id}" )
    public void update( @PathVariable Integer id, @RequestBody TestEntity apiTestEntity )
    {
        TestEntity testEntity = testEntityRepository.findOne( id );
        testEntity.setTestString( apiTestEntity.getTestString() );
        testEntityRepository.save( testEntity );
    }

    @DeleteMapping( "/{id}" )
    public void delete( @PathVariable Integer id )
    {
        testEntityRepository.delete( id );
    }

    @GetMapping( "/search" )
    public List<TestEntity> search( @RequestParam String testString )
    {
        return testEntityRepository.findByTestString( testString );
    }
}
