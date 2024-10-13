package com.aracomp.fileSystem;

import java.util.ArrayList;

import com.aracomp.fileSystem.exception.FileNotFoundException;

public class FileTable {
	private ArrayList<File> files;

	public FileTable() {
		this.files = new ArrayList<>();
	}

	public File search(String name) {
		
		File fileFound = files.stream().filter(file -> file.getName().equals(name)).findFirst().orElseThrow(FileNotFoundException::new);
		
    	return fileFound;
	}

	public void add(String name, int size, int address) {
		File file = new File(name, size, address);
		
		this.files.add(file);
	}
	
	public void delete(String name) {
		files.removeIf(file -> file.getName().equals(name));
	}

	@Override
    public String toString() {
        if (files.isEmpty()) {
            return "No files available.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("File List:\n");
        for (File file : files) {
            sb.append("Name: ").append(file.getName())
              .append(", Size: ").append(file.getSize())
              .append(", Address: ").append(file.getAddress())
              .append("\n");
        }
        return sb.toString();
    }


}
