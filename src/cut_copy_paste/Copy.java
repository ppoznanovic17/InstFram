package cut_copy_paste;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import actions.MyAbsAction;
import model.Parametar;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class Copy extends MyAbsAction {

	public Copy() {
	putValue(SMALL_ICON, loadIcon("images/toolbar/copy.png"));
	putValue(NAME, " Copy");
	putValue(SHORT_DESCRIPTION, "Copy ");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		TreePath[] paths= WorkZone.getInstance().getjTree().getSelectionModel().getSelectionPaths();
		
		if(paths.length>0) {
			ArrayList<Parametar> listaParametra= new ArrayList<>();
			
			for(TreePath p: paths) {
				Object lastPath = p.getLastPathComponent();
				if(lastPath instanceof Parametar) {
					Parametar original= (Parametar) lastPath;
					listaParametra.add(Parametar.copy(original));
				}
			}
			NodeSelection selektovaniParametri = new NodeSelection(listaParametra);
			MainFrame.getInstance().setTipPaste("copy");
			MainFrame.getInstance().getClipboard().setContents(selektovaniParametri, MainFrame.getInstance());
		}
		
	}

	
	
}
