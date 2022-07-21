# CHASQUI-DELIVERY

### Documentacion de referencia
Aplicación REST en Java con el framewordk `Spring Boot` con el objetivo de administrar la el catalogo de productos de delivery, tomando como estrategia de implementacion una Arquitectura Hexagonal y buenas pracitas como HATEOS, Spring Data Specification,

## Arquitectura Hexagonal
La arquitectura hexagonal es una arquitectura del software en la que se busca es separar el core lógico de la aplicación, dejarlo en el centro totalmente aislado del exterior, del cliente y de otras interacciones.

#### 1.Patrón Hexagonal – Modelo Domain

Un modelo domain es un modelo conceptual, una representación de conceptos significativos para el domain que han de ser modelados en el software. Los conceptos incluyen la información envuelta en el negocio y dirige los usos de negocio en relación a esa información.

#### 2.Puertos en las arquitecturas hexagonales

Un puerto es un punto de entrada, proveniente del centro de la lógica. 
Define una serie de funciones. Un puerto es el mediante el que la lógica de negocio es accesible.

#### 3. Adaptadores en arquitectura de software hexagonal
Un adaptador es un puente entre la aplicación y el servicio necesitado en una aplicación.
Se adapta a un puerto específico. Los adaptadores actúan de capa que sirve para transformar
la comunicación entre actores externos y la lógica de la aplicación de forma que ambas dos quedan independientes.


La aplicacion presenta la siguiente estructura:

```
└── src
    └── main
       └── java
           └── com
           │   └── imorochi
           │       └── chasqui
           │           │── application  
           │           │   └── engine
           │           │   └── search
           │           │   └── services
           │           │── domain
           │           │   └── document
           │           │   └── graphQL
           │           │   └── repository
           │           └── infrastructure
           │               └── config
           │               └── controller 
           resources
             └── static
             │    └── es-product.json
             └── application.yml                     
```

#### Herramientas desarrollo

* Spring boot 2.6.9
* Spring Data Elastisearch (Access+Driver)
* Gradle 7.4.1
* Java 11
* GraphQL
* OpenApi 1.6.6

## Configuraciones

#### Elastisearch
Se debe tener instalado elastisearch v8.1.0 las credenciales se deben ingregarse en 
el `application.yml`

```yml
elasticsearch:
  host: ${HOST_ELASTISEARCH}
  user: ${USER_ELASTISEARCH}
  password: ${PASSWORD_ELASTISEARCH}
```

Se debe crear el indice product con las configuraciones especificadas para aplicar 
analysis-phonetic [settings-index-producto.json](/src/main/resources/static/es-product.json)
```curl
PUT HOST_ELASTISEARCH/product
{
  ....
  contenido del archivo resources/static/es-product.json
}
```

#### Dgraph
Se debe tener una cuenta asociada al serivico Dgraph Cloud para obtener un hostname cliente.
```yml
slash-graph-ql:
  hostname: ${SLASH_GRAPH_QL_HOSTNAME}
```

#### GraphQL
Se debe crear los siguientes Schema:
* [Schema](https://github.com/IsaiasMorochi/chasqui-delivery/tree/master/src/main/resources/static/schema.json)

```graphql
type Category {
  categoryId: Int!
  categoryName: String!
}

type Customer {
  username: String! @id @search(by: [hash, regexp])
  ratings: [Rating] @hasInverse(field: by)
}

type Product {
  productId: Int!
  category: Category!
  productName: String! @id @search(by: [hash, regexp])
  productPrice: String!
  productPhotoUrl: String!
  ratings: [Rating] @hasInverse(field: about)
}

type Rating {
  id: ID!
  about: Product!
  by: Customer!
  score: Int @search
}
```

Seguido se debe poblar con data ejecutando las mutaciones en el archivo `mutation.json`

### Entregables
* [Repositorio](https://github.com/IsaiasMorochi/chasqui-delivery)
* [Index Product](https://github.com/IsaiasMorochi/chasqui-delivery/tree/master/src/main/resources/static/es-product.json)
* [Schema GraphQL](https://github.com/IsaiasMorochi/chasqui-delivery/tree/master/src/main/resources/static/schema.json)
* [Mutation GraphQL](https://github.com/IsaiasMorochi/chasqui-delivery/tree/master/src/main/resources/static/mutation.json)


### Additional Links

These additional references should also help you:

* [Dgraph](https://cloud.dgraph.io/_/explorer)
