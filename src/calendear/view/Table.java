package calendear.view;
import java.util.ArrayList;

import calendear.util.Task;

public class Table {
	private static final String HEADER_NAME = "task name:";
	private static final String HEADER_TAG = "tag:";
	private static final String HEADER_STARTTIME = "start time:";
	private static final String HEADER_ENDTIME = "end time:";
	private static final String HEADER_DUETIME = "due time:";
	private static final String HEADER_RECURRING_ENDTIME = "next due time:";
	private static final String HEADER_LOCATION = "location:";
	private static final String HEADER_NOTE = "note:";
	private static final String HEADER_IMPORTANCE = "important:";
	private static final String HEADER_FINISHED = "finished:";
	private static final String[] HEADERS = {HEADER_IMPORTANCE,HEADER_FINISHED,HEADER_NAME, 
			HEADER_TAG, HEADER_STARTTIME,HEADER_ENDTIME,HEADER_LOCATION,HEADER_NOTE};

	private static final String MSG_WELCOME = "welcome to calendear!";
	private static final String MSG_COMMAND = "command:";
	private static final String MSG_YES = "yes";
	private static final String MSG_NO = "no";
	
	private static final String S = " ";
	private static final int NUM_OF_ATTRI = 8;
	private static final int LEN_ID = 2;
	private static final int LEN_IMPO = 10;
	private static final int LEN_FINI = 10;
	private static final int LEN_NAME = 16;
	private static final int LEN_STIME = 12;
	private static final int LEN_ETIME = 12;
	private static final int LEN_TAG = 16;
	private static final int LEN_LOCA = 16;
	private static final int LEN_NOTE = 16;
	private static final int NOT_ARR_LIST = -1;
	private static final int NUM_OF_TITLE_BAR = NUM_OF_ATTRI+2;
	private static final int LEN_TOTAL = LEN_ID+LEN_IMPO+LEN_FINI+
						LEN_NAME+LEN_STIME+LEN_ETIME+LEN_TAG+LEN_LOCA+LEN_NOTE+NUM_OF_TITLE_BAR;
	private static final String BORDER_SIGN = "*";
	
	private static String format = "|%1$-"+LEN_ID+"s"+
									"|%2$-"+LEN_IMPO+"s"+
									"|%3$-"+LEN_FINI+"s"+
									"|%4$-"+LEN_NAME+"s"+
									"|%5$-"+LEN_TAG+"s"+
									"|%6$-"+LEN_STIME+"s"+
									"|%7$-"+LEN_ETIME+"s"+
									"|%8$-"+LEN_NAME+"s"+
									"|%9$-"+LEN_NOTE+"s"+
									"|\n";
	
	
	public static ArrayList<String> getDetailsArr(Task task){
		ArrayList<String> details= new ArrayList<String>();
		for(int i=0;i<NUM_OF_ATTRI;i++){
			details.add("");
		}
		if(task.isImportant()){
			details.set(0, "√");
		}
		if(task.isFinished()){
			details.set(1, "√");
		}
		details.set(2, task.getName());
		details.set(3, task.getTag());
		details.set(4, task.getStartTimeStr());
		details.set(5, task.getEndTimeStr());
		details.set(6, task.getLocation()) ;
		details.set(7, task.getNote());
		
		return details;
	}
 	
	
	public static String getMultipleTasks(ArrayList<Task> taskArr){
		String output = titleLine();
		output+=borderLine();
		for(int i=0;i<taskArr.size();i++){
			if(taskArr.get(i)!=null){
				output+=getSingleTask(taskArr.get(i),i);
				output+=borderLine();
			}
		}
		return output;
	}
	

	public static String getTask(Task task,int id){
		ArrayList<String> detailsInArray = getDetailsArr(task);
		ArrayList<ArrayList<String>> arrayOfAttributesArr = formArrayOfAttributesArr(detailsInArray);
		ArrayList<ArrayList<String>> arrayOfLines = formLineArr(arrayOfAttributesArr);
		String taskInLine = titleLine()+borderLine()+formLineString(arrayOfLines,id)+borderLine();
		return taskInLine;
	}
	
	public static String getSingleTask(Task task,int id){
		ArrayList<String> detailsInArray = getDetailsArr(task);
		ArrayList<ArrayList<String>> arrayOfAttributesArr = formArrayOfAttributesArr(detailsInArray);
		ArrayList<ArrayList<String>> arrayOfLines = formLineArr(arrayOfAttributesArr);
		String taskInLine = formLineString(arrayOfLines,id);
		return taskInLine;
	}
	
