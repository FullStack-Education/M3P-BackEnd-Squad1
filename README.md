
# Scholargate - Plataforma de Gestão para Educação

## Sobre o projeto

O sistema Scholargate consiste em uma aplicação web direcionada à gestão de um ambiente educacional, ela engloba o cadastramento de Professores, Alunos, Cursos, Turmas, Matérias e Notas, bem como realiza a gestão e controle desses relacionamentos.

O Software foi desenvolvido pensando na integração das informações com um sistema de dados sólido, escalável, que permita a alocação e acesso célere aos recursos educacionais necessários à atividade de ensino.

Com a implementação de recursos de tecnologia da informação certamente poderemos imprimir agilidade e eficiência de gestão no trato administrativo de sistemas e estruturas de ensino, seja ele básico ou superior.

Não menos importante, ainda podemos citar a facilidade de acesso aos dados por parte de qualquer usuário por meio da rede mundial de computadores, disponibilizando as informações em qualquer tempo e local do globo, desde que provido de acesso a internet.





## Tecnologias utilizadas
### Back end
- Java
- Spring Boot
- Spring Security - JWT Token
- Swaggeg para documentação
- JPA / Hibernate
- Maven
- PostegreSQL (cliente ou docker)
- Docker (Dockerfile e Docker-compose)


## Artefato
modulo1_proj.avaliativo

## Swagger - Lista de endpoints e retorno
http://localhost:8080/v3/api-docs

http://localhost:8080/swagger-ui/index.html

## Clonar repositório - Back-end
https://github.com/FullStack-Education/M3P-BackEnd-Squad1

## Clonar repositório - Front-end
https://github.com/FullStack-Education/M3P-FrontEnd-Squad1

## Trello
https://trello.com/b/n0OCGxwR/m3p-backend-squad-1


## Execução e cuidados
Inicialmente, gizamos que a presente aplicação foi refatorada para uso do front-end acima referenciado, mas nada obsta ao seu uso por meio dos endpoins disponibilizados.

A execução da aplicação não reclama maiores cuidados, havendo o arquivo data.sql a gerar os cadastros iniciais para operação do sistemas. Salientamos, todavia, que o aspecto de usuário encontra-se dissociado da role posteriormente a se agregada. A exemplo disso temos o usuário 'admin', com senha '123', que encontra-se dissociado de qualquer outro papel Aluno ou Professor. A tentativa de se criar entidade Aluno/Professor sem usuário relacionado irá retornar um erro.

~~~java
    public DtoAlunoResponse criarAluno(DtoAlunoRequest aluno) {
        if (!usuarioRepo.existsById(aluno.getUsuario_id())){
            logger.error("Necessário um usuário cadastrado para se criar novo Aluno");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Impossível criar um Aluno sem um usuário não cadastrado."
            );
        }
~~~

Caso o cliente deseje criar um novo perfil, deverá inicialmente logar como administrador e realizar os cadastros mediante o token de autenticação fornecido pelo Spring Security. Nada obstante, reiteramos, o cadastro de Professor ou Aluno, importa no registro de um usuário respectiovo, o qual comporta senha/login e papel/role.

Sendo feito o uso do front-end em conjunto, o cadastramento das duas entidades é feito de forma automática pela aplicação em Angular, já aqui depende de requisição distinta e sequencial.

Qualquer que seja o caso, é primordial efetivar o cadastramento de novo ADMIN com senha diversa da padrão (login: root / senha:root).

Independente dos dados 'mockados', acrescentamos ainda a importância da ordem de cadastros junto ao sistema, em face de suas inequívocas dependências que visam a garantia da integridade dos dados.

Assim, a ordem seguida pelas requisições do Postman é sugerida (arquivo presente no projeto "/postman"), dado que, a exemplo do cadastro de Notas, não poderão elas ser cadastradas sem o anterior cadastro de Curso, Turma e Aluno.

A atribuição de Professor a um Curso ou Turma, entretanto, foi propositalemente deixada em aberto, dado que tais elementos podem ser criados posteriormente, com a inclusão do Docente, permitindo que seja alterado o Professor, ou outro seja atribuído.


Para uso do Postman pode-se usar o login inicial de admin:
~~~javascript
        {
        "login": "admin",
        "senha": "123"
        }
~~~

Anotamos que os mocks inseridos através do data.sql não contemplam necessariamente todos os campos de preenchimento obrigatório do Front-End, servindo apenas de 'start' ou exemplo para a inicialização do sistema.

Por fim, colacionamos as especificações do application.properties:

~~~java
spring.application.name=modulo1_proj.avaliativo

server.error.include-message=always

modulo1_proj.avaliativo.security.token.secret=${JWT_SECRET:senha-secreta}

spring.datasource.url=jdbc:postgresql://localhost:${PORT:5432}/${POSTGRES_DB:postgres}
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect

spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always

spring.jpa.show-sql=true
~~~


## Modelo para application.properties
~~~java
spring.application.name=modulo1_proj.avaliativo

server.error.include-message=always

modulo1_proj.avaliativo.security.token.secret=${JWT_SECRET:senha-secreta}

spring.datasource.url=jdbc:postgresql://localhost:${PORT:5432}/${POSTGRES_DB:postgres}
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect

spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always

spring.jpa.show-sql=true
~~~

## Considerações sobre a Segurança (importante!)
O sistema de segurança faz uso de autenticação stateless (Bearer Authentication), enviando um token ao usuario cadastrado que solicite login e, através dele, gerenciando as suas credenciais e "role" de acesso ao sistema.

Uma fez feito um cadastro de usuário, o seu login é armazenado no banco de dados (String), bem como sua senha em criptografada (hash).

