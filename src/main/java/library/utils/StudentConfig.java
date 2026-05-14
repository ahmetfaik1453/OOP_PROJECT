package library.utils;

public class StudentConfig {
    private final int variant;

    public StudentConfig(int studentNumber) {
        if (studentNumber < 0) {
            throw new IllegalArgumentException("Student number must be non-negative.");
        }
        this.variant = studentNumber % 3;
    }

    public int getVariant() {
        return variant;
    }

    public String[] getTierNames() {
        return switch (variant) {
            case 0 -> new String[] {"Standard", "Premium", "VIP"};
            case 1 -> new String[] {"Basic", "Silver", "Gold"};
            case 2 -> new String[] {"Regular", "Plus", "Elite"};
            default -> new String[0];
        };
    }

    public double getFinePerDay() {
        return switch (variant) {
            case 0 -> 0.50;
            case 1 -> 0.25;
            case 2 -> 1.00;
            default -> 0.0;
        };
    }

    public String getVariantDescription() {
        return switch (variant) {
            case 0 -> "Standard / Premium / VIP";
            case 1 -> "Basic / Silver / Gold";
            case 2 -> "Regular / Plus / Elite";
            default -> "Unknown";
        };
    }
}
