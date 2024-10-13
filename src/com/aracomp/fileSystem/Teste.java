package com.aracomp.fileSystem;

import java.lang.reflect.Method;
import java.util.List;

public class Teste {

    private Disk disk;
    private DiskManager diskManager;
    private String description;

    public void setUp() {
        disk = new Disk(10);  
        diskManager = new DiskManager(disk);
        disk.setDiskManager(diskManager);
    }

    public void execute(List<String> methodsToExecute) {
        try {

            for (String methodName : methodsToExecute) {

                setUp();

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
        diskManager.add("file1", "cont1"); 
        diskManager.add("file2", "cont2"); 
    }

    public void addFilesToDiskLimit() {
        this.description = "Adiciona arquivos até o limite de armazenamento do disco.";
        diskManager.add("file1", "123");  
        diskManager.add("file1", "456");  
        diskManager.add("file1", "7");  
        diskManager.add("file2", "890");    
    }

    public void addAboveDiskLimit() {
        this.description = "Tenta adicionar arquivos acima do limite de memória.";
        diskManager.add("file1", "123");  
        diskManager.add("file1", "456");  
        diskManager.add("file1", "77");  
        diskManager.add("file2", "890");  
    }
    
    public void  addDiskFill() {
        this.description = "Tenta adicionar arquivos mesmo com o disco cheio.";
        diskManager.add("file1", "1234567890");    
        diskManager.add("file2", "00");
        diskManager.add("file2", "11");  
        diskManager.add("file2", "22");  
    }

    public void addDuplicateFile() {
        this.description = "Tenta adicionar um arquivo que já existe no sistema de arquivos.";
        diskManager.add("file1", "content1"); 
        diskManager.add("file1", "content1"); 
    }

    public void readFile() {
        this.description = "Lê um arquivo específico do sistema de arquivos.";
        diskManager.add("file1", "content1");  
        diskManager.read("file1");  
    }

    public void readMultipleFiles() {
        this.description = "Lê múltiplos arquivos do sistema de arquivos.";
        diskManager.add("file1", "con1");
        diskManager.add("file2", "con2");
        diskManager.add("file3", "ee");
        diskManager.read("file1");
        diskManager.read("file3");
    }

    public void readAllFiles() {
        this.description = "Lê todos os arquivos presentes no sistema de arquivos.";
        diskManager.add("file1", "con1");
        diskManager.add("file2", "con2");
        diskManager.add("file3", "ee");
        diskManager.read("file1");
        diskManager.read("file2");
        diskManager.read("file3");
    }

    public void readNonExistentFile() {
        this.description = "Tenta ler um arquivo que não existe no sistema de arquivos.";
        diskManager.add("file1", "con1");
        diskManager.add("file2", "con2");
        diskManager.add("file3", "ee");
        diskManager.read("nonExistentFile"); 
    }

    public void deleteFile() {
        this.description = "Remove um arquivo do sistema de arquivos.";
        diskManager.add("file1", "content1");
        diskManager.delete("file1");
    }

    public void deleteMultipleFiles() {
        this.description = "Remove múltiplos arquivos do sistema de arquivos.";
        diskManager.add("file1", "con1");
        diskManager.add("file2", "con2");
        diskManager.add("file3", "ee");
        diskManager.delete("file1");
        diskManager.delete("file2"); 
    }

    public void deleteAllFiles() {
        this.description = "Remove todos os arquivos presentes no sistema de arquivos.";
        diskManager.add("file1", "con1");
        diskManager.add("file2", "con2");
        diskManager.add("file3", "ee");

        diskManager.delete("file1");
        diskManager.delete("file2");
        diskManager.delete("file3");
    }

    public void deleteNonExistentFile() {
        this.description = "Tenta remover um arquivo que não existe no sistema de arquivos.";
        diskManager.add("file1", "con1");
        diskManager.add("file2", "con2");
        diskManager.add("file3", "ee");
        diskManager.delete("nonExistentFile"); 
    }
}
