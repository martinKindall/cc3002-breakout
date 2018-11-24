package test;

/**
 * @author https://github.com/martinKindall
 */

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import org.junit.*;
import static org.junit.Assert.*;

public class BrickTest {
    private Brick wooden;
    private Brick glass;
    private Brick metal;


    @Before
    public void setup(){
        wooden = new WoodenBrick();
        glass = new GlassBrick();
        metal = new MetalBrick();
    }

    @Test
    public void hitsTest(){
        int glassCnt = 0;

        while (!glass.isDestroyed()){
            glass.hit();
            glassCnt++;
        }

        assertEquals(glassCnt, 1);

        int woodenCnt = 0;

        while (!wooden.isDestroyed()){
            wooden.hit();
            woodenCnt++;
        }

        assertEquals(woodenCnt, 3);

        int metalCnt = 0;

        while (!metal.isDestroyed()){
            metal.hit();
            metalCnt++;
        }

        assertEquals(metalCnt, 10);
    }

    @Test
    public void scoreTest(){
        assertEquals(glass.getScore(), 50);
        assertEquals(wooden.getScore(), 200);
        assertEquals(metal.getScore(), 0);
    }

    @Test
    public void remainingHitsTest(){
        int hits = 2;

        for (int i = 0; i < hits; i++){
            glass.hit();
            wooden.hit();
            metal.hit();
        }

        assertEquals(glass.remainingHits(), 0);
        assertEquals(wooden.remainingHits(), 1);
        assertEquals(metal.remainingHits(), 8);

        for (int i = 0; i < hits; i++){
            glass.hit();
            wooden.hit();
            metal.hit();
        }

        assertEquals(glass.remainingHits(), 0);
        assertEquals(wooden.remainingHits(), 0);
        assertEquals(metal.remainingHits(), 6);
    }
}
