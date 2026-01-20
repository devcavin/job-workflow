package io.github.devcavin.workflow

import java.util.UUID

data class Job(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    var status: JobStatus = JobStatus.CREATED
) {
    fun withStatus(newStatus: JobStatus): Job {
        return copy(status = newStatus)
    }
}