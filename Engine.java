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

    public static void main(String[] args) {

    	

        boolean running = true;
        while (running) {

            FrontMotor.setPower(80);
            LeftMotor.setPower(80);
            RightMotor.setPower(80);

        	int lightValue1 = lightSensorFR.getLightValue();
            int lightValue2 = lightSensorBL.getLightValue();
            int lightValue3 = lightSensorFR.getLightValue();
            int lightValue4 = lightSensorFL.getLightValue();
        	
        	if(lightValue1 < 50)      // Shi at front right
        	{
        		FrontMotor.setPower(0);
                LeftMotor.setPower(80);
                RightMotor.setPower(80);

                try 
                {  // Pause logic
                    Thread.sleep(200);  
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }
                
                RightMotor.forward();  // Left
                LeftMotor.backward();

                FrontMotor.setPower(0);
                LeftMotor.setPower(100);
                RightMotor.setPower(100);

                try 
                {  // Pause logic
                    Thread.sleep(500);  
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }
                
                LeftMotor.forward();
                RightMotor.forward();
                
                try 
                {  // Pause logic
                    Thread.sleep(500);  
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }
        	}
        	else if(lightValue3 < 50) // some shi at front left
        	{
                RightMotor.forward();  // Left
                LeftMotor.backward();

                FrontMotor.setPower(0);
                LeftMotor.setPower(100);
                RightMotor.setPower(100);

                try 
                {  // Pause logic
                    Thread.sleep(500);  
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }
                
                LeftMotor.forward();
                RightMotor.forward();
                
                try 
                {  // Pause logic
                    Thread.sleep(500);  
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }
                
                LeftMotor.forward();
                RightMotor.forward();
                
        	}        	
        	//else if(lightValue2 < 50)
        	//{
        	//	LeftMotor.forward();
            //    RightMotor.forward();
        	//}
        	//else if(lightValue4 < 50)
           	//{
            //    LeftMotor.forward();
            //    RightMotor.forward();
            //}
        	
        	try 
            {  // Pause logic
                Thread.sleep(1000);  
            } 
            catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();  // Restore interrupt status
            }

            FrontMotor.forward();
            LeftMotor.forward();
            RightMotor.forward();

           	// add ultra sonic sensor to work, finish over weekend, track turn with angle of side of octogon
        	// also track last turn and time between
        }
    }
}
   

 
