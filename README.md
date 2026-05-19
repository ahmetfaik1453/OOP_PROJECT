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



Genel çalışma mantığı
Uygulama başlangıcı (LibraryApp)

Program öğrenci numarası ister.
studentNumber % 3 ile bir varyant seçilir (StudentConfig).
Bu varyant, hangi üye tiplerinin kullanılacağını belirler (ör. Standard/Premium/VIP).
Merkez yönetim (Library)

Sistem iki ana map tutar:
catalog → itemId → kitap/dergi/tez
members → memberId → üye
Tüm işlemler buradan yürür: item ekleme, üye ekleme, ödünç alma, iade, arama, rapor.
Item modeli (LibraryItem + alt sınıflar)

Ortak alanlar: id, title, author, isAvailable
Alt sınıflar: Book, Magazine, Thesis
borrow():
item müsait değilse ItemNotAvailableException
müsaitse üyeye eklenir, item unavailable olur
returnItem():
üyeden düşülür, item tekrar available olur
calculateFine(overdueDays) günlük ceza hesabını yapar.
Üye modeli (Member + tier sınıfları)

Üye sınıfları farklı ödünç limiti ve ceza çarpanı döndürür.
borrowItem() limit aşılırsa BorrowLimitExceededException fırlatır.
returnItem() o item üyede yoksa hata verir.
Tier sınıfları:
Variant 0: Standard / Premium / VIP
Variant 1: Basic / Silver / Gold
Variant 2: Regular / Plus / Elite
Factory kullanımı (MemberFactory)

Seçilen varyant + tier numarasına göre doğru Member alt sınıfını üretir.
Bu sayede LibraryApp içinde karmaşık if/switch dağılmaz.
Menü akışı (LibraryApp.run)

Kullanıcı menüden:
Item ekler
Üye kaydeder
Ödünç alır
İade eder (gecikme gününü kullanıcı girer)
Başlık/yazar arar
Üye raporu görür
Müsait itemleri listeler
Tüm giriş hataları ve iş kuralı hataları try/catch ile kullanıcıya mesaj olarak döner.
OOP açısından
Abstraction: LibraryItem, Member
Inheritance: Book/Magazine/Thesis ve tüm Member tier’ları
Polymorphism: aynı borrow/return/calculateFine çağrıları farklı sınıflarda farklı davranır
Encapsulation: alanlar private, doğrulamalar setter/constructor içinde
İstersen bir sonraki adımda sana bu akışı gerçek bir örnek senaryo (A üyesi B kitabını aldı, limit doldu, iade etti vs.) üzerinden adım adım simüle edebilirim.


## Notes

This README provides an overview of the project design, structure, and usage. Update it as needed if the implementation changes or new features are added.
