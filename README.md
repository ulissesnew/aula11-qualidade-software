# Aula 11 - Qualidade de Software

Entrega prática da atividade de análise estática e refatoração. O projeto transforma duas regras com condicionais extensos em implementações orientadas a estratégia, reduzindo a complexidade ciclomática dos serviços de decisão.

## Refatorações

| Classe original | Problema | Solução |
| --- | --- | --- |
| `FinanceUtils` | impostos decididos por cadeia `if/else` | `TaxStrategy` + registro imutável de estratégias |
| `DiscountCalculator` | descontos de pagamento decididos por cadeia `if/else` | `DiscountStrategy` + registro imutável de estratégias |

Entradas nulas, códigos desconhecidos e valores negativos são tratados explicitamente. Valores monetários usam `BigDecimal`, nunca `double`.

## Executar testes e cobertura

```bash
mvn clean verify
```

O relatório é gerado em `target/site/jacoco/index.html`.

## Analisar no SonarQube

Com o SonarQube local ativo em `http://localhost:9000` e um token criado no servidor:

```bash
mvn clean verify sonar:sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=SEU_TOKEN
```

O Quality Gate mínimo recomendado está documentado em [docs/quality-gate.md](docs/quality-gate.md).
