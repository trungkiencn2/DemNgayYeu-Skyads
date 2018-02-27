package com.example.hp6300pro.demngayyeu.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class DecodeIcon {

	public static Bitmap decodeScaleBitmapFromPath(String path) {
		return decodeScaleBitmapFromPath(path, 500, 500);

	}

	public static Bitmap decodeScaleBitmapFromPath(String path, int reqHeight,
                                                   int reqWidth) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		try {
			BitmapFactory.decodeFile(path, options);
		} catch (OutOfMemoryError e) {
			return null;
		}

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		options.inSampleSize = inSampleSize;
		options.inJustDecodeBounds = false;

		try {
			return BitmapFactory.decodeFile(path, options);
		} catch (OutOfMemoryError e) {
			return null;
		}

	}

	public static Bitmap decodeScaleBitmapFromResource(Resources res,
                                                       int resId, int reqHeight, int reqWidth) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		try {
			BitmapFactory.decodeResource(res, resId, options);
		} catch (OutOfMemoryError e) {
			return null;
		}

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		options.inSampleSize = inSampleSize;
		options.inJustDecodeBounds = false;

		try {
			return BitmapFactory.decodeResource(res, resId, options);
		} catch (OutOfMemoryError e) {
			return null;
		}
	}

	public static Drawable getAssetImage(Context context, String filename,
                                         String dir) {
		AssetManager assets = context.getAssets();
		InputStream buffer;
		try {
			buffer = assets.open(dir + "/" + filename);
			Bitmap bitmap = BitmapFactory.decodeStream(buffer);
			return new BitmapDrawable(context.getResources(), bitmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap getAssetImageBitmap(Context context, String filename,
                                             String dir) {
		AssetManager assets = context.getAssets();
		InputStream buffer;
		try {
			buffer = assets.open(dir + "/" + filename);
			Bitmap bitmap = BitmapFactory.decodeStream(buffer);
			return bitmap;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// Glide library
	/*public static void addAssetBitmapToImageView(Context context,
			String filename, String dir, ImageView iv) {
		String path = "file:///android_asset/" + dir + "/" + filename;
		Glide.with(context).load(Uri.parse(path)).into(iv);
	}*/

//	public static void addAssetBitmapNoCacheToImageView(Context context,
//			String filename, String dir, ImageView iv) {
//		String path = "file:///android_asset/" + dir + "/" + filename;
//		Glide.with(context).load(path).into(iv);
//	}

	public static Drawable getAssetImage(Context context, String filename,
                                         String dir1, String dir2) {
		AssetManager assets = context.getAssets();
		InputStream buffer;
		try {
			buffer = assets.open(dir1 + "/" + dir2 + "/" + filename);
			Bitmap bitmap = BitmapFactory.decodeStream(buffer);
			return new BitmapDrawable(context.getResources(), bitmap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap scaleBitmap(Bitmap b, int maxWidth, int maxHeight) {
		if (b != null) {
			int width = b.getWidth();
			int height = b.getHeight();

			if (width >= maxWidth || height >= maxHeight) {
				while (true) {
					if (width <= maxWidth || height <= maxHeight) {
						break;
					}
					width /= 2;
					height /= 2;
				}
				b = Bitmap.createScaledBitmap(b, width, height, false);
			}

			return b;
		}
		return null;
	}
}
