package model.shapes;

import model.interfaces.IShape;
import java.awt.*;
import java.util.ArrayList;

public class MakeGroup{//} implements IShape{
    public int x;
    public int y;
    public int width;
    public int height;
    ArrayList<IShape> groupedShapes;

    public Boolean groupSelected = false;
//    public Boolean moved = false;
//    public Boolean pasted = false;
//    public Boolean deleted = false;

    public MakeGroup(ArrayList<IShape> groupedShapes){
        this.groupedShapes = groupedShapes;
        setGroupBounds();
    }

    public void setGroupBounds(){
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        for (IShape s : groupedShapes){
            MakeShape m = s.getMadeShape();
            int sx = (int) m.getMin().getX();
            int sy = (int) m.getMin().getY();
            int mx = (int) m.getMax().getX();
            int my = (int) m.getMax().getY();
            if (x1 == 0 || sx < x1){
                x1 = sx;
            }
            if (y1 == 0 || sy < y1){
                y1 = sy;
            }
            if (x2 == 0 || mx > x2){
                x2 = mx;
            }
            if (y2 == 0 || my > y2){
                y2 = my;
            }
        }
        x = Math.min(x1, x2) -10;
        y = Math.min(y1, y2) -10;
        width = Math.abs(x1-x2) +20;
        height = Math.abs(y1-y2) +20;
    }

    public void setGroupSelected(){ groupSelected = true; }
    public boolean getGroupSelected(){ return groupSelected; }

//    @Override
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for (IShape s : groupedShapes){
            s.draw(g2d);
        }
    }


//    public void increaseGroup(){ numberOfGroups++; }
//    public void decreaseGroup(){ numberOfGroups--; }
//
//    //    public Boolean isGrouped(){
////        for(Group group: myGroupsList){
////            group.getGroupedShapes().contains(this);
////        }
//////        return myGroupsList.contains(this);
////    }
//    public void setShapeGroup(){
//        groupsList.add(group);
//        this.group = groupsList.get(groupsList.size()-1); // sets the active group to the last one in the list
//    }
//
//    public void addToGroupList(Group group){
//        groupsList.add(group);
//    }
//    public void removeFromGroupList(){
//        removedgroupList.add(groupsList.remove(groupsList.size()-1));
//    }
//
//    public Boolean hasGroup(){
//        return this.group != null;
//    }

//    public int groupCount(){
//        int count = 0;
//        for(Group g: myGroupsList){
//            if(g.getGroupedShapes().contains(this)){
//                count++;
//            }
//        }
//        return count;
//    }


//    @Override
//    public MakeShape getMadeShape() {
//        return null;
//    }
//
//    @Override
//    public Boolean isGroup(){
//        return null;
//    }
}
