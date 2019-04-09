package com.akos.catchclasspath;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

public class CatchClasspath {

    public static void main(String[] args) {
        String dirName = "C:\\Users\\akos_nemes\\AppData\\Local\\Temp";
        while (true){
            File dir = new File(dirName);
            FileFilter filter = new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    final boolean isClasspathFile = pathname.getName().startsWith("idea_classpath");
                    if (isClasspathFile) {
                        System.out.println(pathname.getName());
                    }
                    return isClasspathFile;
                }
            };
            final File[] files = dir.listFiles(filter);
            Arrays.stream(files).forEach(file -> {
                try {
                    final String destName = file.getAbsolutePath().replace(file.getName(), "") + "bkp_" + file.getName();
                    System.out.println(String.format("Copy %s to %s", file.getName(), destName));
                    FileUtils.copyFile(file, new File(destName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
