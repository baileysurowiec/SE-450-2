package controller;

import model.interfaces.IShape;
import model.shapes.Group;
import model.shapes.MakeShape;
import java.awt.*;
import java.util.ArrayList;
import static model.shapes.MyShapesList.*;

public class CopyShapesCommand implements ICommand{

    @Override
    public void run() {
        copyShapeList.clear();
        copyGroupList.clear();
        int offset = -50;

        ArrayList<IShape> copyClipboard = new ArrayList<>();
//        for(IShape shape : selectedShapeList) {
//            if(!shape.isGroup()) { copyClipboard.add(shape); }
//        }
        copyClipboard.addAll(selectedShapeList);
        for (IShape copyShape: copyClipboard){
            MakeShape m = copyShape.getMadeShape();
            // setting offset for copied shapes
            int xs = (int) m.getStartC().getX() + offset;
            int ys = (int) m.getStartC().getY() + offset;
            int xe = (int) m.getEndC().getX() + offset;
            int ye = (int) m.getEndC().getY() + offset;
            Point newStart = new Point(xs, ys);
            Point newEnd = new Point(xe, ye);
            m.setPasteStartC(newStart);
            m.setPasteEndC(newEnd);
            copyShapeList.add(copyShape);
        }
        ArrayList<Group> copyGroupClipboard = new ArrayList<>();
        copyGroupClipboard.addAll(selectedGroups);
        for(Group group : copyGroupClipboard){
            for (IShape copyShape: group.getGroupedShapes()){
            // setting offset for copied shapes
                MakeShape m = copyShape.getMadeShape();
                int xs = (int) m.getStartC().getX() + offset;
                int ys = (int) m.getStartC().getY() + offset;
                int xe = (int) m.getEndC().getX() + offset;
                int ye = (int) m.getEndC().getY() + offset;
                Point newStart = new Point(xs, ys);
                Point newEnd = new Point(xe, ye);
                m.setPasteStartC(newStart);
                m.setPasteEndC(newEnd);
            }
            copyGroupList.add(group);
        }
    }
}
