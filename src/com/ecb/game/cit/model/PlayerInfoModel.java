package com.ecb.game.cit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ecb.game.cit.code.PlayerEnum;

public class PlayerInfoModel {
	private String name;
	private PlayerModel player;
	private int san;
	private List<CardModel> openMyItems;
	private List<CardModel> closedMyItems;
	private List<CardModel> openAnotherItems;
	private List<CardModel> closedAnotherItems;
	private List<EventModel> events;
	private boolean skill;
	private List<CardModel> hands;
	private int fate;
	private List<CardModel> selections;
	private List<String> infoLines;
	private boolean calculated;

	/**
	 * コンストラクタ
	 */
	public PlayerInfoModel(String name) {
		this.name = name;
		this.player = null;
		this.san = 0;
		this.openMyItems = new ArrayList<CardModel>();
		this.closedMyItems = new ArrayList<CardModel>();
		this.openAnotherItems = new ArrayList<CardModel>();
		this.closedAnotherItems = new ArrayList<CardModel>();
		this.events = new ArrayList<EventModel>();
		this.skill = true;
		this.hands = new ArrayList<CardModel>();
		this.fate = 0;
		this.selections = new ArrayList<CardModel>();
		this.infoLines = new ArrayList<String>();
		this.calculated = false;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PlayerModel getPlayer() {
		return player;
	}
	public void setPlayer(PlayerModel player) {
		this.player = player;
	}
	public int getSan() {
		return san;
	}
	public void setSan(int san) {
		this.san = san;
	}
	public List<CardModel> getOpenMyItems() {
		return openMyItems;
	}
	public void setOpenMyItems(List<CardModel> openMyItems) {
		this.openMyItems = openMyItems;
	}
	public List<CardModel> getClosedMyItems() {
		return closedMyItems;
	}
	public void setClosedMyItems(List<CardModel> closedMyItems) {
		this.closedMyItems = closedMyItems;
	}
	public List<CardModel> getOpenAnotherItems() {
		return openAnotherItems;
	}
	public void setOpenAnotherItems(List<CardModel> openAnotherItems) {
		this.openAnotherItems = openAnotherItems;
	}
	public List<CardModel> getClosedAnotherItems() {
		return closedAnotherItems;
	}
	public void setClosedAnotherItems(List<CardModel> closedAnotherItems) {
		this.closedAnotherItems = closedAnotherItems;
	}
	public List<EventModel> getEvents() {
		return events;
	}
	public void setEvents(List<EventModel> events) {
		this.events = events;
	}
	public boolean getSkill() {
		return skill;
	}
	public void setSkill(boolean skill) {
		this.skill = skill;
	}
	public List<CardModel> getHands() {
		return hands;
	}
	public void setHands(List<CardModel> hands) {
		this.hands = hands;
	}
	public int getFate() {
		return fate;
	}
	public void setFate(int fate) {
		this.fate = fate;
	}
	public List<CardModel> getSelections() {
		return selections;
	}
	public void setSelections(List<CardModel> selections) {
		this.selections = selections;
	}
	public List<String> getInfoLines() {
		return infoLines;
	}
	public void setInfoLines(List<String> infoLines) {
		this.infoLines = infoLines;
	}
	public boolean getCalculated() {
		return calculated;
	}
	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}

	public int checkSan() {
		return (this.getSan() + this.getPlayer().getSan());
	}

	public int totalSan() {
		int san = this.checkSan();
		boolean bookDouble = this.getPlayer().getId() == PlayerEnum.PROFESSOR.getId();
		boolean eventDouble = this.getPlayer().getId() == PlayerEnum.DILETTANTE.getId();
		List<CardModel> allCards = new ArrayList<CardModel>();

		allCards.addAll(this.getOpenMyItems());
		allCards.addAll(this.getOpenAnotherItems());
		allCards.addAll(this.getClosedMyItems());
		allCards.addAll(this.getClosedAnotherItems());

		for (CardModel card : allCards) {
			if (card.getType() == 7) {
				san -= 5;
			} else if (card.getType() == 8) {
				san -= 10;
			} else if (card.getType() == 14) {
				san += 5;
				if (bookDouble) {
					san += 5;
				}
			} else if (card.getType() == 15) {
				san += 7;
				if (bookDouble) {
					san += 7;
				}
			} else if (card.getType() == 16) {
				san += 5;
			} else if (card.getType() == 17) {
				san += 10;
				if (bookDouble) {
					san += 10;
				}
			} else if (card.getType() == 21) {
				san += 20;
			}
		}

		for (EventModel event : this.getEvents()) {
			san += event.getSan();
			if (eventDouble) {
				san += event.getSan();
			}
		}

		return san;
	}

	public int calcAdvent() {
		int san = this.totalSan();
		boolean bookDouble = this.getPlayer().getId() == PlayerEnum.PROFESSOR.getId();
		List<CardModel> allCards = new ArrayList<CardModel>();

		allCards.addAll(this.getOpenMyItems());
		allCards.addAll(this.getOpenAnotherItems());
		allCards.addAll(this.getClosedMyItems());
		allCards.addAll(this.getClosedAnotherItems());


		for (CardModel card : allCards) {
			if (card.getType() == 13) {
				san -= 10;
			} else if (card.getType() == 14) {
				san += 10;
				if (bookDouble) {
					san += 10;
				}
			} else if (card.getType() == 15) {
				san += 14;
				if (bookDouble) {
					san += 14;
				}
			} else if (card.getType() == 16) {
				san += 20;
			} else if (card.getType() == 17) {
				san += 20;
				if (bookDouble) {
					san += 20;
				}
			} else if (card.getType() == 21) {
				san += 40;
			}
		}

		return san;
	}

