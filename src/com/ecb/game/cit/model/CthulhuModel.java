package com.ecb.game.cit.model;

public class CthulhuModel {
	private int id;
	private String name;
	private int battle;
	private String skill;
	private String skillDetail;

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
	public int getBattle() {
		return battle;
	}
	public void setBattle(int battle) {
		this.battle = battle;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getSkillDetail() {
		return skillDetail;
	}
	public void setSkillDetail(String skillDetail) {
		this.skillDetail = skillDetail;
	}
}
