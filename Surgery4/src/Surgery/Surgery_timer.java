package Surgery;
import java.awt.Container;
import java.awt.Font;
import javax.swing.*;

class TimerRunnable extends JFrame implements Runnable {
	public JLabel timerLabel;
	
	public TimerRunnable(JLabel timerLable) {
		this.timerLabel = Surgery_main.timerLabel;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int n=0;
		while(true) {
			Surgery_main.timerLabel.setText(Integer.toString(n));
			n++;
			if(n<0) {
				//엔딩화면으로 (아직안함)
			}
		
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				return;
			}
		}
	}
}

public class Surgery_timer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Surgery_timer(); 
	}

}
