import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TodoReqDto} from "../model/todo-req.dto";
import {TodoResDto} from "../model/todo-res.dto";
import {ResponseDto} from "../model/response.dto";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  baseUrl = environment.API_URL;

  constructor(private http: HttpClient) { }

  createTodo(todoReqDto: TodoReqDto) {
    return this.http.post(`${this.baseUrl}/v1/todo`, todoReqDto);
  }

  getAll(): Observable<ResponseDto<TodoResDto[]>> {
    return this.http.get<ResponseDto<TodoResDto[]>>(`${this.baseUrl}/v1/todo`);
  }

  setAsDone(todoId: number, todoReqDto: TodoReqDto) {
    return this.http.put(`${this.baseUrl}/v1/todo/${todoId}`, todoReqDto);
  }

  deleteTodo(todoId: number) {
    return this.http.delete(`${this.baseUrl}/v1/todo/${todoId}`);
  }
}
