package cn.itcast.recycleview;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    Button open;
    private DialogOrderTypeFragment mFragment2=new DialogOrderTypeFragment();
    String name;
    RelativeLayout r1;
    public static String judge_user;//用于接收数据库查询的返回数据
    private CommonDialog myDialog;
    private Button button;
//    private RecyclerView mRecyclerView;
//    private HomeAdapter mAdapter;

    private Banner banner;
    private GlideImageLoader glideImageLoader;
    private List<String> imagePath;
    private List<String> imageTitle;

    private OneFragment fg1;
    private TwoFragment fg2;
    private TextView firstText;
    private TextView secondText;
    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private FragmentManager fragmentManager;
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;
    int img;
    int f;
   String ziliao;
     String buy1;
   String name_receive1;
    private int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details);
        fragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();

        f = intent.getIntExtra("f",0);
        name = intent.getStringExtra("detail_name");
        ziliao = intent.getStringExtra("detail_introduce");
        buy1 = intent.getStringExtra("detail_buy");
         img =intent.getIntExtra("detail_iv",0);
       name_receive1 = intent.getStringExtra("name");//景点地区


        final TextView menpiao = findViewById(R.id.menpiao);
        menpiao.setText(buy1);

        TextView jieshouname = findViewById(R.id.jieshouname);
        jieshouname.setText(name);

        TextView jieshouintroduce = findViewById(R.id.jieshouintroduce);
        jieshouintroduce.setText(ziliao);

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDialog=new CommonDialog(DetailsActivity.this,R.style.MyDialog);
                myDialog.setTitle("TIP！");
                myDialog.setMessage("Tip：Are you sure you want to buy！");

                myDialog.setYesOnclickListener("Yes", new CommonDialog.onYesOnclickListener() {
                    @Override
                    public void onYesOnclick() {

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{

                                    judge_user = connect.reachtwo(1);
                                }catch (SQLException e){
                                    e.printStackTrace();
                                }

                            }
                        });
                        thread.start();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(judge_user.equals("1")){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        connect.create(name,ziliao,buy1);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                            Toast.makeText(getApplicationContext(),"Deal is done!",Toast.LENGTH_LONG).show();
                        }
                      if(judge_user.equals("0")){
                          Toast.makeText(getApplicationContext(),"Please login before purchasing tickets!",Toast.LENGTH_LONG).show();
                      }
                        myDialog.dismiss();
                    }
                });
                myDialog.setNoOnclickListener("Cancel", new CommonDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(getApplicationContext(),"Sorry! The deal is failed!",Toast.LENGTH_LONG).show();
                        myDialog.dismiss();
                    }
                });
               myDialog.show();
            }
        });
        open=(Button)findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment2.show(getFragmentManager(), "android");

            }
        });

        ImageView fanhui = findViewById(R.id.image);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f==0){
                    Intent intent =new Intent();
                    intent.setClass(DetailsActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(f==1){
                    Intent intent =new Intent();
                    intent.setClass(DetailsActivity.this,ZhujiemianActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        mFragment2.setOnDialogListener(new DialogOrderTypeFragment.OnDialogListener() {
            @Override
            public void onDialogClick(String person,String code) {

                if (code.equals("0"))
                {
                    openMap1();

                }else if(code.equals("1"))
                {
                    openMap2();
                }else
                {

                }

            }
        });

        r1 = findViewById(R.id.R1);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.putExtra("image",img);
                intent.putExtra("name1",name);
                intent.putExtra("buy1",buy1);
                intent.putExtra("troduce",ziliao);
                intent.putExtra("name",name_receive1);
                intent.setClass(DetailsActivity.this,JianshaoActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        mRecyclerView = findViewById(R.id.recycle);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mAdapter = new HomeAdapter();
//        mRecyclerView.setAdapter(mAdapter);

        initDate();
        initView();
        initView1();
        setChioceItem(a); // 初始化页面加载时显示第一个选项卡
    }
    private void initView1() {
        firstText = (TextView) findViewById(R.id.first_text);
        secondText = (TextView) findViewById(R.id.second_text);
        firstLayout = (RelativeLayout) findViewById(R.id.first_layout);
        secondLayout = (RelativeLayout) findViewById(R.id.second_layout);
        firstLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(0);
            }
        });
        secondLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(1);
            }
        });


    }
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
// firstImage.setImageResource(R.drawable.XXXX); 需要的话自行修改
                firstText.setTextColor(dark);
                firstLayout.setBackgroundColor(gray);
