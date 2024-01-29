plugins {
    java
    alias(libs.plugins.jmh)
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.ksp)
}

//group 'io.github.fourlastor.benchmarks'
//version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.gdx.core)
    implementation(libs.kotlin.std)
    implementation(libs.kotlinx.immutable)
    implementation(libs.kotlinx.serialization)
    implementation(libs.moshi.core)
    ksp(libs.moshi.codegen)
    implementation(libs.jsoniter)
    implementation(libs.gson)
    implementation(libs.pcollections)
}

val deleteEmptyBmList = tasks.register<Delete>("deleteEmptyBmList") {
    delete(layout.buildDirectory.dir("jmh-generated-classes/META-INF/BenchmarkList"))
}

tasks.jmhCompileGeneratedClasses.configure {
    finalizedBy(deleteEmptyBmList)
}

//jmhCompileGeneratedClasses.finalizedBy deleteEmptyBmList
//
jmh {
    fork = 1
    iterations = 2
    timeOnIteration.set("3s")
    warmup.set("3s")
    includes.add("ListsBenchmark")
}

tasks.test {
    useJUnitPlatform()
}
