import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    idea
    id("org.springframework.boot") version "2.2.2.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    kotlin("jvm") version "1.3.61" apply false
    kotlin("plugin.spring") version "1.3.61" apply false
    kotlin("plugin.jpa") version "1.3.61" apply false
}

subprojects {

    group = "de.cwrose"

    apply {
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("io.spring.dependency-management")
    }

    repositories {
        jcenter()
    }

    dependencies {
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        "testImplementation"("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:2.2.2.RELEASE")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

project(":domain")

project(":rest") {

    dependencies {
        "compile"(project(":domain"))

        "implementation"("org.springframework.boot:spring-boot-starter-hateoas")
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
    }
}

project(":storage") {

    dependencies {
        "compile"(project(":domain"))

        "implementation"("org.springframework.boot:spring-boot-starter-data-jpa")
        "runtime"("com.h2database:h2:1.4.199")
    }
}

project(":main") {

    apply(plugin = "application")
    apply(plugin = "org.springframework.boot")

    configure<ApplicationPluginConvention> {
        mainClassName = "todo.ApplicationKt"
    }

    val developmentOnly by configurations.creating
    configurations {
        "runtimeClasspath" {
            extendsFrom(developmentOnly)
        }
    }

    dependencies {
        "compile"(project(":rest"))
        "compile"(project(":storage"))

        "implementation"("org.springframework.boot:spring-boot-starter")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
    }
}