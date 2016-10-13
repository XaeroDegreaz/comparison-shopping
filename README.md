These test projects test REST and JMS using only the dependencies from their Fractions (Wildfly Swarm)  / Starters (Spring Boot)

The test is a basic CRUD application. For create operations, we use JMS to pass the payload from the REST controller to a JMS message consuming bean.
For all other operations we talk to a repository service directly from the REST controller.

As a JavaEE engineer coming from a TomEE background I found getting this project working on Wildfly extremely time consuming when working strictly from their provided documentation.
The most frustrating period of time came from working to get JMS working properly.

Apparently you can't trust the project generator to provide you with a good setup that will allow JaxRS + CDI + JPA + JMS to work together. By default, the generator provides you 
with a `<packaging>war</packaging>` setup that outputs and uberjar. This doesn't work if you want to use JMS as it fails on startup with errors about some `EnhancedServer` class not
being found by the classloader after deployment. The solution is to change the packaging to a jar, and shrinkwrap. However if you're using JPA you have to also
manually shrinkwrap your `persistence.xml` and copy it to the appropriate place otherwise the persistence unit can't be found.

If you're just using JaxRS + CDI + JPA, then it's not really that bad to set up. The biggest let down is the documentation showing that you can configure your applications with `YAML`,
but finding out that you will still have to code that crap in your `main()` bootstrap method.

Creating this project using Spring Boot was just stupidly fast, and I never had a roadblock. The documentation was spot on at all times, and it just worked right out of the box.
There's a lot I could say about my experience with Spring Boot on this project, but you can just look at the code and see how much more straight-forward it is to put together than Wildfly.


Some stats:

| Container     | Jar Size | Boot Time                              | RAM @ GC | Idle RAM Growth |
|---------------|----------|----------------------------------------|----------|-----------------|
| Wildfly Swarm | 108M     | 33 seconds (console output claims 4.9!) | 40M      | Pretty Fast     |
| Spring Boot   | 31M      | 8 seconds                              | 30M      | Pretty Slow     |
|               |          |                                        |          |                 |

Of course the RAM stuff may not mean that much because GC will handle that stuff if it grows too big anyhow. The biggest thing on this list to me is the load-time discrepancy
for Wildfly Swarm.

