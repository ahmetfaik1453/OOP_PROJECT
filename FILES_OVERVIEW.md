# Library Management System File Overview

This project is a Java console-based library system. It uses packages for grouping items, members, exceptions, and utility classes.

## Main files

- `LibraryApp.java` — main entry point and menu-driven console interface.
- `Library.java` — central library manager handling catalog, members, borrowing, returns, search, and reports.

## Packages

### `library.items`

- `LibraryItem.j` — abstract base class for library items.
- `Borrowable.java` — interface for borrowable item behavior.
- `Book.java`, `Magazine.java`, `Thesis.java` — item types with loan days and type labels.

### `library.members`

- `Member.java` — abstract member class with borrow tracking and limit checks.
- `StandardMember.java`, `PremiumMember.java`, `VIPMember.java` — variant 0 member tiers.
- `BasicMember.java`, `SilverMember.java`, `GoldMember.java` — variant 1 member tiers.
- `RegularMember.java`, `PlusMember.java`, `EliteMember.java` — variant 2 member tiers.

### `library.exceptions`

- `ItemNotAvailableException.java` — thrown when a book is not available.
- `BorrowLimitExceededException.java` — thrown when a member reaches borrowing limit.

### `library.utils`

- `SearchResult.java` — generic wrapper for search results.
- `MemberFactory.java` — creates the correct member type based on student variant.
- `StudentConfig.java` — stores student-based tier names and fine rate.

## Notes

- The app is organized to keep related classes together.
- It is intentionally short and avoids AI-style wording.
