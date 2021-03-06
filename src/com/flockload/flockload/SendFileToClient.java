package com.flockload.flockload;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import com.suprith.flockload.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SendFileToClient extends Activity implements AsyncResponse{
	private DownloadParams dp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server);
		Bundle extras = getIntent().getExtras();
		
		if (extras != null) {
		    dp = (DownloadParams) extras.getSerializable("com.suprith.flockload.DownloadParams");
		}
		
		Button clientButton = (Button)findViewById(R.id.serverButton);
		
		clientButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				new ServerSendAsyncTask(SendFileToClient.this).execute(dp);
				
				}
		});
	}
	@Override
	public void processFinish(Integer result) throws InterruptedException,
			ExecutionException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void processFinish(DownloadParams dp) throws InterruptedException,
			ExecutionException {
		// TODO Auto-generated method stub
		if(dp != null){
			System.out.println("Compelted sending the file to client");
			Toast.makeText(SendFileToClient.this, "Sent to client", Toast.LENGTH_SHORT).show();
			System.out.println("Sending client file");
			Intent intent = new Intent(SendFileToClient.this,GetFileFromClient.class);
			intent.putExtra("com.suprith.flockload.DownloadParams", dp);
			startActivity(intent);
		
	}
		
	}
	@Override
	public void onDestroy() {
	    super.onDestroy();  // Always call the superclass
	    
	    // Stop method tracing that the activity started during onCreate()
	    
	}
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onResume(){
		super.onResume();
	}
	
	@Override
	public void onStop(){
		super.onStop();
	}
	


}
