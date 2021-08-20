//package controller;
//
//import model.interfaces.IShape;
//import model.shapes.Group2trial;
//
//import java.util.ArrayList;
//
//import static model.shapes.MyShapesList.*;
//
///* Use Composite pattern
//    remove elements from shape list and replace with a composite
//    undo : find the composite in the shape list & remove it
//    redo: add composite members back into the shape list
//*/
//public class GroupShapesTrial2 implements ICommand, IUndoable{
//    ArrayList<IShape> toUnGroup = new ArrayList<>();
//    Group2trial group;
//
//
//    @Override
//    public void run() {
//        if(selectedShapeList.size()>1) {
////            ArrayList<IShape> selected = selectedShapeList;
//
//            group = new Group2trial();
//            myGroupsList.add(group); // add group to group list
//
//            for (IShape shape : selectedShapeList) {
//                myShapeList.remove(shape); // remove shape from shape list
//                if (!shape.isGroup()) {
//                    shape.getMadeShape().shapeSelected = false;
//                    group.addChild(shape);  // add to composite
//                }
//                else if (shape.isGroup()) {
//                    shape.getGroup().selected = false;
//                    group.addChild(shape);
//                }
//            }
////            group.selected = true;
////            group.setGroupBounds();
////            group.setGroup();
//            myShapeList.add(group); // add composite back to shapelist
//
//            selectedShapeList.clear();
//            selectedShapeList.add(group);
//        }
//        drawMyShapes();
//        CommandHistory.add(this);
//    }
//
//    @Override
//    public void undo() {
//
//    }
//
//    @Override
//    public void redo() {
//
//    }
//
//
//
//
//}
