package output;

import input.InputConsumer;
import input.InputDistributor;

import java.util.ArrayList;

public class Output {
    private ArrayList<InputConsumer> consumers;
    private ArrayList<InputDistributor> distributors;

    public ArrayList<InputConsumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(ArrayList<InputConsumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<InputDistributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<InputDistributor> distributors) {
        this.distributors = distributors;
    }

    @Override
    public String toString() {
        return "InitialData{" + "consumers=" + consumers
                + ", distributors=" + distributors
                + '}';
    }
}
