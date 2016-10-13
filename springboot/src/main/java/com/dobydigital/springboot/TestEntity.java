package com.dobydigital.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "test_entities" )
public class TestEntity implements Serializable
{
    @Id
    @GeneratedValue
    @Column( name = "id" )
    private Integer id;
    @Column( name = "test_string" )
    private String testString;

    public TestEntity()
    {
    }

    public TestEntity( String testString )
    {
        this.testString = testString;
    }

    public String getTestString()
    {
        return testString;
    }

    public void setTestString( String testString )
    {
        this.testString = testString;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }
}