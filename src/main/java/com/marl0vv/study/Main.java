package com.marl0vv.study;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage("./src/main/resources/inputData.json");
        Service service = new Service(storage);
        service.run();
    }
}
