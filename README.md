# Sistema POO - Exercícios de Programação Orientada a Objetos

Sistema completo desenvolvido em Java seguindo os princípios de Programação Orientada a Objetos (POO), incluindo encapsulamento, herança, polimorfismo e boas práticas de desenvolvimento.

## 📋 Descrição

Este projeto implementa quatro módulos independentes que demonstram diferentes conceitos de POO:

1. **Sistema de Carros e Pessoas** - Cálculo de IPVA e valor de revenda
2. **Sistema de Bonificações** - Gerenciamento de funcionários e gerentes com herança
3. **Sistema de Vendas** - Pedidos com múltiplos produtos
4. **Sistema Bancário** - Contas corrente e poupança com operações

## 🚀 Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Maven 3.x** - Gerenciamento de dependências e build
- **JUnit 5 (Jupiter)** - Framework de testes unitários
- **JUnit Params** - Testes parametrizados

## 📁 Estrutura do Projeto

```
exercicios-poo/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── br/com/godev/exerciciospoo/
│   │           ├── Pessoa.java
│   │           ├── Carro.java
│   │           ├── Funcionario.java
│   │           ├── Gerente.java
│   │           ├── ControleDeBonificacoes.java
│   │           ├── Produto.java
│   │           ├── ItemPedido.java
│   │           ├── Pedido.java
│   │           ├── TipoConta.java (enum)
│   │           ├── ContaBancaria.java
│   │           └── Main.java
│   └── test/
│       └── java/
│           └── br/com/godev/exerciciospoo/
│               ├── CarroTest.java
│               ├── ContaBancariaTest.java
│               ├── PedidoTest.java
│               └── ControleDeBonificacoesTest.java
├── pom.xml
└── README.md
```

## 🔧 Como Compilar e Executar

### Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.x (opcional, pode usar javac)

### Compilação

**Opção 1: Usando Maven**
```bash
mvn clean compile
```

**Opção 2: Usando javac**
```bash
javac -d target/classes -sourcepath src/main/java src/main/java/br/com/godev/exerciciospoo/*.java
```

### Executar a Aplicação Principal

**Usando Maven:**
```bash
mvn exec:java -Dexec.mainClass="br.com.godev.exerciciospoo.Main"
```

**Usando Java:**
```bash
java -cp target/classes br.com.godev.exerciciospoo.Main
```

### Executar os Testes

```bash
mvn test
```

Ou através da sua IDE (IntelliJ IDEA, Eclipse, VS Code com extensões Java).

## 📚 Módulos do Sistema

### 1. Sistema de Carros e Pessoas

**Classes:** `Pessoa`, `Carro`

**Funcionalidades:**
- Associação entre carro e proprietário (Pessoa)
- Cálculo de tempo de uso do veículo
- Cálculo de valor de revenda com depreciação
- Cálculo de IPVA

**Regras de Negócio:**
- Vida útil do carro: 20 anos
- Depreciação: 5% ao ano sobre o valor de compra
- IPVA: 4% do valor de revenda
- Carros com mais de 10 anos não pagam IPVA
- Valor de revenda mínimo: R$ 0,00

**Exemplo:**
```java
Pessoa proprietario = new Pessoa("João Silva", "123.456.789-00");
Carro carro = new Carro("Toyota", "Corolla", "Prata", 2020, 80000.0, proprietario);

int tempoUso = carro.calcularTempoDeUsoEmAnos(2025);  // 5 anos
double valorRevenda = carro.calcularValorRevenda();    // ~77.378,09
double ipva = carro.calcularIPVA();                    // 4% do valor de revenda
```

### 2. Sistema de Bonificações

**Classes:** `Funcionario`, `Gerente` (herda de Funcionario), `ControleDeBonificacoes`

**Funcionalidades:**
- Cálculo de bonificação diferenciado por tipo de funcionário
- Herança e polimorfismo
- Controle centralizado de bonificações

**Regras de Negócio:**
- Funcionário comum: bonificação de 5% do salário
- Gerente: bonificação de 10% do salário
- ControleDeBonificacoes acumula todas as bonificações registradas

**Exemplo:**
```java
Funcionario func = new Funcionario("Ana", "111.222.333-44", 3000.0);
Gerente gerente = new Gerente("Carlos", "555.666.777-88", 8000.0, "senha123", 5);

ControleDeBonificacoes controle = new ControleDeBonificacoes();
controle.registrar(func);     // Adiciona 150.00 (5%)
controle.registrar(gerente);  // Adiciona 800.00 (10%)

double total = controle.getTotalBonificacao();  // 950.00
```

### 3. Sistema de Vendas

