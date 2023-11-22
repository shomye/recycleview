package cn.itcast.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by DELL on 2022/10/10.
 */

public class OneFragment extends Fragment {
  //苏州市
    private String[] suzname = {"kaibinsiji","shizun","wendemu","zhongyinhuangguan"};
    private  int[]  szicons = {R.drawable.kaibinsiji,R.drawable.shizun,R.drawable.wendemu,R.drawable.zhongyinhuangguan};
    private String[] szbuy = {"¥400","¥450","¥500","¥600"};
    private String [] szintroduce = {"Kempinski Hotels is the world's oldest luxury hotel group. Founded in 1897 in Germany, Kempinski Hotels now has 76 luxury hotels and resorts in 34 destinations in Europe, the Middle East, Africa, the Americas and Asia, including Beijing, Berlin, Budapest, Istanbul, Dresden and St. Moritz.",
            "The hotel has 308 luxurious and comfortable guestrooms, and the distinctive Chinese and Western restaurants allow you to enjoy global cuisine and authentic Chinese cuisine. The spa, heated swimming pool, lobby bar, rooftop garden and business club provide you with a peaceful oasis in this bustling city. 9 meeting rooms and 1300 square meters of pillarless grand ballroom, is your meeting, wedding and banquet celebration of the perfect choice",
            "Wyndham Hotels & Resorts is a premium brand of Wyndham Hotels & Resorts, one of three subsidiaries of Wyndham Global Inc. (NYSE: WYN). Wyndham Hotel Group [1] is the world's largest * and most diversified hotel company, operating 15 brands in 71 countries, with approximately 7,590 hotels and 655,300 rooms. In addition to Wyndham hotels and resorts, there are also household comfort hotel brands -- Ramada, Days Inn, Super 8 hotels and Howard Johnson hotels",
            "Crowne Plaza Suzhou [1] is an international business hotel that truly understands and can meet the needs of business travel. In addition to providing well-equipped business facilities and thoughtful and convenient conference services, there are also many leisure and entertainment facilities designed for you to relax your nervous business trip. Croque-plaza Suzhou is located in the eastern part of the city by the beautiful Jinji Lake in Suzhou Industrial Park, surrounded by picturesque scenery, comfortable and quiet environment. At the same time, the traffic is also very convenient, driving only 10 minutes to the city center, only two hours from Shanghai. It makes you feel comfortable and relaxed."  };
//南京市
    private String[] nanjname = {"lisikaidun","yihegongguan","xiangzhanghuaping","hanbilou"};
    private  int[]  njicons = {R.drawable.lisikadun,R.drawable.yihegongguan,R.drawable.xiangzhanghuaping,R.drawable.hanbilou};
    private String[] njbuy = {"¥350","¥400","¥600","¥500"};
    private String [] njintroduce = {"Ritz-Carlton Hotels (Ritz-Carlton) is a premium hotel and resort brand, distributed in 24 countries in major cities, headquartered in Maryland, USA, very close to Washington, D.C. The Hotel is managed by Marriott International's Ritz-Carlton Hotel Company, which currently employs more than 38,000 people and owns more than 70 hotel properties.",
            "Nanjing Yihe Residence is located in the east of Yihe Road Residence area in Gulou District of Nanjing, which is the 12th area of Yihe Road residence area. It covers a total area of about 20,000 square meters, accounting for about one-tenth of the whole residence area. It has 26 villas of different styles in the Republic of China, all of which are carefully renovated and have profound historical and cultural information. In 2014, Yihe Residence was awarded the UNESCO 2014 Asia Pacific Cultural Heritage Conservation Honor Award.",
            "Lijiang Camphor Huaping Hotel Co., Ltd. was established on July 13, 2010.",
            "Hanbilou is located in the Hanbilou Peninsula of the Sun Moon Lake. In 1916, the Japanese ITO was enchanted by the beauty of the Sun Moon Lake and built a villa in the Hanbilou Peninsula, named \"Hanbilou\". In 1919, Hanbi Building was converted into a two-story building and became an official guest house. In 1949, Chiang Kai-shek to the Sun Moon Lake, the sense of its ethereal force, then set the Hanbi building for the personal museum."};

