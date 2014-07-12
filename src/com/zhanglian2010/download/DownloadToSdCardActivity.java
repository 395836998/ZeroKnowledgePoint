package com.zhanglian2010.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zhanglian2010.R;

public class DownloadToSdCardActivity extends Activity {

	private Button downButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_to_sd_card);
		
		downButton = (Button) findViewById(R.id.downId);
		downButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				new Thread(){
					@Override
					public void run() {
						super.run();
						
						downloadFileToSdcard("http://www.zhanglian2010.cn/wp-content/uploads/2014/04/viewgroup.png",
								"ddd/", "viewgroup.png");
						System.out.println("download finish in thread...");
						
						handler.sendEmptyMessage(0);
					}
				}.start();
				
			}
		});
	}

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			System.out.println("download finish in main...");
		};
	};
	
	
	private void downloadFileToSdcard(String urlStr, String path, String fileName){
		try{
			URL url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			InputStream inputStream = urlConn.getInputStream();
			write2SDFromInput(path, fileName, inputStream);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void write2SDFromInput(String path, String fileName, InputStream input){
		OutputStream output = null;
		String SDPATH = Environment.getExternalStorageDirectory() + "/"; 
		try{
			File dir = new File(SDPATH + path);
			dir.mkdir();
			
			File file2 = new File(SDPATH + path + fileName);
			file2.createNewFile();
			
			output = new FileOutputStream(file2);

			byte buffer[] = new byte[4*1024];
			while((input.read(buffer)) != -1){
				output.write(buffer);
			}
			output.flush();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.download_to_sd_card, menu);
		return true;
	}

}
