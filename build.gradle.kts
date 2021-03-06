import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"

    id("org.jmailen.kotlinter") version "3.3.0"
    `java-library`
    `maven-publish`
    jacoco
}


val version = " 0.0.0.SNAPSHOT"
val group  = "ch.keepcalm.demo"


repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://repo.spring.io/milestone")
    maven("https://repo.spring.io/snapshot")
}

dependencies {
    // KoFu
    implementation("org.springframework.fu:spring-fu-kofu:0.5.0-SNAPSHOT")
    // lambda based type safe validation
    implementation("am.ik.yavi:yavi:0.5.0")

    //	Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    // Reactor (Webflux / Coroutine)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter")

    // ---------------> Test Dependencies <--------------- //
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    // kotest
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.4.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.4.1")
    testImplementation("io.kotest:kotest-property-jvm:4.4.1")

    // kotlin-faker
    testImplementation("io.github.serpro69:kotlin-faker:1.6.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlinter {
    ignoreFailures = true
    indentSize = 4
    reporters = arrayOf("checkstyle", "plain", "html")
    experimentalRules = false
    disabledRules = arrayOf("no-wildcard-imports")
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.destination = file("$buildDir/jacocoHtml")
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            enabled = true
            element = "CLASS"
            excludes = listOf("com.jacoco.dto.*")
            limit {
                counter = "BRANCH"
                minimum = 0.0.toBigDecimal()
            }
        }
    }
}

tasks.test {
    // report is always generated after tests run
    finalizedBy(tasks.jacocoTestReport)
    finalizedBy(tasks.jacocoTestCoverageVerification)
}
