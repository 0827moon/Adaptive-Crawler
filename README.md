Adaptive-Crawler
================

This Twitter Adaptive Crawler is based on the correlation between the traffic pattern of Hashtags.

The full details are avaible through: 
[Xinyue Wang, Laurissa Tokarchuk, Félix Cuadrado, and Stefan Poslad. 2013. Exploiting hashtags for adaptive microblog crawling. In Proceedings of the 2013 IEEE/ACM International Conference on Advances in Social Networks Analysis and Mining (ASONAM '13). DOI=10.1145/2492517.2492624!](http://dl.acm.org/citation.cfm?id=2492517.2492624)

Motivation
----------
A Twitter crawler is software that filters a large number of twitter data, tweets, to select the ones of interest, i.e., that match a set of hashtags and key terms as search criteria. One important application is to use the Twitter crawler to detect unplanned events or trends of mass interest. However, the majority of Twitter crawlers use a set of predefined keywords that is often highly subjective and can easily lead to incomplete data. This is often because related terms which are note predefined, but which become key terms and hashtags, cannot be used as the (predefined) search criteria. Even, given expert knowledge, new keywords and specialised hashtags often arise in the midst of such events. Another issue is that in order to identify events and trends, we need to analyse a large collection tweets, however,  free access to Twitter data is rate limited so that we can typically only access 1% of the available tweets.  The consequence of this is that the effect of the set of limited key search terms is greatly aggravated and this means that we are far less likely to reliably detect unforeseen events or trends.

We have developed software (in Java) to automatically, without requiring manual modification of the search terms, to generate a better, more comprehensive set of search terms based upon correlating the traffic patterns of new key words against predefined words. We validated the Twitter crawler on the Olympic 2012, Glastonbury (UK) music festival 2013 events. This approach introducing high volume of additional traffic for event of interests in real-time.

Run the code
------------
###Dependencies
In order to run the program, machine must has the following tools/jars
- Correlation
  + JAVA
  + MySQL
  + MySQL jdbc
  + Twitter4j 3.0.3

###Accounts
Please change them in the crawler/util/Settings.java
- Twitter account
  + Comsumer Key -> ConsumerKey
  + Comsumer Secret -> ConsumerSecret
  + Access Token -> AccessToken
  + AccessSecret -> AccessSecret
- MySQL database
  + host name -> HOSTNAME
  + user name -> USER
  + password -> PWD
  + database name -> databaseName

###Input parameters
Parameters are initialized in the crawler/util/Settings.java
- command line changable
  + initial keywords -> baseKeywords
  + time frame -> timer
  + sample time slot -> sample
- text file changable
  + blacklist: the blacklist can be modified during the crawling, but must follow the format like "#keys"
- others: please see in the file

###Outputs
All the outputs are named with a prefix which indicates the running time & date. For example, if the cralwer is started at 12:00 30th Jun, the prefix will be "T06301200"...
- Keywords List: a txt file records all the keywords will be generated under KeyWord file with name [prefix]KeywordList.txt
- Black List: a txt file records all the black list keys with name [prefix]BlackList.txt (this can be modified during the crawling)
- MySQL table: a table stores all the collected tweets with name [prefix]COR
  MySQL table format: 
    (pid bigint(50) NOT NULL,
		createdAt text DEFAULT NULL, 
		geoLocationLat double NOT NULL,
		geoLocationLong double NOT NULL,	
		placeInfo text, 
		id bigint(50) NOT NULL, 
		tweet longtext CHARACTER SET utf8, 
		source text CHARACTER SET utf8, 
		lang text,
		screenName VARCHAR(150),
		replyTo text,
		rtCount bigint(50), 
		hashtags text, 
		PRIMARY KEY (pid)}

###Entrance
The main method is in the file cralwer/TwitterCrawler.java

Pros and Cons
-------------
- Correlation
This version of Adaptive Crawler tries to identify new keywords that talking about the event of interests. However, the performance sometime is not stable. Namely, it can lead to new noisy terms being generated which would otherwise worsen the detection of related tweets. This is because, 1) people, sometimes include hashtags that are not really relevant to the event; 2) rate limits free Twitter API access disturb the traffic pattern; 3) the traffic pattern of some irrelevant hashtags present linear relationship with the pre-defined set at sometimes. This issue is extremely apparent when the event becomes a trending one. It is difficult for this version of crawler go back to the normal state. Additional, event with little extra new hashtags is not the target application. As a result, this RKwA has strict application to the medium traffic, but not tranquil events. Currently, we are working on the content similarity based adaptive crawler. It is supposed to work under any kind of event and achieve a good accuracy. A minor delay is the cost of its good performance.

