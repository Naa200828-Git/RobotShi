import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/* MOVE, TURN, STOP

    these are all function names,
    they are structured like this:
    
    -- MOVE(boolean DIRECTION, int POWER, int EASE_RATIO, int CAP, double DISTANCE, intTIME)

    it takes a direction 0 = backwards, 1 = forwards, a power setting 0-100, 
    and if you want it to ease in, have a non zero ease in ration, witch is how 
    many steps it takes (e.g. +1 or +5, ect..) and cap is the max
    it power / speed it can reach, it also takes in a pause time,
    just use 10 as a default, its mean 10 miliseconds pause until it 
    increases the power by a step, but you can customize it, 
    you can set all of these, if you dont want to ease in at all and just jump 
    to full power do not touch ease in, just leave, but if you do use ease in,
    make sure CAP is set, a call would look like:

    Ex:

    MOVE(1, 100, 20, 5, 10, 10);

    This will move Forward, 
    increment in power / speed by 20, 5 times
    and takes a 10 ms pause to increment power / speed

    to move a set distance :

    -- TURN
*/



public class Robot {
    
    static NXTMotor LeftMotor = new NXTMotor(MotorPort.A);
    static NXTMotor RightMotor = new NXTMotor(MotorPort.B);
    static NXTMotor FrontMotor = new NXTMotor(MotorPort.C);

    public static void main(String[] args) {
    /* 
        The main is used for testing whatever,
        I made all the code very easy to use,
        If you want to test the engine, just 
        uncomment INIT(); and make sure all of 
        your other code is below that statement.

        keep it in a comment if you want to write
        test code, dont mess w/ the engine
    */
        // INIT();
        
        // Testing Code ONLY Below:



    }

    public static void INIT() {

        List<List<Boolean>> MAP = new ArrayList<>(); // determine cell size and saftey cus wtf should i do with 3 sensors

        boolean running = true;
        while (running) {

            // tracks coordinates and inputs white or black / wall or road, use to determine plausible lines of travell for smarter, saffer & faster movement
            // rember to not trust anymore if collision occurs

    
    
            }


        }
    } // wtf? fix this

    public static void MOVE(boolean DIRECTION, int POWER, int EASE_RATIO, int CAP, int SLEEP, int DISTANCE) {
        if (DIRECTION) {
            LeftMotor.forward();
            RightMotor.forward();
            FrontMotor.forward();
        } else {
            LeftMotor.backward();
            RightMotor.backward();
            FrontMotor.backward();
        }

        if (EASE_RATIO > 0) {  // builds up speed until max
            for (int i = 0; i < CAP; i += EASE_RATIO) {
                LeftMotor.setPower(i);
                RightMotor.setPower(i);
                FrontMotor.setPower(i);

                try {  // Pause logic
                    Thread.sleep(SLEEP);  // 10-millisecond sleep
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }

            }
        } else {
            LeftMotor.setPower(POWER);
            RightMotor.setPower(POWER);
            FrontMotor.setPower(POWER);
        }
    }

    public static void STOP() {
        LeftMotor.stop();
        RightMotor.stop();
        FrontMotor.stop();
        // Additional stop logic if necessary
    }

    public static void TURN(boolean LEFT_OR_RIGHT, int LEFT_POWER, int RIGHT_POWER, 
                            boolean TURN_WHILE_MOVING, int POWER, int EASE_RATIO, int CAP, int SLEEP) {

        if (LEFT_OR_RIGHT) {
            LeftMotor.backward();
            RightMotor.forward();
        } else {
            LeftMotor.forward();
            RightMotor.backward();      
        }

        if (TURN_WHILE_MOVING) {

            if (EASE_RATIO > 0) {
                for (int i = 0; i < CAP; i += EASE_RATIO) {
                    FrontMotor.setPower(i);

                    try {   // Pause logic
                        Thread.sleep(SLEEP);  // 10-millisecond sleep
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();  // Restore interrupt status
                    }

                }
            } else { // No ease in, just set power
                FrontMotor.setPower(POWER);
            }

        } else { // No forward while moving, just set side wheels
            LeftMotor.setPower(LEFT_POWER);
            RightMotor.setPower(RIGHT_POWER);
        }
    }
}
