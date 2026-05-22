# ChatApp – Part 2: Sending Messages

**PROG5121 Proof of Portfolio (PoE)**  
Console‑based messaging system built on top of the registration & login feature from Part 1.

---

##  New Features in Part 2

- **Post‑login Messaging Menu** – “Welcome to QuickChat”
- **Send Messages** with:
  - Auto‑generated **10‑digit Message ID**
  - Auto‑incremented **Message Number** (per message entered)
  - Recipient cell number validation (South African `+27` format)
  - Message text **≤250 characters** (with error message if exceeded)
  - Auto‑generated **Message Hash** – format: `first2IDdigits:messageNumber:firstwordlastword` (all caps)
- **Send / Store / Disregard** options when a message is ready:
  - *Send* → marks as sent, stores in `messages.json` (JSON)
  - *Store* → saves without sending
  - *Disregard* → user cancels
- **Display full message** after sending: ID, Hash, Recipient, Message
- **Total messages sent** counter (accumulates across all runs – static variable)
- **JSON persistence** – each message saved to `messages.json` with status (`SENT` or `STORED`)
- **Unit tests** (JUnit 5) for all core logic
- **GitHub Actions** – automated test runner on every push

---

##  Technologies

- Java 17
- Maven
- JUnit 5
- JSON.simple (for file storage)
- Git & GitHub (feature branch `feature/messaging`)
- GitHub Actions (CI)

---

##  Getting Started (Part 2)

### Prerequisites
- Part 1 code (Registration & Login) already working
- Java 17, Maven, Git
- (Optional) GitHub Desktop

### Clone & switch to feature branch
```bash
