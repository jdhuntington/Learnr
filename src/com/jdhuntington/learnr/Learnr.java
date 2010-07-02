package com.jdhuntington.learnr;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Learnr extends Activity {
    
	ImageView iv = null;
	Button b1 = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        iv = (ImageView) this.findViewById(R.id.ImageView01);
        b1 = (Button) this.findViewById(R.id.Button01);
        b1.setOnClickListener(onClick);
    }
    
    public void displayImage(Uri resourceUri)
    {
    	
    }
    
    private View.OnClickListener onClick = new View.OnClickListener() {
		public void onClick(View v) {
			Uri uri = Uri.parse("http://farm5.static.flickr.com/4095/4750690015_26a73971de.jpg");
			displayImage(uri);
		}
    };
}