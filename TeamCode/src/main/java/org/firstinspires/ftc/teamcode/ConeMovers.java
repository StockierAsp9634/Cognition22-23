package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="ConeMover", group="Autonomous")
public class ConeMovers extends LinearOpMode {


    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double TURN_SPEED = 0.25;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        TurnRight(1500);
        TurnLeft(1500);
        StrafeRight(4000);
        MoveForward(6000);
    }
    //Motor 1: Normal
    //Motor 2: Reverse
    //Motor 3: Normal
    //Motor 4: Reverse
    public void MoveForward(long timeoutS) {
        if (opModeIsActive()) {
            // Remember 2 and 3 are reverse
            robot.setMotorPowers(TURN_SPEED, TURN_SPEED, -TURN_SPEED, -TURN_SPEED, 0);
            runtime.reset();
            sleep(timeoutS);
            robot.setMotorPowers(0);
            //  runtime.reset();
        }
    }

    public void TurnLeft(long timeoutB){

        if (opModeIsActive()){

            robot.setMotorPowers(-TURN_SPEED, -TURN_SPEED, -TURN_SPEED, -TURN_SPEED,0);
            runtime.reset();
            sleep(timeoutB);
            robot.setMotorPowers(0);
        }

    }
    public void TurnRight(long timeoutC){

        if (opModeIsActive()){

            robot.setMotorPowers(TURN_SPEED, TURN_SPEED, TURN_SPEED, TURN_SPEED,0);
            runtime.reset();
            sleep(timeoutC);
            robot.setMotorPowers(0);
        }

    }
    public void StrafeRight(long timeoutD){
//Move Backward because motor 1 and 4 are negative
        if (opModeIsActive()){

            robot.setMotorPowers(TURN_SPEED, -TURN_SPEED, TURN_SPEED, -TURN_SPEED,0);
            runtime.reset();
            sleep(timeoutD);
            robot.setMotorPowers(0);
        }

    }

}



