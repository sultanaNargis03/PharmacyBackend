
# PharmacyManagementAppBackend

This repository contains the backend code for a pharmacy app, designed to facilitate order medicine. Built with Spring Boot and integrated with MySQL, it supports full CRUD operations.


## Features

- Create, Read, Update, and Delete Medicine records 
- Integration with MySQL for data persistence
- Mail Integration
- RESTful API endpoints


## Prerquisite
-  Java JDK 17 or later
- Maven 3.6 or later
- MySQL
## Setup
1. Clone the repository:

git clone 
    https://github.com/sultanaNargis03/PharmacyBackend.git

2. Navigate to the project directory:

    cd PharmacyBackend

3. Configure MySQL connection in src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql:///your_database_name
spring.datasource.username=your username
spring.datasource.password=your password

4. Build and run the application:

    mvn spring-boot:run
## Usage
The application exposes RESTful endpoints:

1. AuthController
- POST/api/auth/login -for login
- POST/api/auth/register -for register

2. MedicineController
- POST/api/medicine - Add medicine
- GET/api/medicine - Retrieve all medicines
- GET/api/medicine/{id} - Retrieve a medicine by ID
- GET/api/getmedicine/{medicineName} - Retrieve a medicine by  MEDICINE NAME
- PUT/api/update-medicine/{id} - Update a medicine by ID
- DELETE/api/medicine/{id} - Delete a medicine by ID
3. CartController
- POST/api-cart/cart/{medicineName} - Add to cart
- GET/api-cart/cart - Retrieve all carts
- DELETE/api-cart/cart/{id} - Delete a cart by ID
4. CheckoutController
- GET/api-order/checkout - Checkout
- GET/api-order/list - Retrieve all orders

5. RoleController
- POST/api-role/role - Create new role
## Contributing
Contributions are welcome! Please feel free to submit a pull request.