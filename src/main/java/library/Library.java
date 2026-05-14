package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import library.items.LibraryItem;
import library.members.Member;
import library.utils.SearchResult;

public class Library {
    private Map<String, LibraryItem> catalog;
    private Map<String, Member> members;

    public Library() {
        catalog = new HashMap<>();
        members = new HashMap<>();
    }

    public void addItem(LibraryItem item) {
        if (catalog.containsKey(item.getId())) {
            throw new IllegalArgumentException("An item with ID " + item.getId() + " already exists.");
        }
        catalog.put(item.getId(), item);
    }

    public void registerMember(Member member) {
        if (members.containsKey(member.getMemberId())) {
            throw new IllegalArgumentException("A member with ID " + member.getMemberId() + " already exists.");
        }
        members.put(member.getMemberId(), member);
    }

    public void borrowItem(String memberId, String itemId) {
        Member member = getMember(memberId);
        LibraryItem item = getItem(itemId);
        item.borrow(member);
    }

    public double returnItem(String memberId, String itemId, int overdueDays) {
        Member member = getMember(memberId);
        LibraryItem item = getItem(itemId);
        item.returnItem(member);
        double fine = item.calculateFine(overdueDays) * member.getFineMultiplier();
        return Math.max(fine, 0.0);
    }

    public SearchResult<LibraryItem> searchByTitle(String keyword) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : catalog.values()) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return new SearchResult<>(results);
    }

    public SearchResult<LibraryItem> searchByAuthor(String keyword) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : catalog.values()) {
            if (item.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return new SearchResult<>(results);
    }

    public void listAllAvailable() {
        boolean found = false;
        for (LibraryItem item : catalog.values()) {
            if (item.isAvailable()) {
                item.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no available items at the moment.");
        }
    }

    public void getMemberReport(String memberId) {
        Member member = getMember(memberId);
        member.displayInfo();
    }

    private LibraryItem getItem(String itemId) {
        LibraryItem item = catalog.get(itemId);
        if (item == null) {
            throw new IllegalArgumentException("No item found with ID: " + itemId);
        }
        return item;
    }

    private Member getMember(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            throw new IllegalArgumentException("No member found with ID: " + memberId);
        }
        return member;
    }
}
