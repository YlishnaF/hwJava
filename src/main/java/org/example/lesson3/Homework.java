package org.example.lesson3;

import java.util.*;

public class Homework {

    public static void main(String[] args) {
        // 1. Создать список объектов типа Notebook
        List<Notebook> notebooks = new ArrayList<>(List.of(new Notebook(4,30245), new Notebook(12,56987),
                new Notebook(8, 32658), new Notebook(6,43500), new Notebook(4,20500)));
        // Отсортировать его тремя способами:
        // 1.1 по ОЗУ (ram)
        System.out.println("----Сортировка по ram----");
        Comparator<Notebook> notebookComparator = Comparator.comparingInt(note -> note.ram);
        Collections.sort(notebooks, notebookComparator);
        for (Notebook n: notebooks) {
            System.out.println("ram: "+ n.ram+" Price: "+n.price);
        }
        // 1.2 по цене (price)
        System.out.println("----Сортировка по price----");
        Comparator<Notebook> notebookComparatorPrice = Comparator.comparingDouble(note -> note.price);
        Collections.sort(notebooks, notebookComparatorPrice);
        for (Notebook n: notebooks) {
            System.out.println("Price: "+n.price+ " ram: "+ n.ram);
        }
        // 1.3 сначала по ram, потом по price (если ram не равны, сортируем по ним; если равны, то по цене)
        System.out.println("----Сортировка по ram и price----");
        Comparator<Notebook> comparatorNote = new Comparator<>() {
            @Override
            public int compare(Notebook o1, Notebook o2) {
                if (o1.ram > o2.ram) {
                    return 1;
                } else if (o1.ram == o2.ram) {
                    return (int) (o1.price - o2.price);
                } else {
                    return -1;
                }
            }
        };
        Collections.sort(notebooks, comparatorNote);
        for (Notebook n: notebooks) {
            System.out.println("Price: "+n.price+ " ram: "+ n.ram);
        }

        // 2. Реализовать итератор для массива
        int[] source = {1, 2, 3, 4, 5, 6, 7};
        Iterator<Integer> intArrayIterator = new IntArrayIterator(source);
        while (intArrayIterator.hasNext()) {
            System.out.println(intArrayIterator.next());
        }
    }

    private static class Notebook {

        private final int ram;
        private final double price;

        public Notebook(int ram, double price) {
            this.ram = ram;
            this.price = price;
        }

        public int getRam() {
            return ram;
        }

        public double getPrice() {
            return price;
        }
    }

    private static class IntArrayIterator implements Iterator<Integer> {

        private final int[] source;
        private int cursor = 0;

        public IntArrayIterator(int[] source) {
            this.source = source;
        }

        @Override
        public boolean hasNext() {
            return cursor<=source.length-1;
        }

        @Override
        public Integer next() {
            cursor++;
            return source[cursor-1];
        }
    }

}
