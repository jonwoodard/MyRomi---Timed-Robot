package frc.robot.subsystems;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.DigitalOutput;

public class RomiLED extends DigitalOutput
{
    static
    {
        System.out.println("RomiLED started");
    }

    public enum Color
    {
        kGREEN(1), kRED(2), kYELLOW(3);

        public final int port;

        private Color(int port)
        {
            this.port = port;
        }
    }

    private enum State
    {
        kOFF, kON;
    }

    private Color color = null;
    private State state = State.kOFF;

    // private double lastBlink = 0.0;

    public RomiLED(Color color)
    {
        super(color.port);
        off();
        this.color = color;

        SendableRegistry.addLW(this, "RomiLED", color.toString());
    }

    public void on()
    {
        set(true);
        state = State.kON;
    }

    public void off()
    {
        set(false);
        state = State.kOFF;
    }

    public void change()
    {
        switch(state)
        {
            case kOFF:
                on();
                break;
            case kON:
                off();
                break;
        }
    }

    public void blink(double onSec, double offSec)
    {
        
    }

    public boolean isOn()
    {
        return state == State.kON;
    }

    public boolean isOff()
    {
        return state == State.kOFF;
    }

    @Override
    public String toString()
    {
        return "Color = " + color + " State = " + state;
    }
}
