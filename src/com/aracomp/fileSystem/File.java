package com.aracomp.fileSystem;

public class File {
	private String name;
	private int size;
	private String address;

	public File(String name, int size, String address) {
		this.name = name;
		this.size = size;
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
