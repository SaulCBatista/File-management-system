package com.aracomp.fileSystem;

public class Main {
    public static void main(String[] args) {

        Disk disk = new Disk(15);

        DiskManager diskManager = new DiskManager(disk);
        //--------------------------------------------//
        
        diskManager.add("File1", "Hello, World!");
        
        System.out.println(diskManager.toString());

        String content = diskManager.read("File1");
        System.out.println(content);

        diskManager.delete("File1");

        System.out.println(diskManager.toString());

    }
}
