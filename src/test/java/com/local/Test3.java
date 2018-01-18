package com.local;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pasha on 14.01.18.
 */
public class Test3 {

    static int cnt = 0;

    public int getMinSum(int[] a) {
        cnt++;
        if (a.length == 1) {
            return Math.min(a[0], a[0] * (-1));
        }

        int[] a1 = Arrays.copyOfRange(a, 0, a.length-1);
        int rPred= getMinSum(a1);
        return Math.min(rPred + a[a.length-1], rPred + a[a.length-1] * (-1));
    }


    @Test
    public void test3() {
        int[] a = new int[] {1,2,5,-2};
        int s = getMinSum(a);
        System.out.println(Arrays.toString(a));
        System.out.println(s);
        System.out.println(cnt);
    }

    public int getSumTree(int[] a, int level, int levelNodeVal) {
        cnt++;
        if (a.length == level) {
            return levelNodeVal;
        }
        int leftChildNodeVal = levelNodeVal - a[level];
        int rightChildNodeVal = levelNodeVal + a[level];


        int leftTreeValue;
        leftTreeValue = getSumTree(a, level + 1, leftChildNodeVal);

        int rightTreeValue;
        rightTreeValue = getSumTree(a, level + 1, rightChildNodeVal);

        return Math.min( Math.abs( leftTreeValue ),
                         Math.abs( rightTreeValue) );

    }

    @Test
    public void test31() {
        int[] a = new int[] {1,2,5,-2};
        int s = getSumTree(a, 0, 0);
        System.out.println(Arrays.toString(a));
        System.out.println(s);
        System.out.println(cnt);
    }


    // map - для уровня I, все возможные комбинации низлежащих суммм
    public int getSumTree2(int[] a, int level, int levelNodeVal,Map<Integer, Integer[]> map) {
        cnt++;
        if (a.length == level) {
            map.put( level, new Integer[] { a[level-1] } );  // только одно это значение
            return levelNodeVal;
        }

        // предыдущие комбинации нижнего уровня
        Integer[] comb = map.get(level + 1);
        if (comb != null) {
            // если есть уже
            Integer[] newComb = Arrays.copyOf(comb, comb.length + 2);
            newComb[newComb.length-2] = a[level];
            newComb[newComb.length-1] = 0-a[level];
            // запомнить комбинации для этого уровня
            map.put(level, newComb);
            // без рекурсии пройтись по списку комбинаций и выбрать с мин суммой
            for (Integer v : newComb) {

            }
        } else {
            // если ничего еще нет - ижем рекурсией ниже
            int leftChildNodeVal = levelNodeVal - a[level];
            int rightChildNodeVal = levelNodeVal + a[level];

            int leftTreeValue;
            leftTreeValue = getSumTree(a, level + 1, leftChildNodeVal);

            int rightTreeValue;
            rightTreeValue = getSumTree(a, level + 1, rightChildNodeVal);

            return Math.min( Math.abs( leftTreeValue ),  Math.abs( rightTreeValue) );
        }

        return 0;
    }


    public int min_abs_sum ( int[] a) {
        int[] arr = new int[101];

        for (int i = 0; i < a.length; ++i) {
            arr[Math.abs(a[i])] = arr[Math.abs(a[i])] + 1;
        }

        int sump = 0;
        int sumn = 0;
        int i = 0, j = 100;
        boolean proceed = true;
        while (proceed) {
            // если сумма отрицательная  = 0 или sump > sumn
            while((sumn == 0 && proceed) || (sump > sumn && proceed)) {
                int v = arr[j];
                if (v > 0) {
                    sumn += j;
                    v--;
                    arr[j] = v;
                } else {
                    j--;
                }
                proceed = (i<j) || (i==j && v==0);
            }
            // добавить положительных сумм до тех пор пока не зайдем за sumn
            while ((sump == 0 && proceed) || (sump <= sumn && proceed) ) {
                int v = arr[i];
                if (v > 0) {
                    sump += i;
                    v--;
                    arr[i] = v;
                } else {
                    i++;
                }
                proceed = (i<j) || (i==j && v==0);
            }
            proceed = (i<j) || (i==j && arr[i]==0);
        }

        return Math.abs(sump-sumn);
    }

    @Test
    public void test33() {
        int[] a = new int[] {1, 5, -2, 5, 2, 3};
        int s = min_abs_sum(a);
        System.out.println(Arrays.toString(a));
        System.out.println(s);
        System.out.println(cnt);
    }


}
