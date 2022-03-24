# REVIEW QUESTIONS

a) -

b) -
Podemos encontrar um exemplo de fazer mock ao comportamento de um repositório no ficheiro C_EmployeeController_WithMockServiceTest.java, presente no método whenPostEmployee_thenCreateEmployee()

```
when( service.save(Mockito.any()) ).thenReturn( alex);
```

c) - @Mock Pertence à Plain Mockito library, e permite criar um objeto Mock de uma classe/interface. So devemos utilizar @Mock em classes de teste.
Enquanto que o @MockBean pertence à Spring Boot library, sendo que o @MockBean é utilizado num contexto de Spring Boot, o mock irá então substituir qualquer bean que exista que seja do mesmo tipo no contexto da aplicação. Um novo bean é adicionado se nenhum bean do mesmo tipo esteja definido.

d) - O ficheiro application-integrationtest.properties contem os detalhes de configuração de armazenamento persistente. Este ficheiro deve ser utilizado caso queiramos manter dados persistentes enquantos estamos a correr testes.

e) -
