phina.define('CthulhuPlayerInfo', {
	superClass: 'DisplayElement',

	init: function(scene) {
		this.superInit();

		this.scene = scene;
		this.name = "player";
		this.player = CthulhuPlayer();
		this.san = 0;
		this.openMyItems = [];
		this.closedMyItems = [];
		this.openAnotherItems = [];
		this.closedAnotherItems = [];
		this.events = [];
		this.skill = true;
		this.hands = [];
		this.fate = 0;
		this.selections = [];
		this.infoLines = [];
		this.calculated = false;
		this.playerInfoGridX = Grid({
			width: scene.width,
			columns: 36,
		});
		this.playerInfoGridY = Grid({
			width: scene.height,
			columns: 36,
		});
		this.width = this.playerInfoGridX.span(12) - 6;
		this.height = this.playerInfoGridY.span(12) - 6;

		this.backRectangle = RectangleShape({
			height: this.height,
			width: this.width,
			fill: '#FFFFFF',
			backgroundColor: 'transparent',
			stroke: '#FFFFFF',
			padding: 3,
		});
		this.label = Label({
			text: '',
			fontSize: 12,
		});

		this.backRectangle.setPosition(0, 0).addChildTo(this);
		this.label.setPosition(this.playerInfoGridX.span(0), this.playerInfoGridY.span(-5)).addChildTo(this);
		this.player.setPosition(this.playerInfoGridX.span(0), this.playerInfoGridY.span(-1)).addChildTo(this);

		this.addChildTo(scene);
	},

	setData: function(data) {
		this.name = data.name;
		if (data.player) {
			this.player.setData(data.player);
		}
		this.san = data.san;
		for (var omi = 0; omi < data.openMyItems.length; omi++) {
			if (!this.openMyItems[omi]) {
				this.openMyItems.push(CthulhuCard());
				this.openMyItems[omi].addChildTo(this);
			}
			this.openMyItems[omi].setData(data.openMyItems[omi]);
		}
		for (var omo = data.openMyItems.length; omo < this.openMyItems.length; omo++) {
			this.removeChild(this.openMyItems[omo]);
		}
		this.openMyItems.length = data.openMyItems.length;
		for (var cmi = 0; cmi < data.closedMyItems.length; cmi++) {
			if (!this.closedMyItems[cmi]) {
				this.closedMyItems.push(CthulhuCard());
				this.closedMyItems[cmi].addChildTo(this);
			}
			this.closedMyItems[cmi].setData(data.closedMyItems[cmi]);
		}
		for (var cmo = data.closedMyItems.length; cmo < this.openMyItems.length; cmo++) {
			this.removeChild(this.closedMyItems[cmo]);
		}
		this.closedMyItems.length = data.closedMyItems.length;
		for (var oai = 0; oai < data.openAnotherItems.length; oai++) {
			if (!this.openAnotherItems[oai]) {
				this.openAnotherItems.push(CthulhuCard());
				this.openAnotherItems[oai].addChildTo(this);
			}
			this.openAnotherItems[oai].setData(data.openAnotherItems[oai]);
		}
		for (var oao = data.openAnotherItems.length; oao < this.openAnotherItems.length; oao++) {
			this.removeChild(this.openAnotherItems[oao]);
		}
		this.openAnotherItems.length = data.openAnotherItems.length;
		for (var cai = 0; cai < data.closedAnotherItems.length; cai++) {
			if (!this.closedAnotherItems[cai]) {
				this.closedAnotherItems.push(CthulhuCard());
				this.closedAnotherItems[cai].addChildTo(this);
			}
			this.closedAnotherItems[cai].setData(data.closedAnotherItems[cai]);
		}
		for (var cao = data.closedAnotherItems.length; cao < this.closedAnotherItems.length; cao++) {
			this.removeChild(this.closedAnotherItems[cao]);
		}
		this.closedAnotherItems.length = data.closedAnotherItems.length;
		for (var ei = 0; ei < data.events.length; ei++) {
			if (!this.events[ei]) {
				this.events.push(CthulhuEvent());
				this.events[ei].addChildTo(this);
			}
			this.events[ei].setData(data.events[ei]);
		}
		for (var eo = data.events.length; eo < this.events.length; eo++) {
			this.removeChild(this.events[eo]);
		}
		this.events.length = data.events.length;
		this.skill = data.skill;
		for (var hi = 0; hi < data.hands.length; hi++) {
			if (!this.hands[hi]) {
				this.hands.push(CthulhuCard());
				this.hands[hi].addChildTo(this);
			}
			this.hands[hi].setData(data.hands[hi]);
		}
		for (var ho = data.hands.length; ho < this.hands.length; ho++) {
			this.removeChild(this.hands[ho]);
		}
		this.hands.length = data.hands.length;
		this.fate = data.fate;
		for (var si = 0; si < data.selections.length; si++) {
			if (!this.selections[si]) {
				this.selections.push(CthulhuCard());
			}
			this.selections[si].setData(data.selections[si]);
		}
		for (var so = data.selections.length; so < this.selections.length; so++) {
			this.removeChild(this.selections[so]);
		}
		this.selections.length = data.selections.length;
		this.infoLines = data.infoLines;
		this.calculated = data.calculated;

		return this;
	},

	refresh: function(place, players, selectLevel, turn) {
		if (players) {
			var scene = this.scene;
			var posX, posY;
			if (place == 1) {
				if (players >= 5) {
					posX = this.playerInfoGridX.span(30);
					posY = this.playerInfoGridY.span(30);
				} else {
					posX = this.playerInfoGridX.span(30);
					posY = this.playerInfoGridY.span(18);
				}
			} else if (place == 2) {
				if (players >= 5) {
					posX = this.playerInfoGridX.span(30);
					posY = this.playerInfoGridY.span(18);
				} else {
					posX = this.playerInfoGridX.span(18);
					posY = this.playerInfoGridY.span(6);
				}
			} else if (place == 3) {
				if (players >= 6) {
					posX = this.playerInfoGridX.span(30);
					posY = this.playerInfoGridY.span(6);
				} else if (players == 5) {
					posX = this.playerInfoGridX.span(18);
					posY = this.playerInfoGridY.span(6);
				} else {
					posX = this.playerInfoGridX.span(6);
					posY = this.playerInfoGridY.span(18);
				}
			} else if (place == 4) {
				if (players >= 6) {
					posX = this.playerInfoGridX.span(18);
					posY = this.playerInfoGridY.span(6);
				} else {
					posX = this.playerInfoGridX.span(6);
					posY = this.playerInfoGridY.span(18);
				}
			} else if (place == 5) {
				if (players >= 7) {
					posX = this.playerInfoGridX.span(6);
					posY = this.playerInfoGridY.span(6);
				} else {
					posX = this.playerInfoGridX.span(6);
					posY = this.playerInfoGridY.span(18);
				}
			} else if (place == 6) {
				posX = this.playerInfoGridX.span(6);
				posY = this.playerInfoGridY.span(18);
			} else if (place == 7) {
				posX = this.playerInfoGridX.span(6);
				posY = this.playerInfoGridY.span(30);
			} else {
				posX = this.playerInfoGridX.span(18);
				posY = this.playerInfoGridY.span(30);
			}
			this.setPosition(posX, posY);
			var strokeColor = '#FFFFFF';
			if (turn) {
				strokeColor = '#FF0000';
			}

			this.backRectangle.stroke = strokeColor;

			var info =  this.name + ":" + this.player.name + " san:";
			if (this.san != -10) {
				info += this.san;
			} else {
				info += '??';
			}
			info += "+" + this.player.san + "\n";
			info += "スキル:";
			if (this.skill == 1) {
				info += "未";
			} else {
				info += "済";
			}

			this.label.text = info;

			if (this.player.id != -1) {
				if (selectLevel == 1) {
					this.player.setDispatch(place, scene);
				} else {
					this.player.setDispatch(-1, scene);
				}
			}
			var cardX = CARD_SIZE_X * 0.5 + PLAYER_SIZE_X * 0.5;
			var cardY = this.playerInfoGridY.span(-2);
			var dispWidth = this.playerInfoGridX.span(6);
			var dispHeight = this.playerInfoGridY.span(6);
			var index = 0;
			this.openMyItems.forEach(function(card) {
				card.setPosition(cardX, cardY);
				if (selectLevel == 1) {
					card.setDispatch(place, scene, Cthulhu.CARD_MOVE_MY_OPEN, index);
				} else {
					card.setDispatch(-1, scene, Cthulhu.CARD_MOVE_MY_OPEN, index);
				}

				cardX += CARD_SIZE_X;
				if (cardX + CARD_SIZE_X > dispWidth) {
					cardX = CARD_SIZE_X * 0.5 + PLAYER_SIZE_X * 0.5;
					cardY += CARD_SIZE_Y;
				}

				index++;
			});
			index = 0;
			this.closedMyItems.forEach(function(card) {
				card.setPosition(cardX, cardY);
				if (selectLevel == 1) {
					card.setDispatch(place, scene, Cthulhu.CARD_MOVE_MY_CLOSED, index);
				} else {
					card.setDispatch(-1, scene, Cthulhu.CARD_MOVE_MY_CLOSED, index);
				}

				cardX += CARD_SIZE_X;
				if (cardX + CARD_SIZE_X > dispWidth) {
					cardX = CARD_SIZE_X * 0.5 + PLAYER_SIZE_X * 0.5;
					cardY += CARD_SIZE_Y;
				}

				index++;
			});
			this.events.forEach(function(card) {
				card.setPosition(cardX, cardY);
				card.setDispatch(-1, scene);

				cardX += CARD_SIZE_X;
				if (cardX + CARD_SIZE_X > dispWidth) {
					cardX = CARD_SIZE_X * 0.5 + PLAYER_SIZE_X * 0.5;
					cardY += CARD_SIZE_Y;
				}
			});

			cardX = -1 * CARD_SIZE_X * 0.5 - PLAYER_SIZE_X * 0.5;
			cardY = this.playerInfoGridY.span(-2);
			index = 0;
			this.openAnotherItems.forEach(function(card) {
				card.setPosition(cardX, cardY);
				if (selectLevel == 1) {
					card.setDispatch(place, scene, Cthulhu.CARD_MOVE_ANOTHER_OPEN, index);
				} else {
					card.setDispatch(-1, scene, Cthulhu.CARD_MOVE_ANOTHER_OPEN, index);
				}

				cardX -= CARD_SIZE_X;
				if (cardX - CARD_SIZE_X < -1 * dispWidth) {
					cardX = -1 * CARD_SIZE_X * 0.5 - PLAYER_SIZE_X * 0.5;
					cardY += CARD_SIZE_Y;
				}

				index++;
			});
			index = 0;
			this.closedAnotherItems.forEach(function(card) {
				card.setPosition(cardX, cardY);
				if (selectLevel == 1) {
					card.setDispatch(place, scene, Cthulhu.CARD_MOVE_ANOTHER_CLOSED, index);
				} else {
					card.setDispatch(-1, scene, Cthulhu.CARD_MOVE_ANOTHER_CLOSED, index);
				}

				cardX -= CARD_SIZE_X;
				if (cardX - CARD_SIZE_X < -1 * dispWidth) {
					cardX = -1 * CARD_SIZE_X * 0.5 - PLAYER_SIZE_X * 0.5;
					cardY += CARD_SIZE_Y;
				}

				index++;
			});
			cardX = this.playerInfoGridX.span(-5);
			cardY = this.playerInfoGridY.span(5);
			this.hands.forEach(function(card) {
				card.setPosition(cardX, cardY);
				card.setDispatch(-1, scene, 0, 0);

				cardX += CARD_SIZE_X;
			});
		}
	},

	showSelection: function() {
		var cardX = this.playerInfoGridX.center(-5);
		var cardY = this.playerInfoGridY.center(4);
		var index = 0;
		var scene = this.scene;
		this.selections.forEach(function(card) {
			card.setPosition(cardX, cardY).addChildTo(scene);
			card.setDispatch(index, scene, 0, 0);

			cardX += CARD_SIZE_X;
			index++;
		});
	},

	hideSelection: function() {
		var scene = this.scene;
		this.selections.forEach(function(card) {
			scene.removeChild(card);
		});
	}
});