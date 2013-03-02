package net.clonecomputers.lab.todo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class Todo {
	
	private static JButton removeButton;
	private static JList alarmList;
	private static Vector<ScheduledAlarm> alarms = new Vector<ScheduledAlarm>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadAlarms();
		EventQueue.invokeLater(new Runnable() {

			//@Override
			public void run() {
				initGui();
			}
			
		});
	}
	
	private static void loadAlarms() {
		//TODO write code here!
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
		alarmList = new JList(alarms);
		alarmList.setFixedCellHeight(80);
		alarmList.setFixedCellWidth(350);
		alarmList.setCellRenderer(new AlarmListCellRenderer());
		alarmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		alarmList.setDragEnabled(false);
		alarmList.clearSelection();
		listPanel.add(alarmList, BorderLayout.NORTH);
		listPanel.add(getAddRemoveButtons(), BorderLayout.SOUTH);
		return listPanel;
	}
	
	private static JPanel getAddRemoveButtons() {
		JPanel buttonPanel = new JPanel();
		JButton addButton = new JButton("+");
		removeButton = new JButton("-");
		addButton.addActionListener(new ActionListener() {

			//@Override
			public void actionPerformed(ActionEvent arg0) {
				addNewAlarm();
			}
			
		});
		removeButton.addActionListener(new ActionListener() {
			
			//@Override
			public void actionPerformed(ActionEvent e) {
				removeSelectedAlarm();
			}
			
		});
		addButton.setPreferredSize(new Dimension(20, 20));
		removeButton.setPreferredSize(new Dimension(20, 20));
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		removeButton.setEnabled(!alarmList.isSelectionEmpty());
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
	
	public static class AlarmListCellRenderer extends Component implements ListCellRenderer {
		
		private static final long serialVersionUID = -323811720627355823L;
		
		private Image img;
		private Image selImg;
		
		private boolean isSelected;
		
		private ScheduledAlarm cellValue = null;
		
		public AlarmListCellRenderer() {
			img = Toolkit.getDefaultToolkit().createImage("resources/Louis/ListItemBackground.jpg");
			selImg = Toolkit.getDefaultToolkit().createImage("resources/Louis/SelectedListItemBackground.jpg");
		}
		
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			this.isSelected = isSelected;
			if(value instanceof ScheduledAlarm) {
				cellValue = (ScheduledAlarm)value;
			}
			return this;
		}
		
		@Override
		public void paint(Graphics g) {
			if(isSelected) {
				g.drawImage(selImg, 0, 0, null);
			} else {
				g.drawImage(img, 0, 0, null);
			}
			if(cellValue == null) return;
			//TODO draw rest of list component
		}
		
	}

}
