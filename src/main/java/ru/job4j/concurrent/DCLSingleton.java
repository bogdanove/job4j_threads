package ru.job4j.concurrent;

public final class DCLSingleton {

    /*
     *
     * Возможна ситуация, при которой другой поток может получить и начать использовать
     * (на основании условия, что указатель не нулевой) не полностью сконструированный объект
     *
     * */

    private static volatile DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

    private DCLSingleton() {
    }

}
