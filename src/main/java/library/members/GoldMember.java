package library.members;

public class GoldMember extends Member {
    public GoldMember(String memberId, String name) {
        super(memberId, name, "Gold");
    }

    @Override
    public int getMaxBorrowLimit() {
        return 6;
    }

    @Override
    public double getFineMultiplier() {
        return 0.25;
    }
}
