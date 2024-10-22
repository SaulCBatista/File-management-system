package com.aracomp.fileSystem;

import java.nio.file.FileSystemNotFoundException;

import com.aracomp.exception.InvalidOperationException;
import com.aracomp.exception.StorageUnenoughException;

public class DiskManager {

    private int diskAvailableSize;
    private Disk disk;
    private FileTable fileTable;
    private int refEmptyBlock;
    
    public DiskManager(Disk disk) {
        this.disk = disk;
        fileTable = new FileTable();
        diskAvailableSize = disk.getTotalSize();

	}

    public void add(String name, String content) {
		if (name == null || name.isEmpty()) {
			throw new InvalidOperationException("File's name cannot be empty");
		}

        if (content == null || content.isEmpty()) {
			throw new InvalidOperationException("File's content cannot be empty");
		}

        int contentSize = content.length();

    
        if (contentSize > this.diskAvailableSize) {
        	throw new StorageUnenoughException("Storage's size is not enough");
        }
        
        int address = this.disk.add(content);
		
		this.fileTable.add(name, contentSize, address);

        this.diskAvailableSize = this.diskAvailableSize - contentSize;

	}

    public String read(String name) {
    	File file = this.fileTable.search(name);
    	
    	if (file == null) {
    		throw new FileSystemNotFoundException();
    	}
    	
		int andress = file.getAddress();

        String content = this.disk.read(andress);

        return content;

	}

    public void delete(String name) {

        File file = this.fileTable.search(name);

        this.disk.delete(file.getAddress());

        this.fileTable.delete(name);

        this.diskAvailableSize = this.diskAvailableSize + file.getSize();

	}
    
    public String showStructure() {
    	StringBuilder sb = new StringBuilder();
        sb.append(this.fileTable.toString());
        sb.append("\n");
        sb.append(this.disk.toString());
        return sb.toString();
    }

    public int getRefEmptyBlock() {
        return refEmptyBlock;
    }

    public void setRefEmptyBlock(int refEmptyBlock) {
        this.refEmptyBlock = refEmptyBlock;
    }

}
