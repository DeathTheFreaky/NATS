package at.clemens.nats;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.adt.color.Color;

import android.util.Log;
import at.alex.nats.Player;
import at.stefan.nats.EnemyPool;
import at.stefan.nats.GameEnvironment;
import at.stefan.nats.UserData;
import at.stefan.nats.nats;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class EnemyTypeTwoSmall extends PEnemy {

	private final int resources = 20;

	private final int maxMoveSpeed = 200;
	private final int acceleration = 10;

	private TextureRegion textur;
	private Sprite enemy;
	// private Rectangle enemy;
	private PhysicsWorld world;
	private Body body;
	private FixtureDef fd;
	private PhysicsConnector pc;
	private TimerHandler th;
	private EnemyPool enemyPool;

	public EnemyTypeTwoSmall(GameEnvironment g, TextureRegion textur,
			nats nats, PhysicsWorld world, Player p, EnemyPool enemyPool) {
		super(nats, p, g);
		this.world = world;
		this.textur = textur;
		this.enemyPool = enemyPool;
		// enemy = new Sprite(super.posx, super.posy, this.textur,
		// nats.getVertexBufferObjectManager());
		enemy = new Sprite(0f, 0f, super.game.getEnemyTwoSmallTextureRegion(),
				nats.getVertexBufferObjectManager());
		enemy.setCullingEnabled(true);
		// enemy.setColor(new Color(0f, 0f, 1f));
		enemy.setVisible(false);
		fd = PhysicsFactory.createFixtureDef(0f, 0f, 0f);
		body = PhysicsFactory.createBoxBody(world, enemy, BodyType.DynamicBody,
				fd);
		body.setActive(false);
		body.setAwake(false);
		body.setUserData(new UserData("enemytwo", this));

		th = new TimerHandler(0.050f, true, new ITimerCallback() {

			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				// TODO Auto-generated method stub
				move();
				// Log.i("NATS", "movex: " + EnemyTypeTwo.this.getMovex() +
				// " movey: " + EnemyTypeTwo.this.getMovey());
				body.setLinearVelocity(
						EnemyTypeTwoSmall.this.getMovex() * 0.05f,
						EnemyTypeTwoSmall.this.getMovey() * 0.05f);

				float pRotationRad = (float) Math.atan2(
						(EnemyTypeTwoSmall.super.movex / maxMoveSpeed),
						(EnemyTypeTwoSmall.super.movey / maxMoveSpeed));
				// Log.i("NATSRot", "" + (-pRotationRad));
				body.setTransform(EnemyTypeTwoSmall.super.posx / 32,
						EnemyTypeTwoSmall.super.posy / 32, -pRotationRad);
			}
		});

		pc = new PhysicsConnector(enemy, body, true, true);
	}

	public void start(float x, float y) {
		// this.createStartPos(super.game);
		if (!enemy.hasParent()) {
			super.game.getEnemyTwoSmallSpriteGroup().attachChild(enemy);
		}
		enemy.setVisible(true);

		// TODO start fly function | alle 15 msec ausf�hren
		// TODO Timehandler

		body.setActive(true);
		body.setAwake(true);

		if (Math.random() < 0.5f) {
			super.movex = 0;
			super.movey = 50;
		} else {
			super.movex = 50;
			super.movey = 0;
		}

		body.setTransform(x / 32, y / 32, 0f);

		world.registerPhysicsConnector(pc);
		nats.getEngine().registerUpdateHandler(th);
	}

	@Override
	protected void createStartPos(Scene pf) {
		super.createStartPos(pf);
	}

	@Override
	public void stop() {
		nats.getEngine().runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				EnemyTypeTwoSmall.super.addRessources(resources);
				EnemyTypeTwoSmall.super.game.getEnemyTwoSmallSpriteGroup()
						.detachChild(enemy);
				enemy.setVisible(false);
				body.setActive(false);
				body.setAwake(false);
				body.setLinearVelocity(0f, 0f);
				body.setTransform(-500, -340, 0.0f);
				world.unregisterPhysicsConnector(pc);
				nats.getEngine().unregisterUpdateHandler(th);

				nats.getEngine().registerUpdateHandler(
						new TimerHandler(1f, new ITimerCallback() {

							@Override
							public void onTimePassed(TimerHandler pTimerHandler) {
								// TODO Auto-generated method stub
								nats.getEngine().unregisterUpdateHandler(
										pTimerHandler);
								enemyPool
										.recycleEnemyTwoSmall(EnemyTypeTwoSmall.this);
							}
						}));
			}
		});

	}

	protected void move() {
		float offsetX, offsetY;
		float pPosx, pPosy;

		super.posx = enemy.getX();
		super.posy = enemy.getY();

		if (frozen) {
			super.movex = 0;
			super.movey = 0;
			return;
		}

		// Log.i("NATS", "pposx: " + super.player.getPosX() + " pposy: " +
		// super.player.getPosY());
		pPosx = super.player.getPosX();
		pPosy = super.player.getPosY();
		offsetX = pPosx - super.posx;
		offsetY = pPosy - super.posy;

		// Adjust move direction X to new player position
		if (offsetX < 0) {
			super.movex -= (super.movex > -this.maxMoveSpeed) ? acceleration
					: 0;
		} else if (offsetX > 0) {
			super.movex += (super.movex < this.maxMoveSpeed) ? acceleration : 0;
		} else if (offsetX == 0 && super.movex != 0) {
			super.movex = (super.movex > 0) ? super.movex - acceleration
					: super.movex + acceleration;
		}

		// Adjust move direction Y to new player position
		if (offsetY < 0) {
			super.movey -= (super.movey > -this.maxMoveSpeed) ? acceleration
					: 0;
		} else if (offsetY > 0) {
			super.movey += (super.movey < this.maxMoveSpeed) ? acceleration : 0;
		} else if (offsetY == 0 && super.movey != 0) {
			super.movey = (super.movey > 0) ? super.movey - acceleration
					: super.movey + acceleration;
		}

		return;
	}

	public float getPosx() {
		return super.posx;
	}

	public float getPosy() {
		return super.posy;
	}

	public int getResources() {
		return this.resources;
	}

	private int getMovex() {
		return super.movex;
	}

	private int getMovey() {
		return super.movey;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		nats.getEngine().runOnUpdateThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// Log.i("NATS", "stop");
				EnemyTypeTwoSmall.super.game.getEnemyTwoSmallSpriteGroup()
						.detachChild(enemy);
				// Log.i("NATS", "stop1");
				enemy.setVisible(false);
				// Log.i("NATS", "stop2");
				body.setActive(false);
				// Log.i("NATS", "stop3");
				body.setAwake(false);
				// Log.i("NATS", "stop4");
				// body.setLinearVelocity(0f, 0f);
				body.setTransform(-500, -340, 0.0f);
				// Log.i("NATS", "stop5");
				world.unregisterPhysicsConnector(pc);
				// Log.i("NATS", "stop6");
				nats.getEngine().unregisterUpdateHandler(th);
				// Log.i("NATS", "stop7");
				enemyPool.recycleEnemyTwoSmall(EnemyTypeTwoSmall.this);
			}
		});
	}
}

