package com.aracomp.fileSystem;

public class Block {
	private char data;
	private int pointer;

	public Block(char data, int pointer) {
		this.data = data;
		this.pointer = pointer;
	}

	public char getData() {
		return this.data;
	}

	public void setData(char data) {
		this.data = data;
	}

	public int getPointer() {
		return this.pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
}
