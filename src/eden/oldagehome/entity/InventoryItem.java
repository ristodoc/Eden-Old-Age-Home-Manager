 package eden.oldagehome.entity;

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
    
    public boolean isRestockNeeded() {
        return quantityLeft < (monthlyRequirement * 0.3); // 30% threshold
    }
    
    public void restock(int amount) {
        quantityLeft += amount;
    }
    
    // Getters and setters
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantityLeft() { return quantityLeft; }
    public void setQuantityLeft(int quantityLeft) { this.quantityLeft = quantityLeft; }
    public int getMonthlyRequirement() { return monthlyRequirement; }
    public void setMonthlyRequirement(int monthlyRequirement) { this.monthlyRequirement = monthlyRequirement; }
}