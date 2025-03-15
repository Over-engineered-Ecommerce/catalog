Feature: ProductRepository


  Scenario: Add a new Product into database with null category
    When a Product is created with name "Ipod" and brand "Apple" and EAN "0194252031551" and null categories
    Then the result should be a Product with name "Ipod" and brand "Apple" and EAN "0194252031551" and generatedId
    And delete product with generatedId


  Scenario: Add a new Product into database
    When a Product is created with name "Ipod 2" and brand "Apple" and EAN "0194252031552"
    Then the result should be a Product with name "Ipod 2" and brand "Apple" and EAN "0194252031552" and generatedId
    And delete product with generatedId




