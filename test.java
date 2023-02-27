package view;

import java.awt.EventQueue;


import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;
import com.mysql.cj.xdevapi.Table;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.JDBC;

import java.awt.event.ActionListener;
//import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
public class test extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private final JScrollPane scrollPane ;
	private JTable table;
	private JTextField textField_tongthu;
	private JTextField textField_tongchi;
	private JTextField textField_tongsotien;
	private Image img;
	private double tongthu;
	private double tongchi;
	private double tongsotien;
	private JComboBox comboBox;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private bangdulieu[] list;

    JDBC sc = new JDBC();
	private int stt;
	private String theloaithuchi;
	private String ngay;
	private int sotien;
	private String taikhoan;
	private String theloai;
	private String ghichu;
	int i = 1;
	
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public test() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\84329\\OneDrive\\Hình ảnh\\icon\\piggy-bank-6183186_960_720.png"));

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 900, 720);
		contentPane = new JPanel();	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		LoadDataToSQL();
		
		ImageIcon icon = new ImageIcon("slider_bg.png");
		img = icon.getImage();
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cài đặt");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Thay đổi hình nền");
		mnNewMenu.add(mnNewMenu_2);
		
		JButton btnNewButton_9 = new JButton("Ban ngay");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinhnenbanngay();
			}
		});
		btnNewButton_9.setForeground(new Color(0, 0, 0));
		mnNewMenu_2.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Ban đêm");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinhnenbandem();
			}
		});
		mnNewMenu_2.add(btnNewButton_10);
		JButton btnNewButton_11 = new JButton("Mặc định");
		mnNewMenu_2.add(btnNewButton_11);
		JButton btnNewButton_6 = new JButton("Thoát");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnNewMenu.add(btnNewButton_6);
		
		JLabel lb2 = new JLabel("");
		lb2.setForeground(Color.RED);
		lb2.setFont(new Font("Arial Black", Font.BOLD, 15));
		lb2.setBounds(688, 332, 188, 38);
		contentPane.add(lb2);
		btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		        if(textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("")) {
		            lb2.setText("Something wrong!!!!");
		        } else {
		            try {
		            	
//		            	int stt = Integer.parseInt(textField_3.getText());
		                String the_loai_chi_tieu = (String) comboBox.getSelectedItem();
		                String ngay = textField.getText();
		                double so_tien = Double.parseDouble(textField_1.getText());
		                String tai_khoan = (String) comboBox_1.getSelectedItem();
		                String the_loai = (String) comboBox_2.getSelectedItem();
		                String ghi_chu = textField_2.getText();
		               Connection conn = JDBC.getConnection();
		                PreparedStatement prstm = conn.prepareStatement(
		                    "INSERT INTO `thuchi1`(`stt`, `the_loai_chi_tieu`, `ngay`, `so_tien`, `tai_khoan`, `the_loai`, `ghi_chu`) VALUES (?, ?, ?, ?, ?, ?, ?)");
		            	PreparedStatement prst = conn.prepareStatement("select stt from thuchi1 ");
						int stt = 0;
						ResultSet rs = prst.executeQuery();
						while(rs.next()) {
							stt = rs.getInt(1);
						}

		                prstm.setInt(1, stt+1); 
		                prstm.setString(2, the_loai_chi_tieu);
		                prstm.setString(3, ngay);
		                prstm.setDouble(4, so_tien);
		                prstm.setString(5, tai_khoan);
		                prstm.setString(6, the_loai);
		                prstm.setString(7, ghi_chu); 
		                
		                int rowsAffected = prstm.executeUpdate();
		                
		                // Tăng giá trị của stt sau khi thêm thành công
		                stt++;
		                
		            } catch (SQLException se) {
		                se.printStackTrace();
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		         
		            them();
		      
		        }
		    }
		});

		btnNewButton.setBackground(new Color(255, 128, 64));
		
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 18));
		
		btnNewButton.setBounds(40, 380, 90, 40);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Sửa");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Lấy chỉ số của hàng được chọn trong table
		            int rowIndex = table.getSelectedRow();

		            // Kết nối cơ sở dữ liệu
		            Connection conn = JDBC.getConnection();

		            // Truy vấn cơ sở dữ liệu để lấy thông tin về hàng được chọn
		            String sql = "SELECT the_loai_chi_tieu, ngay, so_tien, tai_khoan, the_loai, ghi_chu FROM thuchi1 WHERE stt = ?";
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(table.getValueAt(rowIndex, 0).toString()));
		            ResultSet rs = pstmt.executeQuery();
		            if (rs.next()) {
		                String theloaithuchi = rs.getString("the_loai_chi_tieu");
		                String ngay = rs.getString("ngay");
		                int sotien = rs.getInt("so_tien");
		                String taikhoan = rs.getString("tai_khoan");
		                String theloai = rs.getString("the_loai");
		                String ghichu = rs.getString("ghi_chu");

		                // Hiển thị thông tin hàng được chọn trên các thành phần giao diện
//		                textField_3.setText(String.valueOf(table.getValueAt(rowIndex, 0)));
		                comboBox.setSelectedItem(theloaithuchi);
		                textField.setText(ngay);
		                textField_1.setText(String.valueOf(sotien));
		                comboBox_1.setSelectedItem(taikhoan);
		                comboBox_2.setSelectedItem(theloai);
		                textField_2.setText(ghichu);
		            }

		            // Đóng kết nối cơ sở dữ liệu
		            conn.close();
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});


		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(255, 128, 64));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 18));
		btnNewButton_1.setBounds(246, 380, 90, 40);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int row =table.getSelectedRow();				
					if (row < 0) {
			            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa");
			            return;
			        }
					int stt = (int) table.getValueAt(row, 0);
					System.out.println(stt);
					try {
						Connection conn = JDBC.getConnection();
						String sql = "DELETE FROM thuchi1 WHERE stt = ?";
						PreparedStatement statement = conn.prepareStatement(sql);
						statement.setInt(1, stt);
						int rowdelete = statement.executeUpdate();
						statement.close();
						conn.close();
						model.setRowCount(0);
						LoadData(table);
						
						
						
						
						
						
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi: Không thể xóa hàng");
					}
				}
					
		});
		btnNewButton_2.setBackground(new Color(255, 128, 64));
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 18));
		btnNewButton_2.setBounds(493, 380, 90, 40);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_4 = new JButton("Thoát");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setBackground(new Color(255, 128, 64));
		btnNewButton_4.setFont(new Font("Arial", Font.BOLD, 18));
		btnNewButton_4.setBounds(676, 380, 90, 40);
		contentPane.add(btnNewButton_4);
		JMenu mnNewMenu_1 = new JMenu("Nhiều hơn");
		menuBar.add(mnNewMenu_1);
		
		JButton btnNewButton_7 = new JButton("Thông tin");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		mnNewMenu_1.add(btnNewButton_7);
		JButton btnNewButton_8 = new JButton("Phiên bản");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				version();
			}
		});
		mnNewMenu_1.add(btnNewButton_8);
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lí chi tiêu");
		lblNewLabel.setForeground(new Color(255, 128, 64));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
		lblNewLabel.setBounds(230, 10, 396, 49);
		contentPane.add(lblNewLabel);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 430, 886, 161);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Th\u1EC3 lo\u1EA1i thu chi", "Ng\u00E0y", "S\u1ED1 ti\u1EC1n", "T\u00E0i kho\u1EA3n", "Th\u1EC3 lo\u1EA1i", "Ghi ch\u00FA"
			}
		));
		LoadData(table);		
