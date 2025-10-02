# Creational Design Patterns

Creational patterns focus on object creation mechanisms, increasing flexibility and reuse of existing code.

## Patterns Included

### 1. Singleton Pattern
- **Intent:** Ensure only one instance of a class is created and provide a global access point.  
- **Implementation:** `CacheManager.java`, with `Main.java` demonstrating usage.  
- **Example Use Case:** Managing shared resources like caching, logging, or configuration.

### 2. Builder Pattern
- **Intent:** Separate the construction of a complex object from its representation, allowing different configurations of the same object.  
- **Implementation:** `PC.java` (product class) and `PCBuilder.java` (builder).  
- **Example Use Case:** Building a computer with different configurations (RAM, storage, processor).

---

##  How to Run

# Compile all Java files
javac */src/*.java Main.java

# Run Singleton Example
java Main   # inside Singleton Pattern folder

# Run Builder Example
java PCBuilder   # inside Builder Pattern folder
