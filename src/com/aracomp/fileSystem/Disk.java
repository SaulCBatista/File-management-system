package com.aracomp.fileSystem;

public class Disk {
	private Block[] storage;
	private int totalSize;
	private int availableSize;
	
	public Disk(int size) {
		this.storage = new Block[size];
		this.totalSize = size;
		this.availableSize = size;
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
	
	public int add(String content, int size) {
		if(size > availableSize) {
			System.out.println("storage's size unenough");
			return -1;
		}
		
		// TODO Gerenciamento de memória livre e alocação de dados
		
		return 1;
	}
	
	public void delete(int address) {
		// TODO Gerenciamento de memória livre e realocação de dados
	}
}
