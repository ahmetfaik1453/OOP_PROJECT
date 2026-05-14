package library.members;

public class SilverMember extends Member {
    public SilverMember(String memberId, String name) {
        super(memberId, name, "Silver");
    }

    @Override
    public int getMaxBorrowLimit() {
        return 4;
    }

    @Override
    public double getFineMultiplier() {
        return 0.25;
    }
}
