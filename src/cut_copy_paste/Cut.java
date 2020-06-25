package cut_copy_paste;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import actions.MyAbsAction;
import model.Node;
import model.Parametar;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class Cut extends MyAbsAction {
	
	public Cut() {
		putValue(SMALL_ICON, loadIcon("images/toolbar/cut.png"));
		putValue(NAME, "Cut");
		putValue(SHORT_DESCRIPTION, "Cut");
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		HashMap<Parametar, Node> roditeljDete= new HashMap<Parametar,Node>();
		
		TreePath[] paths= WorkZone.getInstance().getjTree().getSelectionModel().getSelectionPaths();
		
		if(paths.length>0) {
			ArrayList<Parametar> listaParametra= new ArrayList<>();
			ArrayList<Parametar> original= new ArrayList<Parametar>();
			ArrayList<Integer> in= new ArrayList<Integer>();
			for(TreePath p: paths) {
				Object lastPath = p.getLastPathComponent();
				if(lastPath instanceof Parametar) {
					in.add(((Parametar) lastPath).getRoditelj().getDeca().indexOf(lastPath));
					original.add((Parametar) lastPath);
					listaParametra.add(Parametar.copy((Parametar) lastPath));
					roditeljDete.put((Parametar) lastPath , ((Parametar) lastPath).getRoditelj());
				}
			}
			
			NodeSelection selektovaniParametri = new NodeSelection(listaParametra);
			MainFrame.getInstance().setOriginal(original);
			SwingUtilities.updateComponentTreeUI(WorkZone.getInstance().getjTree());
			MainFrame.getInstance().setTipPaste("cut");
			MainFrame.getInstance().getClipboard().setContents(selektovaniParametri, MainFrame.getInstance());
			MainFrame.getInstance().setIndexi(in);
		}
		
	}

		
	}


