# Trip-reservation report
# Matias Lespiau

This document is part of Croosover tech trial assignment for Software engineering  manager – Java position. Given the time constrainst of three days I reaccomodated the scope of the project as I advanced on the domain modelling and technical discovery.

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

The hotel room functionaliy was implementing having all the requirements in mind and I provide a description on how that functionality could have been implemented and which changes in the design should be done to support it.

