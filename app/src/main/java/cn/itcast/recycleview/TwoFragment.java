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

import com.bumptech.glide.Glide;

import java.sql.SQLException;

/**
 * Created by DELL on 2022/10/10.
 */

public class TwoFragment extends Fragment {
    //
    private String[] suzname = {"jiangnanshouxi","meilugongguan","songhelou","xinmeihua"};
    private  String[]  szicons = {"https://dimg04.c-ctrip.com/images/100s16000000zse30E5B6_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/100p0d0000006rn7h40AF_R_1600_10000.jpg",
            "https://bkimg.cdn.bcebos.com/pic/7acb0a46f21fbe099301b5f061600c338644adf3?x-bce-process=image/watermark,image_d2F0ZXIvYmFpa2U5Mg==,g_7,xp_5,yp_5/format,f_auto",
            "https://youimg1.c-ctrip.com/target/1009190000017wec4F64B.jpg"};
    private String[] szbuy = {"¥90/person","¥150/person","¥80/person","¥70/person"};
    private String [] szintroduce = {"3F, Hubin Xintianxin Hubin Building, 158 Xinggang Street,","1 Zhongxin Avenue West","No. 72 Eunuch Lane, Gusu District","752 Renmin Road, Gusu District"};
    private String[] szpingfen = {"4.3 points","4.2 points","4.6 points","4.5 points"};
    private String[] sztime = {"10:00-13:00,16:00-21:30","11:00-22:00","10:30-21:00","10:00-14:00,16:30-01:30"};

    //古田县
    private String[] nanjname = {"jiangnanzao","minguohonggongguan","tongqinglou","nanjingdapaidang"};
    private  String[]  njicons = {"https://tr-osdcp.qunarzz.com/tr-osd-tr-manager/img/b47e11450de0ff7184128b451084c042.jpg_r_640x427x70_bf9ff2c6.jpg",
            "https://tr-osdcp.qunarzz.com/tr-osd-tr-space/img/7001d5206f6a61ead6d24e5e6e558317.jpg",
            "https://th.bing.com/th/id/R.7b5264a7c94b0ff0b7d5e4ec56c60a5c?rik=Cd0t4pypQjjENw&riu=http%3a%2f%2fwww.tongqinglou.cn%2fupload%2fadmin%2f20210712%2f7c468154104cf92a04e12db77766edf7.png&ehk=sNI%2bgb2kgwJt0TAP%2b1EIQffK%2fBuqLhowWFunGvgfkP0%3d&risl=&pid=ImgRaw&r=0",
            "https://img1.qunarzz.com/travel/d5/1607/36/cfaa666c13476bb6.jpg_r_1024x683x95_9b88ae35.jpg"};
    private String[] njbuy = {"¥80/person","¥200/person","¥120/person","¥60/person"};
    private String [] njintroduce = {"188 Jiayuan Road, Xiangcheng District","55th Floor, Building A, New Century Plaza, No. 288-2-3, Zhongshan East Road, Qinhuai District","5F, 435 Zhujiang Road, Xuanwu District","7th Floor, Deji Plaza, 18 Zhongshan Road, Xuanwu District"};
    private String[] njpingfen = {"4.4 points","4.0 points","4.2 points","4.5 points"};
    private String[] njtime = {"11:00-22:00","11:00-14:00,16:00-22:00","11:00-14:00,17:00-22:30","10:00-22:00"};

