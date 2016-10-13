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
}
