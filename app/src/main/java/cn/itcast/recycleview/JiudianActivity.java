package cn.itcast.recycleview;

import android.content.Intent;
import android.content.IntentFilter;
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

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JiudianActivity extends AppCompatActivity {
    String name;
    Button open;
    String nameadd;
    private CommonDialog myDialog;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private DialogOrderTypeFragment mFragment2=new DialogOrderTypeFragment();
    private String[] name1 = {"Standard single room","Standard twin room","Business single room","Deluxe twin room","Deluxe suite"};
    private int[] image = {R.drawable.danrenchuang,R.drawable.shuangrenchuang,R.drawable.busdanren,R.drawable.busshuangren,R.drawable.delsuite};
//    private int[] image = {R.drawable.achuncanguang,R.drawable.shuangchuangfang,R.drawable.youzhidachuangfang,R.drawable.youzhishuangchuangfang,R.drawable.haohuataofang};

    private  String[] buy ={"¥123","¥129","¥177","¥177","¥235"};
    private  String[] introduces = {
            "single bed  18-22 square meters",
            "double bed  18-22 square meters",
            "single bed  24-26 square meters",
            "double bed  24-26 square meters",
            "single bed  44-46 square meters"};
    int imageone;
    String nameone;
    String buyone;
    String troduceone;
    String name_receive;
    int numble=1 ;
    public static String name_one;
    public static String nametype_one;
    public static String name_numble;
    private Banner banner;
    private GlideImageLoader glideImageLoader;
    private List<String> imagePath;
    private List<String> imageTitle;
    private List<Integer> imgs;


    int f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiudian);
        Intent intent = getIntent();
        imageone = intent.getIntExtra("image",0);
        nameone = intent.getStringExtra("name1");
        buyone = intent.getStringExtra("buy1");
        troduceone = intent.getStringExtra("troduce");

        f = intent.getIntExtra("f",0);
        name = intent.getStringExtra("detail_name1");
        final String buy = intent.getStringExtra("detail_buy");
        final  String introdece = intent.getStringExtra("detail_introduce");
        name_receive= intent.getStringExtra("name");//景点地区
        final TextView menpiao = findViewById(R.id.menpiao);
        menpiao.setText(buy);
        TextView jieshouziliao = findViewById(R.id.jieshouziliao);
        jieshouziliao.setText(introdece);

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
                intent.setClass(JiudianActivity.this,DetailsActivity.class);
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
        imageTitle = new ArrayList<>();
        imgs = new ArrayList<>();
         if(name.equals("jingjiangjiudian")){
            imgs.add(R.drawable.dachuangfa);
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.ba8764a68316317c9dfe800bcb4a4927?rik=Kz04VOoMeZiCtw&riu=http%3a%2f%2fpublic.baihe.com%2fhunli%2f2017%2f05%2f25%2f5926a2e169391.jpg%3fstyle%3d1%26w%3d560%26h%3d420&ehk=FLrbDQuOwHCORi5Qk5WtQnpk8ReCKvgmJWEEbbR8x6g%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://uimg.huixiaoer.net/86104222/da123000994d99bb6d11b797a85af832.jpg");



            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");


        }
        if(name.equals("jinghuajiudian")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.b26943b389f205afdc4b1b87a4b014b8?rik=ABczgkNbkBflIg&riu=http%3a%2f%2fpavo.elongstatic.com%2fi%2fHotel870_470%2fnw_000gZ4TG.jpg&ehk=bkTHfpOIkU8qS%2favKEyZnALAdfgnMlGfiSuR%2fu1Y2g8%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.9e64d9f5997649691c934e7dc0fce47a?rik=tFNW3CwrnNB%2f5A&riu=http%3a%2f%2fpavo.elongstatic.com%2fi%2fHotel795_325%2fnw_000dBhcl.jpg&ehk=5hOjo4H%2fHXonL%2bgyDp2mX%2bNdIbU6ftDcFjqeSHel9HM%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1");


            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("sijijiudian")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.032233629ccf63c3d04b080954cc7e36?rik=iCA7onXgyRDabg&riu=http%3a%2f%2fimg1.selfimg.com.cn%2fuedvoguecms%2f2020%2f12%2f14%2f1607928604_85MFX5.jpg&ehk=tjs1EhYI1%2bPJuUjuZLO0lfDQRrpIvNG3tRdjZIuI7E8%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.bb1f47dbd71ec45baa4c56c33e553b51?rik=xSVFHOCH3W0qwQ&riu=http%3a%2f%2fwww.cntgol.com%2fuploads%2fallimg%2f200602%2f1343542Z9-7.png&ehk=COcKI51qrLBUFkhRq2xLUWwjq%2baBc4BhFaRBovZpNk8%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("xianggelila")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.5d4691f9f16e2ff880db0b1e80f02030?rik=1kWZqfRi%2boysew&riu=http%3a%2f%2fimg2.zol.com.cn%2fproduct%2f102_940x705%2f51%2fceR4c2jnICgLw.jpg&ehk=2fmxfKgrJ%2fhG0BGkS%2fJMPqOLqaC%2fX32AIGjmANNw6n8%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://www.badazhou.com/style/images/zhuanti/shangrila/Shangri-la5.jpg");


            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");

        }
        if(name.equals("kaibinsiji")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.f41f0c423b8592db8aac11d56623b323?rik=wHqGuAPFaqd7fw&riu=http%3a%2f%2fwww.hotel1288.com%2ffileimages%2ftimg(12).jpg&ehk=vq%2f2EvjVlBteZmPNTKPJrat2m6RhIr5B7nnJWVE8Pe8%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.1d6c078898eaf499b949e9f0bbd4bd7f?rik=B1Ovq83GfPBmaw&riu=http%3a%2f%2fzuocxc.com%2fUpLoadFile%2f20190926%2f37e7d859-52e1-48a6-a292-81c19212bd80.jpg&ehk=v4%2frthlz1LinyI3FLGdbelqA5yNYHkw2dSY80lYrVAA%3d&risl=&pid=ImgRaw&r=0");


            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("shizun")){
            imagePath.add("https://youimg1.c-ctrip.com/target/100s0u000000jb8ab7A48.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.9a58da48d38847061e075176fbc7ffef?rik=pik6Qq2zEk4Txw&riu=http%3a%2f%2f25740155.s21i.faiusr.com%2f2%2fABUIABACGAAg3MX1-wUomLn2CDCACjjVBg.jpg&ehk=Q8pxMuT%2bMEY%2fkyfj3oifpiH1bbv0vvicqch2Z0mNguY%3d&risl=&pid=ImgRaw&r=0");


            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("wendemu")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.53d2cf0f17e8670ef707a082afae8566?rik=xO8agkoeJ%2bg0%2bg&riu=http%3a%2f%2fwww.kangtaitrip.com%2fuploads%2f2018%2f1114%2f82e152af185534693761318c3558332d_1_833x0.jpg&ehk=cbw%2fQF5xNM%2f2kNCSmSNaJwOH%2fJJVRFMasq6YeWh5zr0%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://cdn2.jinxidao.com/group1/M00/3C/95/oYYBAF3ky0GAQG-hAAzocHbi5xc588.jpg");

            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("zhongyinhuangguan")){
            imagePath.add("https://www.joinin.com.cn/ueditor/php/upload/image/20201218/1608284088389513.jpg");
            imagePath.add("https://uimg.huixiaoer.net/86127016/2a768d1feb0527a513d232c09a88f190.jpg");

            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("lisikaidun")){
            imagePath.add("https://qiniu-cdn0.jinxidao.com/images/productintroduction/2016/2/d8d91e4cdc4045999252425c901a0887.jpg");
            imagePath.add("https://p0.itc.cn/q_70/images03/20210218/eb9c1c6437bb4c9c811b9ce6aec61ab9.png");

            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("yihegongguan")){
            imagePath.add("https://qnimg.zhaibian.com/upload/2022111013/e4b8f733f8190053de023c5b87f949ab.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100r14000000x4tjoA856.jpg");

            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("xiangzhanghuaping")){
            imagePath.add("https://pavo.elongstatic.com/i/Hotel870_470/nw_QPPgk2Kkx2.jpg");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.1b60f6f4b69b184ce2fafe39196292dd?rik=cD7Y8WrW1MFcoA&riu=http%3a%2f%2fm.tuniucdn.com%2ffb2%2ft1%2fG4%2fM00%2fFC%2fCA%2fCii_J1y52yeIJlzLAAFkg3DjCt8AAFXvAHZFqUAAWSb272_w0_h600_c0_t0.jpg&ehk=%2fy4kIrRqEszLPtIPjWP%2fpbrPFNXq3K%2b54uqsjBIQQM4%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
        if(name.equals("hanbilou")){
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.d12fe50bc938ef5cbb6b078274f512ef?rik=VwUTEltODuysgw&riu=http%3a%2f%2fwww.gufengjia.com%2fd%2ffile%2fimages%2f201906281546%2f13-1FGQ20234-50.jpg&ehk=a46CehSEZvHGVth8Klp0hTW6PKhLbtU9OPF0SfAgxfY%3d&risl=&pid=ImgRaw&r=0");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.87280032fe66abbbb995c2edec221d8d?rik=DIMRyi8xDh%2flQg&riu=http%3a%2f%2fd2.cgahz.com%2fd%2ffile%2fueditor%2fimage%2f20170420%2f1492672677734208.jpg&ehk=fMQro7LRUyehTBmzREulXxIJcxKOWKMC9ePQoyNU9Vs%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("Image of hotel");
            imageTitle.add("Image of hotel");
        }
       
        if(name.equals("Huaguoshan Hotel")){
            imagePath.add("https://cf2.bstatic.com/xdata/images/hotel/max1280x900/109834211.jpg?k=fe2d35607e558136791f19dfe304f45ce21c2b292851fa7318957f27ae503faf&o=&hp=1");
            imagePath.add("https://cf2.bstatic.com/xdata/images/hotel/max1280x900/283808630.jpg?k=90f0e2345066774b56c53aa6f532d41723307b26355d2061e86206f4b76a963d&o=&hp=1");
            imagePath.add("https://cf2.bstatic.com/xdata/images/hotel/max1280x900/109833877.jpg?k=f580d8c2e7e5f9105738dfadfe856074c265df44ca5167d746e5339574dade6b&o=&hp=1");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.20dfc8d06909e83cccd818be2f8670b1?rik=e1lBw78gSY60%2bA&riu=http%3a%2f%2fimg1n.soufunimg.com%2fzxb%2f201609%2f13%2f530%2faa824db6dd5f6d568a9a3508dd3c4f92.jpeg&ehk=i%2fNotuidt81PD7ZqNLuBJakA0qZrrucqJ%2fZ3wQgxHoI%3d&risl=&pid=ImgRaw&r=0");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Sotitel Lianyungang Suning")){
            imagePath.add("http://sofitel-lianyungang-suning.31td.com/UploadFiles/2.jpg");
            imagePath.add("http://sofitel-lianyungang-suning.31td.com/UploadFiles/3.jpg");
            imagePath.add("http://sofitel-lianyungang-suning.31td.com/UploadFiles/4.jpg");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }

        if(name.equals("Zhongyin Mingdu Hotel")){
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/72159141.jpg?k=b2d8b51f0a59f40443313c85b4c3b657de46aa8e486b0fa501c0f8501a5bc7ad&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/72159147.jpg?k=ad6a5f9a684cb9c3765fc670efeb69312059d2d1ca2ccf3f04fd83f09308c703&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/72159127.jpg?k=5b34fafe88106dbf449a155233df3c9492a4bf5ae10361a5b3d10219472bafc0&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Atour Hotel")){
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/402255430.jpg?k=39b75ae81641eb97505f0c8ba17f73f6e56b660cebea6c70b39815e65077671f&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/402255425.jpg?k=f4fa6ab8456d9045a9467ad973560424bda3cc6483c7675244231410c7f48d4b&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/402255442.jpg?k=1f87822907dfe7af804b35e3db86069f67fd6732f384248c430afc79cbbeaa6a&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Jinjiang Inn")){
            imagePath.add("http://dimg04.c-ctrip.com/images//200t080000003b04dB4C8_R_550_412.jpg");
            imagePath.add("http://dimg04.c-ctrip.com/images//20050700000025dgxA18C_R_550_412.jpg");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/78782917.jpg?k=04d4effb7e9a09830f72e005bb1caecd045404844b9a242467669059783eb6d2&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Home Inn")){
            imagePath.add("https://images.bthhotels.com/homeinns/020073_a.jpg");
            imagePath.add("https://media-cdn.tripadvisor.com/media/daodao/photo-s/04/51/ff/d2/caption.jpg");
            imagePath.add("https://images.bthhotels.com/image/25c3b950-5cd6-4b0c-bc84-a3a5f833250c.png");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Hanting Hotel")){
            imagePath.add("https://cdn-img.readytotrip.com/t/1024x768/content/1d/df/1ddf603dde686f438178c0b1180c9de8505d3a2d.jpeg");
            imagePath.add("http://dimg04.c-ctrip.com/images//200e080000003db75C21D_R_550_412.jpg");
            imagePath.add("https://cdn-img.readytotrip.com/t/1024x768/content/11/df/11dfab27475b757d6d90e1ef64e9bf747d996d0a.jpeg");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }

        if(name.equals("Atour")) {
            imagePath.add("https://i.travelapi.com/lodging/38000000/37670000/37666700/37666651/79ba584e_z.jpg");
            imagePath.add("https://i.travelapi.com/lodging/38000000/37670000/37666700/37666651/6a293fbf_z.jpg");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1024x720/207694728.jpg?k=d54f02e3c34a4874516ca0220781bbfaf730a4a9f319ce9668aa714a8c7ce95b&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }

        if(name.equals("InterContinental Nantong")){
            imagePath.add("https://tse2-mm.cn.bing.net/th?id=OLC.behAZ8gW/s+pfQ480x360&rs=1&pid=ImgDet");
            imagePath.add("http://dimg04.c-ctrip.com/images//200o0k000000buolg48CD_R_550_412.jpg");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/324802757.jpg?k=5c740981a119db9703ed2b8a934e90647e76543f3ae5a52834c1403518ee6ce4&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }
        if(name.equals("the modern Holiday Inn Nantong Oasis International")){
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1024x720/263334048.jpg?k=d5eab3fec85d95b0e8113a6a882c0f62a546c28e692013be9d38e4741086d0ad&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1024x720/263041325.jpg?k=afc487633b0a33e96924c55f4012f489ff07a7081454d9cfcf07794228305b7b&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1024x720/263334063.jpg?k=97126129e6f47d523154c6e24500d09d1944adede0dc62239db8ef1af0924fe6&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");

        }
        if(name.equals("Jinshi International Hotel")){
            imagePath.add("http://dimg04.c-ctrip.com/images//200w050000000cx2hF8B5_R_550_412.jpg");
            imagePath.add("http://dimg04.c-ctrip.com/images//200b0r000000hga3kDCC1_R_550_412.jpg");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/840x460/134270222.jpg?k=9d66e0a951477d57d6d0311127163eeaaf8cc9045ff13e714aac4504b4d903f8&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }

        if(name.equals("Jinjiang Inn")){
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/78769417.jpg?k=ffb2fbf99138d6f288835208effa75ebff7e2b2e3b9a4c0904dd268333b5e8d9&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/78769424.jpg?k=059b88928afa76bac9850804b34da98cee109cc4675f73404d124db4342bb3ef&o=");
            imagePath.add("https://q-xx.bstatic.com/xdata/images/hotel/max1280x900/78769419.jpg?k=e9efbfa64297990d081a2b99ad98f33e11bfad1fa30d42ae2afe35a1dccfa7d7&o=");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
        }

        if(name.equals("Hilton Garden Inn Nantong Xinghu")){
            imagePath.add("https://tse1-mm.cn.bing.net/th/id/OLC.AIC5tp0ASaQ9dA480x360?&rs=1&pid=ImgDet");
            imagePath.add("https://tse3-mm.cn.bing.net/th/id/OLC.gJffZVCM29UZ4A480x360?&rs=1&pid=ImgDet");
            imagePath.add("https://tse2-mm.cn.bing.net/th/id/OLC.s4KuzwjDPB30Wg480x360?&rs=1&pid=ImgDet");

            imageTitle.add("picture");
            imageTitle.add("picture");
            imageTitle.add("picture");
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
//        banner.setImages(imagePath);
        banner.setImages(imagePath);
        banner.start();
    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
           MyViewHolder holder = new MyViewHolder(LayoutInflater.from( JiudianActivity.this).inflate(R.layout.recycle_jiudian,parent,false));
            return holder   ;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.nameone.setText(name1[position]);
            holder.iv.setImageResource(image[position]);
            holder.troduce.setText(introduces[position]);
            holder.buy.setText(buy[position]);
            holder.yuding.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog=new CommonDialog(JiudianActivity.this,R.style.MyDialog);
                    myDialog.setTitle("Attention！");
                    myDialog.setMessage("Are you sure to book the order?");
                    myDialog.setYesOnclickListener("Confirm", new CommonDialog.onYesOnclickListener() {
                        @Override
                        public void onYesOnclick() {
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        connect.jiudiancreate(name,name1[position],introduces[position],buy[position],numble);
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
                            Toast.makeText(JiudianActivity.this,"Book successfully!",Toast.LENGTH_LONG).show();
                            myDialog.dismiss();
                        }
                    });

                    myDialog.setNoOnclickListener("Cancel", new CommonDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            Toast.makeText(JiudianActivity.this,"Sorry! The book is failed",Toast.LENGTH_LONG).show();
                            myDialog.dismiss();
                        }
                    });
                    myDialog.show();

                }
            });

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
            Toast.makeText(JiudianActivity.this, "You have not installed Baidu Map", Toast.LENGTH_LONG).show();
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
            Toast.makeText(JiudianActivity.this, "You have not installed Gaode Map", Toast.LENGTH_LONG).show();
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


