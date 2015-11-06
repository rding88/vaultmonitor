package com.intelliwaves.vaultclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class vaultNofity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
			  	System.out.println("Client running .... \n");
			  	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			  	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			  	String timeStamp = dateFormat.format(new Date());
			  	System.out.println(timeStamp);
			  	
				URL url = new URL("http://localhost:8080/vaultmonitor/webapi/vaultmsg/" + timeStamp);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				//conn.setRequestProperty("Accept", "application/json");
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }

			}
		
		
	}


