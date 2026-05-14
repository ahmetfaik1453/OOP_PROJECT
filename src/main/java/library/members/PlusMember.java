package library.members;

public class PlusMember extends Member {
    public PlusMember(String memberId, String name) {
        super(memberId, name, "Plus");
    }

    @Override
    public int getMaxBorrowLimit() {
        return 6;
    }

    @Override
    public double getFineMultiplier() {
        return 1.0;
    }
}
