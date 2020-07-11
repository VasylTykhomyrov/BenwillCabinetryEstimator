package com.benwillcabinets.benwillestimator.refacing.materials;

public enum Colour {
    UNDEFINED("UNDEFINED"),
    WHITE("WHITE"),
    ALMOND("ALMOND"),
    WASHED_ANTIQUE_WHITE("WASHED_ANTIQUE_WHITE"),
    ANTIQUE_WHITE("ANTIQUE_WHITE"),
    LIGHT_GREY("LIGHT_GREY"),
    CHOCOLATE("CHOCOLATE"),
    TUXEDO("TUXEDO"),
    VANILA_STIX("VANILA_STIX")
    ;
    private String colour;

    Colour(String colour) {
        this.colour = colour;
    }
}
