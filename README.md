# Techventory – IT Inventory Management System

Techventory is a Java-based inventory management application designed to efficiently manage IT resources and materials. 
The system focuses on controlling equipment, devices, and assignments within an organization, providing secure access, 
inventory tracking, and user management.

The application is built using Java, Swing for the desktop interface, and MySQL for reliable data persistence.

---

## Tech Stack
- Java
- Java Swing
- MySQL
- JDBC
- Maven
- Git

---

## Key Features

### Secure Authentication
- Restricted access through a secure login system.
- User credentials are protected using password hashing.
- Admin users can create and remove application users.

### Inventory Management
- Full management of IT materials such as equipment, devices, and phone assets.
- Each inventory item includes name, available quantity, and optional image.
- Search and filtering by name or category.
- Automatic stock control when items are assigned to users.

### User Management
- User registration with relevant information (name, role, contact details).
- Users can view assigned materials.
- Assignment history tracking per user.

### Material Assignment
- Assignment of materials to specific users.
- Validation to prevent assigning more items than available in stock.
- Editable assignment records with full history tracking.

### Offline Desktop Application
- No internet connection required.
- Runs on any machine with Java Runtime Environment (JRE) installed.

---

## Application Workflow

### Login
- Users authenticate using username and password.
- Upon successful authentication, users access the main dashboard.

### Inventory Operations
- View, add, edit, and manage inventory items.
- Update quantities and view associated images.

### Resource Assignment
- Select a material, assign it to a user, and define the quantity.
- Inventory stock is updated automatically.

### User Administration
- Add, update, or remove users.
- Manage material assignments and user records.

---

## What I Learned
- Designing desktop applications using Java and Swing.
- Implementing secure authentication and password hashing.
- Managing relational data using MySQL and JDBC.
- Applying validation logic and inventory control rules.
- Structuring Java applications for maintainability.

---

## Future Improvements
- Migration to a RESTful backend using Spring Boot.
- API-based architecture for web or mobile clients.
- Role-based access control improvements.
