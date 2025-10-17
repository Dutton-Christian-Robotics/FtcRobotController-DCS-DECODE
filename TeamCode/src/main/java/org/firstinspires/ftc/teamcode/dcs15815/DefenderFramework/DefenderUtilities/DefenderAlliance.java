package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities;

public class DefenderAlliance {


    public enum Color {
        UNKNOWN,
        RED,
        BLUE
    }

    public DefenderAlliance.Color currentColor;

    private static final DefenderAlliance instance = new DefenderAlliance();

    private DefenderAlliance() {
        currentColor = Color.UNKNOWN;
    }

    public static DefenderAlliance getInstance() {
        return instance;
    }

    public void setColor(DefenderAlliance.Color c) {
        currentColor = c;
    }


    public DefenderAlliance.Color getColor() {
        return currentColor;
    }

    public boolean isRed() {
        return currentColor == Color.RED;
    }

    public boolean isBlue() {
        return currentColor == Color.BLUE;
    }

    public boolean isKnown() {
        return currentColor != Color.UNKNOWN;
    }


}