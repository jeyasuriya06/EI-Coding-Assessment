# Behavioural Design Patterns

Behavioural patterns focus on communication between objects, making interactions more flexible.


## Patterns Included

### 1. Observer Pattern
- **Intent:** Define a one-to-many dependency so when one object (Subject) changes state, all its dependents (Observers) are notified automatically.  
- **Implementation:**  
  - Subject: `StockExchange.java`  
  - Observers: `RetailTrader.java`, `InstitutionalTrader.java`  
  - Interface: `Trader.java`  
  - Runner: `Main.java`  
- **Example Use Case:** Stock price updates sent to multiple traders.

### 2. Template Method Pattern
- **Intent:** Define the skeleton of an algorithm in a base class but let subclasses override specific steps.  
- **Implementation:**  
  - Abstract Template: `OrderProcessTemplate.java`  
  - Concrete Subclasses: `OnlineOrder.java`, `CODOrder.java`  
  - Runner: `Main.java`  
- **Example Use Case:** Order processing where payment/delivery methods differ but structure remains same.


## How to Run

# Compile all Java files
javac */src/*.java Main.java

# Run Observer Example
java Main     #inside Observer Pattern folder

# Run Template Method Example
java Main     #inside Template Method Pattern folder
