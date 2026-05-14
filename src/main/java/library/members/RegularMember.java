package library.members;

public class RegularMember extends Member {
    public RegularMember(String memberId, String name) {
        super(memberId, name, "Regular");
    }

    @Override
    public int getMaxBorrowLimit() {
        return 3;
    }

    @Override
    public double getFineMultiplier() {
        return 1.0;
    }
}