    //Wuxi
    private String[] wuxname = {"Apo's Xiaolong Bao","Three Gorges Fish Restaurant","Huang Ji Stew Soup","Crab King"};
    private  String[]  wuxicons = {"https://img.zcool.cn/community/01a2535b9326d2a8012017ee8a636c.jpg@1280w_1l_2o_100sh.jpg",
            "https://ww4.sinaimg.cn/large/c5b9f85ajw1e3v08lvu90j20f00780uv.jpg",
            "https://dimg04.c-ctrip.com/images/100u0x000000l1upm71A1.jpg",
            "https://ts1.cn.mm.bing.net/th/id/R-C.9e5efa95de53d25329b5263dbba13252?rik=UAzfLQd4Tcg1Zg&riu=http%3a%2f%2fimg.canyin.com%2f2019%2f10%2f16e0d4da14b.png&ehk=f%2bYOcAXiB%2bP5%2bbrCn0XQBu0%2fCtpEA36GrGPnA4iDq4Y%3d&risl=&pid=ImgRaw&r=0",};
    private String[] wuxbuy = {"¥43/person","¥56/person","¥33/person","¥90/person"};
    private String [] wuxintroduce = {"18 Qingshi Road, Liangxi District, Wuxi","39 Nanchan Temple Lane, Wuxi","57 Nanchan Temple Lane, Wuxi","59 Renmin Middle Road, Liangxi District, Wuxi"};
    private String[] wuxpingfen = {"4.3points","3.6points","4.6points","3.9points"};
    private String[] wuxtime = {"10:30-13:30，16:30-20:30","09:00-24:00","08:00-24:00","11:00-24:00"};

    //周宁县
    private String[] yangzname = {"quyuanchashe","yechunchashe","yangzhouyan","yingbinguan"};
    private  String[]  yzicons = {"https://pic2.zhimg.com/v2-8499d5851a0b0109daa2dca95f8f86b9_r.jpg",
            "https://youimg1.c-ctrip.com/target/100m0f0000007exrv92CD.jpg",
            "https://youimg1.c-ctrip.com/target/100u0z000000nqkysD9CB.jpg",
    "https://uimg.huixiaoer.net/86184844/c31a59b8025369e996e28e3508771f19.jpg"};
    private String[] yzbuy = {"¥80/person","¥70/person","¥120/person","¥100/person"};
    private String [] yzintroduce = {"1 Changchun Road, Hanjiang District","Hanjiang District Big Hongqiao Road 18 Hongqiao Fang Building 1","Hanjiang District Changchun Road 48 Song Jiacheng Sports and Leisure Park","Hanjiang District, Slender West Lake Road No. 48"};
    private String[] yzpingfen = {"4.7 points","4.7 points","3.6 points","3.7 points"};
    private String[] yztime = {"10:00-13:30，17:00-23:30","10:30-00:30","10:30-13:30,16:30-20:30","10:00-23:00"};

