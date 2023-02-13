package org.example.lesson7;

import java.util.ArrayList;
import java.util.List;

public class WildcardExam {

    public static void main(String[] args) {
        // A -> B -> C -> D -> E
        List<A> aList = new ArrayList<>();
        List<B> bList = new ArrayList<>();
        List<C> cList = new ArrayList<>();
        List<D> dList = new ArrayList<>();
        List<E> eList = new ArrayList<>();

//        List<C> list33 = foo(cList);
//        List<D> list44 = foo(dList);
//        List<E> list55 = foo(eList);
//
//        List<C> list1 = foo(cList); // 1
//        List<D> list2 = foo(cList); // 2
//        List<E> list3 = foo(cList); // 3
//
//        List<C> list4 = foo(dList); // 4
//        List<D> list5 = foo(dList); // 5
//        List<E> list6 = foo(dList); // 6
//
//        List<C> list7 = foo(eList); // 7
//        List<D> list8 = foo(eList); // 8
//        List<E> list9 = foo(eList); // 9


    }

//    // T = [C, D, E]
//    // T = C => S = [C, D, E]
//    // T = D => S = [D, E]
//    // T = E => S = [E]
//    public static <T extends C, S extends T> List<S> foo(List<T> source) {
//
//    }


    public static class A {

    }

    public static class B extends A {

    }

    public static class C extends B {

    }

    public static class D extends C {

    }

    public static class E extends D {

    }

}
