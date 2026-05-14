package library.items;

import library.exceptions.ItemNotAvailableException;
import library.members.Member;

public abstract class LibraryItem implements Borrowable {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable;

    public LibraryItem(String id, String title, String author) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be empty.");
        }
        this.id = id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        this.author = author.trim();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    protected void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public abstract String getItemType();

    public abstract int getMaxLoanDays();

    public abstract double getDailyFineRate();

    public void displayInfo() {
        System.out.println("[" + getItemType() + "] ID: " + id + " | Title: " + title + " | Author: " + author + " | Available: " + (isAvailable ? "Yes" : "No"));
    }

    @Override
    public void borrow(Member member) {
        if (!isAvailable) {
            throw new ItemNotAvailableException("The item '" + title + "' is currently not available.");
        }
        member.borrowItem(this);
        setAvailable(false);
    }

    @Override
    public void returnItem(Member member) {
        member.returnItem(this);
        setAvailable(true);
    }

    @Override
    public double calculateFine(int overdueDays) {
        if (overdueDays <= 0) {
            return 0.0;
        }
        return overdueDays * getDailyFineRate();
    }
}
