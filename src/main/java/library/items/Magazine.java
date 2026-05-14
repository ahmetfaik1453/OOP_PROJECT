package library.items;

public class Magazine extends LibraryItem {
    public Magazine(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }

    @Override
    public int getMaxLoanDays() {
        return 7;
    }

    @Override
    public double getDailyFineRate() {
        return 1.0;
    }
}
