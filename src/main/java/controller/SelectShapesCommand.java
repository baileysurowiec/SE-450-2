package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MyShapesList;
import java.awt.*;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;

public class SelectShapesCommand implements ICommand{
    private MyShapesList myShapesList;
    private Point startC;
    private Point endC;
    public ArrayList<IShape> moveToSelected;

    public SelectShapesCommand(MyShapesList myShapesLists, Point startC, Point endC) {
        this.myShapesList = myShapesLists;
        this.startC = startC;
        this.endC = endC;
    }
    // create invisible bounding box from start coord to end coords
    // determine if shape is in bounding box
    //  if yes, selected = true

    @Override
    public void run() {
        myShapesList.clearSelected();
        moveToSelected = getMyShapeList();
            // find mouse bounding box
        int mouseMinX = Math.min((int) startC.getX(), (int) endC.getX());
        int mouseMinY = Math.min((int) startC.getY(), (int) endC.getY());
        int mouseBoundsWidth = Math.abs((int) startC.getX() - (int) endC.getX());
        int mouseBoundsHeight = Math.abs((int) startC.getY() - (int) endC.getY());
            // if in bounding box add selected shape(s) to selected shape list

        ArrayList<Group> selectGroups = new ArrayList<>();

        for (IShape iShape : moveToSelected) {

            if(!iShape.isGroup()) {
                int shapeMinX = (int) Math.min(iShape.getMadeShape().getStartC().getX(), iShape.getMadeShape().getEndC().getX() - 5);
                int shapeMinY = (int) Math.min(iShape.getMadeShape().getStartC().getY(), iShape.getMadeShape().getEndC().getY() - 5);
                int shapeWidth = iShape.getMadeShape().getWidth() + 10;
                int shapeHeight = iShape.getMadeShape().getHeight() + 10;
                // collision detection
                if (shapeMinX < mouseMinX + mouseBoundsWidth && shapeMinX + shapeWidth > mouseMinX
                        && shapeMinY < mouseMinY + mouseBoundsHeight && shapeMinY + shapeHeight > mouseMinY) {
                    iShape.getMadeShape().shapeSelected = true;
                    selectedShapeList.add(iShape);
                }
            }
            else{
                Group s = iShape.getMadeShape().getLastGroup();
                    if(!selectGroups.contains(s)){
                        selectGroups.add(s);
                    }
            }

        }
        for(Group g: selectGroups){
            g.selectGroup(mouseMinX, mouseMinY, mouseBoundsWidth, mouseBoundsHeight);
            if(g.groupSelected){
                selectedGroups.add(g);
            }
        }
        drawMyShapes();
    }

}
