# 🏆 Quiz Leaderboard System

## 📌 Overview

This project is a backend application developed as part of an internship assignment.
It simulates a real-world system where quiz data is fetched from an external API, processed, and used to generate a leaderboard.

The main challenge is handling **duplicate API responses** and ensuring accurate score aggregation.

---

## 🎯 Objective

* Poll the API 10 times
* Collect and process all responses
* Remove duplicate data
* Aggregate scores per participant
* Generate a sorted leaderboard
* Compute total score
* Submit the final leaderboard

---

## ⚙️ Tech Stack

* Java 17+
* Spring Boot
* Maven
* REST APIs

---

## 📂 Project Structure

```
com.neerajteja.quiz_leaderboard
 ├── model
 ├── service
 ├── controller
 └── QuizLeaderboardApplication.java
```

---

## 🔄 Working Flow

1. Call API 10 times with poll values (0–9)
2. Maintain 5-second delay between each request
3. Collect all responses
4. Remove duplicates using:

   ```
   (roundId + participant)
   ```
5. Aggregate scores
6. Sort leaderboard (descending order)
7. Calculate total score
8. Submit result to API

---

## 🚀 How to Run

### ▶️ Using IntelliJ IDEA

1. Open project in IntelliJ
2. Run `QuizLeaderboardApplication.java`
3. Open browser:

   ```
   http://localhost:8080/run
   ```

---

### 💻 Using Terminal

```bash
cd quiz-leaderboard
mvn clean install
mvn spring-boot:run
```

OR

```bash
java -jar target/quiz-leaderboard-0.0.1-SNAPSHOT.jar
```

Then open:

```
http://localhost:8080/run
```

---

## 🔑 Important Notes

* Exactly **10 API calls** must be made
* Maintain **5 seconds delay**
* Duplicate entries must be ignored
* Submit API must be called **only once**

---

## ⚠️ Duplicate Handling Example

Incorrect:

```
Poll 1 → Alice +10  
Poll 3 → Alice +10 again  
Total = 20 ❌
```

Correct:

```
Poll 1 → Alice +10  
Poll 3 → Duplicate → Ignore  
Total = 10 ✅
```

---

## 📊 Sample Output

```
Leaderboard:
Alice -> 100
Bob -> 120

TOTAL SCORE: 220
```

---

## 📡 API Endpoints Used

### GET

```
/quiz/messages?regNo=XXXX&poll=0-9
```

### POST

```
/quiz/submit
```

---

## 🧠 Key Concepts Used

* REST API Integration
* Deduplication using HashSet
* Data aggregation using HashMap
* Sorting using Collections
* Backend system design

---

## 📌 Author

Neeraj Teja

---

## ⭐ Notes

This project demonstrates backend development skills including API handling, data processing, and problem-solving in distributed systems.
