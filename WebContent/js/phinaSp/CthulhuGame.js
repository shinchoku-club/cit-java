var INFO_SIZE_X_BASE = 32;
var INFO_SIZE_Y_BASE = 32;
var INFO_SCALE = 0.8;
var INFO_SIZE_X = INFO_SIZE_X_BASE * INFO_SCALE;
var INFO_SIZE_Y = INFO_SIZE_Y_BASE * INFO_SCALE;
var GAME_IMAGE = {
	image: {
		'info': "./img/info.png",
	}
};

phina.define('GameDefaultScene', {
	superClass: 'DisplayScene',
	init: function(params) {
		this.superInit(params);
		this.detailGridX = Grid({
			width: this.width,
			columns: 36,
		});
		this.detailGridY = Grid({
			width: this.width,
			columns: 36,
		});

		this.bottomGridY = Grid({
			width: this.height - this.width,
			columns: 10,
			offset: this.width,
		});
	},

	refreshView: function(data) {
	},

	updateLog: function(log) {
	},

	sendAction: function(kind, args) {
		var action = kind;
		if (args !== "") {
			action += Cthulhu.SEPARATOR + args;
		}
		this.app.send(Cthulhu.ACTION_HEADER + action);
	},

	sendBattle: function(kind, args) {
		var action = kind;
		if (args !== "") {
			action += Cthulhu.SEPARATOR + args;
		}
		this.app.send(Cthulhu.BATTLE_HEADER + action);
	},
});

phina.define('TitleScene', {
	superClass: 'GameDefaultScene',
	init: function(params) {
		this.superInit(params);

		this.label = Label("読み込み中")
			.addChildTo(this)
			.setPosition(this.detailGridX.center(), this.detailGridY.center());
	},
});

