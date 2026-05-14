package library.utils;

import library.members.*;

public class MemberFactory {
    public static Member createMember(int variant, int tierChoice, String memberId, String name) {
        switch (variant) {
            case 0:
                return createVariantZeroMember(tierChoice, memberId, name);
            case 1:
                return createVariantOneMember(tierChoice, memberId, name);
            case 2:
                return createVariantTwoMember(tierChoice, memberId, name);
            default:
                throw new IllegalArgumentException("Unknown variant: " + variant);
        }
    }

    private static Member createVariantZeroMember(int tierChoice, String memberId, String name) {
        return switch (tierChoice) {
            case 1 -> new StandardMember(memberId, name);
            case 2 -> new PremiumMember(memberId, name);
            case 3 -> new VIPMember(memberId, name);
            default -> throw new IllegalArgumentException("Invalid tier selection.");
        };
    }

    private static Member createVariantOneMember(int tierChoice, String memberId, String name) {
        return switch (tierChoice) {
            case 1 -> new BasicMember(memberId, name);
            case 2 -> new SilverMember(memberId, name);
            case 3 -> new GoldMember(memberId, name);
            default -> throw new IllegalArgumentException("Invalid tier selection.");
        };
    }

    private static Member createVariantTwoMember(int tierChoice, String memberId, String name) {
        return switch (tierChoice) {
            case 1 -> new RegularMember(memberId, name);
            case 2 -> new PlusMember(memberId, name);
            case 3 -> new EliteMember(memberId, name);
            default -> throw new IllegalArgumentException("Invalid tier selection.");
        };
    }
}
