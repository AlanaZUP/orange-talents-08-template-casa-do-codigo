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