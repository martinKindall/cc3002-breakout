package gui;

import java.util.Random;

public abstract class AbstractBonus implements Bonus {

    protected View myView;

    public AbstractBonus(View view){
        myView = view;
    }

    @Override
    public void onAction() {
        Random generator = new Random();
        double chance = generator.nextDouble();
        if (chance <= probability()){
            actOnView();
        }
    }

    public abstract double probability();

    public abstract void actOnView();
}
