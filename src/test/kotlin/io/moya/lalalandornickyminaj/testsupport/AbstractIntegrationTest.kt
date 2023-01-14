package io.moya.lalalandornickyminaj.testsupport

import io.moya.lalalandornickyminaj.testsupport.initializers.TestContainersContextInitializer
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

@ActiveProfiles("test")
@ContextConfiguration(initializers = [TestContainersContextInitializer::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class AbstractIntegrationTest {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var tfs: TestFixtureService

    @AfterEach
    fun cleanUpDatabase() {
        tfs.clearData()
    }
}
