## 🏦 ATM System LLD

### 📌 Problem Statement

Design and implement an extensible **ATM System** that allows users to withdraw, deposit, and check their account balances through physical ATMs. The system must support multiple banks and ensure secure authentication and transaction handling.

---

### Functional Requirements

1. **User Authentication**

   * Card insertion + PIN validation

2. **Transactions**

   * Withdraw cash (if funds + ATM cash available)
   * Deposit cash (update user account)
   * Check account balance
   * Mini statement (last 10 transactions)

3. **ATM Functionalities**

   * Multiple ATMs supported
   * Refill ATM cash
   * Track ATM cash per denomination

---

### 📦 Non-Functional Requirements
* Scalable to support multiple banks and ATMs
* Extensible: add features like fund transfer, UPI, biometric auth

---

### 🔁 Flow

1. Insert card
2. Authenticate PIN
3. Show menu: Withdraw / Deposit / Balance / Statement
4. Validate business rules (sufficient funds, daily limit, ATM cash)
5. Process and print receipt
6. Eject card

---

### 📤 Extensible Cell Types (Optional Follow-ups)

1. 🔒 Biometric authentication
2. 🔁 Fund transfers
3. 🪙 Currency conversion for international cards
4. 🧾 Printable receipts
5. 📱 SMS/Email alerts
6. 🏦 Support for inter-bank transactions

---
