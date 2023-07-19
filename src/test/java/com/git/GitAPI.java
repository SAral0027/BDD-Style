package com.git;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

import java.io.File;
import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.base.UtilityClass;

public class GitAPI extends UtilityClass{
	@BeforeMethod
	public void beforeMethod() throws IOException {
		baseURI = getPropertyFileValue("baseURI");
	}

	@Test(priority = 0)
	public void getUser() {
		given()
		.when() 
			.get("users/SAral0027")
		.then()
			.assertThat().statusCode(200).body("id", isA(Integer.class)).log().all();
	}
	@Test(priority = 1)
	public void getRepo() {
		given()
		.when()
			.get("users/SAral0027/repos")
		.then()
			.assertThat().statusCode(200).body("[1].name", equalTo("BenchStudy")).log().all();
	}

	@Test(priority = 2)
	public void create() throws IOException {
		given()
			.header("Authorization", "Bearer " + getPropertyFileValue("token"))
			.header("Content-Type", "application/json")
			.body(new File(getPropertyFileValue("jsonPathOfCreateRepo")))
		.when()
			.post("user/repos")
		.then()
			.assertThat().statusCode(201).body("name", equalTo("REST")).log().all();
	}

	@Test(priority = 3)
	public void update() throws IOException {
		given()
			.header("Authorization", "Bearer " + getPropertyFileValue("token"))
			.header("Content-Type", "application/json")
			.body(new File(getPropertyFileValue("jsonPathOfUpdateRepo")))
		.when()
			.patch("repos/SAral0027/REST")
		.then()
			.assertThat().statusCode(200).body("name", equalTo("RESTAssured")).log().all();
	}

	@Test(priority = 4)
	public void delete() throws IOException {
		given()
			.header("Authorization", "Bearer " + getPropertyFileValue("token"))
		.when()
			.delete("repos/SAral0027/RESTAssured")
		.then()
			.assertThat().statusCode(204).log().all();
	}
}
