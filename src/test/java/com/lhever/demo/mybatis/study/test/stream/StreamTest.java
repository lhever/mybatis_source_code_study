package com.lhever.demo.mybatis.study.test.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/4/18 14:52
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/4/18 14:52
 * @modify by reason:{方法名}:{原因}
 */
public class StreamTest {

    public static class User{
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Test
    public void testFlatMap() {
        List<User> userList0 = IntStream.range(1, 4).mapToObj(i -> new User("user-" + i)).collect(Collectors.toList());
        List<User> userList1 = IntStream.range(4, 8).mapToObj(i -> new User("user-" + i)).collect(Collectors.toList());
        List<User> userList2 = IntStream.range(8, 12).mapToObj(i -> new User("user-" + i)).collect(Collectors.toList());

        List<List<User>> lists = new ArrayList(){{
            add(userList0);
            add(userList1);
            add(userList2);

        }};
        System.out.println(lists);

        List<User> collect = lists.stream().
                filter(subList -> subList != null).
                flatMap(subList -> subList.stream().
                        map(Function.identity())).collect(Collectors.toList());
        System.out.println(collect);


    }



}





















