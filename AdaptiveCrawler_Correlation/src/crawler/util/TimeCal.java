/* Copyright [2013] [Xinyue Wang]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

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
