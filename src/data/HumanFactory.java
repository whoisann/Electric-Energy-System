package data;

public final class HumanFactory {
    private static HumanFactory instance = null;

    private HumanFactory() { }

    /**
     * Create the consumers and distributors
     */
    public static Human create(final String human) {
        switch (human) {
            case "consumer" -> {
                return new Consumer();
            }
            case "distributor" -> {
                return new Distributor();
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Get instance
     */
    public static HumanFactory getInstance() {
        if (instance == null) {
            instance = new HumanFactory();
        }
        return instance;
    }
}
