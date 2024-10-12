package com.aracomp.fileSystem;

public class Disk {
	private Block[] storage;
	private int totalSize;
	private int availableSize;
	private int refEmptyBlock;
	
	public Disk(int size) {
		this.storage = new Block[size];
		this.totalSize = size;
		this.availableSize = size;
		this.refEmptyBlock = 0;

		for (int i = 0; i < size - 1; i++) {
            this.storage[i] = new Block('\0', i + 1); 
        }

        this.storage[size - 1] = new Block('\0', 0);
	}
	
	public String read(File file) {
		int currentBlockIndex = file.getAddress();
		StringBuilder content = new StringBuilder();
		
		for(int i = 0; i < file.getSize(); i++) {
			Block currentBlock = storage[currentBlockIndex];
			content.append(currentBlock.getData());
			currentBlockIndex = currentBlock.getNext();
		}
		
		return content.toString();
	}
	
	public int add(String content, int contentSize) {

		int refBlock = this.refEmptyBlock;
		int startingBlockIndex;
	
		if (contentSize > availableSize) {
			System.out.println("Storage's size is not enough");
			return -1;
		}

		startingBlockIndex = refBlock;
	
		for (int i = 0; i < contentSize; i++) {
			Block currentBlock = storage[refBlock];
			currentBlock.setData(content.charAt(i));
			refBlock = currentBlock.getNext();
		}
	
		return startingBlockIndex;
	}
	
	
	public void delete(int address) {
		// TODO Gerenciamento de memória livre e realocação de dados
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Disk Blocks:\n");
        for (int i = 0; i < totalSize; i++) {
            sb.append("Block ").append(i).append(": ");
            sb.append("[Data: '").append(storage[i].getData()).append("', ");
            sb.append("Next: ").append(storage[i].getNext()).append("]\n");
        }
        return sb.toString();
    }

}
