package commands;

import java.util.ArrayList;

public class CommandManager {

	private static CommandManager commandMannager;
	
	private ArrayList<AbstractCommand> commands = new  ArrayList<>();
	private  int currentCommand=-1;
	
	private CommandManager() {
		
	}
	
	public void addCommand(AbstractCommand c) {
		if(commands.size()==currentCommand-1) {
			commands.add(c);
		}else {
			ArrayList<AbstractCommand> cmd= new ArrayList<AbstractCommand>();
			for(AbstractCommand cm: commands) {
				if(commands.indexOf(cm)<=currentCommand) {
					cmd.add(cm);
				}
			}
			
			commands=cmd;
			commands.add(c);
		}
		
		currentCommand++;
		c.RedoCommand();
	}
	
	public static CommandManager getCommandMannager() {
		if(commandMannager==null) commandMannager= new CommandManager();
		return commandMannager;
	}
	public ArrayList<AbstractCommand> getCommands() {
		return commands;
	}
	public int getCurrentCommand() {
		return currentCommand;
	}
	public void smanjiPointer() {
		currentCommand--;
	}
	public void povecajPointer() {
		currentCommand++;
	}
}
