package utn.methodology.domain.entities

interface DomainEvent {
    abstract val eventName: String
}