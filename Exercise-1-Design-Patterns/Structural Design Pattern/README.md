
##  **Structural Design Pattern/README.md**
# Structural Design Patterns

Structural patterns focus on the composition of classes and objects to form larger structures while keeping them flexible and efficient.


##  Patterns Included

### 1. Decorator Pattern
- **Intent:** Dynamically add responsibilities to objects without modifying their code.  
- **Implementation:** 
  - Core: `Text.java`, `PlainText.java`  
  - Decorators: `BoldDecorator.java`, `ItalicDecorator.java`, `UnderlineDecorator.java`  
  - Runner: `Main.java`  
- **Example Use Case:** Applying different text styles at runtime.

### 2. Proxy Pattern
- **Intent:** Provide a surrogate or placeholder for another object to control access to it.  
- **Implementation:** 
  - `Image.java` (interface)  
  - `RealImage.java` (actual heavy object)  
  - `Proxy.java` (lazy loading / access control)  
  - `Main.java` demonstrates image loading.  
- **Example Use Case:** Loading large images only when needed.



##  How to Run

# Compile all Java files
javac */src/*.java Main.java

# Run Decorator Example
java Main   # inside Decorator Pattern folder

# Run Proxy Example
java Main   # inside Proxy Pattern folder
