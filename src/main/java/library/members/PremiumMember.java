package library.members;

public class PremiumMember extends Member {
    public PremiumMember(String memberId, String name) {
        super(memberId, name, "Premium");
    }

    @Override
    public int getMaxBorrowLimit() {
        return 5;
    }

    @Override
    public double getFineMultiplier() {
        return 0.5;
    }
}
