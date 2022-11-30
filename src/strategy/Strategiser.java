package strategy;

public enum Strategiser {
    RANDOM(new RandomStrategy());

    private final Strategy strategy;

    Strategiser(Strategy strategy) {
        this.strategy = strategy;
    }
}
