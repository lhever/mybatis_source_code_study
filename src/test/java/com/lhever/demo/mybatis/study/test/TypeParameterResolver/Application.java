package com.lhever.demo.mybatis.study.test.TypeParameterResolver;

/**
 * <p>
 * 类说明
 * </p>
 *
 * @author lihong10 2020/3/12 9:55
 * @version v1.0
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人} 2020/3/12 9:55
 * @modify by reason:{方法名}:{原因}
 */
public class Application {
//    public static void main(String[] args) {
//        Plate<? extends Fruit> plate1 = new Plate<>(new Apple());
//
//        /**
//         * “? extends Fruit”是上界通配符，表示 plate1 中的item存储的是Fruit及其子类类型（Fruit就是上界），但是无法确定到底是哪一个。
//         *
//         * # 由于知道plate1的item存储的肯定是Fruit或者其子类，所以可以get并转换成Fruit或者其超集（Fruit或者其子类 =get=> Fruit或者其超集）
//         *
//         * # 由于不知道plate1中的item到底存储的是Fruit或者其子类的哪一个，所以没办法set。
//         */
//        // getter
//        Fruit fruit1 = plate1.getItem(); // 正确
//        Food food1 = plate1.getItem(); // 正确
//        Meat meat1 = plate1.getItem(); // 错误，Fruit不能转成Meat
//
//        //setter
//        plate1.setItem(new Apple()); // 错误
//        plate1.setItem(new Fruit()); // 错误，假设应该保存Fruit的子类如Apple，Fruit无法转换成Apple
//        plate1.setItem(new Food()); // 错误
//        plate1.setItem(new Object()); // 错误，假设应该保存的是Fruit，存入Object，Object无法转化成Fruit
//        // 所以set任何类型都有问题，既无法set
//
//        // 构造器
//        Plate<? extends Fruit> plate1_1 = new Plate<>(new Apple()); // 正确，因为构造器是“Plate(T item)”，然后“T extents Fruit”
//        Plate<? extends Fruit> plate1_2 = new Plate<>(new Fruit()); // 正确
//        Plate<? extends Fruit> plate1_3 = new Plate<>(new Food());  // 正确，因为构造器是“Plate(T item)”，然后“T extents Fruit”，T为Food不满足条件
//
//
//        /**
//         * "<? super Fruit>" 是下界通配符，表明plate2中的item存储的是Fruit自身或者其超类（Fruit就是下界），但是不能确定到底是哪一个。
//         *
//         * # 由于知道“item”中存储的肯定是Fruit或这其超类，所以set进去Fruit或者Fruit的子类（Fruit或者其子类 =set=> Fruit或者其超类）。
//         *
//         * # get的时候由于不能确定到底存储的是Fruit或者其超类的哪一个，所以get会出问题，但是所有的类都是Object的子类，所以返回Object没问题
//         */
//        Plate<? super Fruit> plate2 = new Plate<>(new Fruit());
//        // setter
//        plate2.setItem(new Food()); // 错误，假设存储的是Fruit，Food没办法转成Fruit（下界）
//        plate2.setItem(new PinkLady()); // 正确
//        plate2.setItem(new Fruit()); // 正确
//
//        // getter
//        Fruit fruit2 = plate2.getItem(); // 错误
//        Food food2 = plate2.getItem(); // 错误
//        Object object = plate2.getItem(); // 正确
//
//        // 构造器
//        Plate<? super Fruit> plate2_1 = new Plate<>(new Food()); // 正确
//        Plate<? super Fruit> plate2_2 = new Plate<>(new Apple()); // 正确
//        Plate<? super Fruit> plate2_3 = new Plate<>(new Meat()); // 正确
//
//        List<? super Fruit> list = new ArrayList<>();
//    }

}

  class Plate<T> {
    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}

  class Food {
}

  class Meat extends Food {
}

  class Fruit extends Food {
}

  class Apple extends Fruit {
}

 class PinkLady extends Apple {

}

