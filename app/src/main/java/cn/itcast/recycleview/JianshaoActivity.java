package cn.itcast.recycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class JianshaoActivity extends AppCompatActivity {
    int imageone;
    String nameone;
    String buyone;
    String troduceone;
    String name_receive;
    private RecyclerView mRecycleview;
    private HomeAdapter mAdapter;
    private String[] images1 ;

    private String[] introduces1;

    private String[] yzimages1={"https://youimg1.c-ctrip.com/target/100611000000qbsoy001C.jpg",
            "https://youimg1.c-ctrip.com/target/100q11000000qcgpg2F48.jpg",
            "https://p1.ssl.qhimg.com/t0131880d923b0e9c5e.jpg",
            "https://youimg1.c-ctrip.com/target/0103l120005v781o08CE2_D_10000_1200.jpg?proc=autoorient"
    };
    private String[] yzintroduces1 ={" Slender West Lake, formerly known as Guarantee Lake, is a tributary of the Yangzhou section of the Beijing-Hangzhou Grand Canal. It is located at No. 28, Dahongqiao Road, Hanjiang District, Yangzhou City, Jiangsu Province, with a water area of 700 mu. Slender West Lake is made up of the ancient Sui and Tang Grand Canal system and the moat of the Sui, Tang, Song, Yuan, Ming and Qing dynasties. Slender West Lake in the Qing Dynasty Kangqian period has formed a basic pattern.",
            " Slender West Lake is a typical small shallow lake, and its source water comes from the Beijing-Hangzhou Grand Canal.Slender West Lake belongs to the Yangzhou section of the Beijing-Hangzhou Grand Canal and is a tributary of the Yangzhou section of the Beijing-Hangzhou Grand Canal. The Beijing-Hangzhou Grand Canal is the main channel of North-South water transportation in ancient China.",
            "Slender West Lake belongs to the transition zone from subtropical monsoon humid climate to temperate monsoon climate. It has four distinct seasons, sufficient sunshine and abundant rainfall, and the prevailing wind direction changes significantly with the season.",
            "Slender West Lake garden architecture has typical Buddhist cultural features, such as the famous Slender West Lake White pagoda is a temple tower, a typical Lamaism temple tower system. The Lotus Bridge of Slender West Lake, commonly known as the Five Pavilion Bridge, is the symbol of Slender West Lake."
    };

    private String[] lianimages1 ={
           "https://bkimg.cdn.bcebos.com/pic/6a600c338744ebf899ff903fdef9d72a6059a720?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto",
           "https://bkimg.cdn.bcebos.com/pic/faf2b2119313b07e5433a4080bd7912397dd8c20?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto",
           "https://bkimg.cdn.bcebos.com/pic/77094b36acaf2edd0c7fbde98a1001e939019320?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto"
   };
    private String[] lianintroduces1 = {
           "        Huaguo Mountain, located in the middle foot of Yuntai Mountain in the south of Lianyungang City, Jiangsu Province, was called Cangwu Mountain in the Tang and Song dynasties, also known as Qingfeng Peak, the main peak of Yuntai Mountain Range, and the highest peak of Jiangsu Mountains. Huaguo Mountain has 136 peaks, and the main peak of Huaguo Mountain is the Jade Girl Peak, with an altitude of 624.4 meters.",
           "        The geological formation of Huaguo Mountain is closely related to the tectonic movement and erosion of the earth's crust, and has experienced a long geological history and the influence of natural action, forming a unique landform landscape, and the landform of Huaguo Mountain in the scenic area is composed of red granite, with steep mountains and peculiar shapes.",
           "        The scenic area where Huaguo Mountain is located has been named as a national key scenic spot, a national AAAAA tourist area, and a national geological park."};

    private String[] nantongimages1={
            "https://bkimg.cdn.bcebos.com/pic/0b46f21fbe096b6303d724fc0e338744eaf8ac54?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto",
            "https://bkimg.cdn.bcebos.com/pic/2934349b033b5bb550adafd635d3d539b600bca4?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto" ,
            "https://bkimg.cdn.bcebos.com/pic/6c224f4a20a4462392b30d489b22720e0cf3d79b?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto"};
    private String[] nantongintroduces1 = {
            "        The formation of the five Mountains has been 350 million to 400 million years ago, [2] The Han Dynasty \"Literature General Examination\" record: Hailing, Han County, there is Wolf Mountain. [3] Huangnishan (29.3 meters), Ma 'anshan (49.4 meters), Wolf Mountain (104.8 meters), Jianshan (80.5 meters) and Junshan (108.5 meters) cover an area of 0.728 square kilometers.。",
            "        The five mountains in the Wolf Mountain Scenic Area were formed by strong crustal changes and sea and land changes, which have a history of 350 million to 400 million years, 1 million years earlier than the Himalayas. 7,500 years ago, Wolf Mountain was still a small island in the vast sea.",
            "        The cliff on the north slope of Wolf Mountain was formed during the Yanshan Movement 150 million to 70 million years ago. Nanding is a single mountain, with rock strata inclined to the southeast and an inclination of about 15°."};

    private String[] njimages1={"https://img.zcool.cn/community/010ade5ae1d918a80120927b43dd8f.jpg@1280w_1l_2o_100sh.jpg",
           "https://ts1.cn.mm.bing.net/th/id/R-C.e73f837220394ccf7fb2da9ed2f82c44?rik=Qdfy2IAbq7OqPg&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2f100g0a0000004x76gB512.jpg&ehk=ruOytQB80zqxbMvvkOOdBBaouqgpmuEUY2T4gYK%2fbKs%3d&risl=&pid=ImgRaw&r=0",
            "https://img.zcool.cn/community/01db7755497da90000019ae907bae8.jpg@1280w_1l_2o_100sh.jpg",
            "https://img.zcool.cn/community/01eafb554b9ee7000001bf72265f2a.jpg@1280w_1l_2o_100sh.jpg"};
    private String[] njintroduces1 = {"Xuanwu Lake is located in Xuanwu District, Nanjing City, Jiangsu Province, with Zijinshan Mountain in the east, Ming City Wall in the west, Nanjing Railway Station in the north, and Zhoushan Mountain in the south. It is the largest urban park in the south of the Yangtze River, the largest royal garden lake in China, and the only royal garden in the south of the Yangtze River. ",
            "It is known as the 'Pearl of the Jinling', also known as the Back Lake and the North Lake. The cultural history of Xuanwu Lake can be traced back to the pre-Qin period. During the Six Dynasties, it became a place for the emperor to read the sailors and was turned into a royal garden. On the south bank, there were royal palaces such as Hualin Garden and Leyou Garden. In the Northern Song Dynasty, Wang Anshi, the governor of Jiangning Prefecture, 'returned the abandoned lake to the field', and the Xuanwu Lake disappeared for more than 200 years.",
            "In the Yuan Dynasty, after two dredging, Xuanwu Lake reappeared; In the Ming Dynasty, it was set up as the Houhu Yellow Book Library, which was the royal forbidden land. At the end of the Qing Dynasty, when the Nanyang persuasion meeting was held, Fenrun Gate (now Xuanwu Gate) was opened, and Xuanwu Lake became a tourist area. In August 1928, Xuanwu Lake was officially opened to the public as a park." ,
            "Xuanwu Ten scenery formed in the Six Dynasties, around the Xuanwu Lake distribution, some located on the lake. Xuanwu ten scenic spots are the most representative of Xuanwu Lake, Xuanwu Lake tour is the most important node, respectively: five continents spring dawn, Lv Yuan Xin Feng, Lotus Lake evening singing, Taicheng Yanliu, ancient pagoda setting sun, nine Hua Zhaohui, chicken singing evening bell, West Dike autumn moon, moon lake Shengsong, ancient wall mirror. "};

    private String[] Wuximages1={"https://bkimg.cdn.bcebos.com/pic/d31b0ef41bd5ad6e29d3893c8bcb39dbb6fd3c3a?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto",
            "https://bkimg.cdn.bcebos.com/pic/902397dda144ad34598227e16ff01bf431adcbef2f00?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto",
            "https://bkimg.cdn.bcebos.com/pic/342ac65c10385343fbf238462c41a77eca8065382203?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto",
            "https://bkimg.cdn.bcebos.com/pic/d8f9d72a6059252df9e10c6c349b033b5ab5b9e7?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2UxMTY=,g_7,xp_5,yp_5/format,f_auto"};
    private String[] Wuxintroduces1 = {"        Wuxi Turtle Head Scenic Area is a peninsula located within the boundaries of Wuxi on the northwest shore of Taihu Lake, surrounded by water on three sides.",
            "        Turtle Head Islet has the largest scale and variety of iris flowers in the field of gardening in the East China region. In May, large areas bloom with flowers of the iris family, such as Iris lactea and yellow iris.",
            "        Wuxi Renjie Garden is located amidst the cherry blossom forest at the foot of Luding Mountain. It consists of the Renjie Hall, sculptures, exhibition hall, and more, showcasing the life achievements of over 60 Wuxi sages.",
            "        At the western part of the scenic area, there is a natural water bay, adorned with the Wavelength Bridge and a winding embankment. When the lake breeze picks up, water splashes, resembling a flurry of snowflakes in the air.。"};

    private String[] szimages1={"https://ts1.cn.mm.bing.net/th/id/R-C.d709ce85785ad406e1aefbe717ee734e?rik=aTuyWVXFaT5%2bCA&riu=http%3a%2f%2fseopic.699pic.com%2fphoto%2f50080%2f9994.jpg_wh1200.jpg&ehk=0roWcPT%2fm3pydtNZzgVqTwP6Gm5oxoQ%2bAG9dC2yUzbY%3d&risl=&pid=ImgRaw&r=0",
            "https://ts1.cn.mm.bing.net/th/id/R-C.be9cb14851cc31b6bf005dca8adeaf9e?rik=UcAaSXy53VEPlQ&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ffd%2ftg%2fg3%2fM01%2f1C%2f0D%2fCggYGVYk44CAMYdDAB2fPyseKzQ376.jpg&ehk=lnywQw5VHGGde6A4DMAZOkmoQBSLk9%2bE0rdwgkC8%2b84%3d&risl=&pid=ImgRaw&r=0",
            "https://ts1.cn.mm.bing.net/th/id/R-C.87b45f3d6d2bfe5155916a77e7ad4473?rik=CCvnHOyT3bytsQ&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fphotoblog%2f7%2f2%2f2%2f2%2f7222156%2f20103%2f21%2f1269174599324.jpg&ehk=obnM8XMPTk%2bO9P428NM6LY%2fo197XAcG%2fYGHIFJqTWp0%3d&risl=&pid=ImgRaw&r=0",
            "https://youimg1.c-ctrip.com/target/100m0b0000005zd4q7476.jpg"
    };
    private String[] szintroduces1 = {"Humble Administrator's Garden covers an area of 78 mu, the whole garden is divided into three parts: east, middle and west. The east is bright and cheerful, with Pinggang distant mountains, pine forest lawn, bamboo dock and Qu Shui. The western pool is in a curved shape, which is characterized by Taiwan hall Zhi, corridor ups and downs, water reflection, unique interest, gorgeous and exquisite decoration, the main building is near the residential side of the 36 mandarin duck hall.",
            "Suzhou Garden Museum, founded in 1992, is the first garden museum in China. On December 4, 2007, the new garden Museum was completed and opened to the public. The new museum is close to the Garden of the Noble Administrator, covering an area of 3205 square meters, with a construction area of 3390 square meters. The garden museum has five exhibition halls, such as orderly hall, garden history, garden art, garden culture and garden inheritance. Taking the famous gardens of Suzhou in past dynasties as an example, it shows visitors the rich connotation and artistic charm of Suzhou classical gardens.",
            "The Humble Administrator's Garden has forty-five plaques, twenty-two pairs of couplets, and eighteen tiles on the door. The relevant materials have been lost: 24 tablets, 25 pairs of couplets, four brick carvings. These frontal couplets, many from the hands of famous, elegant, exquisite calligraphy, itself has a high historical and artistic value. They reflect well with the garden and add poetry to the whole garden. At the same time, they also provide rich materials for studying the historical changes of garden layout and the development and change of garden art.",
            "In 1961, the Humble Administrator's Garden was announced by The State Council of the People's Republic of China as the first batch of national key cultural relic protection units. In December 1997, as a typical example of classical gardens in Suzhou, with the approval of UNESCO, the Tiny Administrator's Garden was included in the World Heritage List together with Lingering Garden, Master of the Net Garden and Huanxiu Villa."


    };

    private String[] xiaimages1={"https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E6%99%AF%E7%82%B9%E4%BB%8B%E7%BB%8D/xia1.jpg"};
    private String[] xiaintroduces1 = {"        罗汉溪，源于福建省霞浦县柏洋乡洋里土勃头村，呈西北—东南流向。溪流经横江、溪西、洋沙溪，于下村汇集桐油溪支流，经百步溪，出水磨坑、大桥头入后港海。其主要支流桐油溪，源于水门乡百笕村，流域面积42平方公里，河道长度17公里。罗汉溪是霞浦县三大河流之一，蕴含丰富的水资源，是霞浦县最重要的集储水发电、农业灌溉、城市饮用水为一体的水源河流。罗汉溪又是具有独特山水景色的河流，第四纪冰川遗迹冰臼、巨石滩、峡谷、岩崖瀑布、两岸山景，绘就秀丽的山水画卷，有着重要的旅游观光价值。"};

    private String[] zheimages1={"https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E6%99%AF%E7%82%B9%E4%BB%8B%E7%BB%8D/zhe1.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E6%99%AF%E7%82%B9%E4%BB%8B%E7%BB%8D/zhe2.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E6%99%AF%E7%82%B9%E4%BB%8B%E7%BB%8D/zhe3.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E6%99%AF%E7%82%B9%E4%BB%8B%E7%BB%8D/zhe4.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E6%99%AF%E7%82%B9%E4%BB%8B%E7%BB%8D/zhe5.jpg"};
    private String[] zheintroduces1 = {"        柘荣鸳鸯头草场位于省级风景名胜区东狮山南侧，海拔980米至1110米之间，距柘荣县城约5公里。",
            "        这是一片面积约5000亩，四周被阔叶和针叶混交林包围的草场。周边的山峰巍峨挺拔，充满阳刚之壮美。草场核心区的山岚远远望去，又如人工泥塑的微型盆景。草山低矮，绵延起伏;山脊走势，富有韵律，节奏中蕴含着温文典雅之美妙!",
            "        站在草原的高处\"鸳鸯峰\"顶环顾四周，向东可望见太姥山与茫茫东海水天相接。如果遇到天朗气清的早晨，还可以看到旭日从海平面冉冉升起的难得景象。向南可探视柘荣与霞浦交界的高峰\"目海尖\"(海拔1192.4米)的全貌，再远处则是台湾海峡浩渺的烟波。向西望去，近可观柘荣南山(海拔1301.7米)，远能眺望群山绵延之中的世界地公园\"白云山\"(海拔1450.2米)。向北可见太姥山脉主峰\"东狮山\"(海拔1479米)云蒸霞蔚景象。",
            "        鸳鸯头草原四季风景各具魅力。春天，绵延的草丛中绽放着淡紫色、红色和白色的杜鹃，与漫山遍野的野花竞相争艳，引来众多的蜜蜂和彩蝶尽情飞舞。置身其中，能使人心花怒放。夏天，翠绿的山岗与蓝天白云融为一体，放眼望去，处处都是天然画卷，令人心旷神怡。秋天，此起彼伏的草甸开着银白色的草花，在微风吹拂下，摆动着修颀窈窕的身姿。冬天，泛红的草山与四周绿色的阔叶和针叶混交林带，形成了鲜明的色彩对比。如果降下一场瑞雪，还会使草原披上银装，其乐更无穷。",
            ""};
    TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianshao);
        Intent intent = getIntent();
        imageone = intent.getIntExtra("image",0);
        nameone = intent.getStringExtra("name1");
        buyone = intent.getStringExtra("buy1");
        troduceone = intent.getStringExtra("troduce");
        name_receive= intent.getStringExtra("name");//景点地区
        text2 = findViewById(R.id.text2);
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
                intent.setClass(JianshaoActivity.this,DetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if(name_receive.equals("Yangzhou")){
            images1 = yzimages1;
            introduces1 = yzintroduces1;
            text2.setText("Shouxihu");
        }
        if(name_receive.equals("Lianyungang")){
            images1 = lianimages1;
            introduces1 = lianintroduces1;
            text2.setText("Mount Huaguoshan");
        }
        if(name_receive.equals("Nantong")){
            images1 = nantongimages1;
            introduces1 = nantongintroduces1;
            text2.setText("the Wolf Mountain");
        }
        if(name_receive.equals("Nanjing")){
            images1 = njimages1;
            introduces1 = njintroduces1;
            text2.setText("Xuanwuhu");
        }
        if(name_receive.equals("Wuxi")){
            images1 = Wuximages1;
            introduces1 = Wuxintroduces1;
            text2.setText("Yuantouzhu");
        }
        if(name_receive.equals("Suzhou")){
            images1 = szimages1;
            introduces1 = szintroduces1;
            text2.setText("Zhuozhengyuan");
        }
        if(name_receive.equals("霞浦县")){
            images1 = xiaimages1;
            introduces1 = xiaintroduces1;
            text2.setText("罗汉溪风景区");
        }
        if(name_receive.equals("柘荣县")){
            images1 = zheimages1;
            introduces1 = zheintroduces1;
            text2.setText("柘荣鸳鸯草场景区");
        }
        if(name_receive.equals("周宁县")){
            images1 = yzimages1;
            introduces1 = yzintroduces1;
            text2.setText("九龙漈风景区");
        }
        mRecycleview = findViewById(R.id.recycle);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeAdapter();
        mRecycleview.setAdapter(mAdapter);
    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           MyViewHolder holder = new MyViewHolder(LayoutInflater.from(JianshaoActivity.this).inflate(R.layout.jianshao_recycle,parent,false));

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Glide.with(JianshaoActivity.this).load(images1[position]).into(holder.iv);
            holder.introduces1.setText(introduces1[position]);
        }

        @Override
        public int getItemCount() {
            return images1.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView introduces1;
            ImageView iv;
            public MyViewHolder(View view){
                super(view);
                iv = (ImageView) view.findViewById(R.id.iv);
                introduces1 = view.findViewById(R.id.introduce);
            }
        }
    }
}
