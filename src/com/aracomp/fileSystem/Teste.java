package com.aracomp.fileSystem;

import java.lang.reflect.Method;
import java.util.List;

public class Teste {

    private Disk disk;
    private DiskManager diskManager;
    private String description;

    public void setUp() {
        disk = new Disk(7);
        diskManager = new DiskManager(disk);
        disk.setDiskManager(diskManager);
    }

    public void execute(List<String> methodsToExecute) {
        try {
            setUp();
            
            for (String methodName : methodsToExecute) {
                Method method = this.getClass().getMethod(methodName);

                method.invoke(this);

                System.out.println("Teste executado: " + this.description);
                System.out.println("--------------------------- ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addFile() {
        this.description = "Adiciona um arquivo ao sistema de arquivos.";
        diskManager.add("file1", "content1");
    }

    public void addMultipleFiles() {
        this.description = "Adiciona múltiplos arquivos ao sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.add("file2", "content2");
    }

    public void addFilesToDiskLimit() {
        this.description = "Adiciona arquivos até o limite de armazenamento do disco.";
        diskManager.add("file1", "1234567");
    }

    public void addAboveDiskLimit() {
        this.description = "Tenta adicionar arquivos mesmo com o disco cheio.";
        diskManager.add("file1", "1234567");
        diskManager.add("file2", "extra");
    }

    public void addDuplicateFile() {
        this.description = "Tenta adicionar um arquivo que já existe no sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.add("file1", "content1");
    }

    public void readFile() {
        this.description = "Lê um arquivo específico do sistema de arquivos.";
        diskManager.add("file1", "content1");
        String content = diskManager.read("file1");
    }
    
    public void readMultipleFiles() {
        this.description = "Lê múltiplos arquivos do sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.add("file2", "content2");

        String content1 = diskManager.read("file1");
        String content2 = diskManager.read("file2");
    }

    public void readAllFiles() {
        this.description = "Lê todos os arquivos presentes no sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.add("file2", "content2");

        String content1 = diskManager.read("file1");
        String content2 = diskManager.read("file2");
    }

    public void readNonExistentFile() {
        this.description = "Tenta ler um arquivo que não existe no sistema de arquivos.";
        String content = diskManager.read("nonExistentFile");
    }

    public void deleteFile() {
        this.description = "Remove um arquivo do sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.delete("file1");
    }
    
    public void deleteMultipleFiles() {
        this.description = "Remove múltiplos arquivos do sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.add("file2", "content2");

        diskManager.delete("file1");
        diskManager.delete("file2");

    }

    public void deleteAllFiles() {
        this.description = "Remove todos os arquivos presentes no sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.add("file2", "content2");

        diskManager.delete("file1");
        diskManager.delete("file2");
    }

    public void deleteNonExistentFile() {
        this.description = "Tenta remover um arquivo que não existe no sistema de arquivos.";
        diskManager.delete("nonExistentFile");
    }

}
