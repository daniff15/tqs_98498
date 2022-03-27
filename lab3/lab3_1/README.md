# REVIEW QUESTIONS

a)
2 Exemplos de assertJ expressive methods chaining:

```
Ficheiro: C_EmployeeController_WithMockServiceTest.java

mvc.perform(
    get("/api/employees").contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$", hasSize(3)))
    .andExpect(jsonPath("$[0].name", is(alex.getName())))
    .andExpect(jsonPath("$[1].name", is(john.getName())))
    .andExpect(jsonPath("$[2].name", is(bob.getName()))
);
```

```
Ficheiro: A_EmployeeRepositoryTest.java

assertThat(allEmployees).hasSize(3).extracting(Employee::getName)
    .containsOnly(alex.getName(), ron.getName(), bob.getName()
);
```

b)
Podemos encontrar um exemplo de fazer mock ao comportamento de um repositório no ficheiro C_EmployeeController_WithMockServiceTest.java, presente no método whenPostEmployee_thenCreateEmployee()

```
when( service.save(Mockito.any()) ).thenReturn( alex);
```

c)

@Mock

Pertence à Plain Mockito library, e permite criar um objeto Mock de uma classe/interface. So devemos utilizar @Mock em classes de teste.

@MockBean

Pertence à Spring Boot library, sendo que o @MockBean é utilizado num contexto de Spring Boot, o mock irá então substituir qualquer bean que exista que seja do mesmo tipo no contexto da aplicação. Um novo bean é adicionado se nenhum bean do mesmo tipo esteja definido.

d)

O ficheiro application-integrationtest.properties contem os detalhes de configuração de armazenamento persistente (bases de dados real).

Este ficheiro deve ser utilizado caso queiramos manter dados persistentes enquantos estamos a correr testes.

e) -
