package com.dobydigital.wildflyswarm;

import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.spi.api.StageConfig;

public class Main
{
    public static void main( String[] args ) throws Exception
    {
        Swarm swarm = new Swarm();
        swarm.fraction( new DatasourcesFraction()
                            .dataSource( "TestPu", ds ->
                            {
                                StageConfig stageConfig = swarm.stageConfig();
                                ds.driverName( "mysql" );
                                ds.userName( stageConfig.resolve( "swarm.ds.username" ).getValue() );
                                ds.password( stageConfig.resolve( "swarm.ds.password" ).getValue() );
                                ds.connectionUrl( stageConfig.resolve( "swarm.ds.connection.url" ).getValue() );
                            } ) );
        swarm.start();
        swarm.deploy();
    }
}