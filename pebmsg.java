//Here is the source code for my app, PebMsg.
//The link below goes to the App in the play store
//https://play.google.com/store/apps/details?id=co.exampl.pebmsg
//Feel free to do anything with this code.
//The most useful bit will be the sendMsg method, a super easy way to send messages to pebble
//Good luck!
//Created by Jonathan Davis 
//jonnysdavis|at|gmail|dot|com

package com.example.pebmsg;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import co.exampl.pebmsg.R;

public class MainActivity extends Activity {
	public final static String PEBBLE_ALERT = "PEBBLE_ALERT";
	String title, body;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button Send = (Button) findViewById(R.id.button1);
		//If they click the button, start the message send
		Send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Method GimmeOne is the first step (at the bottom of the code)
				GimmeOne();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	public void sendMsg(String name, String text) {
		//Name = Header text, text = body text
		//I copied this code right of off the pebble website
		//Be sure to declare || public final static String PEBBLE_ALERT = "PEBBLE_ALERT"; || somewhere
		final Intent i = new Intent("com.getpebble.action.SEND_NOTIFICATION");
		final Map<String, Object> data = new HashMap<String, Object>();
		//The title text is set to name
		data.put("title", name);
		//Body text set to body text
		data.put("body", text);
		final JSONObject jsonData = new JSONObject(data);
		final String notificationData = new JSONArray().put(jsonData)
				.toString();
		i.putExtra("messageType", PEBBLE_ALERT);
		i.putExtra("sender", "MyAndroidApp");
		i.putExtra("notificationData", notificationData);
		//Sends the data
		sendBroadcast(i);
	}
	public void Delay() {
		int inputnum;
		//Get an EditText variable for the delay field
		EditText editText = (EditText) findViewById(R.id.editText3);
		//Check if blank, if it is no delay. 
		if (editText.getText().toString().equals("")) {
			inputnum=0;
		}
		else {
		String temp = editText.getText().toString();
		inputnum = Integer.parseInt(temp);
		}
		try {
			//sleep for input * 1000 to convert milliseconds to seconds
			Thread.sleep(inputnum*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void GimmeOne() {
		//Get an EditText variable for the head field
		EditText editText = (EditText) findViewById(R.id.editText1);
		//Check if head field is blank, otherwise get input
		if (editText.getText().toString().equals("")) {
			title = "[blank]";
		} else {
			title = editText.getText().toString();
		}
		//Same as above, but for the body
		EditText editText2 = (EditText) findViewById(R.id.editText2);
		if (editText2.getText().toString().equals("")) {
			body = "[blank]";
		} else {
			body = (editText2.getText().toString());
		}
		//Adds a delay before sending, if none then their will be no delay
		Delay();
		//Pushes the message to the pebble
    	sendMsg(title, body);
	}
}
