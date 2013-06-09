/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitter4j.examples.user;

import java.util.Properties;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.PropertyConfiguration;

/**
 * Looks up users.
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class LookupUsers {
	
	private static Properties props = new Properties();
	
    /**
     * Usage: java twitter4j.examples.user.LookupUsers [screen name[,screen name..]]
     *
     * @param args message
     */
    public static void main(String[] args) {
    	
    	props.put(PropertyConfiguration.HTTP_PROXY_HOST, "127.0.0.1");
		props.put(PropertyConfiguration.HTTP_PROXY_PORT, "8087");
		props.put(PropertyConfiguration.OAUTH_CONSUMER_KEY, "WdM6eZhENPYSEbxvCXH8A");
        props.put(PropertyConfiguration.OAUTH_CONSUMER_SECRET, "Z3rUV2S9sgsfTKytdpipjugZykQe9F8TQPOyt9ldo");	
		AccessToken token = new AccessToken(
				"835028173-Tc6qHpE14zNWcxpp65aKkIzd3ggXRU2xKtjnKdzh",
				"cGrAh8FDmfDwNlLEr2PCwzMJulzMCu0bPkxz5suMCc");
		PropertyConfiguration conf = new PropertyConfiguration(props);
		
    	
        if (args.length < 1) {
            System.out.println(
                    "Usage: java twitter4j.examples.user.LookupUsers [screen name[,screen name..]]");
            System.exit(-1);
        }
        try {
        	Twitter twitter = new TwitterFactory(conf).getInstance(token);
            ResponseList<User> users = twitter.lookupUsers(args[0].split(","));
            for (User user : users) {
            	
            	System.out.println(user);
//                if (user.getStatus() != null) {
//                    System.out.println("@" + user.getScreenName() + " - " + user.getStatus().getText());
//                } else {
//                    // the user is protected
//                    System.out.println("@" + user.getScreenName());
//                }
            }
            System.out.println("Successfully looked up users [" + args[0] + "].");
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to lookup users: " + te.getMessage());
            System.exit(-1);
        }
    }
}