    //寿宁县
    private String[] shouname = {"姑奶奶（寿宁店）","佳客来（胜利街店）","芝根芝底（寿宁店）","廊桥鱼庄（寿宁店）","沙拉斯（解放店）","重庆鸡公煲"};
    private  String[]  shouicons = {"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.qqjm.com%2F201908%2F01%2F16777d09eb9.png&refer=http%3A%2F%2Fimg.qqjm.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903571&t=9ae42e6356bd0f87bc5d2eefd89c41e9",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic2.to8to.com%2Fcase%2F2014%2F07%2F28%2F20140728144121-4cd559c1.jpg&refer=http%3A%2F%2Fpic2.to8to.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669904264&t=52830c16710560a187890e0f1298d675",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F12247280.s21i.faiusr.com%2F2%2FABUIABACGAAgjdeR_wUoxrqYzgEw4BI4mAs%21700x700.jpg&refer=http%3A%2F%2F12247280.s21i.faiusr.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903673&t=b89a81d3bb76f21afd90e76027f2f5ff",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E5%AF%BF%E5%AE%81%E5%8E%BF/langqiaoyuzhuang.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E5%AF%BF%E5%AE%81%E5%8E%BF/shalasi.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E5%AF%BF%E5%AE%81%E5%8E%BF/jigongbao.jpg"};
    private String[] shoubuy = {"¥43/人","¥56/人","¥26/人","¥33/人","¥34/人","¥20/人"};
    private String [] shouintroduce = {"本帮江浙菜 寿宁县中心城区","自助餐 寿宁县中线城区","披萨 寿宁县中线城区","鱼火锅 寿宁县中心城区","快餐简餐 寿宁县中心城区","川菜馆 寿宁县中心城区"};
    private String[] shoupingfen = {"3.3分","3.5分","3.6分","3.6分","3.6分","3.5分"};
    private String[] shoutime = {"10:30-13:30，16:30-20:30","08:30-24:00","09:00-24:00","10:00-24:00","08:00-24:00","11:00-24:00"};

    //福安市
    private String[] lianname = {"Chengen Chinese Restaurant","Seafood home cooking","Wanghai Tide Restaurant","Yongde Restaurant","Dubble Happiness Restaurant","Koryewon Barbecue","Xin Xiang Hui"};
    private  String[]  lianicons = {
            "https://dimg04.c-ctrip.com/images/0102512000a2zl2ch0E82_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/0102d120009l3s0lh065D_R_1600_10000.jpg",
            "https://dimg04.c-ctrip.com/images/0101712000a997fnp727E_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/0105e12000a6ybw7446D7_C_1600_1200.png",
            "https://dimg04.c-ctrip.com/images/100k180000013r9ujA678_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/100j0k000000cjh78CC89_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/1004070000002ny775BA2_C_1600_1200.jpg"};
    private String[] lianbuy = {"¥89/person","¥100/person","¥75/person","¥129/person","¥45/person","¥88/person","¥115/peron"};
    private String [] lianintroduce = {"2nd Floor, 98 Huaguoshan Avenue","155 zhongshan West Road, West Unit","No. 39 Haitang Middle Road, Lianyun District","Qixia Road pedestrian street seafood food City, No. 20 seafood street","21 Haichang North Road","Hengli Building, intersection of Haichang Road and Qingnian Road, Haizhou District","F5, Lianyungang Suning Plaza, No.58 Tongguan North Road, Haizhou District"};
    private String[] lianpingfen = {"3.9 points","3.4 points","3.7 points","3.8 points","3.4 points","4.1 points","3.4 points"};
    private String[] liantime = {"11:00 am-22:00 pm","16:00 pm-02:00 am","11:00 am-13:30 pm,16:30 pm-21:00 pm","08:00 am-13:30 pm,16:00 pm-20:00 pm","17:00 pm-02:00 am","11:00 am-23:30 pm","16:00 pm-23:30 pm"};

    //柘荣县
    private String[] zhename = {"晓家碧芋","聚福楼私房菜","小富春饭店","东源农家菜","乐乐锅（柘荣店）","恒昌荣饭店","九香火锅（柳城南路店）"};
    private  String[]  zheicons = {"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.shejiben.com%2Fcase%2F2016%2F02%2F15%2F20160215120845-c8cea06b.jpg&refer=http%3A%2F%2Fpic1.shejiben.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903422&t=22e328b1af7b8bd7726417446ccaaa6f",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/jufulou.jpg",
            "https://img0.baidu.com/it/u=701376769,2403496470&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=334",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50044%2F7417.jpg_wh1200.jpg&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903422&t=ecc921cb50cdd571aad5b98a574f3815",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/leleguo.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/hengchangrong.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E6%9F%98%E8%8D%A3%E5%8E%BF/jiuxianghuoguo.jpg"};
    private String[] zhebuy = {"¥52/人","¥37/人","¥45/人","¥33/人","¥70/人","¥70/人","¥/40人"};
    private String [] zheintroduce = {"福建 柘荣县中心城区","其他中餐 柘荣县中心城区","其他中餐 柘荣县中心城区","农家菜 柘荣县中心城区","火锅 柘荣县中心城区","家常菜 柘荣县中心城区","火锅 柘荣县中心城区"};
    private String[] zhepingfen = {"3.7分","3.7分","3.7分","3.5分","3.5分","3.5分","3.5分"};
    private String[] zhetime = {"暂无营业时间","10:00-13:00,16:00-23:00","13:30-24:00","暂无营业时间","16:30-24:00","08:30-14:00,16:00-22:00","10:00-次日01:30"};

    //Nantong
    private String[] nantongname = {
            "Grilled Whole Fish outside the River Edge",
            "Haidilao Hotpot",
            "Buffet Barbecue",
            "Star Coast Seafood Restaurant",
            "Yao Ji private room dishes",
            "Orchid Restaurant",
           };
    private  String[]  nantongicons = {
            "https://dimg04.c-ctrip.com/images/100l0k000000ckq6a7836_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/10020k000000cprlq0D59_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/0101s1200098pyyx7E4FE_C_1600_1200.png",
            "https://dimg04.c-ctrip.com/images/01011120008fzml91694B_R_1600_10000.jpg",
            "https://dimg04.c-ctrip.com/images/100s16000000zte1iB937_C_1600_1200.jpg",
            "https://dimg04.c-ctrip.com/images/0104t1200091xedcsC5C5_R_10000_1200.jpg"};
    private String[] nantongbuy = {"¥180/person","¥140/person","¥65/person","¥162/person","¥142/person","¥147/person"};
    private String [] nantongintroduce = {
            "F5, Nantong CBD, No.12 Taoyuan Road, Chongchuan District",
            "F6, Golden Eagle Shopping Center, No.57 Gongnong Road, Chongchuan District",
            "Room 4009, 4th Floor, West Block, Wenfeng City Plaza, No.1 Hongqiao Road",
            "No. 2, Evergrande Venice Bar Street, Diding Highway",
            "Yinyang Town Evergrande sea Venice Evergrande Food Street y38",
            "No.5 Huancheng South Road, Chongchuan District",
            };
    private String[] nantongpingfen = {"3.7 points","3.4 points","3.5 points","3.4 points","4.0 points","3.6 points"};
    private String[] nantongtime = {
            "11:00 am-22:00 pm",
            "11:00 am-14:00pm,16:00 pm-22:00 am",
            "11:00 am-14:00 pm,17:00 pm-22:30 pm",
            "10:00 am-22:00 pm",
            "11:00 am-14:00 pm,17:00 pm-21:30 pm",
            "10:00 am-01:30 am"};

    //霞浦县
    private String[] xianame = {"晨曦大酒店","环海大酒楼","鑫鑫饭店","台湾欣乐园","赵妈私房菜排挡","浦天酒店-中餐厅","人民公社（霞浦店）"};
    private  String[]  xiaicons = {"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fphoto.16pic.com%2F00%2F07%2F21%2F16pic_721042_b.jpg&refer=http%3A%2F%2Fphoto.16pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903992&t=7af8384f36ea861da73044ca5e9a06f1",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2Fb21bb051f81986185464465044ed2e738ad4e6c7&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669904265&t=25f2705c89378d8755f363f779e7b8f5",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.to8to.com%2Fcase%2F2016%2F12%2F25%2F20161225020123-850d8c3d.jpg&refer=http%3A%2F%2Fpic.to8to.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903836&t=9bbd5797496f1ee60d8f464cd9355e74",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fx3.tuozhe8.com%2Fattachment%2Fforum%2F201512%2F23%2F005742qxrfbvw6t8mbw6mf.jpg&refer=http%3A%2F%2Fx3.tuozhe8.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1669903836&t=d7ba53ef529895b2b06dd717c4f0e0ec",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E9%9C%9E%E6%B5%A6%E5%8E%BF/zhaomapaidang.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E9%9C%9E%E6%B5%A6%E5%8E%BF/putianjiudian.jpg",
            "https://recycleimage.oss-cn-hangzhou.aliyuncs.com/%E7%BE%8E%E9%A3%9F/%E9%9C%9E%E6%B5%A6%E5%8E%BF/renminggongshe.jpg"};
    private String[] xiabuy = {"¥230/人","¥115/人","¥58/人","¥21/人","¥115/人","¥50/人","¥70/人"};
    private String [] xiaintroduce = {"福建菜 松城镇","福建菜 松城镇","其他中餐 霞浦县其他","其他中餐 松城镇","其他中餐 三沙镇","海鲜 松城镇","湘菜 松城镇"};
    private String[] xiapingfen = {"3.5分","3.8分","3.5分","4.0分","3.4分","3.7分","3.8分"};
    private String[] xiatime = {"11:00-22:00","11:00-14:00,16:00-22:00","暂无营业时间","11:00-14:00,17:00-22:30","10:00-22:00","11:00-14:00,17:00-21:30","10:00-次日01:30"};

    private String[] name1 = {};
    private String[] icons = {};
    private  String[] buy ={};
    private  String[] introduces = {};
    private String[] pingfen = {};
    private String[] time = {};
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
            buy   = szbuy;
            introduces = szintroduce;
            pingfen = szpingfen;
            time = sztime;
        }
        if(name_receive1.equals("Nanjing")){
            name1 = nanjname;
            icons = njicons;
            buy   = njbuy;
            introduces = njintroduce;
            pingfen = njpingfen;
            time = njtime;
        }
        if(name_receive1.equals("Wuxi City")){
            name1 = wuxname;
            icons = wuxicons;
            buy =wuxbuy;
            introduces = wuxintroduce;
            pingfen = wuxpingfen;
            time = wuxtime;
        }
        if(name_receive1.equals("Yangzhou")){
            name1 = yangzname;
            icons = yzicons;
            buy   = yzbuy;
            introduces = yzintroduce;
            pingfen = yzpingfen;
            time = yztime;
        }
        if(name_receive1.equals("寿宁县")){
            name1 = shouname;
            icons = shouicons;
            buy =shoubuy;
            introduces = shouintroduce;
            pingfen = shoupingfen;
            time = shoutime;
        }
        if(name_receive1.equals("Lianyungang")){
            name1 = lianname;
            icons = lianicons;
            buy =lianbuy;
            introduces = lianintroduce;
            pingfen = lianpingfen;
            time = liantime;
        }
        if(name_receive1.equals("柘荣县")){
            name1 = zhename;
            icons = zheicons;
            buy =zhebuy;
            introduces = zheintroduce;
            pingfen = zhepingfen;
            time = zhetime;
        }
        if(name_receive1.equals("Nantong")){
            name1 = nantongname;
            icons = nantongicons;
            buy = nantongbuy;
            introduces = nantongintroduce;
            pingfen = nantongpingfen;
            time = nantongtime;
        }
        if(name_receive1.equals("霞浦县")){
            name1 = xianame;
            icons = xiaicons;
            buy =xiabuy;
            introduces = xiaintroduce;
            pingfen = xiapingfen;
            time = xiatime;
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
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.twofragment,parent,false));
            return holder   ;
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            Glide.with(getActivity()).load(icons[position]).into(holder.iv);
            holder.nameone.setText(name1[position]);
            holder.buy.setText(buy[position]);
            holder.troduce.setText(introduces[position]);
            holder.pingfen.setText(pingfen[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),MeishiActivity.class);
                    intent.putExtra("detail_iv",icons[position]);
                    intent.putExtra("detail_name1",name1[position]);
                    intent.putExtra("detail_buy",buy[position]);
                    intent.putExtra("name",name_receive1);
                    intent.putExtra("detail_introduce",introduces[position]);
                    intent.putExtra("detail_pingfen",pingfen[position]);

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
            TextView pingfen;
            public MyViewHolder(View view){
                super(view);
                nameone = (TextView)view.findViewById(R.id.name);
                iv = (ImageView)view.findViewById(R.id.iv);
                buy=(TextView)view.findViewById(R.id.buy);
                troduce =(TextView) view.findViewById(R.id.ziliao);
                pingfen = view.findViewById(R.id.pingfen);
            }
        }
    }

}
