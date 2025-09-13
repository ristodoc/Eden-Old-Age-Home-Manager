package eden.oldagehome.entity;

public class Medication {
    private String name;
    private String dosage;
    private int quantityLeft;
    private int monthlyRequirement;

    public boolean isRefillNeeded() {
        return quantityLeft < (monthlyRequirement * 0.2); // 20% threshold
    }

    public void refill(int amount) {
        quantityLeft += amount;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public int getQuantityLeft() { return quantityLeft; }
    public void setQuantityLeft(int quantityLeft) { this.quantityLeft = quantityLeft; }
    public int getMonthlyRequirement() { return monthlyRequirement; }
    public void setMonthlyRequirement(int monthlyRequirement) { this.monthlyRequirement = monthlyRequirement; }
}