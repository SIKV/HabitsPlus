package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.store.EmitSideEffectAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class AddTodoMiddleware(
    private val todosRepository: TodosRepository
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                AddTodoAction.Add -> {
                    val newTodo = Todo(
                        title = state.addTodoState.title
                    )
                    // TODO: Only for testing.
                    if (newTodo.title == "Error") {
                        val result = AddTodoResult.Failure(AddTodoError.Unknown)
                        val effect = AddTodoHandleResultEffect(result)
                        dispatcher(EmitSideEffectAction(effect))
                    } else {
                        todosRepository.addTodo(newTodo)
                        val effect = AddTodoHandleResultEffect(AddTodoResult.Success)
                        dispatcher(EmitSideEffectAction(effect))
                    }
                }
            }
        }
    }
}
