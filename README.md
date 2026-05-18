# OOP Library Management System

This project implements a library management system in Java using object-oriented programming (OOP) principles. It is designed to manage library members, borrowable items, and access rules while separating responsibilities across specialized classes.

## Project Overview

The application models a library environment with core concepts such as:

- Library members with different membership levels and borrowing privileges
- Borrowable items including books, magazines, and theses
- Custom exceptions to handle borrowing limits and unavailable items
- Utility classes for searching items and configuring member behavior

## Code Structure

- `src/main/java/library/Library.java` - Main library manager that handles inventory and borrowing logic
- `src/main/java/library/LibraryApp.java` - Application entry point and example usage runner
- `src/main/java/library/exceptions/` - Custom exception classes for error handling
- `src/main/java/library/items/` - Item models including `Book`, `Magazine`, `Thesis`, and borrowable item behavior
- `src/main/java/library/members/` - Member types and membership logic for different user tiers
- `src/main/java/library/utils/` - Support classes for search results, member creation, and configuration

## Key Features

- Supports multiple membership tiers with varying borrow limits
- Manages borrowable library items and item availability
- Enforces member-specific borrowing rules with custom exceptions
- Includes item search and structured result handling
- Designed for easy extension with new item types or member categories

## How to Build and Run

Compile the project sources with the Java compiler, then run the main application:

```bash
javac -d out src/main/java/library/*.java src/main/java/library/exceptions/*.java src/main/java/library/items/*.java src/main/java/library/members/*.java src/main/java/library/utils/*.java
java -cp out library.LibraryApp
```

## Suggested Improvements

- Add unit tests for borrow logic and member rules
- Implement a command-line or GUI interface for interactive use
- Add persistent storage for library items and member data
- Expand search filters and item categories

## Notes

This README provides an overview of the project design, structure, and usage. Update it as needed if the implementation changes or new features are added.
