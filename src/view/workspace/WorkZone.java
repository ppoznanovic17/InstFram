package view.workspace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import model.Modul;
import model.Node;
import model.Parametar;
import model.Proizvod;
import model.SoftverskaKompanija;
import model.TreeModel;

import view.bars.MyToolBar;
import view.bars.SecondToolBar;
import view.parametar.Logo;
import view.parametar.UsloviKoriscenja;
import view.tree.MyJTree;



public class WorkZone extends JPanel implements Observer {

	private static WorkZone instance=null;
	private MyJTree jTree;
	private TreeModel model;
	
	private JLabel lbl3 ;
	private JLabel lbl4 ;
	private JLabel lbl5 ;

	
	private static final Dimension minimumSize = new Dimension(250, 300);
	
	private WorkZone(){
		
		this.setBorder(new EmptyBorder(450, 650, 20, 20));
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setBackground(Color.white);
		
		/*JToolBar glavnitoolbar= MyToolBar.getInstance();						//---(Formiranje toolBar komponente radne zone)----(04.1) 
		glavnitoolbar.setToolTipText("Glavni ToolBar Aplikacije");
								
		this.add(glavnitoolbar, BorderLayout.PAGE_START);*/
					    
		//--------------------(UbacivanjeSplitPanea)--------------------------
		
		JSplitPane workSplitPane = new JSplitPane();
		
	
		SoftverskaKompanija k = new SoftverskaKompanija("Kompanija", null);
		/*k.addNewNode(new Proizvod("proizvod", k,"Source/komp"));
		((Proizvod) k.getChildAt(0)).addNewNode(new Modul("modul", k,"Source/komp"));*/
		model= new TreeModel(k);
		jTree= new MyJTree();
		jTree.setModel(model);
		
		workSplitPane.setMinimumSize(minimumSize);    	
		//---------------------------(Postavi stablo)------------------------
		

        JScrollPane scrol = new JScrollPane();
        scrol.setMinimumSize(minimumSize);
        scrol.setViewportView(jTree);
		
		JPanel jsp2 = new JPanel(new BorderLayout(0,0));			//------(Formiranje desne gornje komponente panela)-------
       	jsp2.setBorder(BorderFactory.createEtchedBorder());
       	//jsp2.setBackground(Color.white);
        jsp2.setMinimumSize(minimumSize);
        //jsp2.add();
        
        JToolBar paleta= SecondToolBar.getInstance();
    	paleta.setToolTipText("Paleta Alatki Radne Zone");
    	jsp2.add(paleta, BorderLayout.PAGE_START);
    	
    	MyTab tb= MyTab.getInstance();
		jsp2.add(tb.getTabbedPane());
																	//------(Formiranje multitaba - podrska otvaranju stavke)---
    	
    	JPanel jsp3 = new JPanel();									//------(Formiranje desne donje komponente panela)-------
        jsp3.setBorder(BorderFactory.createEtchedBorder());
        jsp3.setBackground(Color.white);
        jsp3.setMinimumSize(minimumSize);
        
        lbl3= new JLabel();
        lbl4= new JLabel();
        lbl5= new JLabel();
        
        
        
        
        jsp3.add(lbl3,BorderLayout.NORTH);
        jsp3.add(lbl4,BorderLayout.CENTER);
        jsp3.add(lbl5,BorderLayout.SOUTH);
                
    	JSplitPane spRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jsp2, jsp3);	 
        spRight.setDividerSize(2);
        spRight.setContinuousLayout(true);
        spRight.setDividerLocation(1.00);
        spRight.setResizeWeight(0.2);

        
        workSplitPane.setBottomComponent(spRight);
        
        workSplitPane.setTopComponent(scrol);
         
        workSplitPane.setDividerSize(2);
        workSplitPane.setContinuousLayout(true);
        workSplitPane.setDividerLocation(1.00);
        workSplitPane.setResizeWeight(0.2);
        
        this.add(workSplitPane,BorderLayout.CENTER);					//----------(Smestanje na GUI)-------------------
	}
		
	
	public MyJTree getjTree() {
		return jTree;
	}
	public static WorkZone getInstance() {
		if(instance == null) instance= new WorkZone();
		return instance;
	}
	public TreeModel getModel() {
		return model;
	}
	public void setModel(TreeModel model) {
		this.model = model;
	}
	public void setjTree(MyJTree jTree) {
		this.jTree = jTree;
	}
	public SoftverskaKompanija getRoot(){
		return (SoftverskaKompanija) model.getRoot();
	}


	@Override
	public void update(Observable o, Object arg) {
		Node n= (Node) arg;
		  lbl3.setText("Naziv-----> "+n.getName());
		  lbl4.setText("Broj dece-----> "+n.getChildCount());
		  lbl5.setText("Roditelj-----> "+n.getParent());
		  
	}
	
}
