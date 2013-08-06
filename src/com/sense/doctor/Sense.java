package com.sense.doctor;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

enum WhichView {
	MAIN_MENU, ZZCX_VIEW, CCCX_VIEW, CZCCCX_VIEW, LIST_VIEW, PASSSTATION_VIEW, CCTJ_VIEW, CZTJ_VIEW, GXTJ_VIEW, FJGN_VIEW, WELCOME_VIEW, ABOUT_VIEW, HELP_VIEW
}

public class Sense extends Activity {

	WelcomeView wv;//进入欢迎界面
	WhichView curr;//当前枚举值	
	
	@SuppressLint("HandlerLeak")
	Handler hd = new Handler()// 声明消息处理器
	{
		@Override
		public void handleMessage(Message msg)// 重写方法
		{
			switch (msg.what) {
			case 0:// 进入欢迎界面
					 goToWelcomeView();

				break;
			case 1:// 进入菜单界面
					parseHorizontalTab();
				break;
			case 2:// 进入关于界面
					// setContentView(R.layout.about);
					// curr=WhichView.ABOUT_VIEW;
				break;
			case 3:// 进入帮助界面
					// setContentView(R.layout.help);
					// curr=WhichView.HELP_VIEW;
				break;

			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置为全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 设置横屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.main_tab_horizontal);


		this.hd.sendEmptyMessage(0); // 发送消息进入欢迎界面

	}
	 
	public void goToWelcomeView()
	    {
	    	if(wv==null)//如果该对象没创建则创建
	    	{
	    		wv=new WelcomeView(this);
	    	}
	    	setContentView(wv);
	    	curr=WhichView.WELCOME_VIEW;//标识当前所在界面
	    }
	
	Drawable icon_tab_1, icon_tab_2, icon_tab_3, icon_tab_4;

	private void parseHorizontalTab() {
		// 注意下面的代码用的是android.R.id.tabhost，在布局中有2个ID参数是固定的需要使用固定的ID:
		// 选项卡：TabWidget->android:id/tabs
		// 选项内容：FrameLayout android:id="android:id/tabcontent"
		setContentView(R.layout.main_tab_horizontal);
		curr=WhichView.MAIN_MENU;//标识当前所在界面	
		
		final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
		tabHost.setup();
		icon_tab_1 = this.getResources().getDrawable(R.drawable.ic_launcher);
		icon_tab_2 = this.getResources().getDrawable(R.drawable.ic_launcher);
		icon_tab_3 = this.getResources().getDrawable(R.drawable.ic_launcher);
		icon_tab_4 = this.getResources().getDrawable(R.drawable.ic_launcher);
	
		createHorizontalTab(tabHost);

	}

	private void createHorizontalTab(TabHost tabHost) {
		tabHost.addTab(tabHost
				.newTabSpec("tab1")
				.setIndicator(
						createIndicatorView(this, tabHost, icon_tab_1, "tab1"))
				.setContent(R.id.id_tab_view1));
		tabHost.addTab(tabHost
				.newTabSpec("tab2")
				.setIndicator(
						createIndicatorView(this, tabHost, icon_tab_2, "tab2"))
				.setContent(R.id.id_tab_view2));
		tabHost.addTab(tabHost
				.newTabSpec("tab3")
				.setIndicator(
						createIndicatorView(this, tabHost, icon_tab_3, "tab3"))
				.setContent(R.id.id_tab_view3));
		tabHost.addTab(tabHost
				.newTabSpec("tab4")
				.setIndicator(
						createIndicatorView(this, tabHost, icon_tab_4, "tab4"))
				.setContent(R.id.id_tab_view4));
		TabWidget tw = tabHost.getTabWidget();
		tw.setOrientation(LinearLayout.VERTICAL);
		// 注意在此处设置此参数使TAB 垂直布局
	}

	private View createIndicatorView(Context context, TabHost tabHost,
			Drawable icon, String title) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View tabIndicator = inflater.inflate(R.layout.tab_indicator_horizontal,
				tabHost.getTabWidget(), false);
		final ImageView iconView = (ImageView) tabIndicator
				.findViewById(R.id.icon);
		final TextView titleView = (TextView) tabIndicator
				.findViewById(R.id.title);
		titleView.setText(title);
		iconView.setImageDrawable(icon);
		return tabIndicator;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sense, menu);
		return true;
	}

}
