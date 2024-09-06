package com.github.andremarchiori;

public class combateChar {
	private int id;
	private String name;
	private int maxHp;
	private int actualHp;
	private int exLog = 1;
	private int type;
	
	public combateChar() {}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHp() {
		return maxHp;
	}
	
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
		this.actualHp = maxHp;
	}
	
	public int getActualHp() {
		return actualHp;
	}
	
	public void sumActualHp(int value) {
		this.actualHp += value;
	if(this.actualHp == 0) {
		System.out.println("!!!!!Morrendo!!!!!");
	}else if(this.actualHp < 0) {
			this.exLog = 0;
		}
	}
	
	@Override
	public String toString() {
		return String.format("%d;%s;%d;%d;%d;%d", getId(), getName(), getMaxHp(), getActualHp(), this.type,this.exLog);
	}
}
