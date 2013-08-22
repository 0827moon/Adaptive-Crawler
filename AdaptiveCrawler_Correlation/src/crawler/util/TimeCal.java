package crawler.util;

public final class TimeCal {
	private long startTime;
	private long interval;
	
	public TimeCal(long interval) {
		this.interval = interval;
	}
	
	public void start() {
		System.out.println("Timer is restarting ...");
		startTime = System.currentTimeMillis();
	}
	
	public long now() {
		return System.currentTimeMillis();
	}
	
	public long getFinalEnd() {
		return startTime+interval;
	}
	
	public boolean expire() {
		if (duration()<interval)
			return false;
		else
			return true;
		
	}
	
	public long duration() {
		return (now()-startTime);
	}

	public void restart() {
		start();
	}
}
