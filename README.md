# Documentação
# Trabalho APS Ufsj
## Dependências
Programa foi desenvolvido utilizando java 11, não testado com outras versões.

Link da JDK utilizada para desenvolvimento, apesar de necessitar apenas da JRE.

[Java-11-Download](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)

Necessário possuir o maven instalado, ou utilizar uma IDE com suporte.

Também é necessário possui o SGBD MYSQL 8 instalado, recomendo a instalação utilizando o docker.

**Banco de Dados:**
- **Docker**: [Instale aqui](https://docs.docker.com/get-docker/)  
- **Docker Compose**: [Instale aqui](https://docs.docker.com/compose/install/)
Para criar o container com o MYSQL e já criar o banco de dados da aplicação, basta entrar na pasta infra e executar o seguinte comando 

```shell
docker compose up -d
```


No pacote resources/schema/database.sql possui o script de criação do banco de dados do programa, assim como um arqquivo resources/db.properties o qual deve ser configurado com as credencias do seu banco de dados.

## Como rodar o programa:
Após estar com o banco de dados configurado, basta compilar o programa através de uma ide de sua preferência e executar a classe main