import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * <p>A variation of an actor that maintains a precise location (using doubles for the co-ordinates
 * instead of ints).  This allows small precise movements (e.g. movements of 1 pixel or less)
 * that do not lose precision.0
 * 

 * <p>Modified by Jordan Cohen to include a precise rotation variable, as well as turn, setRotation 
 * and turnTowards methods.</p>
 * 
 * <p>To use this class, simply have all of the Actors that need to move smoothly inherit from this
 * class. This class adds new versions of move, turn and setLocation which take doubles. It also adds the
 * following methods to access the precise values:</p>
 * <ul>
 * <li><code>getPreciseX, getPreciseY -></code> Retrieves precise values</li>
 * <li><code>getPreciseRotation -></code> gets the precise angle</li>
 * <li><code>turnTowards (Actor) -></code> an added bonus - turn towards another Actor instead of an xy position</li>
 * </ul>
 * <p>Version 3.1 update - Now includes the option to enable static rotation, meaning the Actor will remain
 *    facing the same direction visually even while turning and moving as desired. Call the method enableStaticRotation() 
 *    to try this out</p>
 * <p>Version 3.2 update (11/23) - Now includes the ability to rotate images manually for SuperSmoothMover objects
 *    with staticRotation enabled. (Note that these new commands will do nothing if sR is disabled)</p>
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 * @author Neil Brown
 * 
 * @version 3.2.jc -- Modified by Jordan Cohen
 * 
 */
public abstract class SuperSmoothMover extends Actor
{
    private double exactX;
    private double exactY;
    private double preciseRotation;
    private boolean staticRotation = false;
    private double cosRotation;
    private double sinRotation;

    public SuperSmoothMover (){
        staticRotation = false;
    }
    
    /**
     * Move forward by the specified distance.
     * (Overrides the method in Actor).
     * 
     * @param distance  the distance to move in the current facing direction
     */
    @Override
    public void move(int distance)
    {
        move((double)distance);
    }

    /**
     * Move forward by the specified exact distance.
     * 
     * @param distance the precise distance to move in the current facing direction
     */
    public void move(double distance)
    {
        if (cosRotation == 0 && sinRotation == 0){
            setRotation(0);
        }
        double dx = cosRotation * distance;
        double dy = sinRotation * distance;
        setLocation(exactX + dx, exactY + dy);
    }

    /**
     * Static rotation means that the IMAGE WILL NOT ROTATE. The turn, turnTowards and move commands
     * will still work, and the Actor will move in the appropriate direction, but the image's facing angle
     * will not change. Note that the disableStaticRotation() method can be used to turn this off.
     */
    public void enableStaticRotation (){
        super.setRotation(0);
        staticRotation = true;
        preciseRotation = 0.0;
    }

    /**
     * This will turn off static rotation. Note that this will not do anything by default as static rotation
     * is disabled. 
     */
    public void disableStaticRotation (){
        super.setRotation((int)(preciseRotation + 0.5));
        staticRotation = false;
    }

    /** 
     * Rotate image, not movement facing angle
     * 
     * Needs to be improved / added to.
     */

    public void rotateImage(int degrees) {
        if (!staticRotation){return;}
        turnImage(degrees);
    }

    /**
     * Set the internal rotation value to a new value.
     * 
     * @param preciseRotation the precise new angle
     */
    public void setRotation (double preciseRotation){
        this.preciseRotation = preciseRotation;
        if(!staticRotation)
            super.setRotation ((int)(preciseRotation + 0.5));
        cosRotation = Math.cos(Math.toRadians(preciseRotation));
        sinRotation = Math.sin(Math.toRadians(preciseRotation));
    }

    /**
     * Set the internal rotation value to a new value. This will override the method from Actor.
     * 
     * @param preciseRotation the new angle
     */
    //@Override
    public void setRotation (int preciseRotation){
        //this.preciseRotation = preciseRotation;
        //if(!staticRotation)
        //    super.setRotation(preciseRotation);
        setRotation ((double)preciseRotation);
    }

