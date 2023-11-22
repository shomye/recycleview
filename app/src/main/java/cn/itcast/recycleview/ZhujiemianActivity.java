package cn.itcast.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

import java.sql.SQLException;

/**
 * Created by DELL on 2022/8/7.
 */

public class ZhujiemianActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private RecyclerView mrecycleview;
    private HomeAdapterTwo madapter;
    String name_receive;//用于接收数据库查询的返回数据

    private String[] name = {};
    private int[] icons = {};
    private  String[] buy = {};
    private  String[] introduces = {};

   //苏州市
    private String[] suz = {"zhuozhengyuan","liuyuan","shizilin","wangshiyuan","canglangting","pingjianglu","shantangjie","xietanglaojie","suzhoubowuguan"};
    private int[] szicons = {R.drawable.zhuozhengyuan,R.drawable.liuyuan,R.drawable.shizilin,R.drawable.wangshiyuan,R.drawable.canglangting,R.drawable.pingjianglu,R.drawable.shantangjie,R.drawable.xietanglaojie,R.drawable.suzhoubowuguan};
    private String[] szbuy = {"¥80","¥55","¥40","¥40","¥20","free","free","free","free"};
    private String[] szintroduce = {"Humble Administrator's Garden covers an area of 78 mu, the whole garden is divided into three parts: east, middle and west. The east is bright and cheerful, with Pinggang distant mountains, pine forest lawn, bamboo dock and Qu Shui. The western pool is in a curved shape, which is characterized by Taiwan hall Zhi, corridor ups and downs, water reflection, unique interest, gorgeous and exquisite decoration, the main building is near the residential side of the 36 mandarin duck hall.",
            "Suzhou Liuyuan was built in the Ming Dynasty Wanli 21 years (AD 1593), for the Taimu Temple Shaoqing Xu Taishi private garden, known as the East garden, when the East garden \"Hongli Xuan, front buildings and back hall, can be drunk guests\". Ruiyun Peak \"Yan Qiao A in the south of the river\", by the stack of master Zhou Shichen stone screen, exquisite steep cut \"like a landscape painting\". Now the central pool, pool west of the lower part of the rockery stone, like the relics of the year.",
            "The Lion Forest was built from the Yuan Dynasty to the second year (1342), which is one of the representatives of Chinese classical private garden architecture. It belongs to one of the four famous gardens in Suzhou. Lion Forest is also a world cultural heritage, a national key cultural relic protection unit, and a national AAAA tourist scenic spot. Lion Forest is located in the northeast of Suzhou city. Because of the stone peaks in the park, much like a lion, the name \"lion forest\". The lion Forest is rectangular in plane, covering an area of about 15 mu. There are many and exquisite lake rockery in the forest, and the buildings are scattered at random. The main buildings are Yanyu Hall, See hill building, waterfall pavilion, ask Mei Pavilion, etc.",
            "Netmaster Garden is located at No. 11 Kuajiatou Lane, Daichengqiao Road, Southeast of Suzhou City, Jiangsu Province, about 120 kilometers from Shanghai Hongqiao Airport. Suzhou garden is a medium-sized classical landscape house representative works. The Garden was first built in the Southern Song Dynasty (AD 1127-1279). It used to be the site of the \"Wan Xuan Tang\" of Shi Zhengzhi, a scholar in Yangzhou who was a book collector and official in the Song Dynasty. The garden was named \"Yu Yin\" and was abandoned later. In the reign of Qianlong in the Qing Dynasty (about 1770 AD), Song Zongyuan, the retired Shaoqing priest of Guanglu Temple, bought it and rebuilt it, naming it \"Master of the Nets Garden\".",
            "Canglang Pavilion, located in the south of Suzhou City, Jiangsu Province, is the oldest garden in Suzhou. It was built in the Qingli period of the Northern Song Dynasty (1041 ~ 1048), and was once the residence of the famous general Han Shizhong in the early Southern Song Dynasty (early 12th century). Canglang Pavilion garden art is unique, not into the garden door will be set a pool of green water around the garden. The park with rocks as the main scene, face a mountain, canglang stone pavilion is located on it. There is a pool chiseled under the mountain, and a winding corridor connects the mountains and rivers. The Ming Dao Hall in the southeast of the rockery is the main building of the garden. In addition, there are five hundred sages' ancestral Hall, hill tower, Cui Linglong Hall, Yang Zhi Pavilion and Imperial Stinscription Pavilion and other buildings set off it.",
            "Pingjiang Road is an old historical street in Suzhou. It is a path along the river, which is called Pingjiang River. Pingjiang Road Historical District is the most well-preserved area of Suzhou ancient city, which can be called the epitomization of the ancient city. Compared with \"Pingjiang Map\" in the Southern Song Dynasty and \"General Map of Waterways in Suzhou Fucheng\" in the late Ming Dynasty, Pingjiang Road basically continued the pattern of city and square since the Tang and Song Dynasties, and maintained vitality. Pingjiang Road south from Ganjiang East Road, North Yue Baita East Road and North East Street, the ancient name is called \\\" Shiquanli \\\", earlier appeared in the 1834 \"Wu Men Table hidden\", said :\\\" Pingjiang Road ancient name of Shiquanli, there are ten ancient Wells, Huayang Bridge south one, Xijia Bridge south one, Yuan Bridge north one \\\", very detailed. Pingjiang Road is a road along the river, with a total length of about 1,606 meters, that is, three li long, and there are many horizontal streets and narrow lanes on both sides, such as Lion Temple Lane, Chuanfang Lane, East flower bridge lane, Caohuxu Lane, Daxinqiao Lane, Weidao Guanqian, Zhongzhangjia Lane, Daru Lane, Lilac Lane, Huxiangzhi Lane, Xiaojia Lane, Nianjia Lane, hanging bridge lane and so on.",
            "Shantang Street is located in the northwest of the ancient city of Suzhou, Jiangsu Province. In the east, it goes to the gate of Chang, \"a place of wealth and elegance\" in the world, and in the west, it goes to Huqiu, \"the first scenic spot in Wuzhong\". The total length of Shantang Street is about 3,600 meters, about 7 li, so it is called \"seven Li Shantang to Huqiu\". Shantang Street is named after the Shantang River. In the first year of the Tang Dynasty (825), the poet Bai Juyi served as the governor of Suzhou. He dredged the northwest river outside the city of Suzhou and used the natural river to dig into the straight river (from the moat outside the gate of Chang to the foot of the Tiger Hill), called Shantang River, which was convenient for sailing boats. At that time, Tiger Hill was called Wuqiu, and there was Wuqiu Temple on the mountain. Therefore, the poet wrote it as Wuqiu Temple Road, and later called \"White public Dike\". The dike was seven miles long, and it was also called seven miles Mountain Pond. Because it is between the downtown of Chang Men and the scenic spot Huqiu, it has gradually become a bustling tourist commercial street.",
            "Xitang Old Street is located at the Xitang River in the east of Jinji Lake in Suzhou Industrial Park. It is a commercial district featuring cultural tourism, with strong memories of Jiangnan water home and humanistic feelings. It was rebuilt on the site of Xitang Town with a history of more than 760 years. At the beginning of the planning, the historical evolution and local customs of Xitang Town were deeply excavated. Through the planning and design of antique architecture, Soviet-style gardens, green gardening, leisure business and other aspects, the traditional culture and modern commerce were organicly integrated. To build a collection of catering, shopping, entertainment, culture and other functions as one of the fashion leisure block.",
            "Suzhou Museum, located at No. 204, Northeast Street, Gusu District, Suzhou City, was established on January 1, 1960, and the site is the Taiping Emperor's Palace. On October 6, 2006, the Suzhou Museum designed by I. M. Pei was completed and officially opened to the public. The museum covers an area of about 10700 square meters, with a floor area of more than 19000 square meters, and a total floor area of 26500 square meters, including the Palace of the Taiping Heavenly Kingdom. On September 25, 2021, the West Hall of Suzhou Museum opened for trial operation, with a construction area of 48,365 square meters and an exhibition area of 13,391 square meters. Suzhou Museum is a local comprehensive museum that collects, displays, studies and spreads the history, culture and art of Suzhou. Suzhou Museum has four basic exhibitions: Wu-land relics, Wu-ta National treasures, Wu-zhong elegance and Wu-men calligraphy and painting. The total number of collections is 24819 pieces/set, and 9734 pieces/set of precious cultural relics, of which 222 pieces/set of first-class items and 829 pieces/set of second-class items. There are 8683 pieces/set of third-level products, which are good at archaeological relics unearthed over the years, Ming and Qing paintings and crafts."};

    //南京市
    private  String [] nanj = {"fuzimiao","xuanwuhu","zhongshanling","zhenzhuquan","nanjingzongtongfu","mingxiaoling","nanjingbowuguan","jimingsi","zhanyuan","yuhuatai"};
    private int[] njicons = {R.drawable.fuzimiao,R.drawable.xuanwuhu,R.drawable.zhongshanling,R.drawable.zhenzhuquan,R.drawable.zongtongfu,R.drawable.mingxiaoling,R.drawable.nanjingbowuguan,R.drawable.jimingsi,R.drawable.zhanyuan,R.drawable.yuhuatai};
    private String[] njbuy = {"¥30","¥20","free","¥40","free","free","¥25","¥10","¥100","free"};
    private String[] njintroduce = {"Nanjing Confucius Temple is located in the west of Gongyuan Street and Jiangnan Gongyuan on the north bank of Qinhuai River in Qinhuai District of Nanjing. It is located in the core area of Qinhuai River scenery belt of Confucius Temple, namely Nanjing Confucius Temple, Nanjing Confucian Temple and Wenxuanwang Temple. It is the place where Confucius is worshipped and offered sacrifices. It is not only the cultural and educational center of Nanjing in the Ming and Qing Dynasties, but also the cultural and educational complex of the crown in the Southeast provinces. Confucius Temple is a group of large-scale ancient architectural complex, mainly composed of Confucian temple, academy, tribute courtyard three buildings, covering a large area. There are Zhaobei, Panchi, archway, Juxing Pavilion, Kuixing Pavilion, Lingxing gate, Dacheng Hall, Mingde Hall, Zunjing Pavilion and other buildings. The Confucius Temple is known as a scenic spot in the Qinhuai region and a characteristic landscape area in the ancient capital of Nanjing. From the Six Dynasties to the Ming and Qing Dynasties, many families gathered nearby, so it is known as the \"Six Dynasties of golden fans\". It is the largest traditional ancient street market in China, and is one of the four major downtown markets in China, along with the Shanghai Chenghuang Temple, Suzhou Xuanmiao Temple and Beijing Tianqiao. It is also a famous open national AAAAA tourist attraction and international tourist attraction in China.",
            "Xuanwu Lake is located in Xuanwu District, Nanjing City, Jiangsu Province, with Zijinshan Mountain in the east, Ming City Wall in the west, Nanjing Railway Station in the north, and Zhoushan Mountain in the south. It is the largest urban park in the south of the Yangtze River, the largest royal garden lake in China, and the only royal garden in the south of the Yangtze River. It is known as the \"Pearl of the Jinling\", also known as the Back Lake and the North Lake. The cultural history of Xuanwu Lake can be traced back to the pre-Qin period. During the Six Dynasties, it became a place for the emperor to read the sailors and was turned into a royal garden. On the south bank, there were royal palaces such as Hualin Garden and Leyou Garden. In the Northern Song Dynasty, Wang Anshi, the governor of Jiangning Prefecture, \"returned the abandoned lake to the field\", and the Xuanwu Lake disappeared for more than 200 years. In the Yuan Dynasty, after two dredging, Xuanwu Lake reappeared; In the Ming Dynasty, it was set up as the Houhu Yellow Book Library, which was the royal forbidden land. At the end of the Qing Dynasty, when the Nanyang persuasion meeting was held, Fenrun Gate (now Xuanwu Gate) was opened, and Xuanwu Lake became a tourist area. In August 1928, Xuanwu Lake was officially opened to the public as a park." ,
            "Sun Yat-sen's Mausoleum is located in Zhongshan Mountain Scenic Spot, south of Zijinshan Mountain, Xuanwu District, Nanjing City, Jiangsu Province. It is the mausoleum of Sun Yat-sen, the great pioneer of modern China's democratic revolution, and its affiliated memorial buildings. The mausoleum covers an area of more than 80,000 square meters, and was started in the spring of 1926 and completed in the summer of 1929. With Pingchuan in front, Qingzhang in the back, Linggu Temple in the east and Ming Xiaoling Mausoleum in the west, the whole architectural complex is built along the mountain and gradually rises along the central axis from south to north. The main buildings are fraternity Square, tomb road, tomb gate, stone steps, stately pavilion, worship hall and tomb chamber, etc., arranged on a central axis, which reflects the style of traditional Chinese architecture. Like a \"Liberty bell\" lying flat on a green carpet. The architecture of Sun Yat-sen's mausoleum combines the essence of ancient Chinese and Western architecture, solemn and simple, and does not innovate.",
            "The Pearl Spring Scenic Area is located at the south foot of Dingshanxi, Pukou District, Nanjing City, Jiangsu Province. It is about 7 kilometers away from Pukou Railway Station and Pukou Ferry, and 16 kilometers away from Nanjing city. Pearl Spring was reconstructed as a scenic park in May 1984, with a total area of 14.8 square kilometers, including 1 square kilometer of water area. The annual reception of the scenic spot reaches 1.2 million people, and the maximum daily reception is 91,113 people. Nanjing Pearl Spring Scenic Spot is now a national water Conservancy Scenic spot, national AAAA level tourist scenic spot, Jiangsu Provincial tourist resort, Jiangsu provincial park.",
            "Nanjing President's Office is located at No. 292, Changjiang Road, Xuanwu District, Nanjing City. It is the largest and best preserved architectural complex in modern Chinese architectural remains, and is also one of the main representatives of Nanjing Republic of China architecture and an important site of modern Chinese history. Since modern times, the President's Office in Nanjing has become the center of China's political and military affairs and the source of major events. A series of major events in China may take place here, which is closely related to it. Many important figures have been active here. The Nanjing Presidential Palace has a history of more than 600 years, which can be traced back to the GUI De Hou Mansion and the Han Wang Mansion in the early Ming Dynasty. In the Qing Dynasty, it was established as Jiangning Weaving Department, Liangjiang Governor's Department, etc., and Kangxi, Qianlong's southern tour took this as the palace. After the Taiping Heavenly Kingdom made Tianjing its capital, it built a grand Tianwang mansion here. On January 1, 1912, Sun Yat-sen was sworn in as the provisional President of the Republic of China, which was turned into the Grand presidential office and later the presidential office of the Nanjing National Government.",
            "Ming Xiaoling Mausoleum is located at the south foot of Zijinshan Mountain in Xuanwu District, Nanjing City, Jiangsu Province, under the Dulongfu play Mount Qomolangma, east of Zhongshan's tomb, south of Meihua Mountain, located in the Zhongshan Mountain Scenic Spot, is the tomb of Ming Taizu Zhu Yuanzhang and his queen. Because the Empress Ma Shi posthumous title \"Filial piety high Empress\", and because the practice of filial piety rule the world, the name \"Xiaoling\". Covering an area of more than 1.7 million square meters, it is one of the largest imperial tombs in China. Ming Xiaoling was built in the fourteenth year of Ming Hongwu (1381), and completed in the third year of Ming Yongle (1405). It has called 100,000 military industries, which lasted for 25 years. Tang Song Emperor mausoleum \"according to the mountain for the tomb\" old system, and create square grave for Circular mound new system. The harmonious unification of humanity and nature achieves the perfect height of the integration of nature and man, and becomes an excellent model of the combination of traditional Chinese architectural art and culture and environmental aesthetics. The main buildings and stone carvings in the mausoleum area include Square city, Ming tower, Bao city and Bao Ding, including Xia Ma Fang, Great Golden Gate, Shen Gong Sheng De tablet, Shen Tao, stone statue Road stone carvings, etc., which are all architectural remains of the Ming Dynasty and maintain the authenticity of the original building and the integrity of the spatial layout of the mausoleum.",
            "Nanjing Museum is located at No. 321, Zhongshan East Road, Xuanwu District, Nanjing City, Jiangsu Province. It is one of the three major museums in China, referred to as South Yuan or South Bo. Its predecessor was the National Central Museum, which was founded by CAI Yuanpei and others in 1933. Now it is a large-scale comprehensive national museum, the first batch of national first-class museums, the first batch of central and local co-built national museums, the national AAAA tourist attractions, the national key cultural relic protection units and China's 20th century architectural heritage. Nanjing Museum covers an area of more than 130,000 square meters, with a pattern of \"one hospital and six museums\", namely the history museum, special exhibition hall, digital museum, art museum, intangible cultural heritage museum, and the Republic of China Museum. In addition, the Institute has \"six\" research departments, namely, the Institute of Archaeology, the Institute of Cultural Relic Protection, the Institute of Ancient Architecture, the Institute of Exhibition Art, the Institute of Intangible Cultural heritage Protection, and the Institute of Ancient Art. It also has the only research institution of ethnic folklore in the Chinese museum, among which the Institute of Cultural Relic protection is called \"the third grade Hospital of cultural Relic\". It is the key scientific research base of the State Administration of Cultural Heritage of China's Paper Cultural relics protection.",
            "Jiming Temple is located in the east foot of Jilong Mountain, Xuanwu District, Nanjing, also known as the ancient Jiming Temple. It was built in the first year of Yongkang in the West Jin Dynasty (300 years), and has a history of more than 1,700 years. It is one of the oldest Sanskrit temples and royal temples in Nanjing, and its incense has always been vigorous. The first of the \"four hundred and eighty temples in the Southern Dynasty\", it was the center of Buddhism in China during the Southern Dynasty, along with Qixia Temple and Dingshan Temple. The history of Jiming Temple can be traced back to Qixuan Temple in the Eastern Wu Dynasty, which was located in the back garden of the Wu State during The Three Kingdoms. In the first year of Yongkang in the West Jin Dynasty (300 years), he built a room against the mountain and created the ashram. After the Eastern Jin Dynasty, this place was opened as the Tingwei Department; In the first year of the Southern Liang Dynasty (527), Emperor Wudi of Liang built Tongtai Temple in Jimingdai. He sacrificed his life here four times and promulgated the Text on Meat and wine, which was the beginning of Buddhist vegetarian diet. This place became a Buddhist resort. In the Southern Tang Dynasty, it was renamed Jingju Temple, and later changed to Yuanji Temple. In the Song Dynasty, it was changed to the Magic Treasure Temple.",
            "Zhanyuan is located in the core area of Qinhuai Scenery Belt of Confucius Temple in Qinhuai District of Nanjing City, Jiangsu Province. It is the oldest extant classical garden of Ming Dynasty in Nanjing and one of the \"four famous Gardens in southern China\". Its history can be traced back to the Ming Taizu Zhu Yuanzhang Emperor before the Wu Palace, after the King of Zhongshan Xu Da's palace garden, known as rockery, Ouyang Xiu poem \"looking at the jade Hall, such as in the sky\" named, Ming Dynasty is known as the \"Southern capital first garden\". It is now a national key cultural relic protection unit and a national AAAAA tourist scenic spot. Covering an area of about 20,000 square meters, Zhanyuan has more than 20 scenic spots, both large and small. The layout is elegant and exquisite, including magnificent ancient buildings of Ming and Qing Dynasties, steep rockery mountains, the famous Taihu Stone of the Northern Song Dynasty, quiet and simple pavilions and pavilions, and marvelous peaks. There is the Taiping Heavenly Kingdom History Museum in the garden, which is the only Taiping Heavenly Kingdom history museum in China. In the 87 edition of A Dream of Red Mansions and Zhao Yazhi's edition of The Legend of the New White Lady, the White Mansion was shot in Zhanyuan.",
            "Yuhuatai Martyrs' Cemetery, located one kilometer outside the Gate of China in the south of Nanjing, Jiangsu Province, covers an area of 153.7 hectares. It is the earliest and largest national martyrs' cemetery established after the founding of New China. On the central axis of 1500 meters in length, from north to south, martyrs martyred group sculptures, martyrs monument, reflecting pool, memorial bridge, memorial hall, and Zhongsoul Pavilion are unfolded in turn to form the central memorial area, which is solemn and majestic. Complementing the central memorial area, there are also well-known martyrs' tombs and memorial buildings such as the East and West martyr sites distributed throughout the cemetery.\n" +
                    "\n"};
    //Wuxi
    private  String [] pingnan = {"Yuantouzhu","Water Margin City","Xihui Park","Lingshan Scenic Area","Dangkou Ancient Town","Ancient Canal Scenic Area","Wuxi Zoo","Wuxi Museum","Huishan Temple",};
    private int[] pingnanicons = {R.drawable.yuantouzhu,R.drawable.sanguocheng,R.drawable.xihuipark,R.drawable.lingshan,R.drawable.dangkoutown,R.drawable.guyunriver,R.drawable.wuxizoo,R.drawable.wuximuseum,R.drawable.huishantemple,};
    private String[] pingnanbuy = {"¥120","¥73","free","free","free","free","free","free","free","free","free","¥70"};
    private String[] pingnanintroduce = {
            "Yuantouzhu Scenic Area in Wuxi, Jiangsu, sprawls over a peninsula on Lake Taihu's northwest shore, renowned for its dragon turtle-shaped rock.",
            "\"Shuihucheng\" is located on the shores of Lake Tai in Wuxi, Jiangsu Province, China. ",
            "Wuxi Xihui Park is located in the western suburbs of Wuxi, Jiangsu Province, covering an area of 90 hectares.",
            "Lingshan Scenic Area in Shangrao, Jiangxi, China, covers 160 sq. km and is nationally recognized. Its picturesque mountains earned it the name \"Sleeping Beauty.\" ",
            "Dangkou Ancient Town, located 25 kilometers from downtown Wuxi, is situated at the junction of Wuxi, Suzhou, and Changshu in Jiangsu, China. ",
            "Qingming Bridge Ancient Canal Scenic Area is located at the southern end of downtown Wuxi, covering an area of about 44 hectares. ",
            "Taihu Pearl Joy Park (Wuxi City Zoo) draws inspiration from advanced zoo concepts both domestically and internationally in its exhibition style and park design. ",
            "Wuxi Museum, located at 100 Zhongshu Road, Liangxi District, Wuxi, Jiangsu, is a comprehensive museum housing nearly 40,000 cultural relics. ",
            "Huishan Temple, located in Wuxi's Huishan Ancient Town, Jiangsu, has a rich history dating back to the Southern and Northern Dynasties. ",

    };
    //扬州市 
    private  String [] yangz = {"shouxihu","damingsi","geyuan","heyuan","dongguanjie","guazhougudu","hanlingyuan","guyunhe","mengchengyi","shaoboguzhen"};
    private int[] yzicons = {R.drawable.shouxihu,R.drawable.damingsi,R.drawable.geyuan,R.drawable.heyuan,R.drawable.dongguanjie,R.drawable.guzhougudu,R.drawable.hanlingyuan,R.drawable.guyunhe,R.drawable.mengchengyi,R.drawable.shaoboguzhen};
    private String[] yzbuy = {"¥100","¥30","¥135","¥45","free","free","¥30","free","free","free"};
    private String[] yzintroduce = {"Slender West Lake, formerly known as Guarantee Lake, is a tributary of the Yangzhou section of the Beijing-Hangzhou Grand Canal. It is located at No. 28, Dahongqiao Road, Hanjiang District, Yangzhou City, Jiangsu Province, with a water area of 700 mu. Slender West Lake is made up of the ancient Sui and Tang Grand Canal system and the moat of the Sui, Tang, Song, Yuan, Ming and Qing dynasties. Slender West Lake in the Qing Dynasty Kangqian period has formed a basic pattern. Slender West Lake is a typical small shallow lake, and its source water comes from the Beijing-Hangzhou Grand Canal.",
            "Daming Temple, located in the northwest suburb of Yangzhou City, Jiangsu province, was approved by The State Council to be included in the sixth batch of national key cultural relic protection units list. Daming Temple was originally built in the Southern Song Dynasty during the reign of Emperor Xiaowu and Daming (457-464). In the Qing Dynasty, because of the taboo \"Daming\" two characters, was once along the \"Qiling temple\", Qianlong 30 years of the emperor's handwriting book \"rech the title of the law of the net temple\". In 1980, Daming Temple restored its original name. In 2002, it was rated as the national AAAA level scenic spot. According to the History of the Old Five Dynasties, in 887, Yang Xingmi, the governor of Luzhou, set up camp here during the war with Qin Yan, the governor of Xuanzhou." ,
            "The park is located in the northeast corner of Guangling District, Yangzhou City, Jiangsu Province, No. 10 Yanfu East Road, has won the third batch of \"National Key Cultural Relic Protection Units\" and \"the first batch of National Key parks\" title. This private garden of Yangzhou salt merchant's mansion in Qing Dynasty is famous for planting green bamboo everywhere and winning with rockery in spring, summer, autumn and winter. It was built as a residential garden on the basis of the \"Shouzhi Garden\" of the Ming Dynasty by Huang Zhijun, the general manager of the salt industry of the Lianghuai Province, in the 23rd year of Jiaqing (1818 AD). The garden is famous for its stone folding art. The rockery in spring, summer, autumn and winter is made of bamboo shoot stone, lake stone, Huangshi and Xuanshi, which integrates the principles of garden building and landscape painting, and is praised as \"the only example in China\" by the garden master Mr. Chen Congzhou.",
            "He Garden, located at No. 66, Xuningmen Street, Yangzhou City, Jiangsu Province, also known as \"Post Xiao Villa\", is a Chinese classical garden architecture built in the mid-Qing Dynasty, known as the \"first garden of the late Qing Dynasty\", with an area of more than 14,000 square meters and a construction area of more than 7,000 square meters. The garden is made around the world by He Zhi in the Guangxu period of Qing Dynasty. The Shishan House is a masterpiece of master Shi Tao. He Yuan is a national key cultural relic protection unit, national AAAA level tourist scenic spot, one of the first 20 key parks in the country, the main attractions are complex corridor, horse riding building, etc. He Garden is a representative work of Yangzhou gardens in the late Qing Dynasty, which is a national key cultural relic protection unit.",
            "Dongguan Street is one of the most representative historical streets in Yangzhou. It reaches the ancient canal in the east and the National Day Road in the west, with a total length of 1122 meters. The original street pavement is paved with long slabs and stones. This street was not only the main water and land traffic of Yangzhou before, but also the center of commerce, handicrafts and religious culture. The street is bustling with businesses and businesses, and business is booming. Lu Chen line, oil rice square, fresh fish line, eight fresh line, melon and fruit line, bamboo line nearly a hundred.",
            "Guazhou Ancient Ferry is located at the intersection of Yangzhou Grand Canal and Yangtze River in the lower reaches of Yangzhou City, Jiangsu Province. Runyang Highway Bridge, Zhenyang Ferry and Yangzhou Port are adjacent to it, and Zhenjiang Jinshan Temple is opposite to the park across the river. \"Bian flow, Si flow, flow to Guazhou ancient ferry\", \"Jingkou Guazhou one water\". Jian Zhen, a famous monk in the Tang Dynasty, set sail from here to Japan, and Emperor Kangqian and poet Moke of all dynasties passed through Guazhou, leaving behind many popular poems. The story of Du Shiniang's angry sinking of treasure boxes happened here.",
            "Yangzhou Hanling Garden, also known as the Han Guangling King's Tomb Museum, is the first Western Han Dynasty tomb excavated in 1979 in Gaoyou City Gaoyou Lake West New District Shenju Mountain, the area of its wood zi is 18 times larger than the Hunan Mawangdui Han tomb, representing the highest etiquette of the ancient funeral \"yellow intestinal scum\", and unearthed Jin Lu jade clothing fragments. Preliminary research indicates that the owner of the tomb is Liu Xu and his wife, King of Guangling in the Western Han Dynasty. Hanling Garden is one of the important tourist attractions in the Slender West Lake and Shugang Scenic spot, and it is a pearl in the scenic spot. It is AN exhibition center of CULTURAL relics and gardens, reflecting Yangzhou Han CULTURE, with undulating terrain, simple and powerful buildings, lush trees and green grass. It is a national AAAA scenic spot.",
            "The Grand Canal is a great project on the eastern plain of China. It is a great water conservancy construction created by the ancient working people of China. It is the longest canal in the world, and also the earliest and largest canal in the world. Founded in 486 BC, the Grand Canal consists of three parts: the Grand Canal of Sui and Tang Dynasties, the Grand Canal of Beijing-Hangzhou and the Grand Canal of Eastern Zhejiang. It is 2,700km long and spans more than 10 latitudes of the earth, covering Beijing, Tianjin, Hebei, Shandong, Henan, Anhui, Jiangsu and Zhejiang provinces and municipalities directly under the Central government, running through the North China Plain. Connecting the five major river systems of the Hai River, the Yellow River, the Huaihe River, the Yangtze River and the Qiantang River, the Grand Canal is the main artery of North-South communication in ancient China. By 2020, the Grand Canal has a history of more than 2,500 years.",
            "Yucheng Post, located in Guanyi Lane, Nanmen Street, Gaoyou City, Yangzhou City, Jiangsu Province, was built in the eighth year of Hongwu of Ming Dynasty (1375). It is the largest and best preserved ancient post station in China. In the second year of Longqing (1568), Zhao Laiheng, the governor of the state, rebuilt according to the old system. Since then, the governor Zhang Desheng, Feng Xin, Zhu Ronggui and other people have rebuilt or added. It covers an area of more than 16,000 square meters. There were more than 100 halls, including post station, post house, Qin post mansion, post house, military attache hall, horse temple, horse barn, warehouse, prison room, post dormitory and other buildings. Huanghua Hall and Resident Hall are the main buildings of Yucheng Yi. In the eighth year of Hongwu of Ming Dynasty (1375), Huang Keming, the governor of the state, built Yucheng Post at the south gate of Gaoyou City. Ming Yongle first year (1403), Zhizhou Wang Jun rebuilt. In the 36th year of Jiajing (1557), the Japanese pirates invaded the country, and the Yucheng Post was destroyed in the fire of war. In the second year of Longqing (1568), Zhao Laiheng, the governor of the state, rebuilt according to the old system. Since then, the governor Zhang Desheng, Feng Xin, Zhu Ronggui and other people have rebuilt or added. After the Revolution of 1911, the Yucheng Post was ordered to be withdrawn. After the founding of the People's Republic of China, Yucheng Post was used as a residential residence. In 1993, Gaoyou Municipal People's Government presided over the renovation and repaired the main building of the post station, forming a beautiful Ming and Qing residential complex together with the Nanmen Ancient Street. In 2003, the State Post Bureau established Yucheng Post as the \"National patriotic Education base for postal workers\", and the State Administration of Cultural Heritage confirmed the Post Museum as one of the 100 national characteristic museums.",
            "Shaobo ancient town, is one of thousands of historical and cultural towns, has a history of more than 1600 years. In 2008 Shaobo was included in the fourth batch of Chinese historical and cultural town list. In 2014, Shaobo Ancient Road of Ming and Qing Canal, Shaobo Wharf group, Shaobo ancient Dike, Shaobo old lock and main line of Huaiyang Canal (Shaobo section) were included in the World Heritage Protection list of the Grand Canal of China. Shaobo Ancient town is the ancient town with the most heritage points along the Grand Canal."};

    //寿宁县
    private String[] shou = { "Universal Dinosaur City","Tianmu Lake","Yancheng","Tianning Zen Temple","Jintan Maoshan",};
    private int[] shouicons = {R.drawable.xixi,R.drawable.xipu,R.drawable.lingfeng,R.drawable.nanshan,R.drawable.xiadangcun,R.drawable.shuikufengjingqu,R.drawable.shengtaichayuan,R.drawable.xi,R.drawable.mindongjiuzhi,R.drawable.chelinggudao};
    private String[] shoubuy = {"¥138","free","free","free","free"};
    private String[] shouintroduce = {"Changzhou Global Dinosaur City Leisure and Tourism Area covers an area of more than 4,800 acres. It is a comprehensive tourist resort integrating theme parks, cultural performances, hot spring leisure, recreational business and animation creativity. It is the most famous dinosaur theme park in China. One of the most famous attractions in Changzhou, it is also a must-visit internet celebrity destination in Changzhou.",
            "Tianmu Lake has an ecological reserve of 300 square kilometers. There are two large national-level reservoirs, Shahe and Daxi, in the area. It is known as the \"Pearl of the South of the Yangtze River\" and the \"Green Wonderland\" and is a famous scenic spot in Changzhou. Tianmu Lake covers an area of 17.92 square kilometers and is mainly composed of the Landscape Garden Scenic Area, Nanshan Bamboo Sea Scenic Area and Yushui Hot Spring Scenic Area.",
            "China Spring and Autumn Yancheng Tourist Area, also known as Yancheng, Spring and Autumn Yancheng, includes the Spring and Autumn Yancheng Ruins, Yancheng Spring and Autumn Paradise, Yancheng Wildlife World, Yancheng Traditional Commercial Neighborhoods and Yancheng Baolin Temple.",
            "Tianning Temple, which is listed in the list of famous scenic spots in Changzhou, was built during the Zhenguan period of the Tang Dynasty. It is one of the birthplaces of Buddhist music and is one of the birthplaces of Buddhist music. Together with Jinshan Temple in Zhenjiang, Gaomin Temple in Yangzhou and Tiantong Temple in Ningbo, it is also known as the four major Zen jungles in China.",
            "Maoshan is the most famous Taoist mountain in Jiangsu Province and the birthplace of the Shangqing sect of Taoism. It is called the \"Shangqing Zongtan\" by Taoists. Since the Tang and Song Dynasties, it has enjoyed the reputation of \"the first blessed place and the eighth cave\" of Taoism. Located in Changzhou, Jintan Maoshan Scenic Area sits on Maoshan, a famous Taoist mountain. It has rich humanities and beautiful environment. It has been known as the \"Eighth Cave and the First Blessed Land\" since ancient times.",
    };

    //连云港
    private String[] lian = {"Mount Huaguoshan","Haizhou Ancient City","KongWang Mountains","YunTai Mountains","Taohuajian Mountain Stream","Eden Garden","LianYunGang Museum","Qinshan Island","And Lake Wetland Park","Dayi Mountains"};
    private int[] lianicons = {R.drawable.huaguoshan,R.drawable.haizhou,R.drawable.kongwang,R.drawable.yuntai,R.drawable.taohua,R.drawable.yidian,R.drawable.lianyunbo,R.drawable.qinshandao,R.drawable.hean,R.drawable.dayi};
    private String[] lianbuy = {"¥90","¥50","¥40","¥80","¥30","¥60","Free","Free","Free","¥30"};
    private String[] lianintroduce ={
             "Huaguo Mountain is the highest peak of Jiangsu mountains. Huaguo Mountain has 136 peaks, the main peak of Huaguo Mountain is Jade peak, elevation 624.4 meters.",
            "Haizhou is the source, origin and starting point of Lianyungang. With a history of more than 2,000 years, the ancient city has been the political, economic and cultural center.",
            "Kongwang Mountain is a national 4A-level scenic spot, with a thousand-year unique mountain in the annals of Chinese culture.",
            "Yuntai Mountain on the Sea is a famous tourist resort along the Jiangsu coast. This mountain was originally an island in the sea, and then evolved into land.",
            "Taohuajian is located at the southern foot of Jinping Mountain. In 2014, it was rated as an \"AAAA\" scenic spot by the National Tourism Administration.",
            "The scenic area covers an area of 10,000 acres, including 6,500 acres of pear orchards, 3,200 acres of hundred gardens, and about 300 acres of Danish fairy tale town.",
            "Lianyungang Museum belongs to the Lianyungang Municipal Bureau of Culture, Radio, Television and Tourism.",
            "There are more than 20 major scenic spots on the island, such as Chess Bay, which have always enjoyed the reputation of \"Qinshan Ancient Island, Yellow Sea Wonderland\".",
            "And Lake Wetland Park is a typical wetland ecosystem, with abundant terrestrial and aquatic animal and plant resources, and a wide variety of plant species.",
            "Dayi Mountain is a national 4A-level scenic spot, known as the first sacred mountain in Pingchuan, Huaibei."};

    //柘荣县
    private String[] zhe = {"柘荣鸳鸯草场","九龙井","宁德东狮山","仙都胜境景区","柘荣县九龙井水利风景区","东源古建筑群","凤岐吴氏大宅","百丈岩八仙洞","蟠桃映翠","青岚湖水利风景区"};
    private int[] zheoicons = {R.drawable.zherong,R.drawable.jiulongjing,R.drawable.dongshishan,R.drawable.xiandujingqu,R.drawable.zherongjiulong,R.drawable.dongyuangujianzhu,R.drawable.fengqiwushidazhai,R.drawable.baizhangyanbaxiandong,R.drawable.pantao,R.drawable.qinglanfengjingqu};
    private String[] zhebuy = {"¥55","¥45","free","free","¥60","free","free","free","free","free"};
    private String[] zheintroduce ={"柘荣鸳鸯头草场位于省级风景名胜区东狮山南侧，海拔980米至1110米之间，距柘荣县城约5公里。这是一片面积约5000亩，四周被阔叶和针叶混交林包围的草场。周边的山峰巍峨挺拔，充满阳刚之壮美。草场核心区的山岚远远望去，又如人工泥塑的微型盆景。草山低矮，绵延起伏;山脊走势，富有韵律，节奏中蕴含着温文典雅之美妙!",
            "九龙井风景区是生态示范区、省级园林县城福建省柘荣县生态旅游的一朵奇葩，是世界地质公园——白云山石臼群的重要组成部分，也是泛太姥旅游圈、闽东亲水游线路的重要组成部分，她集形态各异的龙井群、冰臼奇观、瀑布、青竹走廊、原生态山谷等景观于一体，由九龙井生态景区、金溪漂流景区（筹建中）、石山洋生态观光区三部分组成。",
            "东狮山耸立在柘荣县城东2公里处，面积25平方公里。东狮山是太姥山脉的主峰，海拔1480米，气势雄伟。东狮山属花岗岩地貌，岩体、岩洞千姿百态，有皤桃岩、马头岩、象鼻岩。千笋石、万书岩。擎天柱。玉屏洞、灵峰洞、“仙人锯板”等景点，山下龙并瀑布落差100余米，宽6米，蔚为壮观。",
            "仙都胜境景区位于风景区南部，面积约1.4平方公里，以良好的田园和村居民俗为景观特色。主要有梨坪、彭琪垄两个自然村。东狮山也是多彩多姿的生物世界。据初步调查，整个景区栖有长尾雉、黄头雉、真鸟仔、鹁鸪、翠鸟、画眉等四十多种飞禽。“百啭千声随意啼，山花红紫树高低。”徜徉于山水之间，畅游于林泉之下，倾听鸟儿浅唱低吟，无比惬意。东狮山景区内花木品种众多，有桃花、樱花、百合、空谷幽兰，杜鹃更是一绝，每逢春夏之交，满山花开，有红、有白、有黄、有紫，绚丽多彩，白的素洁、红的热烈，顾盼生姿、煞是可爱，“此时逢国色，何处觅天香。”",
            "九龙井水利风景区，她集形态各异的龙井群、冰臼奇观、原生态风貌等景观于一体，两岸青山滴翠、谷中流水潺潺。水以溪水为练，将蝙蝠井、金猪井、观音井、龙门井、阴阳井、瓮子井、长河井、双心井、太阳井等九个龙井串连，动静结合，舒展妩媚和神奇；溪以石为奇，千姿百态的河床、风格迥异的冰臼，一段一景，石得水而活，悬泉飞流的瀑布，深不可测的龙井，不禁让人流连忘返，纷纷赞叹大自然的鬼斧神工。",
            "东源古建筑群位于柘荣县东源乡东源村，年代为清、民国。由吴氏宗祠戏楼、古书堂、培凤亭、古井、粉墙厝和吴成故居组成。",
            "该宅建于清乾隆年间（1736—1795），坐北向南，依山而建。内、外两重围墙，四周花园，通面阔76米，通进深99.1米，总占地面积7531.6平方米（11.3亩），其中建筑占地面积2391.52平方米。第一级台基宽56米，深9.5米。单檐砖木构穿斗式硬山顶门楼，三开间。二门为青砖三合土混合结构，三楼牌坊式顶盖，横额正面行书“凤岐聚秀”，内面行书“仁义为庐”。第二至第五级台基上建四座相隔一定距离的横向楼房，两旁建厢房，正中天井两旁建纵向单扇窗阁式廊庑，将横向楼房隔成三个纵向大院，第三级台基与第四级台基之间横向建空斗防护砖墙，将建筑群分隔成前后两大区域。",
            "百丈朝暾景区位于风景区中部，蟠桃映翠景区的东北面，面积约3.54平方公里，以绝岩峭壁，幽深洞岩为景观特色，主要景点有：罗隐湾、土地岩、清云宫、石门、石将军、风吹洞、石门楼、仙人脚印、观日台、灵岩洞、通真洞、灵峰洞、何仙姑洞、百丈灵岩等。",
            "蟠桃映翠景区位于风景区中西部，面积约1.88平方公里，以溪涧风光为景观特色，洞奇石怪，风景资源多而集中。主要景点有：迎宾亭、仙景岭、普光寺、仙掌泉、蟠桃溪、蟠桃岩、金蟾朝圣、蟠桃洞、马头岩、象鼻岩、玉屏洞、南天门、三曹院遗址、白马宫等。",
            "青岚湖水利风景区位于宁德市柘荣县，依托青岚水库而建，景区面积19.34平方公里，其中水域面积0.71平方公里，属于水库型水利风景区。区域内水体澄清，水质I级;负氧离子含量高，空气质量指数一级；森林覆盖率为86%,水土流失综合治理率高达96%；动植物资源丰富，生态环境优良。"};

    //南通市
    private String[] Nantong = {"the Wolf Mountain","Seyuan Garden","Nantong Forest Safari Park","Zhang Jian Memorial Hall","Junshan Mountain","Yangkou port","Xiaoyangkoubeauty spot","HAOHE"};
    private int[] Nantongicons = {R.drawable.wolfm,R.drawable.seyuan,R.drawable.amp,R.drawable.zj,R.drawable.junshan,R.drawable.yangkou,R.drawable.xiaoyangkou,R.drawable.haohe};
