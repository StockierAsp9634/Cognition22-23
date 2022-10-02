package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
@TeleOp(name="Teleop2023", group ="ACompetition")
public class Teleop2023 extends OpMode {

        DcMotor upperRight;
        DcMotor upperLeft;
        DcMotor lowerRight;
        DcMotor lowerLeft;

        HardwareMap hwmap;
        public void init() {
            upperRight = hardwareMap.dcMotor.get("upperRight");
            upperLeft  = hardwareMap.dcMotor.get("upperLeft");
            lowerRight = hardwareMap.dcMotor.get("lowerRight");
            lowerLeft  = hardwareMap.dcMotor.get("lowerLeft");
            hwmap = hardwareMap;
        }
        public void loop() {
            upperRight.setPower(-gamepad1.left_stick_y*0.5);
            upperLeft.setPower(gamepad1.left_stick_y*0.5);
            lowerLeft.setPower(gamepad1.left_stick_y*0.5);
            lowerRight.setPower(gamepad1.left_stick_y*0.5);
        }

    }
