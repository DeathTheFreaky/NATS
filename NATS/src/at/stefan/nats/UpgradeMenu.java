package at.stefan.nats;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import android.graphics.Typeface;
import at.alex.nats.Player;
import at.stefan.nats.ProgressBar.AndEngine;

public class UpgradeMenu extends Scene {

	boolean touch = false;

	nats nats;
	Camera mainCamera;
	GameEnvironment gameEnvironment;
	SceneManager sceneManager;
	Player player;

	Font myFont;
	Font smallFont;
	Font resourcesFont;
	Finals finals;
	Upgrade upgrade;

	BitmapTextureAtlas backgroundBitmapTextureAtlas;
	ITextureRegion backgroundITextureRegion;
	Sprite backgroundSprite;

	BitmapTextureAtlas backBitmapTextureAtlas;
	ITextureRegion backITextureRegion;
	Sprite backSprite;

	BitmapTextureAtlas movespeedBitmapTextureAtlas;
	ITextureRegion movespeedITextureRegion;
	Sprite movespeedSprite;
	ProgressBar movespeedProgressBar;
	Rectangle movespeedSelect;

	BitmapTextureAtlas gunnerBitmapTextureAtlas;
	ITextureRegion gunnerITextureRegion;
	Sprite gunnerSprite;
	ProgressBar gunnerProgressBar;
	Rectangle gunnerSelect;

	BitmapTextureAtlas shieldBitmapTextureAtlas;
	ITextureRegion shieldITextureRegion;
	Sprite shieldSprite;
	ProgressBar shieldProgressBar;
	Rectangle shieldSelect;

	BitmapTextureAtlas shotfrequenceBitmapTextureAtlas;
	ITextureRegion shotfrequenceITextureRegion;
	Sprite shotfrequenceSprite;
	ProgressBar shotfrequenceProgressBar;
	Rectangle shotfrequenceSelect;

	BitmapTextureAtlas shotspreadingBitmapTextureAtlas;
	ITextureRegion shotspreadingITextureRegion;
	Sprite shotspreadingSprite;
	ProgressBar shotspreadingProgressBar;
	Rectangle shotspreadingSelect;

	BitmapTextureAtlas stasisfieldBitmapTextureAtlas;
	ITextureRegion stasisfieldITextureRegion;
	Sprite stasisfieldSprite;
	// BitmapTextureAtlas stasisfieldCounterBitmapTextureAtlas;
	// ITextureRegion stasisfieldCounterITextureRegion;
	Text stasisfieldText;
	Rectangle stasisfieldSelect;

	BitmapTextureAtlas turboBitmapTextureAtlas;
	ITextureRegion turboITextureRegion;
	Sprite turboSprite;
	Text turboText;
	Rectangle turboSelect;

	BitmapTextureAtlas deadlytrailBitmapTextureAtlas;
	ITextureRegion deadlytrailITextureRegion;
	Sprite deadlytrailSprite;
	Text deadlytrailText;
	Rectangle deadlytrailSelect;

	BitmapTextureAtlas bombBitmapTextureAtlas;
	ITextureRegion bombITextureRegion;
	Sprite bombSprite;
	Text bombText;
	Rectangle bombSelect;

	Rectangle rand;
	Rectangle info;
	BitmapTextureAtlas infoBuyBitmapTextureAtlas;
	ITextureRegion infoBuyITextureRegion;
	Sprite infoBuySprite;
	BitmapTextureAtlas infoBuyBackgroundBitmapTextureAtlas;
	ITextureRegion infoBuyBackgroundITextureRegion;
	Sprite infoBuyBackgroundSprite;
	Text header;
	Text level;
	Text description;
	Text price;
	Text resources;

	public UpgradeMenu(nats nats, Camera cam, GameEnvironment ge,
			SceneManager s, Player player) {
		this.nats = nats;
		this.mainCamera = cam;
		this.gameEnvironment = ge;
		this.sceneManager = s;
		this.player = player;

		finals = new Finals();

		this.setBackgroundEnabled(false);
	}

