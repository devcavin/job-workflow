package io.github.devcavin.workflow

class TransitionRules {
    private val rules = mutableMapOf<JobStatus, MutableSet<JobStatus>>()

    infix fun JobStatus.canGoTo(target: JobStatus) {
        rules.getOrPut(this) { mutableSetOf() }.add(target)
    }

    fun isAllowed(from: JobStatus, to: JobStatus): Boolean {
        return rules[from]?.contains(to) ?: false
    }
}

fun transitions(block: TransitionRules.() -> Unit): TransitionRules {
    return TransitionRules().apply(block)
}

val jobTransitions = transitions {
    JobStatus.CREATED canGoTo JobStatus.RUNNING
    JobStatus.RUNNING canGoTo JobStatus.COMPLETED
    JobStatus.RUNNING canGoTo JobStatus.FAILED
}