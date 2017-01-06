# spring-exception


TODO 



The Controller level @ExceptionHandler
La gestion de l'exception est active que pour le controlleur où c'es tdéfini le handler.

HandlerExceptionResolver
Permet d'intercepter les exceptions renvoyé par l'application.
Spring implémente déja des Resolver par défaut qui permettent de simplifier la gestion des exceptions.
DefaultHandlerExceptionResolver : qui permet de transformer les exceptions remonté par spring en http erreur avec le bon code.
ResponseStatusExceptionResolver : permet en ajoutant @ResponseStatus, de mapper l'exception sur un status HTTP code

@ControllerAdvice (depuis spring 3.2)
C'est un global exception handler
*******************************************
    Controller Based – We can define exception handler methods in our controller classes. All we need is to annotate these methods with @ExceptionHandler annotation. This annotation takes Exception class as argument. So if we have defined one of these for Exception class, then all the exceptions thrown by our request handler method will have handled.

    These exception handler methods are just like other request handler methods and we can build error response and respond with different error page. We can also send JSON error response, that we will look later on in our example.

    If there are multiple exception handler methods defined, then handler method that is closest to the Exception class is used. For example, if we have two handler methods defined for IOException and Exception and our request handler method throws IOException, then handler method for IOException will get executed.
    Global Exception Handler – Exception Handling is a cross-cutting concern, it should be done for all the pointcuts in our application. We have already looked into Spring AOP and that’s why Spring provides @ControllerAdvice annotation that we can use with any class to define our global exception handler.

    The handler methods in Global Controller Advice is same as Controller based exception handler methods and used when controller class is not able to handle the exception.
    HandlerExceptionResolver – For generic exceptions, most of the times we serve static pages. Spring Framework provides HandlerExceptionResolver interface that we can implement to create global exception handler. The reason behind this additional way to define global exception handler is that Spring framework also provides default implementation classes that we can define in our spring bean configuration file to get spring framework exception handling benefits.

    SimpleMappingExceptionResolver is the default implementation class, it allows us to configure exceptionMappings where we can specify which resource to use for a particular exception. We can also override it to create our own global handler with our application specific changes, such as logging of exception messages.


***********************************************














Les codes de retours 


Bonnes pratiques API rest

As usual, Spring likes to offer you choice, so what should you do? Here are some rules of thumb. However if you have a preference for XML configuration or Annotations, that’s fine too.

    For exceptions you write, consider adding @ResponseStatus to them.
    For all other exceptions implement an @ExceptionHandler method on a @ControllerAdvice class or use an instance of SimpleMappingExceptionResolver. You may well have SimpleMappingExceptionResolver configured for your application already, in which case it may be easier to add new exception classes to it than implement a @ControllerAdvice.
    For Controller specific exception handling add @ExceptionHandler methods to your controller.
    Warning: Be careful mixing too many of these options in the same application. If the same exception can be handed in more than one way, you may not get the behavior you wanted. @ExceptionHandler methods on the Controller are always selected before those on any @ControllerAdvice instance. It is undefined what order controller-advices are processed. 

Sample Application

A demonstration application can be found at github. It uses Spring Boot and Thymeleaf to build a simple web application.

The application was revised (Oct 2014) and is (hopefully) better and easier to understand. The fundamentals stay the same. It uses Spring Boot V1.1.8 and Spring 4.1 but the code is applicable to Spring 3.x also.

The demo is running on Cloud Foundry at http://mvc-exceptions-v2.cfapps.io/.
About the Demo

The application leads the user through 5 demo pages, highlighting different exception handling techniques:

    A controller with @ExceptionHandler methods to handle its own exceptions
    A contoller that throws exceptions for a global ControllerAdvice to handle
    Using a SimpleMappingExceptionResolver to handle exceptions
    Same as demo 3 but with the SimpleMappingExceptionResolver disabled for comparison
    Shows how Spring Boot generates its error page

A description of the most important files in the application and how they relate to each demo can be found in the project’s README.md.

