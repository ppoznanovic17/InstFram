package view.slider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import model.Proizvod;
import view.parametar.Default;
import view.parametar.DesktopShortcut;
import view.parametar.LookAndFeel;
import view.parametar.PokreniNakonInstalacije;
import view.workspace.MainFrame;

public class PanelSlider42<ParentType extends Container> {

	private static final int           RIGHT             = 0x01;
	private static final int           LEFT              = 0x02;
	private static final int           TOP               = 0x03;
	private static final int           BOTTOM            = 0x04;
	private final JPanel               basePanel         = new JPanel();
	private final JFrame          parent;
	private final Object               lock              = new Object();
	private final ArrayList<JComponent> jPanels           = new ArrayList<JComponent>();
	private final boolean              useSlideButton    = true;
	private boolean                    isSlideInProgress = false;
	private JPanel                     poslednji         = null;
	private JPanel                     prvi              = null;
	private JButton                    btnNext           = new JButton("Next >");
	private JButton                    btnBack           = new JButton("< Back");
	private JButton                    btnFinish         = new JButton("Finish");
	private int                        cnt;
	private boolean                    pokretanje         = false;
	private boolean                    desktop            = false;
	private boolean                    lookfeel           = false;
	private String                     bojaLf             = null;
	private String                     gde                = null;
	private Proizvod                   instaliraSe        = null;
	
	public void setInstaliraSe(Proizvod instaliraSe) {
		this.instaliraSe = instaliraSe;
	}
	
	public void setPrvi(JPanel prvi) {
		this.prvi = prvi;
	}
	
	private final JPanel               glassPane;
	{
	    glassPane = new JPanel();
	    glassPane.setOpaque(false);
	    glassPane.addMouseListener(new MouseAdapter() {
	    });
	    glassPane.addMouseMotionListener(new MouseMotionAdapter() {
	    });
	    glassPane.addKeyListener(new KeyAdapter() {
	    });
	}

