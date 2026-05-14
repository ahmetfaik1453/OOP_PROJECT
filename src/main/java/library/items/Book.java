package library.items;

public class Book extends LibraryItem {
    public Book(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public int getMaxLoanDays() {
        return 14;
    }

    @Override
    public double getDailyFineRate() {
        return 1.0;
    }
}