   private String[] yangzname = {"jingjiangjiudian","jinghuajiudian","sijijiudian","xianggelila"};
    private  int[]  yzicons = {R.drawable.jingjiangjiudian,R.drawable.jinghuajiudian,R.drawable.sijijiudian,R.drawable.xianggelila};
    private String[] yzbuy = {"¥300","¥400","¥350","¥500"};
    private String [] yzintroduce = {"Jingjiang Hotel is located by the Jinjiang River in the center of Chengdu, Sichuan Province. It was built and opened in 1960. As a guest house of Sichuan Province, it has long undertaken the senior reception task of Sichuan Provincial Party Committee and provincial government.",
            "The hotel is located in the center of Yangzhou, renovated in 2003, 17 floors high, a total of 242 rooms (sets). Spring breeze ten miles, bamboo west best place. Yangzhou Jinghua Hotel is located in the center of Yangzhou city, which is simple and elegant. It is located on Wenchang Road, the most prosperous commercial street in Yangzhou and the historic corridor known as \"Tang, Song, Yuan, Ming and Qing Dynasties, from ancient to modern\". Three hundred meters to the north, is the beautiful Slender West Lake, east facing after a thousand years of wind and moon Tang Tower, ancient trees, Wenchang Pavilion. Yangzhou's main government organs, financial, commercial, cultural and educational units are within a kilometer. Good geographical location is its natural advantage. Nearby cities: Shanghai about 3 hours by car, Nanjing about 1 hour, Suzhou about 2.5 hours by car, Wuxi about 2 hours by car, Zhenjiang about half an hour by car.",
            "Four Seasons Hotels is an international luxury hotel management group, headquartered in Toronto, Canada. Founded in 1961 by Mr. Isadore Sharp, Four Seasons Hotels has more than 90 hotels and resorts in nearly 40 countries, and more than 60 hotel development projects are in the pipeline. Four Seasons has been rated as one of the world's best hotel groups by Travel + Leisure magazine and Zagat Guide.",
            "Shangri-la Hotel Nanning is located in the CBD core area of Nanning [2],  Guangxi China Resources Building, the tallest building in Guangxi with a total height of 403 meters, is designed by a number of architectural and interior design companies, inspired by the rich and colorful arts and crafts of Zhuang people, and referring to the living experience and spatial layout of traditional courtyard houses. Combined with modern urban fashion elements, the unique \"Ganlan culture\" theme Loft suite is created. In order to create an immersive experience of Zhuangxiang style, the Zhuangxiang culture is combined with the elements of \"green city\" Nanning, the respect and love for the national culture are shown in detail, and the artistic level of new memories of the hometown is presented, which is also the landmark building in the southwest area. The whole building has a total construction area of about 265,000 square meters, 86 floors above ground, with commercial areas, office areas, hotel areas and other areas, has introduced more than 20 world top 500 enterprises, and contributes more than 1 billion yuan of tax revenue to Nanning city every year, which is the \"billion yuan tax demonstration building\" of Nanning City."
    };
    //Wuxi City
    private String[] wuxname = {"Wuxi Junlan Boutique Hotel","The Marriott Hotel Wuxi Luneng","Sheraton Wuxi Binhu Hotel","Hilton Wuxi Hotel"};
    private  int[]  wuxicons = {R.drawable.junlanjingpin,R.drawable.marriott,R.drawable.xilaideng,R.drawable.hilton};
    private String[] wuxbuy = {"¥212","¥410","¥156","¥320"};
    private String [] wuxintroduce = {"Wuxi Junlan Boutique Hotel is a premium hotel specializing in accommodation services. It is located on Binhu North Road in the eastern part of the city. The hotel rooms are exquisitely decorated, upscale, and equipped with all the amenities you need. Each room reflects the hotel's attention to detail and commitment to providing the best service to every guest. We warmly welcome every guest to our establishment.",
            "The Marriott Hotel Wuxi Luneng is a luxury hotel known for its impeccable service and elegant surroundings. Situated in a prime location, it offers well-appointed rooms and excellent amenities. Whether you're traveling for business or leisure, The Westin provides a comfortable and stylish retreat.",
            "Sheraton Wuxi Binhu Hotel is a top-notch hotel offering a range of modern amenities and elegant accommodations. Located in a scenic area, it provides a comfortable stay with beautiful views and exceptional service.",
            "Hilton Wuxi is a renowned hotel offering a luxurious experience in the heart of the city. With well-furnished rooms and a wide range of facilities, Hilton promises a memorable stay for both business and leisure travelers."};



