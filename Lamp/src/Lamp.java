
public class Lamp {
	
	private boolean lampIsOnn;
	public Lamp() { lampIsOnn = false; }
	public void turnOn() { lampIsOnn = true; }
	public void turnOff() { lampIsOnn = false; }
	public boolean isOn() { return lampIsOnn; }

}

