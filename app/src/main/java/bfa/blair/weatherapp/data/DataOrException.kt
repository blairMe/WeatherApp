package bfa.blair.weatherapp.data

class DataOrException<T, Boolean, E: Exception>(
    var data : T? = null,
    var loading : Boolean? = null,
    val e : E? = null
)