    //周宁县
    private String[] zhouname = {"格林豪泰酒店","周宁县永盛宾馆","锐思特酒店","周宁龙华大酒店","世纪金源大酒店","周宁河畔小居","周宁东洋溪大酒店"};
    private  int[]  zhouicons = {R.drawable.haotai,R.drawable.yongsheng,R.drawable.ruisitezhouning,R.drawable.longhua,R.drawable.jinyuanzhou,R.drawable.hepan,R.drawable.dongxiyang};
    private String[] zhoubuy = {"¥140","¥49","¥144","¥133","¥124","¥116","¥112"};
    private String [] zhouintroduce = {"格林豪泰宁德市周宁县桥南街商务酒店属于格林酒店集团旗下连锁酒店。格林酒店集团是中国全外资连锁酒店集团之一，酒店品牌包括中高端东方经典-格林东方酒店、中高端商务-格美酒店、中高端时尚-格雅酒店、中高端休闲-格菲酒店、中档经典商务-格林豪泰酒店、中档个性组合-格盟酒店、城市绿洲-青皮树酒店、年轻多彩-贝壳酒店，为客人提供\"超健康、超舒适、超价值、超期望的广月汉务。",
            "周宁宁德永盛宾馆宾馆位于周宁县汽车站对面，交通方便，客房性价比高，住宿环境良好、通风采光较好.欢迎下榻入住",
            "锐思特酒店(周宁汽车站店)地处周宁名仕广场附近，闹中取静，出门即是繁华街市，宁静与繁华一步之遥;酒店周边有名仕广场，仙溪公园，KTV ,超市，吃住行娱乐一应俱全，便利交通。",
            "龙华大酒店位于县城桥南街，地理位置优越,临近步行街与名仕广场，周围娱乐场所与餐饮极为方便。宾馆现拥有多档次客房，客房配有空调、有线电视、24小时热水系统、提供WIFl、互联网，门口有停车场，室内车库，贴心为宾客营造了一个理想的旅游度假居停之所，更致力于精心为每位客人提供一种无尽的生活享受。酒店设施齐全，竭诚欢迎各社会团体、旅游团体及社会各界人士的下榻光临。",
            "酒店装修时尚高雅,设施齐全，环境舒适。是您商务、旅游、休闲、娱乐的理想选择。在服务上，酒店秉承着“客户至上，宾至如的宗旨，让您在住店的同时，感受到\"家\"的温暖;在各类主题体验上，酒店力求将每个房间以逼真和完美的姿态展现在您的面前，让您时时刻刻拥有一种身临其境的感觉。酒店采用了先进及高效的管理模式，让您真正的体验到酒店的与众不同。住酒店不只是简单的解决住宿问题，更在于享受住宿。",
            "周宁河畔小居位于周宁县东洋溪溪边，门口就是步行街，周边餐饮及商店等配套设施齐全，干净整洁。客房干净整洁，宽敞明亮,环境卫生，配套设施齐全，服务周到，方便入住，性价比高，住宿环境、通风采光较好。",
            "周宁宁德周东洋溪大酒店周宁县东洋溪大酒店酒店地处周宁县狮城镇商贸中心，位于狮城镇南街37号，周宁宾馆斜对面，酒店装修时尚，设施齐全，环境舒适。酒店以“宾客至上，服务\"为经营宗旨，采用了科学的经营机制和管理方法，不断追求卓越，得到了社会的认可，无论商务、休闲、娱乐，都是理想之选。"};

