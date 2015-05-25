# Trip-reservation report
# Matias Lespiau

This document is part of Crossover tech trial assignment for Software engineering  manager – Java position. Given the time constraints of three days I accommodated the scope of the project as I advanced on the domain modeling and technical discovery.

## Scope
From the original requirements, I left aside:
* the possibility of posting, searching and booking full trip programs
* system admin functionality for searching booking history

What is covered:
* authorization for system admins, hotel agents and customers
* hotel agents can post available rooms so that customers can do reservations after
* customers can search available rooms
* customer can reserve an available room
* system admin can view the reservations history for a given room

The hotel room functionality was implementing having all the requirements in mind and I provide a description on how that functionality could have been implemented and which changes in the design should be done to support it.

## Used technologies
For this assignment I tried to use as little libraries as possible while at the same time reflect a real scenario.
I also wanted to discover and experiment modern Java tools. I did not have experience in most of the third libraries used.

For the framework I used the Java spark microframework (http://sparkjava.com/). I've chosen it because it has a nice documentation,
has no configuration boilerplate and it had almost everything that was needed.

I included junit for doing some unit tests. They really helped me out during development but I was not able to do more due to time constrains. I should note that I love unit testing and where I work I coach a 4 hour workshop on unit testing and design every week :).

I chose mysql for database access. I love mysql because it let's you start simple and can be tweaked to support a high amount of queries and transactions.

I started writing hand-sql but switched to jooq because I was running out of time and now I love it.

## Things that I wanted to implement but ran out of time:
* Hotel room search
    * Pagination
    * Location and includesBreakfast filters
* Input validation such as types (integer, dates) and business rules (checkIn < checkOut).
* Hide some values from json serialization (such as our internal ids or data that is not need in some operations)
* Add foreign keys to mysql tables
* Add a transaction for the booking service
* More unit and integration tests
* System admin search

