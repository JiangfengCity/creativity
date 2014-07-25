package com.yutian.util;

import java.security.MessageDigest;

/**
 * @author 沈锋 email: shenfeng@yutian.com.cn
 * @Description: MD5加密工具类。
 * @date 2013-7-8
 */
public final class MD5Utils
{
	public static final String	HASH_KEY	= "MD52011CLENT";

	private MD5Utils()
	{}

	public static String byteArrayToHexString( byte byteArray[] )
	{
		StringBuffer strHex = new StringBuffer();
		for( int i = 0; i < byteArray.length; i++ )
			strHex.append( byteToHexString( byteArray[ i ] ) );

		return strHex.toString();
	}

	private static String byteToHexString( byte bt )
	{
		int i = bt;
		if( i < 0 )
			i += 256;
		int j = i / 16;
		int k = i % 16;
		return HEX_DIGITS[ j ] + HEX_DIGITS[ k ];
	}

	public static String MD5Encode( String strOrigin )
	{
		String strAim = null;
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance( "MD5" );
			strAim = byteArrayToHexString( messageDigest.digest( strOrigin.getBytes() ) );
		}
		catch( Exception exception )
		{}
		return strAim;
	}

	private static final String	HEX_DIGITS[]	= { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 生成MD5干扰码
	 * 
	 * @return
	 */
	public static String makeMD5Salt()
	{
		Long currentTime = System.currentTimeMillis();
		String result = MD5Utils.MD5Encode( currentTime + "" );
		return result.substring( result.length() - 6 );
	}

	/**
	 * 二次加密，干扰码不存在则单独二次加密
	 * 
	 * @param password
	 * @param md5Salt
	 * @return
	 */
	public static String md5ToTwo( String password, String md5Salt )
	{

		if( password == null ) { return null; }
		if( md5Salt == null )
		{
			md5Salt = "";
		}
		return MD5Utils.MD5Encode( password + md5Salt );
	}
}
