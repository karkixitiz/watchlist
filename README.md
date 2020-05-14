### Watchlist Spring Boot MVC
Simple spring boot MVC application in java.

### Learning
    - Using Spring Initializr to bootstrap a new application (https://start.spring.io)
    
    - Build tool maven
    
    - Deployment: Packaging- putting all files to single bundle  executable and put it on server. Deploying.. Three meathods
    
        1) Packing with a fat JAR:
        
        A fat JAR includes compiled code, resources, and a servlet container.
        It is streamlined, without the hassle of installing a web server and deploying the app to a server. 
        
        2) Packaging with a WAR file:
           A WAR file is a compressed file that contains multiple file bundles.
           It requires the installation of a web server, like Tomcat. 
           It used to be a standard practice, but is less popular than using fat JARs. 
           
        3) Using Maven without packaging.
        
    - Spring MVC Architecture
    
    - Watch Movie List (CRU)
    
    - Test Spring MVC Controllers using JUnit & Spring MockMvc: Unit tests for controllers to make sure they perform their basic functionalities
    
    - Field Validation: Standard annotations and custom annotations(adding own annotations in command object)
    
    - Cross-field validation and Cross-record Validation: Class level annotation
    
    - Configure Spring MVC(configure start up .html page)
    
    - Add a custom error page
    
    - Change tomcat server port number
    
    - Single Responsibility Principle: Each class should have one single task
    
    - Three-tier architecture(Presentation, Business logic, Data layer)
    
    - Refactoring: Changes the design of an application without changing its functionality.
    
    - OMDB API: use external API to get IMDb Rating field of movie. http://www.omdbapi.com/?i=tt3896198&apikey=yourkey&t=matrix
    
    - Idea behind depencency injection(DI framework): don't create instance of your dependencies, declare your dependencies and let someone else create instalnce of then and
     pass them to you. Instead of creating new instance of dependencies, define them as constructor parameters.
     
    - Spring dependency framework : beans-Classes are managed, mark with special annotations(@AutoWired,@service)
    
    - Mockito framework and JUnit
    
    - Spring Bean, Bean configuration, spring context/Container
    
    - Dependency Injection  3 Methods Or Spring bean Configuration: XML files, Java code(@bean,@configure) and Annotations(@Service,@Autowired)
    
    -Logback framework:
    
    - Understand log levels: Error -> Warn -> info -> debuge ->trace
    
    - Save logs on file
    
    -Spring Boot Actuator: optimize the performance of endpoint. some endpoints( /beans, /env, /metrics, /httptrace) actuator
    
### Outcomes
    - Create web applications
    - Use Spring boot & Spring MVC
    - Write testable code
    - Monitor app performance
    -  Comprehend Spring ecosystem