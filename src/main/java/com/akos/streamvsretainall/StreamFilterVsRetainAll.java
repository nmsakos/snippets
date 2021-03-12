package com.akos.streamvsretainall;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class StreamFilterVsRetainAll {

    public static void main(String[] args) throws IOException {

        List<String> listInput = FileUtils.readLines(new File("target/classes/streamvsretainall/input.txt"), Charset.defaultCharset());
        HashSet<String> setInput = new HashSet<>(listInput);
        listInput = new ArrayList<>(setInput);

        List<String> listNeeded = FileUtils.readLines(new File("target/classes/streamvsretainall/needed.txt"), Charset.defaultCharset());
        HashSet<String> setNeeded = new HashSet<>(listNeeded);
        listNeeded = new ArrayList<>(setNeeded);

        System.out.println("Input list size: "+listInput.size());
        System.out.println("Needed list size: "+listNeeded.size());

        final List<String> listResult = listInput.stream().filter(listNeeded::contains).collect(Collectors.toList());

        System.out.println("Result list size: "+listResult.size());

        System.out.println("Input set size: "+setInput.size());
        System.out.println("Needed set size: "+setNeeded.size());

        setInput.retainAll(setNeeded);

        System.out.println("Result set size: "+setInput.size());

    }
}
