<br>

<h1 align="center">SISTEMA DE INTEGRAÇÃO | Desafio Técnico LuizaLabs</h1>

<br>
<h2>Demonstração em vídeo, ACESSE:</h2> <a traget="_blank" href="https://www.youtube.com/watch?v=sA3TJZlYqRk">https://www.youtube.com/watch?v=sA3TJZlYqRk</a>
<br><br>
<br>
<img src="/clientLogistica/src/assets/thumbdesafio.png" alt="" width="100%">

<br>

<h1>Sumário</h1>
<ul>
  <li><a href="#descricao">Descrição do projeto</a></li>
  <li><a href="#tecnologias">Tecnologias usadas</a></li>
  <li><a href="#fluxo">Fluxo</a></li>
  <li><a href="#endpoint">Endpoint</a></li>
  <li><a href="#requisitos">Requisitos necessários</a></li>
  <li><a href="#iniciarProjeto">Como iniciar o projeto</a></li>
  <li><a href="#consideracoes">Considerações Finais</a></li>
  <li><a href="#melhorias">Melhorias</a></li>
</ul>

<br>

<a name="descricao">
<h1>Descrição do projeto</h1>

<p>Sistema de integração que recebe um arquivo.txt envia via Api para ser manipulado e retorna via Api no formato Json.</p>
<p>O Sistema possui funcionalidades de filtros por Id e Data.</p>
<br>
<p>Decidi também criar uma tela de upload de arquivos para o usuário, assim o usuário tem facilmente acesso as funcionalidades que ele precisa. Afinal todo usuário gosta de uma tela bonitinha pra interagir rs. Desenvolvi usando React.</p>


<br>

<a name="tecnologias">

### Backend: 
- [Java 17]
- [Spring Boot]
- [Maven]

### Fontend:
- [Javascript];
- [ReactJS];
- [Vite];
- [React-Router-DOM]


### Banco de dados:
- [SqlLite]

<br>

<a name="fluxo">
<h1>Fluxo do projeto</h1>

O frontend envia um arquivo de texto via API para o endpoint correspondente no backend.

A classe FileUploadController recebe a requisição e encaminha para a classe FileUploadService.

Na classe FileUploadService, o arquivo é manipulado e os dados resultantes são armazenados no banco de dados SQLite.

Após o armazenamento bem-sucedido, os dados são convertidos para o formato JSON.

A resposta em formato JSON é enviada de volta para o frontend via API.

O frontend exibe os dados na página, completando o ciclo.

**Configurações Adicionais**

CORS: O problema de CORS foi tratado utilizando a classe CorsConfig no pacote config.

Banco de Dados: A configuração do banco de dados é tratada pela classe DatabaseConfig no pacote config.
<br>
<br>
<a name="endpoint">
<h1>Endpoint</h1>

Endpoint da API:
<br>
http://localhost:8080/api/response
<br>
<br>

<a name="requisitos">
<h1>Requisitos Necessários</h1>

Antes de iniciar o projeto, certifique-se de ter os seguintes requisitos instalados em sua máquina:

Node.js:
Certifique-se de ter o Node.js instalado. Você pode baixá-lo em https://nodejs.org/.

Git:
O Git é necessário para clonar o repositório. Caso não tenha o Git instalado, faça o download em https://git-scm.com/.

Java Development Kit (JDK):
Instale o JDK para permitir a execução do backend em Java. Baixe-o em https://www.oracle.com/java/technologies/javase-downloads.html.

Maven:
O Maven é utilizado para a construção e gerenciamento de dependências do projeto Java. Faça o download em https://maven.apache.org/download.cgi.
Certifique-se de adicionar o Node.js e o Maven ao seu PATH do sistema para facilitar o acesso aos comandos.

<a name="iniciarProjeto">
<h1>Inicie o projeto</h1>

**Clone o Repositório:**
<br>
Execute git clone https://github.com/Dancurvello/LuizaLabsDesafioTecnico.git.
<br>
<br>

**Acesse a Pasta do Frontend:**
<br>
Navegue até LuizaLabsDesafioTecnico/clienteLogistica.
<br>
<br>

**Instale as Dependências do React:**
<br>
Execute npm install.
<br>
<br>

**Acesse a Pasta do Backend:**
<br>
Volte uma pasta para LuizaLabsDesafioTecnico/serverLogistica.
<br>
<br>

**Inicie o Backend com Maven:**
<br>
Execute mvn spring-boot:run.
<br>
<br>

**Acesse a Pasta do Frontend Novamente:**
<br>
Retorne para LuizaLabsDesafioTecnico/clienteLogistica.
<br>
<br>

**Inicie o Servidor de Desenvolvimento do Frontend:**
<br>
Execute npm run dev.
<br>
<br>

**Acesse o Aplicativo:**
<br>
Abra seu navegador e vá para http://localhost:3000.
A porta :3000 pode variar, certifique-se que a aplicação React está rodando na porta desejada.
<br>
Exemplo: http://localhost:5173



<br>

<a name="consideracoes">
<h1>Considerações finais</h1>

<p>
Caso queira ver uma prévia de como é o projeto, 
<a traget="_blank" href="https://www.youtube.com/watch?v=sA3TJZlYqRk">link</a> 
para ver o vídeo demonstrativo no Youtube.
  <a traget="_blank" href="https://www.youtube.com/watch?v=sA3TJZlYqRk">https://www.youtube.com/watch?v=sA3TJZlYqRk</a>
</p>


<a name="melhorias">
<h1>Próximas melhorias:</h1>

- Documentação da API com Swagger
- Containerizar a aplicação com Docker (fazendo)
- Refatoração da API:
      1 -
      2 -
      3 -
 
