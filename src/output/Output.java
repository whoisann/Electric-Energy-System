package output;

import java.util.ArrayList;

public final class Output {
    private ArrayList<OutputConsumer> consumers;
    private ArrayList<OutputDistributor> distributors;

    public ArrayList<OutputConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<OutputConsumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<OutputDistributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<OutputDistributor> distributors) {
        this.distributors = distributors;
    }

    @Override
    public String toString() {
        return "InitialData{" + "consumers=" + consumers
                + ", distributors=" + distributors
                + '}';
    }
}
