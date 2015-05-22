import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class LeagueWindow {
	
	private WinRate winRate;
	private String adminUsername;
	private String adminPassword;
	
	private Values[] matchInputs;

	private JFrame mainFrame;
	
	private JTabbedPane tab;
	
	private JPanel userPanel;
	private JPanel adminPanel;
	private JPanel displayPane;
	
	private JLabel titleLabel;
	
	private JTextField matchbyIDTextField;
	private JTextField matchIDbyChampionTextField;
	private JTextField userNameTextField;
	private JTextField passwordTextField;
	private JTextField T1I;
	private JTextField T2I;
	
	private JTable displayTable;
	
	private JDialog diag;
	
	private JButton matchbyIDButton;
	private JButton matchIDbyChampionButton;
	
	private LeagueConnection leagueConn;

	public LeagueWindow(LeagueConnection leagueConn) {
		this.leagueConn = leagueConn;
		this.winRate = new WinRate();
		this.matchInputs = new Values[126];
		prepareGUI();
		
	}

	public void prepareGUI() {
		this.mainFrame = new JFrame("League Genei");
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		this.mainFrame.setSize(xSize, ySize);
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					leagueConn.closeConnection();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("Connection Closed");
				e.getWindow().dispose();
			}
		});
		

		
		this.userPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		this.userPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel labelPane = new JPanel();
		labelPane.setBackground(Color.BLUE);
		labelPane.setOpaque(true);
		JPanel getMatchPane = new JPanel();
		getMatchPane.setBackground(Color.GREEN);
		getMatchPane.setOpaque(true);
		JPanel getWinRatePane = new JPanel();
		getWinRatePane.setBackground(Color.RED);
		getWinRatePane.setOpaque(true);
		this.displayPane = new JPanel();
		displayPane.setBackground(Color.YELLOW);
		displayPane.setOpaque(true);
		
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        
        this.userPanel.add(labelPane, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.4;

        userPanel.add(getMatchPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.4;

        userPanel.add(getWinRatePane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.5;

        userPanel.add(displayPane, gbc);
		
		this.titleLabel = new JLabel("League Genie", JLabel.CENTER);
		this.titleLabel.setFont(new Font("Courier New", Font.BOLD, 24));
		this.titleLabel.setForeground(Color.WHITE);
		labelPane.add(titleLabel);
		
	
		this.matchbyIDTextField = new JTextField(7);
		this.matchIDbyChampionTextField = new JTextField(7);
		
		this.matchbyIDButton = new JButton("Match by ID");
		this.matchbyIDButton.addActionListener(new MatchbyID());
		this.matchIDbyChampionButton = new JButton("MatchID by Champion");
		this.matchIDbyChampionButton.addActionListener(new MatchIDbyChampion());
		
		getMatchPane.setLayout(new GridLayout(2,2));
		getMatchPane.add(matchIDbyChampionTextField);
		getMatchPane.add(matchIDbyChampionButton);
		getMatchPane.add(matchbyIDTextField);
		getMatchPane.add(matchbyIDButton);
		
		getWinRatePane.setLayout(new GridBagLayout());
		
		JLabel T1Label = new JLabel("Team1");
		JLabel T2Label = new JLabel("Team1");
		JLabel T1CLabel = new JLabel("Champions(required)");
		JLabel T2CLabel = new JLabel("Champions(required)");
		JLabel T1ILabel = new JLabel("Items(optional)");
		JLabel T2ILabel = new JLabel("Items(optional)");
		this.T1I = new JTextField();
		this.T2I = new JTextField();
	
		JButton addT1I = new JButton("Add Item");
		addT1I.addActionListener(new AddT1Item());
		JButton addT2I = new JButton("Add Item");
		addT2I.addActionListener(new AddT2Item());
		JButton getWinRate = new JButton("Get Win Rate");
		getWinRate.addActionListener(new GetWinRate());
		
		gbc.insets = new Insets(3,5,5,3);
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 6;
	    gbc.weightx = 1;
	    gbc.weighty = 1;
	    getWinRatePane.add(T1Label, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(T1CLabel, gbc);
	    
		for(int i = 0;i<5;i++){
			final int j = i;
			final JTextField T1C = new JTextField();
			T1C.getDocument().addDocumentListener(new DocumentListener(){

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					winRate.T1Champions[j] = T1C.getText();
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}});
			gbc.gridx = i+1;
		    gbc.gridy = 1;
		    gbc.gridwidth = 1;
		    getWinRatePane.add(T1C, gbc);
		}
		
		gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(T1ILabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(T1I, gbc);
	    
	    gbc.gridx = 2;
	    gbc.gridy = 2;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(addT1I, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 6;
	    getWinRatePane.add(T2Label, gbc);
	    
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(T2CLabel, gbc);
	    
	    for(int i = 0;i<5;i++){
			final int j = i;
			final JTextField T2C = new JTextField();
			T2C.getDocument().addDocumentListener(new DocumentListener(){

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					winRate.T2Champions[j] = T2C.getText();
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
				}});
			gbc.gridx = i+1;
		    gbc.gridy = 4;
		    gbc.gridwidth = 1;
		    getWinRatePane.add(T2C, gbc);
		}
	    
	    gbc.gridx = 0;
	    gbc.gridy = 5;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(T2ILabel, gbc);
	    
	    gbc.gridx = 1;
	    gbc.gridy = 5;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(T2I, gbc);
	    
	    gbc.gridx = 2;
	    gbc.gridy = 5;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(addT2I, gbc);
	    
	    gbc.gridx = 5;
	    gbc.gridy = 6;
	    gbc.gridwidth = 1;
	    getWinRatePane.add(getWinRate, gbc);
		
		
		this.tab = new JTabbedPane();
		this.mainFrame.add(this.tab);
		
		this.tab.addTab("User", this.userPanel);
		this.tab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {

			    JTabbedPane tabbedPane = (JTabbedPane)evt.getSource();

			    int tabIndex = tabbedPane.getSelectedIndex();
			    if(tabIndex==1){
			    	promptForInfoWithWait();
			    	

					//prepareAdimTab();
			    }

			}

		    });

		this.adminPanel = new JPanel();
		this.adminPanel.setLayout(new BoxLayout(this.adminPanel, BoxLayout.PAGE_AXIS));
		this.tab.add("Admin", this.adminPanel);

		this.mainFrame.setVisible(true);
	}
	

	private void addComponent(JDialog diag, Component comp,
			GridBagLayout layout, GridBagConstraints cons) {
		layout.setConstraints(comp, cons);
		diag.add(comp);
	}

	
	private void promptForInfoWithWait() {
		this.diag = new JDialog();
		diag.setTitle("Admin");
		diag.setResizable(false);
		diag.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		diag.addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(@SuppressWarnings("unused") WindowEvent we) {
				diag.dispose();
			}
		});

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(3, 5, 5, 3);
		diag.setLayout(layout);

		JLabel promptLabel = new JLabel("Enter Username and Password");
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 3;
		addComponent(diag, promptLabel, layout, cons);

		JLabel userNameLabel = new JLabel("Username:");
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth = 1;
		addComponent(diag, userNameLabel, layout, cons);

		this.userNameTextField = new JTextField();
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridwidth = 2;
		cons.fill = GridBagConstraints.HORIZONTAL;
		addComponent(diag, userNameTextField, layout, cons);

		JLabel passwordLabel = new JLabel("Password:");
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth = 1;
		cons.fill = GridBagConstraints.NONE;
		addComponent(diag, passwordLabel, layout, cons);

		this.passwordTextField = new JPasswordField();
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridwidth = 2;
		cons.fill = GridBagConstraints.HORIZONTAL;
		addComponent(diag, passwordTextField, layout, cons);

		JButton cancelButton = new JButton("Cancel");
		cons.gridx = 1;
		cons.gridy = 3;
		cons.gridwidth = 1;
		cancelButton.addActionListener(new Cancel());
		addComponent(diag, cancelButton, layout, cons);

		JButton okButton = new JButton("OK");
		cons.gridx = 2;
		cons.gridy = 3;
		cons.gridwidth = 1;
		addComponent(diag, okButton, layout, cons);
		okButton.addActionListener(new OK());
		diag.getRootPane().setDefaultButton(okButton);

		diag.pack();
		diag.setVisible(true);
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	public void prepareAdimTab() {
		
		JButton enterButton = new JButton("Enter Game");
		enterButton.addActionListener(new EnterMatch());
		
		JPanel matchPanel = new JPanel();
		JPanel team1Panel = new JPanel();
		JPanel team2Panel = new JPanel();
		JPanel playersIn1Panel = new JPanel();
		JPanel playersIn2Panel = new JPanel();
		
		JLabel matchLabel = new JLabel("Match");
		JLabel team1Label = new JLabel("Team1");
		JLabel team2Label = new JLabel("Team2");
		
		final JTextField matchIDTextField = new JTextField(5);//0
		
		matchIDTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				Values v = new Values();
				v.intInputs = Integer.parseInt( matchIDTextField.getText());
				matchInputs[0] = v;
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
			}
			
		});
		
		final JTextField timeTextField = new JTextField(20);//1
		timeTextField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				Values v = new Values();
				v.intInputs = Integer.parseInt( timeTextField.getText());
				matchInputs[1] = v;
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
			}
			
		});
		
		matchPanel.add(matchLabel);
		matchPanel.add(matchIDTextField);
		matchPanel.add(timeTextField);
		matchPanel.add(enterButton);
		team1Panel.add(team1Label);
		addTeamTextFields(team1Panel, 2);//2--8
		team2Panel.add(team2Label);
		addTeamTextFields(team2Panel, 9);//9--15
		
		playersIn1Panel.setLayout(new GridLayout(5,11));
		addPlayers(playersIn1Panel, 16);//16--70
		playersIn2Panel.setLayout(new GridLayout(5,11));
		addPlayers(playersIn2Panel, 71);//71--125
		
		this.adminPanel.add(matchPanel);
		this.adminPanel.add(team1Panel);
		this.adminPanel.add(playersIn1Panel);
		this.adminPanel.add(team2Panel);
		this.adminPanel.add(playersIn2Panel);
	}
	
	private void addPlayers(JPanel p, int start) {
		for (int i=0; i<5; i++) {
			for (int j=0; j<11; j++) {
				final JTextField tf = new JTextField(7);
				final int k = start + i*11 +j;
				
				tf.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent arg0) {
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {
						Values v = new Values();
						try{
							v.intInputs = Integer.parseInt(tf.getText());
						}catch(Exception e){
							v.stringInputs = tf.getText();
						}
						matchInputs[k] = v;
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {
					}
					
				});
				
				p.add(tf);
			}
		}
	}
	
	private void addTeamTextFields(JPanel p, int start) {
		for(int i=0; i<7;i++) {
			final JTextField tf = new JTextField(7);
			final int j = i+start;
			tf.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void changedUpdate(DocumentEvent arg0) {
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					Values v = new Values();
					v.intInputs = Integer.parseInt( tf.getText());
					matchInputs[j] = v;
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
				}
				
			});
			p.add(tf);
		}
	}
	
	private class AddT1Item implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			winRate.T1Items.add(T1I.getText());
			T1I.setText("");
		}
		
	}
	
	private class AddT2Item implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			winRate.T2Items.add(T2I.getText());
			T2I.setText("");
		}
		
	}
	
	private class GetWinRate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JLabel l = new JLabel(winRate.calculateWinRate(leagueConn.conn));
			l.setFont(new Font("Courier New", Font.BOLD, 24));
			displayPane.removeAll();
			displayPane.add(l);
			displayPane.revalidate();
		}
		
	}
	
	private class Cancel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			diag.dispose();
		}
		
	}
	
	private class OK implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			adminUsername = userNameTextField.getText();
			adminPassword = passwordTextField.getText();
			int r = -1;
			
			try {
				CallableStatement cstmt = LeagueProcedure.createCallabel2(
						leagueConn.conn, LeagueProcedure.AUTHENTICATE_ADMIN, adminUsername+adminPassword);
				r = cstmt.getInt(1);
				if(r==0){
					prepareAdimTab();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			diag.dispose();
		}
		
	}
	
	private class EnterMatch implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
