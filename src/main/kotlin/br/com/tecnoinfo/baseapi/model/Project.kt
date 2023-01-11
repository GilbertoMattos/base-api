package br.com.tecnoinfo.baseapi.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "PROJECT")
data class Project(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long? = null,

        @Column(name = "NAME")
        val name: String,

        @Column(name = "CREATE_DATE")
        val creationDate: LocalDate,

        @Column(name = "SIZE")
        @Enumerated(EnumType.STRING)
        val size: SizeEnum
)

enum class SizeEnum {
    SMALL,
    MEDIUM,
    BIG
}