package com.ecb.game.cit.model;

public class PlayerModel {
	private int id;
	private String name;
	private int battle;
	private int san;
	private String chara;
	private String charaDetail;
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
	public int getSan() {
		return san;
	}
	public void setSan(int san) {
		this.san = san;
	}
	public String getChara() {
		return chara;
	}
	public void setChara(String chara) {
		this.chara = chara;
	}
	public String getCharaDetail() {
		return charaDetail;
	}
	public void setCharaDetail(String charaDetail) {
		this.charaDetail = charaDetail;
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
