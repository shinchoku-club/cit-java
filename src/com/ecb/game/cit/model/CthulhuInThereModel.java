package com.ecb.game.cit.model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.ecb.game.cit.code.CardEnum;
import com.ecb.game.cit.code.CthulhuEnum;
import com.ecb.game.cit.code.EventEnum;
import com.ecb.game.cit.code.KindEnum;
import com.ecb.game.cit.code.PlayerEnum;
import com.ecb.game.cit.code.TypeEnum;
import com.ecb.game.cit.util.GameConstant;
import com.ecb.game.cit.util.GameUtil;
import com.ecb.game.cit.util.JsonConverterUtil;
import com.ecb.game.cit.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CthulhuInThereModel {
	Logger log = Logger.getLogger(CthulhuInThereModel.class.getName());

	private List<PlayerInfoModel> playerInfos;
	private int phase;
	private int subPhase;
	private int turn;
	private int lastTurnPlayer;
	private List<CardModel> cardDeck;
	private List<EventModel> eventDeck;
	private String mes;
	private List<String> suspendArr;
	private CardModel removeCard;
	private List<PlayerInfoModel> cthulhuSide;
	private CthulhuModel cthulhu;
	private List<Integer> cthulhuFate;
	private List<PlayerInfoModel> winners;
	private List<PlayerInfoModel> losers;
	private String se;

	public CthulhuInThereModel() {
		this.init();
	}

	public List<PlayerInfoModel> getPlayerInfos() {
		return playerInfos;
	}
	public void setPlayerInfos(List<PlayerInfoModel> playerInfos) {
		this.playerInfos = playerInfos;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getSubPhase() {
		return subPhase;
	}
	public void setSubPhase(int subPhase) {
		this.subPhase = subPhase;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int getLastTurnPlayer() {
		return lastTurnPlayer;
	}
	public void setLastTurnPlayer(int lastTurnPlayer) {
		this.lastTurnPlayer = lastTurnPlayer;
	}
	public List<CardModel> getCardDeck() {
		return cardDeck;
	}
	public void setCardDeck(List<CardModel> cardDeck) {
		this.cardDeck = cardDeck;
	}
	public List<EventModel> getEventDeck() {
		return eventDeck;
	}
	public void setEventDeck(List<EventModel> eventDeck) {
		this.eventDeck = eventDeck;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public List<String> getSuspendArr() {
		return suspendArr;
	}
	public void setSuspendArr(List<String> suspendArr) {
		this.suspendArr = suspendArr;
	}
	public CardModel getRemoveCard() {
		return removeCard;
	}
	public void setRemoveCard(CardModel removeCard) {
		this.removeCard = removeCard;
	}
	public List<PlayerInfoModel> getCthulhuSide() {
		return cthulhuSide;
	}
	public void setCthulhuSide(List<PlayerInfoModel> cthulhuSide) {
		this.cthulhuSide = cthulhuSide;
	}
	public CthulhuModel getCthulhu() {
		return cthulhu;
	}
	public void setCthulhu(CthulhuModel cthulhu) {
		this.cthulhu = cthulhu;
	}
	public List<Integer> getCthulhuFate() {
		return cthulhuFate;
	}
	public void setCthulhuFate(List<Integer> cthulhuFate) {
		this.cthulhuFate = cthulhuFate;
	}
	public List<PlayerInfoModel> getWinners() {
		return winners;
	}
	public void setWinners(List<PlayerInfoModel> winners) {
		this.winners = winners;
	}
	public List<PlayerInfoModel> getLosers() {
		return losers;
	}
	public void setLosers(List<PlayerInfoModel> losers) {
		this.losers = losers;
	}

	public String getSe() {
		return se;
	}

	public void setSe(String se) {
		this.se = se;
	}

	public void appendMes(String mes) {
		this.setMes(this.getMes() + mes);
	}

	public PlayerInfoModel getTurnPlayer() {
		if (this.getTurn() >= 0 && this.getTurn() < this.getPlayerInfos().size()) {
			return this.getPlayerInfos().get(this.getTurn());
		}
		return null;
	}

	@SuppressWarnings(value = { "unused" })
	@JsonIgnore
	public String getView(String name) {
		CthulhuInThereModel view = new CthulhuInThereModel();

		view.playerInfos = new ArrayList<PlayerInfoModel>();
		for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
			view.playerInfos.add(playerInfo.getView(name));
		}
		view.phase = this.getPhase();
		view.subPhase = this.getSubPhase();
		view.turn = this.getTurn();
		view.lastTurnPlayer = this.getLastTurnPlayer();
		view.cardDeck = new ArrayList<CardModel>();
		for (CardModel card : this.getCardDeck()) {
			view.cardDeck.add(CardModel.getUnknownCard());
		}
		view.eventDeck = new ArrayList<EventModel>();
		for (EventModel event : this.getEventDeck()) {
			view.eventDeck.add(EventModel.getUnknownEvent());
		}
		view.mes = this.getMes();
		view.suspendArr = this.getSuspendArr();
		view.removeCard = this.getRemoveCard();
		view.cthulhuSide = this.getCthulhuSide();
		view.cthulhu = this.getCthulhu();
		view.cthulhuFate = this.getCthulhuFate();
		view.winners = this.getWinners();
		view.losers = this.getLosers();

		String ret = "{}";
		try {
			ret = JsonConverterUtil.toString(view);
		} catch (Exception exc) {
			log.error(exc.getMessage());
		}
		return ret;
	}

	public boolean join(String name) {
		if (this.getPhase() != GameConstant.READY_PHASE) {
			return false;
		}

		if (this.getPlayerInfos().size() >= 8) {
			return false;
		}

		PlayerInfoModel joinPlayer = new PlayerInfoModel(name);
		this.getPlayerInfos().add(joinPlayer);

		return true;
	}

	public boolean exit(String name) {
		if (this.getPhase() != GameConstant.READY_PHASE) {
			return false;
		}

		if (this.getPlayerInfos().size() >= 9) {
			return false;
		}

		for (int i = this.getPlayerInfos().size() - 1; i >= 0 ; i--) {
			if (this.getPlayerInfos().get(i).getName().equals(name)) {
				this.getPlayerInfos().remove(i);
				break;
			}
		}

		return true;
	}

	public void startGame() {
		if (this.getPhase() != GameConstant.READY_PHASE) {
			return;
		}

		if (this.getPlayerInfos().size() < 4) {
			return;
		}

		List<PlayerModel> playerDeck = GameUtil.createPlayerDeck();
		List<Integer> sanDeck = GameUtil.createSanDeck();
		List<CardModel> cardDeck = GameUtil.createCardDeck();

		for (PlayerInfoModel info : this.getPlayerInfos()) {
			info.setPlayer(playerDeck.remove(0));
			info.setSan(sanDeck.remove(0).intValue());
			for (int i = 0; i < 3; i++) {
				info.getHands().add(cardDeck.remove(0));
			}
		}

		this.setPhase(GameConstant.TOP_PHASE);
		this.setSubPhase(GameConstant.DRAW_SUB_PHASE);

		int minNo = 10;
		for (int i = 0; i < this.getPlayerInfos().size(); i++) {
			PlayerInfoModel info = this.getPlayerInfos().get(i);
			if (info.getPlayer().getId() < minNo) {
				minNo = info.getPlayer().getId();
				this.setTurn(i);
			}
		}

		this.setCardDeck(GameUtil.timePutShuffle(cardDeck));
		this.setEventDeck(GameUtil.createEventDeck());
	}

	public void action(String act) {
		this.setMes(StringUtil.EMPTY);
		this.setSe(null);

		try {
			String[] actArr = act.split(GameConstant.SEPARATOR);
			String name;
			String actionStr;
			List<String> args = new ArrayList<String>();
			name = actArr[0];
			actionStr = actArr[1];
			for (int i = 2; i < actArr.length; i++) {
				args.add(actArr[i]);
			}

			if (actionStr.equals(GameConstant.DRAW_ACTION)) {
				if (this.isTurn(name)) {
					this.draw();
				}
			} else if (actionStr.equals(GameConstant.USE_PREDRAW_SKILL_ACTION)) {
				if (this.isTurn(name)) {
					this.predrawSkill(args);
				}
			} else if (actionStr.equals(GameConstant.DILETTANTE_DROP_CHOOSE_ACTION)) {
				if (this.isTurn(name)) {
					this.dilettanteDrop(Integer.parseInt(args.get(0)));
				}
			} else if (actionStr.equals(GameConstant.DILETTANTE_CHOOSE_ACTION)) {
				if (this.isTurn(name)) {
					this.dilettanteChoose(Integer.parseInt(args.get(0)));
				}
			} else if (actionStr.equals(GameConstant.CHOOSE_ACTION)) {
				if (this.isTurn(name)) {
					this.choose(Integer.parseInt(args.get(0)));
				}
			} else if (actionStr.equals(GameConstant.USE_ACTION)) {
				if (this.isTurn(name)) {
					this.use(args);
				}
			} else if (actionStr.equals(GameConstant.REPAIR_ACTION)) {
				this.repair(name, args.get(0));
			} else if (actionStr.equals(GameConstant.REGULATE_ACTION)) {
				this.regulate(name, args.get(0));
			}


		} catch (Exception exc) {
			log.error(exc.getMessage());
		}
	}

	public void calc(String name) {
		this.setMes(StringUtil.EMPTY);

		try {
			if (this.getPhase() == GameConstant.ADVENT_PHASE) {
				for (PlayerInfoModel playerInfo : getPlayerInfos()) {
					if (playerInfo.getName().equals(name)) {
						if (!playerInfo.getCalculated()) {
							playerInfo.setCalculated(true);
							StringBuilder line = new StringBuilder();
							line.append(playerInfo.getName());
							line.append("の合計狂気度は");
							line.append(playerInfo.totalSan());
							this.appendMes(line.toString());

							this.getCthulhuSide().clear();

							int maxSan = -999;
							PlayerInfoModel maxPlayer = null;
							boolean allCalculated = true;
							for (PlayerInfoModel cthulhuSidePlayerInfo : this.getPlayerInfos()) {
								if (!cthulhuSidePlayerInfo.getCalculated()) {
									allCalculated = false;
								}
								if (cthulhuSidePlayerInfo.totalSan() >= 100) {
									this.getCthulhuSide().add(cthulhuSidePlayerInfo);
								}
								if (cthulhuSidePlayerInfo.totalSan() > maxSan) {
									maxSan = cthulhuSidePlayerInfo.totalSan();
									maxPlayer = cthulhuSidePlayerInfo;
								}
							}
							if (allCalculated) {
								if (this.getCthulhuSide().size() <= 0 && maxPlayer != null) {
									this.getCthulhuSide().add(maxPlayer);
								}

								List<CardModel> restDeck = this.restDeck();
								int totalAdvent = 0;
								StringBuilder adventLine = new StringBuilder();
								adventLine.append("邪神サイドは");
								for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
									adventLine.append(cthulhuSide.getName());
									adventLine.append("さん");
									adventLine.append("判定値:");
									adventLine.append(cthulhuSide.calcAdvent());
									totalAdvent += cthulhuSide.calcAdvent();

									CardModel fateCard = restDeck.remove(0);
									adventLine.append("運命数:");
									adventLine.append(fateCard.getFate());
									totalAdvent += fateCard.getFate();
									if (fateCard.getFate() % 10 == cthulhuSide.getPlayer().getId()) {
										adventLine.append("クリティカル+20");
										totalAdvent += 20;
									}
								}
								adventLine.append("です");
								adventLine.append("合計判定値:");
								adventLine.append(totalAdvent);

								this.setCthulhu(GameUtil.adventCthulhu(totalAdvent));
								adventLine.append(this.getCthulhu().getName());
								adventLine.append("が降臨しました");

								this.appendMes(adventLine.toString());
								for (PlayerInfoModel itemPlayerInfo : this.getPlayerInfos()) {
									boolean isPlayer = true;
									for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
										if (itemPlayerInfo.getPlayer().getId() == cthulhuSide.getPlayer().getId()) {
											isPlayer = false;
										}
									}

									List<CardModel> restItems = new ArrayList<CardModel>();
									List<CardModel> myItems = new ArrayList<CardModel>();

									myItems.addAll(itemPlayerInfo.getOpenMyItems());
									myItems.addAll(itemPlayerInfo.getClosedMyItems());
									myItems.addAll(itemPlayerInfo.getOpenAnotherItems());
									myItems.addAll(itemPlayerInfo.getClosedAnotherItems());

									if (isPlayer) {
										for (CardModel item : myItems) {
											if (item.getType() == TypeEnum.SIGN.getId()) {
												restItems.add(item);
											} else if (Arrays.asList(GameConstant.BATTON_AND_GUN_IDS).contains(Integer.valueOf(item.getType()))) {
												if (this.getCthulhu().getId() == CthulhuEnum.THE_HOUNDS_OF_TINDALOS.getId()) {
													boolean hasBook = false;
													for (CardModel bookCheck : myItems) {
														if (Arrays.asList(GameConstant.BOOK_IDS).contains(Integer.valueOf(bookCheck.getType()))) {
															hasBook = true;
															break;
														}
													}

													if (hasBook) {
														restItems.add(item);
													}
												} else if (this.getCthulhu().getId() != CthulhuEnum.SHUB_NIGGURATH.getId() && this.getCthulhu().getId() != CthulhuEnum.SHOGGOTH.getId()) {
													restItems.add(item);
												}
											} else if (item.getType() == TypeEnum.FIRE.getId()) {
												if (this.getCthulhu().getId() == CthulhuEnum.THE_HOUNDS_OF_TINDALOS.getId()) {
													boolean hasBook = false;
													for (CardModel bookCheck : myItems) {
														if (Arrays.asList(GameConstant.BOOK_IDS).contains(Integer.valueOf(bookCheck.getType()))) {
															hasBook = true;
															break;
														}
													}

													if (hasBook) {
														restItems.add(item);
													}
												} else if (this.getCthulhu().getId() != CthulhuEnum.SHUB_NIGGURATH.getId()) {
													restItems.add(item);
												}
											} else if (Arrays.asList(GameConstant.ANOTHER_WEAPON_IDS).contains(Integer.valueOf(item.getType()))) {
												restItems.add(item);
											}
										}
									} else {
										for (CardModel item : myItems) {
											if (Arrays.asList(GameConstant.SAN_ITEM_IDS).contains(Integer.valueOf(item.getType()))) {
												restItems.add(item);
											}
										}
									}

									itemPlayerInfo.setOpenMyItems(restItems);
									itemPlayerInfo.getClosedMyItems().clear();
									itemPlayerInfo.getOpenAnotherItems().clear();
									itemPlayerInfo.getClosedAnotherItems().clear();
								}
								this.setCardDeck(this.restDeck());
								this.setPhase(GameConstant.BATTLE_PHASE);
								this.setCthulhuFate(new ArrayList<Integer>());
								if (this.getCthulhu().getId() == CthulhuEnum.NYARLATHOTEP.getId() && this.battleSkillExists()) {
									this.setSubPhase(GameConstant.NYARLATHOTEP_SUB_PHASE);
								} else {
									this.setSubPhase(GameConstant.BATTLE_FATE_SUB_PHASE);
								}
							}
						}
					}
				}
			}
		}catch (Exception exc) {
			log.error(exc.getMessage());
		}
	}

	public void battleAction(String act) {
		this.setMes(StringUtil.EMPTY);
		try {
			String[] actArr = act.split(GameConstant.SEPARATOR);
			String name;
			String actionStr;
			List<String> args = new ArrayList<String>();
			name = actArr[0];
			actionStr = actArr[1];
			for (int i = 2; i < actArr.length; i++) {
				args.add(actArr[i]);
			}

			if (actionStr.equals(GameConstant.NYARLATHOTEP_ACTION)) {
				if (this.isCthulhuSide(name) && this.getSubPhase() == GameConstant.NYARLATHOTEP_SUB_PHASE) {
					boolean isCthulhuSide = false;
					for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
						if (cthulhuSide.getPlayer().getId() == this.getPlayerInfos().get(Integer.parseInt(args.get(0))).getPlayer().getId()) {
							isCthulhuSide = true;
						}
					}

					if (!isCthulhuSide
							&& Arrays.asList(GameConstant.BATTLE_SKILL_HOLDER_IDS).contains(Integer.valueOf(this.getPlayerInfos().get(Integer.parseInt(args.get(0))).getPlayer().getId()))
							&& this.getPlayerInfos().get(Integer.parseInt(args.get(0))).getSkill()) {
						this.getPlayerInfos().get(Integer.parseInt(args.get(0))).setSkill(false);
						this.setSubPhase(GameConstant.BATTLE_FATE_SUB_PHASE);
					}
				}
			} else if (actionStr.equals(GameConstant.DRAW_FATE_ACTION)) {
				if (this.getSubPhase() == GameConstant.BATTLE_FATE_SUB_PHASE) {
					if (this.isCthulhuSide(name) && this.getCthulhuFate().size() < this.getPlayerInfos().size() - 1) {
						CardModel fateCard = this.getCardDeck().remove(0);
						int fate = fateCard.getFate();
						StringBuilder fateLine = new StringBuilder();
						fateLine.append("邪神サイド:運命数:");
						fateLine.append(fate);

						if (this.getCthulhu().getId() == CthulhuEnum.AZATHOTH.getId()) {
							if (fate % 10 == 0) {
								fate += 1;
								fateLine.append("アザトースの能力により運命数+1");
							} else if (fate % 10 == 1) {
								fate += 0;
							} else if (fate % 10 == 2) {
								fate -= 1;
								fateLine.append("アザトースの能力により運命数-1");
							} else if (fate % 10 == 3) {
								fate -= 2;
								fateLine.append("アザトースの能力により運命数-2");
							} else {
								fate += 2;
								fateLine.append("アザトースの能力により運命数+2");
							}
						}

						if (fate % 10 == this.getCthulhu().getId()) {
							fate += 20;
							fateLine.append("クリティカルより運命数+20");
						}

						this.getCthulhuFate().add(Integer.valueOf(fate));
						this.appendMes(fateLine.toString());
					} else {
						for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
							if (playerInfo.getName().equals(name) && playerInfo.getFate() == 0) {
								CardModel fateCard = this.getCardDeck().remove(0);
								int fate = fateCard.getFate();
								boolean churchmanExist = false;
								StringBuilder fateLine = new StringBuilder();
								fateLine.append(playerInfo.getName());
								fateLine.append(":運命数:");
								fateLine.append(fate);

								for (PlayerInfoModel churchman : this.getPlayerInfos()) {
									if (churchman.getPlayer().getId() == PlayerEnum.CHURCHMAN.getId()
											&& churchman.getSkill()) {
										churchmanExist = true;
										for (PlayerInfoModel cthulhuSideChurchman : this.getCthulhuSide()) {
											if (cthulhuSideChurchman.getPlayer().getId() == PlayerEnum.CHURCHMAN.getId()) {
												churchmanExist = false;
												break;
											}
										}
									}
								}

								if (churchmanExist) {
									if (fate % 10 == (playerInfo.getPlayer().getId() + 1) % 10) {
										fate -= 1;
										fateLine.append("聖職者の効果で運命数-1");
									} else if (fate % 10 == playerInfo.getPlayer().getId()) {
										fate += 0;
									} else {
										fate += 1;
										fateLine.append("聖職者の効果で運命数+1");

									}
								}

								if (fate % 10 == playerInfo.getPlayer().getId()) {
									fate += 20;
									fateLine.append("クリティカルより運命数+20");
								}

								playerInfo.setFate(fate);
								this.appendMes(fateLine.toString());
								break;
							}
						}
					}

					boolean drawFinished = true;
					for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
						boolean isCthulhuSide = false;
						for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
							if (playerInfo.getPlayer().getId() == cthulhuSide.getPlayer().getId()) {
								isCthulhuSide = true;
							}
						}
						if (!isCthulhuSide && playerInfo.getFate() == 0) {
							drawFinished = false;
						}
					}
					if (this.getCthulhuFate().size() < this.getPlayerInfos().size() - 1) {
						drawFinished = false;
					}

					if (drawFinished) {
						boolean professorExist = false;
						for (PlayerInfoModel professor : this.getPlayerInfos()) {
							if (professor.getPlayer().getId() == PlayerEnum.PROFESSOR.getId()
									&& professor.getSkill()) {
								professorExist = true;
								for (PlayerInfoModel cthulhuSideProfessor : this.getCthulhuSide()) {
									if (cthulhuSideProfessor.getPlayer().getId() == PlayerEnum.PROFESSOR.getId()) {
										professorExist = false;
										break;
									}
								}
							}
						}
						if (professorExist) {
							this.setSubPhase(GameConstant.PROFESSOR_SKILL_SUB_PHASE);
						} else {
							this.battle(-1);
							this.setSe(GameConstant.ENDING_SE);
						}
					}
				}
			} else if (actionStr.equals(GameConstant.PROFESSOR_SKILL_ACTION)) {
				if (this.getSubPhase() == GameConstant.PROFESSOR_SKILL_SUB_PHASE) {
					boolean isCthulhuSIde = false;
					for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
						if (this.getPlayerInfos().get(Integer.parseInt(args.get(0))).getPlayer().getId() == cthulhuSide.getPlayer().getId()) {
							isCthulhuSIde = true;
						}
					}
					for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
						if (playerInfo.getName().equals(name)
								&& playerInfo.getPlayer().getId() == PlayerEnum.PROFESSOR.getId()
								&& !isCthulhuSIde
								&& this.getPlayerInfos().get(Integer.parseInt(args.get(0))).getPlayer().getId() != PlayerEnum.PROFESSOR.getId()) {
							this.battle(Integer.parseInt(args.get(0)));
							this.setSe(GameConstant.ENDING_SE);
						}
					}
				}
			}
		} catch (Exception exc) {
			log.error(exc.getMessage());
		}
	}

	public boolean reset() {
		if (this.getPhase() == GameConstant.AFTER_PHASE) {
			this.init();
			return true;
		}

		return false;
	}

	private void init() {
		this.playerInfos = new ArrayList<PlayerInfoModel>();
		this.phase = GameConstant.READY_PHASE;
		this.subPhase = GameConstant.READY_SUB_PHASE;
		this.turn = 0;
		this.lastTurnPlayer = 0;
		this.cardDeck = new ArrayList<CardModel>();
		this.eventDeck = new ArrayList<EventModel>();
		this.mes = StringUtil.EMPTY;
		this.suspendArr = new ArrayList<String>();
		this.removeCard = null;
		this.cthulhuSide = new ArrayList<PlayerInfoModel>();
		this.cthulhu = null;
		this.cthulhuFate = new ArrayList<Integer>();
		this.winners = new ArrayList<PlayerInfoModel>();
		this.losers = new ArrayList<PlayerInfoModel>();
	}

	private boolean isTurn(String name) {
		return this.getTurnPlayer().getName().equals(name);
	}

	private void timeProgress() {
		EventModel event = this.getEventDeck().remove(0);
		this.appendMes(MessageFormat.format(GameConstant.FORMAT_TIME_PROGRESS, event.getName()));

		if (event.getId() == EventEnum.EVENT4.getId()) {
			for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
				playerInfo.randomOpen();
			}

			this.getEventDeck().add(event);
			Collections.shuffle(this.getEventDeck());
		} else {
			this.getTurnPlayer().getEvents().add(event);
		}

		if (this.getPhase() == GameConstant.TOP_PHASE) {
			this.setPhase(GameConstant.MIDDLE_PHASE);
		} else if (this.getPhase() == GameConstant.MIDDLE_PHASE) {
			this.setPhase(GameConstant.BOTTOM_PHASE);
		} else if (this.getPhase() == GameConstant.BOTTOM_PHASE) {
			this.setPhase(GameConstant.LAST_PHASE);
			if (this.getTurn() != 0) {
				this.setLastTurnPlayer(this.getTurn() - 1);
			} else {
				this.setLastTurnPlayer(this.getPlayerInfos().size() - 1);
			}
		}
		this.setSe(GameConstant.SCREAM_SE);
	}

	private void nextTurn() {
		if (this.getPhase() == GameConstant.LAST_PHASE) {
			if (this.getTurn() == this.getLastTurnPlayer()) {
				this.setPhase(GameConstant.ADVENT_PHASE);
				this.setSubPhase(GameConstant.READY_SUB_PHASE);
			} else {
				int turn = this.getTurn() + 1;
				if (turn >= this.getPlayerInfos().size()) {
					this.setTurn(0);
				} else {
					this.setTurn(turn);
				}
				this.useSelectionSet();
				this.setSubPhase(GameConstant.USE_SUB_PHASE);
			}
		} else {
			int turn = this.getTurn() + 1;
			if (turn >= this.getPlayerInfos().size()) {
				this.setTurn(0);
			} else {
				this.setTurn(turn);
			}
			this.setSubPhase(GameConstant.DRAW_SUB_PHASE);
		}
	}

	private void useSelectionSet() {
		List<CardModel> selections = new ArrayList<CardModel>();
		boolean engineerFlag = false;
		boolean journalistFlag = false;
		boolean detectiveFlag = false;
		boolean policemanFlag = false;
		boolean armyFlag = false;

		if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.ENGINEER.getId()
				&& this.getTurnPlayer().hasItems()) {
			for (CardModel card : this.getTurnPlayer().getHands()) {
				if (card.getType() == TypeEnum.ITEM_REMOVE.getId()) {
					engineerFlag = true;
				}
			}
		}

		if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.JOURNALIST.getId()) {
			for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
				if (playerInfo.hasClosedItems()) {
					for (CardModel card : this.getTurnPlayer().getHands()) {
						if (card.getType() == TypeEnum.ITEM_OPEN.getId()) {
							journalistFlag = true;
						}
					}
				}
			}
		}

		if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.DETECTIVE.getId()) {
			int investigate = 0;
			for (CardModel card : this.getTurnPlayer().getHands()) {
				if (card.getKind() == KindEnum.CHECK.getId()) {
					investigate++;
				}
			}
			if (investigate >= 2) {
				detectiveFlag = true;
			}
		}

		if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.POLICEMAN.getId()) {
			for (CardModel card : this.getTurnPlayer().getHands()) {
				if (card.getType() == TypeEnum.BIND.getId()) {
					policemanFlag = true;
				}
			}
		}

		if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.ARMY.getId()) {
			int action = 0;
			for (CardModel card : this.getTurnPlayer().getHands()) {
				if (card.getKind() == KindEnum.ACTION.getId()) {
					action++;
				}
			}
			if (action >= 2) {
				armyFlag = true;
			}
		}

		for (CardModel card : this.getTurnPlayer().getHands()) {
			if (card.getType() == TypeEnum.CRYSTAL.getId()) {
				selections.clear();
				selections.add(card);
				break;
			}
			if (engineerFlag) {
				if (card.getType() == TypeEnum.ITEM_REMOVE.getId()) {
					selections.add(card);
				}
			} else if (journalistFlag) {
				if (card.getType() == TypeEnum.ITEM_OPEN.getId()) {
					selections.add(card);
				}
			} else if (detectiveFlag) {
				if (card.getKind() == KindEnum.CHECK.getId()) {
					selections.add(card);
				}
			} else if (policemanFlag) {
				if (card.getType() == TypeEnum.BIND.getId()) {
					selections.add(card);
				}
			} else if (armyFlag) {
				if (card.getKind() == KindEnum.ACTION.getId()) {
					selections.add(card);
				}
			} else {
				selections.add(card);
			}
		}

		this.getTurnPlayer().setSelections(selections);
	}

	private boolean itemCheck(int checkTo) {
		if (this.getPlayerInfos().get(checkTo).hasClosedItems()) {
			StringBuilder line = new StringBuilder();
			line.append(this.getPlayerInfos().get(checkTo).getName());
			line.append("のクローズドカードは");
			for (CardModel card : this.getPlayerInfos().get(checkTo).getClosedMyItems()) {
				line.append(card.getTypeName());
				line.append(",");
			}
			for (CardModel card : this.getPlayerInfos().get(checkTo).getClosedAnotherItems()) {
				line.append(card.getTypeName());
				line.append(",");
			}
			this.getTurnPlayer().getInfoLines().add(line.toString());
			return true;
		}
		return false;
	}

	private void draw() {
		if (this.getSubPhase() == GameConstant.DRAW_SUB_PHASE) {
			if (this.getCardDeck().size() >= 2 && this.getCardDeck().get(0).isTimeCard() && this.getCardDeck().get(1).isTimeCard()) {
				for (int i = 0; i < 2; i++) {
					this.getCardDeck().remove(0);
					this.timeProgress();
				}
				this.nextTurn();
			} else if (this.getCardDeck().size() >= 2 && this.getCardDeck().get(1).isTimeCard()) {
				this.getTurnPlayer().getHands().add(this.getCardDeck().remove(0));
				this.getCardDeck().remove(0);
				this.timeProgress();
				this.useSelectionSet();
				this.setSubPhase(GameConstant.USE_SUB_PHASE);
			} else if (this.getCardDeck().get(0).isTimeCard()) {
				this.getCardDeck().remove(0);
				this.getTurnPlayer().getHands().add(this.getCardDeck().remove(0));
				this.timeProgress();
				this.useSelectionSet();
				this.setSubPhase(GameConstant.USE_SUB_PHASE);
			} else {
				List<CardModel> selections = new ArrayList<CardModel>();
				selections.add(this.getCardDeck().get(0));
				selections.add(this.getCardDeck().get(1));
				this.getTurnPlayer().setSelections(selections);
				this.setSubPhase(GameConstant.CHOOSE_SUB_PHASE);
			}
		}
	}

	private void predrawSkill(List<String> args) {
		if (this.getSubPhase() == GameConstant.DRAW_SUB_PHASE) {
			if (this.getTurnPlayer().getSkill()) {
				if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.DILETTANTE.getId()) {
					if (this.getTurnPlayer().getHands().size() > 3) {
						this.getTurnPlayer().setSelections(this.getTurnPlayer().getHands());
						this.setSubPhase(GameConstant.DILETTANTE_DROP_SUB_PHASE);
					} else {
						this.dilettanteDraw();;
					}
					this.getTurnPlayer().setSkill(false);
				} else if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.PSYCHIATRIST.getId()) {
					for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
						if (playerInfo.hasEvents()) {
							int eIndex = -1;
							for (int i = 0; i < playerInfo.getEvents().size(); i++) {
								if (playerInfo.getEvents().get(i).getId() == Integer.parseInt(args.get(0))) {
									eIndex = i;
								}
							}
							if (eIndex != -1) {
								this.getEventDeck().add(playerInfo.getEvents().remove(eIndex));
								Collections.shuffle(this.getEventDeck());
								this.getTurnPlayer().setSkill(false);
							}
						}
					}
				} else if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.DETECTIVE.getId()) {
					if (this.itemCheck(Integer.parseInt(args.get(0)))) {
						this.getTurnPlayer().setSkill(false);
					}
				}
			}
		}
	}

	private void dilettanteDrop(int index) {
		if (this.getSubPhase() == GameConstant.DILETTANTE_DROP_SUB_PHASE) {
			if (this.getTurnPlayer().getHands().size() > 3) {
				this.getTurnPlayer().getHands().remove(index);
			}

			if (this.getTurnPlayer().getHands().size() <= 3) {
				this.dilettanteDraw();
			}
		}
	}

	private void dilettanteDraw() {
		if (this.getCardDeck().size() >= 2 && this.getCardDeck().get(0).isTimeCard() && this.getCardDeck().get(1).isTimeCard()) {
			for (int i = 0; i < 2; i++) {
				this.getCardDeck().remove(0);
				this.timeProgress();
			}
			this.setSubPhase(GameConstant.DRAW_SUB_PHASE);
		} else if (this.getCardDeck().size() >= 2 && this.getCardDeck().get(1).isTimeCard()) {
			this.getTurnPlayer().getHands().add(this.getCardDeck().remove(0));
			this.getCardDeck().remove(0);
			this.timeProgress();
			this.setSubPhase(GameConstant.DRAW_SUB_PHASE);
		} else if (this.getCardDeck().get(0).isTimeCard()) {
			this.getCardDeck().remove(0);
			this.getTurnPlayer().getHands().add(this.getCardDeck().remove(0));
			this.timeProgress();
			this.setSubPhase(GameConstant.DRAW_SUB_PHASE);
		} else {
			List<CardModel> selections = new ArrayList<CardModel>();
			selections.add(this.getCardDeck().get(0));
			selections.add(this.getCardDeck().get(1));
			this.getTurnPlayer().setSelections(selections);
			this.setSubPhase(GameConstant.DILETTANTE_CHOSE_SUB_PHASE);
		}
	}

	private void dilettanteChoose(int index) {
		this.chooseCommon(index, true);
	}

	private void choose(int index) {
		this.chooseCommon(index, false);
	}

	private void chooseCommon(int index, boolean isSkill) {
		int checkSubPhase;
		int nextSubPhase;
		boolean selectionSet;
		if (isSkill) {
			checkSubPhase = GameConstant.DILETTANTE_CHOSE_SUB_PHASE;
			nextSubPhase = GameConstant.DRAW_SUB_PHASE;
			selectionSet = false;
		} else {
			checkSubPhase = GameConstant.CHOOSE_SUB_PHASE;
			nextSubPhase = GameConstant.USE_SUB_PHASE;
			selectionSet = true;
		}

		if (this.getSubPhase() == checkSubPhase) {
			this.getTurnPlayer().getHands().add(this.getCardDeck().remove(index));
			if (selectionSet) {
				this.useSelectionSet();
			}
			this.setSubPhase(nextSubPhase);
		}
	}

	private void use(List<String> args) {
		if (this.getSubPhase() == GameConstant.USE_SUB_PHASE) {
			int ui = -1;
			for (int hi = 0; hi < this.getTurnPlayer().getHands().size(); hi++) {
				if (this.getTurnPlayer().getHands().get(hi).getId() == this.getTurnPlayer().getSelections().get(Integer.parseInt(args.get(0))).getId()) {
					ui = hi;
					break;
				}
			}
			if (ui != -1) {
				CardModel useCard = this.getTurnPlayer().getHands().remove(ui);
				if (useCard.getKind() == KindEnum.CHECK.getId()) {
					if (useCard.getType() == TypeEnum.CHECK_DOWN.getId()) {
						boolean journalistSkill = false;
						int journalTo = -1;
						if (args.size() >= 4) {
							if (args.get(2).equals("1")
									&& this.getTurnPlayer().getPlayer().getId() == PlayerEnum.JOURNALIST.getId()
									&& this.getTurnPlayer().getSkill()) {
								journalistSkill = true;
								journalTo = Integer.parseInt(args.get(3));
							}
						}
						this.checkCommon(true, useCard.getValue(), Integer.parseInt(args.get(1)), journalistSkill, journalTo);
					} else if (useCard.getType() == TypeEnum.CHECK_UP.getId()) {
						boolean journalistSkill = false;
						int journalTo = -1;
						if (args.size() >= 4) {
							if (args.get(2).equals("1")
									&& this.getTurnPlayer().getPlayer().getId() == PlayerEnum.JOURNALIST.getId()
									&& this.getTurnPlayer().getSkill()) {
								journalistSkill = true;
								journalTo = Integer.parseInt(args.get(3));
							}
						}
						this.checkCommon(false, useCard.getValue(), Integer.parseInt(args.get(1)), journalistSkill, journalTo);
					} else if (useCard.getType() == TypeEnum.ITEM_CHECK.getId()) {
						this.itemCheck(Integer.parseInt(args.get(1)));
						this.nextTurn();
					}
				} else if (useCard.getKind() == KindEnum.ACTION.getId()) {
					boolean regulatable = false;
					for (int i = 0; i < this.getPlayerInfos().size(); i++) {
						if (i == this.getTurn()) {
							continue;
						}
						if (this.getPlayerInfos().get(i).getPlayer().getId() == PlayerEnum.POLICEMAN.getId()) {
							regulatable = this.getPlayerInfos().get(i).getSkill();
							break;
						}
					}

					args.set(0, String.valueOf(useCard.getId()));
					if (regulatable) {
						this.setSuspendArr(args);
						this.setSubPhase(GameConstant.REGULATE_SUB_PHASE);
					} else {
						this.useAction(args);
					}
				} else if (useCard.getKind() == KindEnum.ITEM.getId()) {
					if (this.getTurnPlayer().getPlayer().getId() == PlayerEnum.PSYCHIATRIST.getId()) {
						args.set(2, GameConstant.COMMAND_OK);
					}
					if (this.getTurn() == Integer.parseInt(args.get(1))) {
						if (args.get(2).equals(GameConstant.COMMAND_OK)) {
							this.getTurnPlayer().getClosedMyItems().add(useCard);
						} else {
							this.getTurnPlayer().getOpenMyItems().add(useCard);
						}
					} else {
						if (args.get(2).equals(GameConstant.COMMAND_OK)) {
							this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getClosedAnotherItems().add(useCard);
						} else {
							this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getOpenAnotherItems().add(useCard);
						}
					}
					this.nextTurn();
				}
			}
		}
	}

	private void checkCommon(boolean isDown, int value, int to, boolean journalistSkill, int JournalTo) {
		this.appendMes("狂気度調査:" + this.getTurnPlayer().getName() + "->" + this.getPlayerInfos().get(to).getName());
		int multiple = 1;
		if (!isDown) {
			multiple = -1;
		}
		StringBuilder info = new StringBuilder();
		info.append(this.getPlayerInfos().get(to).getName());
		info.append(":");
		info.append(this.getPlayerInfos().get(to).getPlayer().getName());
		info.append("の狂気度は");
		info.append(value);
		if (multiple * this.getPlayerInfos().get(to).checkSan() <= multiple * value) {
			this.appendMes("結果:手が上がります。");
			if (isDown) {
				info.append("以下");
			} else {
				info.append("以上");
			}
		} else {
			this.appendMes("結果:手が上がりません。");
			if (isDown) {
				info.append("より上");
			} else {
				info.append("より下");
			}
		}
		this.getTurnPlayer().getInfoLines().add(info.toString());
		if (journalistSkill) {
			this.getPlayerInfos().get(JournalTo).getInfoLines().add(info.toString());
			this.getTurnPlayer().setSkill(false);
		}
		this.nextTurn();
	}

	private void useAction(List<String> args) {
		int cardType = CardEnum.getById(Integer.parseInt(args.get(0))).getType();
		if (cardType == TypeEnum.ITEM_OPEN.getId()) {
			this.getPlayerInfos().get(Integer.parseInt(args.get(1))).randomOpen();
			this.nextTurn();;
		} else if (cardType == TypeEnum.ITEM_MOVE.getId()) {
			CardModel move;
			if (args.get(2).equals(GameConstant.CARD_MOVE_MY_OPEN)) {
				move = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getOpenMyItems().remove(Integer.parseInt(args.get(3)));
			} else if (args.get(2).equals(GameConstant.CARD_MOVE_MY_CLOSED)) {
				move = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getClosedMyItems().remove(Integer.parseInt(args.get(3)));
			} else if (args.get(2).equals(GameConstant.CARD_MOVE_ANOTHER_OPEN)) {
				move = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getOpenAnotherItems().remove(Integer.parseInt(args.get(3)));
			} else {
				move = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getClosedAnotherItems().remove(Integer.parseInt(args.get(3)));
			}

			if (args.get(2).equals(GameConstant.CARD_MOVE_MY_OPEN)
					|| args.get(2).equals(GameConstant.CARD_MOVE_ANOTHER_OPEN)) {
				this.getPlayerInfos().get(Integer.parseInt(args.get(4))).getOpenAnotherItems().add(move);
			} else {
				this.getPlayerInfos().get(Integer.parseInt(args.get(4))).getClosedAnotherItems().add(move);
			}

			this.nextTurn();
		} else if (cardType == TypeEnum.ITEM_REMOVE.getId()) {
			boolean repairable = false;
			for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
				if (playerInfo.getPlayer().getId() == PlayerEnum.ENGINEER.getId() && playerInfo.getSkill()) {
					repairable = true;
				}
			}

			CardModel remove;
			if (args.get(2).equals(GameConstant.CARD_MOVE_MY_OPEN)) {
				remove = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getOpenMyItems().remove(Integer.parseInt(args.get(3)));
			} else if (args.get(2).equals(GameConstant.CARD_MOVE_MY_CLOSED)) {
				remove = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getClosedMyItems().remove(Integer.parseInt(args.get(3)));
			} else if (args.get(2).equals(GameConstant.CARD_MOVE_ANOTHER_OPEN)) {
				remove = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getOpenAnotherItems().remove(Integer.parseInt(args.get(3)));
			} else {
				remove = this.getPlayerInfos().get(Integer.parseInt(args.get(1))).getClosedAnotherItems().remove(Integer.parseInt(args.get(3)));
			}

			if (repairable) {
				this.setRemoveCard(remove);
				this.setSubPhase(GameConstant.REPAIR_SUB_PHASE);
			} else {
				this.nextTurn();
			}
		} else if (cardType == TypeEnum.HONEY.getId()) {
			this.getPlayerInfos().get(Integer.parseInt(args.get(1))).toggleSkill();
			this.nextTurn();
		}
	}

	private void repair(String name, String useSkill) {
		if (this.getSubPhase() == GameConstant.REPAIR_SUB_PHASE) {
			int ei = -1;
			for (int i = 0; i < this.getPlayerInfos().size(); i++) {
				if (this.getPlayerInfos().get(i).getName().equals(name)
						&& this.getPlayerInfos().get(i).getPlayer().getId() == PlayerEnum.ENGINEER.getId()) {
					ei = i;
				}
			}

			if (ei != -1) {
				if (useSkill.equals(GameConstant.COMMAND_OK) && this.getPlayerInfos().get(ei).getSkill()) {
					this.getPlayerInfos().get(ei).getHands().add(this.getRemoveCard());
					this.getPlayerInfos().get(ei).setSkill(false);
				}
				this.nextTurn();
			}
		}
	}

	private void regulate(String name, String useSkill) {
		if (this.getSubPhase() == GameConstant.REGULATE_SUB_PHASE) {
			int pi = -1;
			for (int i = 0; i < this.getPlayerInfos().size(); i++) {
				if (this.getPlayerInfos().get(i).getName().equals(name)
						&& this.getPlayerInfos().get(i).getPlayer().getId() == PlayerEnum.POLICEMAN.getId()) {
					pi = i;
				}
			}

			if (pi != -1) {
				if (useSkill.equals(GameConstant.COMMAND_OK) && this.getPlayerInfos().get(pi).getSkill()) {
					this.getPlayerInfos().get(pi).setSkill(false);
					this.nextTurn();
				} else {
					this.useAction(this.getSuspendArr());
				}
			}
		}
	}

	private List<CardModel> restDeck() {
		List<CardModel> deck = GameUtil.createCardDeck();
		List<CardModel> usedCards = new ArrayList<CardModel>();

		for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
			usedCards.addAll(playerInfo.getOpenMyItems());
			usedCards.addAll(playerInfo.getClosedMyItems());
			usedCards.addAll(playerInfo.getOpenAnotherItems());
			usedCards.addAll(playerInfo.getClosedAnotherItems());
		}

		deck.removeAll(usedCards);

		return deck;
	}

	private boolean battleSkillExists() {
		for (PlayerInfoModel playerInfo : this.getPlayerInfos()) {
			boolean isPlayerSide = true;
			for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
				if (cthulhuSide.getPlayer().getId() == playerInfo.getPlayer().getId()) {
					isPlayerSide = false;
					break;
				}
			}
			if (isPlayerSide
					&& Arrays.asList(GameConstant.BATTLE_SKILL_HOLDER_IDS).contains(Integer.valueOf(playerInfo.getPlayer().getId()))
					&& playerInfo.getSkill()) {
				return true;
			}
		}
		return false;
	}

	private boolean isCthulhuSide(String name) {
		for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
			if (cthulhuSide.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	private void battle(int professorSkillTo) {
		int playerBattle = 0;
		int cthulhuBattle = 0;

		int bookMulti = 0;
		if (this.getCthulhu().getId() == CthulhuEnum.CTHULHU.getId()) {
			bookMulti = 15;
		} else if (this.getCthulhu().getId() == CthulhuEnum.DAGON.getId()) {
			bookMulti = 10;
		}

		List<PlayerInfoModel> playerNames = new ArrayList<PlayerInfoModel>();
		List<PlayerInfoModel> cthulhuNames = new ArrayList<PlayerInfoModel>();
		for (int i = 0; i < this.getPlayerInfos().size(); i++) {
			PlayerInfoModel playerInfo = this.getPlayerInfos().get(i);
			boolean isCthulhuSide = false;
			for (PlayerInfoModel cthulhuSide : this.getCthulhuSide()) {
				if (playerInfo.getPlayer().getId() == cthulhuSide.getPlayer().getId()) {
					isCthulhuSide = true;
				}
			}
			if (isCthulhuSide) {
				cthulhuNames.add(playerInfo);

				cthulhuBattle += playerInfo.totalSan();

				for (CardModel item : playerInfo.getOpenMyItems()) {
					if (Arrays.asList(GameConstant.BOOK_IDS).contains(Integer.valueOf(item.getType()))) {
						cthulhuBattle += bookMulti;
					} else if (item.getType() == TypeEnum.SIGN.getId()) {
						int san = playerInfo.totalSan();
						int signValue;
						if (san <= 0) {
							signValue = 25;
						} else if (san <= 20) {
							signValue = 15;
						} else if (san <= 40) {
							signValue = 10;
						} else if (san <= 60) {
							signValue = 5;
						} else if (san <= 80) {
							signValue = 0;
						} else if (san <= 100) {
							signValue = -10;
						} else {
							signValue = -20;
						}
						cthulhuBattle += signValue;
					}
				}
			} else {
				playerNames.add(playerInfo);

				playerBattle += playerInfo.getBattle(professorSkillTo == i);
			}
		}

		cthulhuBattle += this.getCthulhu().getBattle();

		for (Integer fate : this.cthulhuFate) {
			cthulhuBattle += fate.intValue();
		}

		if (this.getPlayerInfos().size() == 4) {
			cthulhuBattle -= 20;
		}

		StringBuilder resultLine = new StringBuilder();
		resultLine.append("プレイヤー側戦闘力:");
		resultLine.append(playerBattle);
		resultLine.append("邪神側戦闘力:");
		resultLine.append(cthulhuBattle);

		if (playerBattle >= cthulhuBattle) {
			resultLine.append("プレイヤー側:");
			this.setWinners(playerNames);
			this.setLosers(cthulhuNames);
		} else {
			resultLine.append("邪神側:");
			this.setWinners(cthulhuNames);
			this.setLosers(playerNames);
		}
		boolean first = true;
		for (PlayerInfoModel winner : this.getWinners()) {
			if (!first) {
				resultLine.append(",");
			}
			resultLine.append(winner.getName());
			first = false;
		}
		resultLine.append("の勝利です");

		this.appendMes(resultLine.toString());

		this.setPhase(GameConstant.AFTER_PHASE);
	}
}
