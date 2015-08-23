package org.mababio.spring.vltool;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ea on 8/23/15.
 */
public class Tester {

    public static void main(String[] args) {
        Calendar cal  = Calendar.getInstance();

        Date date = cal.getTime();

        System.out.print(date);
    }
}