//			for (int i=0;i<matchInputs.length;i++) {
//				System.out.print(i);
//				if(matchInputs[i].stringInputs!=null){
//					System.out.println(matchInputs[i].stringInputs);
//				}else{
//					System.out.println(matchInputs[i].intInputs);
//				}
//			}
			try {
				CallableStatement cstmt = LeagueProcedure.createCallabel(
						leagueConn.conn, LeagueProcedure.GENERATE_GAME,
						matchInputs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private class MatchIDbyChampion implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String s = matchIDbyChampionTextField.getText();
			System.out.println(s);
			try {
				CallableStatement cstmt = LeagueProcedure.createCallabel(
						leagueConn.conn, LeagueProcedure.GET_MATCHES_OF_CHAMPION,
						s);
				ResultSet rs = cstmt.getResultSet();
				System.out.println("Meowuu");
				displayTable = new JTable(buildTableModel(rs));
				displayPane.removeAll();
				displayPane.add(displayTable);
				displayPane.revalidate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class MatchbyID implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int id = Integer.parseInt(matchbyIDTextField.getText());
			try {
				CallableStatement cstmt = LeagueProcedure.createCallabel(
						leagueConn.conn, LeagueProcedure.GET_POST_GAME_STATS,
						id);
				ResultSet rs = cstmt.getResultSet();
				System.out.println("Meowu");
				displayTable = new JTable(buildTableModel(rs));
				displayPane.removeAll();
				displayPane.add(displayTable);
				displayPane.revalidate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class GetAvgUserLevle implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				CallableStatement cstmt = LeagueProcedure.createCallabel(
						leagueConn.conn, LeagueProcedure.GET_AVG_USER_LEVEL,
						12345);

				ResultSet rs = cstmt.getResultSet();
				System.out.println("Meowuu");
				displayTable = new JTable(buildTableModel(rs));
				userPanel.add(displayTable);
				userPanel.revalidate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	

	private class GetPlayerItemsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				CallableStatement cstmt = LeagueProcedure.createCallabel(
						leagueConn.conn, LeagueProcedure.GET_PLAYER_ITEMS,
						12345);

				ResultSet rs = cstmt.getResultSet();
				System.out.println("Meowuu");
				displayTable = new JTable(buildTableModel(rs));
				userPanel.add(displayTable);
				userPanel.revalidate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static void printTable(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int c = rsmd.getColumnCount();
		int r = 0;
		System.out.print("Column: ");
		for (int i = 1; i <= c; i++) {
			System.out.print(rsmd.getColumnLabel(i));
			if (i != c)
				System.out.print(" , ");
		}
		System.out.println("");
		while (rs.next()) {
			r++;
			System.out.print("Row: " + r + ": ");
			for (int i = 1; i <= c; i++) {
				System.out.print(rs.getString(i));

				Object columnObject = rs.getObject(i);

				if (i != c)
					System.out.print(" , ");
			}
			System.out.println("");
		}
	}

}
