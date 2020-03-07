package UI;

import Model.Synthesizer;

public class WaveViewController {
	
	private WaveView wavView;
	
	public WaveViewController(Synthesizer synth){
		this.wavView = new WaveView(synth);
	}
	
	public WaveView getWavView() {
		return wavView;
	}

}
