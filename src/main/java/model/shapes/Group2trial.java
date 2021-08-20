//package model.shapes;
//
//import model.interfaces.IShape;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//import static model.shapes.MyShapesList.moveGroupedShapes;
//
//public class Group2trial implements IShape {
//    ArrayList<IShape> children;
//
//    public Boolean selected = false;
//
//    public int x;
//    public int y;
//    public int width;
//    public int height;
//
//
//    public Group2trial(){
//        children = new ArrayList<IShape>();
//    }
//
//    public void addChild(IShape shape){
//        children.add(shape);
//    }
//    public void removeChild(IShape shape){
//        children.remove(children);
//    }
//
//    public ArrayList<IShape> getGroupedShapes(){
//        return children;
//    }
//
//    public void setGroupBounds(){
//        int x1 = 0;
//        int y1 = 0;
//        int x2 = 0;
//        int y2 = 0;
//
//        for (IShape s : children){
//            MakeShape m = s.getMadeShape();
//            int sx = (int) m.getMin().getX();
//            int sy = (int) m.getMin().getY();
//            int mx = (int) m.getMax().getX();
//            int my = (int) m.getMax().getY();
//            if (x1 == 0 || sx < x1){
//                x1 = sx;
//            }
//            if (y1 == 0 || sy < y1){
//                y1 = sy;
//            }
//            if (x2 == 0 || mx > x2){
//                x2 = mx;
//            }
//            if (y2 == 0 || my > y2){
//                y2 = my;
//            }
//        }
//        x = Math.min(x1, x2) ;//-10;
//        y = Math.min(y1, y2) ;//-10;
//        width = Math.abs(x1-x2) ;//+10;
//        height = Math.abs(y1-y2) ;//+10;
//    }
//
//    public void setGroup(){
//        for(IShape shape: children){
//            shape.getMadeShape().group = this;
//        }
//    }
//
//    public void selectGroup(int mx, int my, int mw, int mh){
//        int gX = x -5;
//        int gY = y -5;
//        int gW = width +10;
//        int gH = height +10;
//
//        if (gX < mx + mw && gX + gW > mx && gY < my + mh && gY + gH > my) {
//            selected = true;
////            moveGroupedShapes.addAll(groupedShapes);
//        }
//        else{ selected = false; }
//    }
//
//    public void drawSelected(Graphics2D g){ // grouped shapes border
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        g.setColor(Color.RED);
//        g.setStroke(stroke);
//        int x = this.x-5;
//        int y = this.y-5;
//        int w = width + 10;
//        int h = height + 10;
//
//        g.drawRect(x, y, w, h);
//    }
//
//    @Override
//    public Group2trial getGroup(){
//        return this;
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        for (IShape s : children){
//            if(selected){ drawSelected(g2d); }
//
//            s.draw(g2d);
//        }
////        if(selected){
////            drawSelected(g2d);
////        }
//    }
//
//    @Override
//    public MakeShape getMadeShape() {
//        MakeShape m = null;
//        for (IShape shape : children){
//            m = shape.getMadeShape();
//        }
//        return m;
//    }
//
//    @Override
//    public Boolean isGroup() {
//        return true;
//    }
//
//    @Override
//    public Boolean isSelected(){
//        for(IShape s : children){
//            if(s.isSelected()){
//                selected = true;
//                return true;
//            }
//        }
//        return false;
//    }
//}
