Feature: Category

  Scenario: Successfully reach products endpoint
    When a valid request is made to retrieve all products
    Then the response status should be 200

    Scenario: Successfully create product
    When a request is made to create a product called "Apple iPhone 12 6,1'' 128GB Blanco" from the brand "Mac" with EAN "0194252031551"
    Then the response status should be 201
      And the response should contain detail "4" details
      And the response should contain detail key "color" value "White"
      And the response should contain detail key "Device Size" value "6,1"
      And the response should contain detail key "version" value "12"
      And the response should contain detail key "Storage" value "128GB"

