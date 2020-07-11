package com.benwillcabinets.benwillestimator.domain;

public enum Category {
    All_CATEGORIES("All_CATEGORIES"),
    PAINT("PAINT"),
    CABINETRY("CABINETRY"),
    DRYWALL("DRYWALL"),
    ELECTRICAL("ELECTRICAL"),
    PLUMBING("PLUMBING"),
    FLOORING("FLOORING"),
    LABOUR("LABOUR"),
    DEMOLITION("DEMOLITION"),
    SPECIAL_DELIVERIES("SPECIAL_DELIVERIES"),
    ;
    private String name;

    Category(String name) {
        this.name = name;
    }
}
