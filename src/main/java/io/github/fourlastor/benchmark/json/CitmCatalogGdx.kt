package io.github.fourlastor.benchmark.json

import java.util.HashMap

class CitmCatalogGdx{
    val areaNames: HashMap<String, String>? = null
    val blockNames: HashMap<String, String>? = null
    val events: HashMap<String, CitmEventGdx>? = null
    val audienceSubCategoryNames: HashMap<String, String>? = null
    val performances: List<CitmPerformanceGdx>? = null
    val seatCategoryNames: HashMap<String, String>? = null
    val subTopicNames: HashMap<String, String>? = null
    val subjectNames: HashMap<String, String>? = null
    val topicNames: HashMap<String, String>? = null
    val topicSubTopics: HashMap<String, List<Int>>? = null
    val venueNames: HashMap<String, String>? = null
}

class CitmPerformanceGdx {
    val eventId: Int? = null
    val id: Int? = null
    val logo: String? = null
    val name: String? = null
    val prices: List<CitmPriceGdx>? = null
    val seatCategories: List<CitmSeatCategoryGdx>? = null
    val seatMapImage: String? = null
    val start: Long? = null
    val venueCode: String? = null
}

class CitmSeatCategoryGdx {
    val areas: List<CitmAreaGdx>? = null
    val seatCategoryId: Int? = null
}

class CitmAreaGdx {
    val areaId: Int? = null
    val blockIds: List<String>? = null
}

class CitmPriceGdx {
    val amount: Int? = null
    val audienceSubCategoryId: Int? = null
    val seatCategoryId: Int? = null
}

class CitmEventGdx {
    val description: String? = null
    val id: Int? = null
    val logo: String? = null
    val name: String? = null
    val subTopicIds: List<Int>? = null
    val subjectCode: Int? = null
    val subtitle: String? = null
    val topicIds: List<Int>? = null
}
