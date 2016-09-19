package com.i7676.qyclient.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.i7676.qyclient.R;
import com.i7676.qyclient.util.AppUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AutoLoadImageView extends ImageView {

  private ImageDownloader imageDownloader;
  private String authorInfo;

  private SimpleDiskCache diskCache = new SimpleDiskCache(getContext().getCacheDir());

  public AutoLoadImageView(Context context) {
    super(context);
  }

  public AutoLoadImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public AutoLoadImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (authorInfo != null) {
      this.drawAuthorInfo(canvas);
    }
  }

  public void setImageUrlAndAuthorInfo(String imgUrl, String authorInfo) {
    this.setAuthorInfo(authorInfo);
    this.setImageUrl(imgUrl);
  }

  private void drawAuthorInfo(Canvas canvas) {
    int imgWidth = getWidth();
    int imgHeight = getHeight();
    final Paint textPaint = getTextPaint();
    float textWidth = textPaint.measureText(authorInfo);
    canvas.drawText(authorInfo, 0, authorInfo.length(), (imgWidth / 2) - (textWidth / 2),
        (imgHeight / 20) * 19, textPaint);
  }

  private Paint getTextPaint() {
    Paint paint = new Paint();
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    paint.setTextSize(48);
    paint.setAntiAlias(true);
    return paint;
  }

  private void setAuthorInfo(String authorInfo) {
    if (authorInfo != null && authorInfo.trim().length() > 0) {
      this.authorInfo = "#" + authorInfo;
    }
  }

  private void setImageUrl(String imgURL) {
    if (imgURL != null) {
      Bitmap cacheBitmap = diskCache.get(imgURL);
      if (cacheBitmap == null) {
        new Thread() {
          @Override public void run() {
            super.run();
            AutoLoadImageView.this.downloadImage(imgURL);
          }
        }.start();
      } else {
        this.loadImage(cacheBitmap);
      }
    } else {
      this.loadPlaceholderImage();
    }
  }

  private void loadPlaceholderImage() {
    ((Activity) getContext()).runOnUiThread(() -> {
      AutoLoadImageView.this.setImageResource(R.mipmap.placeholder);
    });
  }

  private void loadImage(Bitmap bitmap) {
    ((Activity) getContext()).runOnUiThread(() -> {
      AutoLoadImageView.this.setImageBitmap(bitmap);
      AutoLoadImageView.this.setScaleType(ScaleType.FIT_XY);
    });
  }

  private void downloadImage(String imgURL) {
    if (AppUtil.isThereInternetConnection(getContext())) {
      if (imageDownloader == null) {
        imageDownloader = new ImageDownloader();
      }
      imageDownloader.download(imgURL, new ImageDownloader.Callback() {
        @Override public void onSuccess(Bitmap bitmap) {
          diskCache.put(bitmap, imgURL);
          AutoLoadImageView.this.loadImage(bitmap);
        }

        @Override public void onFailed(Exception e) {
          Logger.e(">>> Image load failed. \n" + e.getMessage());
          AutoLoadImageView.this.loadPlaceholderImage();
        }
      });
    } else {
      Logger.i(">>> No internet connection.");
      AutoLoadImageView.this.loadPlaceholderImage();
    }
  }

  /**
   * Image downloader
   */
  private static class ImageDownloader {
    interface Callback {
      void onSuccess(Bitmap bitmap);

      void onFailed(Exception e);
    }

    ImageDownloader() {
    }

    public void download(String imgURL, Callback callback) {
      try {
        URL url = new URL(imgURL);
        URLConnection conn = url.openConnection();
        conn.connect();
        Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        if (callback != null) {
          callback.onSuccess(bitmap);
        }
      } catch (MalformedURLException e) {
        reportError(callback, e);
      } catch (IOException e1) {
        reportError(callback, e1);
      }
    }

    private void reportError(Callback callback, Exception e) {
      if (callback != null) {
        callback.onFailed(e);
      }
    }
  }

  /**
   * A simple implement cache system
   */
  private static class SimpleDiskCache {

    private static final String BASE_IMAGE_NAME_CACHED = "img_";

    private File cacheDir;

    public SimpleDiskCache(File cacheDir) {
      this.cacheDir = cacheDir;
    }

    synchronized Bitmap get(String fileName) {
      File file = this.buildFileFromFileName(fileName);
      Bitmap bitmap = null;
      if (file.exists()) {
        Logger.i(">>> Read from cache:" + file.getName());
        bitmap = BitmapFactory.decodeFile(file.getPath());
        Logger.i(">>> Read finished, bitmap :" + (bitmap == null ? "" : bitmap.toString()));
      }
      return bitmap;
    }

    synchronized void put(Bitmap bitmap, String fileName) {
      File file = this.buildFileFromFileName(fileName);
      if (!file.exists()) {
        try {
          FileOutputStream fOut = new FileOutputStream(file);
          bitmap.compress(Bitmap.CompressFormat.PNG, 90, fOut);
          fOut.flush();
          fOut.close();
        } catch (FileNotFoundException e) {
          Logger.e(">>> Cache file not found.\n" + e.getMessage());
        } catch (IOException e1) {
          Logger.e(">>> Image cant output in file.\n" + e1.getMessage());
        }
      }
      Logger.i(">>> Cache file:" + file.getName() + "success.");
    }

    private File buildFileFromFileName(String fileName) {
      String fullPath = cacheDir.getPath() + File.separator + getFileNameFromUrl(fileName);
      return new File(fullPath);
    }

    private String getFileNameFromUrl(String imageUrl) {
      //we could generate an unique MD5/SHA-1 here
      String hash = String.valueOf(imageUrl.hashCode());
      if (hash.startsWith("-")) {
        hash = hash.substring(1);
      }
      return BASE_IMAGE_NAME_CACHED + hash;
    }
  }
}