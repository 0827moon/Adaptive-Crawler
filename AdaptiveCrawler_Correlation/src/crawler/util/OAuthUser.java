package crawler.util;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class OAuthUser {
	

	public Configuration build(String consumerKey, String consumerSecret, String accessToken, String accessSecret) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthConsumerKey(consumerKey)
    	  .setOAuthConsumerSecret(consumerSecret)
    	  .setOAuthAccessToken(accessToken)
    	  .setOAuthAccessTokenSecret(accessSecret);
		return cb.build();
	}
}
