package com.dobydigital.wildflyswarm;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath( "/" )
public class RestApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        return new HashSet<>( Arrays.asList( RestEndpoint.class ) );
    }
}