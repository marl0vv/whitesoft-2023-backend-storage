package com.marl0vv.study;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage(args[0]);
        Service service = new Service(storage);
        service.run();
    }
}
