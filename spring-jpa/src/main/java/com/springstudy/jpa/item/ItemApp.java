package com.springstudy.jpa.item;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemApp {

    public static void main(String[] args) throws
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException,
        NoSuchFieldException,
        ClassNotFoundException {

        Class<Item> itemClass = (Class<Item>)Class.forName("com.springstudy.jpa.item.Item");

        Constructor<Item> defaultConstructor = itemClass.getDeclaredConstructor(null);
        Item item1 = defaultConstructor.newInstance();
        log.info("item1 : {}", item1);

        Constructor<Item> constructor = itemClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Item item2 = constructor.newInstance("cup");
        log.info("item2 : {}", item2);

        Field id = Item.class.getDeclaredField("id");
        log.info("id : {}", id.get(null));

        id.set(null, "newId");
        log.info("id : {}", id.get(null));

        Field name = Item.class.getDeclaredField("name");
        name.setAccessible(true);
        log.info("name : {}", name.get(item2));

        name.set(item2, "phone");
        log.info("name : {}", name.get(item2));

        Method sum = itemClass.getDeclaredMethod("sum", int.class, int.class);
        sum.setAccessible(true);
        Object result = sum.invoke(item1, 1, 2);
        log.info("result : {}", result);
    }
}