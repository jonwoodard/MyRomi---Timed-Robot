package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

import static frc.robot.Constants.DrivetrainConstants;

public class RomiDrivetrain extends DifferentialDrive
{
    static
    {
        System.out.println("RomiDrivetrain started");
    }

    // private final double MM_PER_INCH = 25.4;
    // private final double WHEEL_DIAMETER_INCH = 70.0 / MM_PER_INCH;
    // private final double ENCODER_COUNTS_PER_WHEEL_REV = 1440.0;
    // private final double ROBOT_TRACKWIDTH_INCH = 141.0 / MM_PER_INCH;
    private final double ROBOT_SPINNING_CIRCUMFERENCE = Math.PI * DrivetrainConstants.ROBOT_TRACK_WIDTH_INCH;

    private static final Talon leftMotor = new Talon(DrivetrainConstants.LEFT_MOTOR_PORT);
    private static final Talon rightMotor = new Talon(DrivetrainConstants.RIGHT_MOTOR_PORT);
    // private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    private final Encoder leftEncoder = new Encoder(DrivetrainConstants.LEFT_ENCODER_CHANNEL_A,
                                                    DrivetrainConstants.LEFT_ENCODER_CHANNEL_B);
    private final Encoder rightEncoder = new Encoder(DrivetrainConstants.RIGHT_ENCODER_CHANNEL_A, 
                                                    DrivetrainConstants.RIGHT_ENCODER_CHANNEL_B);

    public RomiDrivetrain()
    {
        super(leftMotor, rightMotor);
        double distancePerPulse = Math.PI * DrivetrainConstants.WHEEL_DIAMETER_INCH / DrivetrainConstants.COUNTS_PER_REV;

        leftMotor.setInverted(false);
        rightMotor.setInverted(true);

        leftEncoder.setDistancePerPulse(distancePerPulse);
        rightEncoder.setDistancePerPulse(distancePerPulse);
        resetEncoders();

        SendableRegistry.addLW(this, "RomiDrivetrain", "Motors");
        SendableRegistry.addLW(leftEncoder, "RomiDrivetrain", "Encoder Left");
        SendableRegistry.addLW(rightEncoder, "RomiDrivetrain", "Encoder Right");
    }

    // public void arcadeDrive(double speed, double rotate)
    // {
    //     differentialDrive.arcadeDrive(speed, rotate);
    // }

    public void resetEncoders()
    {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getLeftDistanceInch()
    {
        return leftEncoder.getDistance();
    }

    public double getRightDistanceInch()
    {
        return rightEncoder.getDistance();
    }

    public double getLeftSpinDegree()
    {
        double spin = getLeftDistanceInch() / ROBOT_SPINNING_CIRCUMFERENCE * 360.0;
        return spin;
    }

    public double getRightSpinDegree()
    {
        double spin = getRightDistanceInch() / ROBOT_SPINNING_CIRCUMFERENCE * 360.0;
        return spin;
    }


    @Override
    public String toString()
    {
        // String str = "Left = " + getLeftDistanceInch() + " Right = " + getRightDistanceInch();
        String str = String.format("Left = %5.1f Right = %5.1f", getLeftDistanceInch(), getRightDistanceInch());
        return str;
    }
}
