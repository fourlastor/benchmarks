package io.github.fourlastor.benchmark

import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.pcollections.TreePVector
import java.util.concurrent.*
import kotlin.random.Random

@Warmup(iterations = 7, time = 1)
@Measurement(iterations = 7, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(2)
open class ListsBenchmark {
    private val random = Random
    private val list = List(1000) { random.nextInt() }
    private val immutableList = list.toImmutableList()
    private val persistentList = list.toPersistentList()
    private val pImmutableList = TreePVector.from(list)
    private val smallList = List(100) { random.nextInt() }
    private val smallImmutableList = smallList.toImmutableList()
    private val smallPersistentList = smallList.toPersistentList()
    private val smallPImmutableList = TreePVector.from(smallList)

    private var numberToAdd: Int = 0

    @Setup(Level.Invocation)
    fun setup() {
        numberToAdd = random.nextInt()
    }

    @Benchmark
    fun list(blackhole: Blackhole) {
        for (i in list) {
            blackhole.consume(i)
        }
    }
    @Benchmark
    fun immutableList(blackhole: Blackhole) {
        for (i in immutableList) {
            blackhole.consume(i)
        }
    }

    @Benchmark
    fun persistentList(blackhole: Blackhole) {
        for (i in persistentList) {
            blackhole.consume(i)
        }
    }
    @Benchmark
    fun pImmutableList(blackhole: Blackhole) {
        for (i in pImmutableList) {
            blackhole.consume(i)
        }
    }
    @Benchmark
    fun toImmutableList(blackhole: Blackhole) {
        blackhole.consume(list.toImmutableList())
    }
    @Benchmark
    fun toPersistentList(blackhole: Blackhole) {
        blackhole.consume(list.toPersistentList())
    }
    @Benchmark
    fun toPImmutableList(blackhole: Blackhole) {
        blackhole.consume(TreePVector.from(list))
    }

    @Benchmark
    fun listAdd(blackhole: Blackhole) {
        blackhole.consume(list + numberToAdd)
    }
    @Benchmark
    fun immutableListAdd(blackhole: Blackhole) {
        blackhole.consume(immutableList + numberToAdd)
    }
    @Benchmark
    fun persistentListAdd(blackhole: Blackhole) {
        blackhole.consume(persistentList + numberToAdd)
    }
    @Benchmark
    fun pImmutableListAdd(blackhole: Blackhole) {
        blackhole.consume(pImmutableList + numberToAdd)
    }
    @Benchmark
    fun listConcat(blackhole: Blackhole) {
        blackhole.consume(list + smallList)
    }
    @Benchmark
    fun immutableListConcat(blackhole: Blackhole) {
        blackhole.consume(immutableList + smallImmutableList)
    }
    @Benchmark
    fun persistentListConcat(blackhole: Blackhole) {
        blackhole.consume(persistentList + smallPersistentList)
    }
    @Benchmark
    fun pImmutableListConcat(blackhole: Blackhole) {
        blackhole.consume(pImmutableList + smallPImmutableList)
    }
}