/*
 * public class EnemyTypeTwoSmall extends PEnemy {
 * 
 * private final int maxMoveSpeed = 200; private TextureRegion textur; //
 * private Sprite enemy; private Rectangle enemy; private PhysicsWorld world;
 * private Body body; private FixtureDef fd; private PhysicsConnector pc;
 * private TimerHandler th; private EnemyPool enemyPool;
 * 
 * public EnemyTypeTwoSmall(GameEnvironment g, TextureRegion textur, nats nats,
 * PhysicsWorld world, Player p, EnemyPool enemyPool) { super(nats, p, g);
 * this.world = world; this.textur = textur;
 * 
 * this.enemyPool = enemyPool; // enemy = new Sprite(super.posx, super.posy,
 * this.textur, // nats.getVertexBufferObjectManager()); enemy = new
 * Rectangle(0, 0, 20, 20, nats.getVertexBufferObjectManager());
 * enemy.setColor(new Color(1f, 0f, 0f)); enemy.setVisible(false); fd =
 * PhysicsFactory.createFixtureDef(0f, 0f, 0f); body =
 * PhysicsFactory.createBoxBody(world, enemy, BodyType.DynamicBody, fd);
 * body.setActive(false); body.setAwake(false); body.setUserData(new
 * UserData("enemytwosmall", this));
 * 
 * th = new TimerHandler(0.050f, true, new ITimerCallback() {
 * 
 * @Override public void onTimePassed(TimerHandler pTimerHandler) { // TODO
 * Auto-generated method stub move();
 * body.setLinearVelocity(EnemyTypeTwoSmall.this.getMovex(),
 * EnemyTypeTwoSmall.this.getMovey()); } });
 * 
 * pc = new PhysicsConnector(enemy, body, true, false); }
 * 
 * public void start(float x, float y) { // this.createStartPos(game);
 * super.game.attachChild(enemy); enemy.setVisible(true);
 * 
 * // TODO start fly function | alle 15 msec ausf�hren // TODO Timehandler
 * 
 * body.setActive(true); body.setAwake(true);
 * 
 * body.setTransform(x / 32, y / 32, 0f);
 * 
 * world.registerPhysicsConnector(pc);
 * nats.getEngine().registerUpdateHandler(th); }
 * 
 * @Override protected void createStartPos(Scene pf) { super.createStartPos(pf);
 * this.move(); }
 * 
 * @Override public void stop() { nats.getEngine().runOnUpdateThread(new
 * Runnable() {
 * 
 * @Override public void run() { // TODO Auto-generated method stub
 * EnemyTypeTwoSmall.super.game.detachChild(enemy); enemy.setVisible(false);
 * body.setActive(false); body.setAwake(false); body.setLinearVelocity(0f, 0f);
 * body.setTransform(-500, -340, 0.0f); world.unregisterPhysicsConnector(pc);
 * nats.getEngine().unregisterUpdateHandler(th);
 * 
 * nats.getEngine().registerUpdateHandler( new TimerHandler(1f, new
 * ITimerCallback() {
 * 
 * @Override public void onTimePassed(TimerHandler pTimerHandler) { // TODO
 * Auto-generated method stub nats.getEngine().unregisterUpdateHandler(
 * pTimerHandler); enemyPool .recycleEnemyTwoSmall(EnemyTypeTwoSmall.this); }
 * })); } });
 * 
 * }
 * 
 * protected void move() { float offsetX, offsetY; float pPosx, pPosy;
 * 
 * if (frozen) { movex = 0; movey = 0; return; }
 * 
 * pPosx = player.getPosX(); pPosy = player.getPosY(); offsetX = pPosx - posx;
 * offsetY = pPosy - posy;
 * 
 * // Adjust move direction X to new player position if (offsetX < 0) { movex -=
 * (movex > -maxMoveSpeed) ? 1 : 0; } else if (offsetX > 0) { movex += (movex <
 * maxMoveSpeed) ? 1 : 0; } else if (offsetX == 0 && movex != 0) { movex =
 * (movex > 0) ? movex - 1 : movex + 1; }
 * 
 * // Adjust move direction Y to new player position if (offsetY < 0) { movey -=
 * (movey > -maxMoveSpeed) ? 1 : 0; } else if (offsetY > 0) { movey += (movey <
 * maxMoveSpeed) ? 1 : 0; } else if (offsetY == 0 && movey != 0) { movey =
 * (movey > 0) ? movey - 1 : movey + 1; }
 * 
 * return; }
 * 
 * private int getMovex(){ return super.movex; }
 * 
 * private int getMovey(){ return super.movey; }
 * 
 * @Override public void start() { // TODO Auto-generated method stub
 * 
 * } }
 */