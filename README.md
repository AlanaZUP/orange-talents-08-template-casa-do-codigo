# Casa do código

## 2 - Validação do email
**Necessidades**
O email do autor precisa ser único no sistema

**Resultado esperado**
Erro de validação no caso de email duplicado

### Minha resposta
    Para criar uma anotação personalizada, eu terei que seguir um artigo como apoio, no caso eu vou seguir esse aqui https://emmanuelneri.com.br/2017/05/30/criando-validacoes-de-bean-validation-customizadas/ .
    
    De acordo com o artigo, eu devo criar uma @interface que representará a minha anotação do email unico, ele receberá as anotações @Target para indicará onde ele é permitido para usar, no nosso caso apenas no FILED, nas variáveis globais; o @Retention para indicar como ela será armazenada, no nosso caso será RUNTIME, no tempo de execução; e o @Constraint para que a anotação seja uma restrição Bean Validation, ele terá o elemento validatedBy, onde passaremos a classe que implementa a restrição. Nossa anotação tem quatro atributos: mensagem que mostrará quando os dados não forem válidos no nosso caso será "Email já cadastrado"; grupos, permite dividir as regras em grupos para aplicar diferentes validações, no nosso caso não teremos nenhum grupo; payloads para transportar informações de metadados.
    
    Agora é necessário criar a classe que fará a restrição. Essa classe implementará o ConstraintValidator passando a interface criada e o objeto que será analisado a restrição. A lógica será de analisar se o email já existe no nosso banco, para isso precisaremos do EntityManager para fazer esse select do email.  Sobrescreveremos o método isValid onde será executado a validação, ele receberá um value referente ao email e um ConstraintValidatorContext. Nele faremos uma Query "select 1 from Autor a where a.email=:value", ou seja para ele selecionar se existe algum Autor com o email igual ao value recebido. Se o resultado da query estiver vazio, ou seja um tamanho igual a 0, então não existe nenhum email cadastrado, logo eu retorno true, senão já existe algum email com o valor recebido, logo eu retorno false.
    
    Podemos generalizar a anotação para que seja utilizada em qualquer campo, não apenas o email. Para isso na @interface iremos adicionar mais dois atributos, um do tipo string que representará o nome do campo e outro do tipo classe que referente a classe ao qual queremos analisar. Na classe que executa a restrição, teremos que sobrescrever o método initialize para recebermos o nome do campo e a classe recebida. E no método isValid, mudaremos a query para "select 1 from "+ classe.getName() +" t where t."+ nomeCampo +"=:value". Agora ele fará a analise de forma generalizada.

### Resposta Especialista
Crio uma classe de validação que implementa a interface Validator do Spring
Caso não tenha, crio um Repository para o Autor e faço uso da facilidade do Spring Data JPA(algo como findByEmail)
Adiciono o validador para ser utilizado no controller específico

### Resultado

Peso 7: Criação de um validador customizado do Spring para realizar a validação ou criação de um validador customizado da Bean Validation

Peso 2.5: Se for utilizado um validador do Spring, precisamos ver a utilização das facilidades do Repository ou o uso do EntityManager direto lá dentro.

Peso 0.5: Se for utilizado o Validador do Spring, precisa adicionar o objeto na lista de validadores a ser aplicado nos parâmetros dos métodos do controller. Se for utilizado a Bean validation precisa aplicar a anotação no atributo de email do DTO de entrada.

Penalidade - 1: Imaginação da regra direto no fluxo no controller. Só controlamos fluxos que não podem ser controlados pelas tecnologias base do projeto. A validação de dados pela borda mais externa da aplicação, pode ser controlada. Aqui é o típico caso que consideramos uma indireção com potencial interessante. Em vez de implementarmos um fluxo na mão, delegamos para uma tecnologia com código mais maduro em termos de testes no campo de batalha do que o da aplicação em si.

Penalidade - 2: Se ainda aparece um service no fluxo de cadastro, vamos dobrar a penalidade. Só vamos colocar indireção na aplicação quando realmente for necessário.

Penalidade - 2: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.

Penalidade - 2: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.


## Criação da Categoria

Necessidades
Toda categoria precisa de um nome
Restrições
O nome é obrigatório
O nome não pode ser duplicado
Resultado esperado
Uma nova categoria cadastrada no sistema e status 200 retorno
Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação
### Resposta

