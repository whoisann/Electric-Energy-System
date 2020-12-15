package input;

import java.util.ArrayList;

public final class InputMonthlyUpdate {
    private ArrayList<InputConsumer> newConsumers;
    private ArrayList<InputCostsChange> costsChanges;

    public ArrayList<InputConsumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<InputConsumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<InputCostsChange> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final ArrayList<InputCostsChange> costsChanges) {
        this.costsChanges = costsChanges;
    }

    @Override
    public String toString() {
        return "MonthlyUpdates{" + "newConsumers=" + newConsumers
                + ", costsChanges=" + costsChanges + '}';
    }
}
