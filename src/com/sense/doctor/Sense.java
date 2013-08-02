package com.sense.doctor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class Sense extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sense);

	}

	Drawable icon_tab_1, icon_tab_2, icon_tab_3, icon_tab_4;

	private void parseHorizontalTab() {
		// 注意下面的代码用的是android.R.id.tabhost，在布局中有2个ID参数是固定的需要使用固定的ID:
		// 选项卡：TabWidget->android:id/tabs
		// 选项内容：FrameLayout android:id="android:id/tabcontent"
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
