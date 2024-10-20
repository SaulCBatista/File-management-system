package com.aracomp.fileSystem;

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

		if (name.isEmpty()) {
			System.out.println("File's name can't empty");
			return ;
		}

        if (content.isEmpty()) {
			System.out.println("File's content can't empty");
			return ;
		}

        int contentSize = content.length();

    
        if (contentSize > this.diskAvailableSize) {
            System.out.println("Storage's size is not enough");
            return ;
        }
        
        int address = this.disk.add(content);
		
		this.fileTable.add(name, contentSize, address);

        this.diskAvailableSize = this.diskAvailableSize - contentSize;

	}

    public String read(String name) {

		int andress = this.fileTable.search(name).getAddress();

        String content = this.disk.read(andress);

        return content;

	}

    public void delete(String name) {

        File file = this.fileTable.search(name);

        this.disk.delete(file.getAddress());

        this.fileTable.delete(name);

        this.diskAvailableSize = this.diskAvailableSize + file.getSize();

	}

    public int getRefEmptyBlock() {
        return refEmptyBlock;
    }

    public void setRefEmptyBlock(int refEmptyBlock) {
        this.refEmptyBlock = refEmptyBlock;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(this.fileTable.toString());
        sb.append("\n");
        sb.append(this.disk.toString());
        return sb.toString();
        
    }
    

}
