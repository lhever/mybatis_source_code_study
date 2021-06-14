package com.lhever.demo.mybatis.study.test.SQL;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/12 15:00
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/12 15:00
 * @modify by reason:{方法名}:{原因}
 */
public class SelectSQLTest {



    @Test
    public void testGetSql() {
        String sql =  new SQL() {
            {
                SELECT_DISTINCT("*");
                FROM("table");

                WHERE("region_id = '111'");

                WHERE("tenant_id = '111'");

                WHERE(" (rule_name like CONCAT('%', '固定金额', '%') or house_type = 3 )");

            }
        }.toString();

        System.out.println(sql);
    }




    @Test
    public void testSQL2() {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(" table ");

                List<String> and = new ArrayList<>();
                and.add("region_id = '111'");

                and.add("tenant_id = '111'");


                List<String> or = new ArrayList<>();
                or.add( "house_name LIKE CONCAT('%', '迎春', '%') ");
                or.add( "resident_names LIKE CONCAT('%', '李', '%') ");


                if (and.size() > 0) {
                    String andCondition = StringUtils.join(and, " and ");
                    WHERE(andCondition);
                }

                if (or.size() > 0) {
                    String orCondition = "(" + StringUtils.join(or, " or ") + ")";
                    WHERE(orCondition);
                }

                if (true) {
                    ORDER_BY("create_time desc");
                }
            }
        }.toString();

        System.out.println(sql);
    }


}