	public boolean hasItems() {
		return (this.getOpenMyItems().size() + this.getClosedMyItems().size() + this.getOpenAnotherItems().size() + this.getClosedAnotherItems().size() > 0);
	}

	public boolean hasClosedItems() {
		return (this.getClosedMyItems().size() + this.getClosedAnotherItems().size() > 0);
	}

	public boolean hasEvents() {
		return this.getEvents().size() > 0;
	}

	public void toggleSkill() {
		this.setSkill(!this.getSkill());
	}

	public int getBattle(boolean bookDouble) {
		if (this.getPlayer().getId() == PlayerEnum.PROFESSOR.getId() && this.getSkill()) {
			bookDouble = true;
		}
		boolean fireDouble = (this.getPlayer().getId() == PlayerEnum.ARMY.getId() && this.getSkill());
		boolean signDouble = (this.getPlayer().getId() == PlayerEnum.CHURCHMAN.getId() && this.getSkill());
		List<CardModel> allCards = new ArrayList<CardModel>();

		allCards.addAll(this.getOpenMyItems());
		allCards.addAll(this.getOpenAnotherItems());
		allCards.addAll(this.getClosedMyItems());
		allCards.addAll(this.getClosedAnotherItems());

		int bind = 0;
		int weapon = 0;
		int book = 0;
		int sign = 0;
		int guard = 0;
		int crystal = 0;

		for (CardModel card : allCards) {
			if (card.getType() == 9 && weapon < 5) {
				weapon = 5;
			} else if (card.getType() == 10 && weapon < 10) {
				weapon = 10;
			} else if (card.getType() == 11 && weapon < 15) {
				weapon = 15;
			} else if (card.getType() == 12 && weapon < 20) {
				weapon = 20;
			} else if (card.getType() == 13) {
				bind -= 10;
			} else if (card.getType() == 14) {
				book += 10;
			} else if (card.getType() == 15) {
				book += 14;
			} else if (card.getType() == 17) {
				book += 20;
			} else if (card.getType() == 18) {
				int san = this.totalSan();
				int signValue = -20;
				if (san < 0) {
					signValue = 25;
				} else if (san < 20) {
					signValue = 15;
				} else if (san < 40) {
					signValue = 10;
				} else if (san < 60) {
					signValue = 5;
				} else if (san < 80) {
					signValue = 0;
				} else if (san < 100) {
					signValue = -10;
				}
				sign += signValue;
			} else if (card.getType() == 19 && guard < 5) {
				guard = 5;
			} else if (card.getType() == 20 && guard < 10) {
				guard = 10;
			} else if (card.getType() == 21) {
				crystal += 20;
			}
		}

		if (fireDouble && weapon >= 10) {
			weapon *= 2;
		}
		if (signDouble) {
			sign *= 2;
		}
		if (bookDouble) {
			book *= 2;
		}

		return this.getPlayer().getBattle() + bind + weapon + book + sign + guard + crystal + this.getFate();
	}

	public void randomOpen() {
		if (this.hasClosedItems()) {
			List<CardModel> allCards = new ArrayList<CardModel>();

			allCards.addAll(this.getClosedMyItems());
			allCards.addAll(this.getClosedAnotherItems());

			Random rnd = new Random();
			int openIndex = rnd.nextInt(allCards.size());

			int openId = allCards.get(openIndex).getId();
			int index = -1;
			for (int i = 0; i < this.getClosedMyItems().size(); i++) {
				if (this.getClosedMyItems().get(i).getId() == openId) {
					index = i;
				}
			}
			if (index != -1) {
				this.getOpenMyItems().add(this.getClosedMyItems().remove(index));
			}
			index = -1;
			for (int i = 0; i < this.getClosedAnotherItems().size(); i++) {
				if (this.getClosedAnotherItems().get(i).getId() == openId) {
					index = i;
				}
			}
			if (index != -1) {
				this.getOpenAnotherItems().add(this.getClosedAnotherItems().remove(index));
			}
		}
	}

	@SuppressWarnings(value = { "unused" })
	public PlayerInfoModel getView(String name) {
		PlayerInfoModel view = new PlayerInfoModel(this.getName());
		view.player = this.getPlayer();
		view.openMyItems = this.getOpenMyItems();
		view.openAnotherItems = this.getOpenAnotherItems();
		view.events = this.getEvents();
		view.skill = this.getSkill();
		view.fate = this.getFate();
		view.calculated = this.getCalculated();

		if (this.getName().equals(name)) {
			view.san = this.getSan();
			view.closedMyItems = this.getClosedMyItems();
			view.closedAnotherItems = new ArrayList<CardModel>();
			for (CardModel card : this.getClosedAnotherItems()) {
				view.closedAnotherItems.add(CardModel.getUnknownCard());
			}
			view.hands = this.getHands();
			view.selections = this.getSelections();
			view.infoLines = this.getInfoLines();
		} else {
			view.san = -10;
			view.closedMyItems = new ArrayList<CardModel>();
			for (CardModel card : this.getClosedMyItems()) {
				view.closedMyItems.add(CardModel.getUnknownCard());
			}
			view.closedAnotherItems = new ArrayList<CardModel>();
			for (CardModel card : this.getClosedAnotherItems()) {
				view.closedAnotherItems.add(CardModel.getUnknownCard());
			}
			view.hands = new ArrayList<CardModel>();
			for (CardModel card : this.getHands()) {
				view.hands.add(CardModel.getUnknownCard());
			}
			view.selections = new ArrayList<CardModel>();
			view.infoLines = new ArrayList<String>();
		}


		return view;
	}
}
