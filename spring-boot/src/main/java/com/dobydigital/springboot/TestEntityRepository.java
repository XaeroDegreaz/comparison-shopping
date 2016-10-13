package com.dobydigital.springboot;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TestEntityRepository extends CrudRepository<TestEntity, Integer>
{
    //@Query("select e from TestEntity e where e.testString = ?1")
    List<TestEntity> findByTestString( String testString );
}
