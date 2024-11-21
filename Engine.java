import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.LightSensor;



public class Robot {
    
    static NXTMotor LeftMotor = new NXTMotor(MotorPort.A);
    static NXTMotor RightMotor = new NXTMotor(MotorPort.B);
    static NXTMotor FrontMotor = new NXTMotor(MotorPort.C);
    
    static LightSensor lightSensorFR = new LightSensor(SensorPort.S1);
    static LightSensor lightSensorBR = new LightSensor(SensorPort.S2);
    static LightSensor lightSensorFL = new LightSensor(SensorPort.S3);
    static LightSensor lightSensorBL = new LightSensor(SensorPort.S4);

    public static void WAIT(int SLEEP) // in miliseconds
    {
        try {  // Pause logic
            Thread.sleep(SLEEP);  // 10-millisecond sleep
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restore interrupt status
        }
    }

    public static void MOVE(boolean DIRECTION, int POWER, int EASE_RATIO, int CAP, int SLEEP) {
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

                WAIT(SLEEP);

            }
        } else {
            LeftMotor.setPower(POWER);
            RightMotor.setPower(POWER);
            FrontMotor.setPower(POWER);
        }
    }

    public static void STOP(boolean lm, boolean rm, boolean fm) {
        if(lm)
        	LeftMotor.setPower(0);
        if(rm)
            RightMotor.setPower(0);
        if(fm)
            FrontMotor.setPower(0);
    }

    public static void TURN(boolean LEFT_OR_RIGHT, int LEFT_POWER, int RIGHT_POWER, 
                            int FRONT_POWER, boolean TURN_WHILE_MOVING, int EASE_RATIO, int CAP, int SLEEP) 
    {

        if (LEFT_OR_RIGHT) { 
            LeftMotor.backward(); // left
            RightMotor.forward();
        } else {
            LeftMotor.forward();  // right
            RightMotor.backward();      
        }

        if (TURN_WHILE_MOVING) {

            if (EASE_RATIO > 0) {
                for (int i = 0; i < CAP; i += EASE_RATIO) {
                    FrontMotor.setPower(i);

                    WAIT(SLEEP);

                }
            } else { // No ease in, just set power
                FrontMotor.setPower(FRONT_POWER);
            }

        } else { // No forward while moving, just set side wheels
            LeftMotor.setPower(LEFT_POWER);
            RightMotor.setPower(RIGHT_POWER);
        }
    }

    
    public static void main(String[] args) {


        boolean running = true;
        while (running) {

        	int lightValue1 = lightSensorFR.getLightValue();
            int lightValue2 = lightSensorBL.getLightValue();
            int lightValue3 = lightSensorFR.getLightValue();
            int lightValue4 = lightSensorFL.getLightValue();
        	
        	if(lightValue1 < 50)
        	{}
        	if(lightValue2 < 50)
        	{}        	
        	if(lightValue3 < 50)
        	{}
           	if(lightValue4 < 50)
           	{}
           	
           	// add ultra sonic sensor to work, finish over weekend, track turn with angle of side of octogon
        }
    }
}
   

 
