Example Project for publish events in a broker.  I used springboot and communicate with AmazonMQ. 


  I created a little project using springboot to populate the three queues in AmazonMQ. 
  Each queue is for a event Delivery, Adjustment and Bonus.
  [courrierPublisher](https://github.com/peryclesjr/courrierPublisher)
  - To run the project you need to configure the application.properties with the information of your AmazonMQ.
    - The project is a maven project and you need to run the following command in terminal:
    ``` mvn clean install ```
  - To run the project you need to run the following command in terminal:
    ``` mvn spring-boot:run ```
  - The project is running in the port 8080.
  - The project has 1 endpoints:
    - /delivery
