package library.items;

public class Thesis extends LibraryItem {
    public Thesis(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public String getItemType() {
        return "Thesis";
    }

    @Override
    public int getMaxLoanDays() {
        return 21;
    }

    @Override
    public double getDailyFineRate() {
        return 1.0;
    }
}
