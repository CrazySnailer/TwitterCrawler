package twitter4j.examples.oauth;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
 
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.PropertyConfiguration;
 
public class NamexTweet {
   // private final static String CONSUMER_KEY = "eEKgNmj5LZM1Y0s9w256A";
    //private final static String CONSUMER_KEY_SECRET = "IZzKrkFVd2dsJkaKQCUFA5vHFI8cDjthj7hccXnM";
 
    public void start() throws TwitterException, IOException {
    	Properties props = new Properties();
    	 props.put(PropertyConfiguration.HTTP_PROXY_HOST, "127.0.0.1");
         props.put(PropertyConfiguration.HTTP_PROXY_PORT, "8087");
         props.put(PropertyConfiguration.OAUTH_CONSUMER_KEY,
					"PUrgpzGjVb8bBlj5THzzTw");
			props.put(PropertyConfiguration.OAUTH_CONSUMER_SECRET,
					"MmfW4bqClQDX2BAarc2B0dctVHK9Fm8BcIiiz4Vf4");
         PropertyConfiguration conf = new PropertyConfiguration(props);
    Twitter twitter = new TwitterFactory(conf).getInstance();
   // twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
    RequestToken requestToken = twitter.getOAuthRequestToken();
    System.out.println("Authorization URL: \n"
        + requestToken.getAuthorizationURL());
 
    AccessToken accessToken = null;
 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (null == accessToken) {
        try {
        System.out.print("Input PIN here: ");
        String pin = br.readLine();
 
        accessToken = twitter.getOAuthAccessToken(requestToken, pin);
 
        } catch (TwitterException te) {
 
        System.out.println("Failed to get access token, caused by: "
            + te.getMessage());
 
        System.out.println("Retry input PIN");
 
        }
    }
 
    System.out.println("Access Token: " + accessToken.getToken());
    System.out.println("Access Token Secret: "
        + accessToken.getTokenSecret());
 
    //twitter.updateStatus("hi.. im updating this using test");
 
    }
 
    public static void main(String[] args) throws Exception {
    new NamexTweet().start();// run the Twitter client
    }
}