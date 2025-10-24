# Foodies API

This is a Spring Boot REST API for an online food delivery application.

## Description

This API provides the backend services for a food delivery application. It includes functionalities for user authentication, managing food items, handling user carts, and processing orders with payments.

## Technologies Used

*   **Framework:** Spring Boot
*   **Language:** Java 17
*   **Database:** MongoDB
*   **Authentication:** Spring Security with JWT
*   **Payment Gateway:** Razorpay
*   **File Storage:** AWS S3
*   **Build Tool:** Maven

## API Endpoints

### Authentication

*   `POST /api/login`: Authenticate a user and get a JWT token.

    **Request Body:**
    ```json
    {
        "email": "user@example.com",
        "password": "password"
    }
    ```

    **Response Body:**
    ```json
    {
        "email": "user@example.com",
        "token": "<JWT_TOKEN>"
    }
    ```

### Users

*   `POST /api/register`: Register a new user.

    **Request Body:**
    ```json
    {
        "name": "John Doe",
        "email": "john.doe@example.com",
        "password": "password"
    }
    ```

    **Response Body:**
    ```json
    {
        "id": "<USER_ID>",
        "name": "John Doe",
        "email": "john.doe@example.com"
    }
    ```

### Foods

*   `POST /api/foods`: Add a new food item with an image.

    **Request:**

    *   This is a multipart request.
    *   Part 1 (food): A JSON object with the following structure:
        ```json
        {
            "name": "Pizza",
            "description": "Delicious pizza",
            "price": 10.99,
            "category": "Italian"
        }
        ```
    *   Part 2 (file): The food image file.

    **Response Body:**
    ```json
    {
        "id": "<FOOD_ID>",
        "name": "Pizza",
        "description": "Delicious pizza",
        "price": 10.99,
        "category": "Italian",
        "imageUrl": "<IMAGE_URL>"
    }
    ```

*   `GET /api/foods`: Get a list of all food items.
*   `GET /api/foods/{id}`: Get a specific food item by its ID.
*   `DELETE /api/foods/{id}`: Delete a food item by its ID.

### Cart

*   `POST /api/cart`: Add an item to the cart.

    **Request Body:**
    ```json
    {
        "foodId": "<FOOD_ID>"
    }
    ```

*   `GET /api/cart`: Get the user's cart.
*   `DELETE /api/cart`: Clear the user's cart.
*   `POST /api/cart/remove`: Remove an item from the cart.

    **Request Body:**
    ```json
    {
        "foodId": "<FOOD_ID>"
    }
    ```

### Orders

*   `POST /api/orders/create`: Create a new order and process payment.

    **Request Body:**
    ```json
    {
        "orderedItems": [
            {
                "foodId": "<FOOD_ID>",
                "quantity": 1,
                "price": 10.99,
                "category": "Italian",
                "imageUrl": "<IMAGE_URL>",
                "name": "Pizza",
                "description": "Delicious pizza"
            }
        ],
        "userAddress": "123 Main St",
        "amount": 10.99,
        "orderStatus": "Pending",
        "phoneNumber": "1234567890",
        "email": "user@example.com"
    }
    ```

*   `POST /api/orders/verify`: Verify the payment for an order.
*   `GET /api/orders`: Get the orders for the current user.
*   `DELETE /api/orders/{orderId}`: Delete an order by its ID.
*   `GET /api/orders/all`: Get all orders (for admin).
*   `PATCH /api/orders/status/{orderId}`: Update the status of an order (for admin).

## Getting Started

### Prerequisites

*   Java 17
*   Maven
*   MongoDB
*   AWS S3 Bucket
*   Razorpay Account

### Configuration

1.  Open the `application.properties` file in `src/main/resources` and update the following properties with your credentials:

    ```properties
    # MongoDB Configuration
    spring.data.mongodb.uri=<YOUR_MONGODB_URI>

    # AWS S3 Configuration
    aws.accessKeyId=<YOUR_AWS_ACCESS_KEY>
    aws.secretKey=<YOUR_AWS_SECRET_KEY>
    aws.s3.bucket.name=<YOUR_AWS_S3_BUCKET_NAME>

    # Razorpay Configuration
    razorpay.key.id=<YOUR_RAZORPAY_KEY_ID>
    razorpay.key.secret=<YOUR_RAZORPAY_KEY_SECRET>

    # JWT Configuration
    jwt.secret.key=<YOUR_JWT_SECRET_KEY>
    ```

### Installation

1.  Clone the repository:
    ```sh
    git clone https://github.com/your-username/foodiesapi.git
    ```
2.  Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Application

```sh
mvn spring-boot:run
```

### Running Tests

```sh
mvn test
```

## Usage

You can use a tool like Postman or curl to interact with the API.
<img width="1440" height="900" alt="Screenshot 2025-10-24 at 10 33 51 PM" src="https://github.com/user-attachments/assets/44dd2811-a86d-4eb7-9684-24519c2cf8a2" />
