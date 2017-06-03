import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class ThingSpeakerReader {
	
	String jsonString;
	
	public static void main(String[] args) {
		
		String ServerURL = "https://api.thingspeak.com/channels/269958/feed.json?results=1500";
		
		ThingSpeakerReader reader = new ThingSpeakerReader(ServerURL);
				
	}
	public ThingSpeakerReader(String URL) {
		
		readFromUrl(URL);
		Gson gson = new Gson();
		Feeds feeds=gson.fromJson(jsonString, Feeds.class);
		printAllData(feeds,feeds.getChannel().getId(),feeds.getChannel().getName());
		//System.out.println("ID: " +  feeds.getChannel().getId() + " Name : " + feeds.getChannel().getName());
		
	}

	private void printAllData(Feeds feeds,String ID,String TableName) {
		
		 String []ALLDATA = new String[16000];
		 String temp1,temp2;
		 
		 int []time = new int[16000];
		 
		 int i=0;
		for(Feed f : feeds.getFeeds()){
			
			
			if( f.getField1() != null && f.getField2() != null ){
				int y=Integer.parseInt(f.getCreated_at().substring(11, 13));
				time[i]=(y+3)%24;
				temp1 = f.getField1().replace("\n", "").replace("\r", "");
				temp2 = f.getField2().replace("\n", "").replace("\r", "");
				if(Integer.parseInt(temp1) > 0 && Integer.parseInt(temp2) > 0){
					ALLDATA[i]   = f.getField1();
					ALLDATA[i+1] = f.getField2();
					ALLDATA[i+2] = f.getCreated_at().substring(0, 10);
					ALLDATA[i+3] = time[i]+f.getCreated_at().substring(13, 19);
				
				
					//System.out.println("Temperature :"+f.getField1()+" Date :"+f.getCreated_at().substring(0, 10)+" Time :"+f.getCreated_at().substring(11, 19));
					i=i+4;
				}
			}
				
				
		}
		Screen screen=new Screen(ALLDATA,ID,TableName);
		screen.setVisible(true);
		
	}

	public void readFromUrl(String url) {
		jsonString = null;
		URLConnection con;
		try {
			URL urlInstance = new URL(url);
			con = urlInstance.openConnection();
			con.setRequestProperty("Connection", "Keep-Alive");
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			jsonString = "";
			while ((inputLine = in.readLine()) != null) {
				jsonString += inputLine;
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();		
		} catch (SocketTimeoutException e){
			e.printStackTrace();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	

	
}
