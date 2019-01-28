package io.github.jorelali.varchangelogger;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;

public class VarLogger implements Plugin {

	@Override
	public String getName() {
		return "VarLogger";
	}
	
	@Override
	public void init(JavacTask task, String... args) {

		task.addTaskListener(new TaskListener() {

			@Override
			public void finished(TaskEvent taskEvent) {
				if (taskEvent.getKind() == TaskEvent.Kind.PARSE) {
					taskEvent.getCompilationUnit().accept(new VarLoggerTreeScanner(task), null);
				}
			}

			@Override
			public void started(TaskEvent taskEvent) {}
		});
	}

}
