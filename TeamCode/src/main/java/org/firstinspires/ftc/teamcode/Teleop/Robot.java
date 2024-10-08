/*
Imports

creation class

when button pressed
    activate process


Driver Joysticks
    Call the drive.java
        - Current Joystick Values

Drive A Button

Driver B Button
    PickUp
        - Arm angle = 60 degrees - Arm Class angle input function
        - Claw angle 90 degrees - Claw Class angle input function
        - Extend to set position & let RT and LT extend and retract the arm - Arm Class set position function
        - Turn on the corresponding LED light - LED Class sets the led lights to a sequence based off of arm angle (Arm class angle output)
            - Blue

Driver Y Button
    Stow
        - Arm angle = 90 degrees - Arm Class angle input function
        - Claw angle = 180 degrees - Claw Class angle input function
        - Fully retracted - Arm Class set position function
        - Turn on the corresponding LED light - LED Class sets the led lights to a sequence based off of arm angle (Arm class angle output)
            - Flashing Yellow

Driver X Button
    Placing
        - Arm angle = 230 degrees - Arm Class angle input function
        - Claw angle = 180 degrees - Claw Class angle input function
        - Extend arm to set position & let RT and LT extend and retract the arm - Arm Class
        - Allow claw angle to be changed & let RB and LB change that angle
            - Green
Driver LT
    Extending Arm - Arm class button input function

Driver RT
    Retracting Arm - Arm class button input function

Driver LB
    Open Claw - Claw class button input function


Driver RB
    Close Claw - Claw class button input function

 */

package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Teleop.LED;
import org.firstinspires.ftc.teamcode.Teleop.Arm;
import org.firstinspires.ftc.teamcode.Teleop.Claw;
import org.firstinspires.ftc.teamcode.Teleop.Drive;

@TeleOp(group = "Primary")
public class robot extends LinearOpMode {

    private HardwareMap hwMap;
    //private final LED status_lights = new LED();
    //private final Arm robotArm = new Arm();
    //private final Claw robotClaw = new Claw();
    //private final Drive robotDrive = new Drive(hwMap);

    //private ArmTemp robotArmTemp;

    private Drive robotDrive;
    private Claw robotClaw;


    @Override
    public void runOpMode() throws InterruptedException {

        HardwareMap hwMap = hardwareMap;

        robotDrive = new Drive(hwMap);
        robotClaw = new Claw(hwMap);

        String state = "Stowed";
        String clawState = "Stowed";
        float arm_angle = 90;

        initHardware(hwMap);
        while(!isStarted()){}
        waitForStart();
        while(opModeIsActive()){

            boolean aButton = gamepad1.a;
            boolean bButton = gamepad1.b;
            boolean xButton = gamepad1.x;
            boolean yButton = gamepad1.y;

            if(bButton){ //Pickup
                state = "pickUp";
            }
            if(xButton){ //Stowed
                state = "stowed";
            }
            if(yButton){ //Place
                state = "place";
            }

            //robotArm.controlArm(state, gamepad1);
            robotClaw.claw_servos(gamepad1);
            robotDrive.update_Drive(gamepad1);
            //robotArmTemp.controlArm(gamepad1);
            //status_lighths.set_status(state,arm_angle);

            //This is where running code is added

            // Add detection for different buttons that will run  curtain sequences
            // for example a button changing a state should be able to update  b    
        }
    }

    public void initHardware(HardwareMap hwMap){
        robotDrive.initDrive();
        robotClaw.initClaw();
        //robotArmTemp.initArm();
        //status_lights.initLights();
        //robotArm.initArm();
        //robotClaw.initClaw();

    }
}

