# AdotePet
<h4>O projeto AdotePet é um projeto de ideia autoral, onde ONG's podem se cadastrar e adicionar seus animais para que as pessoas vejam quais animais estão no abrigo, todos os animais possuem dados, como: nome, idade, situação do resgate, situação atual e fotos atuais.<br>
O projeto também conta com uma área de denúncia anônima, onde quem ver o animal sendo maltratado, sendo abandonado ou qualquer outro tipo de coisa que vai contra a criação dos animais, pode denunciar para uma ONG de forma anônima ou se identificando.</h4>
<br>
<h2>Tecnologias utilizadas no projeto:</h2>
<li>Java 17 - Linguagem do Backend</li>
<li>Spring Boot - Framework para desenvolvimento web</li>
<li>Spring Data JPA - Relacionamento com o Banco de Dados</li>
<li>PostgreSQL - Banco de Dados</li>
<li>Thymeleaf - Integração com o Frontend</li>
<li>Spring Security - Proteção dos dados</li>
<li>Bean Validation - Validação dos dados</li>
<li>Maven - Gerenciamento de dependências</li>
<li>Git & GitHub - Versionamento do código</li>
<li>Render - Deploy da aplicação</li>
<br>
<h2>Funcionalidades</h2>
<h3>ONG</h3>
<li>Cadastrar animais</li>
<li>Informar dados dos animais</li>
<li>Atualização dos dados</li>
<li>Dashboards dos dados</li>
<li>Receber denúncias</li>
<br>
<h3>Usuário</h3>
<li>Denunciar anonimamente ou se identificando</li>
<li>Acompanhar o status da denúncia</li>
<li>Vizualizar os animais da ONG</li>
<li>Vizualizar os dados dos animais</li>
<br>
<h3>Prazo Final do Projeto - 15 de Julho de 2025</h3>
<br>
<h2>Modelagem dos Dados</h2>
<br>
<h3>ONG</h3>
<p>- id (Long)</p>
<p>- nome (String)</p>
<p>- email (String)</p>
<p>- senha (String)</p>
<p>- cidade (String)</p>
<p>- telefone (String)</p>
<br>
<h3>CÂO</h3>
<p>- id (Long)</p>
<p>- nome (String)</p>
<p>- idade (Int)</p>
<p>- raça (String)</p>
<p>- status (Enum: DISPONIVEL, ADOTADO)</p>
<p>- foto (String url)</p>
<p>- descrição (String)</p>
<p>- id_ong (Id da ONG)</p>
<br>
<h3>DENÚNCIA</h3>
<p>- id (Long)</p>
<p>- foto (String url)</p>
<p>- localização (String)</p>
<p>- descrição (String)</p>
<p>- status (Enum: PENDENTE, IGNORADA, RESGATADO)</p>
<p>- dataHora (LocalDateTime)</p>

### 📁 Estrutura de Pacotes

- `controller`: controladores REST e web
- `model`: entidades do domínio (ONG, Cão, Denúncia, etc)
- `repository`: interfaces para persistência (JPA)
- `service`: lógica de negócio
- `dto`: objetos de transporte de dados
- `config`: configurações gerais do projeto (ex: segurança, CORS)
- `exception`: tratamento de exceções e validações
