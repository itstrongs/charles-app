package com.charles.myapp.learn;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by itstrongs on 2018/1/15.
 */

public class TClass {

    public static void main(String[] args) {
        Foo<String> foo = new Foo<String>() {

        };
        // 在类的外部这样获取
        Type type = ((ParameterizedType) foo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(type);
        // 在类的内部这样获取
        System.out.println(foo.getTClass());
    }

    abstract static class Foo<T> {
        public Class<T> getTClass() {
            Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return tClass;
        }
    }
}
