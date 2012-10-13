package gr.polaris.model;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 
 * @author Chtiwi Malek
 * @source www.codicode.com/art/upload_files_from_android_to_a_w.aspx
 * 
 */
public class ImageUpload implements Runnable {

	private static final String LOG_TAG = "ImageUpload";
	private static final int MAX_BUFFER_SIZE = 40 * 1024; // 20 KiB

	private URL serverUrl;
	private String response;
	private String image;

	private static ArrayList<String> res;
	private static int i;

	public ImageUpload(String url) {
		// TODO DEBUG
		// i = 0;
		// res = new ArrayList<String>();
		// res.add("L224");
		// res.add("A308");
		// res.add("E106");
		// res.add("L333");
		// res.add("E106");
		// res.add("C215");
		// res.add("C204");

		// ~ TODO DEBUG
		try {
			this.serverUrl = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.e(LOG_TAG, e.getLocalizedMessage());
		}
	}

	public void sendImage(String fStream) {

		image = fStream;
		run();
	}

	public void run() {
		try {
			HttpURLConnection conn = (HttpURLConnection) serverUrl
					.openConnection();
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// Read response
			Log.i(LOG_TAG,
					"Response from server: "
							+ String.valueOf(conn.getResponseCode()));
			if (conn.getResponseCode() != 200) {
				Log.e(LOG_TAG, "Wrong response code!");
				conn.disconnect();
				return;
			}
			InputStream is = conn.getInputStream();
			// retrieve the response from server
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			this.response = b.toString();
			conn.disconnect();
		} catch (IOException ex) {
			Log.e(LOG_TAG, ex.getMessage(), ex);
		}
	}

	public void run2() {
		try {
			// Open a HTTP connection
			HttpURLConnection conn = (HttpURLConnection) serverUrl
					.openConnection();

			// Allow inputs & outputs
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// Don't use cache
			conn.setUseCaches(false);
			// Use a post method
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "image/jpeg");

			// @source
			// http://blog.sptechnolab.com/2011/03/09/android/android-upload-image-to-server/
			Log.i(LOG_TAG, "Decoding image from file");
			Bitmap im = BitmapFactory.decodeStream(new FileInputStream(image));
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			Log.i(LOG_TAG, "Compressing JPEG format");
			im.compress(Bitmap.CompressFormat.JPEG, 90, bao);
			// File f = new File(image + ".compress.jpg");
			// FileOutputStream fos = new FileOutputStream(f);
			byte[] ba = bao.toByteArray();
			// fos.write(ba);
			// fos.close();
			Log.i(LOG_TAG, "Encoding JPEG format");
			// String ba1=Base64.encodeToString(ba, Base64.NO_WRAP);
			// f = new File(image + ".base64.jpg");
			// DataOutputStream dfs = new DataOutputStream(new
			// FileOutputStream(f));
			// dfs.writeBytes(ba1);
			// dfs.close();

			Log.i(LOG_TAG, "Sending string content to server");

			// Write image to the websites output stream
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			// dos.writeBytes("image=");
			// create a buffer of maximum size
			int maxSize = ba.length;
			int maxBufferSize = MAX_BUFFER_SIZE;
			Log.i(LOG_TAG, "Size of string: " + String.valueOf(maxSize));

			int bufferSize = Math.min(maxSize, maxBufferSize);

			// Read bytes from string and write it into server output...
			// ba1.substring(start, end)
			int i = 0;
			do {
				dos.write(ba, i, bufferSize);
				dos.flush();
				i += bufferSize;
				bufferSize = Math.min(maxSize - i, maxBufferSize);
				Log.i(LOG_TAG, "i " + String.valueOf(i) + "; bufferSize "
						+ String.valueOf(bufferSize));
				// Log.i(LOG_TAG, " "+String.valueOf(i) +
				// "; bufferSize "+String.valueOf(bufferSize));
			} while (i < maxSize);
			// dos.writeBytes(LE);
			dos.close();
			Log.i(LOG_TAG, "Finished");

			try {
				// Read response
				Log.i(LOG_TAG,
						"Response from server: "
								+ String.valueOf(conn.getResponseCode()));
				if (conn.getResponseCode() != 200) {
					Log.e(LOG_TAG, "Wrong response code!");
					conn.disconnect();
					return;
				}
				InputStream is = conn.getInputStream();
				// retrieve the response from server
				int ch;
				StringBuffer b = new StringBuffer();
				while ((ch = is.read()) != -1) {
					b.append((char) ch);
				}
				this.response = b.toString();
				conn.disconnect();
			} catch (FileNotFoundException e) {
				Log.e(LOG_TAG, "Exception: " + e.getMessage(), e);
			}

		} catch (MalformedURLException ex) {
			Log.e(LOG_TAG, ex.getMessage(), ex);
		} catch (IOException ex) {
			Log.e(LOG_TAG, ex.getLocalizedMessage(), ex);
		}
	}

	// public void run()
	// {
	// //try
	// //{
	// Log.i(LOG_TAG, "Decoding image from file");
	// Bitmap im = BitmapFactory.decodeStream( new FileInputStream(image) );
	// ByteArrayOutputStream bao = new ByteArrayOutputStream();
	// Log.i(LOG_TAG, "Compressing JPEG format");
	// im.compress(Bitmap.CompressFormat.JPEG, 70, bao);
	// //File f = new File(image + ".compress.jpg");
	// //FileOutputStream fos = new FileOutputStream(f);
	// byte [] ba = bao.toByteArray();
	//
	// // HttpClient hc = new DefaultHttpClient();
	//
	// }
	// }

	public String getResponse() {
		return this.response;
	}

}
