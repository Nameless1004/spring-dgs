package com.moview.scalar

import com.netflix.graphql.dgs.DgsScalar
import graphql.GraphQLContext
import graphql.execution.CoercedVariables
import graphql.language.StringValue
import graphql.language.Value
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingSerializeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

// @DgsScalar(name = "Date")
class DateScalar : Coercing<LocalDate, String> {

    /**
     * 서버에서 클라이언트로 데이터를 보낼 때 타입을 변환시킬 수 있다.
     */
    override fun serialize(dateFetcherResult: Any, graphQLContext: GraphQLContext, locale: Locale): String? {
        if(dateFetcherResult is LocalDate) {
            return dateFetcherResult.format(DateTimeFormatter.ISO_DATE)
        } else {
            throw CoercingSerializeException("유효한 Date가 아닙니다.")
        }
    }

    /**
     * 클라이언트에서 서버로 데이터를 보낼때 쿼리에 하드코딩한 값을 변환할 수 있다.
     */
    override fun parseLiteral(
        input: Value<*>,
        variables: CoercedVariables,
        graphQLContext: GraphQLContext,
        locale: Locale
    ): LocalDate? {
        if(input is StringValue) {
            return LocalDate.parse(input.value, DateTimeFormatter.ISO_DATE)
        } else {
            throw  CoercingParseLiteralException("값이 유효한 ISO 날짜가 아닙니다.")
        }
    }

    /**
     * 클라이언트에서 서버로 보낼 때 Variables를 사용한 값들을 변환할 수 있다.
     */
    override fun parseValue(input: Any): LocalDate? {
        return LocalDate.parse(input.toString(), DateTimeFormatter.ISO_DATE)
    }
}