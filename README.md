# ChatApp – Part 1: Registration and Login

**PROG5121 Proof of Portfolio (PoE)**  
A console‑based user registration and login system, built with **Java** and **Maven**, following clean code and test‑driven principles.

---

##Features

- **User Registration**  
  - Validate username (must contain `_` and be ≤5 characters)  
  - Validate password (≥8 chars, one capital, one number, one special character)  
  - Validate South African cell phone number (must start with `+27` followed by 9 digits)  

- **User Login**  
  - Verify stored username and password  
  - Display personalised welcome message on success  

- **Automated Unit Tests**  
  - 14 JUnit 5 tests covering all validation and login logic  
  - Tests match the exact specification tables (assertEquals / assertTrue / assertFalse)

---

##Technologies

- **Java 17**  
- **Maven** (build automation)  
- **JUnit 5** (unit testing)  
- **Git** (version control, hosted on GitHub)

---

##Getting Started

### Prerequisites
- JDK 17 or later  
- Maven (or use the Maven wrapper included)  
- Git (optional, for cloning)

### Clone the Repository
```bash
git clone https://github.com/your-username/ChatAppPart1PoE.git
cd ChatAppPart1PoE
