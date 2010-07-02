package com.jdhuntington.learnr;

import java.io.StringReader;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Learnr extends Activity {
    
	DefaultHttpClient client = new DefaultHttpClient();
	ImageView iv = null;
	Button b1 = null;
	Button b2 = null;
	TextView theView = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        iv = (ImageView) this.findViewById(R.id.ImageView01);
        b1 = (Button) this.findViewById(R.id.Button01);
        b2 = (Button) this.findViewById(R.id.Button02);
        theView = (TextView) this.findViewById(R.id.thetext);
        
        b1.setOnClickListener(onClick);
        b2.setOnClickListener(goAndGet);
    }
    
    public void displayImage(Uri resourceUri)
    {
    
    }
    
    public void displayBusArrivals(int stopId) {
    	String url = "http://developer.trimet.org/ws/V1/arrivals?locIDs=" + stopId + "&appID=10A08D676D628A96A222E424E";
    	HttpGet getMethod = new HttpGet(url);
    	try {
    		ResponseHandler<String> handler = new BasicResponseHandler();
    		String responseBody = client.execute(getMethod, handler);
    		theView.setText(responseBody);
    		
    		TransitFeedParser.parse(new StringReader(responseBody));
    	}
    	catch (Throwable t) {
    		android.util.Log.e("BUSted", "Exception fetching data", t);
    		Toast.makeText(this, "Failure: " + t.toString(), 4000).show();
    	}
    }
    
    
    private View.OnClickListener goAndGet = new View.OnClickListener() {
		public void onClick(View v) {
			displayBusArrivals(12450);
		}
    };
    
    private View.OnClickListener onClick = new View.OnClickListener() {
		public void onClick(View v) {
			Uri uri = Uri.parse("http://farm5.static.flickr.com/4095/4750690015_26a73971de.jpg");
			displayImage(uri);
		}
    };
}