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
        TouchSensor Ts = new TouchSensor(SensorPort.S3);

        LeftMotor.setPower(0);
		RightMotor.setPower(0); 
		FrontMotor.setPower(0);
        
        boolean running = true;
        while (running){
        	
        	if( Ts.isPressed() )  // Check if the touch sensor is pressed, ts test  
        	{

        		LeftMotor.setPower(100);  // Set left motor to 100% power
        		RightMotor.setPower(100); // Set right motor to 100% power
        		FrontMotor.setPower(100); // Set front motor to 100% power
        		
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();
        	}
        	
        	// horrible solution, temp **
        	else // Check if the touch sensor is pressed, ts test  
        	{

        		LeftMotor.setPower(0);  // Set left motor to 100% power
        		RightMotor.setPower(0); // Set right motor to 100% power
        		FrontMotor.setPower(0); // Set front motor to 100% power
        		
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();
        	}

        }

        // retreat

        // turn degree

        // turn while moving

        // % foward

        // % back

        // Senseor Loop


	}// end main
}// end class
