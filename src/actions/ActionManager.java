package actions;

import cut_copy_paste.Copy;
import cut_copy_paste.Cut;
import cut_copy_paste.Paste;

public class ActionManager {

	
	private static ActionManager instance = null;
	
	private AboutAction aboutaction;
	private CloseTabAction closeTab;
	private CloseAllTabsAction clsAllTabs;
	private SaveAction save;
	private SaveAsAction saveAs;
	private DeleteNodeAction delete;
	private OpenProizvodAction open;
	private CloseWorkspaceAction close;
	
	private NewNodeAction newNode;
	private ExportAction export;
	
	private Paste paste;
	private Copy copy;
	private Cut cut;
	
	private UndoAction undo;
	private RedoAction redo;
	
	private EditAction edit;
	
	private PreviewAction prw;
	
	public ActionManager() {
		instalization();
	}
	
	public void instalization(){
		export= new ExportAction();
		aboutaction = new AboutAction();
		closeTab= new CloseTabAction();
		clsAllTabs= new  CloseAllTabsAction();
		save= new  SaveAction();
		saveAs= new SaveAsAction();
		delete = new DeleteNodeAction();
		open= new OpenProizvodAction();
		close= new CloseWorkspaceAction();
		
		newNode= new NewNodeAction();
		paste= new Paste();
		copy= new Copy();
		cut= new Cut();
		
		undo= new UndoAction();
		redo= new RedoAction();
		
		edit= new EditAction();
		
		prw= new PreviewAction();
	}
	public PreviewAction getPrw() {
		return prw;
	}
	public static ActionManager getActionManager() {
		if(instance==null) instance= new ActionManager();
		return instance;
	}

	public NewNodeAction getNewNode() {
		return newNode;
	}
	public EditAction getEdit() {
		return edit;
	}
	
	public AboutAction getAboutaction() {
		return aboutaction;
	}

	public CloseTabAction getCloseTab() {
		return closeTab;
	}

	public CloseAllTabsAction getClsAllTabs() {
		return clsAllTabs;
	}

	public SaveAction getSave() {
		return save;
	}

	public SaveAsAction getSaveAs() {
		return saveAs;
	}

	public DeleteNodeAction getDelete() {
		return delete;
	}

	public OpenProizvodAction getOpen() {
		return open;
	}

	public CloseWorkspaceAction getClose() {
		return close;
	}
	public ExportAction getExport() {
		return export;
	}
	public Copy getCopy() {
		return copy;
	}
	public Cut getCut() {
		return cut;
	}
	public Paste getPaste() {
		return paste;
	}
	public UndoAction getUndo() {
		return undo;
	}
	public RedoAction getRedo() {
		return redo;
	}
}
