package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwarePushbot
{
    /* Public OpMode members. */
    public DcMotor  upperLeft   = null;
    public DcMotor  upperRight = null;
    public DcMotor  lowerLeft = null;
    public DcMotor  lowerRight = null;
  //  public DcMotor  arm = null;
  //  public Servo duckSpinner = null;
  //  public Servo ServoR= null;
  //  public Servo ServoL = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwarePushbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
//        upperLeft  = hwMap.get(DcMotor.class, "upperLeft");
//        upperRight  = hwMap.get(DcMotor.class, "upperRight");
//        lowerLeft  = hwMap.get(DcMotor.class, "lowerLeft");
//        lowerRight  = hwMap.get(DcMotor.class, "lowerRight");
        upperLeft = hwMap.dcMotor.get("upperLeft"); //motorFrontLeft
        upperRight = hwMap.dcMotor.get("lowerLeft"); //motorBackLeft
        lowerLeft = hwMap.dcMotor.get("upperRight"); //motorFrontRight
        lowerRight = hwMap.dcMotor.get("lowerRight"); //motorBackRight
    //    arm  = hwMap.get(DcMotor.class, "arm");
    //    duckSpinner = hwMap.get(Servo.class, "duckSpinner");
    //    ServoR = hwMap.get(Servo.class, "rightClaw");
    //    ServoL = hwMap.get(Servo.class, "leftClaw");

        // Reversing left motors
//        upperLeft.setDirection(DcMotor.Direction.FORWARD );
//        upperRight.setDirection(DcMotor.Direction.REVERSE);
//        lowerLeft.setDirection(DcMotor.Direction.REVERSE);
//        lowerRight.setDirection(DcMotor.Direction.FORWARD);

        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //upperRight.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerRight.setDirection(DcMotorSimple.Direction.REVERSE);
    //    arm.setDirection(DcMotor.Direction.FORWARD );

        // Set all motors to zero power'
        setMotorPowers(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        upperLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        upperRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lowerLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lowerRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      //  arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Sets all motor powers to given speeds.
     * @param LFPower
     * @param RFPower
     * @param LBPower
     * @param RBPower
     */
    public void setMotorPowers(double LFPower, double RFPower, double LBPower, double RBPower, double APower) {
        upperLeft.setPower(LFPower);
        upperRight.setPower(RFPower);
        lowerLeft.setPower(LBPower);
        lowerRight.setPower(RBPower);
      //  arm.setPower(APower);

    }

    public void setMotorPowers(double allPower) {
        setMotorPowers(allPower, allPower, allPower, allPower, allPower);
    }
}

