# Todo List Rest Service

This is a playground for Hexagonal Architecture / DDD / CQRS using Kotlin and Spring Boot (WIP).

Idea is to strictly separate adapters from domain by using sub-projects. All Adapter depend on their specific 
infrastructure configuration only.

## /domain
This sub-project contains all domain / business logic. It is intended that it does not depend on any framework.

## /rest
This sub-project contains an adapter that represents Rest interface to the driver port. Implemented in Spring HATEOS 
sporting CQRS (Command-query responsibility segregation) approach. This said there is no event bus in place. Command and 
query resolution is implemented as simple as possible maintaining decoupling controller from command and query handlers.
This may change later in favor of Axios.
 
## /storage
This sub-project implements a simple adapter that stores application data in a H2 in memory DB. It includes repositories 
and the configuration. 

## /main
Basically just the main class.
