package library.members;

public class EliteMember extends Member {
    public EliteMember(String memberId, String name) {
        super(memberId, name, "Elite");
    }

    @Override
    public int getMaxBorrowLimit() {
        return 9;
    }

    @Override
    public double getFineMultiplier() {
        return 1.0;
    }
}
