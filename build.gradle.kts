plugins {
    kotlin("jvm") version "2.0.20-Beta1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = "systems.rishon"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:1.18.32")

    implementation("redis.clients:jedis:5.1.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

val targetJavaVersion = 17
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.shadowJar {
    archiveBaseName.set("VelocitySync")
    archiveClassifier.set("")
    mergeServiceFiles()
}

publishing {
    repositories {
        maven {
            name = "seladevelopment-repo"
            url = uri("https://repo.rishon.systems/releases")
            credentials {
                username = System.getenv("MAVEN_NAME")
                password = System.getenv("MAVEN_SECRET")
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "systems.rishon"
            artifactId = "velocitysync-api"
            version = "${project.version}"
            from(components["java"])
        }
    }
}