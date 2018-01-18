package com.local;

import org.junit.Test;

/**
 * Created by pasha on 13.01.18.
 */
public class Test1 {

    public int getGap(int n) {
        if (n <= 0) return 0;
        int maxGap = 0, gap = 0;
        int r1 = n;
        boolean start = false;
        while (r1 > 0) {
            int r2 = r1 >> 1;
            if (r1 == (r2<<1)) {
                if (start) {
                    gap++;
                    if (gap > maxGap) {
                        maxGap = gap;
                    }
                }
            } else {
                start = true;
                gap = 0;
            }
            r1 = r2;
        }
        return maxGap;
    }

    @Test
    public void gap1() {
        // 1...max_int
        // -> binary representatioon
        // -> count max length of 0

        int n = 201;
        // start
        int res = getGap(n);

        System.out.println(Integer.toBinaryString(n));
        System.out.println(res);

    }
}
