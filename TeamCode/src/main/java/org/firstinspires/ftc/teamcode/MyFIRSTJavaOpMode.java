package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Test: By Sudheer", group ="Test")
public class MyFIRSTJavaOpMode extends LinearOpMode {
    // Declare our motors
    // Make sure your ID's match your configuration
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackRight;
    DcMotor servoArm;
    Servo servoDuckSpinner;
    Servo servoRightClaw;
    Servo gripServo;
    double  armPosition, gripPosition, contPower;
    double  MIN_POSITION = 0, MAX_POSITION = 1;

    // Declare variables
    boolean lastA = false;                      // Use to track the prior button state.
    double servo_duckspinner_power;
    double servo_arm_pos;
    boolean secondHalf = false;                 // Use to hint the drivers for end game start
    final double HALF_TIME = 60.0;              // Wait this many seconds before alert for half-time
    ElapsedTime runtime = new ElapsedTime();    // Use to determine when end game is starting.

    static final double SERVO_DUCKSPINNER_STOP = 0;
    static final double SERVO_DUCKSPINNER_SPIN_FORWARD = 1;
    static final double SERVO_DUCKSPINNER_SPIN_REVERSE = -1;
    static final double SERVO_ARM_STOP = 0;
    static final double SERVO_ARM_UP = 0.25;
    static final double SERVO_ARM_DOWN = -0.25;
    static final double MOTOR_TICK_COUNT = 1120;
    static final double MAX_POS     =  1.0;     // Maximum rotational position for gripper
    static final double MIN_POS     =  0.0;     // Minimum rotational position for gripper
    double  position = MIN_POS; // Start at minimum position position  for gripper
    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle

    Gamepad.RumbleEffect customRumbleEffect;    // Use to build a custom rumble sequence.

    public void runOpMode() throws InterruptedException {

        motorFrontLeft = hardwareMap.dcMotor.get("upperLeft"); //motorFrontLeft
        motorBackLeft = hardwareMap.dcMotor.get("lowerLeft"); //motorBackLeft
        motorFrontRight = hardwareMap.dcMotor.get("upperRight"); //motorFrontRight
        motorBackRight = hardwareMap.dcMotor.get("lowerRight"); //motorBackRight
        servoArm = hardwareMap.dcMotor.get("arm"); //arm
        //servoDuckSpinner = hardwareMap.get(Servo.class, "duckSpinner");
        servoDuckSpinner = hardwareMap.get(Servo.class,"duckSpinner");
        //servoRightClaw = hardwareMap.get(Servo.class, "rightClaw");
        gripServo = hardwareMap.get(Servo.class, "leftClaw");

        double quarterTurn = MOTOR_TICK_COUNT / 4;
        servoArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        servoArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //sets the counter of ticks to 0
        //servoArm.setZeroPowerBehavior(DcMotor.ZeroPowerBrake.BRAKE);

        customRumbleEffect = new Gamepad.RumbleEffect.Builder()
                .addStep(0.0, 1.0, 500)  //  Rumble right motor 100% for 500 mSec
                .addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .addStep(0.0, 0.0, 250)  //  Pause for 250 mSec
                .addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .build();

        servo_duckspinner_power = SERVO_DUCKSPINNER_STOP;

        //servoDuckSpinner.setPower(servo_duckspinner_pos);

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

        servoArm.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("TeleOp>", "Press Start");
        telemetry.update();
        waitForStart();
        runtime.reset();    // Start game timer.

        telemetry.addData("TeleOp>", "Stage 1");
        telemetry.update();

        if (isStopRequested()) return;

        armPosition = .5;                   // set arm to half way up.
        gripPosition = MAX_POSITION;        // set grip to full open.

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            if ((runtime.seconds() > HALF_TIME) && !secondHalf)  {
                gamepad1.runRumbleEffect(customRumbleEffect);
                secondHalf =true;
            }

            if (!secondHalf) {
                telemetry.addData(">", "Halftime Alert Countdown: %3.0f Sec \n", (HALF_TIME - runtime.seconds()) );
            }

         /*   if (gamepad1.x) {
                servo_duckspinner_power = SERVO_DUCKSPINNER_SPIN_FORWARD;
                telemetry.addData("Duck>", "Start Spin");
            }
            if (gamepad1.b) {
                servo_duckspinner_power = SERVO_DUCKSPINNER_STOP;
                telemetry.addData("Duck>", "Stop Spin");
            } */

            if (gamepad1.x){
                servo_duckspinner_power = .20;

                servoDuckSpinner.setPosition(servo_duckspinner_power);

            telemetry.addData("Duck>", "Start Spin");
            }
            else if (gamepad1.b) {
               // servo_duckspinner_power = .0;
              //  servoDuckSpinner.setPosition(servo_duckspinner_power);
                servoDuckSpinner.setPosition(0.0);
                telemetry.addData("Duck>", "Stop Spin");
            }



            if (gamepad1.dpad_up) {
                //int newTarget = servoArm.getTargetPosition() + (int)quarterTurn;
                servoArm.setTargetPosition(1000);
                servoArm.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Tell the motor to run to the target
                servoArm.setPower(SERVO_ARM_UP);
            }
            else {
                if (gamepad1.dpad_down) {
                    //int newTarget = servoArm.getTargetPosition() - (int) quarterTurn;
                    servoArm.setTargetPosition(0);
                    servoArm.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Tell the motor to run to the target
                    servoArm.setPower(SERVO_ARM_STOP);
                }
            }

            while (servoArm.isBusy()) {
                telemetry.addData("Arm>", "Up " + servoArm.getTargetPosition());
                telemetry.update();
            }

           /* if (Math.abs(target - servoArm.getTargetPosition()) < 100)
                speed = 0.1;
            else
                speed = 0.8;

            */

            if (gamepad2.a) {
                // Keep stepping up until we hit the max value.
                position += INCREMENT ;
                if (position >= MAX_POS ) {
                    position = MAX_POS;
                }
            }
            else if(gamepad2.b) {
                // Keep stepping down until we hit the min value.
                position -= INCREMENT ;
                if (position <= MIN_POS ) {
                    position = MIN_POS;
                }
            }

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFrontLeft.setPower(frontLeftPower);
            motorBackLeft.setPower(backLeftPower);
            motorFrontRight.setPower(frontRightPower);
            motorBackRight.setPower(backRightPower);

            telemetry.update();
        }

        telemetry.addData("Game>", "Over");
        telemetry.update();
    }
}