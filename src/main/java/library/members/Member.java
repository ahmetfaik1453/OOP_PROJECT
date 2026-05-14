package library.members;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import library.items.LibraryItem;
import library.exceptions.BorrowLimitExceededException;

public abstract class Member {
    private String memberId;
    private String name;
    private List<LibraryItem> borrowedItems;
    private final String tierName;

    public Member(String memberId, String name, String tierName) {
        setMemberId(memberId);
        setName(name);
        if (tierName == null || tierName.isBlank()) {
            throw new IllegalArgumentException("Tier name cannot be empty.");
        }
        this.tierName = tierName;
        this.borrowedItems = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        this.memberId = memberId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getTierName() {
        return tierName;
    }

    public abstract int getMaxBorrowLimit();

    public abstract double getFineMultiplier();

    public void borrowItem(LibraryItem item) {
        if (borrowedItems.size() >= getMaxBorrowLimit()) {
            throw new BorrowLimitExceededException("Member '" + getName() + "' has reached the borrowing limit of " + getMaxBorrowLimit() + " items.");
        }
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        if (!borrowedItems.remove(item)) {
            throw new IllegalArgumentException("Item '" + item.getTitle() + "' is not borrowed by member " + getName() + ".");
        }
    }

    public List<LibraryItem> getBorrowedItems() {
        return Collections.unmodifiableList(borrowedItems);
    }

    public void displayInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Tier: " + tierName);
        System.out.println("Borrowed items (" + borrowedItems.size() + "/" + getMaxBorrowLimit() + "): ");
        if (borrowedItems.isEmpty()) {
            System.out.println("  None");
        } else {
            for (LibraryItem item : borrowedItems) {
                System.out.print("  - ");
                item.displayInfo();
            }
        }
    }
}
