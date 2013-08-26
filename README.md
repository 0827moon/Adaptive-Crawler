Adaptive-Crawler
================

This Twitter Adaptive Crawler is based on the traffic correlation of Hashtags (please cite the paper if you use our crawling approach in your publications: 
Xinyue Wang, Laurissa Tokarchuk, Félix Cuadrado and Stefan Poslad. Exploiting Hashtags for Adaptive Microblog Crawling, In ASONAM2013 paper)

Motivation
----------
A Twitter crawler is a program that collects tweets or user information through the Twitter API that matches a set of search criteria. In fact, all of the existing work required crawling model to get the system run. This is due to the reason that a comprehensive dataset describing the event is compulsory for any work to identify and analyze events in the Twittersphere. The majority of collection techniques collect tweets from the live Twitter stream by matching a few search keywords or hashtags. However, the set of predefined keywords is subjective and can easily lead to incomplete data. Moreover, even given expert knowledge, keywords and specialised hashtags often arise in the midst of such events. Another issue is that collection of this size of dataset under Twitter’s API free restrictions has scalability issues and sometimes doesn’t provide compressive enough information about the events themselves. Therefore, in this work, we are interested in keyword-based Twitter crawling, where every matching tweet will contain at least one of the defined search keywords. The problem we want to solve is how to automatically, i.e. no manual modification of the search terms, gather a comprehensive set of social media documents with unknown features while relying on keyword searching for streaming collection during live events.

Run the code
------------
###Dependencies
In order to run the program, machine must has the following tools/jars
- JAVA
- MySQL
- MySQL jdbc
- Twitter4j 3.0.3

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

###Entrance
The main method is in the file cralwer/TwitterCrawler.java

Pros and Cons
-------------
According to our review of all the datasets, we conduct that the Moving Window approach is a naïve one which introduces traffic of all the trending events. However, its leak of filtering of abundant information makes it defective to apply to real scenario. The Traffic correlation (RKwA) approach is better than the Moving Window (SKwA) one as it tries to identify new keywords that talking about the event of interests. However, the performance sometime is not stable due to the rate limits of Twitter. This issue is extremely apparent when the event becomes a trending one. As a result, this RKwA has strict application to the low traffic, but not tranquil events. Currently, we are working on the content similarity based adaptive crawler. It is supposed to work under any kind of event and achieve a good accuracy. A minor delay is the cost of its good performance.

