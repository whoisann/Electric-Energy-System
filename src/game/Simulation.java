package game;

import common.Constants;
import data.Consumer;
import data.Contract;
import data.Distributor;
import data.HumanFactory;
import input.Input;
import input.InputConsumer;
import input.InputCostsChange;

import java.util.ArrayList;

public final class Simulation {

    private Simulation() { }

    /**
     * Update the cost changes, new consumers and distributors' costs
     */
    public static void monthlyUpdates(final int month, final Input input,
                                      final ArrayList<Consumer> consumers,
                                      final ArrayList<Distributor> distributors) {

        ArrayList<InputCostsChange> costsChanges
                = input.getMonthlyUpdates().get(month).getCostsChanges();
        ArrayList<InputConsumer> inputConsumers
                = input.getMonthlyUpdates().get(month).getNewConsumers();

        for (InputConsumer inputConsumer : inputConsumers) {
            HumanFactory humanFactory = HumanFactory.getInstance();
            Consumer consumer = (Consumer) HumanFactory.create(Constants.CONSUMER);
            assert consumer != null;
            consumer.setConsumer(inputConsumer.getId(), inputConsumer.getMonthlyIncome(),
                    inputConsumer.getInitialBudget(), null);
            consumers.add(consumer);
        }

        // update monthly the distributors' costs
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

    /**
     * Update contract price
     */
    public static void updateContract(final ArrayList<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            distributor.updateContractPrice();
        }
    }

    /**
     * Get the lowest price contract for a consumer
     */
    public static void getLowestDistributor(final ArrayList<Distributor> distributors,
                                            final ArrayList<Consumer> consumers) {

        Distributor lowestPriceDistributor = Distributor.getLowestPriceDistributor(distributors);

        for (Consumer consumer : consumers) {
            // give the consumer the lowest price contact if he is not bankrupt
            if (!consumer.isBankrupt()) {
                // verify if a consumer has already a contract or it is expired
                if (consumer.getCurrentContract() == null || consumer.expiredContract()) {
                    assert lowestPriceDistributor != null;
                    Contract contract = new Contract(consumer.getId(),
                            (int) lowestPriceDistributor.getContractPrice(),
                            lowestPriceDistributor.getContractLength());
                    consumer.setCurrentContract(contract);
                    // set the consumer's distributor
                    consumer.setCurrentDistributor(lowestPriceDistributor);
                    lowestPriceDistributor.getContracts().add(contract);
                }
            }
        }
    }

    /**
     * Remove a contract if it is expired and calculate the taxes
     * */
    public static void calculateTaxes(final ArrayList<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            distributor.getContracts().removeIf(contract
                    -> contract.getRemainedContractMonths() == 0);
            distributor.calculateTaxes();
        }
    }

    /**
     * Get the consumer income and pay the taxes
     */
    public static void consumerPayTaxes(final ArrayList<Consumer> consumers) {
        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                consumer.setIncome();
                consumer.payTaxes();
            }
        }
    }

    /**
     * Remove a distributor contract if his consumer is bankrupt
     */
    public static void removeBankrupt(final ArrayList<Consumer> consumers) {
        for (Consumer consumer : consumers) {
            if (consumer.isBankrupt()) {
                consumer.getCurrentDistributor().getContracts().removeIf(contract ->
                        contract.getConsumerId() == consumer.getId());
            }
        }
    }

    /**
     * Pay the distributor taxes if he is not bankrupt
     */
    public static void distributorPayTaxes(final ArrayList<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            if (!distributor.isBankrupt()) {
                distributor.payTaxes();
            }
        }
    }

    /**
     * Simulate the game
     */
    public static void simulateMonth(final ArrayList<Consumer> consumers,
                                     final ArrayList<Distributor> distributors) {

        updateContract(distributors);

        getLowestDistributor(distributors, consumers);

        calculateTaxes(distributors);

        consumerPayTaxes(consumers);

        removeBankrupt(consumers);

        distributorPayTaxes(distributors);

    }
}
