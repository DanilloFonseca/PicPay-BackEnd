# PicPay Backend Challenge

## About the Project

This project is my solution for the **PicPay Backend Challenge** ([official repository](https://github.com/PicPay/picpay-desafio-backend)). The challenge consists of developing an API for processing transactions between users while applying best practices in architecture, security, and scalability.

## Technologies Used

- **Java 21**
- **Spring Boot** (Spring Web)
- **H2** (DataBase in memory)
- **Lombok** (to reduce boilerplate code)

## Implemented Features

- **User Registration** (COMMON and MERCHANT Users)
- **Processing Transactions Between Users**
- **Balance and User Type Validation**
- **Payment Authorization via External Service**
- **Transaction Notification via External API**

## Project Structure

```
📦 PicPay-BackEnd
 ┣ 📂 src/main/java/com/fonseca/DesafioBackEnd
 ┃ ┣ 📂 services
 ┃ ┣ 📂 controllers
 ┃ ┣ 📂 domain
 ┃ ┣ 📂 dtos
 ┃ ┗ 📂 repositories
 ┣ 📂 src/test/java/com/fonseca/DesafioBackEnd
 ┃
 ┗ 📜 README.md
```

## API Endpoints

| Method | Endpoint         | Description                        |
|--------|-----------------|------------------------------------|
| `POST` | `/users/post`   | Register a new user               |
| `GET`  | `/users/get`    | Retrieve registered users         |

## License
This project was developed for personal study purposes and has no official affiliation with PicPay.

---

**Author:** Danillo Fonseca  
**Contact:** danillo.fonseca.129@ufrn.edu.br
