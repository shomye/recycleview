package cn.itcast.recycleview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2022/8/7.
 */

public class FirstFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private HomeAdaptertwo mAdaptertwo;
    private RecyclerView mRecyclerViewtwo;
    private String[] name = {"Suzhou","Nanjing","Wuxi","Yangzhou","寿宁","Lianyungang","柘荣","Nantong","霞浦","Other"};
    private String[] name1 = {"Suzhou","Nanjing","Wuxi","Yangzhou","寿宁县","Lianyungang","柘荣县","Nantong","霞浦县"};
    private int[] image = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten};
    private Banner banner;
    private GlideImageLoader glideImageLoader;
    private List<String> imagePath;

    private String[] fuding = {"zhonghuamiluyuan","nianhuawan","luzhiguzhen","hanshansi","yuantouzhu","tianmuhu","jurongmaoshan","hongzehu"};
    private String[] fudingoicons = {
            "https://youimg1.c-ctrip.com/target/01005120008a2ribsFD95_D_10000_1200.jpg?proc=autoorient",
            "https://staticfile.tujia.com/upload/TravelArticleContent/day_151120/201511200100365277.jpg",
            "https://img.zcool.cn/community/01f7e75d6e6caba801202f170bbd2b.jpg@1280w_1l_2o_100sh.jpg",
            "https://ts1.cn.mm.bing.net/th/id/R-C.95a22807a75b1a1e8a22723960786f92?rik=uM2qH3%2bSnvtxMQ&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fupload%2fupc%2ftx%2fitbbs%2f1608%2f30%2fc33%2f26284466_1472553586402_mthumb.jpg&ehk=px5wMjIRkdH9mmJqRP3iFclbKHrIO58bpWcoVq7P62o%3d&risl=&pid=ImgRaw&r=0",
            "https://img.zcool.cn/community/0140815ca2ad22a801214168264fc6.jpg@2o.jpg",
            "https://ts1.cn.mm.bing.net/th/id/R-C.6185d40a233840dec6679667fc41de2b?rik=ZaZs5ImkvyLq1g&riu=http%3a%2f%2fimg.pconline.com.cn%2fimages%2fupload%2fupc%2ftx%2fphotoblog%2f1703%2f14%2fc4%2f39427650_39427650_1489461517345.jpg&ehk=eE8SyEo8Bzws4eCojXul%2fl1b7DBijpe8hyiTIBS8z%2bU%3d&risl=&pid=ImgRaw&r=0",
            "https://youimg1.c-ctrip.com/target/100e190000015u2hzAA7B.jpg",
            "https://youimg1.c-ctrip.com/target/100613000000tkqze4C21.jpg"};
    private String[] fudingbuy = {"¥90","free","free","¥20","¥60","¥120","¥80","¥100"};
    private String[] fudingpingfen = {"4.7 points","4.4 points","4.2 points","4.4 points","4.3 points","4.3 points","4.8 points","4.5 points"};

    private String[] fudingintroduce ={
            "China Milu DEER Park was established in 1986, located in Dafeng Milu deer National Nature Reserve in Jiangsu Province, covering an area of 40,000 mu. Up to now, the elk population in Dafeng Moose Reserve accounts for 60% of the total elk population in the world, and has been listed in the list of international important wetlands. It has become the Global Environment Facility (GEF) China Project Demonstration area, China Biodiversity Protection Demonstration base, and national model Nature Reserve. The Chinese MILU DEER PARK IS FULL OF BRANCHES, QUIET BEACHES, red grass and green, and ROE YUE BIRDS SINGING. The natural ecological environment is beautiful, and the MILu deer population and rare birds and beasts rise one after another, forming a spectacular original natural landscape.",
            "Located at No. 68 West Huanshan Road, Binhu District, Wuxi City, Jiangsu Province, 30 kilometers away from the downtown area of Wuxi, Nianhua Bay covers an area of 106.67 hectares with a total construction area of 350,000 square meters [8] [9]. The main attractions include Half Moon Title Day, Xiangyuehua Street, Nianhua Tower and Smile Square.As an important part of Lingshan Buddhist culture, Nianhuawan takes 'Zen' as the theme element in general. The so-called'Zen' refers to a peaceful mind and a healthy life, which is in line with the increasingly fast pace of life and the demand of citizens to live slowly and gain peace of mind. 'Zen' connects the overall Buddhist culture of Lingshan Cultural scenic spot with the dual proposition of travel and vacation, so that Nianhuayuan has the overall positioning of 'the paradise of Oriental Zen life, the world soul holiday garden'.",
            "Luzhi Ancient Town belongs to Wuzhong District of Suzhou City, located in the east of Suzhou City, 18 kilometers west from Suzhou City, 58 kilometers east from Shanghai.Luzhi Ancient town is a water town in the south of the Yangtze River with a history of 2500 years. It has more water, Bridges, lanes, ancient houses and celebrities. The main attractions are Baosheng Temple, Ye Shengtao Memorial Hall, Jiangnan Cultural Park, etc. Luzhi Ancient Town is surrounded by a large number of lakes, ponds and pools. The appearance of the ancient town maintains the characteristics of traditional Chinese architecture. Luzhi has Bridges of different styles, streets paved with stones, and all kinds of beautiful patterns carved on the revetments. The crafts made by the women are of extraordinary creativity, showing the unique value of Luzhi's traditional costumes.",
            "Hanshan Temple is located in Gusu District, Suzhou City, was built in the Southern Dynasty Xiao Liang Dynasty Tianjian period (AD 502-519), the original name \"Miao Lipuming Pagoda Courtyard\". Hanshan Temple covers an area of about 13,000 square meters, with a floor area of more than 3,400 square meters. Hanshan Temple belongs to the Linji Sect of Zen. In more than 1,000 years, Hanshan Temple was destroyed by fire five times (seven times), and the last time it was rebuilt was during the Guangxu period of the Qing Dynasty. In history, Hanshan Temple was one of the top ten famous temples in China. There are many historical sites in the temple, such as the stone inscriptions of Zhang Jishi, the stone statues of Hanshan and Shide, and the fragments of the inscriptions written by Wen Zhengming and Tang Yin.",
            "Wuxi Yuantouzhu Scenic Area, located at No. 1, Islet Zhu Road, Binhu District, Wuxi City, Jiangsu Province, lies on a peninsula on the northwest shore of Taihu Lake, covering an area of 5.39 square kilometers. It is named for the giant rock jutting into the lake in the shape of a turtle holding its head, and is one of the main attractions of Taihu Lake Scenic Area (National Key Scenic Spot). In the fifth year of the Republic of China (1916), Wuxi Yuantouzhu Scenic area was built; After the founding of the People's Republic of China in 1949, the Wuxi Municipal People's Government took over and established \"Yuantouzhu Park\". Poet Guo Moruo praised the poem \"The best place of Taihu Lake, after all, is in the Turtle Head\". Wuxi Yuantouzhu Scenic area, as the largest landscape garden in the south of the Yangtze River, has some famous scenic spots, such as hidden show in the mountains, Yinghui in the deer, Turtle Islet Spring, Hengyun Villa, Wan Lang Roll snow, the true idea of Lake and Mountain, Ten Li Fang Path, Taihu Xiandao, Jiangnan Orchid Garden, Cherry Blossom Valley, Wuxi Renjie Garden, Zhongdu Morning Mist and Guangfu Ancient Temple.",
            "Tianmu Lake is located eight kilometers south of Liyang City, Changzhou, because of Tianmu Mountain, so named \"Tianmu Lake\". Tianmu Lake is a deep water lake (reservoir) that is narrow from east to west and long from south to south. The water depth in the south is about 4 ~ 5m, and the water depth in the north is about 10 ~ 14m. Around Tianmu Lake, there are many historical and cultural sites: Wuyuan Mountain named after Wu Zixu, a famous \"member\" of Chu in the Spring and Autumn period, the reading platform of CAI Yong, a great writer in the Eastern Han Dynasty, Taibai Building, Bao En Zen Temple, the site of the famous Longxing Temple in the Tang Dynasty, \"the first stone arch dam in the world\" and so on. Tianmu Lake area is rich in products, such as \"Shahe cinnamon\" green tea, \"Oolong tea\", \"pearl chestnut\", \"Guiyuan chestnut\", \"casserole fish head\" and so on. Tianmu Lake is known as the \"Pearl of the South of the Yangtze River\" and \"Green wonderland\". The whole area of Tianmu Lake has 300 square kilometers of ecological protection area, and there are two national large reservoirs in the area, Shahe River and Daxi River.",
            "Maoshan, located in the southwest of Jiangsu Province, is the first blessed place for Taoism. It is prominent in Jurong, Dantu, Jintan, Liyang, Lishui and Gaochun, slightly S-shaped trend. It is about 90 kilometers long from north to south and 10 kilometers wide from east to west. The area is about 900 square kilometers and the total area of the mountain is about 450 square kilometers. It is one of the source and watershed of east branch of Qinhuai River system and west branch of Taihu River system. The main peak is Damao Peak, 372.5 meters above sea level. Maoshan was one of the earliest areas in China to carry out geological research because of its complex fold structure and strong fault variation, and many mountain names have been listed as standard strata naming places in China. In Maoshan Mountain range, there is 1 mountain above 400 meters, 7 mountains between 400 and 300 meters, 23 mountains between 300 and 200 meters, more than 60 mountains between 200 and 100 meters, and more than 40 hills below 100 and 50 meters. Maoshan is one of the holy places of Taoism in China, with rich historical relics and numerous cultural relics. Maoshan Taoism in the historical development of two thousand years, with Sanmao Zhenjun as the founder of the mountain, is the birthplace of Taoism Shangqing School, known as the \"Shangqing Temple\"." ,
            "Hongze Lake, located in the lower reaches of the Huaihe River in the west of Jiangsu Province, the west side of the central North Jiangsu Plain, Huai 'an and Suqian, is the fourth largest fresh water lake in China. Its geographical position is between 33º06 '-33 º40' north latitude and 118º10 '-118 º52' east longitude. It is the junction of the middle and lower reaches of the Huaihe River. It was originally a small shallow lake group, called Fuling Lake in ancient times, called Bubutang after Han Dynasty, called Hongzepu in Sui Dynasty, and Hongze Lake in Tang Dynasty. After 1128, the Yellow River south migration by Sishui in the following Huaiyin to capture the lower reaches of the Huaihe River into the sea, the Huaihe River lost into the sea channel, in Xuyi east water, the original small lake expanded to Hongze Lake. Hongze Lake has a vast lake surface, rich resources and a long history. It is not only a large reservoir in the Huaihe River Basin, a shipping hub, but also a production base for fishery, special products and livestock products. It is known as the \"sunrise fighting gold\"."
    };
    private String[] fudingintroduce1 ={
            "    China Milu DEER Park was established in 1986, located in Dafeng Milu deer National Nature Reserve in Jiangsu Province...",
            "    Located at No. 68 West Huanshan Road, Binhu District, Wuxi City, Jiangsu Province, 30 kilometers away from the downtown area of Wuxi...",
            "    Luzhi Ancient Town belongs to Wuzhong District of Suzhou City, located in the east of Suzhou City...",
            "    Hanshan Temple is located in Gusu District, Suzhou City, was built in the Southern Dynasty ...",
            "    Wuxi Yuantouzhu Scenic Area, located at No. 1, Islet Zhu Road, Binhu District, Wuxi Cit...",
            "    Tianmu Lake is located eight kilometers south of Liyang City, Changzhou, because of Tianmu ...",
            "    Maoshan, located in the southwest of Jiangsu Province, is the first blessed place for Taoism...." ,
            "    Hongze Lake, located in the lower reaches of the Huaihe River in the west of Jiangsu Province..."
    };
