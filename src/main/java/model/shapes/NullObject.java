package model.shapes;

public class NullObject implements INull{


    @Override
    public Boolean isNull() {
        return true;
    }

    @Override
    public int getGroupSize() {
        return 0;
    }

//    @Override
//    public Group getShapeGroup() {
//        return "null";
//    }
}