//    private int[] Nantongicons = {R.drawable.tailaoshan,R.drawable.niulang,R.drawable.cuijiao,R.drawable.jiulixipu,R.drawable.xiaobailu,R.drawable.dayushan,R.drawable.yushandao,R.drawable.chixicun};
    private String[] Nantongbuy = {"¥70","Free","¥180","Free","Free","Free","¥40","Free"};
    private String[] Nantongintroduce ={
            "Nantong Wolf Mountain Scenic Area is a provincial scenic area of Jiangsu Province and a national AAAA scenic area, located on the north bank of the Yangtze River.",
            "It is the largest plant ornamental garden in Nantong, Jiangsu Province, with more than 200 kinds of rare tree species, including davidia davidia and Taiwan fir.",
            "Nantong Forest Safari Park is a large-scale comprehensive tourism project integrating tourist attractions,leisure and entertainment and popular science education.",
            "Zhang Jian Memorial Hall, located in Changle Town, Haimen District, Nantong City, Jiangsu Province, was built in memory of Mr. Zhang Jian.",
            "Junshan is a mountain in Chongchuan District of Nantong City. When it was an island in the sea, it was said that it was a place for the King of Qin to camp troops.",
            "Yangkou Port, is a typical offshore deep-water port, located in the north wing of the Yangtze River estuary and the west bank of the Yellow Sea.",
            "Nantong Xiaoyangkou Scenic Spot is located in Rudong Coastal Tourism Economic Development Zone, Jiangsu Province, within 1.5 hours of the economic circle;",
            "Haohe Scenic Area of Nantong City, located in the center of the national historical and cultural city Nantong, is one of the most complete preserved ancient moat in China.",
    };

    //霞浦县
    private String[] xiapu = {"杨家溪","罗汉溪景区","赤岸村","霞浦松山天后圣母行宫","空海大师纪念堂","霞蒲滩涂","北岐滩涂","霞浦县城","小皓海滩","七都溪"};
    private int[] xiapuoicons = {R.drawable.yangjiaxi,R.drawable.luohanxi,R.drawable.chianqiao,R.drawable.shengmuxinggong,R.drawable.jiniantang,R.drawable.xiaputantu,R.drawable.beiqitangtu,R.drawable.xiapuxiancheng,R.drawable.xiaohaohait,R.drawable.qiduxi};
    private String[] xiapubuy = {"¥100","¥178","free","free","free","free","free","free","free","free"};
    private String[] xiapuintroduce ={"杨家溪位于霞浦县牙城镇境内。拥有纬度超北的古榕树群和江南超大的纯枫叶林。其中值得一提的是一株“榕树王”，它的树龄已有800多年，树干周长12.6米，冠幅直径51米，高30米，树干中空，有7个洞口，洞内可容数人。每当早晨的阳光斜斜地洒进树林，斑驳的树影在光影的晃动下展示着它的魔法，营造了一种如梦似幻的感觉。",
            "罗汉溪，源于福建省霞浦县柏洋乡洋里土勃头村，呈西北—东南流向。溪流经横江、溪西、洋沙溪，于下村汇集桐油溪支流，经百步溪，出水磨坑、大桥头入后港海。其主要支流桐油溪，源于水门乡百笕村，流域面积42平方公里，河道长度17公里。罗汉溪是霞浦县三大河流之一，蕴含丰富的水资源，是霞浦县最重要的集储水发电、农业灌溉、城市饮用水为一体的水源河流。罗汉溪又是具有独特山水景色的河流，第四纪冰川遗迹冰臼、巨石滩、峡谷、岩崖瀑布、两岸山景，绘就秀丽的山水画卷，有着重要的旅游观光价值。",
            "位于霞浦县城东北5公里处，在古代曾为重要的海港，因其海岸山石呈赤红色而得名。",
            "霞浦松山天后圣母行宫，建于北宋天圣年间，历史上别称「历史上别称「妈祖行宫」、「靖海宫」、「阿婆宫」，是继湄洲妈祖祖庙之后的第一个天后圣母行宫，是八闽创建较早，声名较着的祷祀“海峡和平女神”宫庙。素有「行宫之尊」的美誉。",
            "“空海大师纪念堂”是由中日双方出资于1994年5月21日建成的。它与1984年在西安青龙寺遗址兴建的“惠果、空海大师纪念堂”遥相辉映。绿草如茵宽广平坦的空埕右侧竖着的两块石碑，刻着建盖该堂经过和有关单位及个人芳名的碑文；左侧也对称地竖着两块石碑，一个是新居祐政先生的《赤岸镇赞歌》七言排律一首，另一个是高野山大学教授静慈圆写的“重访赤岸”七言绝诗四首：“高野残香冻未消，闽江桃杏正娇娇，兹行不为寻芳去，开祖苦心共此朝；求法不辞千里遥，漂流赤岸骨心销。",
            "滩涂，是陆地和大海之间的纽带。现代汉语词典是这么解释的：河流或海流夹杂的泥沙在地势较平的河流入海处或海岸附近沉积而形成的浅海滩。仅福建省就有近300万亩的滩涂，在漫长的岁月长河中，经过无数次的潮起潮落，滩涂形成了自己特有的景观，尤其是这些年迅速发展起来的近海滩涂养殖，那些渔排木屋，那些小舟鱼网，那些浮标竹竿，随着潮水的涨退，变幻着无穷的组合，吸引来无数摄影人的目光。",
            "霞浦县松港街道北岐村海边风光如画，影友如织。霞浦滩涂风光旖旎，2012年被《数码摄影》杂志列为“中国最值得拍摄的80个绝美之地”之一，并成为22大摄影胜地之一被重点推荐，每年前来拍摄滩涂风光的国内外影友多达20多万人次。",
            "霞浦全县14个乡镇有10个靠海，海洋优势尤为突出，境内海岸线长404公里，占全省八分之一，浅海滩涂面积104万亩，港湾众多，岛屿星罗棋布，有大小岛屿196个，港口138个。霞浦的三沙港距台湾的基隆港126港里，渔船朝发夕至，而且三沙与台湾语言相通，习俗相近。",
            "小皓的海滩是摄影者来到霞浦一定要前往的一站，潮水的涨落、季节的变换、月落日升，小皓也在每天以崭新的姿态呈现出她的五彩斑斓。同样的潮水，不同样的光影，同样的劳作，不同样的心情，摄影者总能创作出无尽的精美作品。小皓主要以沙质滩涂为主，当在顺光位置拍摄时能看到山下那一块块巨大的金黄滩涂随波而变，在逆光位置观赏这片滩涂时，镜头中那一条条从沙滩上流淌过的溪水，化作蜿蜒曲折的滩涂动脉，闪烁着迷人的银色光芒，令人兴奋不已。",
            "溪水流程20多公里，清澈见底，其间潭、濑、滩交替分布。山绿水碧，稀有树木花早异彩纷呈，樟树、枫树、榕树、花竹、万竹、金竹、杜鹃、黄杨、芦苇成片分布。“文广断船”、“金龟戏曾鳖”、“观音坐莲”等30多处人文景观，栩栩如生，维妙维肖。溪中盛产香鱼、龙鳗、毛蟹、甲鱼、鲤鱼等淡水珍品，还有鸳鸯、绶带、水獭等珍稀动物。"};


    private String[] qita = {"古田钱来山景区","Xihui Park","太姥山","牛郎岗海滨景区","柘荣鸳鸯草场","九龙井","霍童古镇","宁德东湖水利风景区","福建支提山国家森林公园"};
    private int[] qitaicons = {R.drawable.gutianxian,R.drawable.xihuipark,R.drawable.tailaoshan,R.drawable.niulang,R.drawable.zherong,R.drawable.jiulongjing,R.drawable.huotongguzheng,R.drawable.shuili,R.drawable.senglin};
    private  String[] qitabuy = {"¥44","free","¥90","¥35","¥55","¥45","free","free","free"};
    private String[] qitaintroduce={"钱来山风景区位于福建省宁德市古田县大桥镇钱厝村，是福建首个“钱文化”主题旅游风景区。景区总面积666平方公里，以景区出入口为中心，由荷花梯田、樱花谷、820山谷、飞瀑谷、有钱谷和谷里农场6个片区组成，呈环状分布，游步道总长度约3.8公里。钱来山风景区平均海拔820米，夏季从早到晚均温27℃，是福州周边又一避暑胜地。",
             "Wuxi Xihui Park is located in the western suburbs of Wuxi, Jiangsu Province, covering an area of 90 hectares.",
            "太姥山以花岗岩峰林岩洞为特色，融山、海、川和人文景观于一体，拥有山峻、石奇、洞异、溪秀、瀑急等众多自然景观以及古刹、碑刻等丰富的人文景观，在这里，可以登山、观海、探洞，也可以泛溪、寻古、采风……",
            "牛郎岗海滨这里气候冬暖夏凉，素以“碧海金沙好消夏”而吸引各地游客慕名而至。牛郎岗海滨沙滩平坦、明净，环山绿树成荫，周围礁石造型各异，有鸳鸯礁、织女洞、海上一线天等自然景观。",
            "柘荣鸳鸯头草场位于省级风景名胜区东狮山南侧，海拔980米至1110米之间，距柘荣县城约5公里。这是一片面积约5000亩，四周被阔叶和针叶混交林包围的草场。周边的山峰巍峨挺拔，充满阳刚之壮美。草场核心区的山岚远远望去，又如人工泥塑的微型盆景。草山低矮，绵延起伏;山脊走势，富有韵律，节奏中蕴含着温文典雅之美妙!",
            "九龙井风景区是生态示范区、省级园林县城福建省柘荣县生态旅游的一朵奇葩，是世界地质公园——白云山石臼群的重要组成部分，也是泛太姥旅游圈、闽东亲水游线路的重要组成部分，她集形态各异的龙井群、冰臼奇观、瀑布、青竹走廊、原生态山谷等景观于一体，由九龙井生态景区、金溪漂流景区（筹建中）、石山洋生态观光区三部分组成。",
            "霍童古镇是久负盛名的千年古镇，也是中国民间文化艺术之乡。霍童原名霍山，西周时霍桐真人在此地修炼，故名。霍童的美不仅在于幽美的生态环境，千年的文化积淀也使得这里散发着更大的古镇魅力。说到霍童，不可不提霍童线狮，作为第一批国家级非物质文化遗产，霍童线狮以其独特的艺术表现形式受到人们的喜爱，以其精彩的表演博得台下的观众的阵阵喝彩，这些的种种都奠定了霍童线狮在中国民俗文化中的地位，有“中华绝活”的美名。",
            "宁德东湖水利风景区是国家级水利风景区，位于福建省宁德市。总面积4.48平方公里，其中水域面积2.91平方公里，包括环东湖南、北岸公园和大门山、乌龟山、金蛇山等\"一湖两岸三山\"景观。风景区湿地面积较广，是水鸟觅食与栖息的理想场所。请看正文了解宁德东湖水利风景区的概况及旅游攻略。宁德东湖水利风景区东起金马海堤-金蛇头码头西接福宁北路，南达塔山路-南湖滨路，北至闽东中路-北湖滨路。",
            "第一高峰\"旗山\"好风光地处山区的虎贝镇，海拔高，境内山峦起伏，从而形成了独特的高山气候与风景。第一旗山，因其形如风中之旗而得名。它位于虎贝镇境内文峰、梅鹤村附近，堂义村后山。上山有多条路线:其一虎贝直接上山，其二虎贝黄家村后山上山，其三虎贝乡彭家村后山上山(路程最近)，也是唯一一条公路路线，通常登山游客都选此条，被称通往宁德高峰\"第一旗\"的必备之路。其四，堂义村后山走最美，经过竹林，森林，小溪，还有野菜，野果采摘,经过兔耳岭景区，这里风景独好，是旅游最佳路线。"
    };
    private String[] qitaname ={"古田县","Wuxi","福鼎市","福鼎市","柘荣县","柘荣县","蕉城区","蕉城区","蕉城区"};
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
    private int[] iconstwo = {R.drawable.sanduao,R.drawable.huotongguzheng,R.drawable.shuili,R.drawable.senglin,R.drawable.yangzhong,R.drawable.shangjinbei,R.drawable.hongshui,R.drawable.helin,R.drawable.bowuguan,
            R.drawable.gutianxian,R.drawable.cuipinghu,R.drawable.qiyunshi,R.drawable.gutianxishan,R.drawable.changyangxunshi,R.drawable.jinzhonghushanzhuan,R.drawable.fenglinshi,R.drawable.fenglinshizuo,R.drawable.changlinshi,R.drawable.yuanyinguju,R.drawable.gutianlinshuigong,
            R.drawable.yuantouzhu,R.drawable.sanguocheng,R.drawable.xihuipark,R.drawable.lingshan,R.drawable.dangkoutown,R.drawable.guyunriver,R.drawable.wuxizoo,R.drawable.wuximuseum,R.drawable.huishantemple,
            R.drawable.liyuxi,R.drawable.jiulongfengjingmingshengqu,R.drawable.chenxiaogucun,R.drawable.zhouningdishuiyan,R.drawable.zudian,R.drawable.zhouningbanruosi,R.drawable.jiulongshiku,R.drawable.gaoshanmingzhu,R.drawable.puyuanzongsi,
            R.drawable.xixi,R.drawable.xipu,R.drawable.lingfeng,R.drawable.nanshan,R.drawable.xiadangcun,R.drawable.shuikufengjingqu,R.drawable.shengtaichayuan,R.drawable.xi,R.drawable.mindongjiuzhi,R.drawable.chelinggudao,

            R.drawable.baiyunshan,R.drawable.langu,R.drawable.tianchi,R.drawable.jinshan,R.drawable.lingfeng,R.drawable.liancun,R.drawable.shezu,R.drawable.wenhuaguan,R.drawable.wenhuaguan,R.drawable.yanghongse,R.drawable.fuanbowuguan,

            R.drawable.zherong,R.drawable.jiulongjing,R.drawable.dongshishan,R.drawable.xiandujingqu,R.drawable.zherongjiulong,R.drawable.dongyuangujianzhu,R.drawable.fengqiwushidazhai,R.drawable.baizhangyanbaxiandong,R.drawable.pantao,R.drawable.qinglanfengjingqu,
            R.drawable.tailaoshan,R.drawable.niulang,R.drawable.cuijiao,R.drawable.jiulixipu,R.drawable.xiaobailu,R.drawable.dayushan,R.drawable.yushandao,R.drawable.chixicun,R.drawable.shilancun,R.drawable.chengbao,R.drawable.diantoumazu,R.drawable.fudingxiangshansi,R.drawable.tianmenlingyouqu,R.drawable.gubao,R.drawable.lingfengshi,R.drawable.guoxingshi,R.drawable.xiyanglaorenqiao,R.drawable.fuyao,R.drawable.ziguo,
            R.drawable.yangjiaxi,R.drawable.luohanxi,R.drawable.chianqiao,R.drawable.shengmuxinggong,R.drawable.jiniantang,R.drawable.xiaputantu,R.drawable.beiqitangtu,R.drawable.xiapuxiancheng,R.drawable.xiaohaohait,R.drawable.qiduxi

    };
    private String[] buytwo={           
            "¥32","free","free","free","free","¥35","free","free","free",
            "¥44","¥80","¥68","free","free","free","free","free","free","free","free",
            "¥120","¥73","free","free","free","free","free","free","free","free","free","¥70","¥70",
            "¥60","free","No ticket","free","free","free","No ticket","free","free",
            "¥138","free","free","free","free","free","free","free","free","free",
            "¥90","¥50","¥40","¥80","¥30","¥60","Free","Free","Free","¥30",
            "¥55","¥45","free","free","¥60","free","free","free","free","free",
            "¥70","Free","¥180","Free","Free","Free","¥40","Free",
            "¥100","¥178","free","free","free","free","free","No ticket","free","free"
    };
    private String[] introducestwo = {
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
            "齐云寺建于北宁三年(1066年),位于凤埔与平湖交界,离古田新城20公里,地理条件优越.东靠古田翠屏湖,毗连宁德三都澳;北接屏南白水洋,延伸周宁鲤鱼溪,是通过古田火车站对接黄山、武夷山国家红色旅游线路必经之路。这座千年古刹，寺宇巍峨壮观，殿亭布局合理。建有大雄宝殿，地藏殿，韦伏殿，观音堂、积善堂、客堂，还有莲池宝塔，放生池。去年又增设了红色革命据点纪念堂。寺内塑许多金光闪闪的佛像。寺前竖有清乾隆三十八年福州府古田县正堂的“仁谳”石碑；寺门两柱刻制“齐皆积享先三宝，云可霖福四方”的对联；两条浮雕彩龙，巧夺天工。寺外还建有土地殿、迎客亭、半山歇息亭、水尾亭；去年又在寺前竖立一尊高大的石雕送子观音。",
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
 
            "位于周宁县城西五公里处的浦源村中。鲤鱼溪源于海拔1448米的紫云山麓，汇数十条山涧清泉奔流而下，峰回水转，至浦源村口水势顿减，五弯六曲穿村缓流而过",
            "九龙漈风景名胜区位于周宁县东南13公里处。这里峰奇石秀、峡谷幽深，龙江溪在危崖断壁之间层层跌落，形成了九级瀑布群。",
            "陈峭村，是大自然的厚赠，有瑰丽的日出、云海、星空，有磅礴的峭石、岩洞、峡谷，还有千年遗存的街巷、廊桥、民俗。然而，这里因为地处偏僻，交通不便，一直“养在深闺人未识”。",
            "滴水岩风景区位于福建省周宁县城西南30公里的洞宫山麓，与古朴典雅的鲤鱼溪、气势磅礴的九龙祭瀑布群齐名，名载《辞海》。古人题匾曰“八闽首景”，它邻近国家级自然保护区鸳鸯溪的核心地带，依霍童溪上游的叉溪、白水洋溪等处与政和、屏南、宁德三地毗连，山峦起伏，溪涧密布，风景秀丽，成为闽东霍童溪流域旅游风光带的一个重要组成部分。",
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
    TextView quyuname;
    ImageView fanhui;
    int f = 1;
    int count=0;
    EditText search_edit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhujiemian);

        Button button1 = (Button) this.findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           Intent intent1 = new Intent();
                                           intent1.setClass(ZhujiemianActivity.this,weather.class);
                                           startActivity(intent1);
                                       }
                                   }

        );



        
        name_receive = Result.getResult();
        fanhui = findViewById(R.id.imageView2);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ZhujiemianActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        quyuname =findViewById(R.id.didian);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                   name_receive = connect.dizhiname(1);
