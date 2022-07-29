import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    /**
     * default library
     * - kotlin-stdlib-jdk8 is the Java 8 variant of Kotlin standard library
     * - kotlin-reflect is Kotlin reflection library
     * - jackson-module-kotlin adds support for serialization/deserialization of Kotlin classes and data classes
     */
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val docDir = "src/main/resources/static/docs"

tasks.asciidoctor {
    dependsOn(tasks.test) // test snippets 파일이 생성되므로 test 이후에 실행
    baseDirFollowsSourceDir() // 기본 경로 설정(/src/docs/asciidoc), include 파일의 경로
    doFirst {
        delete(docDir)
    }
    doLast { // 생성된 파일 복사
        copy {
            from(outputDir)
            into(docDir)
        }
    }
}

tasks.build {
    dependsOn(tasks.asciidoctor)
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
}

tasks.clean {
    delete(docDir)
}
