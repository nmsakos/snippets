package com.akos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringChanges {
    public static void main(String[] args) {
        final String s = "/properties/410470/amenities";
        final String regex = "(/properties/)(.*)(/amenities)";
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(s);

        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
        System.out.println(s.split("/")[2]);
        System.out.println(" ".isBlank());
        System.out.println(" ".isEmpty());
        System.out.println("*"+"  iasjdélfsja léasdjflasj ".strip()+"*");
        System.out.println("*"+"  iasjdélfsja léasdjflasj ".trim()+"*");
    }
}
