package io.moya.lalalandornickyminaj.testsupport.initializers

import org.slf4j.LoggerFactory
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ApplicationListener
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

private val log = LoggerFactory.getLogger(TestContainersContextInitializer::class.java)

class TestContainersContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {
        private val POSTGRES_DOCKER_IMAGE_NAME = DockerImageName.parse("flyio/postgres")
            .asCompatibleSubstituteFor("postgres")
    }

    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
        log.info("Starting TestContainers PostgreSQLContainer")
        val postgreSQLContainer = PostgreSQLContainer(POSTGRES_DOCKER_IMAGE_NAME)
        postgreSQLContainer.start()
        log.info("TestContainers PostgreSQLContainer successfully started")

        configurableApplicationContext.beanFactory.registerSingleton("postgreSQLContainer", postgreSQLContainer)

        configurableApplicationContext.addApplicationListener(
            ApplicationListener<ContextClosedEvent> {
                log.info("Stopping TestContainers PostgreSQLContainer")
                postgreSQLContainer.stop()
                log.info("TestContainers PostgreSQLContainer successfully stopped")
            },
        )

        TestPropertyValues.of(
            "spring.datasource.url=jdbc:postgresql://${postgreSQLContainer.host}:${postgreSQLContainer.firstMappedPort}/${postgreSQLContainer.databaseName}",
            "spring.datasource.username=${postgreSQLContainer.username}",
            "spring.datasource.password=${postgreSQLContainer.password}",
        ).applyTo(configurableApplicationContext)
    }
}