The home web-page is index.html which:

    Links to each demo page
    Links (bottom of the page) to Spring Boot endpoints for those interested in Spring Boot.

Each demo page contains several links, all of which deliberately raise exceptions. You will need to use the back-button on your browser each time to return to the demo page.

Thanks to Spring Boot, you can run this demo as a Java application (it runs an embedded Tomcat container). To run the application, you can use one of the following (the second is thanks to the Spring Boot maven plugin):

    mvn exec:java
    mvn spring-boot:run

Your choice. The home page URL will be http://localhost:8080.
Spring Boot and Error Handling

Spring Boot allows a Spring project to be setup with minimal configuration. Spring Boot creates sensible defaults automatically when it detects certain key classes and packages on the classpath. For example if it sees that you are using a Servlet environment, it sets up Spring MVC with the most commonly used view-resolvers, hander mappings and so forth. If it sees JSP and/or Thymeleaf, it sets up these view-technologies.

Spring MVC offers no default (fall-back) error page out-of-the-box. The most common way to set a default error page has always been the SimpleMappingExceptionResolver (since Spring V1 in fact). However Spring Boot does provide for a fallback error-handling page.

At start-up, Spring Boot tries to find a mapping for /error. By convention, a URL ending in /error maps to a logical view of the same name: error. In the demo application this view maps in turn to the error.html Thymeleaf template. (If using JSP, it would map to error.jsp according to the setup of your InternalResourceViewResolver).

If no mapping from /error to a View can be found, Spring Boot defines its own fall-back error page - the so-called “Whitelabel Error Page” (a minimal page with just the HTTP status information and any error details, such as the message from an uncaught exception). If you rename the error.html template to, say, error2.html then restart, you will see it being used.

By defining a Java configuration @Bean method called defaultErrorView() you can return your own error View instance. (see Spring Boot’s ErrorMvcAutoConfiguration class for more information).

What if you are already using SimpleMappingExceptionResolver to setup a default
error view? Simple, make sure the defaultErrorView defines the same view that Spring Boot uses: error. Or you can disable Spring boot’s error page by setting the property
server.error.whitelabel.enabled to false (note: this property has been renamed from error.whitelabel.enabled since I wrote this blog). Your container’s default error page is used instead.

Spring Boot properties are normally set in application.properties or application.yml. Alternatively, you can set them in code - see Main for an example.

Note that in the demo, the defaultErrorView property of the SimpleMappingExceptionResolver is deliberately set not to error but to defaultErrorPage so you can see when the handler is generating the error page and when Spring Boot is responsible. Normally both would be set to error.

Also in the demo application I show how to create a support-ready error page with a stack-trace hidden in the HTML source (as a comment). Ideally support should get this information from the logs, but life isn’t always ideal. Regardless, what this page does show is how the underlying error-handling method handleError creates its own ModelAndView to provide extra information in the error page. See:

    ExceptionHandlingController.handleError() on github
    GlobalControllerExceptionHandler.handleError() on github

    All Posts
    Engineering
    Releases
    News and Events






Mais ceci n'est pas suffisant vu qu'on a besoin de plus de détails lorsqu'on a une exception de typpe 400-Bad Request.
L'idée est d'enrechir le body de la reponse pour envoyer plus de détails

ErrorInfo (retour en json ou xml @XmlElement)


References
https://github.com/jirutka/spring-rest-exception-handler

https://gist.github.com/matsev/4519323
https://blog.jayway.com/2013/02/03/improve-your-spring-rest-api-part-iii/




https://github.com/cosmic-cowboy/spring-boot-exception-handling/tree/master/spring-boot-exception-handling/src/main/java/com/slgerkamp/introductory/spring/boot/exceptions/api
http://www.ekiras.com/2016/02/how-to-do-exception-handling-in-springboot-rest-application.html

https://github.com/john-mcpeek/spring-exception-handling/blob/master/src/main/java/io/volcanolabs/core/exceptions/GlobalExceptionHandler.java
