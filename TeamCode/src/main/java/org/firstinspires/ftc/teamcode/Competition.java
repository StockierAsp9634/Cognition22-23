package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/*hahahahahhaa*/

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


/**
 * Autonomous code for programmer training. Will move forward for 5 seconds and turn for 3 seconds.
 */

@Autonomous(name = "Competition", group = "Pushbot")
public class Competition extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();


    static final double FORWARD_SPEED = 0.5;

    static final double BACKWARD_SPEED = -0.5;
    static final double TURN_SPEED = -0.5;
    static final double ARM_SPEED = 0.25;

    static final double INCREMENT = 0.10;     // amount to slew servo each CYCLE_MS cycle
    static final int CYCLE_MS = 50;     // period of each cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position
    Servo duckspinner;
    Servo Claw;
    double position = (MAX_POS - MIN_POS) / 2; // Start at halfway position
    boolean rampUp = true;


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
        //move backward


        robot.setMotorPowers(BACKWARD_SPEED, BACKWARD_SPEED, BACKWARD_SPEED, BACKWARD_SPEED, 0);
        runtime.reset();
        while (opModeIsActive() && runtime.milliseconds() < 1500) {
            telemetry.addData("moving backward", "");
            telemetry.update();
        }
        robot.setMotorPowers(0);
        sleep(1000);




        // Connect to servo (Assume PushBot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        // Claw = hardwareMap.get(Servo.class, "Claw");
        duckspinner = hardwareMap.get(Servo.class, "DuckSpinner");

        // Wait for the start button
        telemetry.addData(">", "Press Start to scan Servo." );
        telemetry.update();
        waitForStart();



        // Scan servo till stop pressed.
        while(opModeIsActive() && runtime.milliseconds()<8000){

            // slew the servo, according to the rampUp (direction) variable.
            if (rampUp) {
                // Keep stepping up until we hit the max value.
                position += INCREMENT ;
                if (position >= MAX_POS ) {
                    position = MAX_POS;
                    rampUp = !rampUp;   // Switch ramp direction
                }
            }
            else {
                // Keep stepping down until we hit the min value.
                position -= INCREMENT ;
                if (position <= MIN_POS ) {
                    position = MIN_POS;
                    rampUp = !rampUp;  // Switch ramp direction
                }
            }

            // Display the current value
            telemetry.addData("Servo Position", "%5.4f", position);
            telemetry.addData(">", "Press Stop to end test." );
            telemetry.update();

            // Set the servo to the new position and pause;
            // Claw.setPosition(position);
            duckspinner.setPosition(position);
            sleep(CYCLE_MS);
            idle();
        }



       /*  while(opModeIsActive() && runtime.seconds() < 5) {
                //servo move
                if (rampUp) {
                    // Keep stepping up until we hit the max value.
                    position += INCREMENT;
                    if (position >= MAX_POS) {
                        position = MAX_POS;
                        rampUp = !rampUp;   // Switch ramp direction
                    }
                } else {
                    // Keep stepping down until we hit the min value.
                    position -= INCREMENT;
                    if (position <= MIN_POS) {
                        position = MIN_POS;
                        rampUp = !rampUp;  // Switch ramp direction
                    }
                }

                // Display the current value
                telemetry.addData("Servo Position", "%5.4f", position);
                telemetry.addData(">", "Press Stop to end test.");
                telemetry.update();

                // Set the servo to the new position and pause;
                // Claw.setPosition(position);
                duckspinner.setPosition(position);
                sleep(CYCLE_MS);
                idle();
            }

*/
        while (opModeIsActive() && runtime.seconds() < 10) {

            robot.setMotorPowers(FORWARD_SPEED);
            runtime.reset();
            while (opModeIsActive() && runtime.milliseconds() < 1500) {
                telemetry.addData("moving forward", "");
                telemetry.update();
            }
            robot.setMotorPowers(0);
            sleep(1000);

            robot.setMotorPowers(-TURN_SPEED, TURN_SPEED, 1.1*TURN_SPEED, -TURN_SPEED, 0);
            runtime.reset();
            while (opModeIsActive() && runtime.milliseconds() < 1400) {
                telemetry.addData("turning left", "");
            }            telemetry.update();

            robot.setMotorPowers(0);
            sleep(1000);
            robot.setMotorPowers(0, 0, 0, 0, ARM_SPEED);
            runtime.reset();
            while (opModeIsActive() && runtime.milliseconds() < 500) {
                telemetry.addData("moving the arm", "");
            }            telemetry.update();

            robot.setMotorPowers(0);
            sleep(1000);

            if (rampUp) {
                // Keep stepping up until we hit the max value.
                position += INCREMENT;
                if (position >= MAX_POS) {
                    position = MAX_POS;
                    rampUp = !rampUp;   // Switch ramp direction
                }
            } else {
                // Keep stepping down until we hit the min value.
                position -= INCREMENT;
                if (position <= MIN_POS) {
                    position = MIN_POS;
                    rampUp = !rampUp;  // Switch ramp direction
                }
            }

            // Display the current value
            telemetry.addData("Servo Position", "%5.4f", position);
            telemetry.addData(">", "Press Stop to end test.");
            telemetry.update();

            // Set the servo to the new position and pause;
            // Claw.setPosition(position);
            duckspinner.setPosition(position);
            sleep(CYCLE_MS);
            idle();


        }


    }
}

