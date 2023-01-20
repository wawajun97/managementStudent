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

class ScoreInsert extends JFrame{
	public ScoreInsert() {
		setSize(260, 300);
        setTitle("학점추가");
        
        setLayout(new FlowLayout());
        
        JLabel label = new JLabel("학번");
        JTextField text = new JTextField(20);
        JLabel label1 = new JLabel("      A");
        JTextField text1 = new JTextField(20);
        JLabel label2 = new JLabel("      B");
        JTextField text2 = new JTextField(20);
        JLabel label3 = new JLabel("      C");
        JTextField text3 = new JTextField(20);
        JLabel label4 = new JLabel("      D");
        JTextField text4 = new JTextField(20);
        JButton button1 = new JButton("확인");
        
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(text1.getText().isEmpty()|| text2.getText().isEmpty() || text3.getText().isEmpty() || text4.getText().isEmpty()) {
					
				}
				else {
					try {
						String Id = text.getText();
						String A = text1.getText();
						String B = text2.getText();
						String C = text3.getText();
						String D = text4.getText();
						double ave = (Double.parseDouble(A) + Double.parseDouble(B) + Double.parseDouble(C) + Double.parseDouble(D)) / Double.parseDouble("4");
						String Ave = Double.toString(ave);
						DB db = new DB();
						Statement st = db.DbCon();
						st.executeUpdate("UPDATE Score SET A = \"" + A + "\" WHERE Id = " + Id);
						st.executeUpdate("UPDATE Score SET B = \"" + B + "\" WHERE Id = " + Id);
						st.executeUpdate("UPDATE Score SET C = \"" + C + "\" WHERE Id = " + Id);
						st.executeUpdate("UPDATE Score SET D = \"" + D + "\" WHERE Id = " + Id);
						st.executeUpdate("UPDATE Score SET Ave = \"" + Ave + "\" WHERE Id = " + Id);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				dispose();
			}
		});

        this.add(label);
        this.add(text);
        this.add(label1);
        this.add(text1);
        this.add(label2);
        this.add(text2);
        this.add(label3);
        this.add(text3);
        this.add(label4);
        this.add(text4);
        this.add(button1);
        setVisible(true);
	}
}

class ScoreSearch extends JFrame{
	String[] s = {"이름", "학과", "학번"};
	ArrayList<Subject> ScoreArr = new ArrayList<Subject>();
	
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
			ResultSet rs = st.executeQuery("SELECT * FROM Score WHERE Id IN (SELECT Id From Student WHERE " + 
		                                    sqlType + " = \"" + text1.getText() + "\")");
			while(rs.next()) {
				String Id = rs.getString("Id");
				String A = rs.getString("A");
				String B = rs.getString("B");
				String C = rs.getString("C");
				String D = rs.getString("D");
				String Ave = rs.getString("Ave");
				Subject s = new Subject(Id,A,B,C,D,Ave);
				ScoreArr.add(s);
				for(int i = 0;i<ScoreArr.size();i++) {
					area.append("학번: " + ScoreArr.get(i).Id + ", A: " + ScoreArr.get(i).A + ", B: "  + ScoreArr.get(i).B + 
					            ", C: " + ScoreArr.get(i).C + ", D: "  + ScoreArr.get(i).D + 
							    ", Ave: " + ScoreArr.get(i).Ave + "\n");
				}
				ScoreArr.clear();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ScoreSearch() {
		setSize(500, 400);
        setTitle("학점조회");
        
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

class ScoreModify extends JFrame {
	String[] s = {"A", "B", "C", "D"};
	public ScoreModify() {
		setSize(500, 400);
        setTitle("학점수정");
        
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
					DB db = new DB();
					Statement st = db.DbCon();
					try {
						st.executeUpdate("UPDATE Score SET " + strCombo.getSelectedItem().toString() + " = \"" + text2.getText() + "\" WHERE Id = \"" + 
			                      text1.getText() + "\"");
						ResultSet rs = st.executeQuery("SELECT * FROM Score WHERE Id = \"" + text1.getText() + "\"");
						String Ave="";
						while(rs.next()) {
							String A = rs.getString("A");
							String B = rs.getString("B");
							String C = rs.getString("C");
							String D = rs.getString("D");
							double ave = (Double.parseDouble(A) + Double.parseDouble(B) + Double.parseDouble(C) + Double.parseDouble(D)) / Double.parseDouble("4");
							Ave = Double.toString(ave);
						}
						st.executeUpdate("UPDATE Score SET Ave = \"" + Ave + "\" WHERE Id = \"" + 
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

class ScoreDelete extends JFrame {
	JTextField text1 = new JTextField(20);
	public ScoreDelete() {
		setSize(500, 400);
        setTitle("학점삭제");
        
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
	
	void Sdelete() {
		DB db = new DB();
		Statement st = db.DbCon();
		try {
			st.executeUpdate("DELETE From Score WHERE Id = \"" + text1.getText() + "\"");
			st.executeUpdate("INSERT INTO Score (Id, A, B, C, D, Ave) VALUES (\"" + text1.getText() + "\", NULL, NULL, NULL, NULL, NULL)");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
