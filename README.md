# ChatApp – Complete PoE (Parts 1, 2 & 3)

**PROG5121 Proof of Portfolio**  
A fully‑featured console‑based chat application with user registration, login, messaging, and advanced message management using parallel arrays and JSON persistence.

---

##  Project Overview

This application demonstrates a complete chat system with:

- **Part 1 – Registration & Login**  
  - Username validation (underscore, ≤5 chars)  
  - Password complexity (≥8 chars, uppercase, digit, special)  
  - South African cell number validation (international code `+27` + 9 digits)  
  - Unit tests for all validation rules  

- **Part 2 – Sending Messages**  
  - Post‑login chat menu (“Welcome to QuickChat”)  
  - Auto‑generated 10‑digit Message ID and Message Hash  
  - Recipient validation (reuse Part 1 method)  
  - Message length check (≤250 characters)  
  - Options: Send / Store / Disregard  
  - JSON persistence (messages.json)  

- **Part 3 – Store Data & Display Report**  
  - Parallel arrays for sent, stored, disregarded messages, hashes, IDs, recipients  
  - Load stored messages from JSON file into an array  
  - Stored Messages menu with:  
    - Display sender & recipient of all stored messages  
    - Show longest stored message  
    - Search by Message ID  
    - Search all messages for a particular recipient  
    - Delete a message by its Message Hash  
    - Full report (hash, recipient, message)  

All functionality is backed by **JUnit 5 unit tests** (including the test data provided in the specification) and **GitHub Actions** for continuous integration.

---

##  Technologies

- Java 17  
- Maven  
- JUnit 5  
- JSON‑simple (for JSON file handling)  
- Git & GitHub (feature branches, at least 6 commits per part)  
- GitHub Actions (automatic testing on push)

---

##  Getting Started

### Prerequisites
- JDK 17 or later  
- Maven (or use the Maven wrapper)  
- Git (optional, for cloning)

### Clone the repository
```bash
git clone https://github.com/lulama23/ChatAppPart1POE.git
cd ChatAppPart1POE
