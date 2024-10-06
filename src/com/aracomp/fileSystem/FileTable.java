package com.aracomp.fileSystem;

import java.util.ArrayList;

public class FileTable {
	private ArrayList<File> files;

	public FileTable() {
	}

	public File read(String name) {
		File fileFound = files.stream().filter(file -> file.getName().equals(name)).findFirst().orElse(null);

		if (fileFound.equals(null)) {
			System.out.println("File not found");
		}
		
		return fileFound;
	}

	public void add(String name, String content) {
		if (name.isEmpty()) {
			System.out.println("File's name can't empty");
			return;
		}

		int size = content.length();
		File file = new File(name, size, null);
		
		this.files.add(file);
	}
	
	public void delete(String name) {
		files.removeIf(file -> file.getName().equals(name));
	}
}
