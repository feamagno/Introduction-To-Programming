public class Robot {

    //setting constants
    private static final int N = 100;
    private static final int M = 100;
    private static final int MIN = 1;

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;

    //setting variables
    private int x, y, aut, pseudoX, pseudoY, pseudoAut;

    public Robot(int x, int y, int aut){
        //constructor
        this.x = x;
        this.y = y;
        this.aut = aut;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getAut(){return this.aut;}

    public boolean willCollide(Robot other, int distance, int direction){
        //returns true if robots collide

        int ox = other.getX();
        int oy = other.getY();

        boolean collision = false;

        int dx = this.x - ox;
        int dy = this.y - oy;

        switch (direction){
            case UP ->
                collision = ((dx == 0) && (dy < 0) && ((y + distance) >= oy ));
            case DOWN ->
                collision = ((dx == 0) && (dy > 0) && ((y - distance) <= oy ));
            case RIGHT ->
                collision = ((dy == 0) && (dx < 0) && ((x + distance) >= ox ));
            case LEFT ->
                collision = ((dy == 0) && (dx > 0) && ((x - distance) <= ox ));
        }
        return collision;
    }

    public boolean hasBattery(int distance,int aut){
        return (distance <= aut);
    }

    //assuming robots won't collide
    //checks if robot has autonomy to do so
    public int move(int distance, int direction, int x_, int y_, int isMoving, int aut) {

        int old_y, old_x, movey, movex;

        old_y = y_;
        old_x = x_;
        movey = y_;
        movex = x_;

        int moved;

        switch (direction) {
            case UP ->
                movey = Math.min(movey + distance, M);
            case DOWN ->
                movey = Math.max(movey - distance, MIN);
            case RIGHT ->
                movex = Math.min(movex + distance, N);
            case LEFT ->
                movex = Math.max(movex - distance, MIN);
        }

        moved = Math.abs(movey - old_y) + Math.abs(movex - old_x);
        if (this.hasBattery(moved, aut)) {
            if (isMoving == 1) {
                this.x = movex;
                this.y = movey;
                this.aut -= moved;
            } else if (isMoving == 0) {
                this.pseudoX = movex;
                this.pseudoY = movey;
                this.pseudoAut -= moved;
            }
        }
        else {
            moved = -1;
        }
        return moved;
    }

    public int closestWall(Robot other, boolean moving){

        int rX, rY;

        if (moving){
            rX = this.x;
            rY = this.y;
        }
        else {
            rX = this.pseudoX;
            rY = this.pseudoY;
        }

        int horizontal, vertical, direction, hdirection, vdirection;

        //checking which side is the closest (vertical)
        if (rY >= (M/2)){
            vdirection = UP;
        } else {vdirection = DOWN;}

        //checking which side is the closest (horizontal)
        if (rX >= (N/2)){
            hdirection = RIGHT;
        } else {hdirection = LEFT;}

        //calculating distance to closest wall (vertical)
        if (vdirection == UP){
            vertical = Math.abs(rY - M);
        }
        else {
            vertical = Math.abs(rY - MIN);
        }
        //calculating distance to closest wall (vertical)
        if (hdirection == RIGHT){
            horizontal = Math.abs(rX - N);
        }
        else {
            horizontal = Math.abs((rX - MIN));
        }

        /*
            vertical checks if robot will collide
            if does and is at position y == (M/2) robot will automatically return DOWN
            if it does and is at any other point, vertical value = -1
        */
        if (this.willCollide(other, M, vdirection)){
            if (rY == (M/2)){
                vdirection = DOWN;
            } else {
                vertical = -1;
            }
        }
        /*
            horizontal checks if robot will collide
            if it does and x == N/50 hdirection is equal to the other direction
            if it does and x =/= horizontal value equals to -1
        */
        if (this.willCollide(other, M, hdirection)){
            if (rX == (N/2)){
                hdirection = LEFT;
            } else {
                horizontal = -1;
            }
        }

        //checks if there is a collision
        if ((horizontal > 0)&&(vertical>0)) {

            //considering there isn't a collision, checks which side is closest to wall
            if (horizontal > vertical) {
                direction = vdirection;
            }
            //if they are at the same distance, horizontal int are lower and have priority
            else if (horizontal == vertical) {
                direction = vdirection;
            }
            else {
                direction = hdirection;
            }
        }
        else {

            //considering there is a collision, returns other side
            if (vertical < 0){
                direction = hdirection;
            }
            else {
                direction = vdirection;
            }
        }
        return direction;
    }

    public boolean robotReturn(Robot other,int aut ,int x ,int y, boolean moving){

        int direction = this.closestWall(other, moving);
        int distance;
//        System.out.printf("cw: %d\n",direction);

        switch (direction) {
            case 1 -> distance = Math.abs(y - M);
            case 2 -> distance = Math.abs(y - MIN);
            case 3 -> distance = Math.abs(x - N);
            case 4 -> distance = Math.abs(x - MIN);
            default -> distance = 0;
        }

       return this.hasBattery(distance,aut);
    }

    public boolean survey(Robot other, int xi, int yi){

        boolean survey, wontCollide = false, firstMovement, returning;

        this.pseudoAut = this.aut;
        this.pseudoX = this.x;
        this.pseudoY = this.y;

        //setting up distance and direction to use move
        int x = xi - this.x;
        int y = yi - this.y;

        int xDistance = Math.abs(x);
        int yDistance = Math.abs(y);

        int xDirection, yDirection;

        if (y >= 0){
            yDirection = 1;
        }
        else {
            yDirection = 2;
        }

        if (x >= 0){
            xDirection = 3;
        }
        else {
            xDirection = 4;
        }

        //checking if moving is possible
        //if variable move is < 0, robot doesn't have autonomy to do the first movement
        //if move is possible, firstMovement = true;

        boolean collide1, collide2, collide3, collide4;

        Robot r3 = new Robot(this.x,this.y,this.aut);

        collide1 = !(r3.willCollide(other, xDistance, xDirection));

        if (collide1){
            r3.move(xDistance,xDirection,r3.getX(),r3.getY(),1,1000);

            collide2 = !(r3.willCollide(other,yDistance,yDirection));

            if (collide2){
                wontCollide = true;
            }
        }

        Robot r4 = new Robot(this.x, this.y, this.aut);

        collide3 = !(r4.willCollide(other, yDistance, yDirection));

        if (collide3){
            r4.move(xDistance,xDirection,r4.getX(),r4.getY(),1,1000);

            collide4 = !(r4.willCollide(other,xDistance,xDirection));

            if (collide4){
                wontCollide = true;
            }
        }

        int movex, movey;

        Robot r5 = new Robot(this.x,this.y,this.aut);

        movex = r5.move(xDistance,xDirection,r5.getX(),r5.getY(),1,r5.getAut());
        movey = r5.move(yDistance,yDirection,r5.getX(),r5.getY(),1,r5.getAut());

        firstMovement = ((movex>=0)&&(movey>=0));

        //checking if returning is possible
        returning = r5.robotReturn(other,r5.getAut(), r5.getX(), r5.getY(), true);

        //checking if everything is possible
        survey = (wontCollide && firstMovement && returning);

        return survey;
    }
}