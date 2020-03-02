package com.el.poli.entradas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.el.poli.actores.Boo;
import com.el.poli.actores.Vegeta;

public class EscuchadorTeclado extends InputListener implements InputProcessor{

    Vegeta vegeta;
    SpriteBatch batch;

    public EscuchadorTeclado(Vegeta j){
        this.vegeta=j;
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("eventoDown","Input "+keycode);
        switch (keycode) {
            case Input.Keys.A:
                vegeta.getCuerpo().setLinearVelocity(new Vector2(-10,0));

                break;
            case Input.Keys.D:
                vegeta.getCuerpo().setLinearVelocity(new Vector2(10,0));

                break;
            case Input.Keys.W:

                vegeta.getCuerpo().applyLinearImpulse(new Vector2(0,10), vegeta.getCuerpo().getWorldCenter(),true);

                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        vegeta.getCuerpo().setLinearVelocity(new Vector2(0,0));
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
