# Quality Gate mínimo

Configure este gate no SonarQube para o projeto:

| Condição | Critério |
| --- | --- |
| Cobertura em novo código | maior ou igual a 80% |
| Bugs novos | 0 |
| Vulnerabilidades novas | 0 |
| Code smells novos | 0 críticos ou bloqueadores |
| Dívida técnica nova | menor que 1 dia |

O gate deve ser associado ao projeto após a primeira análise. O código deste repositório já fornece o relatório JaCoCo para que a métrica de cobertura seja enviada ao SonarQube.
