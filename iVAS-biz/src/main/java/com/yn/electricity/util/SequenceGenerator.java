package com.yn.electricity.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: IpIdGenerator
 * @Author: zzs
 * @Date: 2021/2/23 15:36
 * @Description: 生成唯一值
 */
public class SequenceGenerator {

    private static final int RANDOM_SIZE = 8;
    private static final int RANDOM_SCOPE = 10;

    public static void main(String[] args) {
        System.out.println(sequence());
    }

    /**
     * 生成序列
     * @return
     */
    public static String sequence(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String time = String.valueOf(System.currentTimeMillis());
        String random = SequenceGenerator.getRandom();
        return year +""+ month +""+ day + time + random;
    }

    /**
     * 生成六位不同随机数
     * @return
     */
    private static String getRandom() {
        List<Integer> ql = new ArrayList<>();
        //随机对象
        Random random = new Random();
        int temp;
        //取5个随机数
        while(ql.size() <= RANDOM_SIZE){
            //生成随机数，范围50以内
            temp = random.nextInt(RANDOM_SCOPE);
            //判断是否重复
            if(!ql.contains(temp)){
                ql.add(temp);
            }
        }

        StringBuilder sf = new StringBuilder();
        ql.forEach(sf::append);
       return sf.toString();
    }


}
