package me.hero.tobywebflux.r2dbc

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

/**
 * Spring Data R2DC는 Immutable 한 Entity 관리가 가능하다. 따라서 Data Class 사용이 가능하다.
 */
@Table
data class Book(
    @Column
    val name: String,

    @Column
    val price: Int,

    @Id
    val id: Long? = null,
)
