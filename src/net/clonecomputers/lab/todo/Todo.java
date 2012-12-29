package net.clonecomputers.lab.todo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Todo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				initGui();
			}
			
		});
	}
	
	private static void initGui() {
		JFrame window = new JFrame("clone todo");
		JPanel contentPane = new JPanel(new BorderLayout(30, 0));
		contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		contentPane.add(getAlarmListPanel(), BorderLayout.WEST);
		contentPane.add(getAlarmSettingsPanel(), BorderLayout.EAST);
		window.setContentPane(contentPane);
		window.pack();
		window.setVisible(true);
	}
	
	private static JPanel getAlarmListPanel() {
		JPanel list = new JPanel(new BorderLayout());
		//TODO create alarm list panel
		return list;
	}
	
	private static JPanel getAlarmSettingsPanel() {
		JPanel settings = new JPanel(/*Layout to be determined*/);
		//TODO make alarm settings panel
		return settings;
	}

}