//    private String[] fuding ;
//    private String[] fudingoicons;
//    private String[] fudingbuy;
//    private  String[] fudingpingfen;
//    private String[] fudingintroduce;
private String[] nametwo = {
        "三都澳","霍童古镇","宁德东湖水利风景区","福建支提山国家森林公园","宁德洋中水利风景区","宁德蕉城区上金贝中华畲家寨","洪口水库","宁德鹤林宫","宁德市博物馆",
        "古田钱来山景区","翠屏湖","齐云寺","古田溪山书画院","长洋徐氏古民居群","金钟湖山庄","凤林祠","凤林祠坐","蝉林祠","圆瑛故居","古田临水宫",
        "Yuantouzhu","Water Margin City","Xihui Park","Lingshan Scenic Area","Dangkou Ancient Town","Ancient Canal Scenic Area","Wuxi Zoo","Wuxi Museum","Huishan Temple",
        "鲤鱼溪","九龙漈风景名胜区","陈峭古村","周宁滴水岩","林公忠平王祖殿","周宁般若寺","九龙石窟","高山明珠","浦源郑氏宗祠",
        "犀溪漂流","西浦","灵峰禅寺","南山风景区","下党村","小托水库水利风景区","寿宁县生态茶园","犀溪","中共闽东特委含溪旧址","车岭古道",
        "Mount Huaguoshan","Haizhou Ancient City","KongWang Mountains","YunTai Mountains","Taohuajian Mountain Stream","Eden Garden","LianYunGang Museum","Qinshan Island","And Lake Wetland Park","Dayi Mountains",
        "柘荣鸳鸯草场","九龙井","宁德东狮山","仙都胜境景区","柘荣县九龙井水利风景区","东源古建筑群","凤岐吴氏大宅","百丈岩八仙洞","蟠桃映翠","青岚湖水利风景区",
        "the Wolf Mountain","Seyuan Garden","Nantong Forest Safari Park","Zhang Jian Memorial Hall","Junshan Mountain","Yangkou port","Xiaoyangkoubeauty spot","HAOHE",
        "杨家溪","罗汉溪景区","赤岸村","霞浦松山天后圣母行宫","空海大师纪念堂","霞蒲滩涂","北岐滩涂","霞浦县城","小皓海滩","七都溪"
};
    private int[] icons = {
            R.drawable.sanduao,R.drawable.huotongguzheng,R.drawable.shuili,R.drawable.senglin,R.drawable.yangzhong,R.drawable.shangjinbei,R.drawable.hongshui,R.drawable.helin,R.drawable.bowuguan,
            R.drawable.gutianxian,R.drawable.cuipinghu,R.drawable.qiyunshi,R.drawable.gutianxishan,R.drawable.changyangxunshi,R.drawable.jinzhonghushanzhuan,R.drawable.fenglinshi,R.drawable.fenglinshizuo,R.drawable.changlinshi,R.drawable.yuanyinguju,R.drawable.gutianlinshuigong,
            R.drawable.yuantouzhu,R.drawable.sanguocheng,R.drawable.xihuipark,R.drawable.lingshan,R.drawable.dangkoutown,R.drawable.guyunriver,R.drawable.wuxizoo,R.drawable.wuximuseum,R.drawable.huishantemple,
            R.drawable.liyuxi,R.drawable.jiulongfengjingmingshengqu,R.drawable.chenxiaogucun,R.drawable.zhouningdishuiyan,R.drawable.zudian,R.drawable.zhouningbanruosi,R.drawable.jiulongshiku,R.drawable.gaoshanmingzhu,R.drawable.puyuanzongsi,
            R.drawable.xixi,R.drawable.xipu,R.drawable.lingfeng,R.drawable.nanshan,R.drawable.xiadangcun,R.drawable.shuikufengjingqu,R.drawable.shengtaichayuan,R.drawable.xi,R.drawable.mindongjiuzhi,R.drawable.chelinggudao,
            R.drawable.baiyunshan,R.drawable.langu,R.drawable.tianchi,R.drawable.jinshan,R.drawable.lingfeng,R.drawable.liancun,R.drawable.shezu,R.drawable.wenhuaguan,R.drawable.wenhuaguan,R.drawable.yanghongse,R.drawable.fuanbowuguan,
            R.drawable.zherong,R.drawable.jiulongjing,R.drawable.dongshishan,R.drawable.xiandujingqu,R.drawable.zherongjiulong,R.drawable.dongyuangujianzhu,R.drawable.fengqiwushidazhai,R.drawable.baizhangyanbaxiandong,R.drawable.pantao,R.drawable.qinglanfengjingqu,
            R.drawable.tailaoshan,R.drawable.niulang,R.drawable.cuijiao,R.drawable.jiulixipu,R.drawable.xiaobailu,R.drawable.dayushan,R.drawable.yushandao,R.drawable.chixicun,R.drawable.shilancun,R.drawable.chengbao,R.drawable.diantoumazu,R.drawable.fudingxiangshansi,R.drawable.tianmenlingyouqu,R.drawable.gubao,R.drawable.lingfengshi,R.drawable.guoxingshi,R.drawable.xiyanglaorenqiao,R.drawable.fuyao,R.drawable.ziguo,
            R.drawable.yangjiaxi,R.drawable.luohanxi,R.drawable.chianqiao,R.drawable.shengmuxinggong,R.drawable.jiniantang,R.drawable.xiaputantu,R.drawable.beiqitangtu,R.drawable.xiapuxiancheng,R.drawable.xiaohaohait,R.drawable.qiduxi

    };
    private String[] buy={
            "¥32","free","free","free","free","¥35","free","free","free",
            "¥44","¥80","¥68","free","free","free","free","free","free","free","free",
            "¥120","¥73","free","free","free","free","free","free","free","free","free","¥70",
            "¥60","free","No ticket","free","free","free","No ticket","free","free",
            "¥138","free","free","free","free","free","free","free","free","free",
            "¥90","¥50","¥40","¥80","¥30","¥60","Free","Free","Free","¥30",
            "¥55","¥45","free","free","¥60","free","free","free","free","free",
            "¥70","Free","¥180","Free","Free","Free","¥40","Free",
            "¥100","¥178","free","free","free","free","free","No ticket","free","free"
    };
    private String[] introduces = {
            "三都澳又名三沙湾、宁德港，位于福建省宁德市东南部，为中国1.84万公里黄金海岸线的中点。距宁德市区30公里，为闽东沿海的“出入门户，五邑咽喉”，是世界级天然深水良港。",
            "霍童古镇是久负盛名的千年古镇，也是中国民间文化艺术之乡。霍童原名霍山，西周时霍桐真人在此地修炼，故名。霍童的美不仅在于幽美的生态环境，千年的文化积淀也使得这里散发着更大的古镇魅力。说到霍童，不可不提霍童线狮，作为第一批国家级非物质文化遗产，霍童线狮以其独特的艺术表现形式受到人们的喜爱，以其精彩的表演博得台下的观众的阵阵喝彩，这些的种种都奠定了霍童线狮在中国民俗文化中的地位，有“中华绝活”的美名。",
            "宁德东湖水利风景区是国家级水利风景区，位于福建省宁德市。总面积4.48平方公里，其中水域面积2.91平方公里，包括环东湖南、北岸公园和大门山、乌龟山、金蛇山等\"一湖两岸三山\"景观。风景区湿地面积较广，是水鸟觅食与栖息的理想场所。请看正文了解宁德东湖水利风景区的概况及旅游攻略。宁德东湖水利风景区东起金马海堤-金蛇头码头西接福宁北路，南达塔山路-南湖滨路，北至闽东中路-北湖滨路。",
            "第一高峰\"旗山\"好风光地处山区的虎贝镇，海拔高，境内山峦起伏，从而形成了独特的高山气候与风景。第一旗山，因其形如风中之旗而得名。它位于虎贝镇境内文峰、梅鹤村附近，堂义村后山。上山有多条路线:其一虎贝直接上山，其二虎贝黄家村后山上山，其三虎贝乡彭家村后山上山(路程最近)，也是唯一一条公路路线，通常登山游客都选此条，被称通往宁德高峰\"第一旗\"的必备之路。其四，堂义村后山走最美，经过竹林，森林，小溪，还有野菜，野果采摘,经过兔耳岭景区，这里风景独好，是旅游最佳路线。",
            "福建支提山国家森林公园位于福建宁德市蕉城区，由平陶山景区、支提山景区、白马山景区与金山景区组成，公园规划总面积为2299.93公顷，公园内森林覆盖率达94.35%。2006年12月27日，国家林业局做出行政许可决定准予蕉城区设立支提山国家级森林公园，定名为“福建支提山国家森林公园”。福建支提山国家森林公园主要景点有双仙谷、百丈瀑坑头水库等。2006年，蕉城区向国家林业局申报申请立支提山国家级森林公园，有关专家也多次深入景区现场考察，并给予推荐。",
            "宁德洋中水利风景区于2016年被列为第十六批全国重点文物保护单位。洋中水利风景区以水库、山潭、河流为依托，结合周边自然和人文旅游资源形成的自然河湖型水利风景区。景区核心项目占地面积约为2.6平方公里，东至三溪电站水库，西至林坂村溪尾水库，北至陈洞村龙潭瀑布，南至东南溪。景区内交通条件便捷、自然生态多样、文化底蕴深厚、水力资源丰富。洋中立足水利区位优势，因地制宜、因势利导，着力抒写“梦里水乡\"的锦绣诗篇。",
            "上金贝\"“中华畲家寨\"景区位于福建省宁德市蕉城区金涵畲族乡上金贝村，距宁德市中心6公里，海拔325米，人口303人。自2007年以来，在各级党委政府和相关部门的支持帮助下，经过数年的努力打造，目前已拥有“全国文明村\"、“福建省4星级乡村旅游经营单位\"、“宁德最美十大乡村一\"、\"宁德市社会主义新农村建设示范村\"、“国家AAA级景区\"等荣誉和称号。上金贝\"中华畲家寨\"景区是在上金贝村进行社会主义新农村建设的基础上创建起来的一个景区，具有与众不同的四个特色:其一，畲族风情文化。",
            "洪口水电站位于宁德市洪口乡境内霍童溪干流峡谷河段，是霍童溪干流梯级开发的第六级水电站,。洪口水电站水库正常蓄水位165米，水面面积8.92平方公里，坝址以上主河道长86.3千米，控制流域面积1701平方公里，约占霍童溪流域面积的75.8%。大坝设计为混凝土碾压重力坝，坝高130米，顶长340米。从坝址至水库末端长约17.22千米，总库容4.497亿立方米。",
            "根据宁德市志、支提图志、宁德文史和霍童各姓氏宗谱的记载，并参考霍童文史等资料，进行探索累集摘录之。鹤林宫位于第一洞天，是中国道教南方发祥地——“霍林洞天”，古时为道教四大名宫之一。其位于福建闽东宁德西北部的霍童山大童峰居五岳之首。考霍童得名，源于三千年前西周时有——霍桐真人\"炼修隐居霍童山丛林中，故号°霍林洞天\"”。《洞天志》云:洞天别有天地，后户可透\"金陵大茅山\"。洞口高丈许，广盈十筋。下极平坦，中有一窟，泉味如醴，旁有小径，黯然纡曲，人迹难到。",
            "宁德市博物馆（闽东畲族博物馆）隶属宁德市文化与出版局，是一家以收藏、展示、研究宁德历史文物和闽东畲族文物为宗旨的综合性博物馆。生动展示了宁德的悠久历史和灿烂文化。闽东畲族文物陈列则以丰富的馆藏内容，从宗教祭祀、服装首饰、生产用具、生活用具和工艺品等方面展示了畲族的婚礼、不同地区不同样式的传统服饰以及生产生活习俗等面貌，为观众了解畲族历史和传统文化提供一个直观而又生动的视角。",
            "钱来山风景区位于福建省宁德市古田县大桥镇钱厝村，是福建首个“钱文化”主题旅游风景区。景区总面积666平方公里，以景区出入口为中心，由荷花梯田、樱花谷、820山谷、飞瀑谷、有钱谷和谷里农场6个片区组成，呈环状分布，游步道总长度约3.8公里。钱来山风景区平均海拔820米，夏季从早到晚均温27℃，是福州周边又一避暑胜地。",
            "翠屏湖位于古田县城东郊，翠屏湖距城关3公里，翠屏湖属亚热带气候，翠屏湖中烟波浩淼，空气清新，四季如春，翠屏湖水域面积达37.1平方公里，翠屏湖蓄水量为6.41亿立方米，水质碧澄（达到国家二级标准）。三十六个大小岛屿，隔水相峙，沿翠屏湖有被省人民政府列为省级文物保护单位的海内外公认的顺天圣母陈靖姑祖庙临水宫。" ,
            "齐云寺建于北宁三年(1066年),位于凤埔与平湖交界,离古田新城20公里,地理条件优越.东靠古田翠屏湖,毗连宁德三都澳;北接Wuxi白水洋,延伸周宁鲤鱼溪,是通过古田火车站对接黄山、武夷山国家红色旅游线路必经之路。这座千年古刹，寺宇巍峨壮观，殿亭布局合理。建有大雄宝殿，地藏殿，韦伏殿，观音堂、积善堂、客堂，还有莲池宝塔，放生池。去年又增设了红色革命据点纪念堂。寺内塑许多金光闪闪的佛像。寺前竖有清乾隆三十八年福州府古田县正堂的“仁谳”石碑；寺门两柱刻制“齐皆积享先三宝，云可霖福四方”的对联；两条浮雕彩龙，巧夺天工。寺外还建有土地殿、迎客亭、半山歇息亭、水尾亭；去年又在寺前竖立一尊高大的石雕送子观音。",
            "五十年前，古田旧县城东北郊外两溪交汇处的沙坂高地处有座建于宋淳化二年（991）的溪山书院，朱熹曾在此讲学，并题匾曰“溪山第一”，曾圮于水，明清两代曾重建、重修，清代每年盛夏这里是诗人雅士吟哦之所，1952年为大水冲毁，1958年建古田溪水库，溪山书院旧址亦随之沉入湖底，人们总是念念不忘。五十年后的今天在原溪山书院不远处的翠屏湖后垅后岛建起溪山书画院，真是令人欢欣鼓舞。",
            "吉巷乡长洋村有一群古民居，博得专家的高度赞赏，专家坦言：长洋古民居群古朴壮观，独具风姿,文化底蕴深厚，保存状态良好，是福建大地上传统古建筑的一朵奇葩。一位著名画家专程来这里写生，看到这古朴瑰丽的景观，大为惊讶，执意要多留几天多画几幅,才意犹未尽的离开，画家感慨万千地赞赏：这是历史留给后人的无价之宝。",
            "山之灵气，水之秀气，举目望去，一片苍翠。郁郁葱葱的山林环抱农舍四周，远远可见袅袅炊烟升起，影影绰绰的房子置身其间，从农舍走出来便是霍童溪，碧绿的溪水一派平静，水中倒影清晰可见。这是笔者11月25日走进福口的“森林人家”所见到的景象。据市林业局工作人员介绍，“森林人家”是省林业厅和省旅游局共同打造的乡村旅游品牌，现在我市共有14个森林人家，自5月19日开业以来共接待了游客3万人次，收入300万元。",
            "凤林祠,也叫李氏宗祠,坐落于古田县杉洋镇西南3公里处。唐天祐二年（905）创建善院,置前后洋田赡之,立祖祠。后唐天成四年（929）改建善院，曰凤林。由入闽四世祖李灞创建。迄今已1100多年。凤林祠规模宏大，是蓝田八景之一，举目远山则层峦叠翠，近观水景则清秀徊旋。祠东有石柱高耸，乃唐时旧物，诉说当时的辉煌。祠门两侧立32副旗杆碣，均为历代科名登第者所立，至今保存完好，蔚为壮观。",
            "凤林祠坐落在古田县杉洋镇西南凤林山麓下，枕西北面东南，为宫殿式建筑，三进，总建筑面积2000多平方米。三向围以风火墙，前为檐牙风雨墙。祠埕立有32副旗杆碣及七根石柱，为历代科试登第碑刻者所献。",
            "蝉林祠位于古田县杉洋镇西北3公里的狮岩山麓。该祠坐北向南，木构建筑，占地1321.80平方米。中轴线上依次为大门，华表门亭、下游廊、前天井、中在厅、后天井、祖祠厅。",
            "圆瑛故居位于平湖镇端上村，始建于明末崇祯年间。端上开祖文昌公，开村第一屋至今400多年，属明代样式二层木结构。主楼长10.5米，宽5.5米，计面积115.5平方米；其侧房为4Ｘ4米二层楼，计面积32平方米；总计面积147.5平方米。圆瑛居住此屋系圆公祖上遗产。只知圆公爷、父辈三代住进此屋。圆公是开村第十一代后裔，圆公生母阙氏。圆公生于1878年５月12日，居住时间：出生至14岁在此屋居住。15-17岁到古田求学中秀才，18岁游涌泉寺并出家，19岁在家养病，20岁重新出家即1897年。从此离开此屋不复返，距今112年。此屋此后交由堂侄吴贞玉看管、居住。贞玉于上世纪70年代搬出，此屋至今休闲已有30余载。",
            "临水宫是一座风格别致的仿唐代宫殿建筑，始建于唐贞元八年（公元792年），后经元明清历代重修扩建，至今已有1200多年的历史，是分布国内外各地临水宫的祖殿，被福建省人民政府列为省级文物保护单位，现已列入国家级文物保护单位。临水宫是祀典道教女神，海内外公认的“顺天圣母”陈靖姑的宫殿。",
            "Yuantouzhu Scenic Area in Wuxi, Jiangsu, sprawls over a peninsula on Lake Taihu's northwest shore, renowned for its dragon turtle-shaped rock.",
            "\"Shuihucheng\" is located on the shores of Lake Tai in Wuxi, Jiangsu Province, China. ",
            "Wuxi Xihui Park is located in the western suburbs of Wuxi, Jiangsu Province, covering an area of 90 hectares.",
            "Lingshan Scenic Area in Shangrao, Jiangxi, China, covers 160 sq. km and is nationally recognized. Its picturesque mountains earned it the name \"Sleeping Beauty.\" ",
            "Dangkou Ancient Town, located 25 kilometers from downtown Wuxi, is situated at the junction of Wuxi, Suzhou, and Changshu in Jiangsu, China. ",
            "Qingming Bridge Ancient Canal Scenic Area is located at the southern end of downtown Wuxi, covering an area of about 44 hectares. ",
            "Taihu Pearl Joy Park (Wuxi City Zoo) draws inspiration from advanced zoo concepts both domestically and internationally in its exhibition style and park design. ",
            "Wuxi Museum, located at 100 Zhongshu Road, Liangxi District, Wuxi, Jiangsu, is a comprehensive museum housing nearly 40,000 cultural relics. ",
            "Huishan Temple, located in Wuxi's Huishan Ancient Town, Jiangsu, has a rich history dating back to the Southern and Northern Dynasties. ",
            "九龙漈风景名胜区位于周宁县东南13公里处。这里峰奇石秀、峡谷幽深，龙江溪在危崖断壁之间层层跌落，形成了九级瀑布群。",
            "陈峭村，是大自然的厚赠，有瑰丽的日出、云海、星空，有磅礴的峭石、岩洞、峡谷，还有千年遗存的街巷、廊桥、民俗。然而，这里因为地处偏僻，交通不便，一直“养在深闺人未识”。",
            "滴水岩风景区位于福建省周宁县城西南30公里的洞宫山麓，与古朴典雅的鲤鱼溪、气势磅礴的九龙祭瀑布群齐名，名载《辞海》。古人题匾曰“八闽首景”，它邻近国家级自然保护区鸳鸯溪的核心地带，依霍童溪上游的叉溪、白水洋溪等处与政和、Wuxi、宁德三地毗连，山峦起伏，溪涧密布，风景秀丽，成为闽东霍童溪流域旅游风光带的一个重要组成部分。",
            "该殿创建于明正德八年(1513年)，清嘉庆十年(1809年)增建太子亭．该宫坐南朝北，面宽17.27米，深24.38米，总建筑面积为421平方米．正殿面阔三间，进深三间，单檐歇山顶穿斗式(金柱抬粱式)，土木结构．殿前太子亭为三檐歇山顶，顶上有“赐封林公忠平王祖殿”石匾额和建造宫碑，皆为明代遗存。左右为钟、鼓楼，建筑形式为穿斗式双檐歇山顶，瓦栋上有泥雕，保存完好。",
            "般若寺坐落于纯池村尾院林桥旁，始建于后唐天成二年（927年），为纯池下坂许姓始祖所筹建。元朝至正元年（1341年）扩建，清朝道光十六年（1836年）重修。1985年12月天王殿拆除重建。上个世纪90年代末，芹山水库工程时迁址失败，后因水库蓄水，弥勒殿被水淹没而拆除，高水位时，大雄宝殿也被泡在水中，后经围堰，般若寺规模逐渐变小。",
            "九龙石窟(又名蝙蝠洞)游览区，是一处长约3公里的溪谷，流水淙淙，瀑布成群，花奇果异，茂林修篁，谷深洞幽，风景绝佳；特别是其纯净无污染的自然环境更为游客所称颂。",
            "周宁境内峰峦翠，玉带缠绵，钟云毓秀，如诗胜画，是一块令人神往的旅游胜地。境内风光秀丽，旅游资源得天独厚。九龙际瀑布群雄伟壮阔，气势磅礴，荣膺省级首批十大名胜风景区之盛誉；鲤鱼溪神鳞戏水，人鱼同乐，令游人流连忘返；滴水岩泻珠溅玉，水滴石穿，史称“八闽首景”；即将建成的库容3.2亿立方米的高山人工湖令人神往；千年古刹灵峰寺、方广寺山深庙古，闻名遐迩......全县平均海拔800米，县城海拔888米，冠全省之首，冬无严寒，夏无酷署，盛夏日均气温仅24摄氏度，享有“天然空调城”的美誉，是旅游、度假、避暑的好去处。",
            "南宋嘉定二年（1208）始建，明洪武十八年（1387）重建，清道光十年（1830）、光绪二年（1876）重修。平面前窄后宽，呈船形，为三进式传统宗祠建筑，由门厅、戏台、次厅、主厅等组成，建筑面积1830.2平方米。大门外两侧有清咸丰九年（1859）设置的旗杆石4对，祠内有泥塑7尊、木雕神祖牌及名人匾额等。",
            "犀溪文化生态旅游是以犀溪流域为纽带，以文化和生态为基础，以廊桥和古建筑为特色，北起犀溪乡外山村及牛当山，南至仙峰村，西至笔架山，东到长岗头山脊一线，并包括甲坑、石竹州一带，面积约17.8平方公里。旅游资源分布呈现大分散小集中的布局，包括西浦文化园林村、犀溪古建筑群、犀溪水域风光带、甲坑红色旅游地、笔架山山地风光带、石竹州峡谷等。犀溪漂流位于犀溪溪头六公里处，是暑假旅游的好去处。",
            "浦景区位于福建省寿宁县东北部闽浙边界的犀溪镇，是犀溪文化生态旅游区的重要组成部分, 景区距离寿宁县城20公里,距离浙江泰顺县城15公里，规划面积3.5平方公里。景区以生态水系和生态植被为基础，以廊桥和古建筑等生态文化为特色，突出状元、民俗、戏曲文化亮点，建设集旅游观光、运动休闲、研学旅行等于一体的综合型旅游区。",
            "灵峰寺又名灵显寺，位于周宁县纯池镇禾溪与桃坑之间的灵显麓，掩映于苍松翠竹之中，环境幽雅，风景秀丽。该寺坐落于纯池镇桃坑村附近，距镇政府所在地约9公里，海拨790多米，是县重点文物保护单位。",
            "南山风景区位于寿宁南阳，由金鸡山、南山顶、赤陵洋、紫云山、龟湖五个风景区组成，拥有明代古刹龙岩寺，明代文学家、寿宁知县冯梦龙塑像，寿宁革命圣地赤陵洋，闽东第一铁索桥----龟岭索桥.",
            "下党村是寿宁县2014年唯一列入第三批中国传统村落的村庄，村落面朝下党修竹溪依山而建，村落呈梯形分布，房子层层叠叠，交错有序。青山巍峨绿水缠绵，山水之间，木拱廊桥横跨其间，形成了“廊桥、流水、人家”的美景。",
            "小托水库，又名韶托水库。位于福建省寿宁县清源乡小托村，始建于1958年。总库容量426万立方米，有效库容277万立方米。小托水库中有“水中大熊猫”之称的中国一级保护动物——“桃花水母”。",
            "寿宁是个“九山半水半分田”的典型山区农业县，茶产业是传统主导产业，现有茶园11.8万亩，福云6号品种就达到10.5万亩。由于福云6号品种在耕作过程中已失去“早发早采早上市”的优势，无法适应市场消费需求，生产效益低下。2007年冬，寿宁县提出打造“闽浙边界生态新茶乡”的思路，致力茶业品种改良和生态茶园建设，优化产业结构，提高生产效益。",
            "犀溪，因在其西浦村的河滩上发现有印有犀牛脚印的溪石而得名，一个有着江浙风情的闽东小镇。犀溪位于福建宁德市寿宁县的犀溪乡，和浙江的泰顺相连，是一个山清水秀的地方，民风淳朴，风景怡人。作为集浙南乡村灵秀于一身的犀溪，绝对能让远行的你得到所要寻找的美好和宁静。犀溪名字的由来，据说就是因为在其西浦村的河滩上发现有印有犀牛脚印的溪石，现在传说中的犀牛脚印早已无处寻觅，而让远方来客陶醉的景色，就在西浦村上流的河滩处。犀溪文化生态旅游是以犀溪流域为纽带，以文化和生态为基础，以廊桥和古建筑为特色，北起犀溪乡外山村及牛当山，南至仙峰村，西至笔架山，东到长岗头山脊一线，并包括甲坑、石竹州一带，面积约17.8平方公里。旅游资源分布呈现大分散小集中的布局，包括西浦文化园林村、犀溪古建筑群、犀溪水域风光带、甲坑红色旅游地、笔架山山地风光带、石竹州峡谷等。内容来自环视旅游网。犀溪漂流，顺流而下，热情四溢，在5.3公里的河道上漂流总落差达到了180米，时而激流，时面缓潭，可谓有惊无险，乐趣天成。",
            "包括含溪闽东特委旧址、瓦窑坪红军标语、瓦窑坪红军洞等。中央红军长征后，闽东苏区与党中央失去了联系，局势十分严峻。1935年5月，中共闽东临时特委委员叶飞在含溪召开紧急会议，重新建立中共闽东特委，下辖霞鼎等4个中心县委和14个县委，领导闽东地区坚持了三年游击斗争。",
            "车岭古道位于寿宁县斜滩镇斜滩村、清源乡阳尾村，年代为明至清。明中叶形成，由明迄清历代维修，是寿宁通往福宁府的重要古官道。古道自斜滩蜿蜒而上，相对高差658米，全长约10公里。山路用石块随地形铺就，宽1到2米，沿途分布多处路亭、摩崖石刻、关隘，主要有车岭关、一亭、“岭峻云深”石刻、二亭、三亭、四亭遗址、五亭遗址、“去思碑”石刻、平氛关、龙凤亭等。",
            "Huaguo Mountain is the highest peak of Jiangsu mountains. Huaguo Mountain has 136 peaks, the main peak of Huaguo Mountain is Jade peak, elevation 624.4 meters.",
            "Haizhou is the source, origin and starting point of Lianyungang. With a history of more than 2,000 years, the ancient city has been the political, economic and cultural center.",
            "Kongwang Mountain is a national 4A-level scenic spot, with a thousand-year unique mountain in the annals of Chinese culture.",
            "Yuntai Mountain on the Sea is a famous tourist resort along the Jiangsu coast. This mountain was originally an island in the sea, and then evolved into land.",
            "Taohuajian is located at the southern foot of Jinping Mountain. In 2014, it was rated as an \"AAAA\" scenic spot by the National Tourism Administration.",
            "The scenic area covers an area of 10,000 acres, including 6,500 acres of pear orchards, 3,200 acres of hundred gardens, and about 300 acres of Danish fairy tale town.",
            "Lianyungang Museum belongs to the Lianyungang Municipal Bureau of Culture, Radio, Television and Tourism.",
            "There are more than 20 major scenic spots on the island, such as Chess Bay, which have always enjoyed the reputation of \"Qinshan Ancient Island, Yellow Sea Wonderland\".",
            "And Lake Wetland Park is a typical wetland ecosystem, with abundant terrestrial and aquatic animal and plant resources, and a wide variety of plant species.",
            "Dayi Mountain is a national 4A-level scenic spot, known as the first sacred mountain in Pingchuan, Huaibei.",
            "柘荣鸳鸯头草场位于省级风景名胜区东狮山南侧，海拔980米至1110米之间，距柘荣县城约5公里。这是一片面积约5000亩，四周被阔叶和针叶混交林包围的草场。周边的山峰巍峨挺拔，充满阳刚之壮美。草场核心区的山岚远远望去，又如人工泥塑的微型盆景。草山低矮，绵延起伏;山脊走势，富有韵律，节奏中蕴含着温文典雅之美妙!",
            "九龙井风景区是生态示范区、省级园林县城福建省柘荣县生态旅游的一朵奇葩，是世界地质公园——白云山石臼群的重要组成部分，也是泛太姥旅游圈、闽东亲水游线路的重要组成部分，她集形态各异的龙井群、冰臼奇观、瀑布、青竹走廊、原生态山谷等景观于一体，由九龙井生态景区、金溪漂流景区（筹建中）、石山洋生态观光区三部分组成。",
            "东狮山耸立在柘荣县城东2公里处，面积25平方公里。东狮山是太姥山脉的主峰，海拔1480米，气势雄伟。东狮山属花岗岩地貌，岩体、岩洞千姿百态，有皤桃岩、马头岩、象鼻岩。千笋石、万书岩。擎天柱。玉屏洞、灵峰洞、“仙人锯板”等景点，山下龙并瀑布落差100余米，宽6米，蔚为壮观。",
            "仙都胜境景区位于风景区南部，面积约1.4平方公里，以良好的田园和村居民俗为景观特色。主要有梨坪、彭琪垄两个自然村。东狮山也是多彩多姿的生物世界。据初步调查，整个景区栖有长尾雉、黄头雉、真鸟仔、鹁鸪、翠鸟、画眉等四十多种飞禽。“百啭千声随意啼，山花红紫树高低。”徜徉于山水之间，畅游于林泉之下，倾听鸟儿浅唱低吟，无比惬意。东狮山景区内花木品种众多，有桃花、樱花、百合、空谷幽兰，杜鹃更是一绝，每逢春夏之交，满山花开，有红、有白、有黄、有紫，绚丽多彩，白的素洁、红的热烈，顾盼生姿、煞是可爱，“此时逢国色，何处觅天香。”",
            "九龙井水利风景区，她集形态各异的龙井群、冰臼奇观、原生态风貌等景观于一体，两岸青山滴翠、谷中流水潺潺。水以溪水为练，将蝙蝠井、金猪井、观音井、龙门井、阴阳井、瓮子井、长河井、双心井、太阳井等九个龙井串连，动静结合，舒展妩媚和神奇；溪以石为奇，千姿百态的河床、风格迥异的冰臼，一段一景，石得水而活，悬泉飞流的瀑布，深不可测的龙井，不禁让人流连忘返，纷纷赞叹大自然的鬼斧神工。",
            "东源古建筑群位于柘荣县东源乡东源村，年代为清、民国。由吴氏宗祠戏楼、古书堂、培凤亭、古井、粉墙厝和吴成故居组成。",
            "该宅建于清乾隆年间（1736—1795），坐北向南，依山而建。内、外两重围墙，四周花园，通面阔76米，通进深99.1米，总占地面积7531.6平方米（11.3亩），其中建筑占地面积2391.52平方米。第一级台基宽56米，深9.5米。单檐砖木构穿斗式硬山顶门楼，三开间。二门为青砖三合土混合结构，三楼牌坊式顶盖，横额正面行书“凤岐聚秀”，内面行书“仁义为庐”。第二至第五级台基上建四座相隔一定距离的横向楼房，两旁建厢房，正中天井两旁建纵向单扇窗阁式廊庑，将横向楼房隔成三个纵向大院，第三级台基与第四级台基之间横向建空斗防护砖墙，将建筑群分隔成前后两大区域。",
            "百丈朝暾景区位于风景区中部，蟠桃映翠景区的东北面，面积约3.54平方公里，以绝岩峭壁，幽深洞岩为景观特色，主要景点有：罗隐湾、土地岩、清云宫、石门、石将军、风吹洞、石门楼、仙人脚印、观日台、灵岩洞、通真洞、灵峰洞、何仙姑洞、百丈灵岩等。",
            "蟠桃映翠景区位于风景区中西部，面积约1.88平方公里，以溪涧风光为景观特色，洞奇石怪，风景资源多而集中。主要景点有：迎宾亭、仙景岭、普光寺、仙掌泉、蟠桃溪、蟠桃岩、金蟾朝圣、蟠桃洞、马头岩、象鼻岩、玉屏洞、南天门、三曹院遗址、白马宫等。",
            "青岚湖水利风景区位于宁德市柘荣县，依托青岚水库而建，景区面积19.34平方公里，其中水域面积0.71平方公里，属于水库型水利风景区。区域内水体澄清，水质I级;负氧离子含量高，空气质量指数一级；森林覆盖率为86%,水土流失综合治理率高达96%；动植物资源丰富，生态环境优良。",
            "Nantong Wolf Mountain Scenic Area is a provincial scenic area of Jiangsu Province and a national AAAA scenic area, located on the north bank of the Yangtze River.",
            "It is the largest plant ornamental garden in Nantong, Jiangsu Province, with more than 200 kinds of rare tree species, including davidia davidia and Taiwan fir.",
            "Nantong Forest Safari Park is a large-scale comprehensive tourism project integrating tourist attractions,leisure and entertainment and popular science education.",
            "Zhang Jian Memorial Hall, located in Changle Town, Haimen District, Nantong City, Jiangsu Province, was built in memory of Mr. Zhang Jian.",
            "Junshan is a mountain in Chongchuan District of Nantong City. When it was an island in the sea, it was said that it was a place for the King of Qin to camp troops.",
            "Yangkou Port, is a typical offshore deep-water port, located in the north wing of the Yangtze River estuary and the west bank of the Yellow Sea.",
            "Nantong Xiaoyangkou Scenic Spot is located in Rudong Coastal Tourism Economic Development Zone, Jiangsu Province, within 1.5 hours of the economic circle;",
            "Haohe Scenic Area of Nantong City, located in the center of the national historical and cultural city Nantong, is one of the most complete preserved ancient moat in China.",
            "杨家溪位于霞浦县牙城镇境内。拥有纬度超北的古榕树群和江南超大的纯枫叶林。其中值得一提的是一株“榕树王”，它的树龄已有800多年，树干周长12.6米，冠幅直径51米，高30米，树干中空，有7个洞口，洞内可容数人。每当早晨的阳光斜斜地洒进树林，斑驳的树影在光影的晃动下展示着它的魔法，营造了一种如梦似幻的感觉。",
            "罗汉溪，源于福建省霞浦县柏洋乡洋里土勃头村，呈西北—东南流向。溪流经横江、溪西、洋沙溪，于下村汇集桐油溪支流，经百步溪，出水磨坑、大桥头入后港海。其主要支流桐油溪，源于水门乡百笕村，流域面积42平方公里，河道长度17公里。罗汉溪是霞浦县三大河流之一，蕴含丰富的水资源，是霞浦县最重要的集储水发电、农业灌溉、城市饮用水为一体的水源河流。罗汉溪又是具有独特山水景色的河流，第四纪冰川遗迹冰臼、巨石滩、峡谷、岩崖瀑布、两岸山景，绘就秀丽的山水画卷，有着重要的旅游观光价值。",
            "位于霞浦县城东北5公里处，在古代曾为重要的海港，因其海岸山石呈赤红色而得名。",
            "霞浦松山天后圣母行宫，建于北宋天圣年间，历史上别称「历史上别称「妈祖行宫」、「靖海宫」、「阿婆宫」，是继湄洲妈祖祖庙之后的第一个天后圣母行宫，是八闽创建较早，声名较着的祷祀“海峡和平女神”宫庙。素有「行宫之尊」的美誉。",
            "“空海大师纪念堂”是由中日双方出资于1994年5月21日建成的。它与1984年在西安青龙寺遗址兴建的“惠果、空海大师纪念堂”遥相辉映。绿草如茵宽广平坦的空埕右侧竖着的两块石碑，刻着建盖该堂经过和有关单位及个人芳名的碑文；左侧也对称地竖着两块石碑，一个是新居祐政先生的《赤岸镇赞歌》七言排律一首，另一个是高野山大学教授静慈圆写的“重访赤岸”七言绝诗四首：“高野残香冻未消，闽江桃杏正娇娇，兹行不为寻芳去，开祖苦心共此朝；求法不辞千里遥，漂流赤岸骨心销。",
            "滩涂，是陆地和大海之间的纽带。现代汉语词典是这么解释的：河流或海流夹杂的泥沙在地势较平的河流入海处或海岸附近沉积而形成的浅海滩。仅福建省就有近300万亩的滩涂，在漫长的岁月长河中，经过无数次的潮起潮落，滩涂形成了自己特有的景观，尤其是这些年迅速发展起来的近海滩涂养殖，那些渔排木屋，那些小舟鱼网，那些浮标竹竿，随着潮水的涨退，变幻着无穷的组合，吸引来无数摄影人的目光。",
            "霞浦县松港街道北岐村海边风光如画，影友如织。霞浦滩涂风光旖旎，2012年被《数码摄影》杂志列为“中国最值得拍摄的80个绝美之地”之一，并成为22大摄影胜地之一被重点推荐，每年前来拍摄滩涂风光的国内外影友多达20多万人次。",
            "霞浦全县14个乡镇有10个靠海，海洋优势尤为突出，境内海岸线长404公里，占全省八分之一，浅海滩涂面积104万亩，港湾众多，岛屿星罗棋布，有大小岛屿196个，港口138个。霞浦的三沙港距台湾的基隆港126港里，渔船朝发夕至，而且三沙与台湾语言相通，习俗相近。",
            "小皓的海滩是摄影者来到霞浦一定要前往的一站，潮水的涨落、季节的变换、月落日升，小皓也在每天以崭新的姿态呈现出她的五彩斑斓。同样的潮水，不同样的光影，同样的劳作，不同样的心情，摄影者总能创作出无尽的精美作品。小皓主要以沙质滩涂为主，当在顺光位置拍摄时能看到山下那一块块巨大的金黄滩涂随波而变，在逆光位置观赏这片滩涂时，镜头中那一条条从沙滩上流淌过的溪水，化作蜿蜒曲折的滩涂动脉，闪烁着迷人的银色光芒，令人兴奋不已。",
            "溪水流程20多公里，清澈见底，其间潭、濑、滩交替分布。山绿水碧，稀有树木花早异彩纷呈，樟树、枫树、榕树、花竹、万竹、金竹、杜鹃、黄杨、芦苇成片分布。“文广断船”、“金龟戏曾鳖”、“观音坐莲”等30多处人文景观，栩栩如生，维妙维肖。溪中盛产香鱼、龙鳗、毛蟹、甲鱼、鲤鱼等淡水珍品，还有鸳鸯、绶带、水獭等珍稀动物。"
};
    private String[] dizhi ={
           "Suzhou","Suzhou","Suzhou","Suzhou","Suzhou","Suzhou","Suzhou","Suzhou","Suzhou",
            "Nanjing","Nanjing","Nanjing","Nanjing","Nanjing","Nanjing","Nanjing","Nanjing","Nanjing","Nanjing",
            "Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi","Wuxi",
            "Yangzhou","Yangzhou","Yangzhou","Yangzhou","Yangzhou","Yangzhou","Yangzhou","Yangzhou","Yangzhou","Yangzhou",
            "Changzhou","Changzhou","Changzhou","Changzhou","Changzhou","Changzhou","Changzhou","Changzhou","Changzhou","Changzhou",
            "Lianyungang","Lianyungang","Lianyungang","Lianyungang","Lianyungang","Lianyungang","Lianyungang","Lianyungang","Lianyungang","Lianyungang",
            "柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县","柘荣县",
            "Nantong","Nantong","Nantong","Nantong","Nantong","Nantong","Nantong","Nantong",
            "霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县","霞浦县"

    };
    EditText search_edit;

    int f=0;
    String nameone = "福鼎市";
    int count=0;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_main3, container, false);
        mRecyclerView = view.findViewById(R.id.recycle);
        mRecyclerViewtwo = view.findViewById(R.id.recycle2);
        search_edit =view.findViewById(R.id.search);

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try{
//                    fuding = connect.tuijianone();
//                    fudingoicons = connect.tuijiantwo();
//                    fudingbuy = connect.tuijianthree();
//                    fudingpingfen = connect.tuijianfour();
//                    fudingintroduce = connect.tuijianfive();
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        thread.start();
//        try{
//            thread.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        banner = view.findViewById(R.id.banner);
        mAdapter = new HomeAdapter();


        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(mAdapter);

        mAdaptertwo = new HomeAdaptertwo();
        mRecyclerViewtwo.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerViewtwo.setAdapter(mAdaptertwo);
//        mRecyclerView = view.findViewById(R.id.recycle);
//        mAdapter = new HomeAdapter();
////        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
////                StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(mAdapter);
        Button search_button = view.findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int a=0;a<nametwo.length;a++){
                    if(search_edit.getText().toString().equals(nametwo[a])){
                        count = a;
                        break;
                    }
                }
                final int count1 = count;
                if(!"".equals(search_edit.getText().toString())){
                    if(nametwo[count1].equals(search_edit.getText().toString())){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), DetailsActivity.class);
                        intent.putExtra("detail_iv", icons[count1]);
                        intent.putExtra("detail_name", nametwo[count1]);
                        intent.putExtra("detail_buy",buy[count1]);
                        intent.putExtra("detail_introduce", introduces[count1]);
                        intent.putExtra("f",f);
                        intent.putExtra("name",dizhi[count1]);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(),"Can not find",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"Empty input！Please try again",Toast.LENGTH_SHORT).show();
                }

            }
        });
        initDate();
        initView();
        return view;
    }
    @SuppressLint("SuspiciousIndentation")
    private void initDate() {
        imagePath = new ArrayList<>();

            imagePath.add("https://img.zcool.cn/community/01be785f5a14c811013e3187e11364.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100613000000tkqze4C21.jpg");
            imagePath.add("https://img.zcool.cn/community/01d2885b461b6ea80121ade00193b7.jpeg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://img.zcool.cn/community/01db7755497da90000019ae907bae8.jpg@1280w_1l_2o_100sh.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100611000000qbsoy001C.jpg");
            imagePath.add("https://youimg1.c-ctrip.com/target/100f1b000001avz1z910B.jpg");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/6c224f4a20a4462392b30d489b22720e0cf3d79b?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://bkimg.cdn.bcebos.com/pic/77094b36acaf2edd0c7fbde98a1001e939019320?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U4MA==,g_7,xp_5,yp_5/format,f_auto");
            imagePath.add("https://ts1.cn.mm.bing.net/th/id/R-C.cc9cd98a7a8416a07a1c6a8de8545cc8?rik=BiPZz6dC6SZlNA&riu=http%3a%2f%2fyouimg1.c-ctrip.com%2ftarget%2ftg%2f349%2f266%2f214%2f44fc605c5a464a3ea533a978e4008b7d.jpg&ehk=O0PQbvvlspobz5Ymc3M9NmHgtAQMZCd8pL1kbfbWrKU%3d&risl=1&pid=ImgRaw&r=0");
            imagePath.add("https://p1.ssl.qhimg.com/t018bb8852d0449da11.jpg");



    }
    private void initView() {
        glideImageLoader = new GlideImageLoader();

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
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_receivetwo,parent,false));
            return holder   ;
        }


        @Override
        public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.iv.setImageResource(image[position]);
            holder.name.setText(name[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(name[position].equals("Other")){
                    }else{
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),ZhujiemianActivity.class);
                        Result.setResult(name1[position]);
                        startActivity(intent);
                    }


                }
            });
        }

        @Override
        public int getItemCount() {
            return name.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            ImageView iv;
            TextView introduce;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                iv = (ImageView) view.findViewById(R.id.iv);
                introduce = (TextView) view.findViewById(R.id.introduce);
            }
        }

    }

    class HomeAdaptertwo extends RecyclerView.Adapter<HomeAdaptertwo.MyViewHolder> {
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.recycle_itemtwo, parent, false));
            return holder;
        }


        @Override
        public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.pingfen.setText(fudingpingfen[position]);
            holder.buy.setText(fudingbuy[position]);
//            holder.iv.setImageResource(fudingoicons[position]);
            Glide.with(getActivity()).load(fudingoicons[position]).into(holder.iv);
            holder.name.setText(fuding[position]);
            holder.introduce.setText(fudingintroduce1[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),DetailsActivity.class);
                    intent.putExtra("detail_iv",fudingoicons[position]);
                    intent.putExtra("detail_name",fuding[position]);
                    intent.putExtra("detail_buy",fudingbuy[position]);
                    intent.putExtra("detail_introduce",fudingintroduce[position]);
                    intent.putExtra("f",f);
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return fuding.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            ImageView iv;
            TextView introduce;
            TextView buy;
            TextView pingfen;
            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                iv = (ImageView) view.findViewById(R.id.iv);
                introduce = view.findViewById(R.id.introduce);
                buy = view.findViewById(R.id.buy);
                pingfen = view.findViewById(R.id.pingfen);
            }
        }
    }}
