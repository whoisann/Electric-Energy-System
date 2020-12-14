package game;

import data.Consumer;
import data.Contract;
import data.Distributor;
import data.HumanFactory;
import input.Input;
import input.InputConsumer;
import input.InputCostsChange;

import java.util.ArrayList;

public class Simulation {

    public static void monthlyUpdates(int month, Input input, ArrayList<Consumer> consumers, ArrayList<Distributor> distributors) {
        ArrayList<InputCostsChange> costsChanges = input.getMonthlyUpdates().get(month).getCostsChanges();
        ArrayList<InputConsumer> inputConsumers = input.getMonthlyUpdates().get(month).getNewConsumers();

        for (InputConsumer inputConsumer : inputConsumers) {
            HumanFactory humanFactory = HumanFactory.getInstance();
            Consumer consumer = (Consumer) HumanFactory.create("consumer");
            assert consumer != null;
            consumer.setConsumer(inputConsumer.getId(), inputConsumer.getMonthlyIncome(),
                    inputConsumer.getInitialBudget(), null);
            consumers.add(consumer);
        }
        // update monthly the distributors
        for (InputCostsChange costsChange : costsChanges) {
            for (Distributor distributor : distributors) {
                if (distributor.getId() == costsChange.getId()) {
                    distributor.setInitialInfrastructureCost(costsChange.getInfrastructureCost());
                    distributor.setInitialProductionCost(costsChange.getProductionCost());
                    break;
                }
            }
        }
    }

    public static void updateContract(ArrayList<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            distributor.updateContractPrice();
        }
    }

    public static void getLowestDistributor(ArrayList<Distributor> distributors, ArrayList<Consumer> consumers) {
        Distributor lowestPriceDistributor = Distributor.getLowestPriceDistributor(distributors);
        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                if (consumer.getCurrentContract() == null || consumer.expiredContract()) {
                    assert lowestPriceDistributor != null;
                    Contract contract = new Contract(consumer.getId(), (int) lowestPriceDistributor.getContractPrice(),
                            lowestPriceDistributor.getContractLength());
                    consumer.setCurrentContract(contract);
                    consumer.setCurrentDistributor(lowestPriceDistributor);
                    lowestPriceDistributor.getContracts().add(contract);
                }
            }
        }
    }

    public static void calculateTaxes(ArrayList<Distributor> distributors, ArrayList<Consumer> consumers) {
        for (Distributor distributor : distributors) {
            distributor.getContracts().removeIf(contract -> contract.getRemainedContractMonths() == 0);
            distributor.calculateTaxes();
        }
    }

    public static void consumerPayTaxes(ArrayList<Consumer> consumers) {
        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                consumer.setIncome();
                consumer.payTaxes();
            }
        }
    }

    public static void removeBankrupt(ArrayList<Consumer> consumers) {
        for (Consumer consumer : consumers) {
            if (consumer.isBankrupt()) {
                consumer.getCurrentDistributor().getContracts().removeIf(contract ->
                        contract.getConsumerId() == consumer.getId());
            }
        }
    }

    public static void distributorPayTaxes(ArrayList<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            if (!distributor.isBankrupt()) {
                distributor.payTaxes();
            }
        }
    }

    public static void simulateMonth(ArrayList<Consumer> consumers, ArrayList<Distributor> distributors) {

        updateContract(distributors);

        getLowestDistributor(distributors, consumers);

        calculateTaxes(distributors, consumers);

        consumerPayTaxes(consumers);

        removeBankrupt(consumers);

        distributorPayTaxes(distributors);

    }
}
