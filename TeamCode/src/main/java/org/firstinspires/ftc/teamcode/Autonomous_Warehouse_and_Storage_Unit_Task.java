/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Autonomous code for programmer training. Will move forward for 5 seconds and turn for 3 seconds.
 */

@Autonomous(name="Autonomous_1", group="Autonomous")
public class Autonomous_Warehouse_and_Storage_Unit_Task extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double FORWARD_SPEED = 1;
    static final double BACKWARD_SPEED = -1;
    static final double TURN_SPEED = 0.25;
    Servo duckSpinner;
    Servo servoR;
    Servo servoL;

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
        duckSpinner = hardwareMap.get(Servo.class, "duckSpinner");
        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        DriveToSpinner(2500);
        SpinDuck(5000);
        StrafeLeft(10000);
        StrafeRight(10000);
//        while (opModeIsActive() && runtime.milliseconds() < 1600) {
//            telemetry.a.
//            ddData("Status", "Moving to Spinner");
//        }
//        telemetry.update();
//
//        robot.setMotorPowers(0);
//        sleep(1000);
//
//        duckSpinner.setPosition(duckSpinner.getPosition()- .1);
//
//        while (opModeIsActive()){
//            duckSpinner.setPosition(+0.1);
//        }
//
//        robot.setMotorPowers(-1*TURN_SPEED, TURN_SPEED, 1.1 * TURN_SPEED, -1*TURN_SPEED, 0);

    }




    public void DriveToSpinner(long timeoutS) {
        if (opModeIsActive()) {
            robot.setMotorPowers(-TURN_SPEED, TURN_SPEED, 1.1 * TURN_SPEED, -TURN_SPEED, 0);
            runtime.reset();
          /*  while (opModeIsActive() && (runtime.seconds() < timeoutS)) {
            }*/
            sleep(timeoutS);
            robot.setMotorPowers(0);
          //  runtime.reset();
        }
    }

    public void SpinDuck(long timeoutS) {
      //  runtime.reset();

            //while (opModeIsActive() && (runtime.milliseconds() < timeoutS)) {
                duckSpinner.setPosition(duckSpinner.getPosition()- .1);
               // runtime.reset();
                telemetry.addData("spin ", " seconds " + runtime.milliseconds());    //
                telemetry.update();
           // }
        sleep(timeoutS);
            duckSpinner.setPosition(duckSpinner.getPosition());
         //   runtime.reset();


    }
    public void StrafeLeft(long timeoutS) {
        if (opModeIsActive()) {
//            robot.setMotorPowers(-2 * TURN_SPEED, 2 * TURN_SPEED, 2.2 * TURN_SPEED, -2 * TURN_SPEED, 0);
            robot.upperRight.setPower(1);
            robot.lowerRight.setPower(1);

            robot.lowerLeft.setPower(1);
            robot.upperLeft.setPower(1);
            // runtime.reset();
            sleep(timeoutS);
            robot.setMotorPowers(0);
            //  runtime.reset();


        }
    }

    public void StrafeRight(long timeoutS) {
            if (opModeIsActive()) {
//            robot.setMotorPowers(-2 * TURN_SPEED, 2 * TURN_SPEED, 2.2 * TURN_SPEED, -2 * TURN_SPEED, 0);
                robot.upperRight.setPower(-1);
                robot.lowerRight.setPower(-1);

                robot.lowerLeft.setPower(-1);
                robot.upperLeft.setPower(-1);
                // runtime.reset();
                sleep(timeoutS);
                robot.setMotorPowers(0);
                //  runtime.reset();


            }
    }
    public void AwayfromSpinner(long timeoutS) {
        if (opModeIsActive()) {
            robot.setMotorPowers(TURN_SPEED, -TURN_SPEED, 1.1 * -TURN_SPEED, TURN_SPEED, 0);
            runtime.reset();
          /*  while (opModeIsActive() && (runtime.seconds() < timeoutS)) {
            }*/
            sleep(timeoutS);
            robot.setMotorPowers(0);
            //  runtime.reset();
        }
    }

    }








    /*
    public void SpinDuck(double timeoutS) {
        if (opModeIsActive()) {
            duckSpinner.setPosition(duckSpinner.getPosition()- .1);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < timeoutS)) {

            }
            duckSpinner.setPosition(duckSpinner.getPosition());
            runtime.reset();
        }
    }
     */



//        robot.setMotorPowers(FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, 0);
//        runtime.reset();
//        while (opModeIsActive() && runtime.milliseconds() < 800) {
//            telemetry.addData("moving forward", "");
//            telemetry.update();
//        }
//        robot.setMotorPowers(0);
//        sleep(1000);




//        robot.setMotorPowers(TURN_SPEED, -TURN_SPEED, 1.1*TURN_SPEED, -TURN_SPEED, 0);
//        runtime.reset();
//        while (opModeIsActive() && runtime.milliseconds() < 3900) {
//            telemetry.addData("180 Turn", "");
//        }            telemetry.update();
//
//        robot.setMotorPowers(0);
//        sleep(1000);
//
//        robot.setMotorPowers(FORWARD_SPEED);
//        runtime.reset();
//        while (opModeIsActive() && runtime.milliseconds() < 1500) {
//            telemetry.addData("moving forward", "");
//            telemetry.update();
//        }
//        robot.setMotorPowers(0);
//        sleep(1000);
///*
//            robot.setMotorPowers(BACKWARD_SPEED);
//            runtime.reset();
//            while (opModeIsActive() && runtime.milliseconds() < 2500) {
//                telemetry.addData("moving backward", "");
//                telemetry.update();
//            }
//            robot.setMotorPowers(0);
//            sleep(1000);
//
//
//            robot.setMotorPowers(-TURN_SPEED, TURN_SPEED, TURN_SPEED, -TURN_SPEED);
//            runtime.reset();
//            while (opModeIsActive() && runtime.milliseconds() < 1500) {
//                telemetry.addData("turning right", "");
//            }            telemetry.update();
//
//            robot.setMotorPowers(0);
//            sleep(1000);
//     */
//    }
//
//
//}
//
