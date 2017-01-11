package alura.com.br.jogodaforca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by natanael.ribeiro on 10/01/2017.
 */

public class PlanoCartesianoView extends View {

  private int displayMinorSide ;
  private int unity;

  public PlanoCartesianoView(Context context) {
    super(context);
  }

  public PlanoCartesianoView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void drawPlanoCartesiano(Canvas canvas) {
    Path path = new Path();
    int max = toPixel(10);
    for(int n=0; n<=10; n++){
      //Drawing vertical lines
      path.moveTo(toPixel(n), 1);
      path.lineTo(toPixel(n), max);
      //Drawing horizontal lines
      path.moveTo(1, toPixel(n));
      path.lineTo(max, toPixel(n));
    }

    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.BLACK);
    paint.setStrokeWidth(1);

    canvas.drawPath(path, paint);
  }

  public int toPixel(int times) {
    return times * getUnity();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if(getHeight() > getWidth())
      setDisplayMinorSide(getWidth());
    else
      setDisplayMinorSide(getHeight());

    setUnity(getDisplayMinorSide() / 10);

    drawPlanoCartesiano(canvas);
  }

  public int getDisplayMinorSide() {
    return displayMinorSide;
  }

  public void setDisplayMinorSide(int displayMinorSide) {
    this.displayMinorSide = displayMinorSide;
  }

  public int getUnity() {
    return unity;
  }

  public void setUnity(int unity) {
    this.unity = unity;
  }
}
