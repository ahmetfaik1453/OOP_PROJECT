package library.members;

public class BasicMember extends Member {
    public BasicMember(String memberId, String name) {
        super(memberId, name, "Basic");
    }

    @Override
    public int getMaxBorrowLimit() {
        return 2;
    }

    @Override
    public double getFineMultiplier() {
        return 0.25;
    }
}