  //寿宁县
    private String[] shouname = {"Sheraton Changzhou Fuli Hotel","Changzhou Marco Polo Hotel","Changzhou Marriott Hotel"};
    private  int[]  shouicons = {R.drawable.dihaoshangwu,R.drawable.dadu,R.drawable.boyue};
    private String[] shoubuy = {"¥112","¥110","¥63"};
    private String [] shouintroduce = {
            "The hotel offers a 24 hour front desk, a concierge, and room service, to make your visit even more pleasant. The property also features a pool and breakfast. Guests arriving by vehicle have access to free parking. ",
            "Located in the Xinbei District and next to the famous Dinosaur Park of Changzhou, the Marco Polo Changzhou is surrounded by 15 acres of lush garden and overseeing the Zaojiang River. It is minutes away from Huning Highway (Shanghai – Nanjing) and 15 minutes’ drive to Changzhou train station. ",
            "Situated in CBD area of city Changzhou, 4 km from China Dinosaurs Park, Changzhou Marriott Hotel features air-conditioned rooms and free WiFi access throughout the entire property. ",
                       };

    //连云港
    private String[] lianname = {
            "Huaguoshan Hotel",
            "Sotitel Lianyungang Suning",
            "Zhongyin Mingdu Hotel","Atour Hotel","Jinjiang Inn","Home Inn","Hanting Hotel"};
    private  int[]  lianicons = {R.drawable.huah,R.drawable.soh,R.drawable.mingh,R.drawable.addoh,R.drawable.jinh,R.drawable.homeh,R.drawable.hanh};
//    private  int[]  lianicons = {R.drawable.tianlong,R.drawable.haoledi,R.drawable.nanzhou,R.drawable.huayuan,R.drawable.kuaijie,R.drawable.haixingjingping,R.drawable.huali};
    private String[] lianbuy = {"¥91","¥112","¥145","¥105","¥116","¥105","¥150"};
    private String [] lianintroduce = {
            "Huaguoshan Hotel has convenient transportation, complete room facilities, warm and comfortable layout, and thoughtful service. Rooms clean and tidy, spacious and bright, environmental health, complete supporting facilities, thoughtful service, convenient to stay, cost-effective, accommodation environment, good ventilation and lighting.",
            "Sotitel Lianyungang Suning is a boutique hotel with accommodation products as the king. There are many banks, restaurants and shopping centers around, and it is a prime location for leisure, entertainment and food, with superior geographical location and convenient transportation.",
            "Zhongyin Mingdu Hotel has complete built-in facilities, reasonable layout, warm and comfortable, is your ideal stay warm home!",
            "Addo Hotel has complete built-in facilities, reasonable layout, warm and comfortable, is your ideal stay warm home!",
            "Jinjiang Inn is located in the business center, opposite the long-distance station, at the exit of the highway intersection. The first floor of the hotel sells bus tickets, train tickets and air tickets, and the airport bus stops here. It is very convenient to travel, shopping and leisure.",
            "Home Inn rooms clean and tidy, perfect facilities, warm service. Hotel to \"guests first, service first\" for the purpose of business, make you feel safe, comfortable, convenient, pleasant, to give you a quiet and comfortable living environment.",
            "Fuan Ningde Huali Hotel Fuan Huali Hotel is an economy hotel that provides accommodation room service, located behind the bus station, the traffic is extremely convenient. The hotel has always been adhering to the \"economical, convenient and fast\" service concept, sincerely welcome your visit."};

