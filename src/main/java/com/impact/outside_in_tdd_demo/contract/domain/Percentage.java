package com.impact.outside_in_tdd_demo.contract.domain;

public class Percentage {

    public static final Percentage ZERO = new Percentage(0);
    public static final Percentage ONE_HUNDRED = new Percentage(100);

    private final double value;

    private Percentage(double value) {
        this.value = value;
    }

    public static Percentage getInstanceOf(double value) {
        return new Percentage(value);
    }

    public boolean isLessThan(Percentage other) {
        return this.value < other.value;
    }

    public boolean isGreaterThan(Percentage other) {
        return this.value > other.value;
    }

    public double of(double amount) {
        return amount * value / 100;
    }

    public double getValue() {
        return value;
    }

}
