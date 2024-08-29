package com.github.andremarchiori;

public class Personagem {
	private int id;
	private int exLogic = 1;
	private int level = 1;
	private long experience = 0;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Personagem() {
	}
	

	public int getExLogic() {
		return exLogic;
	}

	public void setExLogic(int exLogic) {
		this.exLogic = exLogic;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getExperience() {
		return experience;
	}

	public void setExperience(long experience) {
		this.experience = experience;
		refreshLevel(this.level, this.experience);
	}

	public void addExperience(long experience) {
		this.experience += experience;
		refreshLevel(this.level, this.experience);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void refreshLevel(int level, long experience) {
		switch (level) {
		case 1:
			if (experience >= 300) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 2:
			if (experience >= 900) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 3:
			if (experience >= 2700) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 4:
			if (experience >= 6500) {
				levelUp();
				;
				refreshLevel(this.level, this.experience);
			}
			break;
		case 5:
			if (experience >= 14000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 6:
			if (experience >= 23000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 7:
			if (experience >= 34000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 8:
			if (experience >= 48000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 9:
			if (experience >= 64000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 10:
			if (experience >= 85000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 11:
			if (experience >= 100000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 12:
			if (experience >= 120000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 13:
			if (experience >= 140000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 14:
			if (experience >= 165000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 15:
			if (experience >= 195000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 16:
			if (experience >= 225000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 17:
			if (experience >= 265000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 18:
			if (experience >= 305000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 19:
			if (experience >= 355000) {
				levelUp();
				refreshLevel(this.level, this.experience);
			}
			break;
		case 20:
			break;
		default:
			System.err.println("Not valid level value");
		}
	}

	private void levelUp() {
		this.level += 1;
	}
	
	@Override
	public String toString() {
		return String.format("%d;%s;%d;%d;%d", this.id, this.name, this.level, this.experience, this.exLogic);
	}
}