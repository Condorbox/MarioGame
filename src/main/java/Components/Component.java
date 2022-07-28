package Components;

import Hex.GameObject;

public abstract class Component {

    public transient GameObject gameObject = null;

    public void start(){

    }
    public void update(float deltaTime){

    }

    public void imGui(){

    }
}