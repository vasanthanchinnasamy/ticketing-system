# Ticketing System

This assignment has been implemented using **Spring Boot Framework.**  

**Rabbit MQ** is used for Messaging.  

**PostgresSql** is used for database.

## Tasks

Task 1: Created endpoints for all the requirements (a to i) in [TicketingSystemController.java](https://github.com/vasanthanchinnasamy/ticketing-system/blob/main/src/main/java/com/innovate/controller/TicketingSystemController.java)  

Task 2: When response is added to a ticket using (h) rest end point, a message was produced to rabbit mq.   
        The message was then consumed in [TicketingSystemConsumer.java](https://github.com/vasanthanchinnasamy/ticketing-system/blob/main/src/main/java/com/innovate/consumer/TicketingSystemConsumer.java) and sent email using SENDGRID API.

Task 3: When ticket is created using (a) rest end point, if the assigned to user is empty, ticket is assigned to available user who have least number of tickets assigned.
  
Task 4: A method has been scheduled in [ScheduleConfig.java](https://github.com/vasanthanchinnasamy/ticketing-system/blob/main/src/main/java/com/innovate/configuration/ScheduleConfig.java), which will run at 01.00 a.m everyday.  
        This method will produce message to rabbitmq, which will then consumed by a consumer.
        In consumer tickets whose status are resolved and its statusUpdatedDate are greater than 30 days are changed to closed status.
