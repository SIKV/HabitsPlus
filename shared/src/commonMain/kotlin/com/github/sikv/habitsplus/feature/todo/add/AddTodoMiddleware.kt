package com.github.sikv.habitsplus.feature.todo.add

import com.github.sikv.habitsplus.data.model.TodoModel
import com.github.sikv.habitsplus.data.model.TodoStatus
import com.github.sikv.habitsplus.data.repository.TodosRepository
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.store.EmitSideEffectAction
import com.github.sikv.habitsplus.util.ModelValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class AddTodoMiddleware(
    private val todosRepository: TodosRepository,
    private val validator: ModelValidator<TodoModel, AddTodoError?>
) : AppMiddleware {

    override suspend fun invoke(state: AppState, action: Action, dispatcher: Dispatcher) {
        withContext(Dispatchers.IO) {
            when (action) {
                AddTodoAction.Save -> handleSaveAction(state.addTodoState, dispatcher)
            }
        }
    }

    private fun handleSaveAction(state: AddTodoState, dispatcher: Dispatcher) {
        val newTodo = createTodoFromState(state)
        val validationError = validator.checkErrors(newTodo)

        if (validationError == null) {
            todosRepository.addTodo(newTodo)

            val effect = AddTodoResultEffect(AddTodoResult.Success)
            dispatcher(EmitSideEffectAction(effect))
        } else {
            val effect = AddTodoResultEffect(AddTodoResult.Failure(validationError))
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
