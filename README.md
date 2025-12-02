# 🐾 AdotePet

> **Status do Projeto:** ✅ Versão 1.0 Concluída (Novembro 2025)

O **AdotePet** é uma plataforma completa para conectar ONGs de proteção animal à comunidade. O sistema permite que abrigos gerenciem seus resgates e que cidadãos participem ativamente na proteção animal através de denúncias anônimas e adoção.

🔗 **Acesse a Aplicação (Live Demo):** [https://adotepet-72vj.onrender.com](https://adotepet-72vj.onrender.com)

---

## 🚀 Funcionalidades

### Para ONGs (Área Administrativa)
- **Gestão de Acesso:** Cadastro seguro e login com criptografia.
- **Painel de Controle:** Dashboard com estatísticas em tempo real (total de animais, denúncias pendentes).
- **Gestão de Animais:** Cadastro completo de cães com upload de fotos e armazenamento local.
- **Gestão de Denúncias:** Recebimento, análise e alteração de status de denúncias (Pendente -> Em Análise -> Resgatado).

### Para o Público (Acesso Aberto)
- **Denúncia Anônima:** Formulário seguro para reportar animais abandonados.
- **Adoção:** Busca e visualização de animais disponíveis com filtros por raça e idade.
- **Segurança:** Acesso controlado; dados sensíveis protegidos.

---

## 🛠 Tecnologias Utilizadas

O projeto foi construído seguindo as melhores práticas de arquitetura MVC e segurança.

**Backend & Core**
- **Java 17:** Linguagem principal.
- **Spring Boot 3:** Framework para desenvolvimento ágil e configuração.
- **Spring Security:** Autenticação, Autorização (RBAC) e proteção contra ataques (CSRF).
- **Spring Data JPA:** Camada de persistência e abstração de banco de dados.
- **JPA Specifications:** Motor de busca dinâmico para filtros complexos.
- **Bean Validation:** Validação robusta de dados na entrada da API.

**Frontend**
- **Thymeleaf:** Engine de template para renderização no servidor (SSR).
- **Bootstrap 5:** Estilização responsiva e componentes de UI (Modais, Cards).
- **JavaScript (Vanilla):** Interatividade e manipulação de DOM.

**Infraestrutura & DevOps**
- **PostgreSQL:** Banco de dados relacional (Produção).
- **H2 Database:** Banco de dados em memória/arquivo (Desenvolvimento).
- **Docker:** Containerização da aplicação.
- **Render:** Plataforma de Cloud Hosting (PaaS).
- **Maven:** Gerenciamento de dependências e build.

---

## 🗂 Modelagem de Dados

O sistema utiliza um banco de dados relacional com as seguintes entidades principais:

```mermaid
erDiagram
    ONG ||--o{ CACHORRO : cadastra
    ONG ||--o{ DENUNCIA : gerencia
    
    ONG {
        Long id
        String nome
        String email
        String senha
        String cidade
        String telefone
    }

    CACHORRO {
        Long id
        String nome
        String raca
        Int idade
        String fotoUrl
        Enum status
    }

    DENUNCIA {
        Long id
        String descricao
        String localizacao
        Enum status
        Long ong_responsavel_id
    }


📂 Estrutura do Projeto

A arquitetura segue o padrão de camadas para garantir a separação de responsabilidades:

controller: Gerencia as requisições HTTP e a navegação.

service: Contém toda a regra de negócio e validações complexas.

repository: Interface de comunicação com o banco de dados via JPA.

model: Entidades que representam as tabelas do banco.

dto: Objetos para transferência de dados seguros.

config: Configurações de segurança, MVC e Uploads.

specification: Lógica para filtros de busca dinâmica.

exception: Tratamento global de erros.

💻 Como Executar Localmente:

Clone o repositório
git clone [https://github.com/gravonski/adotepet.git](https://github.com/gravonski/adotepet.git)


Configure o Banco de Dados:
O projeto está configurado para usar PostgreSQL em produção e H2/Postgres localmente.

Verifique o arquivo src/main/resources/application-dev.properties.


Execute com Maven:
./mvnw spring-boot:run


Acesse:
Abra http://localhost:8080 no seu navegador.

Desenvolvido por Andrei Gravonski
