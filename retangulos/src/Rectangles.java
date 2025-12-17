public class Rectangles {

    private final double left;
    private final double top;
    private final double right;
    private final double bottom;

    public Rectangles(double xMin,double yMax,double xMax, double yMin) {
        left = xMin;
        top = yMax;
        right = xMax;
        bottom = yMin;
    }

    public double getLeft() {
        return left;
    }

    public double getTop() {
        return top;
    }

    public double getRight() {
        return right;
    }

    public double getBottom() {
        return bottom;
    }

    public double getHeight() {
        return top - bottom;
    }

    public double getWidth() {
        return right - left;
    }

    public double getPerimeter() {
        return 2*(this.getHeight()+this.getWidth());
    }

    public Rectangles getHull(Rectangles other) {

        double xMin = Math.min(left, other.getLeft());
        double yMax = Math.max(top, other.getTop());
        double xMax = Math.max(right, other.getRight());
        double yMin = Math.min(bottom, other.getBottom());

        return new Rectangles(xMin,yMax,xMax,yMin);
    }

    public boolean isSquare() {
        return (this.getHeight() == this.getWidth());
    }
}
