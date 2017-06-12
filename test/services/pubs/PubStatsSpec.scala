package services.pubs

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PubStatsSpec extends Specification {

  "PubUserStatsService#getPubStats" should {

    "return total pubs" in {
      PubStats(Seq(hartyNotVisited)).total mustEqual(1)
    }

    "return zero visited when no pubs visited" in {
      PubStats(Seq(hartyNotVisited)).visited mustEqual(0)
    }

    "return zero percentage when no pubs visited" in {
      PubStats(Seq(hartyNotVisited)).percentage mustEqual(0)
    }

    "return one visited when one pub visited" in {
      PubStats(Seq(constantVisited)).visited mustEqual(1)
    }

    "return 100% visited when one of one pub visited" in {
      PubStats(Seq(constantVisited)).percentage mustEqual(100)
    }

    "return one visited when one of two pub visited" in {
      PubStats(Seq(constantVisited, hartyNotVisited)).visited mustEqual(1)
    }

    "return 50% visited when one of two pub visited" in {
      PubStats(Seq(constantVisited, hartyNotVisited)).percentage mustEqual(50)
    }
  }

  private def hartyNotVisited = {
    SimplePub(1, "Hartington", false)
  }

  private def constantVisited = {
    SimplePub(2, "Constant", true)
  }
}
