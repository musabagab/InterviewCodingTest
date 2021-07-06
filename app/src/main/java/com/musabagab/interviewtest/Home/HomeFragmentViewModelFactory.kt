package com.musabagab.interviewtest.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeFragmentViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel() as T
        }
        throw IllegalArgumentException("ViewModel is unknown")
    }
}

class HomeFragmentViewModel : ViewModel() {

    private val _viewState: MutableLiveData<HomeFragmentViewState> = MutableLiveData()
    val viewState: LiveData<HomeFragmentViewState> = _viewState

//    init {
//        loadTodoList()
//    }
//
//    fun deleteTodo(todoEntity: TodoEntity?) = viewModelScope.launch {
//        repo.deleteTodo(todoEntity)
//        // update the state
//        _viewState.value?.let {
//            val newList = it.todoList.toMutableList()
//            newList.remove(todoEntity)
//            // notify the listeners
//            _viewState.value = TodoListFragmentViewState(todoList = newList)
//        }
//
//    }
//
//    fun reInsertTodo(
//        todoEntity: TodoEntity,
//        fullList: MutableList<TodoEntity>
//    ) {
//        viewModelScope.launch {
//            repo.addTodo(todoEntity)
//            fullList.add(todoEntity)
//            _viewState.value = TodoListFragmentViewState(todoList = fullList)
//        }
//    }
//
//
//    private fun loadTodoList() = viewModelScope.launch {
//        val loadState = repo.loadTodo()
//        _viewState.value = loadState
//    }


}