package gr.polaris.blbecci;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
	private String title;
	private String description;
	private FileInputStream image;
	
	public ImageUpload(String url, String title, String desc)
	{
		try {
			this.serverUrl = new URL(url);
			this.title = title;
			this.description = desc;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.e(LOG_TAG, e.getLocalizedMessage());
		}
	}
	
	public void sendImage(FileInputStream fStream)
	{
		image = fStream;
		run();
	}
	
	public void run()
	{
		String fName = "image.jpg";
		String LE = "\r\n";
		String hypens = "--";
		String boundary = "***" + fName + "***";
		
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
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Connection-Type", "image/jpeg");
			conn.setRequestProperty("Connection-Transfer-Encoding", "binary");
			
			// Write image to the website output stream
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			// - Title
			//dos.writeBytes(hypens + boundary + LE);
			//dos.writeBytes("Content-Disposition: form-data; name=\"title\"" + LE);
			//dos.writeBytes(LE + this.title + LE);
			//dos.writeBytes(hypens + boundary + LE);
			// - Description
			//dos.writeBytes("Content-Disposition: form-data; name=\"description\"" + LE);
			//dos.writeBytes(LE + this.description + LE);
			/*
			dos.writeBytes(hypens + boundary + LE);
			// - image data
			dos.writeBytes("Content-Disposition: form-data; name=\"uploadedimage\";filename=\""+fName+"\"" + LE);
			dos.writeBytes("Content-Type: image/jpeg" + LE);
			dos.writeBytes("Content-Transfer-Encoding: binary" + LE);
			dos.writeBytes(LE);
			*/
			//dos.writeBytes("image" + LE + LE);
			// create a buffer of maximum size
			int bytesAvailable = image.available();
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize);
			
			byte[] buffer = new byte[bufferSize];
			
			// Read bytes from file and write it into form...
			int bytesRead = image.read(buffer, 0, bufferSize);
			while(bytesRead > 0)
			{
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = image.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = image.read(buffer, 0, bufferSize);
			}
			
			//dos.writeBytes(LE);
			
			//dos.writeBytes(hypens + boundary + hypens + LE);
			
			// close streams
			image.close();
			dos.flush();
			
			// Read response
			Log.i(LOG_TAG, "Response from server: " + String.valueOf(conn.getResponseCode()));
			
			InputStream is = conn.getInputStream();
			// retrieve the response from server
			int ch;
			StringBuffer b = new StringBuffer();
			while( (ch = is.read()) != -1) { b.append( (char)ch); }
			this.response = b.toString();
			dos.close();
			
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
