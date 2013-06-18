
/**
* @project Web
* @author Dayong.Shen
* @package isiteam.TwitterCrawler.util
* @file UtfFix.java
* 
* @date 2013-6-18-上午10:04:48
* @Copyright 2013 ISI Team of NUDT-版权所有
* 
*/
 
package isiteam.TwitterCrawler.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;



/**
 * @project Web
 * @author Dayong.Shen
 * @class UtfFix
 * 
 * @date 2013-6-18-上午10:04:48
 * @Copyright 2013 ISI Team of NUDT-版权所有
 * @Version 1.0.0
 */

public class UtfFix {
	 public static void main(String[] args) {
		 
		/* String str  = "Пр�вет"; //U+20000, represented by 2 chars in java (UTF-16 surrogate pair)
		 str = str.replaceAll( "([\\ud800-\\udbff\\udc00-\\udfff])", "");
		 System.out.println(str.length()); //0
	     */
		 
		// System.out.println(removeNonUtf8CompliantCharacters("한가인Пр�вет"+"UserJSONImpl{id=269664261, name='黃麗芝', screenName='_LizHuang', location='Guangzhou,China ', description='不可叫人小看你年輕,總要在言語、行為、愛心、信心、清潔上,都做信徒的榜樣.提摩太前書4:12', isContributorsEnabled=false, profileImageUrl='http://a0.twimg.com/profile_images/1509710346/IMG_0108____normal.jpg', profileImageUrlHttps='https://twimg0-a.akamaihd.net/profile_images/1509710346/IMG_0108____normal.jpg', url='http://t.co/eTvs8F7UuC', isProtected=false, followersCount=162, status=StatusJSONImpl{createdAt=Fri Apr 12 09:30:58 CST 2013, id=322522195962241025, text='Morning Chiang Mai...Breakfast????? http://t.co/vD26qmy9SZ', source='<a href=\"http://instagram.com\" rel=\"nofollow\">Instagram</a>', isTruncated=false, inReplyToStatusId=-1, inReplyToUserId=-1, isFavorited=false, inReplyToScreenName='null', geoLocation=null, place=null, retweetCount=0, isPossiblySensitive=false, contributorsIDs=[J@6d3d7254, retweetedStatus=null, userMentionEntities=[], urlEntities=[URLEntityJSONImpl{url='http://t.co/vD26qmy9SZ', expandedURL='http://instagram.com/p/X_HMiAleM2/', displayURL='instagram.com/p/X_HMiAleM2/'}], hashtagEntities=[], mediaEntities=[], currentUserRetweetId=-1, user=null}, profileBackgroundColor='FF6699', profileTextColor='362720', profileLinkColor='B40B43', profileSidebarFillColor='E5507E', profileSidebarBorderColor='CC3366', profileUseBackgroundImage=true, showAllInlineMedia=false, friendsCount=140, createdAt=Mon Mar 21 14:54:23 CST 2011, favouritesCount=0, utcOffset=-32400, timeZone='Alaska', profileBackgroundImageUrl='http://a0.twimg.com/images/themes/theme11/bg.gif', profileBackgroundImageUrlHttps='https://twimg0-a.akamaihd.net/images/themes/theme11/bg.gif', profileBackgroundTiled=true, lang='en', statusesCount=163, isGeoEnabled=true, isVerified=false, translator=false, listedCount=11, isFollowRequestSent=false}"));
	
		String original= "한가인Пр�вет"+"UserJSONImpl{id=269664261, name='黃麗芝', screenName='_LizHuang', location='Guangzhou,China ', description='不可叫人小看你年輕,總要在言語、行為、愛心、信心、清潔上,都做信徒的榜樣.提摩太前書4:12', isContributorsEnabled=false, profileImageUrl='http://a0.twimg.com/profile_images/1509710346/IMG_0108____normal.jpg', profileImageUrlHttps='https://twimg0-a.akamaihd.net/profile_images/1509710346/IMG_0108____normal.jpg', url='http://t.co/eTvs8F7UuC', isProtected=false, followersCount=162, status=StatusJSONImpl{createdAt=Fri Apr 12 09:30:58 CST 2013, id=322522195962241025, text='Morning Chiang Mai...Breakfast????? http://t.co/vD26qmy9SZ', source='<a href=\"http://instagram.com\" rel=\"nofollow\">Instagram</a>', isTruncated=false, inReplyToStatusId=-1, inReplyToUserId=-1, isFavorited=false, inReplyToScreenName='null', geoLocation=null, place=null, retweetCount=0, isPossiblySensitive=false, contributorsIDs=[J@6d3d7254, retweetedStatus=null, userMentionEntities=[], urlEntities=[URLEntityJSONImpl{url='http://t.co/vD26qmy9SZ', expandedURL='http://instagram.com/p/X_HMiAleM2/', displayURL='instagram.com/p/X_HMiAleM2/'}], hashtagEntities=[], mediaEntities=[], currentUserRetweetId=-1, user=null}, profileBackgroundColor='FF6699', profileTextColor='362720', profileLinkColor='B40B43', profileSidebarFillColor='E5507E', profileSidebarBorderColor='CC3366', profileUseBackgroundImage=true, showAllInlineMedia=false, friendsCount=140, createdAt=Mon Mar 21 14:54:23 CST 2011, favouritesCount=0, utcOffset=-32400, timeZone='Alaska', profileBackgroundImageUrl='http://a0.twimg.com/images/themes/theme11/bg.gif', profileBackgroundImageUrlHttps='https://twimg0-a.akamaihd.net/images/themes/theme11/bg.gif', profileBackgroundTiled=true, lang='en', statusesCount=163, isGeoEnabled=true, isVerified=false, translator=false, listedCount=11, isFollowRequestSent=false}";
			
		/*(
			    (?: [\x00-\x7F]                 # single-byte sequences   0xxxxxxx
			    	    |   [\xC0-\xDF][\x80-\xBF]      # double-byte sequences   110xxxxx 10xxxxxx
			    	    |   [\xE0-\xEF][\x80-\xBF]{2}   # triple-byte sequences   1110xxxx 10xxxxxx * 2
			    	    |   [\xF0-\xF7][\x80-\xBF]{3}   # quadruple-byte sequence 11110xxx 10xxxxxx * 3 
			    	    ){1,100}                        # ...one or more times
			    	  )
			    	| .                                 # anything else 
	  */
		
	    //String regx="((?: [\x00-\x7F]|[\xC0-\xDF][\x80-\xBF]|[\xE0-\xEF][\x80-\xBF]{2}|[\xF0-\xF7][\x80-\xBF]{3}){1,100})| .";                                
	    		
	    		
		System.out.println( original.replaceAll("^[0x00-0xFFFF]", ""));
		 
		 /*	 try {
			//System.out.println(filterOffUtf8Mb4("UserJSONImpl{id=269664261, name='黃麗芝', screenName='_LizHuang', location='Guangzhou,China ', description='不可叫人小看你年輕,總要在言語、行為、愛心、信心、清潔上,都做信徒的榜樣.提摩太前書4:12', isContributorsEnabled=false, profileImageUrl='http://a0.twimg.com/profile_images/1509710346/IMG_0108____normal.jpg', profileImageUrlHttps='https://twimg0-a.akamaihd.net/profile_images/1509710346/IMG_0108____normal.jpg', url='http://t.co/eTvs8F7UuC', isProtected=false, followersCount=162, status=StatusJSONImpl{createdAt=Fri Apr 12 09:30:58 CST 2013, id=322522195962241025, text='Morning Chiang Mai...Breakfast????? http://t.co/vD26qmy9SZ', source='<a href=\"http://instagram.com\" rel=\"nofollow\">Instagram</a>', isTruncated=false, inReplyToStatusId=-1, inReplyToUserId=-1, isFavorited=false, inReplyToScreenName='null', geoLocation=null, place=null, retweetCount=0, isPossiblySensitive=false, contributorsIDs=[J@6d3d7254, retweetedStatus=null, userMentionEntities=[], urlEntities=[URLEntityJSONImpl{url='http://t.co/vD26qmy9SZ', expandedURL='http://instagram.com/p/X_HMiAleM2/', displayURL='instagram.com/p/X_HMiAleM2/'}], hashtagEntities=[], mediaEntities=[], currentUserRetweetId=-1, user=null}, profileBackgroundColor='FF6699', profileTextColor='362720', profileLinkColor='B40B43', profileSidebarFillColor='E5507E', profileSidebarBorderColor='CC3366', profileUseBackgroundImage=true, showAllInlineMedia=false, friendsCount=140, createdAt=Mon Mar 21 14:54:23 CST 2011, favouritesCount=0, utcOffset=-32400, timeZone='Alaska', profileBackgroundImageUrl='http://a0.twimg.com/images/themes/theme11/bg.gif', profileBackgroundImageUrlHttps='https://twimg0-a.akamaihd.net/images/themes/theme11/bg.gif', profileBackgroundTiled=true, lang='en', statusesCount=163, isGeoEnabled=true, isVerified=false, translator=false, listedCount=11, isFollowRequestSent=false}"));
			 System.out.println(filterOffUtf8Mb4("Пр�вет"));
		 } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
	    }
	 
	
	 
	/// <summary>
	/// Removes control characters and other non-UTF-8 characters
	/// </summary>
	/// <param name="inString">The string to process</param>
	/// <returns>A string with no control characters or entities above 0x00FD</returns>
	public static String removeNonUtf8CompliantCharacters(String inString)
	{
	    if (inString == null) return null;

	    StringBuilder newString = new StringBuilder();
	    char ch;

	    for (int i = 0; i < inString.length(); i++)
	    {

	        ch = inString.charAt(i);
	        // remove any characters outside the valid UTF-8 range as well as all control characters
	        // except tabs and new lines
	        if ((ch < 0x00FD && ch > 0x001F) || ch == '\t' || ch == '\n' || ch == '\r')
	        {
	            newString.append(ch);
	        }
	    }
	    return newString.toString();

	}
	

	/*
	 * 过滤非汉字的utf8的字符
	 * 
	 * utf8是变长字符集，单个字符占用1～4个字节。mysql在选择utf8字符集时，
	 * 最多只能存储3个字节的utf8字符，如果想要保存任意的utf8字符，
	 * 数据必须用utf8mb4字符集，有些情况下，不能变更已选定的字符集，
	 * 只好不得以而为之，把输入中的4个字节的utf8字符全部过滤掉，
	 * 好在，utf8字符集中，汉字是3个字节的。 
	 */
	public static  String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("utf-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }
            b += 256;
            if ((b ^ 0xC0) >> 4 == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            }
            else if ((b ^ 0xE0) >> 4 == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            }
            else if ((b ^ 0xF0) >> 4 == 0) {
                i += 4;
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }
}
