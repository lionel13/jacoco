package com.example.demo.jacoco.util;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class RandomObjectFiller {
    private Random random = new Random();

    public <T> T createAndFill(Class<T> clazz) throws Exception {
        T instance = clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = getRandomValueForField(field);
            field.set(instance, value);
        }
        return instance;
    }

    private Object getRandomValueForField(Field field) throws Exception {
        Class<?> type = field.getType();

        // Note that we must handle the different types here! This is just an
        // example, so this list is not complete! Adapt this to your needs!
        if (type.isEnum()) {
            Object[] enumValues = type.getEnumConstants();
            return enumValues[random.nextInt(enumValues.length)];
        } else if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            return random.nextInt();
        } else if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            return random.nextLong();
        } else if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            return random.nextDouble();
        } else if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            return random.nextFloat();
        } else if (type.equals(String.class)) {
            return UUID.randomUUID().toString();
        } else if (type.equals(BigInteger.class)) {
            return BigInteger.valueOf(random.nextInt());
        } else if (type.equals(LocalDate.class)) {
            return randomLocalDate();
        }
        return createAndFill(type);
    }

    private Object randomLocalDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);


//        Instant instant = Instant.ofEpochSecond(getRandomTimeBetweenTwoDates());
//        return LocalDateTime.ofInstant(instant, ZoneId.of("UTC-06:00"));
    }

//    private long getRandomTimeBetweenTwoDates() {
//
//        Long beginTime = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
//        long endTime = Timestamp.valueOf("2013-12-31 00:58:00").getTime();
//
//        long diff = endTime - beginTime + 1;
//        return beginTime + (long) (Math.random() * diff);
//    }
}
