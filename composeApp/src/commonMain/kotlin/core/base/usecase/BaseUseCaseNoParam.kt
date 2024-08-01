package core.base.usecase

abstract class BaseUseCaseNoParam<out Result> {
    abstract suspend fun execute(): Result
}