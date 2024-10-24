### 📁 Documentação do Sistema de Arquivos em Java

Este documento fornece uma visão detalhada do funcionamento do sistema de arquivos implementado em Java, abrangendo suas principais classes e interações. O sistema foi dividido em três pacotes principais: `com.aracomp.fileSystem`, `com.aracomp.exception`, e `com.aracomp.TUI`.

---

### 0. Diagrama de Classe

<img src="resource/classDiagrams.png" alt="Diagrama do sistema" width="600"/>

---

### 1. Pacote `com.aracomp.fileSystem` 🗂️

O pacote `com.aracomp.fileSystem` é o núcleo do sistema de arquivos, lidando com o armazenamento e manipulação de blocos, gerenciamento do disco e operações básicas sobre arquivos.

#### 1.1. Classe `Block` 📦

**Descrição**: Representa um bloco de dados no sistema de arquivos.

- **Atributos**:
  - `char data`: Armazena um caractere que representa parte dos dados armazenados.
  - `int next`: Indica o próximo bloco no encadeamento, permitindo a leitura sequencial dos blocos.
- **Métodos**:
  - `Block(char data, int next)`: Construtor que inicializa um bloco com o dado e a referência para o próximo bloco.
  - `char getData()`: Retorna o dado armazenado no bloco.
  - `void setData(char data)`: Define o dado armazenado no bloco.
  - `int getNext()`: Retorna a referência para o próximo bloco.
  - `void setNext(int next)`: Define a referência para o próximo bloco.

#### 1.2. Classe `Disk` 💽

**Descrição**: Representa o disco que armazena os blocos de dados e permite operações de leitura, escrita e exclusão.

- **Atributos**:
  - `Block[] storage`: Array que representa os blocos de armazenamento do disco.
  - `int totalSize`: Tamanho total do disco (número de blocos).
  - `DiskManager diskManager`: Referência ao gerenciador de disco associado.
- **Métodos**:
  - `Disk(int size)`: Construtor que inicializa o disco com o número de blocos especificado e encadeia os blocos livres.
  - `void setDiskManager(DiskManager diskManager)`: Associa um gerenciador de disco ao disco e define o primeiro bloco vazio.
  - `String read(int address)`: Lê os dados de um arquivo a partir do endereço inicial de um bloco.
  - `int add(String content)`: Adiciona um novo arquivo no disco, dividindo o conteúdo nos blocos disponíveis e retornando o endereço do primeiro bloco.
  - `void delete(int address)`: Exclui um arquivo a partir de seu endereço inicial, liberando os blocos e retornando-os à lista de blocos livres.
  - `int getTotalSize()`: Retorna o tamanho total do disco.
  - `String toString()`: Retorna uma representação textual da estrutura do disco e seus blocos.

#### 1.3. Classe `DiskManager` 📊

**Descrição**: Gerencia as operações de leitura, escrita e exclusão de arquivos no disco, além de manter uma tabela de arquivos.

- **Atributos**:
  - `int diskAvailableSize`: Representa o espaço disponível no disco.
  - `Disk disk`: Referência ao disco associado.
  - `FileTable fileTable`: Gerencia a tabela de arquivos.
  - `int refEmptyBlock`: Referência ao primeiro bloco livre do disco.
- **Métodos**:
  - `DiskManager(Disk disk)`: Construtor que inicializa o gerenciador de disco.
  - `void add(String name, String content)`: Adiciona um novo arquivo ao disco e à tabela de arquivos.
  - `String read(String name)`: Lê o conteúdo de um arquivo a partir do nome na tabela de arquivos.
  - `void delete(String name)`: Exclui um arquivo a partir do nome, liberando os blocos ocupados.
  - `String showStructure()`: Retorna uma representação textual da estrutura do disco e dos arquivos.
  - `int getRefEmptyBlock()`, `void setRefEmptyBlock(int refEmptyBlock)`: Métodos para acessar e modificar a referência ao primeiro bloco vazio.

#### 1.4. Classe `File` 📄

**Descrição**: Representa um arquivo armazenado no disco.

- **Atributos**:
  - `String name`: Nome do arquivo.
  - `int size`: Tamanho do arquivo em caracteres.
  - `int address`: Endereço do primeiro bloco do arquivo.
- **Métodos**:
  - `File(String name, int size, int address)`: Construtor que inicializa um arquivo.
  - Métodos de acesso (`get` e `set`) para `name`, `size` e `address`.

#### 1.5. Classe `FileTable` 🗃️

**Descrição**: Gerencia uma lista de arquivos armazenados no disco.

- **Atributos**:
  - `ArrayList<File> files`: Lista de arquivos.
- **Métodos**:
  - `FileTable()`: Construtor que inicializa a tabela de arquivos.
  - `File search(String name)`: Busca um arquivo pelo nome.
  - `void add(String name, int size, int address)`: Adiciona um novo arquivo à tabela.
  - `void delete(String name)`: Remove um arquivo da tabela pelo nome.
  - `String toString()`: Retorna uma representação textual da lista de arquivos.

---

### 2. Pacote `com.aracomp.exception` 🚨

