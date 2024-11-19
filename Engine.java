import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Robot
{
    public Robot(){}


	public static void main(String[] args) 
    {
		
        NXTMotor LeftMotor = new NXTMotor(MotorPort.A);
        NXTMotor RightMotor = new NXTMotor(MotorPort.B);
        NXTMotor FrontMotor = new NXTMotor(MotorPort.C);
        // TouchSensor Ts = new TouchSensor(SensorPort.S3);
        ;
        
        boolean running = true;
        while (running){
        	
        }

        
        // ALL MOVEMENT ** PARAMTERS (POWER, DIRECTION, EASE_RATIO, CAP), One function for easy writing

            if(DIRECTION)
            {
                LeftMotor.Forward();
                RightMotor.Forward();
                FrontMotor.Forward();
            }
            else
            {
                LeftMotor.Backward();
                RightMotor.Backward();
                FrontMotor.Backward();
            }

            // Ease In ^^ Use for confidence of clear path

            if(EASE_RATIO) // if ease ratio set
            {
                for(int i = 0; i < CAP; i+= EASE_RATIO)
                {
                    LeftMotor.setPower(i);  
                    RightMotor.setPower(i); 
                    FrontMotor.setPower(i); 
                }
            }
            else // Set power to Paramter
            {
        	    LeftMotor.setPower(POWER);  
        	    RightMotor.setPower(POWER); 
        	    FrontMotor.setPower(POWER); 
            }

        // ALL STOPS



        // ALL TURNS ^^ Make legend for wide turns to power settings (LEFT_POWER, RIGHT_POWER,)

        if(LEFT_OR_RIGHT) 
        {
            LeftMotor.Backward(); // turn left
            RightMotor.Forward();
        }
        else
        {
            LeftMotor.Forward(); // turn right
            RightMotor.Backward();
        }

        if(TURN_WHILE_MOVING) // turn while moving, 1. front wheel : ease in, full     
        {
            if(EASE_RATIO)
            {
                for(int i = 0; i < CAP; i+= EASE_RATIO)
                    FrontMotor.setPower(i); 
            }
            else    FrontMotor.setPower(POWER);
        }
        else
        {
            LeftMotor.setPower(LEFT_POWER);  
        	RightMotor.setPower(RIGHt_POWER);
        }



        // Sensor Loop


	}// end main
}// end class
