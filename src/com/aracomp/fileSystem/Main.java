package com.aracomp.fileSystem;

import java.util.List;

import com.aracomp.fileSystem.exception.FileNotFoundException;
import com.aracomp.fileSystem.exception.InvalidOperationException;
import com.aracomp.fileSystem.exception.SizeUnenoughException;

public class Main {
    public static void main(String[] args) {
/*
        Teste teste = new Teste();

        List<String> methodsToRun = List.of(
            "addFile", 
            "addMultipleFiles",
            "addFilesToDiskLimit",
            "addAboveDiskLimit", 
            "addDiskFill",
            //"addDuplicateFile",
            "readFile", 
            "readMultipleFiles",
            "readAllFiles",
            //"readNonExistentFile", 
            "deleteFile",
            "deleteMultipleFiles",
            "deleteAllFiles"
            //"deleteNonExistentFile"
        );

        teste.execute(methodsToRun);
 */


        Disk disk = new Disk(7);

        DiskManager diskManager = new DiskManager(disk);

        disk.setDiskManager(diskManager);

        try {
        	diskManager.add("File1", "12");
        	diskManager.add("File2", "34");
        	diskManager.add("File3", "5");
        	diskManager.add("File4", "67");        	
        } catch (InvalidOperationException | SizeUnenoughException e) {
			System.out.println(e.getMessage());
		}

        
        System.out.println(diskManager.toString());
        try {
        	diskManager.delete("File2");        	
        	System.out.println(diskManager.toString());
        	
        	diskManager.add("File5", "89");
        	
        	System.out.println(diskManager.toString());
        	
        	diskManager.delete("File5");
        	
        	System.out.println(diskManager.toString());
        } catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}


    }
}
