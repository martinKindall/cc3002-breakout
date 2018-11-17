package logic.level;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlayableLevel extends AbstractLevel{

    protected List<Brick> bricks;
    private int obtainableScore;
    private int currentScore;
    private int numberOfBricks;


    public PlayableLevel(String nombre, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        super(nombre);
        this.numberOfBricks = numberOfBricks;
        obtainableScore = 0;

        bricks = new LinkedList<>();
        Brick newBrick;

        Random generator = new Random(seed);

        for (int i=0; i < numberOfBricks; i++){
            double coin = generator.nextDouble();
            if (coin <= probOfGlass){
                newBrick = new GlassBrick();
            }
            else{
                newBrick = new WoodenBrick();
            }

            bricks.add(newBrick);
            obtainableScore += newBrick.getScore();
        }

        for (int i=0; i < numberOfBricks; i++){
            double coin = generator.nextDouble();
            if (coin <= probOfMetal){
                newBrick = new MetalBrick();
                bricks.add(newBrick);
                obtainableScore += newBrick.getScore();
            }
        }
    }

    @Override
    public List<Brick> getBricks() {
        return bricks;
    }

    public int getNumberOfBricks(){
        return numberOfBricks;
    }

    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    @Override
    public boolean hasNextLevel() {
        return next.isPlayableLevel();
    }

    @Override
    public void setNextLevel(Level level) {
        next = level;
    }

    @Override
    public int getPoints() {
        return obtainableScore;
    }
}
