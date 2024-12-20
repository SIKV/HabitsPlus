package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.data.repository.TodosRepository
import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.model.TodoStatus
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
                AddTodoAction.Save -> handleAddAction(state.addTodoState, dispatcher)
            }
        }
    }

    private fun handleAddAction(state: AddTodoState, dispatcher: Dispatcher) {
        val newTodo = createTodoFromState(state)
        val addTodoError = TodoValidator.checkErrors(newTodo)

        if (addTodoError == null) {
            todosRepository.addTodo(newTodo)
            val effect = AddTodoHandleResultEffect(AddTodoResult.Success)
            dispatcher(EmitSideEffectAction(effect))
        } else {
            val result = AddTodoResult.Failure(addTodoError)
            val effect = AddTodoHandleResultEffect(result)
            dispatcher(EmitSideEffectAction(effect))
        }
    }

    private fun createTodoFromState(state: AddTodoState): TodoModel {
        return TodoModel(
            status = TodoStatus.Todo,
            title = state.title.trim(),
            description = state.description?.trim(),
            dueDate = 0L // TODO: Set.
        )
    }
}
