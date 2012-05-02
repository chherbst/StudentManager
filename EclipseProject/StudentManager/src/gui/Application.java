package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import DataModel.Student;
import controller.MainController;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Application {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfLastName;
	private MainController mainController;
	final JFileChooser fc = new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		mainController = new MainController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 686, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		final JList studentList = new JList();
		studentList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() || studentList.getSelectedIndex() == -1)
					return;
				Student student = mainController.getStudent(studentList.getSelectedIndex());
				tfName.setText(student.getName());
				tfLastName.setText(student.getLastName());
			}
		});
		studentList.setModel(mainController.getStudentModel());
    	frame.setTitle(mainController.getModelfile());
		GridBagConstraints gbc_studentList = new GridBagConstraints();
		gbc_studentList.gridheight = 3;
		gbc_studentList.insets = new Insets(0, 0, 5, 5);
		gbc_studentList.fill = GridBagConstraints.BOTH;
		gbc_studentList.gridx = 1;
		gbc_studentList.gridy = 1;
		frame.getContentPane().add(studentList, gbc_studentList);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 3;
		gbc_lblName.gridy = 1;
		frame.getContentPane().add(lblName, gbc_lblName);
		
		tfName = new JTextField();
		GridBagConstraints gbc_tfName = new GridBagConstraints();
		gbc_tfName.gridwidth = 5;
		gbc_tfName.insets = new Insets(0, 0, 5, 0);
		gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfName.gridx = 4;
		gbc_tfName.gridy = 1;
		frame.getContentPane().add(tfName, gbc_tfName);
		tfName.setColumns(10);
		
		JLabel lblLastname = new JLabel("Lastname");
		GridBagConstraints gbc_lblLastname = new GridBagConstraints();
		gbc_lblLastname.anchor = GridBagConstraints.EAST;
		gbc_lblLastname.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastname.gridx = 3;
		gbc_lblLastname.gridy = 2;
		frame.getContentPane().add(lblLastname, gbc_lblLastname);
		
		tfLastName = new JTextField();
		GridBagConstraints gbc_tfLastName = new GridBagConstraints();
		gbc_tfLastName.gridwidth = 5;
		gbc_tfLastName.insets = new Insets(0, 0, 5, 0);
		gbc_tfLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfLastName.gridx = 4;
		gbc_tfLastName.gridy = 2;
		frame.getContentPane().add(tfLastName, gbc_tfLastName);
		tfLastName.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon(Application.class.getResource("/gui/save_medium.png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.save();
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(Application.class.getResource("/gui/minus_medium.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.deleteStudent(studentList.getSelectedIndex());
			}
		});
		
		JButton btnNew = new JButton("New");
		btnNew.setIcon(new ImageIcon(Application.class.getResource("/gui/plus_medium.png")));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.createStudent(tfName.getText(), tfLastName.getText());
			}
		});
		GridBagConstraints gbc_btnNew = new GridBagConstraints();
		gbc_btnNew.anchor = GridBagConstraints.EAST;
		gbc_btnNew.insets = new Insets(0, 0, 0, 5);
		gbc_btnNew.gridx = 5;
		gbc_btnNew.gridy = 4;
		frame.getContentPane().add(btnNew, gbc_btnNew);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 6;
		gbc_btnDelete.gridy = 4;
		frame.getContentPane().add(btnDelete, gbc_btnDelete);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setIcon(new ImageIcon(Application.class.getResource("/gui/apply_medium.png")));
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainController.editStudent(studentList.getSelectedIndex(), tfName.getText(), tfLastName.getText());
			}
		});
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.insets = new Insets(0, 0, 0, 5);
		gbc_btnApply.gridx = 7;
		gbc_btnApply.gridy = 4;
		frame.getContentPane().add(btnApply, gbc_btnApply);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridx = 8;
		gbc_btnSave.gridy = 4;
		frame.getContentPane().add(btnSave, gbc_btnSave);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setCurrentDirectory(new File("."));
				int returnVal = fc.showOpenDialog(frame);

			    if (returnVal == JFileChooser.APPROVE_OPTION) {
			    	File file = fc.getSelectedFile();
			    	mainController.loadFromFile(file.getName());
			    	frame.setTitle(mainController.getModelfile());
			    	tfLastName.setText("");
			    	tfName.setText("");
			    } else {
			    	System.out.println("Open command cancelled by user.");
			    }
			} 
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
	}

}
