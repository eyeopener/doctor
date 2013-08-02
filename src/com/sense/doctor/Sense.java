package com.sense.doctor;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.TabHost;

public class Sense extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sense);
		
	}
	 Drawable icon_tab_1, icon_tab_2, icon_tab_3, icon_tab_4;
	private void parseHorizontalTab() 
	{  
		// 注意下面的代码用的是android.R.id.tabhost，在布局中有2个ID参数是固定的需要使用固定的ID:  
		// 选项卡：TabWidget->android:id/tabs  
		// 选项内容：FrameLayout android:id="android:id/tabcontent"  
		final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);  
		tabHost.setup();  
		icon_tab_1 = this.getResources().getDrawable(R.drawable.ic_laucher);  
		icon_tab_2 = this.getResources().getDrawable(R.drawable.icon2); 
		icon_tab_3 = this.getResources().getDrawable(R.drawable.icon3);  
		icon_tab_4 = this.getResources().getDrawable(R.drawable.icon4);  
		createHorizontalTab(tabHost); 
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sense, menu);
		return true;
	}

}
