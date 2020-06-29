package com.example.zhangwu25;

import android.app.Application;
import android.content.Context;

public class AppApplication extends Application {
    private static Context instance;

    protected String jsonData="{\n" +
            "    \"result\": \"0\",\n" +
            "    \"list\": [\n" +
            "        {\n" +
            "            \"title\": \"多哥\",\n" +
            "            \"filePath\": \"https://movie.douban.com/subject/30271841\",\n" +
            "            \"thumbPath\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2575882765.webp\",\n" +
            "            \"id\": \"2\",\n" +
            "            \"profile\": \"聚焦1925年雪橇手和雪橇犬齐心协力为拯救阿拉斯加州诺姆市孩子的性命而奔波于血清接力路上的事件\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"来自深渊：流浪的黄昏\",\n" +
            "            \"filePath\": \"https://movie.douban.com/subject/30175633\",\n" +
            "            \"thumbPath\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2545641131.webp\",\n" +
            "            \"id\": \"2\",\n" +
            "            \"profile\": \"人气动漫《来自深渊》确认将推出两部剧场版动画——《来自深渊：出发的黎明》《来自深渊：流浪的黄昏》\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"真心半解 \",\n" +
            "            \"filePath\": \"https://movie.douban.com/subject/33420285/\",\n" +
            "            \"thumbPath\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2594916975.webp\",\n" +
            "            \"id\": \"2\",\n" +
            "            \"profile\": \"个性体贴的运动健将保罗爱上了学校校花，但他实在不善言辞，于是决定僱用内向的高材生艾莉帮他展开追求，毫不相干的两人也因此成为朋友\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"给我翅膀 \",\n" +
            "            \"filePath\": \"https://movie.douban.com/subject/30410114\",\n" +
            "            \"thumbPath\": \"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2561160802.webp\",\n" +
            "            \"id\": \"2\",\n" +
            "            \"profile\": \"科斯迪安（让-保罗•卢弗Jean-Paul Rouve 饰）是一名鸟类研究学者，对于鸟类学的热爱让他将毕生的精力都投入到了这个领域之中\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CacheUtil.setPermanent("json",jsonData);
        ImageLoader.init(this);
    }

    public static Context getInstance(){
        return instance;
    }

}