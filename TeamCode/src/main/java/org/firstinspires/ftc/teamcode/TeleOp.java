package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "Pushbot")
public class TeleOp extends LinearOpMode
{
    private DcMotor upperLeft;
    private DcMotor upperRight;
    private DcMotor arm;

    private Servo Claw;

    @Override
    public void runOpMode() throws InterruptedException
    {
        upperLeft = hardwareMap.dcMotor.get("upperLeft");
        upperRight = hardwareMap.dcMotor.get("upperRight");
        arm = hardwareMap.dcMotor.get("arm");

        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        Claw = hardwareMap.servo.get("armServo");

        waitForStart();

        while(opModeIsActive())
        {
            upperLeft.setPower(-gamepad1.left_stick_y);
            upperRight.setPower(-gamepad1.right_stick_y);
            arm.setPower(-gamepad1.right_trigger);

            if(gamepad1.y)
            {
                Claw.setPosition(0.8);
            }
            if(gamepad1.a)
            {
                Claw.setPosition(0.2);
            }

            idle();
        }
    }
}