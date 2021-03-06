[![Build Status](https://travis-ci.org/andyglick/spring-statemachine-cdplayer-sample.svg?branch=master)](https://travis-ci.org/andyglick/spring-statemachine-cdplayer-sample)
[![Coverage Status](https://coveralls.io/repos/github/andyglick/spring-statemachine-cdplayer-sample/badge.svg)](https://coveralls.io/github/andyglick/spring-statemachine-cdplayer-sample)
[![DepShield Badge](https://depshield.sonatype.org/badges/andyglick/spring-statemachine-cdplayer-sample/depshield.svg)](https://depshield.github.io)

Spring Statemachine Sample - cdplayer
=====================================
 
Was taken by surprise when learning that the Spring Statemachine project
even existed. Had difficulties attempting to instantiate a statemachine
when using @Inject following the 1st examples in the docs. Got the
distribution and Mavenized an example -- voila voila.

I thought that the cdplayer state machine looked interesting. Have a
look at the StateChart at *src/main/resources/statechartmodel.txt*.  

Another potentially interesting aspect of this is that I have now
explored the issues around configuring slf4j output into 3 modes,
settable via Maven profiles. With both of the profiles turned off, there
is no logging. As you might expect, the log-info profile produces INFO
level logging, and the log-debug-profile produces DEBUG level logging.
There is no logger configuration file. Changing the classpath via the
profiles is all that you need to get the differing behaviors. 

The log-info profile is enabled by default, as the execution of the
statemachine produces a whole lot of messages that a curious person
might want to look at.

Disable info logging by adding "-P !log-info" to the maven command line.

My gradle skills are nearly non-existent. I know Maven pretty well. So I
constructed a Maven build.
