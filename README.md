<h1 align="center">🎓 SkillSprint: Online Educational System</h1>

<p align="center">
  A Java-based educational platform that enables <strong>Admins</strong>, <strong>Providers</strong>, and <strong>Customers</strong> to interact in a mini-LMS environment.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-EDUCATION-blue?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-Database-00758F?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge"/>
</p>

---

## 🌟 Overview

SkillSprint is a **Java + Swing + MySQL** application that replicates a mini learning management system (LMS). It features role-based access where:

- **Admins** manage courses, providers, and reports.
- **Providers** create and edit their own courses.
- **Customers** explore, subscribe, and track their enrolled courses.

> A perfect blend of database integration, GUI, file-handling, and object-oriented programming.

---

## 🧩 Features

### 👩‍💼 Admin Panel
- ✅ Add/Delete Courses
- 🚫 Ban Providers
- 📈 View platform-wide reports
- 👥 Manage user credentials

### 📦 Provider Panel
- 🧑‍🏫 Create and manage courses
- 📊 View analytics on subscriptions
- ✏️ Modify or delete existing listings

### 👨‍🎓 Customer Panel
- 🔍 Explore available courses
- 📝 Subscribe to them easily
- 📑 View report of enrolled courses

---

## 🛠 Tech Stack

| Category         | Technology                |
|------------------|---------------------------|
| Language         | Java (JDK 17+)            |
| GUI              | Java Swing (for forms)    |
| Database         | MySQL + JDBC              |
| Build Tool       | Manual + IntelliJ/VS Code |
| Data Handling    | `.txt` file I/O, JDBC     |
| Version Control  | Git & GitHub              |

---

## 🗂️ Project Structure

```bash
SkillSprint-OnlineEducationalSystem/
├── src/
│   └── admin/
│       ├── Admin.java
│       ├── ProviderDetails.java
│       ├── DeleteCourse.java
│       └── ...
├── lib/                      # MySQL connector JAR
├── .idea/                    # IntelliJ project config
├── .gitignore
├── README.md
├── Course.txt, logins.txt    # File I/O data
└── mysql-connector-j-9.x.x.jar


# 1. Clone the Repository
git clone https://github.com/AnshikaGarg2005/SkillSprint-OnlineEducationalSystem.git

# 2. Navigate
cd SkillSprint-OnlineEducationalSystem

# 3. Compile
javac src/admin/OnlineEducationSystem.java

# 4. Run
java admin.OnlineEducationSystem



🔐 Sample Login Credentials areStored in logins.txt

💡 Highlights

🧠 Object-Oriented Design
🗄 Hybrid Storage (Database + Files)
💻 Console-based & GUI elements
📊 Admin analytics & user roles
📁 Easily extendable architecture
🌱 Future Enhancements

🔄 Full GUI using JavaFX or Swing
🌐 Web version using Spring Boot
📱 Android app using Java/Kotlin
⭐ Star-rating & feedback system
🔐 Login encryption & validation
📄 License

This project is licensed under the MIT License

<p align="center"> <img src="https://forthebadge.com/images/badges/made-with-java.svg" /> <img src="https://forthebadge.com/images/badges/powered-by-coffee.svg" /> </p> ```

