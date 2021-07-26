package model;

import java.awt.*;

import static java.awt.Color.*;

// watch tutorial vid from discord chat
public enum ShapeColor {

    BLACK(black),
    BLUE(blue),
    CYAN(cyan),
    DARK_GRAY(darkGray),
    GRAY(gray),
    GREEN(green),
    LIGHT_GRAY(lightGray),
    MAGENTA(magenta),
    ORANGE(orange),
    PINK(pink),
    RED(red),
    WHITE(white),
    YELLOW(yellow);

    private Color color;

    ShapeColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
