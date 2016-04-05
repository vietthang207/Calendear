package calendear.view;

public class Pair<Integer,Task> {
	private  Integer id;
	private  Task task;
	
	public Pair(Integer id, Task task){
		this.id = id;
		this.task = task;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public void setTask(Task task){
		this.task = task;
	}
	
	public Integer getId(){
		return id;
	}
	
	public Task getTask(){
		return task;
	}
}