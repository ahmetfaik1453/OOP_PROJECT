package library.utils;

import java.util.List;
import library.items.LibraryItem;

public class SearchResult<T extends LibraryItem> {
    private List<T> results;

    public SearchResult(List<T> results) {
        this.results = results;
    }

    public void display() {
        if (results.isEmpty()) {
            System.out.println("No matching items found.");
        } else {
            for (T item : results) {
                item.displayInfo();
            }
        }
    }

    public int getCount() {
        return results.size();
    }

    public List<T> getResults() {
        return results;
    }
}
