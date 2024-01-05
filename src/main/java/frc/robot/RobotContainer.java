package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.RomiButton;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.RomiLED;
import frc.robot.subsystems.RomiButton.Button;
import frc.robot.subsystems.RomiLED.Color;

// final class so it cannot be inherited as a superclass
public final class RobotContainer 
{
    static
    {
        System.out.println("RobotContainer started");
    }

    private final boolean useRomiDrivetrain     = true;
    private final boolean useJoystick           = true;
    private final boolean useRomiYellowLED      = true;
    private final boolean useRomiRedLED         = true;
    private final boolean useTestAButton        = true;
    private final boolean useTestBButton        = true;

    final RomiDrivetrain romiDrivetrain;// = new RomiDrivetrain();
    final Joystick joystick;// = new Joystick(0);
    final RomiLED yellowLED;// = new RomiLED(Color.kYELLOW);
    final RomiLED redLED;// = new RomiLED(Color.kRED);
    // private final RomiLED greenLED = new RomiLED(Color.kGREEN);
    final RomiButton testAButton;// = new RomiButton(Button.kA);
    final RomiButton testBButton;// = new RomiButton(Button.kB);

    // default modifier, only visible within the package
    RobotContainer()
    {
        romiDrivetrain  = useRomiDrivetrain     ? new RomiDrivetrain()          : null;
        joystick        = useJoystick           ? new Joystick(0)               : null;
        yellowLED       = useRomiYellowLED      ? new RomiLED(Color.kYELLOW)    : null;
        redLED          = useRomiRedLED         ? new RomiLED(Color.kRED)       : null;
        testAButton     = useTestAButton        ? new RomiButton(Button.kA)     : null;
        testBButton     = useTestBButton        ? new RomiButton(Button.kB)     : null;

    }
    
}
