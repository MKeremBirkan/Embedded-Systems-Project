import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class Screen extends JFrame {

	private JTable table;

	public Screen(String[] array,String ID,String Name) {
		
		setBounds(100, 100, 500,500);

		setTitle(Name + " | DataBase ID : " + ID);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(100, 100, 350, 300);
		
		setLocationRelativeTo(null);

		getContentPane().add(scrollPane);

		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"TEMPERATURE","PRESSURE", "DATE", "TIME" 
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(97);
		table.getColumnModel().getColumn(1).setPreferredWidth(97);
		table.getColumnModel().getColumn(2).setPreferredWidth(97);
		table.getColumnModel().getColumn(3).setPreferredWidth(97);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		 
		 String []rowList =new String[4];

		    int i = 0;

		    while (i<array.length)
			{
		    	for(int j=0; j<4; j++)

		    	{

		    		rowList[j]=array[i];

		    		i++;

		    	}

		    	model.addRow(rowList);

			}
		scrollPane.setViewportView(table);
	}

}
