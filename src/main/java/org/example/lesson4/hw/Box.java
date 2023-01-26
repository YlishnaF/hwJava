package org.example.lesson4.hw;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit>{

    // Реализовать хранение фруктов в коробке.
    // Должны быть доступны следующие возможности
    // Добавление фрукта
    // Должен быть конструктор
    // Метод, который пересыпает фрукты из текущей коробки в другую коробку
    // Для внутреннего хранения можно использовать например List

    private List<T> list = new ArrayList<>();

    public <E extends T>void add(E fruit) {
        list.add(fruit);
    }
    
    public double getWeight() {
        double sum=0;
        for (T fruit:list ) {
            sum+=fruit.getWeight();
        }
        return sum;
    }

    public void moveTo(Box <? super T>anotherBox) {
        anotherBox.list.addAll(list);
        list.clear();
    }

}
