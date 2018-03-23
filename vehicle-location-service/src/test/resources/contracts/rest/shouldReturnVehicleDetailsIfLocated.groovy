package contracts.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("""
Represents a successful scenario of locating a vehicle

```
given:
	vehicle to locate
when:
	asked to locate
then:
	will return search results
```

""")
    request {
        method 'GET'
        headers {
            accept(applicationJson())
        }
        urlPath('/locate') {
            queryParameters {
                parameter 'vehicle': "SpringRX"
            }
        }
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body(
                "id": value(anyNonBlankString()),
                "description": value(anyNonBlankString())
        )
    }
}


