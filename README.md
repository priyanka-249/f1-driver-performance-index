# F1 Driver Performance Index (DPI)

A full-stack application that ranks Formula 1 drivers using a composite performance metric computed from real race and standings data.

---

## Overview

The Driver Performance Index (DPI) provides a holistic driver ranking by combining:
- Season performance (points)
- Championship consistency
- Race completion reliability

All scores are computed dynamically — no static rankings or hardcoded values.

---

## DPI Formula

DPI is calculated as:

Performance Score = total points  
Consistency Score = (21 − final position) × 5  
Reliability Score = (races finished / total races) × 100  

DPI = Performance + Consistency + Reliability

---

## Architecture

React Frontend  
→ Spring Boot REST API  
→ Service Layer (aggregation & ranking)  
→ External F1 APIs

---

## Tech Stack

Backend: Java, Spring Boot  
Frontend: React, JavaScript  
Data: External F1 APIs

---

## Running the Project

Backend:
./mvnw spring-boot:run  
API: http://localhost:8080/drivers

Frontend:
cd f1dpi-frontend  
npm install  
npm start  
App: http://localhost:3000

---

## Focus Areas

- Real-world API aggregation
- Metric design and explainability
- Backend–frontend data contracts
- Extendable service-layer logic
