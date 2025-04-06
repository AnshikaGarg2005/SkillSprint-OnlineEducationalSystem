#SkillSprint-OnlineEducationalSystem
![image](https://github.com/user-attachments/assets/76c16c75-7254-4c15-a3c0-4c7ca2a9636a)

🎓 Online Education System - Skill-Sprint

Skill-Sprint is a Java-based console application simulating an online education platform. It enables users to sign up as Customers or Providers, enroll in and manage courses, and allows Admins to monitor and moderate the platform. This system is fully implemented using Object-Oriented Programming principles.

🚀 Features Overview

Provider Module
Sign Up / Log In as a course provider.
Upload Courses with details like course ID, name, duration, and description.
View Uploaded Courses.
Edit / Delete your courses.
Track Reports: View the number of reports on each course.

Customer Module
Sign Up / Log In as a customer.
Browse All Courses uploaded by providers.
Search Courses by keyword.
Buy Courses and store them in personal subscriptions.
View Subscribed Courses.
Report Courses if inappropriate or misleading.

Admin Module
Log In as a admin.
View Provider Details: List all registered course providers.
Delete Reported Courses: Remove courses with high report counts.
Ban Providers: Automatically flag and ban providers who exceed a report threshold.
View Analytics:
  Total number of providers, customers, and admins.
  Total number of courses.
  Course reports with course name and report count.

![image](https://github.com/user-attachments/assets/399afca2-b131-4a66-90ab-17386416d27f)


logins.txt: Stores user credentials and role info.
Course.txt: Contains all course details.
reports.txt: Stores course ID, number of reports, and provider ID.
buy.txt: Records courses bought by customers.
data.txt: Backup file used for displaying course names in reports.


📈 Report & Ban Logic
Courses can be reported by customers.
If a course receives more than 3 reports, its provider is banned.
Banned providers are marked with | BANNED in the logins.txt file.


🧑‍💻 Getting Started
Compile all .java files.
Run the main classes:
Signup.java (for user login/signup)
Admin.java (for admin dashboard)
Ensure the required text files are in the root or correct directory (logins.txt, Course.txt, etc.).

🛠 Technologies Used
Java (OOP Principles)
File Handling for storage
CLI (Command Line Interface)

🌟 Future Improvements
Migrate from text-based storage to a relational database.
Add GUI using Swing..
Introduce course ratings and reviews.

🤝Contributors
Project developed for the Object-Oriented Programming (OOP) course.
Developed with dedication by:
[Dhruv Thakur]
[Parth Gupta]
[Anshika Garg]
[Shreya Gupta]


