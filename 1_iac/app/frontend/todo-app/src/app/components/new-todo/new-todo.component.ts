import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {TodoService} from "../../services/todo.service";

@Component({
  selector: 'app-new-todo',
  templateUrl: './new-todo.component.html',
  styleUrl: './new-todo.component.scss'
})
export class NewTodoComponent {
  form!: FormGroup;

  constructor(private fb: FormBuilder, private todoService: TodoService) {
    this.initForm();
  }

  initForm() {
    this.form = this.fb.group({
      title: '',
      description: ''
    });
  }

  onSubmit() {
    this.todoService.createTodo({
      title: this.form.value.title,
      description: this.form.value.description,
      done: false,
    }).subscribe(() => {
      window.location.reload();
    });
  }

}
