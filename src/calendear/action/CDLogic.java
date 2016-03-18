package calendear.action;

import calendear.action.Action;
import calendear.parser.Parser;
import calendear.util.*;
import calendear.view.View;

import java.util.Scanner;
import java.util.ArrayList;

import java.text.ParseException;

/**
 * @author Phang Chun Rong
 * Facade Class for Logic Component
 */
public class CDLogic {
	
	private Action _action;
	
	public CDLogic(String nameOfFile) throws ParseException {
		_action = new Action(nameOfFile);
	}
	
	public Command parseInput(String userInput) {
		return Parser.parse(userInput);
	}
	
	public Task exeAdd(CommandAdd commandAdd) {
		Task addedTask = _action.exeAdd(commandAdd);
		return addedTask;
	}
	
	public ArrayList<Task> exeDisplay(CommandDisplay commandDisplay) {
		ArrayList<Task> tasks = _action.exeDisplay(commandDisplay);
		return tasks;
	}
	
	public Task exeUpdate(CommandUpdate commandUpdate) {
		Task task = _action.exeUpdate(commandUpdate);
		return task;
	}
	
	public Task exeDelete(CommandDelete commandDelete) {
		Task task = _action.exeDelete(commandDelete);
		return task;
	}
	
}