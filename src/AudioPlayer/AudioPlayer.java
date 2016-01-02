package AudioPlayer;

import java.io.IOException;

import javax.sound.sampled.*;

import Resources.Resources;

public class AudioPlayer {

	private Clip clip;
	public AudioPlayer(String path) {
		AudioInputStream ais;
		try {
			ais = AudioSystem.getAudioInputStream(Resources.getResourceStream(path));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void setLoop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play() {
		if (clip == null)
			return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}

	public void stop() {
		if (clip.isRunning())
			clip.stop();
	}

	public void close() {
		stop();
		clip.close();
	}

}
