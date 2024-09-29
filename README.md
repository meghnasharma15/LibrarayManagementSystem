# 📚 Library Management System

A simple console-based Java application for managing a library system, where users can add books, register members, borrow, and return books.

---

## 🚀 Features

- **Book Management**
  - List all available books.
  - Borrow a book by its ID.
  - Return a borrowed book.

- **Member Management**
  - Register new members.
  - View the list of all members.

- **Error Handling**
  - Invalid menu options are handled.
  - Validation for member existence and book availability.
  - Prevents borrowing of unavailable books and ID duplication for members.

---

## 🛠️ Project Structure

The project consists of three primary classes:

1. **Book Class**
   - Manages individual books in the library, each containing an `id` and `title`.

2. **Member Class**
   - Manages library members, including `id`, `name`, and a list of books borrowed by the member.

3. **Library Class**
   - Central management of books and members.
   - Includes methods for adding members, checking if a member exists, borrowing, and returning books.

