//package controller;
//
//import model.interfaces.IShape;
//import model.shapes.MakeGroup;
//import model.shapes.MakeShape;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GroupComposite implements IShape{ //IComponent {
////    private List<IComponent> children;
//    private List<IShape> children;
////    private ArrayList<IShape[]> shapeGroup;
//
//    public int x;
//    public int y;
//    public int width;
//    public int height;
//
//    public Boolean groupSelected = false;
//    public Boolean grouped = false;
//
////    public GroupComposite(IComponent component){
//    public GroupComposite(ArrayList<IShape> shape){
//
//        for(IShape s : shape) {
//            addChild(s);
//        }
//    }
//
////    public void addChild(IComponent c){
//    public void addChild(IShape c){
//        children.add(c);
//        setGroupBounds();
//    }
//
////    public void removeChild(IComponent c){
//    public void removeChild(IShape c){
//        children.remove(c);
//        setGroupBounds();
//    }
//
////    public List<IComponent> getChildren(){
////        return children;
////    }
//
//    public List<IShape> getChildren(){
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
////        for (IComponent s : children){
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
//        x = Math.min(x1, x2) -10;
//        y = Math.min(y1, y2) -10;
//        width = Math.abs(x1-x2) +20;
//        height = Math.abs(y1-y2) +20;
//    }
//
//
//    @Override
//    public void draw(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//
//        for (IShape s : children){
////        for (IComponent s : children){
//            s.draw(g2d);
//        }
//
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        g2d.setColor(Color.RED);
//        g2d.setStroke(stroke);
//        x = x -5;
//        y = y -5;
//        int w = width + 10;
//        int h = height + 10;
//
//        g.drawRect(x, y, w, h);
//    }
//
//    @Override
//    public MakeShape getMadeShape() {
//        MakeShape m = null;
////        for (IComponent s : children){
//        for (IShape s : children){
//            m = s.getMadeShape();
//        }
//        return m;
//    }
//
//    @Override
//    public Boolean isGroup() {
//        return grouped;
//    }
//
//}
