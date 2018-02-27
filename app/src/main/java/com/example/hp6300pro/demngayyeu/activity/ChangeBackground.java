package com.example.hp6300pro.demngayyeu.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.adapter.CustomPagerAdapter;
import com.example.hp6300pro.demngayyeu.ads.MyBaseActivityWithAds;
import com.example.hp6300pro.demngayyeu.utils.Constants;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;

import static com.example.hp6300pro.demngayyeu.activity.MainActivity.CHUP_ANH_STRING_FOR_CHANGE;

public class ChangeBackground extends MyBaseActivityWithAds implements View.OnClickListener {

    public static final int IMAGE_GALLERY_REQUEST = 10;
    private Button mBtnChangeBackground;
    private ImageView mImgCamera, mImgGallery;
    private ViewPager mViewPager;
    private LinearLayout mSlide;

    private CustomPagerAdapter mPageAdapter;
    private Intent GalIntent;
    private Integer image;
    private Uri uri;
    private boolean mCheckPickImage = false;
    private String mImgSelected;

    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isHienThiQcBanner = false;

        setContentView(R.layout.activity_change_background);
        initView();

        mPageAdapter = new CustomPagerAdapter(this);
        mViewPager.setAdapter(mPageAdapter);
        dotscount = mPageAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);
            mSlide.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));


        mCheckPickImage = true;
        MainActivity.PUT_TYPE = MainActivity.PUT_INTEGER;
        mImgSelected = mPageAdapter.getImageSelected(0);

        ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                mCheckPickImage = true;
                MainActivity.PUT_TYPE = MainActivity.PUT_INTEGER;
                mImgSelected = mPageAdapter.getImageSelected(mViewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        mViewPager.setOnPageChangeListener(pageChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        mBtnChangeBackground = (Button) findViewById(R.id.btn_change_background);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mImgCamera = (ImageView) findViewById(R.id.img_camera_change_background);
        mImgGallery = (ImageView) findViewById(R.id.img_gallery_change_background);
        mSlide = (LinearLayout) findViewById(R.id.linear_dots);

        mBtnChangeBackground.setOnClickListener(this);
        mImgCamera.setOnClickListener(this);
        mImgGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_background:
//                if(mCheckPickImage){
//                    Intent returnIntent = new Intent();
//                    if (MainActivity.PUT_TYPE.equals(MainActivity.PUT_INTEGER)) {
//                        returnIntent.putExtra(MainActivity.RESULT_FROM_CHANGBG_STRING, mImgSelected);
//                    } else if (MainActivity.PUT_TYPE.equals(MainActivity.PUT_URI)) {
//                        returnIntent.putExtra(MainActivity.RESULT_FROM_CHANGBG_STRING, uri.toString());
//                    }
//                    setResult(MainActivity.RESULT_FROM_CHANGBG, returnIntent);
//                    finish();
//                }else{
//                    Intent returnIntent = new Intent();
//                    if(MainActivity.PUT_TYPE.equals(MainActivity.PUT_INTEGER)){
//                        returnIntent.putExtra(MainActivity.RESULT_FROM_CHANGBG_STRING, mPageAdapter.getImageSelected(0));
//                    }
//                    setResult(MainActivity.RESULT_FROM_CHANGBG, returnIntent);
//                    finish();
//                }

                Intent returnIntent = new Intent();
                if (MainActivity.PUT_TYPE.equals(MainActivity.PUT_INTEGER)) {
                    returnIntent.putExtra(MainActivity.RESULT_FROM_CHANGBG_STRING, mImgSelected);
                } else if (MainActivity.PUT_TYPE.equals(MainActivity.PUT_URI)) {
                    returnIntent.putExtra(MainActivity.RESULT_FROM_CHANGBG_STRING, uri.toString());
                }
                setResult(MainActivity.RESULT_FROM_CHANGBG, returnIntent);
                finish();

                break;
            case R.id.img_camera_change_background:
                selectImageAndCrop();
                break;
            case R.id.img_gallery_change_background:
                selectImageAndCrop();
                break;
        }
    }

    private void selectImageAndCrop() {
        MainActivity.PUT_TYPE = MainActivity.PUT_URI;
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle("Crop Image")
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .setCropMenuCropButtonTitle("Done")
                .setRequestedSize(Constants.CROP_IMAGE_REQUEST_SIZE, Constants.CROP_IMAGE_REQUEST_SIZE)
                .setFixAspectRatio(true)
                .setAspectRatio(Constants.CROP_RATIO_X_PORTRAIT, Constants.CROP_RATIO_Y_PORTRAIT)
                .start(ChangeBackground.this);
    }

    public void onImageGalleryClicked(View v) {
        mCheckPickImage = true;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        uri = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(uri, "image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    private void GalleryOpen() {
        GalIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(GalIntent, "Select Image from Gallery"), MainActivity.GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == MainActivity.GALLERY_REQUEST) {
                if (data != null) {
                    uri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                setBackgroundForMain();

            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    uri = result.getUri();

                    setBackgroundForMain();
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    //Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
                } else if (requestCode == RESULT_CANCELED) {
                    Log.d("abc", "cancel");
                }
            }


        } else if (resultCode == MainActivity.CHUP_ANH_FOR_CHANGE) {
            uri = Uri.parse(data.getStringExtra(CHUP_ANH_STRING_FOR_CHANGE));
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                Drawable d = new BitmapDrawable(getResources(), bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setBackgroundForMain();
        }
    }

    private void setBackgroundForMain() {
        Intent returnIntent = new Intent();
        if (MainActivity.PUT_TYPE.equals(MainActivity.PUT_INTEGER)) {
            returnIntent.putExtra(MainActivity.RESULT_FROM_CHANGBG_STRING, mImgSelected);
        } else if (MainActivity.PUT_TYPE.equals(MainActivity.PUT_URI)) {
            returnIntent.putExtra(MainActivity.RESULT_FROM_CHANGBG_STRING, uri.toString());
        }
        setResult(MainActivity.RESULT_FROM_CHANGBG, returnIntent);
        finish();
    }

}