Ao enviar suas credenciais para o serviço de login (POST) o sistema criptografa a senha recebida e a compara com a hash presente no banco de dados. Sendo positiva a comparação, retorna o token de autenticação ao usuário sem guardar estado.

Desta forma, o usuário ao se valer do Postman deverá incialmente realizar seu login com o usuário pretendido, recebendo o token de autenticação.

Na sequência, poderá realizar as operações em cada endpoint fazendo uso da chave/tolken recebida junto a guia ou header "Authentication".


## Melhorias a serem implementadas
Como sugestões ao aprimoramento do projeto podemos citar o controle em cascata para a deleção ou atualização de dados, que pode não ser exatamente oportuna em um sistema educacional, notadamente onde a preservação do histórico escolar e notas é de interesse da instituição e dos próprios alunos.

Também vale acrescentar a utilidade de se adicionar a funcionalidade de cálculo de média por matéria, item não previsto no projeto.

## MER - Relacionamento entre entidades

![MER](/MER/MER_projeto_avaliativo.png)

## Endpoints disponibilizados
Os endpoints encontram-se listados abaixo, a par da própria docunmentação com Swagger do projeto. Reiteramos que a aplicação foi desenvolvida para uso conjunto com o front-end, nada obstante responde a qualquer outra nos termos aqui apresentados.


### POST /dashboard
Funcionalidade nova agregada ao projeto final objetivando o retorno dos dados de número de alunos cadastrados, docentes e turmas. Integra estrura própria dentro da aplicação, trabalhando com os respectivos services para prover as estatísticas de uso do software.

~~~java
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private final DashBoadService dashBoadService;

    public DashboardController(DashBoadService dashBoadService) {
        this.dashBoadService = dashBoadService;
    }

    @GetMapping
    public ResponseEntity<DtoDashBoard> listarCursos() {
        logger.info("Solicitadas estatísticas para DashBoard.");
        return ResponseEntity.status(HttpStatus.OK).body(dashBoadService.buscarEstatisticas());
    }
}
~~~

### POST /login
Direcionado ao acesso e login no sistema por parte de qualquer usuário cadastrado.

### POST /cadastro
Acesso ao cadastramento de novos usuários.
Aqui será necessário indicar o Papel/Role do usuário, podendo ser ADMIN, PEDAGOGICO, PROFESSOR, RECRUITER ou ALUNO.

### POST /docentes
Direcionado ao cadastramento de novos docentes, que deverão ser usuários pré-cadastrados (a serem indicados pelo respectivo id).

### GET /docentes/id
Recupera os dados de um docente pelo ID indicado.

### PUT /docentes/id
Altera os dados cadastrais do docente indicado pelo respectivo ID.

### DELETE /docentes/id
Dedicado à exclusão do docente indicado pelo ID.

### GET /docentes
Lista todos os docentes cadastrados no sistema, listando por ID.

### POST /turmas
Endpoint destinado a fazer o cadastro de novas Turmas.

### GET /turmas/id
Destinado a recuperar os dados de uma turma, indicada pelo seu ID de cadastro.

### PUT /turmas/id
Ponto destinado a alterar as informações cadastrais de uma determinada turma, indicada pelo ID.

### DELETE /turmas/id
Destinado a excluir determinada turma (indicada por ID).

### GET /turmas
Serve a listar todas as turmas cadastradas no sistema.

### POST /alunos
Serve para cadastrar um novo Aluno, que, a semelhança de Docente, deve ser previamente cadastrado como Usuário (o qual será indicado pelo ID).

### GET /alunos/id
Recupera os dados cadastrais de um Aluno indicado pelo ID.

### PUT /alunos/id
Edita as informações cadastrais de um Aluno indicado pelo ID.

### DELETE /alunos/id
Exclui um determinado aluno do banco de dados, indicado pelo ID.

### GET /alunos
Retorna a listagem de todos os alunos cadastrados no sistema.

### POST /cursos
Destinado ao cadastramento de novos Cursos.

### GET /cursos/id
Retorna os dados do Curso indicado pelo seu ID.

### PUT /cursos/id
Edita os dados cadastrais do Curso indicado pelo seu ID.

### DELETE /cursos/id
Exclui da base de dados o Curso indicado pelo seu ID de cadastro.

### GET /cursos
Retorna a listagem de todos os cursos cadastrados no sistema.

### GET /cursos/{id_curso}/materias
Retorna todas as matérias cadastradas em um determinado curso, este indicado por ID.

### POST /materias
Cadastra uma nova matéria em um curso, este a ser indicado na requisição (body).

### GET /materias/id
Retorna os dados de uma determinada matéria cadastrada.

### PUT /materias/id
Altera os dados cadastrais de uma determinada matéria.

### DELETE /materias/id
Exclui da base de dados a matéria indicada pelo seu ID.

### GET /alunos/{id_aluno}/notas
Retorna todas as notas de um determinado aluno, indicado pelo seu ID.
Caso seja chamado por aluno, retornará apenas as suas próprias notas.

### POST /notas
Cadastra nova nota no sistema. Salientamos que notas só podem ser cadastradas em usuários que possuam a role "ALUNO".

### GET /notas/id
Retorna o valor de uma determinada nota, identificada pelo seu ID de cadastro.

### PUT /notas/id
Edita o valor de uma determinada nota, indicada pelo seu ID.

### DELETE /notas/id
Exclui da base de dados uma determinada nota, indicada pelo seu ID.

### GET /alunos/{id}/pontuacao
Retorna a pontuação total de um aluno, indicado pelo ID, conforme a métrica solicitada pelo projeto.





## Autores

David Alves Dutra

Barbara Calderon

Luís Pedro Trindade



