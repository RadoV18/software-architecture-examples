import {Component, OnInit} from '@angular/core';
import {TodoService} from "../../services/todo.service";
import {TodoResDto} from "../../model/todo-res.dto";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrl: './todo-list.component.scss'
})
export class TodoListComponent implements OnInit {
  todos: TodoResDto[];

  constructor(private todoService: TodoService) {
    this.todos = [];
  }

  ngOnInit() {
    this.todoService.getAll().subscribe({
      next: (response) => {
        this.todos = response.data;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  setAsDone(todo: TodoResDto) {
    this.todoService.setAsDone(todo.id, {
      title: todo.title,
      description: todo.description,
      done: true
    }).subscribe({
      next: () => {
        this.todos = this.todos.map((t) => {
          if (t.id === todo.id) {
             return {
               ...t,
               done: true
             };
          }
          return t;
       });
      },
      error: (error) => {
        console.error(error);
      }
   });
  }

  delete(todo: TodoResDto) {
    this.todoService.deleteTodo(todo.id).subscribe({
      next: () => {
        this.todos = this.todos.filter((t) => t.id !== todo.id);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

}
