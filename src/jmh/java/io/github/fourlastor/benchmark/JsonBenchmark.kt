package io.github.fourlastor.benchmark

import com.google.gson.Gson
import io.github.fourlastor.benchmark.json.CitmCatalog
import io.github.fourlastor.benchmark.json.CitmCatalogGdx
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.openjdk.jmh.annotations.*
import java.util.concurrent.*

@Warmup(iterations = 7, time = 1)
@Measurement(iterations = 7, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
@Fork(2)
open class JsonBenchmark {
    /*
     * From https://github.com/Kotlin/kotlinx.serialization/blob/master/benchmark/
     */
    private val input = JsonBenchmark::class.java.getResource("/citm_catalog.json")!!.readBytes().decodeToString()
    private val citm = Json.decodeFromString(CitmCatalog.serializer(), input)
    private val gson = Gson()
    private val gdxJson = com.badlogic.gdx.utils.Json()
    private val citmGdx = decodeCitmGdx()

    @Setup
    fun init() {
        require(citm == Json.decodeFromString(CitmCatalog.serializer(), Json.encodeToString(citm)))
    }

    @Benchmark
    fun decodeCitmNative(): CitmCatalog = Json.decodeFromString(CitmCatalog.serializer(), input)

    @Benchmark
    fun encodeCitmNative(): String = Json.encodeToString(CitmCatalog.serializer(), citm)

    @Benchmark
    fun decodeCitmGson(): CitmCatalog = gson.fromJson(input, CitmCatalog::class.java)

    @Benchmark
    fun encodeCitmGson(): String = gson.toJson(citm)

    @Benchmark
    fun decodeCitmGdx(): CitmCatalogGdx = gdxJson.fromJson(CitmCatalogGdx::class.java, input)

    @Benchmark
    fun encodeCitmGdx(): String = gdxJson.toJson(citmGdx)
}
