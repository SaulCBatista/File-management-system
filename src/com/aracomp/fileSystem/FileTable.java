package com.aracomp.fileSystem;

import java.util.ArrayList;

public class FileTable {
	private ArrayList<File> files;
	private Disk disk;

	public FileTable(Disk disk) {
		this.disk = disk;
	}

	public String read(String name) {
		File fileFound = files.stream().filter(file -> file.getName().equals(name)).findFirst().orElse(null);

		if (fileFound.equals(null)) {
			System.out.println("File not found");
		}
		
		return disk.read(fileFound);
	}

	public void add(String name, String content) {
		if (name.isEmpty()) {
			System.out.println("File's name can't empty");
			return;
		}

		int size = content.length();
		int address = disk.add(content, size);
		
		File file = new File(name, size, address);
		
		this.files.add(file);
	}
	
	public void delete(String name) {
		files.removeIf(file -> file.getName().equals(name));
	}
}
