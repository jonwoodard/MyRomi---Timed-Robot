package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.RomiButton.Button;
import frc.robot.subsystems.RomiLED.Color;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
    static
    {
        System.out.println("Robot started");
    }

    // private final Talon leftMotor = new Talon(0);
    // private final Talon rightMotor = new Talon(1);
    // private final RomiDrivetrain romiDrivetrain = new RomiDrivetrain();
    // private final Joystick joystick = new Joystick(0);
    // private final RomiLED yellowLED = new RomiLED(Color.kYELLOW);
    // private final RomiLED redLED = new RomiLED(Color.kRED);
    // // private final RomiLED greenLED = new RomiLED(Color.kGREEN);
    // private final RomiButton testAButton = new RomiButton(Button.kA);
    // private final RomiButton testBButton = new RomiButton(Button.kB);

    private final RobotContainer robotContainer = new RobotContainer();

    private boolean isTurbo = false;
    private double leftEncoderPosition = 0.0;
    private double rightEncoderPosition = 0.0;
    private int autoStep = 0;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() 
    {
        System.out.println("Hello World");
        SmartDashboard.putString("message", "Hello World");

        // leftMotor.setInverted(false);
        // rightMotor.setInverted(true);
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
     * that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() 
    {

    }

    /** This method is called once when autonomous is enabled. */
    @Override
    public void autonomousInit() 
    {
        System.out.println("Autonomous Mode");
        SmartDashboard.putString("mode", "Autonomous");

        leftEncoderPosition = robotContainer.romiDrivetrain.getLeftDistanceInch();
        rightEncoderPosition = robotContainer.romiDrivetrain.getRightDistanceInch();

        autoStep = 1;
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() 
    {
        // System.out.println("Drivetrain = " + romiDrivetrain);

        switch(autoStep)
        {
        case 1:
            double leftDistance = robotContainer.romiDrivetrain.getLeftDistanceInch() - leftEncoderPosition;
            double rightDistance = robotContainer.romiDrivetrain.getRightDistanceInch() - rightEncoderPosition;

            if(leftDistance < 12.0 && rightDistance < 12.0)
            {
                robotContainer.romiDrivetrain.arcadeDrive(0.5, 0.0);
            }
            else
            {
                robotContainer.romiDrivetrain.arcadeDrive(0.0, 0.0);
                autoStep++;
                leftEncoderPosition = robotContainer.romiDrivetrain.getLeftSpinDegree();
                rightEncoderPosition = robotContainer.romiDrivetrain.getRightSpinDegree();
            }
            break;
        case 2:
            double leftAngle = -(robotContainer.romiDrivetrain.getLeftSpinDegree() - leftEncoderPosition);
            double rightAngle = robotContainer.romiDrivetrain.getRightSpinDegree() - rightEncoderPosition;

            if(leftAngle < 90.0 && rightAngle < 90.0)
            {
                robotContainer.romiDrivetrain.arcadeDrive(0.0, 0.5);
            }
            else
            {
                robotContainer.romiDrivetrain.arcadeDrive(0.0, 0.0);
                autoStep++;
            }
        }
    }

    /** This method is called once when autonomous is disabled. */
    @Override
    public void autonomousExit()
    {
        robotContainer.romiDrivetrain.arcadeDrive(0.0, 0.0);
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() 
    {
        System.out.println("Teleop Mode");
        SmartDashboard.putString("mode", "Teleop");
        
        robotContainer.yellowLED.on();
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() 
    {
        double leftYAxis = -robotContainer.joystick.getRawAxis(1);
        double rightXAxis = -robotContainer.joystick.getRawAxis(4);
        boolean bButton = robotContainer.joystick.getRawButtonPressed(2);

        if(bButton)
            isTurbo = !isTurbo;

        leftYAxis = MathUtil.applyDeadband(leftYAxis, 0.1);
        rightXAxis = MathUtil.applyDeadband(rightXAxis, 0.1);

        if(isTurbo)
            leftYAxis = leftYAxis * 0.75;
        else
            leftYAxis = leftYAxis * 0.5;

        rightXAxis = rightXAxis * 0.5;

        // leftMotor.set(leftYAxis);
        // rightMotor.set(leftYAxis);
        robotContainer.romiDrivetrain.arcadeDrive(leftYAxis, rightXAxis);
    }

    /** This method is called once when the teleop is disabled. */
    @Override
    public void teleopExit()
    {
        // leftMotor.set(0.0);
        // rightMotor.set(0.0);
        robotContainer.romiDrivetrain.arcadeDrive(0.0, 0.0);
        robotContainer.yellowLED.off();
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() 
    {
        System.out.println("Disabled Mode");
        SmartDashboard.putString("mode", "Disabled");
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() 
    {

    }

    /** This method is called once when the robot is enabled. */
    @Override
    public void disabledExit()
    {

    }

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() 
    {
        System.out.println("Test Mode");
        SmartDashboard.putString("mode", "Test");
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() 
    {

    }

    /** This method is called once when test mode is disabled. */
    @Override
    public void testExit()
    {

    }
}
