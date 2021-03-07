package ch.keepcalm.demo.kofu

import io.github.serpro69.kfaker.Faker
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldNotBeLessThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith

class MyTest : StringSpec({

    "length should return size of string" {
        "hello".length shouldBe 5
    }

    "startsWith should test for a prefix" {
        "world" should startWith("wor")
    }

    "should give back any first name with less than 1 character's" {
        Faker().name.firstName() shouldNotBeLessThan "1"
    }
})
