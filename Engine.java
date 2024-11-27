import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.LightSensor;

public class Robot {

    static NXTMotor LeftMotor = new NXTMotor(MotorPort.A);
    static NXTMotor RightMotor = new NXTMotor(MotorPort.B);
    static NXTMotor FrontMotor = new NXTMotor(MotorPort.C);

    static LightSensor lightSensorFR = new LightSensor(SensorPort.S1);  // Front Right
    static LightSensor lightSensorBR = new LightSensor(SensorPort.S2);  // Back Right
    static LightSensor lightSensorFL = new LightSensor(SensorPort.S3);  // Front Left
    static LightSensor lightSensorBL = new LightSensor(SensorPort.S4);  // Back Left

    public static void main(String[] args) {

        FrontMotor.setPower(40);
        LeftMotor.setPower(40);
        RightMotor.setPower(40);

        int SLEEP = 600;
        boolean running = true;

        while (running) {

            FrontMotor.setPower(40);
            LeftMotor.setPower(40);
            RightMotor.setPower(40);

            // Check the light sensor readings for front left and front right
            int lightValueFR = lightSensorFR.getLightValue();  // Right Front
            int lightValueFL = lightSensorFL.getLightValue();  // Left Front
            int lightValueBR = lightSensorBR.getLightValue();  // Back Right
            int lightValueBL = lightSensorBL.getLightValue();  // Back Left

            if (lightValueFR > 55) {  // White object detected on the front right
                // Backup for 700 milliseconds
                LeftMotor.backward();
                RightMotor.backward();
                FrontMotor.backward();
                
                try {
                    Thread.sleep(700);  // Backup for 700 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Turn right after backup
                RightMotor.backward();
                LeftMotor.forward();
                
                try {
                    Thread.sleep(500);  // Turn for 500 ms to the right
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // After the turn, move forward again
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();

                try {
                    Thread.sleep(700);  // Move forward for 700 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            else if (lightValueFL > 55) {  // White object detected on the front left
                // Backup for 700 milliseconds
                LeftMotor.backward();
                RightMotor.backward();
                FrontMotor.backward();
                
                try {
                    Thread.sleep(700);  // Backup for 700 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Turn left after backup
                RightMotor.forward();
                LeftMotor.backward();
                
                try {
                    Thread.sleep(500);  // Turn for 500 ms to the left
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // After the turn, move forward again
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();

                try {
                    Thread.sleep(700);  // Move forward for 700 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            else if (lightValueBR > 55 || lightValueBL > 55) {  // New condition for experimental back sensors
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();

                FrontMotor.setPower(100);
                LeftMotor.setPower(100);
                RightMotor.setPower(100);
            }
            else {  // No interruption, continue moving forward
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();
            }

            try {
                Thread.sleep(SLEEP);  // Main loop delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
