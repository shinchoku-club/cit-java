package com.ecb.game.cit.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ecb.game.cit.code.CardEnum;
import com.ecb.game.cit.code.CthulhuEnum;
import com.ecb.game.cit.code.EventEnum;
import com.ecb.game.cit.code.PlayerEnum;
import com.ecb.game.cit.code.TypeEnum;
import com.ecb.game.cit.model.CardModel;
import com.ecb.game.cit.model.CthulhuModel;
import com.ecb.game.cit.model.EventModel;
import com.ecb.game.cit.model.PlayerModel;

public class GameUtil {
	public static List<CardModel> createCardDeck() {
		List<CardModel> cardDeck = new ArrayList<CardModel>();
		for (CardEnum card : CardEnum.values()) {
			CardModel dto = new CardModel();
			dto.setId(card.getId());
			dto.setFate(card.getFate());
			dto.setKind(card.getKind());
			dto.setType(card.getType());
			dto.setValue(card.getValue());
			dto.setDetail(MessageFormat.format(TypeEnum.getById(card.getType()).getTemplate(), String.valueOf(card.getValue())));

			cardDeck.add(dto);
		}

		Collections.shuffle(cardDeck);

		return cardDeck;
	}

	public static List<PlayerModel> createPlayerDeck() {
		List<PlayerModel> playerDeck = new ArrayList<PlayerModel>();
		for (PlayerEnum player : PlayerEnum.values()) {
			PlayerModel dto = new PlayerModel();
			dto.setId(player.getId());
			dto.setName(player.getName());
			dto.setBattle(player.getBattle());
			dto.setSan(player.getSan());
			dto.setChara(player.getChara());
			dto.setCharaDetail(player.getCharaDetail());
			dto.setSkill(player.getSkill());
			dto.setSkillDetail(player.getSkillDetail());

			playerDeck.add(dto);
		}

		Collections.shuffle(playerDeck);

		return playerDeck;
	}

	public static List<Integer> createSanDeck() {
		List<Integer> sanDeck = new ArrayList<Integer>();
		for (int i = 0; i <= 100; i += 10) {
			sanDeck.add(Integer.valueOf(i));
		}

		Collections.shuffle(sanDeck);

		return sanDeck;
	}

	public static List<CardModel> timePutShuffle(List<CardModel> deck) {
		Collections.shuffle(deck);
		List<CardModel> top = new ArrayList<CardModel>();
		List<CardModel> middle = new ArrayList<CardModel>();
		List<CardModel> bottom = new ArrayList<CardModel>();

		int flg = 0;
		for (CardModel card : deck) {
			if (flg == 0) {
				top.add(card);
			} else if (flg == 1) {
				middle.add(card);
			} else {
				bottom.add(card);
			}
			flg = (flg + 1) % 3;
		}

		top.add(CardModel.getTimeCard());
		middle.add(CardModel.getTimeCard());
		bottom.add(CardModel.getTimeCard());

		Collections.shuffle(top);
		Collections.shuffle(middle);
		Collections.shuffle(bottom);

		deck.clear();
		deck.addAll(top);
		deck.addAll(middle);
		deck.addAll(bottom);

		return deck;
	}

	public static List<EventModel> createEventDeck() {
		List<EventModel> eventDeck = new ArrayList<EventModel>();
		for (EventEnum event : EventEnum.values()) {
			EventModel dto = new EventModel();
			dto.setId(event.getId());
			dto.setName(event.getName());
			dto.setSan(event.getSan());
			dto.setDetail(event.getDetail());

			eventDeck.add(dto);
		}

		Collections.shuffle(eventDeck);

		return eventDeck;
	}

	public static CthulhuModel adventCthulhu(int advent) {
		CthulhuModel cthulhu = new CthulhuModel();
		CthulhuEnum data;
		if (advent >= 221) {
			data = CthulhuEnum.AZATHOTH;
		} else if (advent >= 171) {
			if (advent % 2 == 0) {
				data = CthulhuEnum.NYARLATHOTEP;
			} else {
				data = CthulhuEnum.CTHULHU;
			}
		} else if (advent >= 121) {
			if (advent % 2 == 0) {
				data = CthulhuEnum.SHUB_NIGGURATH;
			} else {
				data = CthulhuEnum.DAGON;
			}
		} else {
			if (advent % 2 == 0) {
				data = CthulhuEnum.THE_HOUNDS_OF_TINDALOS;
			} else {
				data = CthulhuEnum.SHOGGOTH;
			}
		}
		cthulhu.setId(data.getId());
		cthulhu.setName(data.getName());
		cthulhu.setBattle(data.getBattle());
		cthulhu.setSkill(data.getSkill());
		cthulhu.setSkillDetail(data.getSkillDetail());

		return cthulhu;
	}
}
