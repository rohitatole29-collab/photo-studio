# 📸 Photo Studio Management System

A full-stack Photo Studio Management System built using Java Spring Boot, Thymeleaf, MySQL, Redis, Docker, Jenkins, Nginx, Prometheus, Grafana, and AWS EC2.

The application allows customers to browse galleries, place orders, manage carts, download photos, and enables administrators to manage photos and orders through an admin dashboard.

---

## 🚀 Features

### Customer Features

* Browse photo galleries
* View portfolio
* Add photos to cart
* Checkout and order management
* Download purchased photos
* Contact form
* Responsive UI

### Admin Features

* Secure admin login
* Manage photo gallery
* Upload photos
* Order management
* Dashboard analytics

### DevOps Features

* Dockerized application
* Docker Compose deployment
* Jenkins CI/CD pipeline
* Nginx Reverse Proxy
* AWS EC2 deployment
* Prometheus monitoring
* Grafana dashboards

---

## 🛠 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Thymeleaf

### Frontend

* HTML5
* CSS3
* JavaScript
* Bootstrap

### Database

* MySQL 8
* Redis

### Containerization

* Docker
* Docker Compose

### CI/CD

* Jenkins

### Web Server

* Nginx

### Monitoring

* Prometheus

### Visualization

* Grafana

### Cloud

* AWS EC2

### Version Control

* Git
* GitHub

---

## 📂 Project Structure

```text
src/
├── main
│   ├── java
│   │   └── com/example/studio
│   │       ├── controller
│   │       ├── service
│   │       ├── repository
│   │       ├── entity
│   │       └── config
│   │
│   └── resources
│       ├── templates
│       ├── static
│       └── application.properties
```

---

## 🏗 Architecture

```text
User Browser
      │
      ▼
Nginx Reverse Proxy
      │
      ▼
Spring Boot Application
      │
      ├── MySQL
      └── Redis
```

---

## 🐳 Docker Deployment

### Build Image

```bash
docker build -t photostudioapp .
```

### Run Container

```bash
docker run -p 8080:8080 photostudioapp
```

---

## 🐳 Docker Compose

### Start Services

```bash
docker compose up -d --build
```

### Stop Services

```bash
docker compose down
```

### Check Containers

```bash
docker ps
```

---

## ☁ AWS EC2 Deployment

### Launch EC2

Ubuntu 22.04

Open Ports:

* 22 (SSH)
* 80 (HTTP)
* 3000 (Grafana)
* 9090 (Prometheus)

### Connect

```bash
ssh -i key.pem ubuntu@YOUR_PUBLIC_IP
```

### Clone Repository

```bash
git clone https://github.com/rohitatole29-collab/photo-studio.git

cd photo-studio
```

### Deploy

```bash
docker compose up -d --build
```

---

## 🔄 Jenkins CI/CD Pipeline

### Pipeline Flow

```text
Developer
   │
   ▼
GitHub
   │
   ▼
Webhook
   │
   ▼
Jenkins
   │
   ├── Checkout
   ├── Maven Build
   ├── Docker Build
   ├── Docker Compose Deploy
   └── AWS EC2
```

### Jenkinsfile

```groovy
pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/rohitatole29-collab/photo-studio.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose up -d --build'
            }
        }
    }
}
```

---

## 🌐 Nginx Configuration

```nginx
server {
    listen 80;

    location / {
        proxy_pass http://localhost:8080;

        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

---

## 📊 Monitoring

### Prometheus

Metrics Endpoint:

```text
http://SERVER_IP:9090
```

### Grafana

Dashboard:

```text
http://SERVER_IP:3000
```

Default Login:

```text
Username: admin
Password: admin
```

---

## 📸 Screenshots

Add screenshots of:

* Home Page
* Gallery
* Cart
* Checkout
* Admin Dashboard
* Grafana Dashboard

---

## 📈 Future Enhancements

* Payment Gateway Integration
* Kubernetes Deployment
* SSL/HTTPS
* Email Notifications
* AWS RDS Integration
* AWS S3 Image Storage

---

## 👨‍💻 Author

Rohit Atole

GitHub:
https://github.com/rohitatole29-collab



---

## ⭐ Support

If you found this project useful, please give it a star on GitHub.
