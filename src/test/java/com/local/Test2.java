package com.local;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by pasha on 13.01.18.
 */
public class Test2 {

    public int getMinAbsSum(int[] a) {
        int resMin = Integer.MAX_VALUE;
        if (a.length == 1) {
            resMin = a[0]+ a[0];
        }

        // go through array
        for (int i = 0; i<=a.length-1; ++i) {
            // inner cycle for 2 element
            for (int j = i;j<=a.length-1; ++j) {
                cnt++;
                int s = Math.abs(a[i] + a[j]);
                if (s < resMin){
                    resMin = s;
                }
            }
        }

        return resMin;
    }

    @Test
    public void test1() {
        int[] a = new int[100];
        for (int i=0; i<=a.length-1;++i) {
            a[i] = i+1;
        }
        int r = getMinAbsSum(a);
        System.out.println(a);
        System.out.println(r);
        System.out.println(cnt);
    }

    static int cnt = 0;

    public int getMinAbsSumInner(int v, int[] a) {

        cnt++;
        if (a.length == 1) {
            return v + a[0];
        }

        int[] a1 = Arrays.copyOfRange(a, 0, a.length/2);
        int[] a2 = Arrays.copyOfRange(a, a.length/2, a.length);

        int v1 = Math.abs(getMinAbsSumInner(v, a1));
        int v2 = Math.abs(getMinAbsSumInner(v, a2));
        if (v1 < v2) {
            return v1;
        } else {
            return v2;
        }
    }

    public int getMinAbsSum2(int[] a) {
        int resMin = Integer.MAX_VALUE;
        for (int i =0; i<=a.length-1;++i) {
            int[] a1 = Arrays.copyOfRange(a, i, a.length);
            int r = getMinAbsSumInner(a[i], a1);
            if (r < resMin) {
                resMin = r;
            }
        }
        return resMin;
    }

    @Test
    public void test2() {
        int[] a = new int[3];
        for (int i=0; i<=a.length-1;++i) {
            a[i] = i+1;
        }
        //System.out.println(Arrays.toString(a));
        int r = getMinAbsSum2(a);
        System.out.println(r);
        System.out.println(cnt);

    }



    public int getSum3(int[] a) {
        Arrays.sort(a);
        int i = 0;
        int j =a.length-1;
        int res = Integer.MAX_VALUE;
        // find maximum of negative sums (or 0)
        while (i <= j) {
            int s1 = a[i] + a[i];
            int s2 = a[i] + a[j];
            int s = Math.max(s1, s2);

            if (res > Math.abs(s)) {
                res = Math.abs(s);
            }
            if (s<=0) {
                i++;
            } else {
                j--;
            }
        }

        i = 0;
        j =a.length-1;
        while (i <= j) {
            int s1 = a[j] + a[j];
            int s2 = a[i] + a[j];
            int s = Math.min(s1, s2);
            if (res > Math.abs(s)) {
                res = Math.abs(s);
            }
            if (s>=0) {
                j--;
            } else {
                i++;
            }
        }

        return res;
    }

    @Test
    public void test3() {
        int[] a = new int[] {-11,-5,1,2,3,5,7,10};
        System.out.println(Arrays.toString(a));
        int r = getSum3(a);
        System.out.println(r);
        //System.out.println(cnt);

    }


}
