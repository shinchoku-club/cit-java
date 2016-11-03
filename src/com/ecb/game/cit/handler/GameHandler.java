package com.ecb.game.cit.handler;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ecb.game.cit.model.CthulhuInThereModel;
import com.ecb.game.cit.util.GameConstant;
import com.ecb.game.cit.util.StringUtil;
import com.ecb.game.cit.web.socket.SessionInfo;

public class GameHandler extends TextWebSocketHandler {
	Logger log = Logger.getLogger(GameHandler.class.getName());
	/** セッション一覧 */
	private Map<String, SessionInfo> sessionsMap = new ConcurrentHashMap<String, SessionInfo>();
	/** ゲームエンティティ */
	private CthulhuInThereModel game = new CthulhuInThereModel();
	/** 管理者ID */
	private String grantId = null;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.trace("connection started");
		this.sessionsMap.put(session.getId(), new SessionInfo(session));

		this.selfMes(session, this.nameListMes());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.trace("connection closed");
		this.sessionsMap.remove(session.getId());
		if (session.getId().equals(this.grantId)) {
			this.grantId = null;
		}

		this.broadcastMes(this.nameListMes());
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.trace(message);
		String mes = message.getPayload();
		log.debug(mes);

		String head = mes.substring(0, 1);
		String body = StringUtil.htmlSpecialChars(mes.substring(1));

		boolean refresh = false;
		String messageTo = StringUtil.EMPTY;
		boolean errorMes = false;

		StringBuilder sb = new StringBuilder();
		if (GameConstant.NAME_HEADER.equals(head)) {
			boolean nameDuplication = false;
			String[] strArr = body.split(GameConstant.SEPARATOR);

			if (strArr.length >= 2 && strArr[1].equals(GameConstant.PASSWORD)) {
				for (Entry<String, SessionInfo> entry : this.sessionsMap.entrySet()) {
					if (StringUtil.isNotEmpty(entry.getValue().getName()) && entry.getValue().getName().equals(strArr[0])) {
						nameDuplication = true;
						break;
					}
				}
				if (!nameDuplication) {
					this.sessionsMap.get(session.getId()).setName(strArr[0]);
					StringBuilder ok = new StringBuilder();
					ok.append(GameConstant.NAME_OK_HEADER);
					ok.append(strArr[0]);
					this.selfMes(session, ok.toString());

					this.broadcastMes(this.nameListMes());
				}
				refresh = true;
			}
		} else if (GameConstant.MES_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				sb.append(this.sessionsMap.get(session.getId()).getName());
			} else {
				sb.append(GameConstant.NAME_ANONYMOUS);
			}
			sb.append(":");

			sb.append(body);

