package com.benwillcabinets.benwillestimator.domain;

import java.util.Arrays;
import java.util.List;

public enum Category {
    MATERIALS("Generic Materials"),
    PAINT("Materials: Paint"),
    PAINT_LABOUR("Labour: Paint"),
    CABINETRY("Materials: Cabinetry"),
    CABINETRY_LABOUR("Labour: Cabinetry"),
    PLASTERING("Materials: Plastering"),
    PLASTERING_LABOUR("Labour: Plastering"),
    ELECTRICAL("Electrical materials "),
    ELECTRICAL_LABOUR("Electrical labour "),
    PLUMBING("Plumbing materials"),
    PLUMBING_LABOUR("Plumbing labour"),
    FLOORING("Materials: Flooring"),
    FLOORING_LABOUR("Materials: Flooring"),
    LABOUR("Services: Labour"),
    DEMOLITION("Services: Demolition"),
    SPECIAL_DELIVERIES("Services: Special/Deliveries"),
    ;
    private String name;

    Category(String name) {
        this.name = name;
    }

    public static List<Category> getServiceCategories() {
        return Arrays.asList(LABOUR, DEMOLITION, SPECIAL_DELIVERIES, PAINT_LABOUR, CABINETRY_LABOUR,PLASTERING_LABOUR,
                ELECTRICAL_LABOUR,PLUMBING_LABOUR,FLOORING_LABOUR);
    }
}
