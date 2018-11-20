package logic.brick;

public class GlassBrick extends BrickClass{

    GlassBrick(){
        super(1,50);
    }


    @Override
    public void hit() {
        if(this.isDestroyed()==false){
            this.beHit();
            if(this.isDestroyed()==true){
                this.setChanged();
                this.notifyObservers();
            }
        }
    }




}
