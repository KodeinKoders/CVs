package utils

//class StateDelegate<T>(state: Pair<T, RSetState<T>>) : ReadWriteProperty<Any?, T> {
//
//    private var value = state.first
//    private val set = state.second
//
//    override fun getValue(thisRef: Any?, property: KProperty<*>): T = value
//
//    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
//        if (value == this.value) return
//        this.value = value
//        set(value)
//    }
//
//}

//operator fun <T> Pair<T, RSetState<T>>.provideDelegate(thisRef: Any?, property: KProperty<*>) = StateDelegate(this)

//operator fun <P : RProps> FunctionalComponent<P>.invoke(propsBuilder: P.() -> Unit) = Pair(this, jsObject(propsBuilder))
//operator fun <P : RProps> FunctionalComponent<P>.plus(props: P) = Pair(this, props)
//
//fun RBuilder.fchild(component: FunctionalComponent<RProps>, handler: RHandler<RProps> = {}) = child(functionalComponent = component, handler = handler)
//fun <P : RProps> RBuilder.fchild(componentAndProps: Pair<FunctionalComponent<P>, P>, handler: RHandler<P> = {}) = child(functionalComponent = componentAndProps.first, props = componentAndProps.second, handler = handler)
