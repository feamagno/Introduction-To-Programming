public class Circles {

    private final double x;
    private final double y;
    private final double radius;

    public Circles(double x1,double y2, double radius1){
        x = x1;
        y = y2;
        radius = radius1;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getRadius(){
        return radius;
    }
    private double getTwoPoints(double x1,double y1,double x2, double y2){
        //calculates distance between two points
        double linex = x2-x1;
        double liney = y2-y1;
        return (Math.sqrt((Math.pow(linex,2)+Math.pow(liney,2))));
    }
    public double getPerimeter(){
        //returns circles perimeter
        return (2*Math.PI*radius);
    }

    public double getArea(){
        //returns circles area
        return (Math.PI*Math.pow(radius,2));
    }

    public boolean isPointInside(double xP, double yP){
        //figures if point is inside the circle
        double line = getTwoPoints(x,y,xP,yP);
        return (line <= radius);
    }

    public String isCircleInside(Circles c2){
        //checks relation with circle
        double x2 = c2.getX();
        double y2 = c2.getY();
        double radius2 = c2.getRadius();

        double dist = getTwoPoints(x,y,x2,y2);

        if (dist+radius2<=radius){
            return "contains";
        }
        else if (dist <= radius+radius2){
            return "intersects";
        }
        else {
            return "nothing";
        }
    }
}
