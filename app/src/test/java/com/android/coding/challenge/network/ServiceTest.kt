package com.android.coding.challenge.network

import com.android.coding.challenge.BuildConfig
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.After
import org.junit.Test

/**
 * Created on 9/28/2019
 * Email ynaseem@an10.io
 * Organization AN10
 */
class ServiceTest {

    private var spec: RequestSpecification = RequestSpecBuilder().
        setContentType(ContentType.JSON).
        setBaseUri(BuildConfig.API_BASE_URL).
        addFilter(ResponseLoggingFilter()).
        addFilter(RequestLoggingFilter()).build()


    /**
     * Unit Test Case for Checking the API Response
     */
    @Test
    fun moviesListApi(){
        given()
            .spec(spec)
            .`when`()
            .get("movies")
            .then()
            .statusCode(200)
    }
}