Para implementar essa funcionalidade primeiro eu criaria uma classe Categoria anotada com @Entity, essa classe teria os seguintes atributos: private Long id com as anotações @Id e @GeneratedValue(strategy = GenerationType.IDENTITY) que representa a identificação do sregistro; private String nome com a anotação @NotEmpty para que ele seja obrigatório. Junto criaremos também o construtor da classe e os getters.

Com a classe criada, irei implementar as DTOs do Request e Response . O DTO que receberá os dados do request chamarei de CategoriaForm, ele terá um atributo private String nome com as anotações @NotEmpty para que ele seja obrigatório e @ValorUnico (anotação criada no exercício anterior) para que possamos garantir que o registro terá um valor único. Junto teremos o construtor da classe e um método que converterá o registro pro formato Categoria. O DTO que receberá os dados do response chamarei de CategoriaDTO, ele terá o atributo private Long id e o atributo private String nome. Junto teremos o construtor da classe que receberá uma Categoria e os getters da classe.

Agora só falta o controller que receberá a requisição POST. O controller será uma classe CategoriaController com a anotação @RestController e @RequestMapping("/categorias"). Ele terá um atributo EntityManager com a anotação @PersistenceContext. Ele também terá um método adicionarCategoria com a anotação @PostMapping, esse método tratará as requisições do tipo POST, ele receberá um objeto do tipo CategoriaForm com a anotaçõa @RequestBody(que indica onde ele deve pegar os dados) e a anotação @Valid (para validar os dados). Iremos então converter o CategoriaForm para Categoria, com o EntityManager faremos o persist da categoria, converteremos para o modelo CategoriaDTO e retornaremos o Status ok.

Caso ocorra algum erro de validação, ele cairá no @RestControllerAdvice, que tratará o erro retornando o status 400 e um json amigável dos erros encontredos.

### Pontuação
Peso 2: Criação do método para receber a requisição de uma nova Categoria anotado com a configuração para receber um POST, configurado para validar entrada de dados e também para receber os dados como JSON.
Peso 2: Utilização de uma classe específica para receber os dados da nova Categoria. O famoso DTO.
Peso 1: Utilização das annotations de validação da Bean Validation na classe do DTO
Peso 1: Criação da classe que representa a Categoria com as informações
Peso 1: Utilização das annotations de validação da Bean Validation na classe do Categoria
Peso 1: Utilização do construtor na classe Categoria para deixar claro os dados que são obrigatórios
Peso 1: Utilização do EntityManager ou Repository específico para a inserção da Categoria
Peso 1: Criação da tabela no banco de dados com as colunas necessárias

Penalidade - 2: Criação de setters e getters na classe Categoria. Não é necessário dada a especificação.
Penalidade - 3: Criação de uma classe, comumente conhecida como Service para realizar a inserção da Categoria. Não é necessária dada a especificação. Não tem nada lá que sugira a necessidade da flexibilidade que essa indireção traria.
Penalidade - 3: Criação de uma classe específica de conversão entre os dados que vieram da requisição e o objeto do tipo Categoria, comumente conhecida como Converter. Não é necessária porque pode ser feito direto no método do controller ou através de um método na classe que representa o DTO.
Penalidade - 3: Utilização de biblioteca de conversão tipo ModelMapper, MapStruct. Não é necessário porque entendemos que a configuração necessária e o entendimento extra necessário não compensam os possíveis benefícios.
Penalidade - 3: Utilização de biblioteca de geração de código compilado estilo Lombok. Não é necessário porque entendemos que o benefício trazido pelo código gerado não compensa o esforço de entendimento necessário que existe por trás de tal geração.

### Resposta especialista
Crio um controller com um método para receber o post com os dados da nova categoria.
O argumento do método do controller é do tipo de uma classe no estilo DTO para receber os dados da nova categoria. Ali também eu uso a anotação @Valid e @RequestBody para indicar a necessidade de validação e que o dado vem no corpo da requisição.
Na classe que tem os dados de entrada, utilizo as annotations da Bean Validation para sinalizar as validações necessárias
Dentro do método do controller eu converto os dados do DTO para a criação de um objeto do tipo Categoria. Faço através de um método adicionado no DTO que retorna uma Categoria em função dos valores dos atributos do DTO.
Crio a classe Categoria com os atributos devidamente anotados com a Bean Validation.
Também faço o mapeamento na classe Categoria para que o Hibernate consiga persistir os objetos
Recebo injetado o EntityManager no controller e gravo a categoria no banco de dados.
Deixo o Hibernate criar a tabela para mim no banco de dados. Poderia ter criado também o scritpt e executado contra o banco
