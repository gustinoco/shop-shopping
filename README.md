# Android shoplogin Application

## Instalação

Faça o clone do projeto com o seguinte comando:
```bash
git clone git@--SEU_PROJETO--.git
```

## Começando

Este módulo utiliza a arquiteura proposta pelo template do Commons<br>
Para inicializar o módulo utilize o seguinte builder:

```kotlin
val deps = ShoppingModuleDependencies()

ShoppingModuleInitializer.Builder()
    .setContext(this)
    .setDependencies(deps)
    .setKeyCart("key")
    .setTheme(R.style.my_theme)
    .build()
```

## Maintainers

Este projeto é mantido por:
* [Tinoco](http://github.com/gustinoco)

Todas as alterações e publicação do módulo deve ser alinhado com, pelo menos, um dos maintainers do projeto.

## [1.0.0] ÚLTIMA VERSÃO PUBLICADA

Usage:
`implementation "br.com.shop.shopping:shopping:X.Y.Z"`

# Changelog

Todas as mudanças desse projeto ficam listadas nesse [documento](CHANGELOG.md).
