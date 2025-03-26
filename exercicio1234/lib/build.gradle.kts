plugins {
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.0"
}

repositories {
    mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

    // H2 Database for in-memory database (You can replace this with your actual DB like MySQL, PostgreSQL)
    runtimeOnly("com.h2database:h2")

    // Spring Boot Test Starter
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Kotlin standard library for JDK 8
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // JUnit 5 for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    // JUnit 4 (if needed for backward compatibility)
    testImplementation("junit:junit:4.13.2")

    // Apache Commons Math (optional dependency)
    api("org.apache.commons:commons-math3:3.6.1")

    // Guava (optional dependency)
    implementation("com.google.guava:guava:32.1.1-jre")

    // ModelMapper
    implementation("org.modelmapper:modelmapper:3.1.1")

    // Hibernate Validator (Validation)
    implementation("org.hibernate.validator:hibernate-validator:6.1.7.Final")


    // Jakarta Persistence API (for EntityManager and JPA)
    implementation("jakarta.persistence:jakarta.persistence-api:3.0.0")
    
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.test {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

springBoot {
    mainClass.set("desafiotinnova.exercicio5.Application")
}
