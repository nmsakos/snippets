package com.akos;

public class JavaWMVersionTest {

    public static void main(String[] args) {
        String strJavaSpec = System.getProperty("java.specification.version");
        System.out.println(strJavaSpec);
        String strVmVersion = System.getProperty("java.vm.version");
        System.out.println(strVmVersion);
        System.out.println(Character.toString(46));
        int vmVersion = Integer.parseInt(strVmVersion.substring(0, strVmVersion.indexOf(46)));
        System.out.println(vmVersion);
    }
}
