package com.github.sikv.habitsplus.feature.addtodo

import com.github.sikv.habitsplus.data.TodosRepository
import com.github.sikv.habitsplus.data.model.Todo
import com.github.sikv.habitsplus.store.Action
import com.github.sikv.habitsplus.store.AppMiddleware
import com.github.sikv.habitsplus.store.AppState
import com.github.sikv.habitsplus.store.Dispatcher
import com.github.sikv.habitsplus.store.EmitSideEffectAction
import com.github.sikv.habitsplus.util.TimeUtils
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

    private fun createTodoFromState(state: AddTodoState): Todo {
        return Todo(
            title = state.title.trim(),
            description = state.description?.trim(),
            dueDateMs = 0L, // TODO: Set.
            addedAtMs = TimeUtils.currentTimeMillis(),
            editedAtMs = null,
        )
    }
}
