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

        LeftMotor.backward();
        RightMotor.backward();
        FrontMotor.backward();

        FrontMotor.setPower(80);
        LeftMotor.setPower(80);
        RightMotor.setPower(80);

        int SLEEP = 600;
        boolean running = true;
        while (running) {

            slepp = 600; // maybe change to run with small delay for battery ? and when encounters, the delay check is altered

            int lightValue1 = lightSensorFR.getLightValue();
            int lightValue2 = lightSensorBR.getLightValue();  // Corrected sensor read
            int lightValue3 = lightSensorFL.getLightValue();
            int lightValue4 = lightSensorBL.getLightValue();

        	if(lightValue1 > 55 && lightValue3 > 55)/*some shi at both front right and left*/ 
            {
                // go backwards (forwards) same way
                // check if smth at the back from both sensors
                // if so, sleep = 10, & break so loop activates again & backwards detect runs

                    // Ease-out speed the same way as in the other cases
                for (int i = 0; i < 20; i++) 
                {  
                    try 
                    {
                        Thread.sleep(5);  // Short pause to control speed
                    } 
                     catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();  // Restore interrupt status
                    }

                    FrontMotor.setPower(80 - 1 * 4);
                    LeftMotor.setPower(80 - i * 4);
                    RightMotor.setPower(80 - i * 4);
                }

                // Move backward (or forward), same logic as in the other cases
                RightMotor.forward();  // Move backward (reverse the motors accordingly)
                LeftMotor.forward();

                for (int i = 0; i < 20; i++) 
                {  
                    try 
                    {
                        Thread.sleep(5);  // Short pause to control speed
                    } 
                     catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();  // Restore interrupt status
                    }

                    FrontMotor.setPower(80 + 1 * 4);
                    LeftMotor.setPower(80 + i * 4);
                    RightMotor.setPower(80 + i * 4);

                    int lightValue1 = lightSensorBL.getLightValue();
                    int lightValue3 = lightSensorBR.getLightValue();

                    if(lightValue2 > 55 || lightValue4 > 55)
                    {
                        SLEEP = 10;
                        break;
                    }
                }
            }
        	else if(lightValue1 > 55)      // Shi at front right
        	{
                for(int i = 0; i < 20; i ++) // ease out
                {
                    try 
                    {  // Pause logic
                        Thread.sleep(5);  
                    } 
                    catch (InterruptedException e) 
                    {
                        Thread.currentThread().interrupt();  // Restore interrupt status
                    }

                    FrontMotor.setPower(80 - 1 * 4);
                    LeftMotor.setPower(80 - i * 4);
                    RightMotor.setPower(80 - i * 4);

                } 

                RightMotor.backward();  // Left ** the back is the front ngl
                LeftMotor.forward();

                FrontMotor.setPower(0); // turning power
                LeftMotor.setPower(80);
                RightMotor.setPower(80);

                try                     // Turn for how long
                {  // Pause logic
                    Thread.sleep(5);  
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }
                
                for(int i = 0; i < 20; i ++) // ease int
                {
                    try 
                    {  // Pause logic
                        Thread.sleep(5);  
                    } 
                    catch (InterruptedException e) 
                    {
                        Thread.currentThread().interrupt();  // Restore interrupt status
                    }

                    FrontMotor.setPower(80 + 1 * 4);
                    LeftMotor.setPower(80 + i * 4);
                    RightMotor.setPower(80 + i * 4);

                    int lightValue1 = lightSensorBL.getLightValue();
                    int lightValue3 = lightSensorBR.getLightValue();

                    if(lightValue1 > 55)
                    {
                        SLEEP = 10;
                        break;
                    }
                } 
        	}
        	else if(lightValue3 > 55) // some shi at front left
        	{
                for(int i = 0; i < 20; i ++) // ease out
                {
                    try 
                    {  // Pause logic
                        Thread.sleep(5);  
                    } 
                    catch (InterruptedException e) 
                    {
                        Thread.currentThread().interrupt();  // Restore interrupt status
                    }

                    FrontMotor.setPower(80 - 1 * 4);
                    LeftMotor.setPower(80 - i * 4);
                    RightMotor.setPower(80 - i * 4);

                } 

                RightMotor.forward();  // right ** the back is the front ngl
                LeftMotor.backward();

                FrontMotor.setPower(0); // turning power
                LeftMotor.setPower(80);
                RightMotor.setPower(80);

                try                     // Turn for how long
                {  // Pause logic
                    Thread.sleep(5);  
                } 
                catch (InterruptedException e) 
                {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                }
                
                for(int i = 0; i < 20; i ++) // ease int
                {
                    try 
                    {  // Pause logic
                        Thread.sleep(5);  
                    } 
                    catch (InterruptedException e) 
                    {
                        Thread.currentThread().interrupt();  // Restore interrupt status
                    }

                    FrontMotor.setPower(80 + 1 * 4);
                    LeftMotor.setPower(80 + i * 4);
                    RightMotor.setPower(80 + i * 4);

                    int lightValue1 = lightSensorBL.getLightValue();
                    int lightValue3 = lightSensorBR.getLightValue();

                    if(lightValue3 > 55)
                    {
                        SLEEP = 10;
                        break;
                    }
                } 
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
                Thread.sleep(SLEEP);  
            } 
            catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt();  // Restore interrupt status
            }

           	// add ultra sonic sensor 
            
        }
    }
}
   

 
