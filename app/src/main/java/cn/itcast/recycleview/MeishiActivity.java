package cn.itcast.recycleview;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeishiActivity extends AppCompatActivity {
    String name;
    Button open;
    String nameadd;
    private CommonDialog myDialog;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private DialogOrderTypeFragment mFragment2=new DialogOrderTypeFragment();
    private String[] name1 = {"xiefenshizitou","baochaoruandou","bingfengtangchaoxia","beijingkaoya","shuizhuroupian","tilamisu"};
    private String[] image = {"https://bkimg.cdn.bcebos.com/pic/14ce36d3d539b6009d278ecbe950352ac75cb7e5?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto",
            "https://pic.ntimg.cn/file/20220312/8540357_011417311127_2.jpg",
            "https://th.bing.com/th/id/R.56dbfb014c196dbffce71a0e82810a6f?rik=O4DM0EE2Jajlvg&riu=http%3a%2f%2fi3.meishichina.com%2fattachment%2frecipe%2f2016%2f04%2f24%2f2016042414614952928236591561.jpg%40!p800&ehk=8GYfLo4266WNkJSjad5xZKkNChVxfPyKqJtyfwIV198%3d&risl=&pid=ImgRaw&r=0",
            "https://n.sinaimg.cn/sinakd10117/732/w2048h1084/20200627/086d-ivmqpck3629916.jpg",
            "https://pic.ntimg.cn/file/20201209/6657894_180251741000_2.jpg",
            "https://img.zcool.cn/community/01474a5de37a2ca8012159720e68bd.jpg@3000w_1l_0o_100sh.jpg"};
    private  String[] buy ={"¥88","¥68","¥58","¥98","¥48","¥28"};
    private  String[] introduces = {"The taste is soft, fat but not greasy, rich in nutrition. It has the effect of clearing heat and refreshing god, clearing the liver and benefiting the gallbladder and nourishing the stomach.",
            "Used for tonifying deficiency and health conditioning, Qi and blood double conditioning, malnutrition conditioning, postpartum recovery conditioning.",
            "Spicy delicious, special taste, the outer skin scorched crispy, the inside of the meat is tender and juicy",
            "The skin is crisp and pliable. Fat but not greasy, full of oil. It is more delicious with sugar, cucumber and shallots.",
            "The meat is tender and smooth, the seasoning is spicy and crisp, the vegetables are refreshing, and the sesame pepper and garlic are just right",
    "Perfect as a dessert with moderate sweetness and good combination of cream and chocolate powder"};
    int imageone;
    String nameone;
    String buyone;
    String troduceone;
    String name_receive;
    String pingfen;
    int f;
    int numble=1 ;
    public static String name_one;
    public static String nametype_one;
    public static String name_numble;
    private Banner banner;
    private GlideImageLoader glideImageLoader;
    private List<String> imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meishi);
        Intent intent = getIntent();
        f = intent.getIntExtra("f",0);
        imageone = intent.getIntExtra("image",0);
        nameone = intent.getStringExtra("name1");
        buyone = intent.getStringExtra("buy1");
        troduceone = intent.getStringExtra("troduce");

        pingfen = intent.getStringExtra("detail_pingfen");
        name = intent.getStringExtra("detail_name1");
        final String buy = intent.getStringExtra("detail_buy");
        final  String introdece = intent.getStringExtra("detail_introduce");
        name_receive= intent.getStringExtra("name");//景点地区
        final TextView menpiao = findViewById(R.id.menpiao);
        menpiao.setText(buy);
        TextView jieshouziliao = findViewById(R.id.jieshouziliao);
        jieshouziliao.setText(pingfen+"  "+introdece);

        TextView jieshouname = findViewById(R.id.jieshouname);
        jieshouname.setText(name);
        nameadd = name+name_receive;
        open=(Button)findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment2.show(getFragmentManager(), "android");

            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    name_one = connect.jiudianreachone(name);
                    nametype_one = connect.jiudianreachtwo(name);
                    name_numble = connect.jiudianreachthree(name);
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        mRecyclerView = findViewById(R.id.recycle_jiudian);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(mAdapter);

        ImageView fanhui = findViewById(R.id.image);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.putExtra("detail_iv",imageone);
                intent.putExtra("detail_name",nameone);
                intent.putExtra("detail_introduce",troduceone);
                intent.putExtra("detail_buy",buyone);
                intent.putExtra("name",name_receive);
                intent.putExtra("f",f);
                intent.setClass(MeishiActivity.this,DetailsActivity.class);
                startActivity(intent);
                finish();
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
                    finish();
                }

            }
        });
        initDate();
        initView();
    }
    private void initDate() {
        imagePath = new ArrayList<>();
        if(name.equals("jiangnanshouxi")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100w12000000rqfsv7539.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100k12000000rol3f5020.jpg");
        }
        if(name.equals("meilugongguan")){
            imagePath.add("https://imgpicture.kan3721.com/img/201611/27/12-01-06-64-1.jpg");
            imagePath.add("https://imgpicture.kan3721.com/img/201611/27/12-01-30-53-1.jpg");
        }
        if(name.equals("songhelou")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100k160000010lf6x1F48.jpg");
            imagePath.add("https://pic.ntimg.cn/file/20180702/12398452_091419021000_2.jpg");
        }
        if(name.equals("xinmeihua")){
            imagePath.add("https://tse4-mm.cn.bing.net/th/id/OIP-C.coCdfcacA7EqaxFz8CXr9wHaEK?pid=ImgDet&rs=1");
            imagePath.add("https://tse2-mm.cn.bing.net/th/id/OIP-C.2DI-vW9EDWUyQslWmRO30QHaEK?pid=ImgDet&rs=1");
        }
        if(name.equals("jiangnanzao")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.1ddab104e0d962e29ec2e7490950946f?rik=B%2fidlsvQsDDMsg&riu=http%3a%2f%2fjintangjiang.oss-cn-beijing.aliyuncs.com%2fimages%2f8bbbf911d56e8fc45246660fce91ef368f800b86.jpg&ehk=Co2x9aC9YvSns%2fIMsaIxKMfKs5UQEBYDCY72zAVZ7qI%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://tr-osdcp.qunarzz.com/tr-osd-tr-manager/img/cea01e1e0630ee54a7b7ff8a94e09459.jpg_r_640x853x70_795f9404.jpg");
        }
        if(name.equals("minguohonggongguan")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.5c6b14cabe6db22611cd7b99eea2e9ea?rik=ECg%2bEIaIiA4c2w&riu=http%3a%2f%2fblogimg.id-china.com.cn%2f2017%2f11%2f15%2f430ac5f3a3ad4b158f8539f82d50708b.jpg&ehk=2XpWaOwiMnY%2bEgfsQcQSWvKxGqA6tgF%2b%2fMJz9%2b9yikg%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://tr-osdcp.qunarzz.com/tr-osd-tr-space/img/7ab9fd129a88a4e3e0f08bc2a8f88c6d.jpg");
        }
        if(name.equals("tongqinglou")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.4dbd7887751d9e22edd9a5d7a10327ff?rik=g7DSoND2isb8ow&riu=http%3a%2f%2fimg3.winshang.com%2fUpload%2fbrand%2f2017%2f2%2f22%2f131322011801898309.jpg&ehk=ys66C8eG72oLm8CE79r%2bz7D8LsW5kq8aVXEFM3Hqxx0%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://pic1.zhimg.com/v2-8e09fc1cc8f967139a5ed67ef4f772cc_1440w.jpg?source=172ae18b");
        }
        if(name.equals("nanjingdapaidang")){
            imagePath.add("https://youimg1.c-ctrip.com/target/10091a0000018yu5rB8CA.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.0d4bd1254e3e3fdc0d00917e6d4a765c?rik=ZzuXsWpqVvEHUA&riu=http%3a%2f%2fimg1.qunarzz.com%2ftravel%2fd8%2f1607%2fdc%2fbf667ec46ac210b6.jpg_r_1024x683x95_2fe829f9.jpg&ehk=MaoKQmuBZuChQy%2b6QmLWxcYa0MFmQ824UlDd3HD53ac%3d&risl=&pid=ImgRaw&r=0");
        }

        if(name.equals("quyuanchashe")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.4859043b27863beb96860c0f7d1ca1cd?rik=%2b1rTA7DF9S%2ft1g&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f10090k000000cl5zu0DF0.jpg&ehk=KnjFIyqQCE52XyZJCBvpeM8U%2fRehjbaSMQo2zpTGg2s%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://youimg1.c-ctrip.com/target/100i0y000000m2w7v7B45.jpg");
        }
        if(name.equals("yechunchashe")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100b11000000r83p489B3.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/0101g120006zb8t1nE2FF.jpg");
        }
        if(name.equals("yangzhouyan")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.afe00c60ca980fe9d27a8248407b6db0?rik=SjtEsn8psfORLg&riu=http%3a%2f%2f5b0988e595225.cdn.sohucs.com%2fimages%2f20191010%2fdc6f9864598242b2953e80196a690c0b.jpeg&ehk=WOpsRp4Ha5P8951r71uQGbjFItfGlfcR%2byfbvtDPTP4%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://tr-osdcp.qunarzz.com/tr-osd-tr-space/img/c32857a0af8cf26809ee21c60266c9c1.jpg_r_680x453x95_3761d047.jpg");
        }
        if(name.equals("yingbinguan")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100w1c000001ckx3i6305.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.e1cee7ef3c67abf730bec0fe98a9dfae?rik=JJDXCIOJzwP9mA&riu=http%3a%2f%2fn.sinaimg.cn%2fsinakd2021316s%2f799%2fw960h1439%2f20210316%2fba93-kmkptxe1023517.jpg&ehk=gzFHFQH8J7pqh81xtsSSc3I9qKvNF%2ftb1xH1VOtfUN8%3d&risl=&pid=ImgRaw&r=0");
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
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
          MyViewHolder holder = new MyViewHolder(LayoutInflater.from( MeishiActivity.this).inflate(R.layout.recycle_meishi,parent,false));
            return holder  ;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.nameone.setText(name1[position]);
            Glide.with(MeishiActivity.this).load(image[position]).into(holder.iv);
            holder.troduce.setText(introduces[position]);
            holder.buy.setText(buy[position]);

        }
        @Override
        public int getItemCount() {
            return name1.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView nameone;
            ImageView iv;
            TextView buy;
            TextView troduce;
            Button yuding;
            public MyViewHolder(View view){
                super(view);
                nameone = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.tupian);
                buy=(TextView)view.findViewById(R.id.buy);
                yuding = view.findViewById(R.id.yuding);
                troduce =(TextView) view.findViewById(R.id.chuangziliao);
            }
        }
    }
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
            Toast.makeText(MeishiActivity.this, "You have not installed Baidu Map", Toast.LENGTH_LONG).show();
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
                    "&keywords=" +nameadd+
                    "&dev=0");

            intent.setData(uri);

            //启动该页面即可
            startActivity(intent);
        } else {
            Toast.makeText(MeishiActivity.this, "You have not installed Gaode Map", Toast.LENGTH_LONG).show();
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