Este pacote contém classes para gerenciar exceções específicas do sistema de arquivos.

#### 2.1. Classe `FileNotFoundException` 🔍

**Descrição**: Exceção lançada quando um arquivo não é encontrado na tabela de arquivos.

- **Atributos**:
  - `serialVersionUID`: Identificador da versão da classe para serialização.
- **Métodos**:
  - `FileNotFoundException(String message)`: Construtor que inicializa a exceção com uma mensagem.

#### 2.2. Classe `InvalidOperationException` ❌

**Descrição**: Exceção lançada para operações inválidas, como adicionar um arquivo com nome vazio.

- **Atributos**:
  - `serialVersionUID`: Identificador da versão da classe para serialização.
- **Métodos**:
  - `InvalidOperationException(String message)`: Construtor que inicializa a exceção com uma mensagem.

#### 2.3. Classe `StorageUnenoughException` 🛑

**Descrição**: Exceção lançada quando não há espaço suficiente para armazenar um novo arquivo no disco.

- **Atributos**:
  - `serialVersionUID`: Identificador da versão da classe para serialização.
- **Métodos**:
  - `StorageUnenoughException(String message)`: Construtor que inicializa a exceção com uma mensagem.

---

### 3. Pacote `com.aracomp.TUI` 🖥️

Este pacote lida com a interface de usuário em terminal utilizando a biblioteca Lanterna, que permite criar uma interface gráfica simples no terminal.

#### 3.1. Classe `InitialScreen` 🧑‍💻

**Descrição**: Gerencia a tela inicial da interface de usuário em terminal para interação com o sistema de arquivos.

- **Atributos**:
  - `List<String> fileList`: Lista de arquivos adicionados.
  - `Disk disk`: Referência ao disco utilizado.
  - `DiskManager diskManager`: Referência ao gerenciador de disco.
- **Métodos**:
  - `InitialScreen()`: Construtor que inicializa o disco e o gerenciador de disco.
  - `void start()`: Inicia a interface gráfica no terminal, permitindo ao usuário realizar operações como adicionar, ler e deletar arquivos.
  - `void showMessage(String title, String message, Screen screen)`: Exibe uma mensagem de diálogo na tela.

- **Operações de Interface**:
  - ➕ **Adição de Arquivos**: Permite que o usuário adicione um novo arquivo e seu conteúdo ao sistema de arquivos.
  - 📖 **Leitura de Arquivos**: Exibe o conteúdo de um arquivo selecionado pelo usuário.
  - ❌ **Exclusão de Arquivos**: Remove um arquivo e libera os blocos de memória.
  - 🔍 **Visualização da Estrutura do Disco**: Exibe uma representação textual do estado atual do disco e dos arquivos.

---

Aqui está a versão com emojis adicionados para uma apresentação mais visual:

## 1. Funcionamento 🚀

- Este sistema de arquivos em Java utiliza uma abordagem simples de alocação encadeada, onde cada arquivo é armazenado em uma sequência de blocos. A classe `DiskManager` gerencia a adição e exclusão de arquivos, enquanto `FileTable` mantém uma referência aos arquivos armazenados. A interface em terminal, gerida pela classe `InitialScreen`, permite ao usuário interagir com o sistema de forma intuitiva, utilizando a biblioteca Lanterna para criar a interface gráfica. As exceções personalizadas (`InvalidOperationException`, `FileNotFoundException`, `StorageUnenoughException`) garantem que operações inválidas sejam tratadas de maneira controlada, proporcionando uma experiência de usuário mais robusta e informativa. 💡

- O gerenciamento de blocos livres neste sistema de arquivos é implementado utilizando um encadeamento de blocos através de uma lista de referência. Esse encadeamento permite que o sistema saiba quais blocos estão livres e quais estão ocupados. A seguir, detalharei o passo a passo de como funciona esse gerenciamento, abordando como são realizados os processos de adição, leitura e remoção de arquivos a partir do código e da estrutura de dados.

---

### 1. Estrutura de Dados para Gerenciamento de Blocos 📦

**Bloco (`Block`)**: Cada bloco de dados possui um atributo `char data` para armazenar um caractere e um atributo `int next` para indicar o próximo bloco na sequência. O `next` é essencial para formar a lista encadeada de blocos:

- **`data`**: Armazena o conteúdo do bloco.
- **`next`**: Indica o índice do próximo bloco na sequência. Um valor `-1` indica que não há próximo bloco (fim da cadeia).

**Armazenamento (`Disk`)**: Utiliza um array de `Block` (`Block[] storage`) para representar todos os blocos de dados no disco. Inicialmente, todos os blocos estão encadeados, indicando que estão livres.

---

### 2. Gerenciamento de Blocos Livres 🗂️

No momento da inicialização do disco (`Disk(int size)`), é criado um array de blocos (`Block[] storage`), e cada bloco é encadeado para apontar para o próximo:

