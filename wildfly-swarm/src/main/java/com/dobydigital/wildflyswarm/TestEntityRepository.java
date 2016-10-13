package com.dobydigital.wildflyswarm;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class TestEntityRepository
{
    @PersistenceContext( unitName = "TestPu" )
    private EntityManager em;

    public Iterable<TestEntity> findAll()
    {
        TypedQuery<TestEntity> query = em.createQuery( "select testEntity from TestEntity testEntity", TestEntity.class );
        return query.getResultList();
    }

    public TestEntity findOne( Integer id )
    {
        return em.find( TestEntity.class, id );
    }

    public TestEntity save( TestEntity testEntity )
    {
        if ( testEntity.getId() == null )
        {
            em.persist( testEntity );
        }
        else
        {
            testEntity = em.merge( testEntity );
        }
        return testEntity;
    }

    public void delete( Integer id )
    {
        TestEntity testEntity = em.getReference( TestEntity.class, id );
        em.remove( testEntity );
    }

    public Iterable<TestEntity> findByTestString( String testString )
    {
        TypedQuery<TestEntity> query = em.createQuery( "select testEntity from TestEntity testEntity where testEntity.testString = :testString", TestEntity.class );
        query.setParameter( "testString", testString );
        return query.getResultList();
    }
}
