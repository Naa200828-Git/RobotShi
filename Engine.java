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

        FrontMotor.setPower(40);
        LeftMotor.setPower(40);
        RightMotor.setPower(40);

        int SLEEP = 600;
        boolean running = true;

        while (running) {

            FrontMotor.setPower(40);
            LeftMotor.setPower(40);
            RightMotor.setPower(40);

            FrontMotor.backward();
            LeftMotor.backward();
            RightMotor.backward();


            SLEEP = 50; // Decrease delay to improve responsiveness.

            int lightValue1 = lightSensorFR.getLightValue();
            int lightValue2 = lightSensorBR.getLightValue();
            int lightValue3 = lightSensorFL.getLightValue();
            int lightValue4 = lightSensorBL.getLightValue();

            if (lightValue1 > 55) {  // Obstacle at front right
                // Forward motion before turning
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();
                try {
                    Thread.sleep(700); // Forward for 700 ms before turning
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Ease out speed before turning
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(5);  
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    FrontMotor.setPower(40 - i * 2);
                    LeftMotor.setPower(40 - i * 2);
                    RightMotor.setPower(40 - i * 2);
                }

                // Turn logic (right turn)
                RightMotor.backward();
                LeftMotor.forward();

                // Longer turn (increase the sleep time for a longer turn)
                try {
                    Thread.sleep(500);  // Turn for 500 ms for a longer duration
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Ease back into forward motion
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(3);  
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    FrontMotor.setPower(40 + i * 2);
                    LeftMotor.setPower(40 + i * 2);
                    RightMotor.setPower(40 + i * 2);

                    lightValue1 = lightSensorBL.getLightValue();
                    lightValue3 = lightSensorBR.getLightValue();

                    if (lightValue1 > 55) {
                        SLEEP = 1;
                        break;
                    }
                }

                try {
                    Thread.sleep(500);  // Turn for 500 ms for a longer duration
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            else if (lightValue3 > 55) {  // Obstacle at front left
                // Similar logic for forward motion before turning left
                LeftMotor.forward();
                RightMotor.forward();
                FrontMotor.forward();
                try {
                    Thread.sleep(700);  // Forward for 700 ms before turning
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(5);  
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    FrontMotor.setPower(40 - 1 * 2);
                    LeftMotor.setPower(40 - i * 2);
                    RightMotor.setPower(40 - i * 2);
                }

                // Turn logic (left turn)
                RightMotor.forward();
                LeftMotor.backward();

                // Longer turn (increase the sleep time for a longer duration)
                try {
                    Thread.sleep(500);  // Turn for 500 ms for a longer duration
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Ease back into forward motion
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(3);  
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    FrontMotor.setPower(40 + i * 2);
                    LeftMotor.setPower(40 + i * 2);
                    RightMotor.setPower(40 + i * 2);

                    lightValue1 = lightSensorBL.getLightValue();
                    lightValue3 = lightSensorBR.getLightValue();

                    if (lightValue3 > 55) {
                        SLEEP = 1;
                        break;
                    }
                }

                try {
                    Thread.sleep(500);  // Turn for 500 ms for a longer duration
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            else {  // No interruption, continue forward
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
