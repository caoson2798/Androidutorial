package com.example.daiphongpc.hoc_viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewFlipper vf;
    Animation in,on;
    ArrayList<String> array_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
    }

    private void addControl() {
        vf=findViewById(R.id.vf);

        array_link=new ArrayList<>();
        array_link.add("http://ttol.vietnamnetjsc.vn//2015/12/22/08/26/10-nu-sinh-xinh-dep-cua-hoc-vien-bao-chi-tuyen-truyen_1.jpg");
        array_link.add("https://znews-photo.zadn.vn/w1024/Uploaded/kcwvouvs/2017_09_15/20106368_863767830449352_6829951550605727269_n.jpg");
        array_link.add("http://genknews.genkcdn.vn/thumb_w/600/2017/photo-0-1512618939871.jpg");
        array_link.add("http://mediaold.tiin.vn:8080/media_old_2016///archive/images/2017/12/06/102945_kieu-trinh-xiu-chup-anh-cuc-hoa-mi-7.jpg");
        array_link.add("http://i.tinvn.info/m/2017/03/nu-sinh-xay-dung-blogtamsu-1_01.jpg");
        array_link.add("http://a9.vietbao.vn/images/vn999/190/2017/09/20170907-224-y-bao-sao-c-225-c-anh-ch-224-ng-muon-39-trong-c-226-y-si-39-o-dai-hoc-noi-vu-1.jpg");
        array_link.add("https://image.vtcns.com/files/bui.huong/2018/01/18/tien_1-0800550.jpg");
        for (int i=0;i<array_link.size();i++){
         //   ImageView img=new ImageView(MainActivity.this);
            ImageView img=new ImageView(MainActivity.this);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(MainActivity.this).load(array_link.get(i)).into(img);
            vf.addView(img);

        }

        vf.setFlipInterval(3000);
        vf.setAutoStart(true);
        in=AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_in);
        on=AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_out);
        vf.setInAnimation(in);
        vf.setOutAnimation(on);




    }
}
