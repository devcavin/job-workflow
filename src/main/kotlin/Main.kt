package io.github.devcavin

import io.github.devcavin.workflow.Job
import io.github.devcavin.workflow.JobStatus
import io.github.devcavin.workflow.JobStore
import io.github.devcavin.workflow.jobTransitions

fun main() {
    println("Job Workflow")

    val store = JobStore(jobTransitions)

    val job = store.add(Job(name = "Video processing"))

    store.transition(job.id, JobStatus.RUNNING)
    store.transition(job.id, JobStatus.COMPLETED)

    println(store.all())
}