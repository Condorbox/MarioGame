package Components;

import Editor.HImGui;
import Renderer.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class SpriteRenderer extends Component{
    private Vector4f color = new Vector4f(1,1,1,1);
    private Sprite sprite = new Sprite();
    private int zIndex = 0;

    private transient Transform lastTransform;
    private transient boolean isDirty = true;

    public SpriteRenderer(){

    }

    /*public SpriteRenderer(Vector4f color){
        this.zIndex = 0;
        this.color = color;
        this.sprite = new Sprite(null);
        isDirty = true;
    }
    public SpriteRenderer(Sprite sprite, int zIndex){
        this.zIndex = zIndex;
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
        isDirty = true;
    }*/

    @Override
    public void start(){
        this.lastTransform = gameObject.transform.copy();
    }

    @Override
    public void update(float deltaTime) {
        if(!lastTransform.equals(gameObject.transform)){
            gameObject.transform.copy(lastTransform);
            isDirty = true;
        }
    }

    @Override
    public void editorUpdate(float dt) {
        if (!this.lastTransform.equals(this.gameObject.transform)) {
            this.gameObject.transform.copy(this.lastTransform);
            isDirty = true;
        }
    }

    public Vector4f getColor() {
        return color;
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
        isDirty = true;
    }

    public void setColor(Vector4f color){
        if(!color.equals(this.color)){
            this.color = color;
            isDirty = true;
        }
    }

    public boolean isDirty(){
        return isDirty;
    }
    public void setDirty() {
        this.isDirty = true;
    }
    public void setClean(){
        isDirty = false;
    }

    public int zIndex(){
        return zIndex;
    }

    public void setZIndex(int zIndex){
        this.zIndex = zIndex;
    }

    public void setTexture(Texture texture) {
        this.sprite.setTexture(texture);
    }

    @Override
    public void imGui(){
        if (HImGui.colorPicker4("Color Pickier", this.color)) {
            isDirty = true;
        }
        this.zIndex = HImGui.dragInt("Z-Index", this.zIndex);
    }
}
