package io.logantect.springbatch.job

import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class StepNextJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
) {
    private val log = LoggerFactory.getLogger(javaClass)
    @Bean
    fun stepNextJob(): Job {
        return jobBuilderFactory.get("stepNextJob")
            .start(step1())
            .next(step2())
            .next(step3())
            .build()
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory["step1"]
            .tasklet { _: StepContribution, _: ChunkContext ->
                log.info(">>>>> This is StepNextJob Step1")
                RepeatStatus.FINISHED
            }
            .build()
    }

    @Bean
    fun step2(): Step {
        return stepBuilderFactory["step2"]
            .tasklet { _: StepContribution, _: ChunkContext ->
                log.info(">>>>> This is StepNextJob Step2")
                RepeatStatus.FINISHED
            }
            .build()
    }

    @Bean
    fun step3(): Step {
        return stepBuilderFactory["step3"]
            .tasklet { _: StepContribution, _: ChunkContext ->
                log.info(">>>>> This is StepNextJob Step3")
                RepeatStatus.FINISHED
            }
            .build()
    }
}