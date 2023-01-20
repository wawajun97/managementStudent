import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener  {
	
	JButton button1 = new JButton("학생추가");
    JButton button2 = new JButton("학생검색");
    JButton button3 = new JButton("학생수정");
    JButton button4 = new JButton("학생출력");
    JButton button5 = new JButton("학생삭제");
    JButton button6 = new JButton("학점추가");
    JButton button7 = new JButton("학점조회");
    JButton button8 = new JButton("학점변경");
    JButton button9 = new JButton("학점삭제");
    JTextArea area = new JTextArea(20,30);
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button1) {
			new Insert();
		}
		else if(e.getSource() == button2) {
			new Search();
		}
		else if(e.getSource() == button3) {
			new Modify();
		}
		else if(e.getSource() == button4) {
			if(!area.getText().isEmpty()) {
				area.setText("");
			}
			DB db = new DB();
			Statement st = db.DbCon();
			try {
				ResultSet rs = st.executeQuery("SELECT * FROM Student");
				while(rs.next()) {
					String id = rs.getString("Id");
					String name = rs.getString("Name");
					String major = rs.getString("Major");
					area.append("이름: " + name + ", 학과: " + major + ", 학번: " + id +"\n");
				}
			}
			catch(Exception i) {
				i.printStackTrace();
			}		
		}
		else if(e.getSource() == button5) {
			new Delete();
		}
		else if(e.getSource() == button6) {
			new ScoreInsert();
		}
		else if(e.getSource() == button7) {
			new ScoreSearch();
		}
		else if(e.getSource() == button8) {
			new ScoreModify();
		}
		else if(e.getSource() == button9) {
			new ScoreDelete();
		}
	}

	public GUI(){
		setSize(500, 450); //크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("학생정보 프로그램");
        
        setLayout(new FlowLayout()); //배치 관리자 설정
        
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        //컴포넌트 생성 및 추가
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(button9);
        this.add(area);
        setVisible(true);
	}
}