package alura.com.br.jogodaforca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by natanael.ribeiro on 10/01/2017.
 */

public class ForcaView extends PlanoCartesianoView {
  private Path pathForca;
  private Paint paintForca;

  private enum BodyMember { ARM, LEG }
  private enum BodyMemberSide { LEFT, RIGHT }

  public ForcaView(Context context) {
    super(context);
  }
  public ForcaView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setPathForca(new Path());
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    plotForcaSkeleton();
    plotHead();
    plotBody();
    plotBodyMember(BodyMember.ARM, BodyMemberSide.RIGHT);
    plotBodyMember(BodyMember.ARM, BodyMemberSide.LEFT);
    plotBodyMember(BodyMember.LEG, BodyMemberSide.LEFT);
    plotBodyMember(BodyMember.LEG, BodyMemberSide.RIGHT);

    canvas.drawPath(getPathForca(), getPaintForca());
  }

  private void plotForcaSkeleton() {
    getPathForca().moveTo(toPixel(1), toPixel(10));
    getPathForca().lineTo(toPixel(3), toPixel(10));

    getPathForca().moveTo(toPixel(2), toPixel(10));
    getPathForca().lineTo(toPixel(2), toPixel(1));

    getPathForca().rLineTo(toPixel(5), 0);
    getPathForca().rLineTo(0, toPixel(1));
  }

  private void plotHead() {
    getPathForca().addCircle(toPixel(7), toPixel(3), toPixel(1), Path.Direction.CW);
  }

  private void plotBody() {
    getPathForca().moveTo(toPixel(7), toPixel(4));
    getPathForca().lineTo(toPixel(7), toPixel(7));
  }

  private void plotBodyMember(BodyMember bodyMember, BodyMemberSide bodyMemberSide){
    final int bodyPosition = 7;
    final int armHeight = 5;
    final int legHeight = 7;
    int finalHeight;

    if(bodyMember == BodyMember.ARM) {
      getPathForca().moveTo(toPixel(bodyPosition), toPixel(armHeight));
      finalHeight = armHeight + 1;
    } else {
      getPathForca().moveTo(toPixel(bodyPosition), toPixel(legHeight));
      finalHeight = legHeight + 1;
    }

    if(bodyMemberSide == BodyMemberSide.RIGHT)
      getPathForca().lineTo(toPixel(bodyPosition + 1), toPixel(finalHeight));
    else
      getPathForca().lineTo(toPixel(bodyPosition - 1), toPixel(finalHeight));
  }

  public Path getPathForca() {
    return pathForca;
  }
  public void setPathForca(Path pathForca) {
    this.pathForca = pathForca;
  }

  public Paint getPaintForca() {
    paintForca = new Paint();
    paintForca.setColor(Color.BLACK);
    paintForca.setStyle(Paint.Style.STROKE);
    paintForca.setStrokeWidth(8);
    return paintForca;
  }
}