	public PanelSlider42(final JFrame parent) {
		cnt=0;
	    if (parent == null) {
	        throw new RuntimeException("ProgramCheck: Parent can not be null.");
	    }
	    if ((parent instanceof JFrame) /*|| (parent instanceof JDialog) || (parent instanceof JWindow) || (parent instanceof JPanel)*/) {
	    }
	    else {
	        throw new RuntimeException("ProgramCheck: Parent type not supported. " + parent.getClass().getSimpleName());
	    }
	    this.parent = parent;
	    attach();
	    basePanel.setSize(parent.getSize());
	    basePanel.setLayout(new BorderLayout());
	    
	    if (useSlideButton) {
	        final JPanel statusPanel = new JPanel();
	        basePanel.add(statusPanel, BorderLayout.SOUTH);
	         btnFinish= new JButton("Finish") {
	        	{
	            	setEnabled(false);
	                setMargin(new Insets(0, 0, 0, 0));
	            }
	            private static final long serialVersionUID = 9204819004142223529L;
	            {
	                addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(final ActionEvent e) {
	                        Path p= null;
	                        try {
	                        	System.out.println(gde);
								p=Files.createDirectories(Paths.get(gde+File.separatorChar+instaliraSe.getName()));
								
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	                        copyToDestFolder(p.toString(), instaliraSe.getInstaliraSe(), instaliraSe.getInstaliraSeIme());
	                    	if(desktop) {
	                    		copyToDestFolder("C:\\Users"+File.separatorChar+"Peca"+File.separatorChar+"Desktop", instaliraSe.getInstaliraSe(), instaliraSe.getInstaliraSeIme());
	                    		
	                    	}
	                    	
	                    		if (Desktop.isDesktopSupported()) {
		                    	    try {
		                    	        File myFile = new File(instaliraSe.getInstaliraSe());
		                    	        Desktop.getDesktop().open(myFile);
		                    	    } catch (IOException ex) {
		                    	        System.out.println("Ne postoji");
		                    	    }
		                    	}
	                    	
	                    	
	                    	parent.setVisible(false);
	                    }
	                });
	            }
	        };
	         btnBack= new JButton("< Back") {
	        	{
	            	setEnabled(false);
	                setMargin(new Insets(0, 0, 0, 0));
	            }
	            private static final long serialVersionUID = 9204819004142223529L;
	            {
	                addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(final ActionEvent e) {
	                       btnFinish.setEnabled(false);
	                       btnNext.setEnabled(true);
	                       slideLeft();
	                       cnt--;
	                    	if(cnt==0) {
	                        	
	                        	btnBack.setEnabled(false);
	                        }
	                    	
	                    }
	                });
	            }
	        };
	        
	        
	         btnNext= new JButton("Next >") {
	        	{
	            	
	                setMargin(new Insets(0, 0, 0, 0));
	            }
	            private static final long serialVersionUID = 9204819004142223529L;
	            {
	                addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(final ActionEvent e) {
	                    	cnt++;
	                        btnBack.setEnabled(true);
	                        if(jPanels.get(1).equals(poslednji)) {
	                        	btnNext.setEnabled(false);
	                        	btnFinish.setEnabled(true);
	                        }
	                        	slideRight();
	                        
	                        
	                    }
	                });
	            }
	        };
	        
	        statusPanel.add(btnBack);
	        statusPanel.add(btnNext);
	        statusPanel.add(btnFinish);
	        /*statusPanel.add(new JButton("Slide Down") {
	            {
	                setMargin(new Insets(0, 0, 0, 0));
	            }
	            private static final long serialVersionUID = 9204819004142223529L;
	            {
	                addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(final ActionEvent e) {
	                        slideBottom();
	                    }
	                });
	            }
	        });*/
	    }
	}

	public JPanel getBasePanel() {
	    return basePanel;
	}

	private void attach() {
	    final JFrame w = this.parent;
	    if (w instanceof JFrame) {
	        final JFrame j = (JFrame) w;
	        if (j.getContentPane().getComponents().length > 0) {
	            throw new RuntimeException("ProgramCheck: Parent already contains content.");
	        }
	        j.getContentPane().add(basePanel);
	    }
	  /*  if (w instanceof JDialog) {
	        final JDialog j = (JDialog) w;
	        if (j.getContentPane().getComponents().length > 0) {
	            throw new RuntimeException("ProgramCheck: Parent already contains content.");
	        }
	        j.getContentPane().add(basePanel);
	    }
	    if (w instanceof JWindow) {
	        final JWindow j = (JWindow) w;
	        if (j.getContentPane().getComponents().length > 0) {
	            throw new RuntimeException("ProgramCheck: Parent already contains content.");
	        }
	        j.getContentPane().add(basePanel);
	    }*/
	    /*if (w instanceof JPanel) {
	        final JPanel j = (JPanel) w;
	        if (j.getComponents().length > 0) {
	            throw new RuntimeException("ProgramCheck: Parent already contains content.");
	        }
	        j.add(basePanel);
	    }*/
	}

	public void addComponent(final Component component) {
	    if (jPanels.contains(component)) {
	    }
	    else {
	        jPanels.add((JComponent) component);
	        if (jPanels.size() == 1) {
	            basePanel.add(component);
	        }
	        component.setSize(basePanel.getSize());
	        component.setLocation(0, 0);
	    }
	}

	public void removeComponent(final Component component) {
	    if (jPanels.contains(component)) {
	        jPanels.remove(component);
	    }
	}

	public void slideLeft() {
	    slide(LEFT);
	}

	public void slideRight() {
	    slide(RIGHT);
	}

	public void slideTop() {
	    slide(TOP);
	}

	public void slideBottom() {
	    slide(BOTTOM);
	}

	private void enableUserInput(final JFrame w) {
	    if (w instanceof JFrame) {
	        ((JFrame) w).getGlassPane().setVisible(false);
	    }
	/*    if (w instanceof JDialog) {
	        ((JDialog) w).getGlassPane().setVisible(false);
	    }
	    if (w instanceof JWindow) {
	        ((JWindow) w).getGlassPane().setVisible(false);
	    }*/
	}

	private void disableUserInput(final JFrame w) {
	    if (w instanceof JFrame) {
	        ((JFrame) w).setGlassPane(glassPane);
	    }
	    /*if (w instanceof JDialog) {
	        ((JDialog) w).setGlassPane(glassPane);
	    }
	    if (w instanceof JWindow) {
	        ((JWindow) w).setGlassPane(glassPane);
	    }*/
	    glassPane.setVisible(true);
	}

	private void enableTransparentOverylay() {
	    if (parent instanceof JFrame) {
	        ((JFrame) parent).getContentPane().setBackground(jPanels.get(0).getBackground());
	        parent.remove(basePanel);
	        parent.validate();
	    }
	   /* if (parent instanceof JDialog) {
	        ((JDialog) parent).getContentPane().setBackground(jPanels.get(0).getBackground());
	        parent.remove(basePanel);
	        parent.validate();
	    }*/
	   /* if (parent instanceof JWindow) {
	        ((JWindow) parent).getContentPane().setBackground(jPanels.get(0).getBackground());
	        parent.remove(basePanel);
	        parent.validate();
	    }*/
	}

	private void slide(final int slideType) {
	    if (!isSlideInProgress) {
	        isSlideInProgress = true;
	        final Thread t0 = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                parent.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	                disableUserInput(parent);
	                slide(true, slideType);
	                enableUserInput(parent);
	                parent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	                isSlideInProgress = false;
	            }
	        });
	        t0.setDaemon(true);
	        t0.start();
	    }
	    else {
	        Toolkit.getDefaultToolkit().beep();
	    }
	}

	private void slide(final boolean useLoop, final int slideType) {
	    if (jPanels.size() < 2) {
	        System.err.println("Not enough panels");
	        return;
	    }
	    synchronized (lock) {
	        Component componentOld = null;
	        Component componentNew = null;
	        if ((slideType == LEFT) ) {
	            componentNew = jPanels.remove(jPanels.size() - 1);
	            componentOld = jPanels.get(0);
	            jPanels.add(0, (JComponent) componentNew);
                //SwingUtilities.updateComponentTreeUI(componentNew);
                //SwingUtilities.updateComponentTreeUI(componentOld);
	        }
	        if ((slideType == RIGHT) ) {
	            componentOld = jPanels.remove(0);
	            jPanels.add((JComponent) componentOld);
	            componentNew = jPanels.get(0);
	            if(componentOld instanceof LookAndFeel) {
	            	boolean b=((LookAndFeel) componentOld).getBox().isSelected();
	            	if(b) {
	            		String boja=((LookAndFeel) componentOld).getCmb().getSelectedItem().toString();
	            		if(boja.toLowerCase().equals("plavo")){
	            			UIManager.put("nimbusBase", new Color(10,52,242));
	            			UIManager.put("nimbusBlueGrey", new Color(10,147,242));
	            			UIManager.put("control", new Color(10,205,255));
	            			
	            			
	            		}else {
	            			UIManager.put("nimbusBase", new Color(246,53,149));
	            			UIManager.put("nimbusBlueGrey", new Color(246,53,204));
	            			UIManager.put("control", new Color(255,165,204));
	            		}
	            		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            			    if ("Nimbus".equals(info.getName())) {
            			        try {
            						UIManager.setLookAndFeel(info.getClassName());
            						
            					} catch (ClassNotFoundException e) {
            						e.printStackTrace();
            					} catch (InstantiationException e) {
            						// 
            						e.printStackTrace();
            					} catch (IllegalAccessException e) {
            						e.printStackTrace();
            					} catch (UnsupportedLookAndFeelException e) {

            						e.printStackTrace();
            					}
            			        break;
	            	}
	            }

	            	}
	            	
	            }
	            if(componentOld instanceof Default) {
	            	gde= ((Default) componentOld).getPath().getText();
	            }
	            if(componentOld instanceof PokreniNakonInstalacije) {
	            	pokretanje=((PokreniNakonInstalacije) componentOld).getBox().isSelected();
	            }
	            if(componentOld instanceof DesktopShortcut) {
	            	desktop=((DesktopShortcut) componentOld).getBox().isSelected();
	            }
	          // try { 
	        	SwingUtilities.updateComponentTreeUI(componentNew);
                SwingUtilities.updateComponentTreeUI(componentOld);
                SwingUtilities.updateComponentTreeUI(btnFinish);
                SwingUtilities.updateComponentTreeUI(btnNext);
                SwingUtilities.updateComponentTreeUI(btnBack);
             /*   
	           }catch(Exception e){
	        	   
	           }*/
	        }
	        final int w = componentOld.getWidth();
	        final int h = componentOld.getHeight();
	        final Point p1 = componentOld.getLocation();
	        final Point p2 = new Point(0, 0);
	        if (slideType == LEFT) {
	            p2.x += w;
	        }
	        if (slideType == RIGHT) {
	            p2.x -= w;
	        }
	        
	        componentNew.setLocation(p2);
	        int step = 0;
	        if ((slideType == LEFT) || (slideType == RIGHT)) {
	            step = (int) (((float) parent.getWidth() / (float) Toolkit.getDefaultToolkit().getScreenSize().width) * 40.f);
	        }
	        else {
	            step = (int) (((float) parent.getHeight() / (float) Toolkit.getDefaultToolkit().getScreenSize().height) * 20.f);
	        }
	        step = step < 5 ? 5 : step;
	        basePanel.add(componentNew);
	        basePanel.revalidate();
	        if (useLoop) {
	            final int max = (slideType == LEFT) || (slideType == RIGHT) ? w : h;
	            final long t0 = System.currentTimeMillis();
	            for (int i = 0; i != (max / step); i++) {
	                switch (slideType) {
	                    case LEFT: {
	                        p1.x -= step;
	                        componentOld.setLocation(p1);
	                        p2.x -= step;
	                        componentNew.setLocation(p2);
	                        break;
	                    }
	                    case RIGHT: {
	                        p1.x += step;
	                        componentOld.setLocation(p1);
	                        p2.x += step;
	                        componentNew.setLocation(p2);
	                        break;
	                    }
	                    case TOP: {
	                        p1.y -= step;
	                        componentOld.setLocation(p1);
	                        p2.y -= step;
	                        componentNew.setLocation(p2);
	                        break;
	                    }
	                    case BOTTOM: {
	                        p1.y += step;
	                        componentOld.setLocation(p1);
	                        p2.y += step;
	                        componentNew.setLocation(p2);
	                        break;
	                    }
	                    default:
	                        new RuntimeException("ProgramCheck").printStackTrace();
	                        break;
	                }

	                try {
	                    Thread.sleep(500 / (max / step));
	                } catch (final Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            final long t1 = System.currentTimeMillis();
	        }
	        componentOld.setLocation(-10000, -10000);
	        componentNew.setLocation(0, 0);
	    }
	}
	public ArrayList<JComponent> getjPanels() {
		return jPanels;
	}
	public int getNumberOfCommponents() {
		return jPanels.size();
	}
	public void setPoslednji(JPanel poslednji) {
		this.poslednji = poslednji;
	}
	public boolean copyToDestFolder(String destPath, String sourcePath, String fileName) {

		boolean result = true;

		Path source = Paths.get(sourcePath);
		Path destination = Paths.get(destPath + File.separatorChar + fileName);

		try {
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}

		return result;

	}
}
