
# Projeto A3 2024.1
## Estrutura de Dados e Análise de Algoritmos

Tema: Busca e Ordenação de dados usandos Dados da Last.FM

## Membros

- Bruno Venâncio de Souza e Silva - RA: 821135934
- Henrick Melo Vital - RA: 821224905

## Pré-requisitos

Java 17 ou superior\
PostgreSQL 12 ou superior\
Git 2.30 ou superior
## Configurando a aplicação (Opcional)

O arquivo `src/main/resources/application.properties`
contém algumas configurações que são necessárias para
a execução da aplicação, as mais importantes são:

- `server.port=6666` : a porta que será usuada pela aplicação, caso não esteja disponível ou esteja bloqueada, pode ser alterada aqui.

- `spring.datasource.url=jdbc:postgresql://localhost:PORTA/BANCO_DE_DADOS` : Por padrão, o PostgreSQL vai rodar na porta 5432, o que pode ser alterado aqui. Ele também espera a existência de um banco de dados chamado `projetoa3`, onde pode ser substituído por algum banco de dados já existente, desde que exista permissão para criar uma nova tabela (vide configuração seguinte).

- `spring.datasource.username=USUARIO_POSTGRES` : Nome de um usuário do PostgreSQL que tenha permissão de gravação no banco de dados da opção anterior.
- `spring.datasource.password=SENHA` : Senha do usuário acima.

Nenhuma dessas opções é necessário alterar, basta que exista um banco de dados chamado 'projetoa3', acessível por um usuário chamado 'projetoa3' que pode ser utilizado com a senha 'projetoa3'. Consulte a documentação do PostgreSQL para maiores informações sobre o tema.

- Na pasta raiz do projeto, existe um script sql `projetoa3.sql`, execute esse script para criar uma tabela no banco de dados e popula-la com alguns dados, esse passo é necessário para que o programa seja executado corretamente.
## Rodando localmente

Para rodar a aplicação localmente, os passos a seguir supoem que estejam sendo executados em uma janela do `PowerShell (Windows)` ou do `Bash/Zsh (Linux)`:

Clone o projeto:

```bash
  git clone https://github.com/d4rkwav3/edaa_projetoa3.git
```

Entre no diretório do projeto:

```bash
  cd edaa_projetoa3
```

Crie o pacote do aplicação

No Windows:

```powershell
  .\mvnw package -DskipTests
```

No Linux:

```bash
  mvn package -DskipTests
```

Para rodar a aplicação, vá até a pastar `target` e execute o aplicativo projetoa3-0.0.1.jar, é recomendavel que a aplicação seja executada em uma janela de terminal usando o comando abaixo:

```bash
  java -jar projetoa3-0.0.1.jar
```

Para finalizar a aplicação, basta pressionar as teclas `CTRL + C`.

Enquanto estiver em execução, a aplicação será acessível a partir do  endereço abaixo no navegador:

```bash
  localhost:6666
```