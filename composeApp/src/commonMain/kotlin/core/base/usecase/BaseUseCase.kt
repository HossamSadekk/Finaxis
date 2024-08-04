package core.base.usecase

abstract class BaseUseCase<in Params, out Result> {
    abstract suspend fun execute(params: Params): Result
}
data class TwoStringParams(val firstString: String, val secondString: String)
