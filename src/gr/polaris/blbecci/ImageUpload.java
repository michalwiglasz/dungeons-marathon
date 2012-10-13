package gr.polaris.blbecci;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

/**
 * 
 * @author Chtiwi Malek
 * @source www.codicode.com/art/upload_files_from_android_to_a_w.aspx
 *
 */
public class ImageUpload implements Runnable {
	
	private static final String LOG_TAG = "ImageUpload"; 

	private URL serverUrl;
	private String response;
	private String image;
	
	public ImageUpload(String url)
	{
		try {
			this.serverUrl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.e(LOG_TAG, e.getLocalizedMessage());
		}
	}
	
	public void sendImage(String fStream)
	{
		image = fStream;
		run();
	}
	
	public void run()
	{
		try
		{
			// Open a HTTP connection
			HttpURLConnection conn = (HttpURLConnection)serverUrl.openConnection();
			
			//Allow inputs & outputs
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// Don't use cache
			conn.setUseCaches(false);
			// Use a post method
			conn.setRequestMethod("POST");
			
			// @source http://blog.sptechnolab.com/2011/03/09/android/android-upload-image-to-server/
			Log.i(LOG_TAG, "Decoding image from file");
			Bitmap im = BitmapFactory.decodeStream( new FileInputStream(image) );
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			Log.i(LOG_TAG, "Compressing JPEG format");
			im.compress(Bitmap.CompressFormat.JPEG, 70, bao);
			//File f = new File(image + ".compress.jpg");
			//FileOutputStream fos = new FileOutputStream(f);
			byte [] ba = bao.toByteArray();
			//fos.write(ba);
			//fos.close();
			Log.i(LOG_TAG, "Encoding JPEG format");
			String ba1=Base64.encodeToString(ba, Base64.DEFAULT);
			//f = new File(image + ".base64.jpg");
			//DataOutputStream dfs = new DataOutputStream(new FileOutputStream(f));
			//dfs.writeBytes(ba1);
			//dfs.close();
			
			Log.i(LOG_TAG, "Sending string content to server");
			
			// Write image to the websites output stream
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.writeBytes("image=");
			// create a buffer of maximum size
			int maxSize = ba1.length();
			int maxBufferSize = 10*1024;	// 10KiB
			Log.i(LOG_TAG, "Size of string: " + String.valueOf(maxSize));
			
			int bufferSize = Math.min(maxSize, maxBufferSize);
			
			// Read bytes from string and write it into server output...
			//ba1.substring(start, end)
			int i = 0;
			do
			{
				dos.writeBytes(ba1.substring(i, i + bufferSize));
				dos.flush();
				i += bufferSize;
				bufferSize = Math.min(maxSize - i, maxBufferSize);
				Log.i(LOG_TAG, "i "+String.valueOf(i) + "; bufferSize "+String.valueOf(bufferSize));
				//Log.i(LOG_TAG, " "+String.valueOf(i) + "; bufferSize "+String.valueOf(bufferSize));
			} while(i < maxSize);
			//dos.writeBytes(LE);
			dos.close();
			Log.i(LOG_TAG, "Finished");
			
			// Read response
			Log.i(LOG_TAG, "Response from server: " + String.valueOf(conn.getResponseCode()));
			
			InputStream is = conn.getInputStream();
			// retrieve the response from server
			int ch;
			StringBuffer b = new StringBuffer();
			while( (ch = is.read()) != -1) { b.append( (char)ch); }
			this.response = b.toString();
			conn.disconnect();
			
		}
		catch(MalformedURLException ex)
		{
			Log.e(LOG_TAG, ex.getLocalizedMessage(), ex);
		}
		catch(IOException ex)
		{
			Log.e(LOG_TAG, ex.getLocalizedMessage(), ex);
		}
	}
	
	public String getResponse()
	{
		return this.response;
	}

}
