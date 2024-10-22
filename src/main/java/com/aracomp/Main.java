package com.aracomp;

import com.aracomp.TUI.InitialScreen;

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
    	
    	InitialScreen initialScreen = new InitialScreen();
        initialScreen.start();
    }
}
