package input;

public class InputCostsChange {
    private int id;
    private int infrastructureCost;
    private int productionCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    @Override
    public String toString() {
        return "InputCostsChange{" +
                "id=" + id + ", infrastructureCost=" + infrastructureCost
                + ", productionCost=" + productionCost + '}';
    }
}
