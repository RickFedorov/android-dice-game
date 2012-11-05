package com.ridgelineapps.resdicegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;

public class UIDiceRoll extends UIEntity {
   int x;
   int y;
   int width;
   int height;
   Paint paint;
   Paint textPaint;
   
   public UIDiceRoll(Game game, int x, int y, int width, int height) {
      super(game, Type.roll, 0, x, y, x + width, y + height);
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      
      paint = new Paint();
      paint.setDither(true);
      paint.setFilterBitmap(true);
      paint.setAntiAlias(true);
      paint.setStyle(Style.FILL);
      paint.setARGB(255, 50, 80, 120);         
      
      textPaint = new Paint();
      textPaint.setFakeBoldText(true);
      textPaint.setAntiAlias(true);
      textPaint.setARGB(255, 200, 230, 230);
      textPaint.setTextSize(25);
      
      path = new Path();
      path.moveTo(x, y);
      path.lineTo(x + width, y);
      path.lineTo(x + width, y + height);
      path.lineTo(x, y + height);
      path.close();      
   }
   
   @Override
   public void draw(Canvas canvas) {
      String text;
      if(game.canRoll()) {
         text = "";
        if (game.rolls == 0) {
            text = game.getString(R.string.first_roll);
        }
        if (game.rolls == 1) {
            text = game.getString(R.string.second_roll);
        }
        if (game.rolls == 2) {
            text = game.getString(R.string.third_roll);
        }
      }
      else if(game.isGameDone()) {
          text = game.getString(R.string.play_again);
      }
      else {
         text = game.getString(R.string.done);
      }
      int textWidth = (int) textPaint.measureText(text);
//      if(!game.isGameDone()) {
          canvas.drawPath(path, paint);
          canvas.drawText(text, x + width / 2 - textWidth / 2, y + height - 15, textPaint);
//      }
//      else {
//          int offset = 80;
//          canvas.drawRect(x, y - offset, x + width, y + height - offset, paint);
//          canvas.drawText(text, x + width / 2 - textWidth / 2, y + height - 15 - offset, textPaint);
//      }
      super.draw(canvas);      
   }
}