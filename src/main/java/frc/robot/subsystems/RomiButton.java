package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.DigitalInput;

public class RomiButton extends DigitalInput
{
    static
    {
        System.out.println("RomiButton started");
    }

    private enum State
    {
        kPRESSED, kSTILL_PRESSED, kRELEASED, kSTILL_RELEASED;
    }

    public enum Button
    {
        kA(0), kB(1), kC(2);

        public final int port;

        private Button(int port)
        {
            this.port = port;
        }
    }

    private State state = State.kSTILL_RELEASED;
    private Button button = null;

    public RomiButton(Button button)
    {
        super(button.port);

        SendableRegistry.addLW(this, "RomiButton", button.toString());
    }
    
}
