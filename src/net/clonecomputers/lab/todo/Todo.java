package net.clonecomputers.lab.todo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class Todo {
	
	private static JButton removeButton;
	private static JList alarmList;
	private static AlarmSettingsPanel alarmSettings;
	private static Vector<AlarmListItem> alarms = new Vector<AlarmListItem>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadAlarms();
		final Todo todo = new Todo();
		EventQueue.invokeLater(new Runnable() {

			//@Override
			public void run() {
				todo.initGui();
			}
			
		});
	}
	
	private static void loadAlarms() {
		//TODO write code here!
		alarms.add(new AlarmListItem("Test", new ScheduledAlarm(2013, 5, 1, 12, 0))); //just a test thing
	}
	
	private void initGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace(); //ho hum we can't use system look and feel
		}
		JFrame window = new JFrame("clone todo");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel(new BorderLayout(30, 0));
		contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		contentPane.add(getAlarmListPanel(), BorderLayout.WEST);
		alarmSettings = new AlarmSettingsPanel(this);
		contentPane.add(alarmSettings, BorderLayout.EAST);
		window.setContentPane(contentPane);
		window.pack();
		window.setVisible(true);
	}
	
	private JPanel getAlarmListPanel() {
		JPanel listPanel = new JPanel(new BorderLayout(0, 3));
		listPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		alarmList = new JList(alarms);
		alarmList.setFixedCellHeight(80);
		alarmList.setFixedCellWidth(350);
		alarmList.setCellRenderer(new AlarmListCellRenderer());
		alarmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		alarmList.setDragEnabled(false);
		alarmList.clearSelection();
		listPanel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				//TODO: this needs to do something
				alarmList.clearSelection();
			}
		});
		listPanel.add(alarmList, BorderLayout.NORTH);
		listPanel.add(getAddRemoveButtons(), BorderLayout.SOUTH);
		return listPanel;
	}
	
	private JPanel getAddRemoveButtons() {
		JPanel buttonPanel = new JPanel();
		JButton addButton = new JButton("+");
		removeButton = new JButton("-");
		addButton.addActionListener(new ActionListener() {

			//@Override
			public void actionPerformed(ActionEvent e) {
				openNewAlarm();
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
	
	private void openNewAlarm() {
		//TODO write this
	}
	
	private void removeSelectedAlarm() {
		//TODO write this
	}
	
	public void saveAlarm(AlarmListItem alarm) {
		
	}
	
	public class AlarmListCellRenderer extends Component implements ListCellRenderer {
		
		private static final long serialVersionUID = -323811720627355823L;
		
		private Image img;
		private Image selImg;
		
		private boolean isSelected;
		
		private AlarmListItem cellValue = null;
		
		public AlarmListCellRenderer() {
			img = Toolkit.getDefaultToolkit().createImage("resources/Louis/ListItemBackground.jpg");
			selImg = Toolkit.getDefaultToolkit().createImage("resources/Louis/SelectedListItemBackground.jpg");
		}
		
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			this.isSelected = isSelected;
			if(value instanceof AlarmListItem) {
				cellValue = (AlarmListItem)value;
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
			if(cellValue != null) {
				Font normalFont = g.getFont();
				Font bigFont = new Font(normalFont.getName(), Font.PLAIN, 15);
				g.setFont(bigFont);
				g.setColor(Color.BLACK);
				g.drawString(cellValue.getName(), 10, 20);
				g.setFont(normalFont);
				g.drawString("Alarm: " + cellValue.getAlarm(), 18, 37);
				g.drawString("Next Alarm Time: " + DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(cellValue.getAlarm().getSoonestDate()), 18, 53);
			} else {
				throw new NullPointerException("Cell Value " + cellValue + " is null");
			}
		}
		
	}

}
