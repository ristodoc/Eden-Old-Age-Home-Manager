package eden.oldagehome.model;

/**
 * Represents an item in the inventory.
 */
public class InventoryItem {

    private String itemId;
    private String name;
    private int quantityLeft;
    private int monthlyRequirement;

    public InventoryItem(String itemId, String name, int quantityLeft, int monthlyRequirement) {
        this.itemId = itemId;
        this.name = name;
        this.quantityLeft = quantityLeft;
        this.monthlyRequirement = monthlyRequirement;
    }

    /**
     * Checks if the quantity on hand is below the required threshold.
     * @return true if restock is needed, false otherwise.
     */
    public boolean isRestockNeeded() {
        return quantityLeft < monthlyRequirement;
    }

    /**
     * Adds a specified amount to the quantity on hand.
     * @param amount The amount to add.
     */
    public void restock(int amount) {
        this.quantityLeft += amount;
    }

    // Getters and Setters

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