	private static ArrayList<ArrayList<String>> 
		formArrayOfAttributesArr( ArrayList<String> details){
		ArrayList<ArrayList<String>> arrayOfAttributesArr = new ArrayList<ArrayList<String>>();
		for(int i=0;i<NUM_OF_ATTRI;i++){
			arrayOfAttributesArr.add(new ArrayList<String>());
		}
		arrayOfAttributesArr.get(0).add(details.get(0));
		arrayOfAttributesArr.get(1).add(details.get(1));
		arrayOfAttributesArr.get(2).addAll(formAttributesArr(LEN_NAME, details.get(2)));
		arrayOfAttributesArr.get(3).addAll(formAttributesArr(LEN_TAG, details.get(3)));
		arrayOfAttributesArr.get(4).addAll(formAttributesArr(LEN_STIME, details.get(4)));
		arrayOfAttributesArr.get(5).addAll(formAttributesArr(LEN_ETIME, details.get(5)));
		arrayOfAttributesArr.get(6).addAll(formAttributesArr(LEN_LOCA, details.get(6)));
		arrayOfAttributesArr.get(7).addAll(formAttributesArr(LEN_NOTE, details.get(7)));
		
		return arrayOfAttributesArr;
	}
	
	private static ArrayList<String> 
		formAttributesArr(int len, String text){
		ArrayList<String> attriArr = new ArrayList<String>();
		if(text!=null){
		if(text.length()<=len){
			attriArr.add(text);
		}
		else{
			int begin = 0;
			int end =len;
			while(end<text.length()){
				while(!(text.charAt(end)==' ')){
					end--;
				}
				attriArr.add(text.substring(begin, end));
				begin = end;
				end+=len;
				if(end>=text.length()){
					attriArr.add(text.substring(begin));
				}
			}
		}
		}
		return attriArr;
		
	}
	
	private static ArrayList<ArrayList<String>> 
		formLineArr( ArrayList<ArrayList<String>> details){
		int[] colLen = new int[NUM_OF_ATTRI];
		int maxLen = 0;
		for(int i=0;i<NUM_OF_ATTRI;i++){
			if(details.get(i)!=null){
				colLen[i]=details.get(i).size();
			if(colLen[i]>maxLen){
				maxLen=colLen[i];
			}
			}
		}
		ArrayList<ArrayList<String>> lineBlock = new ArrayList<ArrayList<String>>();
		for(int i=0;i<maxLen;i++){
			lineBlock.add(new ArrayList<String>());
		}
		for(int m=0;m<maxLen;m++){
			for(int n=0;n<NUM_OF_ATTRI;n++){
				if(m>=colLen[n]){
					lineBlock.get(m).add(S);
				}
				else{
					lineBlock.get(m).add(details.get(n).get(m));
				}
			}
		}
		return lineBlock;
	}

	
	private static String 
		formLineString(ArrayList<ArrayList<String>> arr,int id){
		String taskInLine ="";
		if(id == NOT_ARR_LIST){
			taskInLine += String.format(format,S,arr.get(0).get(0),arr.get(0).get(1)
				,arr.get(0).get(2),arr.get(0).get(3),arr.get(0).get(4)
				,arr.get(0).get(5),arr.get(0).get(6),arr.get(0).get(7));
		}
		else{
			taskInLine += String.format(format,id+".",arr.get(0).get(0),arr.get(0).get(1)
					,arr.get(0).get(2),arr.get(0).get(3),arr.get(0).get(4)
					,arr.get(0).get(5),arr.get(0).get(6),arr.get(0).get(7));
		}
		for(int i=1;i<arr.size();i++){
			taskInLine += String.format(format," ",arr.get(i).get(0),arr.get(i).get(1)
					,arr.get(i).get(2),arr.get(i).get(3),arr.get(i).get(4)
					,arr.get(i).get(5),arr.get(i).get(6),arr.get(i).get(7));
		}
		return taskInLine;
		
	}
	
	private static String titleLine(){
		String titleLine = borderLine();
		titleLine+= String.format(format," ",HEADER_IMPORTANCE,HEADER_FINISHED,HEADER_NAME, 
				HEADER_TAG, HEADER_STARTTIME,HEADER_ENDTIME,HEADER_LOCATION,HEADER_NOTE);
		return titleLine;
	}
	
	private static String borderLine(){
		String border ="";
		for(int i=0;i<LEN_TOTAL;i++){
			border+= BORDER_SIGN;
		}
		border+="\n";
		return border;
	}
}
