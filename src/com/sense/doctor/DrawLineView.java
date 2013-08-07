package com.sense.doctor;

import java.security.PublicKey;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Toast;
import android.widget.LinearLayout;

@SuppressLint("DrawAllocation")
public class DrawLineView extends View {

	private int mov_start_x = 0;// 声明起点坐标
	private int mov_start_y = 0;
	private int mov_end_x = 0;// 声明起点坐标
	private int mov_end_y = 0;

	private Bitmap bitmap;// 位图
	private Canvas canvas;// 画布

	private int m_screenWith = 200;
	private int m_PaitMarginLeft = 100;
	private int m_PaitMarginTop = 100;
	private int m_PaitWidth = 100;
	private int m_PaitHeigth = 50;

	private int m_LableMarginTop = 20;
	private int m_LableHeigth = 25;

	int COMPONENT_WIDTH; // 该控件宽度
	int COMPONENT_HEIGHT; // 该控件高度
	boolean initflag = false; // 是否要获取控件的高度和宽度标志
	static Bitmap[] bma; // 需要播放的图片的数组
	Paint paint; // 画笔
	int[] drawablesId; // 图片ID数组
	int currIndex = 0; // 图片ID数组下标，根据此变量画图片
	boolean workFlag = true; // 播放图片线程标志位

	public DrawLineView(Context father, AttributeSet as) { // 构造器
		super(father, as);

		paint = new Paint(Paint.DITHER_FLAG);// 创建一个画笔

		bitmap = Bitmap.createBitmap(m_PaitWidth, m_PaitHeigth,
				Bitmap.Config.ARGB_8888); // 设置位图的宽高
		canvas = new Canvas();
		canvas.setBitmap(bitmap);

	}

	public void initBitmaps() { // 初始化图片函数
		Resources res = this.getResources(); // 获取Resources对象
		for (int i = 0; i < drawablesId.length; i++) {
			bma[i] = BitmapFactory.decodeResource(res, drawablesId[i]);
		}
	}

	public void onDraw(Canvas canvas) { // 绘制函数
		if (!initflag) { // 第一次绘制时需要获取宽度和高度
			COMPONENT_WIDTH = this.getWidth(); // 获取view的宽度
			COMPONENT_HEIGHT = this.getHeight(); // 获取view的高度
			initflag = true;
		}
		// 绘制疼痛条

		//
		paint.setStyle(Style.FILL);// 设置填充
		paint.setColor(Color.GRAY);// 设置为灰色
		LinearGradient linearGradient = new LinearGradient(50, 50, 200, 70,
				new int[] { Color.RED, Color.RED, Color.RED, Color.RED,
						Color.YELLOW, Color.GREEN, Color.BLUE }, null,
				Shader.TileMode.REPEAT);
		paint.setShader(linearGradient);
		Rect r = new Rect(50, 50, 200, 70);
		canvas.drawRect(r, paint);
		paint.setShader(null);

		// canvas.drawARGB(255, 200, 128, 128); // 设置背景色
		// canvas.drawBitmap(bma[currIndex], startX, startY, paint); // 绘制图片
	}

	public void ReDraw() {

		
		// 绘制疼痛条
		paint.setStyle(Style.FILL);// 设置填充
		paint.setColor(Color.GRAY);// 设置为灰色
		LinearGradient linearGradient = new LinearGradient(50, 50, 200, 70,
				 new int[] { Color.RED,
						Color.RED, Color.RED, Color.RED, Color.YELLOW,
						Color.GREEN, Color.BLUE }, null, Shader.TileMode.REPEAT);
		paint.setShader(linearGradient);
		Rect r = new Rect(50, 50, 200, 70);
		canvas.drawRect(r, paint);
		paint.setShader(null);

		// draw text
		// paint.setStyle(Style.STROKE);// 设置非填充
		paint.setStrokeWidth((float) 1);// 笔宽5像素
		paint.setFakeBoldText(false);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		paint.setAntiAlias(true); // 消除锯齿
		// paint.setFlags(Paint.ANTI_ALIAS_FLAG); // 消除锯齿

		String strText = "无痛";
		float textSize = paint.getTextSize();
		canvas.drawText(strText, 80,100, // add offset
																	// to y
																	// position
				paint);

		String strText2 = "最痛";
		textSize = paint.getTextSize();
		canvas.drawText(strText2, 100,100, // add offset to y position
				paint);

		String strText3 = "请按您的疼痛程度在标尺上垂直划线";
		textSize = paint.getTextSize();
		canvas.drawText(strText3, 120,100, // add offset to y position
				paint);

		paint.setStyle(Style.STROKE);// 设置非填充
		paint.setStrokeWidth(5);// 笔宽5像素
		paint.setColor(Color.BLACK);// 设置为红笔
		paint.setAntiAlias(true);// 锯齿不显示

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_MOVE) {// 如果拖动
			ReDraw();
			mov_end_x = (int) event.getX();
			mov_end_y = (int) event.getY();
			canvas.drawLine(mov_start_x, mov_start_y, mov_end_x, mov_end_y,
					paint);// 画线

			 invalidate();
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {// 如果点击
			mov_start_x = (int) event.getX();
			mov_start_y = (int) event.getY();
			// canvas.drawPoint(mov_x, mov_y, paint);// 画点
			// invalidate();
		}

		return true;
	}
}
