package org.scoula.todo;

import org.scoula.lib.cli.App;
import org.scoula.lib.cli.ui.Menu;
import org.scoula.lib.cli.ui.MenuItem;
import org.scoula.todo.command.AddTodoCommand;
import org.scoula.todo.command.DeleteTodoCommand;
import org.scoula.todo.command.ListTodoCommand;
import org.scoula.todo.command.UpdateTodoCommand;
import org.scoula.todo.domain.Todo;

import javax.xml.crypto.Data;

public class TodoApplication extends App {

    @Override
    public void createMenu(Menu menu) {
        super.createMenu(menu);

        menu.add(new MenuItem("목록", new ListTodoCommand()));
        menu.add(new MenuItem("추가", new AddTodoCommand()));
        menu.add(new MenuItem("수정", new UpdateTodoCommand()));
        menu.add(new MenuItem("삭제", new DeleteTodoCommand()));
    }

    public static void main(String[] args) {
        TodoApplication app = new TodoApplication();
        app.run();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        Database.close();
    }
}