phina.define('MainScene', {
	superClass: 'GameDefaultScene',
	init: function(params) {
		this.superInit(params);
		// 背景色を指定
		this.backgroundColor = '#EFAA42';

		var that = this;
		this.participateButton = Button({
			text: '参加',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center(-3));
		this.participateButton.onpush = function() {
			that.app.send(Cthulhu.JOIN_HEADER);
		};

		this.exitButton = Button({
			text: '退席',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center());
		this.exitButton.onpush = function() {
			that.app.send(Cthulhu.EXIT_HEADER);
		};

		this.startButton = Button({
			text: '開始',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center(3));
		this.startButton.onpush = function() {
			that.app.send(Cthulhu.START_HEADER);
		};

		this.drawButton = Button({
			text: '引く',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center(-1));
		this.drawButton.onpush = function() {
			that.sendAction(Cthulhu.DRAW_ACTION, '');
		};

		this.skillButton = Button({
			text: 'スキル',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center(2));
		this.skillButton.onpush = function() {
			that.skill();
		};

		this.cancelButton = Button({
			text: 'キャンセル',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center(5));
		this.cancelButton.onpush = function() {
			that.app.stackArgs = [];
			that.disp();
		};

		this.dispLabel = Label({
			text: '',
			fontSize: 12,
			align: 'left',
			baseline: 'top',
			lineHeight: 1,
		}).setPosition(this.detailGridX.center(-5), this.detailGridY.center(-5));

		this.yesButton = Button({
			text: 'はい',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(-3), this.detailGridY.center());
		this.yesButton.onpush = function() {
			that.select(Cthulhu.COMMAND_OK);
		};

		this.noButton = Button({
			text: 'いいえ',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(3), this.detailGridY.center());
		this.noButton.onpush = function() {
			that.select(Cthulhu.COMMAND_NG);
		};

		this.openButton = Button({
			text: '狂気度計算',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center());
		this.openButton.onpush = function() {
			that.app.send(Cthulhu.CALCULATE_HEADER);
		};

		this.drawFateButton = Button({
			text: '運命数決定',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(6),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center());
		this.drawFateButton.onpush = function() {
			that.sendBattle(Cthulhu.DRAW_FATE_ACTION);
		};

		this.resetButton = Button({
			text: 'リセット',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		}).setPosition(this.detailGridX.center(), this.detailGridY.center());
		this.resetButton.onpush = function() {
			that.app.send(Cthulhu.RESET_HEADER);
		};

		this.infoButton = Sprite('info', INFO_SIZE_X_BASE, INFO_SIZE_Y_BASE)
		.setPosition(this.detailGridX.center(5), this.detailGridY.center(5));
		this.infoButton.setScale(INFO_SCALE);
		this.infoButton.setInteractive(true);
		this.infoButton.onpointstart = function() {
			that.info();
		};

		this.diceButton = Button({
			text: 'ダイス',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		})
		.addChildTo(this)
		.setPosition(this.detailGridX.center(-3), this.bottomGridY.span(9));
		this.diceButton.onpush = function() {
			that.app.send(Cthulhu.DICE_HEADER);
		};

		this.bellButton = Button({
			text: 'ベル',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(5),
			fontSize: 16,
		})
		.addChildTo(this)
		.setPosition(this.detailGridX.center(3), this.bottomGridY.span(9));
		this.bellButton.onpush = function() {
			that.app.send(Cthulhu.BELL_HEADER);
		};

		this.logArea = ColorfulLabelArea({
			text: '',
			width: this.width,
			height: this.bottomGridY.unit() * 8,
			fontSize: 24,
			align: 'left',
			baseline: 'top',
			lineHeight: 1,
			backgroundColor: '#111111',
			fill: '#FFFFFF',
		})
		.addChildTo(this)
		.setPosition(this.detailGridX.center(), this.bottomGridY.span(4.2));

		this.playerInfos = [];
		this.ret = '';
		if (params.ret !== '') {
			this.ret = params.ret;
			this.kind = params.kind;
			this.number = params.number;
		}

		this.waitRefresh = true;
	},

	alertPush: function(message) {
		this.exit('alert', {
			message: message,
		});
	},

	confirmPush: function(message, select, kind, number) {
		this.exit('confirm', {
			message: message,
			select: select,
			kind: kind,
			number: number,
		});
	},

	selectPush: function(header, list) {
		this.exit('select', {
			header: header,
			list: list,
		});
	},

	refreshView: function(data) {
		this.data = data;
		this.disp();
		if (this.waitRefresh) {
			if (this.ret !== void 0 && this.ret !== '') {
				this.select(this.ret, this.kind, this.number);
				this.ret = '';
			}
			this.updateLog(this.app.logBody);
			this.waitRefresh = false;
		}
	},

	updateLog: function(log) {
		this.logArea.text = log;
	},

	disp: function() {
		var playerName = this.app.playerName;

		this.removeChild(this.participateButton);
		this.removeChild(this.exitButton);
		this.removeChild(this.startButton);
		this.removeChild(this.drawButton);
		this.removeChild(this.skillButton);
		this.removeChild(this.cancelButton);
		this.removeChild(this.dispLabel);
		this.removeChild(this.yesButton);
		this.removeChild(this.noButton);
		this.removeChild(this.openButton);
		this.removeChild(this.drawFateButton);
		this.removeChild(this.resetButton);
		this.removeChild(this.infoButton);
		this.dispLabel.text = '';
		this.playerInfos.forEach(function(playerInfo) {
			playerInfo.hideSelection();
		});

		var participate = false;
		for (var vpi = 0; vpi < this.data.playerInfos.length; vpi++) {
			if (!this.playerInfos[vpi]) {
				this.playerInfos[vpi] = CthulhuPlayerInfo(this);
			}
			this.playerInfos[vpi].setData(this.data.playerInfos[vpi]).refresh(vpi, this.data.playerInfos.length, 1, (vpi == this.data.turn));
			if (this.app.playerName == this.data.playerInfos[vpi].name) {
				participate = true;
			}
		}
		for (var vpo = this.data.playerInfos.length; vpo < this.playerInfos.length; vpo++) {
			this.removeChild(this.playerInfos[vpo]);
		}
		this.playerInfos.length = this.data.playerInfos.length;

		if (this.data.phase == Cthulhu.READY_PHASE) {
			this.participateButton.addChildTo(this);
			if (participate) {
				this.exitButton.addChildTo(this);
				if (this.data.playerInfos.length >= 4) {
					this.startButton.addChildTo(this);
				}
			}
			for (var vpi = 0; vpi < this.data.playerInfos.length; vpi++) {
				this.playerInfos[vpi].refresh(vpi, this.data.playerInfos.length, -1, (vpi == this.data.turn));
			}
		} else {
			this.dispLabel.addChildTo(this);
			if (participate) {
				this.infoButton.addChildTo(this);
			}

			var isNyarlathotep = false;
			var isProfessor = false;
			if (this.data.phase == Cthulhu.TOP_PHASE
				|| this.data.phase == Cthulhu.MIDDLE_PHASE
				|| this.data.phase == Cthulhu.BOTTOM_PHASE
				|| this.data.phase == Cthulhu.LAST_PHASE) {
				this.dispLabel.text += "降臨前フェーズ 時間経過:" + (this.data.phase - 1);
				if (this.data.phase == Cthulhu.LAST_PHASE) {
					this.dispLabel.text += "\n" + this.data.playerInfos[this.data.lastTurnPlayer].name + "まで";
				}
				this.dispLabel.text += "\n";
			} else if (this.data.phase == Cthulhu.ADVENT_PHASE) {
				this.dispLabel.text += "邪神プレイヤー決定フェーズ\n";
				var buttonOpenDisp = false;
				this.data.playerInfos.forEach(function(playerInfo) {
					if (playerInfo.name == playerName && !playerInfo.calculated) {
						buttonOpenDisp = true;
					}
				});
				if (buttonOpenDisp) {
					this.openButton.addChildTo(this);
				}
			} else if (this.data.phase == Cthulhu.BATTLE_PHASE) {
				this.dispLabel.text += "決戦フェーズ\n";
				if (this.data.subPhase == Cthulhu.NYARLATHOTEP_SUB_PHASE) {
					this.data.cthulhuSide.forEach(function(cthulhuPlayer) {
						if (cthulhuPlayer.name == playerName) {
							isNyarlathotep = true;
						}
					});
				} else if (this.data.subPhase == Cthulhu.BATTLE_FATE_SUB_PHASE) {
					var isCthulhuSide = false;
					this.data.cthulhuSide.forEach(function(cthulhuPlayer) {
						if (cthulhuPlayer.name == playerName) {
							isCthulhuSide = true;
						}
					});
					if (isCthulhuSide && this.data.cthulhuFate.length < this.data.playerInfos.length - 1) {
						this.drawFateButton.addChildTo(this);
					} else {
						var fateDrawable = false;
						this.data.playerInfos.forEach(function(player) {
							if (player.name == playerName && player.fate == 0) {
								fateDrawable = true;
							}
						});
						if (fateDrawable) {
							this.drawFateButton.addChildTo(this);
						}
					}
				} else if (this.data.subPhase == Cthulhu.PROFESSOR_SKILL_SUB_PHASE) {
					this.data.playerInfos.forEach(function(player) {
						if (player.name == playerName && player.player.id == Cthulhu.PROFESSOR) {
							isProfessor = true;
						}
					});
				}
			} else {
				this.dispLabel.text += "ゲームセット\n";this.resetButton.addChildTo(this);
			}

			if (isNyarlathotep) {
				this.dispLabel.text += "這いよる混沌を誰に使いますか？\n";
			}
			if (isProfessor) {
				this.dispLabel.text += "真実の伝達を誰に使いますか？\n";
			}

			for (var vpi = 0; vpi < this.playerInfos.length; vpi++) {
				var selectLevel = 0;
				if (this.data.subPhase == Cthulhu.USE_SUB_PHASE && playerName == this.data.turnPlayer.name) {
					if (this.app.stackArgs.length == 1) {
						if (vpi == 0) {
							this.dispLabel.text += "誰に？";
						}
						selectLevel = 1;
					} else if (this.app.stackArgs.length >= 2) {
						var card = this.playerInfos[this.data.turn].selections[this.app.stackArgs[0]];
						if (card.type == Cthulhu.CHECK_DOWN_TYPE || card.type == Cthulhu.CHECK_UP_TYPE) {
							if (this.data.turnPlayer.player.id == Cthulhu.JOURNALIST && this.data.turnPlayer.skill) {
								if (this.app.stackArgs.length == 3 && this.app.stackArgs[2] == Cthulhu.COMMAND_OK) {
									if (vpi == 0) {
										this.dispLabel.text += "誰に？";
									}
									selectLevel = 1;
								} else if (this.app.stackArgs.length == 2) {
									if (vpi == 0) {
										this.dispLabel.text += "ジャーナリストのスキルを使いますか？";
										this.yesButton.addChildTo(this);
										this.noButton.addChildTo(this);
									}
								}
							}
						} else if (card.kind == Cthulhu.ITEM_KIND) {
							if (vpi == 0) {
								this.dispLabel.text += "クローズセット？";
								this.yesButton.addChildTo(this);
								this.noButton.addChildTo(this);
							}
						} else if (card.type == Cthulhu.ITEM_MOVE_TYPE) {
							if (this.app.stackArgs.length == 4) {
								if (vpi == 0) {
									this.dispLabel.text += "だれに？";
								}
								selectLevel = 1;
							}
						}
					}
				} else if (isNyarlathotep == 1 || isProfessor == 1) {
					selectLevel = 1;
				}
				this.playerInfos[vpi].refresh(vpi, this.playerInfos.length, selectLevel, (vpi == this.data.turn));
			}
			if (this.data.phase == Cthulhu.TOP_PHASE
					|| this.data.phase == Cthulhu.MIDDLE_PHASE
					|| this.data.phase == Cthulhu.BOTTOM_PHASE
					|| this.data.phase == Cthulhu.LAST_PHASE) {
				if (this.data.subPhase == Cthulhu.REPAIR_SUB_PHASE) {
					var repairSkill = false;
					this.data.playerInfos.forEach(function(playerInfo) {
						if (playerInfo.name == playerName && playerInfo.player.id == Cthulhu.ENGINEER) {
							repairSkill = true;
						}
					});
					if (repairSkill) {
						this.dispLabel.text += "修理力スキルを使いますか？";
						this.yesButton.addChildTo(this);
						this.noButton.addChildTo(this);
					} else {
						this.dispLabel.text += "修理力スキル待ち";
					}
				} else if (this.data.subPhase == Cthulhu.REGULATE_SUB_PHASE) {
					var regulateSkill = false;
					this.data.playerInfos.forEach(function(playerInfo) {
						if (playerInfo.name == playerName && playerInfo.player.id == Cthulhu.POLICEMAN) {
							regulateSkill = true;
						}
					});
					if (regulateSkill) {
						this.dispLabel.text += "取り締まりスキルを使いますか？";
						this.yesButton.addChildTo(this);
						this.noButton.addChildTo(this);
					} else {
						this.dispLabel.text += "取り締まりスキル待ち";
					}
				} else {
					if (this.data.turnPlayer.name == playerName) {
						if (this.data.subPhase == Cthulhu.DRAW_SUB_PHASE) {
							this.drawButton.addChildTo(this);
							this.dispLabel.text += "引いてください";
							if ((this.data.turnPlayer.player.id == Cthulhu.DILETTANTE
								|| this.data.turnPlayer.player.id == Cthulhu.PSYCHIATRIST
								|| this.data.turnPlayer.player.id == Cthulhu.DETECTIVE)
								&& this.data.turnPlayer.skill) {
								this.skillButton.addChildTo(this);
							}
						} else if (this.data.subPhase == Cthulhu.DILETTANTE_DROP_SUB_PHASE
									|| this.data.subPhase == Cthulhu.DILETTANTE_CHOSE_SUB_PHASE
									|| this.data.subPhase == Cthulhu.CHOOSE_SUB_PHASE) {
							this.dispLabel.text += "カードを選んでください";
							this.playerInfos[this.data.turn].showSelection();
						} else if (this.data.subPhase == Cthulhu.USE_SUB_PHASE) {
							if (this.app.stackArgs.length <= 0) {
								this.dispLabel.text += "使うカードを選んでください";
								this.playerInfos[this.data.turn].showSelection();
							} else {
								this.cancelButton.addChildTo(this);
							}
						}
					} else {
						if (this.data.subPhase == Cthulhu.DRAW_SUB_PHASE) {
							this.dispLabel.text += this.data.turnPlayer.name + "ドロー待ち";
						} else if (this.data.subPhase == Cthulhu.DILETTANTE_DROP_SUB_PHASE) {
							this.dispLabel.text += this.data.turnPlayer.name + "ディレッタント捨札選択中";
						} else if (this.data.subPhase == Cthulhu.DILETTANTE_CHOSE_SUB_PHASE) {
							this.dispLabel.text += this.data.turnPlayer.name + "ディレッタント選択中";
						} else if (this.data.subPhase == Cthulhu.CHOOSE_SUB_PHASE) {
							this.dispLabel.text += this.data.turnPlayer.name + "ドロー選択中";
						} else if (this.data.subPhase == Cthulhu.USE_SUB_PHASE) {
							this.dispLabel.text += this.data.turnPlayer.name + "使用カード選択中";
						}
					}
				}
			}
		}
	},

	skill: function() {
		var that = this;
		if (this.data.turnPlayer.player.id == Cthulhu.PSYCHIATRIST) {
			var list = [];
			this.data.playerInfos.forEach(function(playerInfo) {
				playerInfo.events.forEach(function(event) {
					var item = Button({
						text: playerInfo.name + "の" + event.typeName,
						height: that.detailGridY.span(2),
						width: that.detailGridX.span(12),
						fontSize: 16,
					});
					item.actionHeader = Cthulhu.USE_PREDRAW_SKILL_ACTION;
					item.selectNum = event.id;

					list.push(item);
				});
			});
			if (list.length > 0) {
				this.selectPush("どのイベントを除去しますか？", list);
			}
		} else if (this.data.turnPlayer.player.id == Cthulhu.DETECTIVE) {
			var list = [];
			var index = 0;
			this.data.playerInfos.forEach(function(playerInfo) {
				var item = Button({
					text: playerInfo.name,
					height: that.detailGridY.span(2),
					width: that.detailGridX.span(12),
					fontSize: 16,
				});
				item.actionHeader = Cthulhu.USE_PREDRAW_SKILL_ACTION;
				item.selectNum = index;

				list.push(item);

				index++
			});
			this.selectPush("どのプレイヤーのアイテムを調査しますか？", list);
		} else {
			this.sendAction(Cthulhu.USE_PREDRAW_SKILL_ACTION, '');
		}
	},

	select: function(num, kind, number) {
		var needDisp = true;
		if (this.data.subPhase == Cthulhu.DILETTANTE_DROP_SUB_PHASE) {
			this.sendAction(Cthulhu.DILETTANTE_DROP_CHOOSE_ACTION, num);
			needDisp = false;
		} else if (this.data.subPhase == Cthulhu.DILETTANTE_CHOSE_SUB_PHASE) {
			this.sendAction(Cthulhu.DILETTANTE_CHOOSE_ACTION, num);
			needDisp = false;
		} else if (this.data.subPhase == Cthulhu.CHOOSE_SUB_PHASE) {
			this.sendAction(Cthulhu.CHOOSE_ACTION, num);
			needDisp = false;
		} else if (this.data.subPhase == Cthulhu.USE_SUB_PHASE) {
			this.app.stackArgs.push(num);
			if (this.app.stackArgs.length >= 2) {
				var card = this.playerInfos[this.data.turn].selections[this.app.stackArgs[0]];
				if (card.type == Cthulhu.CHECK_DOWN_TYPE || card.type == Cthulhu.CHECK_UP_TYPE) {
					if (this.data.turnPlayer.player.id != Cthulhu.JOURNALIST || !this.data.turnPlayer.skill) {
						this.app.stackArgs.push(Cthulhu.COMMAND_NG);
						this.app.stackArgs.push(Cthulhu.COMMAND_NG);
					} else if (this.app.stackArgs.length == 3 && this.app.stackArgs[2] == Cthulhu.COMMAND_NG) {
						this.app.stackArgs.push(Cthulhu.COMMAND_NG);
					}

					if (this.app.stackArgs.length >= 4) {
						this.sendAction(Cthulhu.USE_ACTION, this.app.stackArgs.join('<>'));
						this.app.stackArgs = [];
						needDisp = false;
					}
				} else if (card.kind == Cthulhu.ITEM_KIND) {
					if (this.app.stackArgs.length >= 3) {
						this.sendAction(Cthulhu.USE_ACTION, this.app.stackArgs.join('<>'));
						this.app.stackArgs = [];
						needDisp = false;
					}
				} else if (card.type == Cthulhu.ITEM_MOVE_TYPE) {
					if ((this.playerInfos[this.app.stackArgs[1]].openMyItems.length
						+ this.playerInfos[this.app.stackArgs[1]].closedMyItems.length
						+ this.playerInfos[this.app.stackArgs[1]].openAnotherItems.length
						+ this.playerInfos[this.app.stackArgs[1]].closedAnotherItems.length) <= 0) {
						this.app.stackArgs = [this.app.stackArgs[0]];
					} else {
						if (this.app.stackArgs.length == 2) {
							this.app.stackArgs.push(kind);
							this.app.stackArgs.push(number);
						}
						if (this.app.stackArgs.length >= 4) {
							var items = 0;
							if (this.app.stackArgs[2] == Cthulhu.OPEN_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.OPEN_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openAnotherItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedAnotherItems.length;
							}
							if (this.app.stackArgs[3] < 0 || this.app.stackArgs[3] >= items) {
								this.app.stackArgs = [this.app.stackArgs[0], this.app.stackArgs[1], this.app.stackArgs[2]]
							}
						}
						if (this.app.stackArgs.length >= 3) {
							var items = 0;
							if (this.app.stackArgs[2] == Cthulhu.OPEN_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.OPEN_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openAnotherItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedAnotherItems.length;
							}
							if (items <= 0) {
								this.app.stackArgs = [this.app.stackArgs[0], this.app.stackArgs[1]]
							}
						}
					}
					if (this.app.stackArgs.length >= 5) {
						this.sendAction(Cthulhu.USE_ACTION, this.app.stackArgs.join('<>'));
						this.app.stackArgs = [];
						needDisp = false;
					}
				} else if (card.type == Cthulhu.ITEM_REMOVE_TYPE) {
					if ((this.playerInfos[this.app.stackArgs[1]].openMyItems.length
						+ this.playerInfos[this.app.stackArgs[1]].closedMyItems.length
						+ this.playerInfos[this.app.stackArgs[1]].openAnotherItems.length
						+ this.playerInfos[this.app.stackArgs[1]].closedAnotherItems.length) <= 0) {
						this.app.stackArgs = [this.app.stackArgs[0]];
					} else {
						if (this.app.stackArgs.length == 2) {
							this.app.stackArgs.push(kind);
							this.app.stackArgs.push(number);
						}
						if (this.app.stackArgs.length >= 4) {
							var items = 0;
							if (this.app.stackArgs[2] == Cthulhu.OPEN_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.OPEN_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openAnotherItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedAnotherItems.length;
							}
							if (this.app.stackArgs[3] < 0 || this.app.stackArgs[3] >= items) {
								this.app.stackArgs = [this.app.stackArgs[0], this.app.stackArgs[1], this.app.stackArgs[2]]
							}
						}
						if (this.app.stackArgs.length >= 3) {
							var items = 0;
							if (this.app.stackArgs[2] == Cthulhu.OPEN_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_MY_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedMyItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.OPEN_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].openAnotherItems.length;
							} else if (this.app.stackArgs[2] == Cthulhu.CLOSED_ANOTHER_ITEMS) {
								items = this.playerInfos[this.app.stackArgs[1]].closedAnotherItems.length;
							}
							if (items <= 0) {
								this.app.stackArgs = [this.app.stackArgs[0], this.app.stackArgs[1]]
							}
						}
					}
					if (this.app.stackArgs.length >= 4) {
						this.sendAction(Cthulhu.USE_ACTION, this.app.stackArgs.join('<>'));
						this.app.stackArgs = [];
						needDisp = false;
					}
				} else {
					this.sendAction(Cthulhu.USE_ACTION, this.app.stackArgs.join('<>'));
					this.app.stackArgs = [];
					needDisp = false;
				}
			}
		} else if (this.data.subPhase == Cthulhu.REPAIR_SUB_PHASE) {
			this.sendAction(Cthulhu.REPAIR_ACTION, num);
			needDisp = false;
		} else if (this.data.subPhase == Cthulhu.REGULATE_SUB_PHASE) {
			this.sendAction(Cthulhu.REGULATE_ACTION, num);
			needDisp = false;
		} else if (this.data.subPhase == Cthulhu.NYARLATHOTEP_SUB_PHASE) {
			this.sendBattle(Cthulhu.NYARLATHOTEP_ACTION, num);
			needDisp = false;
		} else if (this.data.subPhase == Cthulhu.PROFESSOR_SKILL_SUB_PHASE) {
			this.sendBattle(Cthulhu.PROFESSOR_SKILL_ACTION, num);
			needDisp = false;
		}
		if (needDisp) {
			this.disp();
		}
	},

	info: function() {
		var info = '';
		info += "残り山札:" + this.data.cardDeck.length + "枚\n";
		var playerName = this.app.playerName;
		this.data.playerInfos.forEach(function(player) {
			if (player.name == playerName) {
				player.infoLines.forEach(function(infoLine) {
					info += infoLine + "\n";
				});
			}
		});
		this.alertPush(info);
	},
});

phina.define('AlertScene', {
	superClass: 'GameDefaultScene',
	init: function(params) {
		this.superInit(params);

		this.backgroundColor = '#E2E3CB';
		this.label = LabelArea({
			text: params.message,
			fontSize: 24,
			height: this.detailGridY.unit() * 30,
			width: this.detailGridX.unit() * 30,
		}).addChildTo(this)
		.setPosition(this.detailGridX.center(), this.detailGridY.center());

		var scene = this;

		this.okButton = Button({
			text: 'ok',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(6),
			fontSize: 16,
		}).addChildTo(this)
		.setPosition(this.detailGridX.center(), this.detailGridY.center(16));
		this.okButton.onpush = function() {
			params.ret = '';
			scene.exit(params);
		};
	},
});

phina.define('ConfirmScene', {
	superClass: 'GameDefaultScene',
	init: function(params) {
		this.superInit(params);

		this.backgroundColor = '#E2E3CB';
		this.label = LabelArea({
			text: params.message,
			fontSize: 24,
			height: this.detailGridY.unit() * 30,
			width: this.detailGridX.unit() * 30,
			}).addChildTo(this)
			.setPosition(this.detailGridX.center(), this.detailGridY.center());

		var scene = this;

		this.okButton = Button({
			text: 'ok',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(6),
			fontSize: 16,
		}).addChildTo(this)
		.setPosition(this.detailGridX.center(5), this.detailGridY.center(16));
		this.okButton.onpush = function() {
			params.ret = params.select;
			scene.exit(params);
		};

		this.cancelButton = Button({
			text: 'キャンセル',
			height: this.detailGridY.span(2),
			width: this.detailGridX.span(6),
			fontSize: 16,
		}).addChildTo(this)
		.setPosition(this.detailGridX.center(-5), this.detailGridY.center(16));
		this.cancelButton.onpush = function() {
			params.ret = '';
			scene.exit(params);
		};
	},
});

phina.define('SelectScene', {
	superClass: 'GameDefaultScene',
	init: function(params) {
		this.superInit(params);

		this.backgroundColor = '#E2E3CB';
		this.label = Label({
			text: params.header,
			fontSize: 16,
			}).addChildTo(this)
			.setPosition(this.detailGridX.center(), this.detailGridY.center(-10));

		var that = this;
		var y = -8;
		for (var i = 0; i < params.list.length; i++) {
			params.list[i].addChildTo(this)
				.setPosition(this.detailGridX.center(), this.detailGridY.center(y));
			y += 2;
			params.list[i].onpush = function() {
				that.sendAction(this.actionHeader, this.selectNum);
				that.exit();
			};
		}
	},
});

phina.define('WebSocketGameApp', {
	superClass: 'GameApp',

	init: function(params) {
		this.superInit(params);
		if (params.ws) {
			this.ws = params.ws;
		}
		this.stackArgs = [];
		this.logBody = '';

		this.on('changescene', function() {
			this.send(Cthulhu.REFRESH_HEADER);
		});

		if (params.mesHandler) {
			for (header in params.mesHandler) {
				this.addMesHandler(header, params.mesHandler[header]);
			}
		}
	},

	send: function(mes) {
		if (this.ws) {
			this.ws.send(mes);
		}
	},

	addMesHandler: function(header, handler) {
		this.on('mes' + header, handler);
	},

	_accessor: {
		ws: {
			get: function() { return this._ws; },
			set: function(ws) {
				var that = this;
				this._ws = ws;

				this._ws.onopen = function(){
				};

				this._ws.onclose = function(){
				};

				this._ws.onmessage = function(message){
					var head = message.data.substr(0, 1);
					var body = message.data.substr(1);
					that.flare('mes' + head, {
						body: body,
					});
				};

				this._ws.onerror = function(event){
					alert("エラー");
				};
			},
		},
		playerName: {
			get: function() { return this._playerName; },
			set: function(playerName) {
				this._playerName = playerName;
			},
		},
		stackArgs: {
			get: function() { return this._stackArgs; },
			set: function(stackArgs) {
				this._stackArgs = stackArgs;
			},
		},
		logBody: {
			get: function() { return this._logBody; },
			set: function(logBody) {
				this._logBody = logBody;
				this.currentScene.updateLog && this.currentScene.updateLog(this._logBody);
			},
		},
	},

});