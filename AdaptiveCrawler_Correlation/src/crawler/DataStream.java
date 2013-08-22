package crawler;

import java.util.ArrayList;

import crawler.util.Settings;

import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class DataStream implements StatusListener {
	private ArrayList<Status> statusRepo = new ArrayList<Status>();
	
	public ArrayList<Status> getList() {
		ArrayList<Status> statuses = new ArrayList<Status>(statusRepo);
		statusRepo.clear();
		return statuses;
	}
	
	public int getSize() {
		return statusRepo.size();
	}
	
	private void freqUpdate(Status status) {
		//String hashtags = "";//
		HashtagEntity[] tags = status.getHashtagEntities();
		for (HashtagEntity t: tags) {
			String hashtag = "#"+t.getText().toLowerCase();
			
			int val = 1;
			if (Settings.TFHashtagFreq.containsKey(hashtag)) {
				val += Settings.TFHashtagFreq.get(hashtag);
			}
			Settings.TFHashtagFreq.put(hashtag, val);
			
			//hashtags+=t.getText()+",";//
			
		}
		//System.out.println(status.getCreatedAt()+", [" + hashtags + "] - " + status.getText());//
	}
	
	public void onStatus(Status status) {
		statusRepo.add(status);
		freqUpdate(status);
		sampleFreqUpdate(status);
				
		/*HashtagEntity[] tags = status.getHashtagEntities();
		String hashtags = "";
		for (HashtagEntity t: tags) hashtags+=t.getText()+",";
		System.out.println(status.getCreatedAt()+", [" + hashtags + "] - " + status.getText());*/
	}

	private void sampleFreqUpdate(Status status) {
		long duration = TwitterCrawler.Timer.duration();
		long temp = duration/Settings.sample;
		int index = (int) temp;
		int maxIndex = (int) (Settings.timer/Settings.sample+1);
		
		if(index<maxIndex){
			//System.out.println(duration+"ms@"+index);
			HashtagEntity[] tags = status.getHashtagEntities();
			for (HashtagEntity t: tags) {
				String hashtag = "#"+t.getText().toLowerCase();
				
				/*if(index <= maxIndex){
					int val = 1;
					if (Settings.TFHashtagFreq.containsKey(hashtag)) {
						val += Settings.TFHashtagFreq.get(hashtag);
					}
					Settings.TFHashtagFreq.put(hashtag, val);
				}*/
				
				double[] freq;
				if (Settings.TFHashtagFreq_sample.containsKey(hashtag)) {
					freq = Settings.TFHashtagFreq_sample.get(hashtag);
					freq[index]+=1;
					//System.out.println(hashtag+": "+index+"th freq is "+freq[index]+"###"+(int) (Settings.timer/Settings.sample));
				}else{
					freq = new double[maxIndex];
					for(int i =0; i<freq.length; i++) freq[i] =0;
					freq[index] = 1;
					//System.out.println(hashtag+": "+index+"th freq is "+freq[index]+"@: "+duration+"/"+Settings.sample);
				}
				Settings.TFHashtagFreq_sample.put(hashtag, freq);
			
				/*double[] val = Settings.TFHashtagFreq_sample.get(hashtag);
				String s="";
				for(double d:val)	s+=d+", ";
				System.out.println("hashtag {"+hashtag+"} got freq {"+s+"}");*/
			}
		}
	}

	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	    //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	}
	
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	    //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	}
	
	public void onScrubGeo(long userId, long upToStatusId) {
	    //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	}
	
	public void onException(Exception ex) {
	    ex.printStackTrace();
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub
		
	}
}