**Classes:** `Produto`, `ItemPedido`, `Pedido`

**Funcionalidades:**
- Cadastro de produtos
- Criação de itens de pedido com quantidade
- Pedidos com múltiplos itens
- Cálculo automático de valores

**Regras de Negócio:**
- Valor do item = preço do produto × quantidade
- Valor total do pedido = soma de todos os itens

**Exemplo:**
```java
Produto notebook = new Produto(1, "Notebook Dell", 3500.0);
Produto mouse = new Produto(2, "Mouse Logitech", 80.0);

ItemPedido item1 = new ItemPedido(notebook, 2);  // 7000.00
ItemPedido item2 = new ItemPedido(mouse, 3);     // 240.00

Pedido pedido = new Pedido();
pedido.adicionarItem(item1);
pedido.adicionarItem(item2);

double total = pedido.calcularValorTotal();  // 7240.00
```

### 4. Sistema Bancário

**Classes:** `ContaBancaria`, `TipoConta` (enum)

**Funcionalidades:**
- Contas corrente e poupança
- Operações de depósito e saque
- Validação de saldo
- Cobrança de taxas diferenciadas

**Regras de Negócio:**
- Conta Corrente: cobra taxa de R$ 0,50 por saque
- Poupança: sem taxa de saque
- Depósitos: apenas valores positivos
- Saques: validação de saldo suficiente (incluindo taxa, se aplicável)

**Exemplo:**
```java
ContaBancaria corrente = new ContaBancaria("12345", "0001", 1000.0, TipoConta.CONTA_CORRENTE);
ContaBancaria poupanca = new ContaBancaria("67890", "0001", 1000.0, TipoConta.POUPANCA);

corrente.depositar(500.0);   // Saldo: 1500.00
corrente.sacar(200.0);       // Saldo: 1299.50 (taxa de 0.50)

poupanca.sacar(200.0);       // Saldo: 800.00 (sem taxa)
```

## 🧪 Testes

O projeto possui cobertura abrangente de testes unitários:

- **CarroTest**: 8 testes (incluindo testes parametrizados)
  - Cálculo de tempo de uso
  - Valor de revenda com depreciação
  - Cálculo de IPVA para diferentes idades

- **ContaBancariaTest**: 13 testes (incluindo testes parametrizados)
  - Depósitos válidos e inválidos
  - Saques com e sem taxa
  - Validação de saldo
  - Comparação entre tipos de conta

- **PedidoTest**: 10 testes
  - Cálculo de valores de itens
  - Valor total do pedido
  - Múltiplos produtos e quantidades

- **ControleDeBonificacoesTest**: 12 testes
  - Bonificações de funcionários e gerentes
  - Herança e polimorfismo
  - Acúmulo de bonificações

### Executar Testes Específicos

```bash
# Todos os testes
mvn test

# Apenas uma classe de teste
mvn test -Dtest=CarroTest

# Um método específico
mvn test -Dtest=CarroTest#deveCalcularIpvaParaCarroComMenosDe10Anos
```

## 🎯 Conceitos de POO Implementados

- ✅ **Encapsulamento**: Atributos privados com getters/setters apropriados
- ✅ **Herança**: Gerente herda de Funcionario
- ✅ **Polimorfismo**: Método `getBonificacao()` sobrescrito em Gerente
- ✅ **Abstração**: Interfaces claras e separação de responsabilidades
- ✅ **Associação**: Carro possui um Proprietario (Pessoa)
- ✅ **Composição**: Pedido composto por ItemPedido
- ✅ **Enum**: TipoConta define tipos de conta bancária

## 📖 Princípios Aplicados

- **SOLID**: Classes com responsabilidade única
- **DRY**: Sem repetição de código
- **Clean Code**: Nomes descritivos, métodos pequenos e focados
- **Boas Práticas Java**: Construtores adequados, toString(), documentação Javadoc
- **Testes**: Cobertura abrangente com JUnit 5 e testes parametrizados

---

**Observações:**
- **Recursividade**: O projeto não utiliza recursividade porque não há estruturas de dados hierárquicas (árvores, grafos) ou problemas que se beneficiariam naturalmente de soluções recursivas. Os cálculos implementados (depreciação de veículos, soma de valores, bonificações) são resolvidos de forma mais eficiente, legível e performática com estruturas iterativas simples (loops). Recursividade seria over-engineering neste contexto, adicionando complexidade desnecessária e potencial risco de StackOverflow sem trazer benefícios reais.
- Todas as regras de negócio estão documentadas no código
- A classe `Main.java` demonstra o funcionamento de todos os módulos com exemplos práticos

