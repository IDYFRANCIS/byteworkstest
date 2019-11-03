# byteworkstest
THIS IS A TEST REST API FOR DEVELOPERS TO ORDER FOR FOOD AND GET NOTIFICATIONS OF THE COST OF THE FOOD ORDERED.
THE FOLLOWING STEPS WILL HELP SET UP THE PROJECT.

(a) After successfully clonning the project, run the code to initialize the application.

(b) Once the code is running, you can access the swagger documentation at "http://localhost:8080/swagger-ui.html#/"

(c) To register as a user on the system, use the "USER REGISTERATION" endpoint on the swagger documentation and fill all the 
    required fields.
    
(d) On successful registeration, an email is sent to the user with an "Activation code" generated from from the system.
    Copy this activation code and head over to the verification endpoint and paste the activation code as well as provide your
    password for login and security access to the system.
    
(e) On completing the verification process, a mail is sent to the user notifying the user of a successful account activation.
(d) Now the user is fully registered and can access the system by loging in through the "Logoin Endpoint" on the swagger documentation.

(f) On login, an access token is generated. Copy the token and head on to the "Developers endpoint" for registration as a developer
    on the system so you can order for food. This registration requires a "UserCode" generated for each user on account activation.
    
(g) At the developer's endpoint, provide your system generated "userCode" and activate your account as a developer. On completion of 
    this, a "developerCode" is generated for you and an email is sent to the user containing the DeveloperCode which will be used for 
    placing order for food.
    
(h) Now you can visit the "Order Endpoint" with your developer's code and place an order for food by selecting the "food type", 
    "payment Type", "delivery method", and inputting the quantity of food you intend to order. On completion of the order process,
    a total cost for the food ordered is calculated and sent through mail to the developer and the food vendor as well will get notified 
    of a new order placed for food by a developer.
    
(i) As a food vendor, you can filter through different orders by different parameters like the food type, delivery method, payment type
    as well as through the "Order Number" of an order.
    
    This application is also hosted on "HEROKU PLATFORM" and can be access through this url:
    https://byteworkstest-api.herokuapp.com/swagger-ui.html#/
