package test;

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
}
