package com.github.sikv.habitsplus.store

typealias Reducer<S, A> = (state: S, action: A) -> S
