package data;

public class HumanFactory {
    private static HumanFactory instance = null;

    private HumanFactory() {

    }

    public static Human create(String human) {
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

    public static HumanFactory getInstance() {
        if (instance == null) {
            instance = new HumanFactory();
        }
        return instance;
    }
}
