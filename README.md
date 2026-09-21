# Library Management System

A menu-driven Java console application for managing a library catalog and its patrons. It lets a user borrow, return, and place holds on books, and add, remove, search, and print books from a catalog of 11,000+ records stored in CSV files.

Built as a two-person project for ICS4U1 (Computer Science) at Don Mills Collegiate Institute, May to June 2025.

## Features

- **Borrow and return books** by barcode and student number
- **Holds:** add and remove holds on books
- **Book management:** add, remove, search, and print books
  - Adding a book validates the barcode, ISBN, call number, and author, and classifies it as Fiction or Non-Fiction
  - Books can be removed by title, barcode, or ISBN
- **Two kinds of search:**
  - *Specific search* finds an exact book using binary search
  - *Keyword search* finds every book whose title or author contains a given word
- **Patron management:** register, remove, search, and print patrons
- **Persistent storage:** the catalog and patron list are loaded from CSV files on startup and saved when you exit

## Built With

- Java (tested with Java 21)
- No external libraries

## Getting Started

### Prerequisites

A Java Development Kit (JDK). Check with:

```bash
java -version
javac -version
```

### Compile and run

Download or clone the repository, open a terminal in the project folder, and run:

```bash
javac Book.java Fiction.java NonFiction.java Patron.java Library.java Main.java
java Main
```

The program reads its data files using relative paths, so run it from the folder that contains `library.csv`, `patrons.csv`, and `write.txt`.

### Using the program

The main menu looks like this:

```
(1) Borrow
(2) Return
(3) Books
(4) Patrons
(5) Holds
(6) Exit
(7) DEBUG
```

Type the number of an option and press Enter. When you choose Exit, the program asks whether you want to save. **Changes are only written to the CSV files if you choose Yes.** Option 7 turns on debug output that prints what the sorting, searching, and file-reading code is doing.

## Project Structure

| File | Purpose |
|------|---------|
| `Main.java` | Console interface and menu logic |
| `Library.java` | Loads and saves data, keeps the catalog sorted, and handles searching, borrowing, returning, and holds |
| `Book.java` | Book class, input validation, and comparison logic |
| `Fiction.java`, `NonFiction.java` | Subclasses of `Book` used to keep separate Fiction and Non-Fiction lists |
| `Patron.java` | Patron class and patron validation and search |
| `library.csv` | Book catalog (title, author, barcode, call number, ISBN, status, patron, hold status) |
| `patrons.csv` | Patron records (student number, phone number, email, books checked out) |
| `write.txt` | Counter of how many times the program has been run |

## How It Works

- **Sorted lists:** when the program starts, `Library` reads `library.csv` line by line and loads every book into several lists, each sorted by a different field: title, author, barcode, call number, and ISBN. Separate Fiction and Non-Fiction lists are also kept.
- **Custom CSV parser:** titles and authors often contain commas (for example, "Lindsey, Cameron"), so the parser tracks whether it is inside quotation marks before treating a comma as a separator.
- **Merge sort and sorted insertion:** the lists are sorted with merge sort on load. New books are inserted into each list in the correct position instead of re-sorting the whole catalog.
- **Binary search** finds an exact book quickly in whichever list is sorted by the field being searched. **Sequential search** is used for keyword matches, since a keyword can appear anywhere in a field.
- **Fiction detection:** a book is classified as Fiction or Non-Fiction from its call number (an "F" prefix, or the absence of a Dewey Decimal number).

## Team

- **Ibraheem Siddiqui:** `Book`, `Fiction`, and `NonFiction` classes; book file reading and saving; catalog sorting and searching; the book management and search menus in `Main`
- **Imaad Kotadia:** `Patron` class; patron loading, saving, and sorting; borrow, return, and hold logic; the patron and holds menus in `Main`

## Known Limitations and Future Improvements

- ISBN validation only checks that the value is numeric. It does not check the length or the check digit.
- `Fiction` and `NonFiction` share nearly identical code and could be replaced with a single generic method.
- `Main.java` is large; the menus could be split into smaller methods.
- Data is stored in CSV files. A database would handle larger catalogs and multiple users better.

## License

This project was created for a school course. No license is specified.
