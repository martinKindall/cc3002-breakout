package gui;

public class ExtraScoreBonus extends AbstractBonus {

    public ExtraScoreBonus(View view){
        super(view);
    }

    @Override
    public double probability() {
        return 0.03;
    }

    @Override
    public void actOnView() {
        myView.increaseScore(50000);
        myView.setPanel();
        myView.getAudioPlayer().playSound("extraScore.wav");
    }
}
