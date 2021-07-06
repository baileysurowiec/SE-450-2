package controller;

// use to find coordinates of mouse click and release point
public class Coordinates {
    private int x;
    private int y;
    private Coordinates coordinates;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(){
        this.y = y;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }
}
