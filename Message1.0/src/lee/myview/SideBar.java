package lee.myview;

import lee.message.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SideBar extends View {

	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

	private TextView showView;

	private Paint paint = new Paint();

	private int choose = -1;

	public static String[] headers = { "#", "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", };

	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SideBar(Context context) {
		super(context);
	}

	public TextView getShowView() {
		return showView;
	}

	public void setShowView(TextView showView) {
		this.showView = showView;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int height = getHeight();
		int width = getWidth();
		int eachHeight = height / headers.length;

		for (int i = 0; i < headers.length; i++) {
			paint.setColor(Color.rgb(0, 0, 0));
			paint.setAntiAlias(true);
			paint.setTextSize(20);
			paint.setTypeface(Typeface.DEFAULT_BOLD);

			if (i == choose) {
				paint.setColor(Color.parseColor("#3399ff"));
				paint.setFakeBoldText(true);
			}

			// x = 中间 - 字符串宽度/2.
			float x = width / 2 - paint.measureText(headers[i]) / 2;
			float y = eachHeight * i + eachHeight;

			canvas.drawText(headers[i], x, y, paint);
			// 还原画笔
			paint.reset();
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		float y = event.getY();// 得到Y坐标
		int action = event.getAction();
		int letterIndex = (int) (y / getHeight() * headers.length);
		int preChoose = choose;
		OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;

		switch (action) {
		case MotionEvent.ACTION_UP:
			setBackgroundDrawable(new ColorDrawable(0x00000000));
			choose = -1;// 释放表示没有选中任何记录
			invalidate();
			if (showView != null) {
				showView.setVisibility(View.INVISIBLE);
			}
			break;
		default:
			setBackgroundResource(R.drawable.sidebar);
			if (preChoose != letterIndex) {
				if (0 <= letterIndex && letterIndex < headers.length) {
					if (listener != null) {
						listener.onTouchingLetterChanged(headers[letterIndex]);
					}
					if (showView != null) {
						showView.setText(headers[letterIndex]);
						showView.setVisibility(View.VISIBLE);
						invalidate();
						choose = letterIndex;
					}
				}
			}
			break;
		}

		return true;
	}

	/**
	 * 公有方法
	 * 
	 * @param OnTouchingLetterChangedListener
	 * 
	 * */
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	/**
	 * 接口
	 * 
	 * @author lxw
	 * */
	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String letter);
	}
}
