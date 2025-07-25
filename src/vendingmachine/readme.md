# 🧃 Vending Machine - Low Level Design (LLD)

This project is a fully modular, extensible **Low-Level Design (LLD)** of a **Vending Machine** implemented in Java. It incorporates multiple design principles and patterns to ensure maintainability, testability, and scalability.

---

## 📌 Problem Statement

Design and implement a vending machine that dispenses items when a user selects a product and makes the required payment. The machine should allow:
- Selecting items
- Accepting payments
- Dispensing products
- Returning change
- Managing inventory

The system should support additional features like cart selection, multi-currency payments, admin operations, and discounts.

---

## ✅ Functional Requirements

- View available items
- Select a product
- Accept cash, card, or wallet/UPI payments
- Dispense the selected item if payment is successful

---

## 🚫 Non-Functional Requirements

- Thread-safe operations to support multiple users simultaneously
- Easy to extend for new currencies or payment methods
- Modular design with SOLID principles
- Strategy pattern for pricing and payment flexibility

---

## 🔁 Follow-Up Features Implemented

| # | Feature | Summary |
|---|---------|---------|
| 1 | **Multi-Currency Support** | Support for INR and USD pricing |
| 2 | **Multiple Payment Methods** | Cash, Card, and UPI/Wallet via Strategy Pattern |
| 3 | **Cart Support** | Select and purchase multiple items at once |
| 4 | **Concurrency Handling** | Thread-safe inventory updates using synchronization |
| 5 | **Admin Monitoring and Refill** | Admin interface for stock refill and sales tracking |
| 6 | **Discounts and Loyalty Programs** | Support for flat discounts and Buy-One-Get-One offers |

---

## 🛠 Design Principles Used

- **Strategy Pattern**: For payments and pricing
- **SOLID Principles**: Separation of concerns and extensibility
- **Interface Segregation**: Payments and pricing logic decoupled

---
