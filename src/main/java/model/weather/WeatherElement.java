package model.weather;

public enum WeatherElement {
    // TODO: replace these numbers with correct numbers.
    NEUTRAL(0),
    BITING_FROST(1),
    RAIN(2),
    FOG(3),
    ;
    private final int initialCounterNumber;
    int counter;

    /**
     * @param initialCounterNumber An integer or double (preferably {@link Double#POSITIVE_INFINITY}) that represents
     *                             lifetime of the weather.
     */
    WeatherElement(int initialCounterNumber) {
        this.initialCounterNumber = initialCounterNumber;
        initializeCounter();
    }

    void initializeCounter() {
        this.counter = initialCounterNumber;
    }

    void passTime() {
        if (counter > 0) counter--;
    }
}
