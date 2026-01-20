package io.github.devcavin.workflow

import java.util.UUID

class JobStore(
    private val transitions: TransitionRules,
    private val jobs: MutableList<Job> = mutableListOf()
) {

    fun add(job: Job): Job {
        jobs.add(job)
        return job
    }

    fun byId(id: UUID): Job =
        jobs.find { it.id == id }
            ?: error("Job not found")

    fun transition(id: UUID, target: JobStatus): Job {
        val current = byId(id)

        if (!transitions.isAllowed(current.status, target)) {
            error("Invalid transition: ${current.status} → $target")
        }

        val updated = current.withStatus(target)

        jobs.remove(current)
        jobs.add(updated)

        return updated
    }

    fun all(): List<Job> = jobs
}