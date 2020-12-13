package input;

import java.util.ArrayList;

public class InputMonthlyUpdate {
    private ArrayList<InputConsumer> newConsumers;
    private ArrayList<InputCostsChange> costsChanges;

    public ArrayList<InputConsumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(ArrayList<InputConsumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<InputCostsChange> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(ArrayList<InputCostsChange> costsChanges) {
        this.costsChanges = costsChanges;
    }

    @Override
    public String toString() {
        return "MonthlyUpdates{" + "newConsumers=" + newConsumers
                + ", costsChanges=" + costsChanges + '}';
    }
}
