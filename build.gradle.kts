plugins {
    id("java")
}

group = "org.aston.prod"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(platform("io.qameta.allure:allure-bom:2.24.0"))
    testImplementation("io.qameta.allure:allure-junit-platform")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("tools.jackson.core:jackson-databind:3.1.4")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation(platform("org.spockframework:spock-bom:2.3-groovy-4.0"))
    testImplementation("org.spockframework:spock-core")
}



tasks.test {
    useJUnitPlatform()
    systemProperty("allure.results.directory", "$buildDir/allure-results")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<ProcessResources>().configureEach {
    filteringCharset = "UTF-8"
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("-Dfile.encoding=UTF-8", "-Dsun.stdout.encoding=UTF-8", "-Dsun.stderr.encoding=UTF-8")
}
