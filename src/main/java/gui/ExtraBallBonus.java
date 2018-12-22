package gui;

public class ExtraBallBonus extends AbstractBonus {

    public ExtraBallBonus(View view){
        super(view);
    }

    @Override
    public double probability() {
        return 0.5;
    }

    @Override
    public void actOnView() {
        myView.generateBall();
        myView.getAudioPlayer().playSound("extraBall.wav");
    }
}