- Os blocos de `0` a `size - 2` são inicializados com `next` apontando para o próximo índice (`i + 1`).
- O último bloco (`size - 1`) aponta para `-1`, indicando o fim da lista de blocos livres.
- **Exemplo**: Se o disco tem 5 blocos, a sequência de blocos livres seria: 
  ```
  [0] -> [1] -> [2] -> [3] -> [4] -> -1
  ```
  onde cada bloco aponta para o próximo, formando uma lista encadeada de blocos livres.

**Referência ao Primeiro Bloco Livre**: A classe `DiskManager` mantém um atributo `refEmptyBlock` que aponta para o primeiro bloco livre no array. Isso permite que o gerenciador de disco saiba onde iniciar a próxima operação de escrita. 📝

---

### 3. Adição de Arquivos ➕

**Processo de Adição**:
1. **Recuperação do Bloco Livre**:
   - O método `add(String content)` da classe `Disk` é chamado pelo `DiskManager` para armazenar um novo arquivo.
   - A partir de `refEmptyBlock`, o método identifica o primeiro bloco livre.
2. **Armazenamento dos Dados**:
   - Para cada caractere do `content`, o método:
     - Preenche o `data` do bloco livre com o caractere.
     - Atualiza `refBlock` para o próximo bloco livre (obtido através de `next`).
     - Atualiza o atributo `refEmptyBlock` em `DiskManager` para apontar para o próximo bloco disponível.
   - Após armazenar o último caractere, o `next` do último bloco recebe `-1`, indicando o fim do arquivo.
3. **Atualização da Tabela de Arquivos**:
   - O `DiskManager` registra o nome do arquivo, tamanho e endereço do bloco inicial na `FileTable`.

**Exemplo**:
- Supondo que `refEmptyBlock = 0` e queremos armazenar "ABC":
  - Bloco `0` recebe `'A'` e aponta para `1`.
  - Bloco `1` recebe `'B'` e aponta para `2`.
  - Bloco `2` recebe `'C'` e aponta para `-1`.
  - `refEmptyBlock` agora aponta para `3`, que é o próximo bloco livre.
  - A tabela de arquivos é atualizada com o nome do arquivo e o endereço inicial `0`.

---

### 4. Leitura de Arquivos 📖

**Processo de Leitura**:
1. **Identificação do Arquivo**:
   - A classe `DiskManager` utiliza a `FileTable` para localizar o arquivo através de seu nome e obter o endereço do bloco inicial.
2. **Leitura Sequencial dos Blocos**:
   - O método `read(int address)` da classe `Disk` é utilizado para ler os blocos a partir do endereço inicial.
   - A leitura ocorre iterando sobre os blocos:
     - Obtém o `data` de cada bloco.
     - Move para o próximo bloco utilizando `next` até que `next` seja `-1`.
3. **Construção do Conteúdo**:
   - O conteúdo é reconstruído em uma `StringBuilder` e retornado.

**Exemplo**:
- Um arquivo "ABC" começa no endereço `0`:
  - Bloco `0` contém `'A'` e aponta para `1`.
  - Bloco `1` contém `'B'` e aponta para `2`.
  - Bloco `2` contém `'C'` e aponta para `-1`.
  - O conteúdo "ABC" é lido e retornado.

---

### 5. Remoção de Arquivos ❌

**Processo de Remoção**:
1. **Identificação do Arquivo**:
   - A classe `DiskManager` localiza o arquivo pelo nome usando a `FileTable` e obtém o endereço do bloco inicial.
2. **Liberação dos Blocos**:
   - O método `delete(int address)` da classe `Disk` é chamado para liberar os blocos:
     - Itera sobre os blocos a partir do endereço inicial.
     - Para cada bloco, redefine o `data` para `'\0'` (caractere vazio) e guarda o próximo bloco.
     - Quando atinge o final da cadeia (`next == -1`), ajusta o `next` do último bloco para apontar para o antigo `refEmptyBlock` (encadeando os blocos liberados à lista de blocos livres).
3. **Atualização do `refEmptyBlock`**:
   - O primeiro bloco do arquivo agora é o novo `refEmptyBlock`, o que significa que ele se torna o primeiro bloco disponível para futuras operações de escrita.
4. **Atualização da Tabela de Arquivos**:
   - O arquivo é removido da `FileTable`, e o espaço é considerado liberado.

**Exemplo**:
- Suponha que o arquivo "ABC" começa em `0`:
  - Bloco `0` é liberado e seu `next` é atualizado para o valor atual de `refEmptyBlock` (por exemplo, `3`).
  - O `refEmptyBlock` agora aponta para `0`, e a cadeia de blocos livres é restaurada.

---

### Resumo 📝

- **Blocos Livres** são gerenciados através de um encadeamento simples utilizando o atributo `next` dos blocos.
- O `DiskManager` usa `refEmptyBlock` para rastrear o próximo bloco disponível, controlando a alocação e liberação de blocos.
- **Adição de Arquivos** envolve preencher blocos com caracteres e encadeá-los até o fim do conteúdo.
- **Leitura de Arquivos** percorre os blocos a partir do endereço inicial até encontrar `next == -1`, reconstruindo o conteúdo.
- **Remoção de Arquivos** libera os blocos e os reintegra à lista de blocos livres, atualizando a referência `refEmptyBlock`.