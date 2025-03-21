plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version  "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id ("com.netflix.dgs.codegen") version "7.0.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com"
version = "0.0.1-SNAPSHOT"


allOpen {
	annotation("jakarta.persistence.Entity")
	// 엔티티의 프록시와 호환성 때문
	// Lazy 로딩이 제대로 동작 안할수도 있다
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

extra["netflixDgsVersion"] = "10.0.4"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")
	implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:${property("netflixDgsVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.generateJava {
	language ="kotlin"
	packageName = "com.moview"
	typeMapping = mutableMapOf(
		"ID" to "Long"
	)
}