// 如果fg1为空，则创建一个并添加到界面上
                if (fg1 == null) {
                    fg1 = new OneFragment();
                    fragmentTransaction.add(R.id.content, fg1);
                } else {
// 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg1);
                }
                break;
            case 1:
                secondText.setTextColor(dark);
                secondLayout.setBackgroundColor(gray);
                if (fg2 == null) {
                    fg2 = new TwoFragment();
                    fragmentTransaction.add(R.id.content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    private void clearChioce() {
// firstImage.setImageResource(R.drawable.XXX);
        firstText.setTextColor(gray);
        firstLayout.setBackgroundColor(whirt);
// secondImage.setImageResource(R.drawable.XXX);


        secondText.setTextColor(gray);
        secondLayout.setBackgroundColor(whirt);

    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }}
    private void initDate() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
   if(name.equals("zhuozhengyuan")){
            imagePath.add("https://img.zcool.cn/community/01be785f5a14c811013e3187e11364.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.be9cb14851cc31b6bf005dca8adeaf9e?rik=UcAaSXy53VEPlQ&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ffd%2ftg%2fg3%2fM01%2f1C%2f0D%2fCggYGVYk44CAMYdDAB2fPyseKzQ376.jpg&ehk=lnywQw5VHGGde6A4DMAZOkmoQBSLk9%2bE0rdwgkC8%2b84%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://youimg1.c-ctrip.com/target/100m0b0000005zd4q7476.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.87b45f3d6d2bfe5155916a77e7ad4473?rik=CCvnHOyT3bytsQ&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fphotoblog%2f7%2f2%2f2%2f2%2f7222156%2f20103%2f21%2f1269174599324.jpg&ehk=obnM8XMPTk%2bO9P428NM6LY%2fo197XAcG%2fYGHIFJqTWp0%3d&risl=&pid=ImgRaw&r=0");



            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("liuyuan")){

            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.91089a0baa2898d89cc72139b3d3e7ef?rik=lplPcEACAbBpbw&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f100l0a00000054lchE5B4.jpg&ehk=s6ixvJ08KFUOThB3FaP4s2mve4%2f5Manyc6cKiwEtI7w%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://youimg1.c-ctrip.com/target/100v1f000001h19tl8030_D_10000_1200.jpg?proc=autoorient");
            imagePath.add("https://youimg1.c-ctrip.com/target/100l1e000001fl5ee672D.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.07569c8124094a949e13e03eca602bb0?rik=Z9%2bUmVXJLrgwHA&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f100r0a00000054l5s3C92.jpg&ehk=hsvR57nESAPGF3v3b87R2v8DorpNpqHhKic2%2fJSKORc%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");

        }
       if(name.equals("shizilin")){
           imagePath.add("https://img.zcool.cn/community/01d2885b461b6ea80121ade00193b7.jpeg@1280w_1l_2o_100sh.jpg");
           imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.c6dd0ae3840ffaa49027a31d3c690589?rik=4PVozhYJiTohgQ&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fupload%2fupc%2ftx%2fphotoblog%2f1201%2f08%2fc8%2f10168420_10168420_1326029832921.jpg&ehk=PUK1Hf9kHUHYrqHsUKif4HVkdB6iscu2WRwZ%2bTAnegk%3d&risl=&pid=ImgRaw&r=0");
           imagePath.add("https://p1.ssl.qhimg.com/t014f6be144a3c7472a.jpg");
           imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.b93a195732606e0e750c6db3b72ef957?rik=duA37shSF%2bWaNA&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fupload%2fupc%2ftx%2fphotoblog%2f1201%2f08%2fc8%2f10168420_10168420_1326029812968.jpg&ehk=zBUQAk%2bFz7uYLiSfK%2bk%2byarez17POjNczQzsAWiDFGo%3d&risl=&pid=ImgRaw&r=0");


           imageTitle.add("Pictures of attraction");
           imageTitle.add("Pictures of attraction");
           imageTitle.add("Pictures of attraction");
           imageTitle.add("Pictures of attraction");

       }
        if(name.equals("wangshiyuan")){
            imagePath.add("https://p1.ssl.qhimg.com/t013d4f344140748599.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100f0c0000006ppdxBF0B.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.6e309fe32d104d744fb2acd94c15bafb?rik=7nHbGHPPxY3rcw&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fphotoblog%2f1%2f3%2f2%2f9%2f1329250%2f200710%2f15%2f1192378897344.jpg&ehk=nTkD6yyRHD6KfhQE3Ld45o0WBolN3luBwZfgku7zkMo%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.37965467190f75b4b84fb22ef77761eb?rik=6MiVsLuoERmq5w&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fupload%2fupc%2ftx%2fphotoblog%2f1802%2f11%2fc5%2f74994997_1518357469105.jpg&ehk=lzGXSue7ATDEUzRE2UwIDuE4coUdvIgaWWhXPweet20%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");

        }
        if(name.equals("canglangting")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.0e59d4f181e3bf255e0c68c7dbc955ba?rik=GkWIYqUZZ0M0QA&riu=http%3a%2f%2fimg1.qunarzz.com%2ftravel%2fpoi%2f201303%2f14%2fcff6af3dddf8113bddb12cfb.jpg_r_1024x683x95_0aab7f04.jpg&ehk=HV9pTvbLu3wPDEmTCBVopRwwOUP4KM1FCTNjpKXbEWw%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.dfff082904dadf6ec2d1cb3e29b7d968?rik=jU6qhyRGM2iXRQ&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ftg%2f107%2f099%2f075%2fe2017e783c1546e294c51fe411b58acb.jpg&ehk=81uaMKcZZbxjMiTkv2wfJn%2bvNKnGIU9%2beYAEI097IdQ%3d&risl=1&pid=ImgRaw&r=0");
            imagePath.add("https://img1.qunarzz.com/travel/d6/1805/3f/6ec474cbf2e2c4b5.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/0102p1200082hcr0l9F01.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");

        }
        if(name.equals("pingjianglu")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100s1c000001crsouAC06_D_10000_1200.jpg?proc=autoorient");
            imagePath.add("https://img.zcool.cn/community/01712656fb3d8232f875a9447768f3.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://n.sinaimg.cn/sinacn10113/70/w1500h970/20190128/83e0-hshmsth2363194.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.30633a835e7196e60b2c30a4a135dbd4?rik=FpTc%2bdgMZ5kmlg&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fupload%2fupc%2ftx%2fphotoblog%2f1510%2f14%2fc6%2f13936351_13936351_1444747194569.jpg&ehk=hHgj3vulgoyeFTHXoSrOq47i%2flLJyIUT1p5vfLgYTcM%3d&risl=&pid=ImgRaw&r=0");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");

        }
        if(name.equals("shantangjie")){
            imagePath.add("https://img.zcool.cn/community/0112e45f5a116811013f1a640a53f6.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://img.zcool.cn/community/0134be5d3344a3a8012187f4b9e981.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://img1.qunarzz.com/travel/d2/1805/43/e8e97c626ee177b5.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.72298833d749968bf90e57c5d088d6c6?rik=zIGrEHjgwQSv9g&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fupload%2fupc%2ftx%2fphotoblog%2f1302%2f13%2fc3%2f18113611_18113611_1360737169062.jpg&ehk=7BwcPJxHZdAOhkfewDO%2fDQqq1nRkWW5XgxtU4eckdso%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");

        }
        if(name.equals("xietanglaojie")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100j190000015tfsx65CB.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100a190000015v8tw4172.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/10031g000001h18bt0B9C.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100p1a0000018qo7n8ED6.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }

        if(name.equals("suzhoubowuguan")){
            imagePath.add("https://img.zcool.cn/community/01c6015ee8c3b3a801215aa0d775df.jpg@2o.jpg");
            imagePath.add("https://img.zcool.cn/community/0150df590fed3ea801214550103be4.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://img.zcool.cn/community/01fe0c5ee8c56fa801206621f01898.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://img.zcool.cn/community/0166ac5c3b167fa80121fbb0f43268.jpg@1280w_1l_2o_100sh.jpg");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }

        if(name.equals("fuzimiao")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100312000000sblw50E04.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100r12000000sbyhg8D85.jpg");
            imagePath.add("https://img.zcool.cn/community/0192db5c812fb8a80120af9a13a0ef.jpg@3000w_1l_0o_100sh.jpg");
            imagePath.add("https://img.zcool.cn/community/01088d556841970000012b20ccfc1a.jpg@3000w_1l_2o_100sh.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("xuanwuhu")){
            imagePath.add("https://img.zcool.cn/community/010ade5ae1d918a80120927b43dd8f.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.e73f837220394ccf7fb2da9ed2f82c44?rik=Qdfy2IAbq7OqPg&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f100g0a0000004x76gB512.jpg&ehk=ruOytQB80zqxbMvvkOOdBBaouqgpmuEUY2T4gYK%2fbKs%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://img.zcool.cn/community/01db7755497da90000019ae907bae8.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://img.zcool.cn/community/01eafb554b9ee7000001bf72265f2a.jpg@1280w_1l_2o_100sh.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("zhongshanling")){
            imagePath.add("https://p1.ssl.qhmsg.com/t01be0017a532117b80.jpg");
            imagePath.add("https://img.zcool.cn/community/011de45df3c729a801219ccec3a276.jpg@2o.jpg");
            imagePath.add("https://img.zcool.cn/community/01bed25df3c71ba8012097b307948d.jpg@2o.jpg");
            imagePath.add("https://img.zcool.cn/community/0135b059de1e9ca80121ae0c664b42.jpg@2o.jpg");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("zhenzhuquan")){
            imagePath.add("https://flight-feed.qunarzz.com/as3/180/image/poi_vishnu/7f685c72-f914-40b6-9d5c-ee97a6cebaf3.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.270bce6cfac7b924d59ab50801390b9a?rik=VUWn7Rk76wfTEQ&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f100e08000000310hi0328.jpg&ehk=h7CBhQ7%2fvECAjHcWnGYzHPaWtsjXyC0k2sdsdWR478Q%3d&risl=1&pid=ImgRaw&r=0");
            imagePath.add("https://youimg1.c-ctrip.com/target/100w1g000001h5kqq001C.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.c1925b7af30aebdd6647de8f5ef2611d?rik=PKQu8G6KNdFWtg&riu=http%3a%2f%2fwww.zzqfjq.com%2fw960%2fu%2fcms%2fwww%2f201808%2f1715275003wd.jpg&ehk=LYnS3WW1ijMGHECJGbkw%2bsPLOJADYT9fWH6YZULBLBU%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("nanjingzongtongfu")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100j1f000001gptg05654_D_10000_1200.jpg?proc=autoorient");
            imagePath.add("https://img.pconline.com.cn/images/upload/upc/tx/photoblog/1308/17/c0/24558436_24558436_1376670003742_mthumb.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100r0b0000005zkbe65CA.jpg");
            imagePath.add("https://img1.qunarzz.com/travel/d2/1511/ef/f38748e45c8fb8f7.jpg_600x600x70_4e2f24c6.jpg");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("mingxiaoling")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.45bcd823a334209641ae3df62062f15f?rik=AkuwJWSyHPxuSA&riu=http%3a%2f%2fn.sinaimg.cn%2fsinakd20201016ac%2f104%2fw1024h680%2f20201016%2fdcc4-kaqzmiv8782493.jpg&ehk=lEsFaq5o7Gw%2bMEOAWq1nxGCbKfk72WKOBAyvNWSaUcQ%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://img.zcool.cn/community/011a675df3c326a801219cceeb11d7.jpg@2o.jpg");
            imagePath.add("https://img.zcool.cn/community/01140b5df3c354a8012097b3b0bb6d.jpg@2o.jpg");
            imagePath.add("https://img.zcool.cn/community/01c6595df3c30ea8012097b3b8f259.jpg@1280w_1l_2o_100sh.jpg");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("nanjingbowuguan")){
            imagePath.add("https://img.zcool.cn/community/01a8095b850d10a8012190f29e99c9.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://img95.699pic.com/photo/50047/9731.jpg_wh860.jpg");
            imagePath.add("https://p1.ssl.qhmsg.com/t017a0e288ebf6b9c89.jpg");
            imagePath.add("https://img.zcool.cn/community/0159495b4845d1a8012036be2e3a0d.JPG@1280w_1l_2o_100sh.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("jimingsi")){
            imagePath.add("https://youimg1.c-ctrip.com/target/fd/tg/g3/M06/51/55/CggYG1bXIHWAVTqVABrBVzsvyIQ620.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/fd/tg/g3/M01/52/D6/CggYGVbXIJyAbrJ2ACCWl6qETxE936.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.0ff821da2d88c3a24462627808093d09?rik=w%2fdhNd7o0Brt6g&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ffd%2ftg%2fg6%2fM04%2f41%2f08%2fCggYtFbXIeuAaC2wABXlEy9jMr4060.jpg&ehk=nOMGDGa0KiRD6URoA6zYWQ1ynxMhvj3UhVYgyDuKWas%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.e2d8b82c697942497f2608fa50cb70cf?rik=U%2bk1XubDP70JpQ&riu=http%3a%2f%2fimg1.qunarzz.com%2fwugc%2fp97%2f201204%2f20%2f9c13cc7587ef585193835fbb.jpg_r_1024x683x95_bb6de912.jpg&ehk=5l4LWKVKpT3rGK065WUUGzrXsSd9TH2Ld5%2fAytIEPC8%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("zhanyuan")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.88bad30941e21b6490594f2a559c8321?rik=XcMre938Zz2bIg&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f100q080000002u6qi7A52.jpg&ehk=yIdPhFquANL2vs7wiSiPpS958A6DVwgQM7VUOonGEPU%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.cc9cd98a7a8416a07a1c6a8de8545cc8?rik=BiPZz6dC6SZlNA&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ftg%2f349%2f266%2f214%2f44fc605c5a464a3ea533a978e4008b7d.jpg&ehk=O0PQbvvlspobz5Ymc3M9NmHgtAQMZCd8pL1kbfbWrKU%3d&risl=1&pid=ImgRaw&r=0");
            imagePath.add("https://img1.qunarzz.com/travel/d2/1609/67/b7bb9c98f4ebc9b5.jpg_r_640x424x70_07829ff7.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.c0efaf68bcf73371e1fcc73c1b37524d?rik=dwN7BUxLnIQuSQ&riu=http%3a%2f%2fn.sinaimg.cn%2fsinakd20210502ac%2f125%2fw2000h1325%2f20210502%2f3fb7-kppteas1668365.jpg&ehk=D5zQ98hUll4EKwCbwXHiqZkhBwvoOfwzAqFkjJDDQrA%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("yuhuatai")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100m14000000xebm9674F.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100c14000000x7kxxE3F5.jpg");
            imagePath.add("https://p1.ssl.qhimg.com/t01b80c10b7e8338a13.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.ea66254415481b30a4558990a5dcbc50?rik=7obAI5ziaKWKcg&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ffd%2ftg%2fg2%2fM00%2f44%2f71%2fCghzf1Vb--qADgojAALL3UN53Nk363.jpg&ehk=JVtk2m62kytHSlTbQuTr7IsG0LGvjIhRw6NVsHWQn%2fI%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("shouxihu")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100q11000000qcgpg2F48.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100611000000qbsoy001C.jpg");
            imagePath.add("https://p1.ssl.qhimg.com/t0131880d923b0e9c5e.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/0103l120005v781o08CE2_D_10000_1200.jpg?proc=autoorient");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("damingsi")){
            imagePath.add("https://p1.ssl.qhimg.com/t018c0de6a9c770da59.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.3275bba8e2c6c8c3ec64ad8736d4c3de?rik=ES1CZXtU5doeAA&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ffd%2ftg%2fg2%2fM03%2fED%2fAC%2fCghzf1UUDl6ALG37ABN53RlMbtk301.jpg&ehk=1devV0ZOkqEING0wv3xIdm0YNQE2XwfjrLquejxb1XY%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://tr-osdcp.qunarzz.com/tr-osd-tr-space/img/8a8b11edf904e7e36461ad6ee3193be3.jpg_r_720x480x95_0b139dd1.jpg");
            imagePath.add("https://img.zcool.cn/community/01e4e9577d34fd0000012e7e7859d8.jpg@3000w_1l_0o_100sh.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("geyuan")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.b09dc3006e9fc2be0eb7145c5c45a617?rik=DjPI9Mf5DMY5gA&riu=http%3a%2f%2fdimg06.c-ctrip.com%2fimages%2ffd%2ftg%2fg1%2fM03%2f0E%2f17%2fCghzfFTLFz-AUj4YAA4lpTH7KUY932.jpg&ehk=UwQQ6lQZV70p1AL%2fb7DhCqd8Vlm0sAwRosNvAmKLmP0%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.ae9c4cc3ac9a08bd96e557136ee2726b?rik=sg9p3ZeI5ACkkw&riu=http%3a%2f%2fy0.ifengimg.com%2f52e0de6343af0d30%2f2013%2f1213%2frdn_52aa82a52059e.jpg&ehk=%2b3ENQHmqeDPHkpDFnCUPBt3Vgl1DSS49F%2f77lAh6MGg%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://img1.qunarzz.com/travel/d7/1512/79/3167f24bd5b962f7.jpg_r_640x360x70_7bdc8041.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.c8493a08844f5872b904e12e0971fb72?rik=exLOwcNpt9UWbQ&riu=http%3a%2f%2fpic.baike.soso.com%2fp%2f20140220%2f20140220162748-1031950010.jpg&ehk=r8zslZpK5IMibnySXCgoZIYmpslVOCUwsKb1PSUR70M%3d&risl=&pid=ImgRaw&r=0");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("heyuan")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100c1f000001gsvpoD3FB_D_10000_1200.jpg?proc=autoorient");
            imagePath.add("https://img1.qunarzz.com/travel/d9/1608/e4/41afe1c83fffc1b5.jpg_r_640x479x70_06ac223f.jpg");
            imagePath.add("https://p1.ssl.qhimg.com/t018bb8852d0449da11.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.e427253c42b0129027f9ffe03e9d4d20?rik=fl9%2f9eNFSUIIxQ&riu=http%3a%2f%2fpic5.40017.cn%2f03%2f000%2fab%2fa6%2frBANB1zwoaiATRRTAAIBwmfayDc773.jpg&ehk=hfUg9nHIexb3lvbC7DXIt%2fDlAOll6HHzTRPfBf9HPJ8%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("dongguanjie")){
            imagePath.add("https://p1.ssl.qhimg.com/t0149bc49a7eb3d54ca.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.11b02b3617617f537c0c560ecc2a01be?rik=tre3LjYUQP477g&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f1002060000001pwpw14E0.jpg&ehk=hI1OvyUdE1FTcabHuUC6aLgqgAp0DTTRf3iyGmPqEK8%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.5c5aebace669b820c5bb0ab6f9ebe329?rik=lwM3AsTjXA1ZSw&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f100d0b000000571ogEF9E.jpg&ehk=A3li2ROZJn9ngg2u1Out982g%2bM%2bE403As28iAg%2fdeFA%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://img1.qunarzz.com/travel/d7/1702/f8/d91392b2d6b500b5.jpg_r_640x427x70_257b5ed3.jpg");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("guazhougudu")){
            imagePath.add("https://p1.ssl.qhimg.com/t01ee274b9e03e06a3d.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.293155756ab3c7da6cd10161c3a7fd79?rik=uCMoTXu5ajOZUw&riu=http%3a%2f%2fp8.itc.cn%2fq_70%2fimages03%2f20201030%2fba527dadfeba43bc9d2c2f658fde420b.jpeg&ehk=yVOhleE43jXVj%2fZMDA8NZKdVKpSMpik9P32X8OQs5uM%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.f530567517d932265d86588f4fa3c787?rik=vd%2fv1J%2f6HjZCow&riu=http%3a%2f%2fwww.yznews.com.cn%2fyzwzt%2f2022-06%2f24%2f1cb5f7ea-bbf6-4f27-922e-1275de6e6946.jpg&ehk=XKgqHqVeCLdcyU4V3yQaqiiK7rporNy%2b%2f3s0f%2bEn4Ac%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://img1.qunarzz.com/travel/d7/1702/ec/55aa295ebb1dceb5.jpg_r_720x480x95_1c54dff2.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("hanlingyuan")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.b77b50b52cd6b8b09792f7719c3b935f?rik=8FO3W5iEep7xIA&riu=http%3a%2f%2fy3.ifengimg.com%2f52e0de6343af0d30%2f2013%2f1230%2frdn_52c0e883368bf.jpg&ehk=858NRboST1xpoMLTtLV%2b1aMFAcC4oqmyybsXQRgt2jc%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.15559a760ca31b2fcc254cc0a793ee73?rik=ZGH0CBLk%2bNIhkg&riu=http%3a%2f%2fy3.ifengimg.com%2f52e0de6343af0d30%2f2013%2f1230%2frdn_52c0ec77328b7.jpg&ehk=HSjh%2f8J8pu33McbiXJq0JqKoox3pVs8ygJQT6RJ2UKk%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://youimg1.c-ctrip.com/target/100g170000011e8nb6ABE.jpg");
            imagePath.add("https://p1-q.mafengwo.net/s19/M00/3B/EE/CoNJcWHhGrEagc7lABYCXo2w47M.jpeg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("guyunhe")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100e1600000101s6mC4D4.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100j0x000000l34ls5E07.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100f1b000001avz1z910B.jpg");
            imagePath.add("https://img1.qunarzz.com/travel/poi/201407/28/21f644d13c6479e0c8d65eac.jpg");

            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("mengchengyi")){
            imagePath.add("https://n.sinaimg.cn/sports/crawl/149/w550h399/20190424/9Jq_-hvvuiyn8393670.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.2ecccd0831ed6dbf0460ed170fb58a23?rik=9%2fuczOXunMNJBw&riu=http%3a%2f%2fimg.picture.lckc.net%2f20170615%2fxmkendlhvaz.jpg&ehk=TRo%2bi1KRcnW%2fjkaK4WZzybDfTc%2fw69LvwBwQPmKgORo%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://img1.qunarzz.com/travel/d7/1801/1a/0594a8feda2520b5.jpg");
            imagePath.add("https://img1.qunarzz.com/travel/d1/1801/2f/2f6e64c8d3be50b5.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }
        if(name.equals("shaoboguzhen")){
            imagePath.add("https://flight-feed.qunarzz.com/as3/180/image/poi_vishnu/7873efb1-7511-47f5-86b3-8cc3eb4303fd.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100c13000000vcyacC635.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.fd10154116437559aa7d2c0ade5ec8e4?rik=OeqfhMhqeeLOOg&riu=http%3a%2f%2fimg.mp.itc.cn%2fupload%2f20170705%2f2c72c3692eb24dac8a2e515871577b15_th.jpg&ehk=GNIT%2bHegj9ML83DBfCP8WcyPuC7t5XFUyVY7o6URXd4%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://img1.qunarzz.com/travel/d8/1606/93/e2c6e019b6e1a39a.jpg");


            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
            imageTitle.add("Pictures of attraction");
        }

  
  
        if(name.equals("Mount Huaguoshan")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/6a600c338744ebf899ff903fdef9d72a6059a720?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/faf2b2119313b07e5433a4080bd7912397dd8c20?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/77094b36acaf2edd0c7fbde98a1001e939019320?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Haizhou Ancient City")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/503d269759ee3d6d35007b5448166d224e4ade6f?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/10dfa9ec8a1363270e6a43ac9a8fa0ec09fac7ae?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9e3df8dcd100baa1c984c7244c10b912c9fc2e80?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("KongWang Mountains")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b636c3df5e602338744ebf8ac64?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/37d3d539b6003af38a9e77d63b2ac65c1038b664?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b999a9014c086e06696597fd0c087bf40bd1cbb3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");


            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("YunTai Mountains")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/f2deb48f8c5494ee55d2db1023f5e0fe98257ec3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/6d81800a19d8bc3e0e02d3c38c8ba61ea8d345b5?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9345d688d43f879445729c27dc1b0ef41bd53a2e?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Taohuajian Mountain Stream")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d788d43f8794a4c25ea1729d00f41bd5ac6e3981?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/c2cec3fdfc039245c91d74b98994a4c27c1e25df?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b3119313b07eca80dc87ac519f2397dda0448382?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Eden Garden")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/962bd40735fae6cd7b892c6117fe182442a7d933a1d4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d1160924ab18972bd4079acafe806c899e510fb39cd4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/aa18972bd40735fae6cdcab9861c18b30f2442a7a2d4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("LianYunGang Museum")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9213b07eca8065389df4453794dda144ad3482fc?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/4a36acaf2edda3ccddb3590402e93901213f92bb?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/faedab64034f78f0e8717bd77a310a55b3191c9f?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Qinshan Island")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/6a63f6246b600c333f226c6f104c510fd9f9a13a?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/adaf2edda3cc7cd9aa2449573301213fb80e9136?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/e7cd7b899e510fb34534de96d933c895d1430c85?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("And Lake Wetland Park")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a50f4bfbfbedab64ffe98d80f436afc378311ed3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/c9fcc3cec3fdfc03460923f8d73f8794a4c22679?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/11385343fbf2b211ff7a450ec98065380cd78e1e?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Dayi Mountains")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/6c224f4a20a446235a374bfb9522720e0df3d7ae?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/bf096b63f6246b600c33266765af0d4c510fd9f904d2?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/242dd42a2834349b033bf69647bd02ce36d3d53910d2?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }

   
        if(name.equals("the Wolf Mountain")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b6303d724fc0e338744eaf8ac54?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/2934349b033b5bb550adafd635d3d539b600bca4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/6c224f4a20a4462392b30d489b22720e0cf3d79b?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Seyuan Garden")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/6a63f6246b600c3376a2a1c4144c510fd8f9a1e7?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0ff41bd5ad6eddc42e27cbf737dbb6fd536633fd?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d1160924ab18972b5cfa7fc6e8cd7b899f510afd?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Nantong Forest Safari Park")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/63d9f2d3572c11df8a687c296c2762d0f703c27b?x-bce-process=image/resize,m_lfit,w_4096,limit_1/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b7003af33a87e9502c30fe211d385343fbf2b453?x-bce-process=image/resize,m_lfit,w_4096,limit_1/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/7af40ad162d9f2d3acb50c99a7ec8a136327cc24?x-bce-process=image/resize,m_lfit,w_4096,limit_1/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Zhang Jian Memorial Hall")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/3801213fb80e7bec14a90b092d2eb9389a506be2?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/c8ea15ce36d3d539ad6d072b3887e950342ab081?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/f2deb48f8c5494eea55107f12ff5e0fe98257ee2?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Junshan Mountain")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/aa64034f78f0f73633a32be20355b319eac413f7?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0e2442a7d933c8955e7eff98d81373f0830200d4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d009b3de9c82d15881f23e52890a19d8bd3e42e4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Yangkou port")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9e3df8dcd100baa14e5b4d244510b912c8fc2e3d?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b999a9014c086e067402bef100087bf40bd1cbe6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d4628535e5dde7116c3df0a0a0efce1b9c1661fd?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Xiaoyangkoubeauty spot")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/10dfa9ec8a1363276e3ee09d998fa0ec09fac748?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/14ce36d3d539b600b157f8c9e750352ac65cb737?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxODA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://img1.qunarzz.com/travel/poi/1806/ed/d4295b9692b1eb37.jpg_480x360x95_3eea9923.jpg");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("HAOHE")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100d14000000w8wbi888F.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100l0x000000legf91ED5.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100d170000010qim0B13B.jpg");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }

        //Wuxi
        if(name.equals("Yuantouzhu")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/fc1f4134970a304e251f4b4d6e9ab086c9177f3ef673?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbf6c815156a68aab3eb13533faeb06?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/fc1f4134970a304e251f4b406e9ab086c9177f3ef674?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/962bd40735fae6cd7b89950bb0e1182442a7d933a105?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/1c950a7b02087bf40ad129814d81402c11dfa9ec6102?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d31b0ef41bd5ad6e29d3893c8bcb39dbb6fd3c3a?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/cefc1e178a82b901aa8bd5d0738da9773912ef2b?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0dd7912397dda144ad34cdd40de5c7a20cf431ad2800?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/342ac65c10385343fbf238462c41a77eca8065382203?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/ca1349540923dd548e5c58ddd109b3de9c82485c?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/4034970a304e251f95ca0e4018d4de177f3e6709f572?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Water Margin City")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/7aec54e736d12f2e41e811114fc2d562843568d6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U3Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/024f78f0f736afc3dd56449db519ebc4b64512eb?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b21c8701a18b87d6e215ce62070828381f30fd7c?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/38dbb6fd5266d01639cd3424992bd40734fa35e1?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/242dd42a2834349bd5bb2e79c0ea15ce37d3be89?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/7e3e6709c93d70cfb8d8a365f8dcd100baa12bb3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d01373f082025aafccb8c3b6fbedab64024f1ac0?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/342ac65c1038534389465b349413b07eca80881e?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/4afbfbedab64034f3019680aa1c379310b551de1?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b812c8fcc3cec3fde29d0d79d888d43f8794271e?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/267f9e2f07082838845b9ce8b199a9014d08f1fc?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Xihui Park")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/21a4462309f79052981148540ff3d7ca7bcbd549?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/ac6eddc451da81cb39db96c4f231c7160924ab189b39?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/77094b36acaf2edd378e9c808e1001e939019358?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d1160924ab18972bd40712c3469a6c899e510fb39c39?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/72f082025aafa40f0f3664d1a564034f79f019cd?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/10dfa9ec8a136327ceea8dad948fa0ec08fac72d?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxODA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/4d086e061d950a7ba00b015a0fd162d9f2d3c976?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxODA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/7aec54e736d12f2e3e096d2a4ac2d56285356868?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxODA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/730e0cf3d7ca7bcb6a9a2d46bd096b63f724a8f2?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/03087bf40ad162d9b082b77512dfa9ec8a13cd6f?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/18d8bc3eb13533fa94293159aad3fd1f41345b2d?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxODA=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Lingshan Scenic Area")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/503d269759ee3d6d6b995be64e166d224f4ade44?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9922720e0cf3d7cadfc297a7f21fbe096b63a92f?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/91ef76c6a7efce1b7f1d5c5aa551f3deb48f6568?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9922720e0cf3d7cad75d9fa7f21fbe096a63a9bc?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0dd7912397dda1447aa2cc63b2b7d0a20df486bc?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0823dd54564e9258de5f263f9c82d158cdbf4ef3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d62a6059252dd42a927aac7a033b5bb5c8eab8f4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/3b87e950352ac65cd354cea2fbf2b21192138a8a?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/21a4462309f790523ce6ebef0cf3d7ca7acbd562?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/e4dde71190ef76c64b7851fa9d16fdfaae5167bd?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b219ebc4b74543a9eeb05d1d1e178a82b80114b3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Dangkou Ancient Town")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/3b87e950352ac65c31a06eaaf9f2b21192138a45?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/fd039245d688d43f17dd992b7f1ed21b0ff43bf6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a9d3fd1f4134970a46d618f697cad1c8a6865d45?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a9d3fd1f4134970a462518f697cad1c8a6865d90?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxODA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b2de9c82d158ccbf46fafb601dd8bc3eb1354121?x-bce-process=image/resize,m_lfit,w_4096,limit_1/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/2934349b033b5bb563cf8bf238d3d539b600bca6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/3b87e950352ac65ca5c3ce7ff5f2b21192138afc?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/e850352ac65c1038e61965cebc119313b07e8953?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/83025aafa40f4bfbbda435580d4f78f0f7361841?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9c16fdfaaf51f3de527411689aeef01f3b2979fc?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Ancient Canal Scenic Area")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9345d688d43f87941f0e7379d51b0ef41ad53af7?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/359b033b5bb5c9ea573638b4d239b6003bf3b39b?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyMjA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d6ca7bcb0a46f21fc298a3cff6246b600d33aefa?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/77094b36acaf2edd0c5fb4758d1001e938019384?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9e3df8dcd100baa13184ba274710b912c9fc2e86?x-bce-process=image/resize,m_lfit,w_4096,limit_1/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/5fdf8db1cb134954da4f10f9564e9258d0094ade?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d1a20cf431adcbef283b4551abaf2edda3cc9f5d?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/63d9f2d3572c11dfa9ec0465e26b75d0f703918f646a?x-bce-process=image/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/279759ee3d6d55fbb2fbd860ec6e584a20a44623776a?x-bce-process=image/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/bf096b63f6246b600c3329326ab40d4c510fd9f9046a?x-bce-process=image/format,f_auto");
            imagePath.add("https://img1.baidu.com/it/u=3281841044,1846177397&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=375");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Wuxi Zoo")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/10dfa9ec8a1363276129df3a918fa0ec09fac7f6?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/314e251f95cad1c81f4e3f33743e6709c93d5129?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/f7246b600c33874477fcba705f0fd9f9d72aa085?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d788d43f8794a4c259ec4d2700f41bd5ac6e39ea?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b7003af33a87e950852e66601e385343fbf2b485?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/faf2b2119313b07e7284c50402d7912397dd8c85?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a044ad345982b2b711a6acc83fadcbef76099b85?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/bba1cd11728b4710a3ce57c0cdcec3fdfd0323dc?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a6efce1b9d16fdfa753346e6b48f8c5494ee7b5f?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/e850352ac65c1038365207b3b2119313b07e8997?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/377adab44aed2e73e87b2c208901a18b87d6fa28?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Wuxi Museum")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/8601a18b87d6277f9e2f69bda7690830e924b99956ae?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/1f178a82b9014a909ce0eb28ab773912b21bee4d?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/77c6a7efce1b9d160efd36f4f1deb48f8c546469?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a6efce1b9d16fdfa5b566a7bb68f8c5494ee7b69?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b8014a90f603738dc594a1b7b11bb051f919ec4d?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/9c16fdfaaf51f3de428815f196eef01f3a297969?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d4628535e5dde71129e2bff3a0efce1b9d166187?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/7e3e6709c93d70cfcbc25708ffdcd100baa12bff?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d53f8794a4c27d1ed11dc6c11cd5ad6eddc438ff?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d6ca7bcb0a46f21fcb1ca356f1246b600c33ae8f?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/37d12f2eb9389b50f97112578235e5dde7116e2d?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/8326cffc1e178a828a0782a5f103738da977e8f8?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("Huishan Temple")){
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d439b6003af33a87e9504829010807385343fbf21ff1?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d833c895d143ad4b91a9edcc8c025aafa40f0632?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/024f78f0f736afc3355f9469bd19ebc4b745126e?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/b3119313b07eca8065383e828d7780dda144ad342500?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/0ff41bd5ad6eddc451da76cbfe8fa1fd5266d01695fc?x-bce-process=image/resize,m_lfit,w_4096,limit_1/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/1ad5ad6eddc451da81cb706ac0a94566d016082494ac?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/d1160924ab18972bd407644890996c899e510eb39caf?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a2cc7cd98d1001e93901cd8dce5a6cec54e737d139ae?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/7aec54e736d12f2eb938d6055396c2628535e5ddc200?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxNTA=,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/a6efce1b9d16fdfaaf515cdc73db9b5494eef01fcdfe?x-bce-process=image/resize,m_lfit,w_4096,limit_1/watermark,image_d2F0ZXIvYmFpa2UyNzI=,g_7,xp_5,yp_5/format,f_auto");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }

     
        //Changzhou
        if(name.equals("Universal Dinosaur City")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.7ba97c87aadce7f70c92c3adaef42b03?rik=jguVVYjqV76fOQ&riu=http%3a%2f%2fwww.yhcfly.com%2fmtscs%2faeb4b52f-4d9b-41d2-a03b-31b72cb4bff2%2f20170818145006971.jpg&ehk=seesbvsQPYkaHNAb5B2rPZs2a6wJ6K3q9hn%2fzq6Boeg%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://youimg1.c-ctrip.com/target/10051f000001gsrqa9E5A_D_10000_1200.jpg?proc=autoorient");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.18a48506b97e646d8bfb11d04f3979c7?rik=A%2bFY%2fyCdMx9cKA&riu=http%3a%2f%2fs3.lvjs.com.cn%2fuploads%2fpc%2fplace2%2f2017-07-21%2fc95a0d4c-daf8-442a-a26e-f2ec98359088.jpg&ehk=NabEz5epySsPqUAz%2fUDEozCxYhyQpOJYOWANB%2bmopB4%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
            imageTitle.add("景点图片");

        }
        if(name.equals("Tianmu Lake")){
            imagePath.add("https://youimg1.c-ctrip.com/target/010491200079nra9f6498.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100i13000000tz0sgA2C3.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100w0g000000860wb627A_D_10000_1200.jpg?proc=autoorient");
            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
        }
        if(name.equals("Yancheng")){
            imagePath.add("https://youimg1.c-ctrip.com/target/0102p1200081x8f101530.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/10090a0000004p90p9F42.jpg");
            imagePath.add("https://img95.699pic.com/photo/50178/1633.jpg_wh860.jpg");
            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
            imageTitle.add("景点图片");

        }
        if(name.equals("Tianning Zen Temple")){
            imagePath.add("https://youimg1.c-ctrip.com/target/10031a00000193bitD827.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/fd/tg/g6/M05/D5/57/CggYtFcbF22AA7BXAAmZbFLGfNA628.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.83202169e9e546b5c451d887ffbc04f3?rik=J6sh8NxssaUSaQ&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ffd%2ftg%2fg4%2fM06%2f57%2f7E%2fCggYHFaUYz2ATE5GABB9Pc9T7xc463.jpg&ehk=4wGo8zwppjVQoC1gg2bpJJ%2f38bWE0QSbSCndWXq4%2fS4%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
        }
        if(name.equals("Jintan Maoshan")){
            imagePath.add("https://img1.qunarzz.com/travel/d3/1808/9d/c8feb0bbd317f5b5.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.a06d9ecf0e2d99370d7f4af7e3f60c02?rik=rRU6NjnEH0AUfg&riu=http%3a%2f%2fwglj.changzhou.gov.cn%2fuploadfile%2fwgxj%2f2022%2f0402%2f20220402155922_57650.jpg&ehk=cG4tBiFNEWWa40rdn3Qinbyc5VT%2fDNec05OxKnL%2faiA%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://youimg1.c-ctrip.com/target/100f0z000000nht0u73CF_D_10000_1200.jpg?proc=autoorient");

            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
            imageTitle.add("景点图片");
        }

    }
    private void initView() {
        glideImageLoader = new GlideImageLoader();
        banner = findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(glideImageLoader);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(imagePath);
        banner.start();

    }


//    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//          MyViewHolder holder = new MyViewHolder(LayoutInflater.from( DetailsActivity.this).inflate(R.layout.recycle_itemone,parent,false));
//            return holder   ;
//        }
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, final int position) {
//
//            holder.iv.setImageResource(icons[position]);
//            holder.nameone.setText(name1[position]);
//            holder.buy.setText(buy[position]);
//            holder.troduce.setText(introduces[position]);
////            int img =intent.getIntExtra("detail_iv",0);
////            final int f = intent.getIntExtra("f",0);
////            name = intent.getStringExtra("detail_name");
////            final String ziliao = intent.getStringExtra("detail_introduce");
////            final String buy = intent.getStringExtra("detail_buy");
////            final  String name_receive = intent.getStringExtra("name");//景点地区
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(DetailsActivity.this,JiudianActivity.class);
//                    intent.putExtra("detail_iv",icons[position]);
//                    intent.putExtra("detail_name1",name1[position]);
//                    intent.putExtra("detail_buy",buy[position]);
//                    intent.putExtra("name",name_receive1);
//                    intent.putExtra("detail_introduce",introduces[position]);
//                    intent.putExtra("image",img);
//                    intent.putExtra("name1",name);
//                    intent.putExtra("buy1",buy1);
//                    intent.putExtra("troduce",ziliao);
//                    startActivity(intent);
//                    finish();
//                }
//            });
//        }
//
//
//
//        @Override
//        public int getItemCount() {
//            return name1.length;
//        }
//        class MyViewHolder extends RecyclerView.ViewHolder{
//            TextView nameone;
//            ImageView iv;
//            TextView buy;
//            TextView troduce;
//            public MyViewHolder(View view){
//                super(view);
//                nameone = (TextView)view.findViewById(R.id.name);
//                iv = (ImageView)view.findViewById(R.id.iv);
//                buy=(TextView)view.findViewById(R.id.buy);
//                troduce =(TextView) view.findViewById(R.id.ziliao);
//            }
//        }
//    }

    private void openMap1(){
        if (isAvilible("com.baidu.BaiduMap")) {//传入指定应用包名
            try {

                //有经纬度的情况
//                Intent intent = Intent.getIntent("intent://map/direction?" +
//                        "destination=latlng:" + "34.264642646862" + "," + "108.95108518068" + "|name:我的目的地" +    //终点
//                        "&mode=driving&" +
//                        "&src=appname#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//                startActivity(intent); //启动调用
                Intent intent = Intent.getIntent("intent://map/direction?" +
                        "destination=name"+
                        "&mode=driving&" +
                        "&src=appname#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                startActivity(intent); //启动调用

            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
        } else {
            //market为路径，id为包名
            //显示手机上所有的market商店
            Toast.makeText(DetailsActivity.this, "You have not installed Baidu Map", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
    }

    private void openMap2()
    {
        if (isAvilible("com.autonavi.minimap")) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            //将功能Scheme以URI的方式传入data   有经纬度的情况
//            Uri uri = Uri.parse("androidamap://navi?sourceApplication=appname&poiname=fangheng&lat=" +
//                    "34.264642646862" + "&lon=" + "108.95108518068" + "&dev=1&style=2");
            Uri uri = Uri.parse("androidamap://poi?sourceApplication=softname" +
                    "&keywords=" +name+
                    "&dev=0");

            intent.setData(uri);

            //启动该页面即可
            startActivity(intent);
        } else {
            Toast.makeText(DetailsActivity.this, "You have not installed Gaode Map", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }
    }

    public boolean isAvilible(String packageName){
        //获取packagemanager

        final PackageManager packageManager = this.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
    }



