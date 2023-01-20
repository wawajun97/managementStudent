import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Insert extends JFrame{
	public Insert() {
		setSize(280, 200);
        setTitle("학생추가");
        
        setLayout(new FlowLayout());
        
        JLabel label1 = new JLabel("이름");
        JTextField text1 = new JTextField(20);
        JLabel label2 = new JLabel("학과");
        JTextField text2 = new JTextField(20);
        JLabel label3 = new JLabel("학번");
        JTextField text3 = new JTextField(20);
        JButton button1 = new JButton("확인");
        
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(text1.getText().isEmpty()|| text2.getText().isEmpty() || text3.getText().isEmpty()) {
					
				}
				else {
					try {
						String name = text1.getText();
						String major = text2.getText();
						String id = text3.getText();
						DB db = new DB();
						Statement st = db.DbCon();
						st.executeUpdate("INSERT OR IGNORE INTO Student (Name, Major, Id) VALUES (\"" + name + "\", \"" + major + "\", \"" + id + "\")");
						st.executeUpdate("INSERT OR IGNORE INTO Score (Id, A, B, C, D, Ave) VALUES (\"" + id + "\", NULL, NULL, NULL, NULL, NULL)");
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				dispose();
			}
		});

        this.add(label1);
        this.add(text1);
        this.add(label2);
        this.add(text2);
        this.add(label3);
        this.add(text3);
        this.add(button1);
        setVisible(true);
	}
}

class Search extends JFrame{
	String[] s = {"이름", "학과", "학번"};
    JComboBox<String> strCombo = new JComboBox<>(s);
    JTextField text1 = new JTextField(20);
    JTextArea area = new JTextArea(30,40);

	public void print(String type) {
		String sqlType = "";
		if(type.equals("이름")) {
			sqlType = "Name";
		}
		else if(type.equals("학과")) {
			sqlType = "Major";
		}
		else if(type.equals("학번")) {
			sqlType = "Id";
		}
		DB db = new DB();
		Statement st = db.DbCon();
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM Student WHERE " + sqlType + " = \"" + text1.getText() + "\"");
			while(rs.next()) {
				String id = rs.getString("Id");
				String name = rs.getString("Name");
				String major = rs.getString("Major");
				area.append("이름: " + name + ", 학과: " + major + ", 학번: " + id +"\n");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Search() {
		setSize(500, 400);
        setTitle("학생검색");
        
        setLayout(new FlowLayout());
        JButton button1 = new JButton("확인");

        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!area.getText().isEmpty()) {
					area.setText("");
				}
				if(text1.getText().isEmpty()) {
					
				}
				else {
					if(strCombo.getSelectedItem().toString().equals("이름")) {
						print("이름");
					}
					else if(strCombo.getSelectedItem().toString().equals("학과")) {
						print("학과");
					}
					else if(strCombo.getSelectedItem().toString().equals("학번")) {
						print("학번");
					}
					text1.setText("");
				}
			}
		});
        add(strCombo);
        add(text1);
        add(button1);
        add(area);
        setVisible(true);
	}
}

class Modify extends JFrame {
	String[] s = {"이름", "학과", "학번"};

	public Modify() {
		setSize(500, 400);
        setTitle("학생수정");
        
        setLayout(new FlowLayout());
        JComboBox<String> strCombo = new JComboBox<>(s);
        JLabel label1 = new JLabel("학생 ID");
        JTextField text1 = new JTextField(10);
        JLabel label2 = new JLabel("변경후");
        JTextField text2 = new JTextField(10);
        JButton button1 = new JButton("확인");

        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(text1.getText().isEmpty() || text2.getText().isEmpty()) {
					
				}
				else {
					String sqlType = "";
					if(strCombo.getSelectedItem().toString().equals("이름")) {
						sqlType = "Name";
					}
					else if(strCombo.getSelectedItem().toString().equals("학과")) {
						sqlType = "Major";
					}
					else if(strCombo.getSelectedItem().toString().equals("학번")) {
						sqlType = "Id";
					}
					DB db = new DB();
					Statement st = db.DbCon();
					try {
						st.executeUpdate("UPDATE Student SET " + sqlType + " = \"" + text2.getText() + "\" WHERE Id = \"" + 
					                      text1.getText() + "\"");
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					text1.setText("");
					text2.setText("");
				}
				dispose();
			}
		});
        
        add(label1);
        add(text1);
        add(strCombo);
        add(label2);
        add(text2);
        add(button1);
       
        setVisible(true);
	}
}

class Delete extends JFrame {
	JTextField text1 = new JTextField(20);
	public Delete() {
		setSize(500, 400);
        setTitle("학생삭제");
        
        setLayout(new FlowLayout());
        JLabel label1 = new JLabel("학번");
        JButton button1 = new JButton("확인");
        
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sdelete();
				dispose();
			}
		});
        add(label1);
        add(text1);
        add(button1);
        setVisible(true);
	}
	
	int target = -1;
	String line;
	void Sdelete() {
		DB db = new DB();
		Statement st = db.DbCon();
		try {
			st.executeUpdate("DELETE FROM Student WHERE Id = \"" + text1.getText() + "\"");
			st.executeUpdate("DELETE FROM Score WHERE Id = \"" + text1.getText() + "\"");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
