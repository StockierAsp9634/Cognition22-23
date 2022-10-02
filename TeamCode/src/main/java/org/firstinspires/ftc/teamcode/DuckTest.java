package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous ;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.concurrent.TimeUnit;
    @Autonomous(name = "DuckTest", group = "Concept")
    public class DuckTest extends LinearOpMode {
        Servo servo;
        Servo servoR;
        Servo servoL;
        public void runOpMode() {
            servo = hardwareMap.get(Servo.class, "duckSpinner");
            servoR = hardwareMap.get(Servo.class, "rightClaw");
            servoL = hardwareMap.get(Servo.class, "leftClaw");
            waitForStart();
            int a= 0;
            while(opModeIsActive()){
                servo.setPosition(+0.1);
                
            }
        }
    }