    //柘荣县
    private String[] zhename = {"柘荣京鼎荣商务酒店","柘荣县富商商务酒店","柘荣幽舍酒店","柘荣县宾馆","柘荣县九华洲宾馆","柘荣东华大酒店","宁德乘峰宾馆"};
    private  int[]  zheicons = {R.drawable.jingding,R.drawable.fushang,R.drawable.youshe,R.drawable.zherongxianbingguan,R.drawable.jiuhuazhou,R.drawable.donghuadajiudian,R.drawable.chengfengbinguan};
    private String[] zhebuy = {"¥113","¥143","¥153","¥234","¥202","¥268","¥73"};
    private String [] zheintroduce = {
            "柘荣京鼎荣商务酒店:2021.6.26重装焕新，升级的是品质，提升的是服务，不变的是价格。我在京鼎荣商务酒店，恭候您的到来!",
            "柘荣县富商商务酒店位于福建省宁德市柘荣县上桥路5-10号，在柘荣县车站附近，出门就可以上104国道，交通非常方便。柘荣县富商商务酒店于2009年开业至今，在2017年1月的时候重新翻新了一遍，房间设施齐全而且新，酒店客房部设在4-6楼，2-3梭定正洽，月电梯，非常适合出差的您。希望能给您带来愉快的入住。柘荣县富商商务酒店竭诚欢迎您的入住",
            "柘荣幽舍酒店位于太子参交易中心门口(二中旁)，酒店极简设计，干净整洁。周边景区:仙屿公园.东狮山风景区.步行可到。交通便利来“柘\"享受，我\"氧\"你。",
            "柘荣县宾馆座落于双城镇河滨西路，位于县城中心位置，靠近县政府，周边配套设施齐全，方便商务人士出行。柘荣是闽浙两省边界贸易点、国务院批准对外开放的全国55个县、市之一，是生态示范区，有“中国太子参之乡”、“中国民间文化艺术之乡\"的美誉。",
            "柘荣县九华洲宾馆是一家电影主题酒店，是提供安全、舒适，让使用者得到短期的休息或睡眠空间的商业机构，按照国家星级标准兴建的集商务、会议、住宿于一体的综合性商务酒店。酒店位于柘荣县双城镇黄金地段，与仙屿公园毗邻，地理位置优越，交通便捷，附件小吃众多。",
            "东华大酒店是由柘荣县联合投资有限公司投资标准设计建造的现代化大酒店，酒店总建筑面积19000平方米，位于生态示范区，有“中具,坐落手风国太子参之乡\"、“中国民间文化艺术之乡”之称的柘荣县，坐落于风景优美的东狮山脚下，毗邻柘荣县人民政府，东依风景名胜太姥山。",
            "宁德乘峰宾馆位于宁德市柘荣县柳城东路7号，柘荣汽车站附近，东师山脚下，交通便利，出行方便，适合出门旅行的您。宁德乘峰宾馆于2013年重新翻修了一遍，房间设施齐全，而且很新，希望能给您带来愉快的入住。宁德乘峰宾馆位于柘荣县繁华的地段，出门购物，享受美食都方便。宁德乘峰宾馆竭诚欢迎您的入住。"};


