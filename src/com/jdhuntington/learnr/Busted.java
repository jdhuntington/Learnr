package com.jdhuntington.busted;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Busted extends Activity {
    DefaultHttpClient client = new DefaultHttpClient();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button save=(Button)findViewById(R.id.Button01);
		save.setOnClickListener(goAndGet);
		
    }
    
    private View.OnClickListener goAndGet = new View.OnClickListener() {
		public void onClick(View v) {
			displayBusArrivals(12450);
		}
    };
    
    public void displayBusArrivals(int stopId) {
    	String url = "http://developer.trimet.org/ws/V1/arrivals?locIDs=" + stopId + "&appID=10A08D676D628A96A222E424E";
    	HttpGet getMethod = new HttpGet(url);
    	try {
    		ResponseHandler<String> handler = new BasicResponseHandler();
    		String responseBody = client.execute(getMethod, handler);
    		((TextView)this.findViewById(R.id.hi)).setText(responseBody);

    	}
    	catch (Throwable t) {
    		android.util.Log.e("BUSted", "Exception fetching data", t);
    		Toast.makeText(this, "Failure: " + t.toString(), 4000).show();
    	}
    }
}