package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Motor Test", group="Autonomous")
public class MotorTest extends LinearOpMode {

    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double TURN_SPEED = 0.25;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();
        RunMotors(10000);

    }

    public void RunMotors(long timeoutA) {
        if (opModeIsActive()) {
            robot.setMotorPowers(0*TURN_SPEED, 3*TURN_SPEED, 2*TURN_SPEED, 1*TURN_SPEED, 0);
            runtime.reset();
            sleep(timeoutA);
            robot.setMotorPowers(0);
        }
    }
}