package io.github.podshot.Podlauncher.gui;

import io.github.podshot.Podlauncher.GetMinecraftVersions;
import io.github.podshot.Podlauncher.files.LauncherConfig;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.json.simple.JSONObject;

public class EditProfileGUI extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldUsername;
	private JTextField textFieldProfileName;
	private JPasswordField passwordFieldPassword;
	private JComboBox<String> comboBox;
	private JButton btnCreateProfile;
	private JTextField textFieldGameDir;
	private ButtonGroup initRamBtnGrp;
	private ButtonGroup maxRamBtnGrp;
	private JRadioButton rdbtnInit256Megabytes;
	private JRadioButton rdbtnInit512Megabytes;
	private JRadioButton rdbtnInit1Gigabyte;
	private JRadioButton rdbtnInit1_5Gigabytes;
	private JRadioButton rdbtnInit2Gigabytes;
	private JRadioButton rdbtnInitCustom;
	private JTextField initCustomRam;
	private JTextField maxCustomRam;
	private JRadioButton rdbtnMax256Megabytes;
	private JRadioButton rdbtnMax512Megabytes;
	private JRadioButton rdbtnMax1Gigabyte;
	private JRadioButton rdbtnMax1_5Gigabytes;
	private JRadioButton rdbtnMax2Gigabytes;
	private JRadioButton rdbtnMaxCustom;

	public EditProfileGUI(String profile_to_edit) {
		//this.addWindowListener(new ExitListener());
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent evt) {
				saveProfile();
			}
		};
		JSONObject profile = LauncherConfig.getProfile(profile_to_edit, true);
		this.setTitle("Edit Profile");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(650, 300);
		this.addWindowListener(exitListener);

		JLabel lblProfileName = new JLabel("Profile Name");
		lblProfileName.setBounds(10, 10, 79, 14);
		getContentPane().add(lblProfileName);

		textFieldProfileName = new JTextField();
		textFieldProfileName.setText((String) profile.get("Profile Name"));
		lblProfileName.setLabelFor(textFieldProfileName);
		textFieldProfileName.setBounds(99, 7, 136, 20);
		getContentPane().add(textFieldProfileName);
		textFieldProfileName.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setLabelFor(textFieldUsername);
		lblUsername.setBounds(10, 50, 79, 14);
		getContentPane().add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setText((String) profile.get("Username"));
		textFieldUsername.setBounds(99, 47, 136, 20);
		getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 90, 79, 14);
		getContentPane().add(lblPassword);

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setText((String) profile.get("Password"));
		lblPassword.setLabelFor(passwordFieldPassword);
		passwordFieldPassword.setBounds(99, 87, 136, 20);
		getContentPane().add(passwordFieldPassword);

		JLabel lblMinecraftVersion = new JLabel("Minecraft Version");
		lblMinecraftVersion.setBounds(10, 130, 120, 14);
		getContentPane().add(lblMinecraftVersion);

		GetMinecraftVersions.setup();

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(GetMinecraftVersions.getStrings()));
		comboBox.setBounds(140, 127, 136, 20);
		comboBox.setSelectedItem((String) profile.get("Minecraft Version"));
		getContentPane().add(comboBox);

		btnCreateProfile = new JButton("Save Profile");
		btnCreateProfile.setBounds(10, 227, 120, 23);
		btnCreateProfile.addActionListener(this);
		getContentPane().add(btnCreateProfile);

		JLabel lblGameDirectory = new JLabel("Game Directory");
		lblGameDirectory.setBounds(10, 170, 120, 14);
		getContentPane().add(lblGameDirectory);

		textFieldGameDir = new JTextField();
		textFieldGameDir.setText((String) profile.get("Game Directory"));
		lblGameDirectory.setLabelFor(textFieldGameDir);
		textFieldGameDir.setBounds(115, 167, 200, 20);
		getContentPane().add(textFieldGameDir);
		textFieldGameDir.setColumns(10);

		JLabel lblRamOptions = new JLabel("RAM Init Heap Options");
		lblRamOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblRamOptions.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRamOptions.setBounds(314, 10, 136, 14);
		getContentPane().add(lblRamOptions);

		initRamBtnGrp = new ButtonGroup();
		maxRamBtnGrp = new ButtonGroup();


		rdbtnInit256Megabytes = new JRadioButton("256 Megabytes");
		rdbtnInit256Megabytes.setBounds(324, 31, 109, 23);
		rdbtnInit256Megabytes.setActionCommand("256M");
		rdbtnInit256Megabytes.addItemListener(this);
		initRamBtnGrp.add(rdbtnInit256Megabytes);
		getContentPane().add(rdbtnInit256Megabytes);

		rdbtnInit512Megabytes = new JRadioButton("512 Megabytes");
		rdbtnInit512Megabytes.setBounds(324, 57, 109, 23);
		rdbtnInit512Megabytes.setActionCommand("512M");
		initRamBtnGrp.add(rdbtnInit512Megabytes);
		rdbtnInit512Megabytes.setSelected(true);
		getContentPane().add(rdbtnInit512Megabytes);

		rdbtnInit1Gigabyte = new JRadioButton("1 Gigabyte");
		rdbtnInit1Gigabyte.setBounds(324, 86, 109, 23);
		rdbtnInit1Gigabyte.setActionCommand("1G");
		initRamBtnGrp.add(rdbtnInit1Gigabyte);
		getContentPane().add(rdbtnInit1Gigabyte);

		rdbtnInit1_5Gigabytes = new JRadioButton("1.5 Gigabytes");
		rdbtnInit1_5Gigabytes.setBounds(324, 112, 109, 23);
		rdbtnInit1_5Gigabytes.setActionCommand("1536M");
		initRamBtnGrp.add(rdbtnInit1_5Gigabytes);
		getContentPane().add(rdbtnInit1_5Gigabytes);

		rdbtnInit2Gigabytes = new JRadioButton("2 Gigabytes");
		rdbtnInit2Gigabytes.setBounds(324, 138, 109, 23);
		rdbtnInit2Gigabytes.setActionCommand("2G");
		initRamBtnGrp.add(rdbtnInit2Gigabytes);
		getContentPane().add(rdbtnInit2Gigabytes);

		rdbtnInitCustom = new JRadioButton("Custom");
		rdbtnInitCustom.setBounds(324, 161, 70, 23);
		rdbtnInitCustom.setActionCommand("Custom");
		initRamBtnGrp.add(rdbtnInitCustom);
		getContentPane().add(rdbtnInitCustom);

		initCustomRam = new JTextField();
		initCustomRam.setText("3G");
		initCustomRam.setBounds(400, 162, 50, 20);
		getContentPane().add(initCustomRam);
		initCustomRam.setColumns(10);

		maxCustomRam = new JTextField();
		maxCustomRam.setText("3G");
		maxCustomRam.setColumns(10);
		maxCustomRam.setBounds(566, 167, 50, 20);
		getContentPane().add(maxCustomRam);

		JLabel lblCustomRamFormat = new JLabel("Custom RAM Format");
		lblCustomRamFormat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCustomRamFormat.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomRamFormat.setBounds(324, 191, 126, 14);
		getContentPane().add(lblCustomRamFormat);

		JTextArea txtrMustBeA = new JTextArea();
		txtrMustBeA.setBackground(UIManager.getColor("Button.background"));
		txtrMustBeA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrMustBeA.setLineWrap(true);
		txtrMustBeA.setText("Must be a valid number and must end with either a \"M\" or a \"G\"");
		txtrMustBeA.setBounds(324, 216, 183, 34);
		getContentPane().add(txtrMustBeA);

		rdbtnMax256Megabytes = new JRadioButton("256 Megabytes");
		rdbtnMax256Megabytes.setBounds(490, 31, 109, 23);
		rdbtnMax256Megabytes.setActionCommand("256M");
		maxRamBtnGrp.add(rdbtnMax256Megabytes);
		getContentPane().add(rdbtnMax256Megabytes);

		rdbtnMax512Megabytes = new JRadioButton("512 Megabytes");
		rdbtnMax512Megabytes.setBounds(490, 57, 109, 23);
		rdbtnMax512Megabytes.setActionCommand("512M");
		maxRamBtnGrp.add(rdbtnMax512Megabytes);
		getContentPane().add(rdbtnMax512Megabytes);

		rdbtnMax1Gigabyte = new JRadioButton("1 Gigabyte");
		rdbtnMax1Gigabyte.setBounds(490, 86, 109, 23);
		rdbtnMax1Gigabyte.setActionCommand("1G");
		rdbtnMax1Gigabyte.setSelected(true);
		maxRamBtnGrp.add(rdbtnMax1Gigabyte);
		getContentPane().add(rdbtnMax1Gigabyte);

		rdbtnMax1_5Gigabytes = new JRadioButton("1.5 Gigabytes");
		rdbtnMax1_5Gigabytes.setBounds(490, 112, 109, 23);
		rdbtnMax1_5Gigabytes.setActionCommand("1536M");
		maxRamBtnGrp.add(rdbtnMax1_5Gigabytes);
		getContentPane().add(rdbtnMax1_5Gigabytes);

		rdbtnMax2Gigabytes = new JRadioButton("2 Gigabytes");
		rdbtnMax2Gigabytes.setBounds(490, 138, 109, 23);
		rdbtnMax2Gigabytes.setActionCommand("2G");
		maxRamBtnGrp.add(rdbtnMax2Gigabytes);
		getContentPane().add(rdbtnMax2Gigabytes);

		rdbtnMaxCustom = new JRadioButton("Custom");
		rdbtnMaxCustom.setBounds(490, 166, 70, 23);
		rdbtnMaxCustom.setActionCommand("Custom");
		maxRamBtnGrp.add(rdbtnMaxCustom);
		getContentPane().add(rdbtnMaxCustom);

		JLabel lblRamMaxHeap = new JLabel("RAM Max Heap Options");
		lblRamMaxHeap.setHorizontalAlignment(SwingConstants.CENTER);
		lblRamMaxHeap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRamMaxHeap.setBounds(490, 10, 134, 14);
		getContentPane().add(lblRamMaxHeap);


		for (Enumeration<AbstractButton> buttons = initRamBtnGrp.getElements(); buttons.hasMoreElements();) {
			JRadioButton radioBtn = (JRadioButton) buttons.nextElement();

			radioBtn.addItemListener(this);
		}
		for (Enumeration<AbstractButton> buttons = maxRamBtnGrp.getElements(); buttons.hasMoreElements();) {
			JRadioButton radioBtn = (JRadioButton) buttons.nextElement();

			radioBtn.addItemListener(this);
		}
		String initRam = (String) profile.get("Init RAM Heap");
		String maxRam = (String) profile.get("Max RAM Heap");
		if (initRam != null) {
			switch (initRam) {
			default:
				this.rdbtnInitCustom.setSelected(true);
				this.initCustomRam.setText(initRam);
				break;
			case "256M":
				this.rdbtnInit256Megabytes.setSelected(true);
				break;
			case "512M":
				this.rdbtnInit512Megabytes.setSelected(true);
				break;
			case "1G":
				this.rdbtnInit1Gigabyte.setSelected(true);
				break;
			case "1536M":
				this.rdbtnInit1_5Gigabytes.setSelected(true);
				break;
			case "2G":
				this.rdbtnInit2Gigabytes.setSelected(true);
				break;
			}

		} else {
			this.rdbtnInit512Megabytes.setSelected(true);
		}
		if (maxRam != null) {
			System.out.println("Max RAM Heap: " + maxRam);
			switch (maxRam) {
			default:
				this.rdbtnMaxCustom.setSelected(true);
				this.maxCustomRam.setText(initRam);
				break;
			case "256M":
				this.rdbtnMax256Megabytes.setSelected(true);
				break;
			case "512M":
				this.rdbtnMax512Megabytes.setSelected(true);
				break;
			case "1G":
				this.rdbtnMax1Gigabyte.setSelected(true);
				break;
			case "1536M":
				this.rdbtnMax1_5Gigabytes.setSelected(true);
				break;
			case "2G":
				this.rdbtnMax2Gigabytes.setSelected(true);
				break;
			}
		} else {
			this.rdbtnMax1Gigabyte.setSelected(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnCreateProfile) {
			this.saveProfile();
			this.dispose();
		}
	}

	@SuppressWarnings("unchecked")
	private void saveProfile() {
		JSONObject profileJSON = new JSONObject();
		profileJSON.put("Profile Name", this.textFieldProfileName.getText());
		profileJSON.put("Username", this.textFieldUsername.getText());
		profileJSON.put("Password", new String(this.passwordFieldPassword.getPassword()));
		if (!(this.comboBox.getSelectedItem().equals("<Latest Release>") || this.comboBox.getSelectedItem().equals("<Latest Snapshot>"))) {
			profileJSON.put("Minecraft Version", GetMinecraftVersions.stripVersionPrefix(this.comboBox.getSelectedItem().toString()));
		} else {
			profileJSON.put("Minecraft Version", this.comboBox.getSelectedItem().toString());
		}
		profileJSON.put("Game Directory", this.textFieldGameDir.getText());
		switch (initRamBtnGrp.getSelection().getActionCommand()) {
		default:
			break;
		case "256M":
			profileJSON.put("Init RAM Heap", "256M");
			break;
		case "512M":
			profileJSON.put("Init RAM Heap", "512M");
			break;
		case "1G":
			profileJSON.put("Init RAM Heap", "1G");
			break;
		case "1536M":
			profileJSON.put("Init RAM Heap", "1536M");
			break;
		case "2G":
			profileJSON.put("Init RAM Heap", "2G");
			break;
		case "Custom":
			if (this.initCustomRam.getText().endsWith("G") || this.initCustomRam.getText().endsWith("M")) {
				profileJSON.put("Init RAM Heap", this.initCustomRam.getText());
			}
			break;
		}
		switch (maxRamBtnGrp.getSelection().getActionCommand()) {
		default:
			break;
		case "256M":
			profileJSON.put("Max RAM Heap", "256M");
			break;
		case "512M":
			profileJSON.put("Max RAM Heap", "512M");
			break;
		case "1G":
			profileJSON.put("Max RAM Heap", "1G");
			break;
		case "1536M":
			profileJSON.put("Max RAM Heap", "1536M");
			break;
		case "2G":
			profileJSON.put("Max RAM Heap", "2G");
			break;
		case "Custom":
			if (this.maxCustomRam.getText().endsWith("G") || this.maxCustomRam.getText().endsWith("M")) {
				profileJSON.put("Max RAM Heap", this.initCustomRam.getText());
			}
			break;
		}
		System.out.println(profileJSON.toJSONString());
		LauncherConfig.addProfileFromJSON(profileJSON);
	}

	@Override
	public void itemStateChanged(ItemEvent itemEVT) {
		if (itemEVT.getStateChange() == ItemEvent.SELECTED) {
			if (itemEVT.getItem() == this.rdbtnInit512Megabytes) {
				this.rdbtnMax256Megabytes.setEnabled(false);
				if (this.rdbtnMax256Megabytes.isSelected()) {
					this.rdbtnMax256Megabytes.setSelected(false);
					this.rdbtnMax512Megabytes.setSelected(true);
				}
			}
			if (itemEVT.getItem() == this.rdbtnInit1Gigabyte) {
				this.rdbtnMax512Megabytes.setEnabled(false);
				this.rdbtnMax256Megabytes.setEnabled(false);
				if (this.rdbtnMax256Megabytes.isSelected()) {
					this.rdbtnMax256Megabytes.setSelected(false);
					this.rdbtnMax1Gigabyte.setSelected(true);
				}
				if (this.rdbtnMax512Megabytes.isSelected()) {
					this.rdbtnMax512Megabytes.setSelected(false);
					this.rdbtnMax1Gigabyte.setSelected(true);
				}
			}
			if (itemEVT.getItem() == this.rdbtnInit1_5Gigabytes) {
				this.rdbtnMax256Megabytes.setEnabled(false);
				this.rdbtnMax512Megabytes.setEnabled(false);
				this.rdbtnMax1Gigabyte.setEnabled(false);
				if (this.rdbtnMax256Megabytes.isSelected()) {
					this.rdbtnMax256Megabytes.setSelected(false);
					this.rdbtnMax1_5Gigabytes.setSelected(true);
				}
				if (this.rdbtnMax512Megabytes.isSelected()) {
					this.rdbtnMax512Megabytes.setSelected(false);
					this.rdbtnMax1_5Gigabytes.setSelected(true);
				}
				if (this.rdbtnMax1Gigabyte.isSelected()) {
					this.rdbtnMax1Gigabyte.setSelected(false);
					this.rdbtnMax1_5Gigabytes.setSelected(true);
				}
			}
			if (itemEVT.getItem() == this.rdbtnInit2Gigabytes) {
				this.rdbtnMax256Megabytes.setEnabled(false);
				this.rdbtnMax512Megabytes.setEnabled(false);
				this.rdbtnMax1Gigabyte.setEnabled(false);
				this.rdbtnMax1_5Gigabytes.setEnabled(false);
				if (this.rdbtnMax256Megabytes.isSelected()) {
					this.rdbtnMax256Megabytes.setSelected(false);
					this.rdbtnMax2Gigabytes.setSelected(true);
				}
				if (this.rdbtnMax512Megabytes.isSelected()) {
					this.rdbtnMax512Megabytes.setSelected(false);
					this.rdbtnMax2Gigabytes.setSelected(true);
				}
				if (this.rdbtnMax1Gigabyte.isSelected()) {
					this.rdbtnMax1Gigabyte.setSelected(false);
					this.rdbtnMax2Gigabytes.setSelected(true);
				}
				if (this.rdbtnMax1_5Gigabytes.isSelected()) {
					this.rdbtnMax1_5Gigabytes.setSelected(false);
					this.rdbtnMax2Gigabytes.setSelected(true);
				}
			}
		}
		if (itemEVT.getStateChange() == ItemEvent.DESELECTED) {
			if (itemEVT.getItem() == this.rdbtnInit512Megabytes) {
				this.rdbtnMax256Megabytes.setEnabled(true);
			}
			if (itemEVT.getItem() == this.rdbtnInit1Gigabyte) {
				this.rdbtnMax512Megabytes.setEnabled(true);
				this.rdbtnMax256Megabytes.setEnabled(true);
			}
			if (itemEVT.getItem() == this.rdbtnInit1_5Gigabytes) {
				this.rdbtnMax256Megabytes.setEnabled(true);
				this.rdbtnMax512Megabytes.setEnabled(true);
				this.rdbtnMax1Gigabyte.setEnabled(true);
			}
			if (itemEVT.getItem() == this.rdbtnInit2Gigabytes) {
				this.rdbtnMax256Megabytes.setEnabled(true);
				this.rdbtnMax512Megabytes.setEnabled(true);
				this.rdbtnMax1Gigabyte.setEnabled(true);
				this.rdbtnMax1_5Gigabytes.setEnabled(true);
			}
		}
	}
}
