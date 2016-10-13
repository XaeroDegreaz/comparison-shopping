package com.dobydigital.wildflyswarm;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.config.logging.Level;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.messaging.MessagingFraction;
import org.wildfly.swarm.spi.api.StageConfig;

public class Main
{
    public static void main( String[] args ) throws Exception
    {
        Swarm swarm = new Swarm();
        StageConfig stageConfig = swarm.stageConfig();
        swarm.fraction( new LoggingFraction()
                            .applyDefaults( Level.valueOf( stageConfig.resolve( "logger.level" ).getValue() ) ) );
        swarm.fraction( new DatasourcesFraction()
                            .dataSource( "TestPu", ds ->
                            {
                                ds.driverName( "mysql" );
                                ds.userName( stageConfig.resolve( "swarm.ds.username" ).getValue() );
                                ds.password( stageConfig.resolve( "swarm.ds.password" ).getValue() );
                                ds.connectionUrl( stageConfig.resolve( "swarm.ds.connection.url" ).getValue() );
                            } ) );
        /*
        Something is wrong here. I can watch this method work, but after the bootstrapping process starts
        it claims that it cannot find the 'EnhancedServer' class. It's totally there when this fraction
        creation kicks off though.
         */
        swarm.fraction( new MessagingFraction()
                            .defaultServer( s -> s.jmsQueue( TestEntity.class.getName() ) ) );
        swarm.start();
        JAXRSArchive deployment = ShrinkWrap.create( JAXRSArchive.class );
        deployment.addPackage( Main.class.getPackage() );
        deployment.addAsWebInfResource( new ClassLoaderAsset( "META-INF/persistence.xml", Main.class.getClassLoader() ), "classes/META-INF/persistence.xml" );
        deployment.addAllDependencies();
        swarm.deploy( deployment );
    }
}