	public void loadUpgradeResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		backgroundBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 800, 480, TextureOptions.DEFAULT);
		backgroundITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(backgroundBitmapTextureAtlas,
						nats.getApplicationContext(), "Grau.png", 0, 0);
		backgroundBitmapTextureAtlas.load();

		backBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 70, 70, TextureOptions.DEFAULT);
		backITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(backBitmapTextureAtlas,
						nats.getApplicationContext(), "Back.png", 0, 0);
		backBitmapTextureAtlas.load();

		movespeedBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		movespeedITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(movespeedBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		movespeedBitmapTextureAtlas.load();
		movespeedProgressBar = new ProgressBar(100,
				nats.getCameraHeight() - 105, 100, 10,
				AndEngine.GLES2_AnchorCenter);
		// movespeedProgressBar.setBackGroundColor(new Color(0.0f, 1.0f, 0.0f));
		// movespeedProgressBar.setForeGroundColor(new Color(0.0f, 0.0f, 1.0f));
		movespeedProgressBar.setIntervall(5);
		// movespeedProgressBar.show();

		gunnerBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		gunnerITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gunnerBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		gunnerBitmapTextureAtlas.load();
		gunnerProgressBar = new ProgressBar(100, nats.getCameraHeight() - 225,
				100, 10, AndEngine.GLES2_AnchorCenter);
		// gunnerProgressBar.setBackGroundColor(new Color(0.0f, 1.0f, 0.0f));
		// gunnerProgressBar.setForeGroundColor(new Color(1.0f, 0.0f, 1.0f));
		gunnerProgressBar.setIntervall(5);

		shieldBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		shieldITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(shieldBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		shieldBitmapTextureAtlas.load();
		shieldProgressBar = new ProgressBar(100, 135, 100, 10,
				AndEngine.GLES2_AnchorCenter);
		// shieldProgressBar.setBackGroundColor(new Color(0.0f, 1.0f, 0.0f));
		// shieldProgressBar.setForeGroundColor(new Color(1.0f, 1.0f, 1.0f));
		shieldProgressBar.setIntervall(5);

		shotfrequenceBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		shotfrequenceITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(shotfrequenceBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		shotfrequenceBitmapTextureAtlas.load();
		shotfrequenceProgressBar = new ProgressBar(250,
				nats.getCameraHeight() - 105, 100, 10,
				AndEngine.GLES2_AnchorCenter);
		// shotfrequenceProgressBar.setBackGroundColor(new Color(0.0f, 1.0f,
		// 0.0f));
		// shotfrequenceProgressBar.setForeGroundColor(new Color(0.0f, 1.0f,
		// 1.0f));
		shotfrequenceProgressBar.setIntervall(5);

		shotspreadingBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		shotspreadingITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(shotspreadingBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		shotspreadingBitmapTextureAtlas.load();
		shotspreadingProgressBar = new ProgressBar(250,
				nats.getCameraHeight() - 225, 100, 10,
				AndEngine.GLES2_AnchorCenter);
		// shotspreadingProgressBar.setBackGroundColor(new Color(0.0f, 1.0f,
		// 0.0f));
		// shotspreadingProgressBar.setForeGroundColor(new Color(0.5f, 0.5f,
		// 0.5f));
		shotspreadingProgressBar.setIntervall(5);

		myFont = FontFactory.create(nats.getFontManager(),
				nats.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 50);
		myFont.load();

		stasisfieldBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		stasisfieldITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(stasisfieldBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		stasisfieldBitmapTextureAtlas.load();
		stasisfieldText = new Text(90, 30, myFont, "x2", new TextOptions(
				HorizontalAlign.CENTER), nats.getVertexBufferObjectManager());

		turboBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		turboITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(turboBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		turboBitmapTextureAtlas.load();
		turboText = new Text(290, 30, myFont, "x1", new TextOptions(
				HorizontalAlign.CENTER), nats.getVertexBufferObjectManager());

		deadlytrailBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		deadlytrailITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(deadlytrailBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		deadlytrailBitmapTextureAtlas.load();
		deadlytrailText = new Text(490, 30, myFont, "x0", new TextOptions(
				HorizontalAlign.CENTER), nats.getVertexBufferObjectManager());

		bombBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 100, 100, TextureOptions.DEFAULT);
		bombITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(bombBitmapTextureAtlas,
						nats.getApplicationContext(), "100.png", 0, 0);
		bombBitmapTextureAtlas.load();
		bombText = new Text(690, 30, myFont, "x0", new TextOptions(
				HorizontalAlign.CENTER), nats.getVertexBufferObjectManager());

		infoBuyBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 150, 50, TextureOptions.DEFAULT);
		infoBuyITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(infoBuyBitmapTextureAtlas,
						nats.getApplicationContext(), "Buy.png", 0, 0);
		infoBuyBitmapTextureAtlas.load();

		infoBuyBackgroundBitmapTextureAtlas = new BitmapTextureAtlas(
				nats.getTextureManager(), 160, 60, TextureOptions.DEFAULT);
		infoBuyBackgroundITextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(infoBuyBackgroundBitmapTextureAtlas,
						nats.getApplicationContext(), "BuyBackground.png", 0, 0);
		infoBuyBackgroundBitmapTextureAtlas.load();

		smallFont = FontFactory.create(nats.getFontManager(),
				nats.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 35);
		smallFont.load();

		header = new Text(
				560,
				420,
				myFont,
				"Ganz langer Text, damit der Array lange genug wird und keine ArrayOutOfIndex Exception geworfen wird.",
				new TextOptions(HorizontalAlign.CENTER), nats
						.getVertexBufferObjectManager());
		level = new Text(
				560,
				370,
				smallFont,
				"Ganz langer Text, damit der Array lange genug wird und keine ArrayOutOfIndex Exception geworfen wird.",
				new TextOptions(HorizontalAlign.LEFT), nats
						.getVertexBufferObjectManager());
		description = new Text(
				560,
				295,
				smallFont,
				"Ganz langer Text, damit der Array lange genug wird und keine ArrayOutOfIndex Exception geworfen wird.",
				new TextOptions(HorizontalAlign.LEFT), nats
						.getVertexBufferObjectManager());
		price = new Text(
				560,
				220,
				smallFont,
				"Ganz langer Text, damit der Array lange genug wird und keine ArrayOutOfIndex Exception geworfen wird.",
				new TextOptions(HorizontalAlign.LEFT), nats
						.getVertexBufferObjectManager());

		resourcesFont = FontFactory.create(nats.getFontManager(),
				nats.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 28,
				Color.WHITE.hashCode());
		resourcesFont.load();

		resources = new Text(
				460,
				160,
				resourcesFont,
				"Ganz langer Text, damit der Array lange genug wird und keine ArrayOutOfIndex Exception geworfen wird.",
				new TextOptions(HorizontalAlign.LEFT), nats
						.getVertexBufferObjectManager());

		header.setText("Speed");
		level.setText("Level: " + player.getPermanents(finals.movespeed()));
		description.setText("Increases Movespeed \nof the spaceship.");
		price.setText("Upgrade: 100");
		resources.setText("Resources: \n" + player.getRessources());
		resources.setColor(new Color(1, 0, 0));

		// new Te
	}

	public void loadUpgradeScene() {

		backgroundSprite = new Sprite(nats.getCameraWidth() / 2,
				nats.getCameraHeight() / 2, backgroundITextureRegion,
				nats.getVertexBufferObjectManager());
		backgroundSprite.setAlpha(0.4f);

		backSprite = new Sprite(nats.getCameraWidth() - 51,
				nats.getCameraHeight() - 35, backITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					gameEnvironment.hideUpgradeMenu();
				}
				return true;
			}
		};

		movespeedSprite = new Sprite(100, nats.getCameraHeight() - 60,
				movespeedITextureRegion, nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.movespeedInfo();
					// gameEnvironment.hideUpgradeMenu();
				}
				return true;
			};
		};
		movespeedSelect = new Rectangle(100, nats.getCameraHeight() - 60, 110,
				110, nats.getVertexBufferObjectManager());
		movespeedSelect.setColor(new Color(1, 140 / 255, 0));
		movespeedSelect.setVisible(true);

		gunnerSprite = new Sprite(100, nats.getCameraHeight() - 180,
				gunnerITextureRegion, nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.gunnerInfo();
					// gameEnvironment.hideUpgradeMenu();
					// sceneManager.switchScene(AllScenes.MAIN_MENU);
				}
				return true;
			};
		};
		gunnerSelect = new Rectangle(100, nats.getCameraHeight() - 180, 110,
				110, nats.getVertexBufferObjectManager());
		gunnerSelect.setColor(new Color(1, 140 / 255, 0));
		gunnerSelect.setVisible(false);

		shieldSprite = new Sprite(100, 180, shieldITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.shieldInfo();
					// gameEnvironment.hideUpgradeMenu();
				}
				return true;
			};
		};
		shieldSelect = new Rectangle(100, 180, 110, 110,
				nats.getVertexBufferObjectManager());
		shieldSelect.setColor(new Color(1, 140 / 255, 0));
		shieldSelect.setVisible(false);

		shotfrequenceSprite = new Sprite(250, nats.getCameraHeight() - 60,
				shotfrequenceITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.shotfrequenceInfo();
					// gameEnvironment.hideUpgradeMenu();
					// sceneManager.switchScene(AllScenes.MAIN_MENU);
				}
				return true;
			};
		};
		shotfrequenceSelect = new Rectangle(250, nats.getCameraHeight() - 60,
				110, 110, nats.getVertexBufferObjectManager());
		shotfrequenceSelect.setColor(new Color(1, 140 / 255, 0));
		shotfrequenceSelect.setVisible(false);

		shotspreadingSprite = new Sprite(250, nats.getCameraHeight() - 180,
				shotspreadingITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.shotspreadingInfo();
					// gameEnvironment.hideUpgradeMenu();
				}
				return true;
			};
		};
		shotspreadingSelect = new Rectangle(250, nats.getCameraHeight() - 180,
				110, 110, nats.getVertexBufferObjectManager());
		shotspreadingSelect.setColor(new Color(1, 140 / 255, 0));
		shotspreadingSelect.setVisible(false);

		stasisfieldSprite = new Sprite(70, 60, stasisfieldITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.stasisfieldInfo();
				}
				return true;
			};
		};
		stasisfieldSelect = new Rectangle(70, 60, 110, 110,
				nats.getVertexBufferObjectManager());
		stasisfieldSelect.setColor(new Color(1, 140 / 255, 0));
		stasisfieldSelect.setVisible(false);

		turboSprite = new Sprite(270, 60, turboITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.turboInfo();
				}
				return true;
			};
		};
		turboSelect = new Rectangle(270, 60, 110, 110,
				nats.getVertexBufferObjectManager());
		turboSelect.setColor(new Color(1, 140 / 255, 0));
		turboSelect.setVisible(false);

		deadlytrailSprite = new Sprite(470, 60, deadlytrailITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.deadlytrailInfo();
				}
				return true;
			};
		};
		deadlytrailSelect = new Rectangle(470, 60, 110, 110,
				nats.getVertexBufferObjectManager());
		deadlytrailSelect.setColor(new Color(1, 140 / 255, 0));
		deadlytrailSelect.setVisible(false);

		bombSprite = new Sprite(670, 60, bombITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					upgrade.bombInfo();
				}
				return true;
			};
		};
		bombSelect = new Rectangle(670, 60, 110, 110,
				nats.getVertexBufferObjectManager());
		bombSelect.setColor(new Color(1, 140 / 255, 0));
		bombSelect.setVisible(false);

		rand = new Rectangle(nats.getCameraWidth() * 3 / 4 - 40, 290, 400, 330,
				nats.getVertexBufferObjectManager());
		rand.setColor(new Color(0, 0, 0));
		rand.setAlpha(0.2f);

		info = new Rectangle(nats.getCameraWidth() * 3 / 4 - 40, 290, 390, 320,
				nats.getVertexBufferObjectManager());
		info.setColor(new Color(0, 0, 1));
		info.setAlpha(0.2f);

		infoBuyBackgroundSprite = new Sprite(660, 160,
				infoBuyBackgroundITextureRegion,
				nats.getVertexBufferObjectManager());
		infoBuyBackgroundSprite.setVisible(false);

		infoBuySprite = new Sprite(660, 160, infoBuyITextureRegion,
				nats.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				if (pSceneTouchEvent.isActionUp()) {
					// Add Timehandler
					infoBuyBackgroundSprite.setVisible(true);
					upgrade.buy();
					this.registerUpdateHandler(new TimerHandler(0.2f,
							new ITimerCallback() {

								@Override
								public void onTimePassed(
										TimerHandler pTimerHandler) {
									// TODO Auto-generated method stub
									infoBuyBackgroundSprite.setVisible(false);
									UpgradeMenu.this
											.unregisterUpdateHandler(pTimerHandler);
								}
							}));

				}
				return true;
			};
		};

		this.attachChild(backgroundSprite);
		this.attachChild(movespeedSelect);
		this.attachChild(movespeedSprite);
		movespeedProgressBar.attach(this);
		this.attachChild(gunnerSelect);
		this.attachChild(gunnerSprite);
		gunnerProgressBar.attach(this);
		this.attachChild(shieldSelect);
		this.attachChild(shieldSprite);
		shieldProgressBar.attach(this);
		this.attachChild(shotfrequenceSelect);
		this.attachChild(shotfrequenceSprite);
		shotfrequenceProgressBar.attach(this);
		this.attachChild(shotspreadingSelect);
		this.attachChild(shotspreadingSprite);
		shotspreadingProgressBar.attach(this);
		this.attachChild(stasisfieldSelect);
		this.attachChild(stasisfieldSprite);
		this.attachChild(stasisfieldText);
		this.attachChild(turboSelect);
		this.attachChild(turboSprite);
		this.attachChild(turboText);
		this.attachChild(deadlytrailSelect);
		this.attachChild(deadlytrailSprite);
		this.attachChild(deadlytrailText);
		this.attachChild(bombSelect);
		this.attachChild(bombSprite);
		this.attachChild(bombText);

		this.attachChild(rand);
		this.attachChild(info);
		this.attachChild(infoBuyBackgroundSprite);
		this.attachChild(infoBuySprite);
		this.attachChild(header);
		this.attachChild(level);
		this.attachChild(description);
		this.attachChild(price);
		this.attachChild(resources);

		this.attachChild(backSprite);

		gameEnvironment.attachUpgradeMenu(this);
		gameEnvironment.setUpgradeReference(movespeedSprite, gunnerSprite,
				shieldSprite, shotfrequenceSprite, shotspreadingSprite,
				stasisfieldSprite, turboSprite, deadlytrailSprite, bombSprite,
				backSprite, infoBuySprite, this);

		upgrade = new Upgrade(this, player, movespeedSelect, gunnerSelect,
				shieldSelect, shotfrequenceSelect, shotspreadingSelect,
				stasisfieldSelect, turboSelect, deadlytrailSelect, bombSelect,
				infoBuySprite, stasisfieldText, turboText, deadlytrailText,
				bombText, movespeedProgressBar, gunnerProgressBar,
				shieldProgressBar, shotfrequenceProgressBar,
				shotspreadingProgressBar, header, level, description, price,
				resources);

	}

	public void actualizeResources() {
		upgrade.actualizeResources();
	}

}
