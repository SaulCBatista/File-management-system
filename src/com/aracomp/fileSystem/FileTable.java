package com.aracomp.fileSystem;
import java.util.ArrayList;

public class FileTable {
	private ArrayList<File> files;
	private Disk disk;

	public FileTable(Disk disk) {
		this.disk = disk;
		this.files = new ArrayList<>();
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
