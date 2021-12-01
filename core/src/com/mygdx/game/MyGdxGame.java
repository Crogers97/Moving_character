package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;
	Texture background;
	Texture imgR;
	Texture imgL;
	Texture imgU;
	Texture imgD;
	Texture staticImgL;
	Texture staticImgR;
	Texture staticImgU;
	Texture staticImgD;
	TextureRegion[] animationFramesR;
	TextureRegion[] animationFramesL;
	TextureRegion[] animationFramesU;
	TextureRegion[] animationFramesD;
	Animation animationR;
	Animation animationL;
	Animation animationU;
	Animation animationD;
	float elapsedTime;
	float x;
	float y;
	int changeSide = 0;
	OrthographicCamera camera;
	final float GAME_WORLD_WIDTH=6000;
	final float GAME_WORLD_HEIGHT=3000;


	@Override
	public void create () {

		float aspectRatio= (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

		camera = new OrthographicCamera(GAME_WORLD_WIDTH * aspectRatio,GAME_WORLD_HEIGHT);
		camera.position.set(GAME_WORLD_WIDTH/2,GAME_WORLD_HEIGHT/2,0);





		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(Gdx.files.internal("map3.jpg")));
		sprite.setSize(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);





		imgR = new Texture("Grandpa_spriteR.png");
		imgL= new Texture("Grandpa_spriteL.png");
		imgU= new Texture("Grandpa_spriteU.png");
		imgD= new Texture("Grandpa_spriteD.png");


		staticImgL = new Texture("Grandpa_singleR.png");
		staticImgR= new Texture("Grandpa_singleL.png");
		staticImgU= new Texture("Grandpa_singleU.png");
		staticImgD= new Texture("Grandpa_singleD.png");

//		background= new Texture("map.png");

		TextureRegion[][] tmpFramesR = TextureRegion.split(imgR,60,48);
		TextureRegion[][] tmpFramesL = TextureRegion.split(imgL,60,48);
		TextureRegion[][] tmpFramesU = TextureRegion.split(imgU,60,48);
		TextureRegion[][] tmpFramesD = TextureRegion.split(imgD,60,48);

//		looping over animation
		animationFramesR = new TextureRegion[9];
		int indexR = 0;

		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 1; j++){
				animationFramesR[indexR++] = tmpFramesR[j][i];
			}
		}

		animationFramesL = new TextureRegion[9];
		int indexL = 0;

		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 1; j++){
				animationFramesL[indexL++] = tmpFramesL[j][i];
			}
		}

		animationFramesU = new TextureRegion[9];
		int indexU = 0;

		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 1; j++){
				animationFramesU[indexU++] = tmpFramesU[j][i];
			}
		}

		animationFramesD = new TextureRegion[9];
		int indexD = 0;

		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 1; j++){
				animationFramesD[indexD++] = tmpFramesD[j][i];
			}
		}


//frame rate
		animationR = new Animation(1f/12f, animationFramesR);
		animationL = new Animation(1f/12f, animationFramesL);
		animationU = new Animation(1f/12f, animationFramesU);
		animationD = new Animation(1f/12f, animationFramesD);


	}



	@Override
	public void render () {

		ScreenUtils.clear(0, 0, 0.2f, 1);

		elapsedTime += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClear((GL20.GL_COLOR_BUFFER_BIT));
		camera.position.set(x, y, 0);
		camera.zoom = 17;
		camera.viewportHeight= camera.zoom;
		camera.viewportWidth= camera.zoom;
		camera.update();
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		sprite.draw(batch);
//		batch.draw(background, 0, 0);







		if(Gdx.input.isKeyPressed(Input.Keys.UP)){batch.draw((TextureRegion) animationU.getKeyFrame(elapsedTime,true), x, y);
			y = y + 2;
			changeSide = 0;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){batch.draw((TextureRegion) animationD.getKeyFrame(elapsedTime,true), x, y);
			y = y - 2;
			changeSide = 1;
		} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){batch.draw((TextureRegion) animationL.getKeyFrame(elapsedTime,true), x, y);
			x = x - 2;
			changeSide = 2;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {batch.draw((TextureRegion) animationR.getKeyFrame(elapsedTime,true), x, y);
			x = x + 2;
			changeSide = 3;
		} else if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){batch.draw((TextureRegion) animationR.getKeyFrame(elapsedTime, true), x, y);
			x = x + 4;
			changeSide = 3;
		} else if (Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT)){batch.draw((TextureRegion) animationL.getKeyFrame(elapsedTime, true), x, y);
			x = x - 4;
			changeSide = 2;
		} else {
			if(changeSide == 3){
				batch.draw(staticImgL, x, y);
			} else if (changeSide == 2){
				batch.draw(staticImgR, x, y);
			} else if (changeSide == 0){
				batch.draw(staticImgU, x, y);
			} else if (changeSide == 1){
				batch.draw(staticImgD, x, y);
			}
		}






		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
		imgR.dispose();
		imgL.dispose();
		imgU.dispose();
		imgD.dispose();
		sprite.getTexture().dispose();
	}

}
