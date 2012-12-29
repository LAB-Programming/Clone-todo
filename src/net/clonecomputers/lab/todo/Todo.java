package net.clonecomputers.lab.todo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Todo {
	
	private static JButton removeButton;
	private static JList alarmList;
	
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
		JPanel listPanel = new JPanel(new BorderLayout(0, 3));
		listPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		//TODO create list for alarmList
		listPanel.add(alarmList, BorderLayout.NORTH);
		listPanel.add(getAddRemoveButtons(), BorderLayout.SOUTH);
		return listPanel;
	}
	
	private static JPanel getAddRemoveButtons() {
		JPanel buttonPanel = new JPanel();
		JButton addButton = new JButton("+");
		removeButton = new JButton("-");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNewAlarm();
			}
			
		});
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeSelectedAlarm();
			}
			
		});
		addButton.setPreferredSize(new Dimension(20, 20));
		removeButton.setPreferredSize(new Dimension(20, 20));
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		return buttonPanel;
	}
	
	private static JPanel getAlarmSettingsPanel() {
		JPanel settings = new JPanel(/*Layout to be determined*/);
		//TODO make alarm settings panel
		return settings;
	}
	
	private static void addNewAlarm() {
		//TODO write this
	}
	
	private static void removeSelectedAlarm() {
		//TODO write this
	}

}
