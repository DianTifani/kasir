package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import model.data.UserData;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JLabel lblPassword;
	private JLabel lblTitle;
	private JLabel lblLoginFailed;
	private JButton btnLogin;
	
	private Dashboard viewDashboard;
	private User userModel;

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
			
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 250, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblTitle = new JLabel("Login");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		JLabel lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 3;
		contentPane.add(lblUsername, gbc_lblUsername);
		
		tfUsername = new JTextField();
		GridBagConstraints gbc_tfUsername = new GridBagConstraints();
		gbc_tfUsername.insets = new Insets(0, 0, 5, 5);
		gbc_tfUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfUsername.gridx = 1;
		gbc_tfUsername.gridy = 4;
		contentPane.add(tfUsername, gbc_tfUsername);
		tfUsername.setColumns(10);
		
		lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 5;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		tfPassword = new JPasswordField();
		GridBagConstraints gbc_tfPassword = new GridBagConstraints();
		gbc_tfPassword.insets = new Insets(0, 0, 5, 5);
		gbc_tfPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPassword.gridx = 1;
		gbc_tfPassword.gridy = 6;
		contentPane.add(tfPassword, gbc_tfPassword);
		
		Login self = this;
		
		lblLoginFailed = new JLabel("  ");
		lblLoginFailed.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblLoginFailed.setForeground(Color.RED);
		GridBagConstraints gbc_lblLoginFailed = new GridBagConstraints();
		gbc_lblLoginFailed.anchor = GridBagConstraints.WEST;
		gbc_lblLoginFailed.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoginFailed.gridx = 1;
		gbc_lblLoginFailed.gridy = 8;
		contentPane.add(lblLoginFailed, gbc_lblLoginFailed);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = tfUsername.getText();
				String password = new String(tfPassword.getPassword());
				
				if (userModel != null) {
					if (username.length() > 0 && password.length() > 0) {					
						
						if (userModel.cekUser(username, password)) {
							UserData sessionUser = new UserData(userModel, username, password);
							viewDashboard.setSessionUser(sessionUser);
							
							self.setVisible(false);
							viewDashboard.back();
						} else {
							
							lblLoginFailed.setText("Username dan password yang anda masukkan salah!");
							
						}
						
					}
				}
			}
		});
		btnLogin.setBackground(SystemColor.control);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.EAST;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 7;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		this.userModel = null;
	}
	
	public Login(Dashboard viewDashboard) {
		this();
		this.setDashboard(viewDashboard);
		this.userModel = viewDashboard.getUserModel();
	}
	
	public void setDashboard(Dashboard viewDashboard) {
		this.viewDashboard = viewDashboard;
	}
	
	public void viewShow() {
		this.setVisible(true);
		this.viewDashboard.getFrame().setVisible(false);
	}
}
