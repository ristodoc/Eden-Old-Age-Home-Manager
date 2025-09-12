package eden.oldagehome.model;

/**
 * Represents a medication for a resident.
 */
public class Medication {

    private String name;
    private String dosage;
    private int quantityLeft;
    private int monthlyRequirement;

    public Medication(String name, String dosage, int quantityLeft, int monthlyRequirement) {
        this.name = name;
        this.dosage = dosage;
        this.quantityLeft = quantityLeft;
        this.monthlyRequirement = monthlyRequirement;
    }

    /**
     * Checks if the quantity left is less than the monthly requirement.
     * @return true if a refill is needed, false otherwise.
     */
    public boolean isRefillNeeded() {
        return quantityLeft < monthlyRequirement;
    }

    /**
     * Adds a specified amount to the quantity left.
     * @param amount The amount to add.
     */
    public void refill(int amount) {
        this.quantityLeft += amount;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(int quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public int getMonthlyRequirement() {
        return monthlyRequirement;
    }

    public void setMonthlyRequirement(int monthlyRequirement) {
        this.monthlyRequirement = monthlyRequirement;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", quantityLeft=" + quantityLeft +
                '}';
    }
}
