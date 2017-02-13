package com.example.administrator.yaome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.example.administrator.yaome.util.FileUitlity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.administrator.yaome.CarPhotoActivity.CONFIRM_IMAGE_USE;

public class CheckPhotoActivity extends AppCompatActivity implements View.OnClickListener{
    //主图片
    private ImageView img;
    //返回按钮、确定按钮、旋转按钮
    private ImageView imgBack,imgOk,imgRotate;

    private Bitmap bitmap;
    //传回来的文件路径
    private String filePath="";
    //传回去的文件路径
    //保存图片文件的路径，发回activity，用于设置imageview
    private String imgPath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_photo);
        img=(ImageView)super.findViewById(R.id.img);
        imgBack=(ImageView)super.findViewById(R.id.imgBack);
        imgOk=(ImageView)super.findViewById(R.id.imgOk);
        imgRotate=(ImageView)super.findViewById(R.id.imgRotate);

        imgBack.setOnClickListener(this);
        imgRotate.setOnClickListener(this);
        imgOk.setOnClickListener(this);
        initView();
    }

    private void initView(){
        Intent intent=getIntent();                           //获取传来的intent
        Bundle bundle=intent.getExtras();
        filePath=bundle.getString("filePath");               //获取CarPhotoActivity、CompanyPhotoActivity、House..、IdCard...传来的图片地址
        initBitmap(filePath);                                //图片压缩
        img.setImageBitmap(bitmap);                          //设置图片
    }
    //压缩图片像素
    private void initBitmap(String filePath) {
        InputStream isBm = null;
        try {
            isBm = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();

        // 通过创建图片的方式，取得options的内容（这里就是利用了java的地址传递来赋值）
        BitmapFactory.decodeStream(isBm, null, options);
        // 这个参数代表，不为bitmap分配内存空间，只记录一些该图片的信息（例如图片大小），说白了就是为了内存优化
        options.inJustDecodeBounds = true;
        try {
            isBm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 生成压缩的图片
        int i = 1;
        int a=2;
        int width=options.outWidth/a;
        int height=options.outHeight/a;
        while (true) {
            // 这一步是根据要设置的大小，使宽和高都能满足
            if (width>720&&height>960){
                // 重新取得流，注意：这里一定要再次加载，不能二次使用之前的流！
                try {
                    isBm = new FileInputStream(filePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // 这个参数表示 新生成的图片为原始图片的几分之一。
                a=(int) Math.pow(2, i);
                options.inSampleSize=a;
                options.inPurgeable=true;
                options.inInputShareable=true;

                width=options.outWidth/a;
                height=options.outHeight/a;

                i += 1;
            }else {
                try {
                    isBm = new FileInputStream(filePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        options.inJustDecodeBounds = false;
        // 这里之前设置为了true，所以要改为false，否则就创建不出图片
        bitmap = BitmapFactory.decodeStream(isBm, null, options);

    }


    public void saveFile(Bitmap bitmap){
        FileOutputStream fos=null;
        File parent= FileUitlity.getInstance(this).makeDir("my_img");
        imgPath=parent.getPath()+ File.separator+ System.currentTimeMillis()+".jpg";
        try {
            File file=new File(imgPath) ;
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }
            fos=new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if(bitmap!=null){
            //bitmap.recycle();
        }
        File file1=new File(filePath);
        if(file1.exists()){
            if(file1.isFile())
                file1.delete();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgOk:
                saveFile(bitmap);

                Intent intent=new Intent();
                intent.putExtra("path",imgPath);             //传回保存的图片的文件地址
                setResult(CONFIRM_IMAGE_USE, intent);        //标记为CONFIRM_IMAGE_USE，activity中在CONFIRM_IMAGE_USE项中处理

                finish();
                break;
            case R.id.imgRotate:
                Matrix matrix=new Matrix();                  //图片旋转，每次顺时针90度
                matrix.setRotate(90f,0,0);
                Bitmap rotate= Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                img.setImageBitmap(rotate);
                bitmap=rotate;
                break;
        }
    }
}
