

public class StrategyCrashedException extends Exception
{
	int crashDex;
	
	StrategyCrashedException() {
		crashDex = -1;
	}
	
	StrategyCrashedException(int dex) {
		crashDex = dex;
	}
	
	int getCauseIndex()
	{
		return crashDex;
	}
}