			if (body.substring(0, 1).equals(GameConstant.META_MESSAGE_HEADER)) {
				String metaBody = body.substring(1);
				if (metaBody.equals(GameConstant.KEY_GRANT)) {
					if (this.grantId == null) {
						grantId = session.getId();
						sb.append(GameConstant.MESSAGE_GRANT);
						this.broadcastMes(this.nameListMes());
					}
				} else if (metaBody.equals(GameConstant.KEY_REVOKE)) {
					if (session.getId().equals(this.grantId)) {
						this.grantId = null;
						sb.append(GameConstant.MESSAGE_REVOKE);
						this.broadcastMes(this.nameListMes());
					}
				} else if (metaBody.equals(GameConstant.KEY_RESET)) {
					if (session.getId().equals(this.grantId)) {
						game = new CthulhuInThereModel();
						sb.append(GameConstant.MESSAGE_RESET_GAME);
						refresh = true;
					}
				}
			}
		} else if (GameConstant.PRIVATE_MES_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);
			String mesName;
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				mesName = this.sessionsMap.get(session.getId()).getName();
			} else {
				mesName = GameConstant.NAME_ANONYMOUS;
			}

			String[] strArr = body.split(GameConstant.SEPARATOR);

			if (strArr.length >= 2) {
				messageTo = strArr[0];
				sb.append(MessageFormat.format(GameConstant.FORMAT_PRIVATE_MESSAGE, mesName, strArr[1]));
			} else {
				sb.append(GameConstant.ERROR_PRIVATE_MESSAGE);
				errorMes = true;
			}
		} else if (GameConstant.JOIN_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				String name = this.sessionsMap.get(session.getId()).getName();

				if (game.join(name)) {
					sb.append(MessageFormat.format(GameConstant.FORMAT_SE, GameConstant.JOIN_SE));

					sb.append(MessageFormat.format(GameConstant.FORMAT_JOIN_SUCCESS, name));
				} else {
					sb.append(MessageFormat.format(GameConstant.FORMAT_JOIN_FAILURE, name));
				}
			} else {
				sb.append(GameConstant.ERROR_INPUT_NAME);
				errorMes = true;
			}
			refresh = true;
		} else if (GameConstant.EXIT_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				String name = this.sessionsMap.get(session.getId()).getName();

				if (game.exit(name)) {
					sb.append(MessageFormat.format(GameConstant.FORMAT_EXIT_SUCCESS, name));
				} else {
					sb.append(MessageFormat.format(GameConstant.FORMAT_EXIT_FAILURE, name));
				}
			} else {
				sb.append(GameConstant.ERROR_INPUT_NAME);
				errorMes = true;
			}
			refresh = true;
		} else if (GameConstant.START_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);

			game.startGame();

			sb.append(MessageFormat.format(GameConstant.FORMAT_SE, GameConstant.OPENING_SE));

			sb.append(GameConstant.MESSAGE_START_GAME);

			refresh = true;
		} else if (GameConstant.ACTION_HEADER.equals(head)) {
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				String name = this.sessionsMap.get(session.getId()).getName();

				StringBuilder action = new StringBuilder();

				action.append(name);
				action.append(GameConstant.SEPARATOR);
				action.append(body);

				game.action(action.toString());
				if (StringUtil.isNotEmpty(game.getMes())) {
					sb.append(GameConstant.MES_HEADER);
					if (game.getSe() != null) {
						sb.append(MessageFormat.format(GameConstant.FORMAT_SE, game.getSe()));
					}
					sb.append(game.getMes());
				}
			} else {
				sb.append(GameConstant.MES_HEADER);
				sb.append(GameConstant.ERROR_INPUT_NAME);
				errorMes = true;
			}
			refresh = true;
		} else if (GameConstant.CALCULATE_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				game.calc(this.sessionsMap.get(session.getId()).getName());
				if (StringUtil.isNotEmpty(game.getMes())) {
					sb.append(game.getMes());
				}
			} else {
				sb.append(GameConstant.ERROR_INPUT_NAME);
				errorMes = true;
			}
			refresh = true;
		} else if (GameConstant.BATTLE_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				String name = this.sessionsMap.get(session.getId()).getName();

				StringBuilder action = new StringBuilder();

				action.append(name);
				action.append(GameConstant.SEPARATOR);
				action.append(body);

				game.battleAction(action.toString());

				if (game.getSe() != null) {
					sb.append(MessageFormat.format(GameConstant.FORMAT_SE, game.getSe()));
				}
				if (StringUtil.isNotEmpty(game.getMes())) {
					sb.append(game.getMes());
				}
			} else {
				sb.append(GameConstant.ERROR_INPUT_NAME);
				errorMes = true;
			}
			refresh = true;
		} else if (GameConstant.BELL_HEADER.equals(head)) {
			this.broadcastMes(GameConstant.BELL_HEADER);
		} else if (GameConstant.DICE_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);

			sb.append(MessageFormat.format(GameConstant.FORMAT_SE, GameConstant.DICE_SE));

			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				sb.append(this.sessionsMap.get(session.getId()).getName());
			} else {
				sb.append(GameConstant.NAME_ANONYMOUS);
			}
			sb.append(":");
			Random rand = new Random();
			int dice = rand.nextInt(100) + 1;
			sb.append(MessageFormat.format(GameConstant.FORMAT_DICE, dice));
		} else if (GameConstant.RESET_HEADER.equals(head)) {
			sb.append(GameConstant.MES_HEADER);
			sb.append(GameConstant.MESSAGE_RESET_GAME);

			game.reset();
			refresh = true;
		} else if (GameConstant.REFRESH_HEADER.equals(head)) {
			if (StringUtil.isNotEmpty(this.sessionsMap.get(session.getId()).getName())) {
				this.selfMes(session, this.refreshMes(this.sessionsMap.get(session.getId()).getName()));
				this.selfMes(session, this.nameListMes());
			}
		} else {
			sb.append(GameConstant.ERROR_HEADER);
		}

		if (sb.length() > 0) {
			if (errorMes) {
				// エラーメッセージ
				this.selfMes(session, sb.toString());
			} else {
				if (StringUtil.isNotEmpty(messageTo)) {
					// プライベートメッセージ
					this.privateMes(messageTo, sb.toString());
					this.selfMes(session, sb.toString());
				} else {
					// ブロードキャストメッセージ
					this.broadcastMes(sb.toString());
				}
			}
		}
		if (refresh) {
			// リフレッシュ
			for (Entry<String, SessionInfo> entry : this.sessionsMap.entrySet()) {
				TextMessage broadcastMessage = new TextMessage(this.refreshMes(entry.getValue().getName()));
				entry.getValue().getSession().sendMessage(broadcastMessage);
			}
		}
	}

	private String refreshMes(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(GameConstant.REFRESH_HEADER);
		sb.append(game.getView(name));
		return sb.toString();
	}

	private String nameListMes() {
		StringBuilder sb = new StringBuilder();
		sb.append(GameConstant.LIST_HEADER);
		boolean first = true;
		for (Entry<String, SessionInfo> entry : this.sessionsMap.entrySet()) {
			if (StringUtil.isNotEmpty(entry.getValue().getName())) {
				if (!first) {
					sb.append(GameConstant.SEPARATOR);
				}
				if (entry.getKey().equals(this.grantId)) {
					sb.append(GameConstant.MARK_GRANT);
				}
				sb.append(entry.getValue().getName());
				first = false;
			}
		}

		return sb.toString();
	}

	private void broadcastMes(String mes) throws Exception {
		TextMessage broadcastMessage = new TextMessage(mes);
		for (Entry<String, SessionInfo> entry : this.sessionsMap.entrySet()) {
			entry.getValue().getSession().sendMessage(broadcastMessage);
		}
	}

	private void privateMes(String name, String mes) throws Exception {
		TextMessage privateMessage = new TextMessage(mes);
		for (Entry<String, SessionInfo> entry : this.sessionsMap.entrySet()) {
			if (entry.getValue().getName().equals(name)) {
				entry.getValue().getSession().sendMessage(privateMessage);
			}
		}
	}

	private void selfMes(WebSocketSession session, String mes) throws Exception {
		TextMessage errorMessage = new TextMessage(mes);
		for (Entry<String, SessionInfo> entry : this.sessionsMap.entrySet()) {
			if (entry.getKey().equals(session.getId())) {
				entry.getValue().getSession().sendMessage(errorMessage);
			}
		}
	}
}