    //南通市
    private String[] nantname = {
            "Atour",
            "InterContinental Nantong",
            "the modern Holiday Inn Nantong Oasis International",
            "Jinshi International Hotel",
            "Jinjiang Inn",
            "Hilton Garden Inn Nantong Xinghu"};
    private  int[]  nanticons = {
            R.drawable.nanatour,
            R.drawable.nanbin,
            R.drawable.nanlvzhou,
            R.drawable.nanjinshi,
            R.drawable.nantjinjiang,
            R.drawable.nantxierdun};
//    private  int[]  fudingicons = {R.drawable.tailaoshan,R.drawable.fudingaimei,R.drawable.fudingshanshuijiari,R.drawable.jinjiangzhixin,R.drawable.ruisitefuding,R.drawable.aolaidajiudian};
    private String[] nantbuy = {"¥126","¥96","¥133","¥132","¥161","¥122"};
    private String [] nantintroduce = {
            "Located in Nantong 6 miles from Nantong Underwater World Atour Hotel (Nantong Zhongcheng) has air-conditioned accommodations and a restaurant. Free WiFi and a 24-hour front desk are available.",
            "All rooms are equipped with flat-screen TV, minibar and coffee/tea makers. The spacious bathrooms are facilitated with a shower area, an over-sized bathtub and branded bathroom amenities. InterContinental Nantong provides free parking, gym, indoor pool and 24-hour front desk service.",
            "The Modern Holiday Inn Nantong Oasis International features an indoor pool, pampering spa treatments and a sauna. Free parking and 4 dining options are available. ",
            "Welcome to Jinshi International Hotel, a nice option for travelers like you. The rooms offer a minibar and air conditioning, and getting online is possible, as free wifi is available, allowing you to rest and refresh with ease.",
            "Jinjiang Inn Nantong Gongnong Road offers accommodation in Nantong. Guests can enjoy the on-site restaurant. All rooms come with a flat-screen TV with cable channels.",
            "Among the facilities of this property are a restaurant, room service and a 24-hour front desk, along with free WiFi throughout the property."};
    //霞浦县
    private String[] xianame = {"霞浦县锦都宾馆","城市便捷酒店宁德霞浦店","福维尔酒店","锐思特酒店霞浦山河路店","骏怡连锁酒店（霞浦店）","速8酒店霞浦九龙街店","龙云宾馆","霞浦千禧之家假日酒店"};
    private  int[]  xiaicons = {R.drawable.jindujiudian,R.drawable.chengshibianjie,R.drawable.fuweier,R.drawable.ruisitejxiapuxian,R.drawable.junyiliansuojiudian,R.drawable.subaxiapu,R.drawable.longyun,R.drawable.xiapuqianxizhijia};
    private String[] xiabuy = {"¥60","¥148","¥164","¥117","¥160","¥175","¥79","¥129"};
    private String [] xiaintroduce = {
            "酒店位于霞浦县县中心，周边交通便利，特色小吃街，娱乐场所，该店客房整洁，宽敞明亮，环境卫生，配套设施齐全，服务周到，方便入住",
            "城市便捷酒店（霞浦店）位于东吾路，近福宁大道，地段繁华，交通便捷。酒店周边旅游资源丰富，海国桃源―杨家溪及被誉为中国夫丽滩涂的霞浦风光摄影地均只需30分钟车程城市便捷酒店（霞浦店）是城市便捷酒店集团旗下的一家经济快捷精品酒店。",
            "艾美福维尔酒店是福建艾美商业集团有限公司旗下精品酒店位于中国滩涂摄影圣地\"霞浦”，毗邻霞浦客运站、福宁文化公园。酒店整体装饰简约现代，客房布置舒适洁净，多款房型内均配以优质床上用品、豪华淋浴设备、舒适办公桌椅、丰富的电视节目，是出差、旅游的理想选择。",
            "锐思特酒店起源于2006年,是逸柏酒店集团旗下个“舒适商旅型\"品牌。以追求“高性价比的品质\"为品牌的市场定位。多年来，通过高性价比的“投资设计\"及良好的产品体验性能，获得了众多投资者与消费者的认可。作为集团旗下精选商务酒店品牌，锐思特酒店结合家居理念精心设计，以\"时尚、舒适、简约\"为主导，精致配置“高星级床上用品\"、“时尚卫浴\"等酒店核心部件，打造出酒店产品的极致化体验。",
            "骏怡连锁酒店位于霞浦县松城街道龙首路102号，专为商务旅客量身设计，为经常在外旅行并追求舒适、便利住宿的人设计的时尚酒店。酒店紧临龙首山公园、南峰山等景点,便于休闲;同时它又处于美食、购物、文化的中心地带，便于交流。酒店距霞浦火车站5么里，距霞浦汽车站2公里，交通便利。酒店拥有各式客房:标准大床，标准双床，商务大床，豪华套房、商务双人房等50间",
            "速8酒店霞浦九龙街店位于霞浦县新城区商业街中心地带。地处长溪路九龙街商业区，毗邻动车站、福宁长途汽车站、沈海高速路霞浦站进出口，交通便捷。酒店共有70间设施齐全、清新、简雅、明亮的客房。",
            "龙云宾馆地理位置优越，交通便利，出门就可以坐上公交车，亦或者有店家提供电动车，让你在各个景点间自由穿梭，附近还有篮球场、小型电影院、咖啡店、超市等让你的旅行更加便利。客房干净整洁，周边知名小吃一条街，宾馆重新装修，价格优惠，提供景点向导咨询。",
            "霞浦千禧之家假日酒店地处霞浦县城中心地段东吾路与福宁大道交汇处，邻近王龙广场、月波公园，旅游出行便利。这里以精品客房为主，房间设计风格时尚大方，配设空调、液晶电视、国内长途电话、24小时热水、独立卫浴等设施，无线网络让你与外界沟通不中断。"};

