# Eficiência na Memória Virtual: Análise de Algoritmos de Substituição de Páginas

> A gestão eficiente da memória virtual é crucial para otimizar o desempenho de sistemas operacionais modernos. Este estudo explora e compara o impacto de diferentes algoritmos de substituição de páginas no gerenciamento da memória virtual. Os algoritmos analisados incluem FIFO (First In, First Out), LRU (Least Recently Used), Clock, Aging e NRU (Not Recently Used). Para simular esses algoritmos, foi desenvolvido um simulador em Java, capaz de testar o desempenho dos métodos em múltiplos cenários e cargas de trabalho. Os resultados obtidos oferecem uma compreensão detalhada das vantagens e limitações de cada algoritmo, destacando como o comportamento de substituição de páginas varia conforme o tipo de carga aplicada.

## Como rodar o projeto?

### Instalação do Java 21

#### Windows

#### 1 - Baixar o Java 21:

Acesse o site oficial do Oracle JDK.

Escolha a versão adequada para Windows e faça o download do instalador (.exe).

#### 2 - Instalar o Java:

Execute o instalador baixado.

Siga as instruções na tela para concluir a instalação.

Durante a instalação, você pode selecionar a opção para configurar a variável de ambiente JAVA_HOME.

#### 3 - Verificar a instalação:

Abra o Prompt de Comando e digite:

```bash
java -version
```

#### Linux

#### 1 - Baixar e instalar o Java 21:

Use o seguinte comando para instalar o OpenJDK 21. A maioria das distribuições modernas tem pacotes disponíveis:

```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

#### 2 - Verificar a instalação:

No terminal, digite:

```bash
java -version
```

### Instalação do Maven

#### Windows

#### 1 - Baixar o Maven:

Acesse o site oficial do Apache Maven.

Baixe a versão mais recente em formato zip.

#### 2 - Instalar o Maven:

Extraia o arquivo zip em um diretório de sua escolha (por exemplo, C:\apache-maven-3.x.x).

Configure a variável de ambiente M2_HOME para apontar para o diretório do Maven.

Adicione o diretório bin do Maven ao PATH:

````makefile
C:\apache-maven-3.x.x\bin
````

#### 3 - Verificar a instalação:

Abra o Prompt de Comando e digite:

````bash
mvn -version
````

#### Linux

#### 1 - Baixar e instalar o Maven:

Use os seguintes comandos:

````bash
sudo apt update
sudo apt install maven
````

#### 2 - Verificar a instalação:

No terminal, digite:

````bash
mvn -version
````

### Rodando o Projeto

No terminal, navegue até o diretório do projeto e execute:

````bash
mvn clean install
mvn javafx:run
````