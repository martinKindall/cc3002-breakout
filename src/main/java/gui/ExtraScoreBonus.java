package gui;

public class ExtraScoreBonus extends AbstractBonus {

    public ExtraScoreBonus(View view){
        super(view);
    }

    @Override
    public double probability() {
        return 0.5;
    }

    @Override
    public void actOnView() {
        myView.increaseScore(50000);
        myView.setPanel();
        myView.getAudioPlayer().playSound("extraScore.wav");
    }
}