    private String[] name1 = {};
    private int[] icons = {};
    private  String[] buy ={};
    private  String[] introduces = {};
    String name_receive1;
    int img;
    int f;
    String ziliao;
    String buy1;
    String name;
    private RecyclerView mRecyclerView;
        private HomeAdapter mAdapter;
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onefragment, container, false);

        Intent intent = getActivity().getIntent();
        f = intent.getIntExtra("f",0);
        name_receive1 = intent.getStringExtra("name");//景点地区
        name = intent.getStringExtra("detail_name");
        ziliao = intent.getStringExtra("detail_introduce");
        buy1 = intent.getStringExtra("detail_buy");
        img =intent.getIntExtra("detail_iv",0);

         if(name_receive1.equals("Suzhou")){
            name1 = suzname;
            icons = szicons;
            buy = szbuy;
            introduces = szintroduce;
        }
       if(name_receive1.equals("Nanjing")){
            name1 = nanjname;
            icons = njicons;
            buy = njbuy;
            introduces = njintroduce;
        }
        if(name_receive1.equals("Wuxi")){
            name1 = wuxname;
            icons = wuxicons;
            buy =wuxbuy;
            introduces = wuxintroduce;
        }
        if(name_receive1.equals("Yangzhou")){
            name1 = yangzname;
            icons = yzicons;
            buy =yzbuy;
            introduces = yzintroduce;
        }
        if(name_receive1.equals("Changzhou")){
            name1 = shouname;
            icons = shouicons;
            buy =shoubuy;
            introduces = shouintroduce;
        }
        if(name_receive1.equals("Lianyungang")){
            name1 = lianname;
            icons = lianicons;
            buy =lianbuy;
            introduces = lianintroduce;
        }
        if(name_receive1.equals("柘荣县")){
            name1 = zhename;
            icons = zheicons;
            buy =zhebuy;
            introduces = zheintroduce;
        }
        if(name_receive1.equals("Nantong")){
            name1 = nantname;
            icons = nanticons;
            buy =nantbuy;
            introduces = nantintroduce;
        }
        if(name_receive1.equals("霞浦县")){
            name1 = xianame;
            icons = xiaicons;
            buy =xiabuy;
            introduces = xiaintroduce;
        }
        mRecyclerView = view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new HomeAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;


    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
          MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.recycle_itemone,parent,false));
            return holder   ;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            holder.iv.setImageResource(icons[position]);
            holder.nameone.setText(name1[position]);
            holder.buy.setText(buy[position]);
            holder.troduce.setText(introduces[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),JiudianActivity.class);
                    intent.putExtra("detail_iv",icons[position]);
                    intent.putExtra("detail_name1",name1[position]);
                    intent.putExtra("detail_buy",buy[position]);
                    intent.putExtra("name",name_receive1);
                    intent.putExtra("detail_introduce",introduces[position]);

                    intent.putExtra("f",f);
                    intent.putExtra("image",img);
                    intent.putExtra("name1",name);
                    intent.putExtra("buy1",buy1);
                    intent.putExtra("troduce",ziliao);
                    startActivity(intent);
                    getActivity().finish();
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
            public MyViewHolder(View view){
                super(view);
                nameone = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.iv);
                buy=(TextView)view.findViewById(R.id.buy);
                troduce =(TextView) view.findViewById(R.id.ziliao);
            }
        }
    }

    }