    /**
     * Set the internal rotation to face towards a given point. This will override the method from Actor.
     * 
     * @param x the x coordinate to face
     * @param y the y coordinate to face
     */
    //@Override
    public void turnTowards (double x, double y){
        setRotation(Math.toDegrees(Math.atan2(y - getY() , x - getX())));
    }

    /**
     * A short-cut method that I (Jordan Cohen) always thought Greenfoot should have - use the
     * tuntToward method above to face another Actor instead of just a point. Keeps calling code
     * cleaner. 
     * 
     * @param a     The Actor to turn towards. 
     */
    public void turnTowards (Actor a){
        turnTowards (a.getX(), a.getY());
    }

    /**
     * Turn a specified number of degrees.
     * 
     * @param angle     the number of degrees to turn.
     */
    @Override
    public void turn (int angle){
        preciseRotation += angle;
        if(!staticRotation)
            super.setRotation ((int)(preciseRotation + 0.5));
        cosRotation = Math.cos(Math.toRadians(preciseRotation));
        sinRotation = Math.sin(Math.toRadians(preciseRotation));
    }

    /**
     * Turn a specified number of degrees with precision.
     * 
     * @param angle     the precise number of degrees to turn
     */
    public void turn (double angle){
        preciseRotation += angle;
        if(!staticRotation)
            super.setRotation ((int)(preciseRotation + 0.5));
        cosRotation = Math.cos(Math.toRadians(preciseRotation));
        sinRotation = Math.sin(Math.toRadians(preciseRotation));
    }

    /**
     * Set the location using exact coordinates.
     * 
     * @param x the new x location
     * @param y the new y location
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) (x + 0.5), (int) (y + 0.5));
    }

    /**
     * Set the location using integer coordinates.
     * (Overrides the method in Actor.)    
     * 
     * @param x the new x location
     * @param y the new y location
     */
    @Override
    public void setLocation(int x, int y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }

    /**
     * Return the exact x-coordinate (as a double).
     * 
     * @return double   the exact x coordinate, as a double
     */
    public double getPreciseX() 
    {
        return exactX;
    }

    /**
     *   original name from SmoothMover, for compatibility. Same as above.
     *   
     *   @return double the exact x coordinate, as a double
     */
    public double getExactX(){
        return exactX;
    }

    /**
     * Return the exact y-coordinate (as a double).
     * 
     * @return double   the exact x coordinate, as a double
     */
    public double getPreciseY() 
    {
        return exactY;
    }

    /**
     *   original name from SmoothMover, for compatibility. Same as above.
     *   
     *   @return double the exact y coordinate, as a double
     */
    public double exactY(){
        return exactY;
    }

    /**
     * Turn the IMAGE. This will not affect the movement rotation.
     * 
     * @param degrees   The delta to be applied to the image's angle
     */
    public void turnImage (int degrees){
        setImageRotation (getImageRotation() + degrees);
    }

    /**
     * Get the precise movement rotation of this Actor
     */
    public double getPreciseRotation (){
        return preciseRotation;
    }

    /**
     * Get the current state of the image's rotation. If static rotation is
     * enabled, this will be different from the movement rotation, otherwise
     * it is the same (and you probably shouldn't be calling this method if
     * you are not using static rotation - use getRotation() or getPreciseRotation()
     * instead.
     * 
     * @return int the current rotation of the image
     */
    public int getImageRotation() {
        return super.getRotation();
    }

    /**
     * Set the desired angle for the image. Note that if static rotation is not\
     * enabled, this method will do nothing.
     */
    public void setImageRotation (double rotation){
        if (!staticRotation) return;
        setImageRotation((int)(rotation + 0.5));
    }

    public void setImageRotation(int rotation) {
        if (!staticRotation) return;
        super.setRotation (rotation);
    }

    @Override
    public int getRotation (){
        if (!staticRotation){
            return super.getRotation();
        } else {
            return (int)(preciseRotation + 0.5);
        }
    }

}
