** \*=== WarehouseUM ===\***
==============

**Developers:**

(Owner) Rui Oliveira -> rui96pedro AT hotmail DOT com

Tomas Ferreira

José Pereira

Jorge Ferreira

--------------

**0) Abstract**

**1) Implementation highlights**

**1.1) Facade**

**1.1) Communication**

**1.1) Concurrency control**

0) Abstract
==============

This software was created for a practical work in Distributed Systems. Was requested to create a Client-Server Warehouse with some business logic, the Server can handle multiple clients connected with sockets.
Each Client is identified by his Username and password.

** Features: **
- The warehouse as many clients.
- Each client has many task types, each task Type requires a list of Items with is quantity.
- Each client can start many task types as he want to, creating an Task.
- Each task as a State: Waiting - when there are not the required items in the Warehouse, Doing - waiting to Client to end the task
- When the client end is task the items are restored in the Warehouse.
- The Warehouse as many Items and the actual quantity in the warehouse.

1) Implementation highlights
==============

Was used java to the development.


1.1) Facade
==============

Because was pretend to use have the same features in the client and in the server for the administrator. Was created an interface with all the needed features, and this Interface was two implementation: one with direct access to the data (DataFacade), and other with a Client/ClientHandler in the middle (Client).

1.2) Communication
==============

To make the communication easier was created the CommunicationSocket and ComunicationSerializer.
ComunicationSerializer is a serializer / deserializer of objects.
CommunicationSocket is an abstraction to the Socket, and have methods like: sendMessage, and readMessage, after call readMessage also can be used pop argument converted to the correct type (Using the ComunicationSerializer)


1.3) Concurrency control
==============

For the Concurrency control was used: hand made Monitors with ReentrantLock, Lock chaining, Conditions.

2) Schema
==============

![sd1.png](https://bitbucket.org/repo/ByggeX/images/665514226-sd1.png)