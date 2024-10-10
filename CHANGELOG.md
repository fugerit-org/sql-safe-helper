# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.2.2 - 2024-10-10]

### Changed

- [GraalVM ready](https://universe.fugerit.org/src/docs/ref/graalvm-ready.html)

## [1.2.1 - 2024-10-10]

### Fixed

- Rimosse dipendenze non necessarie

## [1.2.0 - 2024-10-10]

### Added

- SqlExceptionHelper utility, findSQLException()

### Changed

- new actualValue field in SqlSafeHelperOutput (contains the update result, even if a rollback occurs)
- fj-version set to 8.6.5

## [1.1.0 - 2024-07-16]

### Added

- Query method

## [1.0.0 - 2024-07-16]

### Added

- safe INSERT / UPDATE / DELETE
