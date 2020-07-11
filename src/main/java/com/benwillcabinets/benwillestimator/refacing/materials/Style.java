package com.benwillcabinets.benwillestimator.refacing.materials;

public enum Style {
    UNDEFINED("UNDEFINED"),
    FLAT("FLAT"),
    RAISED("RAISED"),
    SHAKER_VGROOVE("SHAKER_VGROOVE"),
    SHAKER_GROOVELESS("SHAKER_GROOVELESS"),
    CALGARY("CALGARY"),
    _7000("7000"),
    ;
    private String style;

    Style(String style) {
        this.style = style;
    }
}
