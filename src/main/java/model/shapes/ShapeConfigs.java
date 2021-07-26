package model.shapes;


import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

import java.awt.*;

// shape qualities
// every shape has: color(s), shading type, & actual shape type
// passing x,y coords elsewhere
// sets and gets go here
// use to pass into new shape instead of having to pass each shape's individual settings

public class ShapeConfigs {
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeShadingType shadingType;
    private ShapeType shapeType;

    // color(s)
    public void setPrimaryColor(ShapeColor primaryColor){
        this.primaryColor = primaryColor;
    }
    public Color getPrimaryColor(){return primaryColor.getColor();}
    public void setSecondaryColor(ShapeColor secondaryColor){
        this.secondaryColor = secondaryColor;
    }
    public Color getSecondaryColor(){return secondaryColor.getColor();}
    // shape: rectangle, triangle or ellipse
    public void setShapeType(ShapeType shapeType){
        this.shapeType = shapeType;
    }
    public ShapeType getShapeType(){ return shapeType;}
    // shading: outlined, filled, outlined & filled
    public void setShadingType(ShapeShadingType shadingType){
        this.shadingType = shadingType;
    }
    public ShapeShadingType getShadingType(){return shadingType;}

}
