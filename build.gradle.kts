plugins {
    id("java")
    id("java-library")
    id("maven-publish")
}

group = "br.com.act"
version = "1.0.3"
val jasperVersion = "6.20.0"

repositories {
    mavenCentral()
    maven { url = uri("https://jasperreports.sourceforge.net/maven2/") }
    maven { url = uri("https://jaspersoft.jfrog.io/jaspersoft/third-party-ce-artifacts/") }
}

dependencies {
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
    implementation("org.glassfish.expressly:expressly:5.0.0")

    // Report
    implementation("net.sf.jasperreports:jasperreports-metadata:${jasperVersion}")
    implementation("net.sf.jasperreports:jasperreports-functions:${jasperVersion}")
    implementation("net.sf.jasperreports:jasperreports-fonts:${jasperVersion}")
    implementation("net.sf.jasperreports:jasperreports:${jasperVersion}")
    implementation("xerces:xercesImpl:2.12.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

publishing {
    publications {
        create<MavenPublication>("${rootProject.name}") {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
