package cut_copy_paste;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import model.Node;
import model.Parametar;

public class NodeSelection implements Transferable {
	private ArrayList<Parametar> selectNodes;
	
	public static  DataFlavor dataFlavor;
	private DataFlavor[] dataFlavours={dataFlavor};
	
	public NodeSelection(ArrayList<Parametar> selectionNodes) {
		this.selectNodes=selectionNodes;
		try{
			
			dataFlavor= new DataFlavor(Class.forName("java.util.ArrayList"),  "Selected parametar");
			
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if(dataFlavor.equals(flavor))
			return (selectNodes);
		else
			throw new UnsupportedFlavorException(dataFlavor);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return dataFlavours;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(dataFlavor);
	}
	

	
}
