package com.hrw.android.player.activity;




import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ShareActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,0,0,"分享");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			Intent intent=new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			//分享文字
//			intent.setType("text/pain");			
//			intent.putExtra(Intent.EXTRA_TEXT, "jkjjj");
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			startActivity(Intent.createChooser(intent, getTitle()));
//			return true;
			
//			添加图片  
//          intent.setType("image/png");  		
//          File f = new File(Environment.getExternalStorageDirectory()+"/name.png");
//		    Uri uri = Uri.fromFile(f);  
//		    intent.putExtra(Intent.EXTRA_STREAM, uri); 
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			startActivity(Intent.createChooser(intent, getTitle()));
//			return true;
		
		//分享MP3 
			//设置分享类型（注：intent.setType(*/*);表示代表全部）
			intent.setType("audio/mp3");
			//在sd卡中找到要分享的mp3文件
	        File f = new File(Environment.getExternalStorageDirectory()+"/name.mp3");
		    Uri uri = Uri.fromFile(f); 
		    //分享
			intent.putExtra(Intent.EXTRA_STREAM, uri);  
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(Intent.createChooser(intent, getTitle()));
			return true;
			
	
		}
		return false;
	}
	

	
	

}