//                }catch (SQLException e){
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        quyuname.setText(name_receive);
         if(name_receive.equals("Suzhou")){
            name = suz;
            icons = szicons;
            buy = szbuy;
            introduces = szintroduce;
        }
        if(name_receive.equals("Nanjing")){
            name = nanj;
            icons = njicons;
            buy = njbuy;
            introduces = njintroduce;
        }
        if(name_receive.equals("Wuxi")){
            name = pingnan;
            icons = pingnanicons;
            buy = pingnanbuy;
            introduces = pingnanintroduce;
        }
        if(name_receive.equals("Yangzhou")){
            name = yangz;
            icons = yzicons;
            buy = yzbuy;
            introduces = yzintroduce;
        }
        if(name_receive.equals("Changzhou")){
            name = shou;
            icons = shouicons;
            buy = shoubuy;
            introduces = shouintroduce;
        }
        if(name_receive.equals("Lianyungang")){
            name = lian;
            icons = lianicons;
            buy = lianbuy;
            introduces = lianintroduce;
        }
        if(name_receive.equals("柘荣县")){
            name = zhe;
            icons = zheoicons;
            buy = zhebuy;
            introduces = zheintroduce;
        }
        if(name_receive.equals("Nantong")){
            name = Nantong;
            icons = Nantongicons;
            buy = Nantongbuy;
            introduces = Nantongintroduce;
        }
        if(name_receive.equals("霞浦县")){
            name = xiapu;
            icons = xiapuoicons;
            buy = xiapubuy;
            introduces = xiapuintroduce;
        }
        mRecyclerView = findViewById(R.id.recycle);
        mAdapter = new HomeAdapter();
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
//                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ZhujiemianActivity.this));
        mRecyclerView.setAdapter(mAdapter);
        mrecycleview = findViewById(R.id.recycletwo);
        madapter = new HomeAdapterTwo();
        mrecycleview.setLayoutManager(new LinearLayoutManager(ZhujiemianActivity.this));
        mrecycleview.setAdapter(madapter);
        Button search_button = findViewById(R.id.search_button);
        search_edit = findViewById(R.id.search);
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
                        intent.setClass(ZhujiemianActivity.this, DetailsActivity.class);
                        intent.putExtra("detail_iv", iconstwo[count1]);
                        intent.putExtra("detail_name", nametwo[count1]);
                        intent.putExtra("detail_buy",buytwo[count1]);
                        intent.putExtra("detail_introduce", introducestwo[count1]);
                        intent.putExtra("f",f);
                        intent.putExtra("name",dizhi[count1]);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ZhujiemianActivity.this,"Cannot find！",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ZhujiemianActivity.this,"Empty input！Please type in again",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from( ZhujiemianActivity.this).inflate(R.layout.recycle_item,parent,false));
            return holder   ;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.iv.setImageResource(icons[position]);
            holder.buy.setText(buy[position]);
            holder.name.setText(name[position]);
            holder.introduce.setText(introduces[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(ZhujiemianActivity.this,DetailsActivity.class);
                    intent.putExtra("detail_iv",icons[position]);
                    intent.putExtra("detail_name",name[position]);
                    intent.putExtra("detail_buy",buy[position]);
                    intent.putExtra("name",name_receive);
                    intent.putExtra("detail_introduce",introduces[position]);
                    intent.putExtra("f",f);
                    startActivity(intent);
                    finish();
                }
            });
        }



        @Override
        public int getItemCount() {
            return name.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            ImageView iv;
            TextView introduce;
            TextView buy;
            public MyViewHolder(View view){
                super(view);
                buy = view.findViewById(R.id.buy);
                name = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.iv);
                introduce=(TextView)view.findViewById(R.id.introduce);
            }
        }
    }
    class HomeAdapterTwo extends RecyclerView.Adapter<HomeAdapterTwo.MyViewHolder>{
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from( ZhujiemianActivity.this).inflate(R.layout.recycle_itemfirst,parent,false));
            return holder   ;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.iv.setImageResource(qitaicons[position]);
            holder.name.setText(qita[position]);
            holder.buy.setText(qitabuy[position]);
            holder.introduce.setText(qitaintroduce[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(ZhujiemianActivity.this,DetailsActivity.class);
                    intent.putExtra("detail_iv",qitaicons[position]);
                    intent.putExtra("detail_name",qita[position]);
                    intent.putExtra("detail_buy",qitabuy[position]);
                    intent.putExtra("detail_introduce",qitaintroduce[position]);
                    intent.putExtra("f",f);
                    intent.putExtra("name",qitaname[position]);
                    startActivity(intent);
                    finish();
                }
            });
        }



        @Override
        public int getItemCount() {
            return qita.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name;
            ImageView iv;
            TextView introduce;
            TextView buy;
            public MyViewHolder(View view){
                super(view);
                buy = view.findViewById(R.id.buy);
                name = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.iv);
                introduce=(TextView)view.findViewById(R.id.introduce);
            }
        }
}}

