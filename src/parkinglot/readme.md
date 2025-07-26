# 🅿️ Parking Lot - Low Level Design (LLD)

Design and implement a Parking Lot system that manages vehicle parking operations. The system should support multiple types of vehicles, parking slots, and user interactions such as entry, parking, exit, and payment.

---

## 📌 Problem Statement

A company wants to build a parking lot system that can:

- Allow vehicles to enter and occupy an appropriate slot
- Track the time spent in the lot
- Charge based on duration and vehicle type
- Manage slot availability across multiple floors
- Support different types of slots (compact, large, electric, etc.)
- Allow vehicle exit after successful payment
- Support large-scale operations like shopping malls, airports, or corporate campuses
- Allow users to book slots in advance (up to 3 days before arrival)

---

## ✅ Functional Requirements

- Entry panel for vehicles to enter and get a parking ticket
- Exit panel or self-payment machine for calculating charges and completing payment
- Different vehicle types: bike, car, truck, EV, etc.
- Different slot sizes: small, medium, large, electric, etc.
- Multi-floor parking structure with level-wise availability
- Generate and manage parking tickets
- Display available slots by type and level in real time
- Calculate parking charges based on vehicle type and duration
- Admin panel to add/remove slots, floors, and configure pricing
- **Users can reserve a parking slot up to 3 days in advance**

---

## 🚫 Non-Functional Requirements

- Scalable for large facilities (multi-floor with 1000s of slots)
- Extensible to add new vehicle or slot types without code changes
- Thread-safe for concurrent vehicle entry and exit
- Modular and testable codebase
- Payment can be completed at self-payment machines or exit booths on any floor

---

## 💰 Pricing Requirements

- Configurable hourly base rate per vehicle type (e.g., ₹20/hr for bikes, ₹40/hr for cars)
- Different floors or zones may have different rates
- Minimum billing duration: 1 hour
- Optional grace period (e.g., first 15 minutes free)
- Penalty for overstaying unpaid time
- Extendable to support daily rates, night-time rates, or event-based pricing
- Should support multiple pricing strategies via plug-and-play (strategy pattern)

---

## 📦 Key Entities

- `Vehicle` (with type, license number, entry time, etc.)
- `ParkingSlot` (size, type, level, availability)
- `ParkingLot` (multiple floors and slots)
- `Ticket` (issued at entry, settled at exit or payment machine)
- `Booking` (handles advance reservations)
- `PricingStrategy` (calculates dynamic charges)
- `EntryPanel`, `ExitPanel`, and `PaymentKiosk` (interfaces for interaction)
- `Payment` (method, status, time)
- `AdminPanel` (configure lot, floors, and pricing)

---

## 🛠 Design Patterns to Consider

- **Strategy Pattern** for dynamic pricing
- **Factory Pattern** for vehicle and slot instantiation
- **Singleton Pattern** for the central parking lot instance
- **Observer Pattern** for real-time slot availability display
- **State Pattern** for tracking ticket/payment status

---

## 🔁 Follow-Up Enhancements

1. **Dynamic Pricing Rules**  
   Implement time-based or surge pricing strategies.

2. **Online Booking / Slot Reservation**  
   Allow users to book a slot in advance via app or web (up to 3 days ahead).

3. **Real-Time Slot Monitoring**  
   Use sensors/observers to update slot status in real time.

4. **VIP/Subscription Parking Support**  
   Monthly pass holders or VIP customers get priority slots or discounted rates.

5. **Penalty for Overstaying**  
   If a vehicle overstays its paid duration, charge extra fees.

6. **Electric Vehicle Charging Support**  
   Allocate EV-only slots and add charge tracking.
