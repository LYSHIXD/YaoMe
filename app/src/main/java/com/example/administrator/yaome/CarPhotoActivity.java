package com.example.administrator.yaome;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.yaome.util.FileUitlity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarPhotoActivity extends AppCompatActivity implements View.OnClickListener{
    //返回按钮
    private ImageView back_iv;
    //保存按钮
    private Button save_btn;
    //车辆外观
    private ImageView car_appearance_iv;
    //车辆内饰
    private ImageView car_interior_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_photo);

        back_iv=(ImageView)this.findViewById(R.id.back_iv);
        save_btn=(Button) this.findViewById(R.id.save_btn);
        car_appearance_iv=(ImageView)this.findViewById(R.id.car_appearance_iv);
        car_interior_iv=(ImageView)this.findViewById(R.id.car_interior_iv);

        back_iv.setOnClickListener(this);
        save_btn.setOnClickListener(this);
        car_appearance_iv.setOnClickListener(this);
        car_interior_iv.setOnClickListener(this);



    }
    private PopupWindow popupWindow;

    private Button camear_btn,album_btn;

    //拍照或者相册选取弹窗
    private void initpopupwindowView(){

        View view=getLayoutInflater().inflate(R.layout.popupwindow_check_photo,null);
        popupWindow=new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.WRAP_CONTENT,true);

        //先设置背景颜色，后设置显示位置，否则外部无法点击
        ColorDrawable colorDrawable=new ColorDrawable(getResources().getColor(R.color.gray));
        popupWindow.setBackgroundDrawable(colorDrawable);

        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_car_photo,null), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        backgroundAlpha(0.5f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });

        camear_btn=(Button)view.findViewById(R.id.camera_btn);
        album_btn=(Button)view.findViewById(R.id.album_btn);

        //拍照按钮
        camear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromCamera();
                popupWindow.dismiss();
            }
        });
        //相册选取
        album_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromAlbum();
                popupWindow.dismiss();
            }
        });


    }

    public static final int REQUEST_CODE_PICK_IMAGE=0;                            //相册选取标记

    public static final int REQUEST_CODE_CAPTURE_CAMERA=1;                        //相机拍摄标记

    public static final int CONFIRM_IMAGE_USE=2;                                  //确定使用图片



    //打开相册
    private void getImageFromAlbum(){
        Intent intent=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_CODE_PICK_IMAGE);
    }
    //打开相机
    private void getImageFromCamera(){
        String state= Environment.getExternalStorageState();                       //获取外部储存的环境声明
        if(state.equals(Environment.MEDIA_MOUNTED)){                               //如果媒体环境已安装
            Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
            File file= FileUitlity.getInstance(this).makeDir("publishPhoto");
            imgFilePath=file.getPath()+File.separator+System.currentTimeMillis()+".jpg";

            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(imgFilePath)));    //设置拍照后照片的存放地址，下面用
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);

            startActivityForResult(intent,REQUEST_CODE_CAPTURE_CAMERA);
        }
        else{
            Toast.makeText(this, "请插入SD卡", Toast.LENGTH_SHORT).show();
        }
    }



    private String imgFilePath="";

    /**
     *
     * @param requestCode 请求的id
     * @param resultCode 返回的id
     * @param data 返回的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){

            case REQUEST_CODE_PICK_IMAGE:                                               //相册选取
                Uri imgPathUri=data.getData();                                          //获取相册传来的uri
                Bitmap bitmap=null;

                try {
                    bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imgPathUri);
                    saveFile(bitmap);                                                   //将bitmap保存在了路径为imgFilePath的图片

                    Intent intent=new Intent(this,CheckPhotoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("filePath",imgFilePath);                           //将地址发个选择界面，以便旋转、确定、取消照片
                    intent.putExtras(bundle);
                    startActivityForResult(intent,CONFIRM_IMAGE_USE);                   //返回结果在下面处理


                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case REQUEST_CODE_CAPTURE_CAMERA://拍照

                if(new File(imgFilePath).canRead()){                                    //如果照片的地址可以读取....打开相机时设置了文件路径
                    Intent intent=new Intent(this,CheckPhotoActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("filePath",imgFilePath);                           //将地址发个选择界面，以便旋转、确定、取消照片
                    intent.putExtras(bundle);
                    startActivityForResult(intent,CONFIRM_IMAGE_USE);                   //返回结果在下面处理
                }
                break;

            case CONFIRM_IMAGE_USE:                                                     //确定使用图片后返回图片地址，进行设置imageView

                String path=data.getStringExtra("path");                                //获取查看和点击确定后保存的图片地址，设置imageview
                car_appearance_iv.setImageBitmap(BitmapFactory.decodeFile(path));
                break;
        }

    }



    public void saveFile(Bitmap map ){
        FileOutputStream fos=null;
        File parent= FileUitlity.getInstance(this).makeDir("my_img");
        imgFilePath=parent.getPath()+File.separator+System.currentTimeMillis()+".jpg";
        try {
            File file=new File(imgFilePath) ;
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }
            fos=new FileOutputStream(file);
            map.compress(Bitmap.CompressFormat.JPEG,100,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if(map!=null){
            //bitmap.recycle();
        }


    }                    //使用bitmap转换为图片，产生一个图片路径

    //背景透明度
    private void backgroundAlpha(float alpha){

        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=alpha;
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.save_btn:

                break;
            case R.id.car_appearance_iv:
                initpopupwindowView();
                break;
            case R.id.car_interior_iv:

                break;


        }
    }
}
