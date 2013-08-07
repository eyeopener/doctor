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

	private int mov_start_x = 0;// �����������
	private int mov_start_y = 0;
	private int mov_end_x = 0;// �����������
	private int mov_end_y = 0;

	private Bitmap bitmap;// λͼ
	private Canvas canvas;// ����

	private int m_screenWith = 200;
	private int m_PaitMarginLeft = 100;
	private int m_PaitMarginTop = 100;
	private int m_PaitWidth = 100;
	private int m_PaitHeigth = 50;

	private int m_LableMarginTop = 20;
	private int m_LableHeigth = 25;

	int COMPONENT_WIDTH; // �ÿؼ����
	int COMPONENT_HEIGHT; // �ÿؼ��߶�
	boolean initflag = false; // �Ƿ�Ҫ��ȡ�ؼ��ĸ߶ȺͿ�ȱ�־
	static Bitmap[] bma; // ��Ҫ���ŵ�ͼƬ������
	Paint paint; // ����
	int[] drawablesId; // ͼƬID����
	int currIndex = 0; // ͼƬID�����±꣬���ݴ˱�����ͼƬ
	boolean workFlag = true; // ����ͼƬ�̱߳�־λ

	public DrawLineView(Context father, AttributeSet as) { // ������
		super(father, as);

		paint = new Paint(Paint.DITHER_FLAG);// ����һ������

		bitmap = Bitmap.createBitmap(m_PaitWidth, m_PaitHeigth,
				Bitmap.Config.ARGB_8888); // ����λͼ�Ŀ��
		canvas = new Canvas();
		canvas.setBitmap(bitmap);

	}

	public void initBitmaps() { // ��ʼ��ͼƬ����
		Resources res = this.getResources(); // ��ȡResources����
		for (int i = 0; i < drawablesId.length; i++) {
			bma[i] = BitmapFactory.decodeResource(res, drawablesId[i]);
		}
	}

	public void onDraw(Canvas canvas) { // ���ƺ���
		if (!initflag) { // ��һ�λ���ʱ��Ҫ��ȡ��Ⱥ͸߶�
			COMPONENT_WIDTH = this.getWidth(); // ��ȡview�Ŀ��
			COMPONENT_HEIGHT = this.getHeight(); // ��ȡview�ĸ߶�
			initflag = true;
		}
		// ������ʹ��

		//
		paint.setStyle(Style.FILL);// �������
		paint.setColor(Color.GRAY);// ����Ϊ��ɫ
		LinearGradient linearGradient = new LinearGradient(50, 50, 200, 70,
				new int[] { Color.RED, Color.RED, Color.RED, Color.RED,
						Color.YELLOW, Color.GREEN, Color.BLUE }, null,
				Shader.TileMode.REPEAT);
		paint.setShader(linearGradient);
		Rect r = new Rect(50, 50, 200, 70);
		canvas.drawRect(r, paint);
		paint.setShader(null);

		// canvas.drawARGB(255, 200, 128, 128); // ���ñ���ɫ
		// canvas.drawBitmap(bma[currIndex], startX, startY, paint); // ����ͼƬ
	}

	public void ReDraw() {

		
		// ������ʹ��
		paint.setStyle(Style.FILL);// �������
		paint.setColor(Color.GRAY);// ����Ϊ��ɫ
		LinearGradient linearGradient = new LinearGradient(50, 50, 200, 70,
				 new int[] { Color.RED,
						Color.RED, Color.RED, Color.RED, Color.YELLOW,
						Color.GREEN, Color.BLUE }, null, Shader.TileMode.REPEAT);
		paint.setShader(linearGradient);
		Rect r = new Rect(50, 50, 200, 70);
		canvas.drawRect(r, paint);
		paint.setShader(null);

		// draw text
		// paint.setStyle(Style.STROKE);// ���÷����
		paint.setStrokeWidth((float) 1);// �ʿ�5����
		paint.setFakeBoldText(false);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		paint.setAntiAlias(true); // �������
		// paint.setFlags(Paint.ANTI_ALIAS_FLAG); // �������

		String strText = "��ʹ";
		float textSize = paint.getTextSize();
		canvas.drawText(strText, 80,100, // add offset
																	// to y
																	// position
				paint);

		String strText2 = "��ʹ";
		textSize = paint.getTextSize();
		canvas.drawText(strText2, 100,100, // add offset to y position
				paint);

		String strText3 = "�밴������ʹ�̶��ڱ���ϴ�ֱ����";
		textSize = paint.getTextSize();
		canvas.drawText(strText3, 120,100, // add offset to y position
				paint);

		paint.setStyle(Style.STROKE);// ���÷����
		paint.setStrokeWidth(5);// �ʿ�5����
		paint.setColor(Color.BLACK);// ����Ϊ���
		paint.setAntiAlias(true);// ��ݲ���ʾ

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_MOVE) {// ����϶�
			ReDraw();
			mov_end_x = (int) event.getX();
			mov_end_y = (int) event.getY();
			canvas.drawLine(mov_start_x, mov_start_y, mov_end_x, mov_end_y,
					paint);// ����

			 invalidate();
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN) {// ������
			mov_start_x = (int) event.getX();
			mov_start_y = (int) event.getY();
			// canvas.drawPoint(mov_x, mov_y, paint);// ����
			// invalidate();
		}

		return true;
	}
}
