# Foodies API

A robust Spring Boot REST API designed for an online food delivery application. This API handles core functionalities such as user management, authentication, food item management, shopping cart operations, and order processing, including payment integration.

## Features

*   **User Management:** Register, authenticate, and manage user profiles.
*   **Authentication & Authorization:** Secure API endpoints using JWT (JSON Web Tokens) and Spring Security.
*   **Food Management:** Create, retrieve, update, and delete food items.
*   **Cart Management:** Add, update, and remove items from a user's shopping cart.
*   **Order Processing:** Create and manage food orders.
*   **Payment Integration:** Seamless integration with Razorpay for payment processing.
*   **Cloud Storage:** Integration with AWS S3 for storing images or other static assets.
*   **Data Validation:** Robust input validation for all API requests.

## Technologies Used

*   **Java 17**
*   **Spring Boot** (3.5.6)
*   **Maven**
*   **MongoDB:** NoSQL database for data persistence.
*   **Spring Security:** For authentication and authorization.
*   **JWT (JSON Web Tokens):** For secure API access.
*   **AWS S3 SDK:** For cloud storage operations.
*   **Razorpay Java SDK:** For payment gateway integration.
*   **Lombok:** To reduce boilerplate code.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have the following installed:

*   **Java Development Kit (JDK) 17**
*   **Apache Maven**
*   **MongoDB:** A running instance of MongoDB. You can install it locally or use a cloud-based service like MongoDB Atlas.
*   **AWS Account:** With an S3 bucket configured and appropriate access keys.
*   **Razorpay Account:** With API keys (Key ID and Key Secret).

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/foodiesapi.git
    cd foodiesapi
    ```

2.  **Build the project:**
    ```bash
    mvn clean install
    ```

### Configuration

Create an `application.properties` (or `application.yml`) file in `src/main/resources/` and configure the following properties:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/foodiesdb

# JWT Configuration
application.security.jwt.secret-key=YOUR_SUPER_SECRET_JWT_KEY_HERE
application.security.jwt.expiration=86400000 # 24 hours in milliseconds
application.security.jwt.refresh-token.expiration=604800000 # 7 days in milliseconds

# AWS S3 Configuration
cloud.aws.credentials.access-key=YOUR_AWS_ACCESS_KEY
cloud.aws.credentials.secret-key=YOUR_AWS_SECRET_KEY
cloud.aws.region.static=YOUR_AWS_REGION # e.g., us-east-1
cloud.aws.s3.bucket-name=YOUR_S3_BUCKET_NAME

# Razorpay Configuration
razorpay.key.id=YOUR_RAZORPAY_KEY_ID
razorpay.key.secret=YOUR_RAZORPAY_KEY_SECRET
```
**Note:** Replace placeholder values with your actual credentials and configurations.

### Running the Application

You can run the Spring Boot application using Maven:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## API Endpoints

The API provides various endpoints for managing food delivery operations. Here's a high-level overview of the main categories:

*   `/api/auth`: User registration and authentication.
*   `/api/users`: User-related operations (e.g., profile management).
*   `/api/foods`: CRUD operations for food items.
*   `/api/carts`: Manage items in the shopping cart.
*   `/api/orders`: Create and view orders.
