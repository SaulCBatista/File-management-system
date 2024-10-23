package com.aracomp.fileSystem;

public class Disk {
	private Block[] storage;
	private int totalSize;
	private DiskManager diskManager;
	
	public Disk(int size) {
		this.storage = new Block[size];
		this.totalSize = size;

		for (int i = 0; i < size - 1; i++) {
            this.storage[i] = new Block('\0', i + 1); 
        }

        this.storage[size - 1] = new Block('\0', -1);
	}

	public void setDiskManager(DiskManager diskManager) {
        this.diskManager = diskManager; 
		this.diskManager.setRefEmptyBlock(0);
    }
	
	public String read(int address) {
		int currentBlockIndex = address;
		StringBuilder content = new StringBuilder();
		
		while (currentBlockIndex != -1) {
			Block currentBlock = storage[currentBlockIndex];
			content.append(currentBlock.getData());
			currentBlockIndex = currentBlock.getNext();
		}
		
		return content.toString();
	}
	
	public int add(String content) {

		int refBlock = diskManager.getRefEmptyBlock();
		int startingBlockIndex;
		Block currentBlock = null;

		startingBlockIndex = refBlock;
		for (char ch : content.toCharArray()) {
			currentBlock = storage[refBlock];
			currentBlock.setData(ch);
			refBlock = currentBlock.getNext();
		}

		diskManager.setRefEmptyBlock(refBlock);
		currentBlock.setNext(-1);
		
	
		return startingBlockIndex;
	}

	public void delete(int address) {		
		int currentBlockIndex = address;
		Block currentBlock = null;

		while (currentBlockIndex != -1) {
			currentBlock = storage[currentBlockIndex];
			currentBlock.setData('\0');
			currentBlockIndex = currentBlock.getNext();
		}
		currentBlock.setNext(diskManager.getRefEmptyBlock());
		diskManager.setRefEmptyBlock(address);

	}

	public int getTotalSize() {
		return this.totalSize;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Disk Blocks:\n");
        for (int i = 0; i < totalSize; i++) {
            sb.append("Block ").append(i).append(": ");
            if (storage[i].getData() == '\0') {
            	sb.append("[Data: ").append("");
            } else {
            	sb.append("[Data: '").append(storage[i].getData()).append("', ");
            }
            sb.append("Next: ").append(storage[i].getNext()).append("]\n");
        }
        return sb.toString();
    }

}
