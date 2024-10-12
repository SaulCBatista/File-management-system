package com.aracomp.fileSystem;

public class Main {
    public static void main(String[] args) {


        Disk disk = new Disk(15);

        disk.add("Hello, World!");
        System.out.println(disk.toString());

        System.out.println(disk.read(0));

        disk.delete(0);
        System.out.println(disk.toString());


        /* 
        String content = fileTable.read("file1.txt");
        System.out.println("Conteúdo: " + content);

        
        System.out.println("Deletando arquivo 'file1.txt'...");
        fileTable.delete("file1.txt");


        System.out.println("Tentando ler arquivo 'file1.txt' novamente...");
        content = fileTable.read("file1.txt");
        System.out.println("Conteúdo: " + content);
        */
        
    }
}