//		table.getColumnModel().getColumn(0).setPreferredWidth(80);
//		table.getColumnModel().getColumn(1).setPreferredWidth(80);
//		table.getColumnModel().getColumn(2).setPreferredWidth(80);
//		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel("Thể loại thu chi");
		lblNewLabel_1.setForeground(new Color(255, 128, 64));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_1.setBounds(60, 68, 200, 35);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Ngày");
		lblNewLabel_2.setForeground(new Color(255, 128, 64));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2.setBounds(60, 118, 200, 35);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Số tiền");
		lblNewLabel_3.setForeground(new Color(255, 128, 64));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_3.setBounds(60, 168, 200, 35);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Tài khoản");
		lblNewLabel_4.setForeground(new Color(255, 128, 64));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_4.setBounds(60, 218, 200, 35);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Thể loại");
		lblNewLabel_5.setForeground(new Color(255, 128, 64));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_5.setBounds(60, 268, 200, 35);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Ghi chú");
		lblNewLabel_6.setForeground(new Color(255, 128, 64));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setIcon(null);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_6.setBounds(60, 318, 200, 35);
		contentPane.add(lblNewLabel_6);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Thu", "Chi"}));
		comboBox.setBounds(300, 68, 300, 35);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		textField.setBounds(300, 118, 300, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_1.setBounds(300, 168, 300, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Arial", Font.BOLD, 18));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Tien mat", "Chuyen khoan", "The tin dung"}));
		comboBox_1.setBounds(300, 218, 300, 35);
		contentPane.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Arial", Font.BOLD, 16));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"An uong", "Giai tri", "So thich", "Sinh hoat", "Ao quan", "Lam dep", "Suc khoe", "Giao duc", "Su kien", "Khac"}));
		comboBox_2.setBounds(300, 268, 300, 35);
		contentPane.add(comboBox_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_2.setBounds(300, 318, 300, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		lblNewLabel_7 = new JLabel("Tổng thu:");
		lblNewLabel_7.setForeground(new Color(255, 128, 64));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_7.setBounds(140, 611, 104, 40);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Tổng chi:");
		lblNewLabel_8.setForeground(new Color(255, 128, 64));
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_8.setBounds(380, 611, 102, 40);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Tổng số tiền:");
		lblNewLabel_9.setForeground(new Color(255, 128, 64));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_9.setBounds(620, 611, 142, 40);
		contentPane.add(lblNewLabel_9);
		
		textField_tongthu = new JTextField();
		textField_tongthu.setForeground(new Color(0, 128, 255));
		textField_tongthu.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_tongthu.setBounds(248, 612, 122, 30);
		contentPane.add(textField_tongthu);
		textField_tongthu.setColumns(10);
		
		textField_tongchi = new JTextField();
		textField_tongchi.setForeground(new Color(255, 128, 64));
		textField_tongchi.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_tongchi.setHorizontalAlignment(SwingConstants.LEFT);
		textField_tongchi.setBounds(493, 612, 118, 30);
		contentPane.add(textField_tongchi);
		textField_tongchi.setColumns(10);
		
		textField_tongsotien = new JTextField();
		textField_tongsotien.setFont(new Font("Arial", Font.PLAIN, 18));
		textField_tongsotien.setHorizontalAlignment(SwingConstants.LEFT);
		textField_tongsotien.setBounds(763, 612, 113, 30);
		contentPane.add(textField_tongsotien);
		textField_tongsotien.setColumns(10);
		
		btnNewButton_3 = new JButton("Làm mới");
		btnNewButton_3.setBackground(new Color(255, 128, 64));
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_3.setBounds(10, 610, 120, 35);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = JDBC.getConnection();
				PreparedStatement prst;
				tongthu = 0;
				tongchi = 0;
				try {
					prst = conn.prepareStatement("select so_tien from thuchi1 where the_loai_chi_tieu = ?");
					prst.setString(1,"thu");
					ResultSet rs = prst.executeQuery();
					while (rs.next()) {
						tongthu+= rs.getInt(1);
					}
					prst = conn.prepareStatement("select so_tien from thuchi1 where the_loai_chi_tieu = ?");
					prst.setString(1,"chi");
					rs = prst.executeQuery();
					while (rs.next()) {
						tongchi+= rs.getInt(1);
					}
					
					tongsotien = tongthu - tongchi;
					textField_tongthu.setText(String.valueOf(tongthu));
					textField_tongchi.setText(String.valueOf(tongchi));
					textField_tongsotien.setText(String.valueOf(tongsotien));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
															
			}
		});
		
		
		
		
	}

	private void LoadDataToSQL() {
		// TODO Auto-generated method stub
		
	}

	public void hinhnenbanngay() {
		contentPane.setBackground(Color.WHITE);
		scrollPane.setBackground(Color.WHITE);
		contentPane.setOpaque(true);
	}
	public void hinhnenbandem() {
		contentPane.setBackground(new Color(47, 47, 47));
		scrollPane.setBackground(new Color(47, 47, 47));
		contentPane.setOpaque(true);
	}
	public void about() {
		JOptionPane.showMessageDialog(null, "Đây hiện đang là bản thử nghiệm");
	}
	public void version() {
		JOptionPane.showMessageDialog(null, "Đây là phiên bản beta: 0.0.1");
	}

	// Còn làm trống cột thứ nhất để set stt từ database
	public void them() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {
			Connection conn = JDBC.getConnection();
			PreparedStatement prst = conn.prepareStatement("select stt from thuchi1 ");
			int stt = 0;
			ResultSet rs = prst.executeQuery();
			while(rs.next()) {
				stt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		model.addRow(new Object[] {
				stt+1,
				comboBox.getSelectedItem().toString(),
				textField.getText().toString(),
				textField_1.getText().toString(),
				comboBox_1.getSelectedItem().toString(),
				comboBox_2.getSelectedItem().toString(),
				textField_2.getText().toString()});
		        comboBox.setSelectedItem("Thu");
		        textField.setText(""	);
		        textField_1.setText("");
		        comboBox_1.setSelectedItem("Tiền mặt");
		        comboBox_2.setSelectedItem("Ăn uống");
		        textField_2.setText("");		        
	}
	public void LoadData(JTable table) {

		Connection conn = JDBC.getConnection();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		try {
			String sql = sql = "SELECT stt, the_loai_chi_tieu , ngay, so_tien, tai_khoan, the_loai, ghi_chu FROM thuchi1";
			PreparedStatement prstm = conn.prepareStatement(sql);
			ResultSet rs = prstm.executeQuery();
			while(rs.next()) {
				stt = rs.getInt(1);
				theloaithuchi = rs.getString("the_loai_chi_tieu");
				ngay = rs.getNString("ngay");
				sotien = rs.getInt("so_tien");
				taikhoan = rs.getString("tai_khoan");
				theloai = rs.getString("the_loai");
				ghichu = rs.getString("ghi_chu");
				model.addRow(new Object[] {stt, theloaithuchi , ngay, sotien, taikhoan, theloai, ghichu});
			}
			rs.close();
            prstm.close();
            conn.close();
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}
	public class LoadDataToSQL {
	    public void loadData() {
	        // Tạo kết nối đến cơ sở dữ liệu MySQL
	        Connection conn = JDBC.getConnection();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try {
	            // Tạo statement để truy vấn bảng trong Java
	            pstmt = conn.prepareStatement("SELECT * FROM thuchi1");
	            rs = pstmt.executeQuery();

	            // Duyệt qua các bản ghi trong ResultSet
	            while (rs.next()) {
	                // Lấy các giá trị từ ResultSet
	                int stt = rs.getInt("stt");
	                String theloaithuchi = rs.getString("theloaithuchi");
	                String ngay = rs.getString("ngay");
	                int sotien = rs.getInt("sotien");
	                String taikhoan = rs.getString("taikhoan");
	                String theloai = rs.getString("theloai");
	                String ghichu = rs.getString("ghichu");

	                // Tạo lệnh SQL INSERT INTO và thêm dữ liệu vào bảng trong MySQL
	                String sql = "INSERT INTO mytable(stt, theloaithuchi, ngay, sotien, taikhoan, theloai, ghichu) " +
	                             "VALUES (?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement insertStmt = conn.prepareStatement(sql);
	                insertStmt.setInt(1, stt);
	                insertStmt.setString(2, theloaithuchi);
	                insertStmt.setString(3, ngay);
	                insertStmt.setInt(4, sotien);
	                insertStmt.setString(5, taikhoan);
	                insertStmt.setString(6, theloai);
	                insertStmt.setString(7, ghichu);
	                insertStmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // Đóng tất cả các đối tượng ResultSet, PreparedStatement và Connection
	                if (rs != null)
	                    rs.close();
	                if (pstmt != null)
	                    pstmt.close();
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}
}






