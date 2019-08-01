package app.getfeeling.feeling.api.models

data class ErrorsModel(val errors: List<Error>) {
    data class Error(
        val type: String,
        val message: String
    )
}
