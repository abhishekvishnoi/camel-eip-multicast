package com.lizard.edu.camel.eip.multicast;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MulticastRoute extends EndpointRouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .maximumRedeliveries(3);

//        from("{{input.file}}")
//                .log(LoggingLevel.DEBUG, log, "New message received")
//                .multicast()
//                .stopOnException()
//                .to("{{output.file.w174}}")
//                .to("{{output.file.w180}}")
//                .end()
//                .log("The files have been sent to all the outbounds via multicast.");


        from(file("{{input.file}}").antExclude("*.tmp").delete(true))
                .log(LoggingLevel.DEBUG, log, "New message received")
                .multicast()
                .stopOnException()
                .to(file("{{output.file.w174}}"))
                .to(file("{{output.file.w180}}"))
                .end()
                .log("The files have been sent to all the outbounds via multicast.");


    }
}
