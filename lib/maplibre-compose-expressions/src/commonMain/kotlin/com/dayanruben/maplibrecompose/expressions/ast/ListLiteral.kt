package com.dayanruben.maplibrecompose.expressions.ast

import com.dayanruben.maplibrecompose.expressions.ExpressionContext
import com.dayanruben.maplibrecompose.expressions.value.ExpressionValue
import com.dayanruben.maplibrecompose.expressions.value.ListValue

/** A [Literal] representing a JSON array. */
public data class ListLiteral<T : ExpressionValue>
private constructor(override val value: List<Literal<T, *>>) :
  Literal<ListValue<T>, List<Literal<T, *>>> {

  override fun compile(context: ExpressionContext): CompiledListLiteral<T> =
    CompiledListLiteral.of(value.map { it.compile(context) })

  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.forEach { it.visit(block) }
  }

  public companion object {
    internal fun <T : ExpressionValue> of(value: List<Literal<T, *>>): ListLiteral<T> =
      ListLiteral(value)
  }
}
