package com.aracomp.fileSystem;

public class Block {
	private char data;
	private int next;

	public Block(char data, int next) {
		this.data = data;
		this.next = next;
	}

	public char getData() {
		return this.data;
	}

	public void setData(char data) {
		this.data = data;
	}

	public int getNext() {
		return this.next;
	}

	public void setNext(int next) {
		this.next = next;
	}
}
