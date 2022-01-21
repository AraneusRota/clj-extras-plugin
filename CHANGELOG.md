# clojure-extras-plugin Changelog

## [Unreleased]

## 0.5.1

- Support to Ansi colors on stdout
- Streamlined namespace highlighter

## 0.5.0

- Option do analyze the project classpath with clj-kondo for better linting results
- Inline eval panel will now resize itself automatically based on content
- Inline eval pretty printing (good for long maps)
- Option to output stdout to REPL console
- Bump clj-kondo version to 2022.01.15

## 0.4.5

- Bump clj-kondo version to 2022.01.13
- Make warnings highlighting less intrusive
- Fix error when the project doesn't have a .clj-kondo config folder
- Fix error linting CLJS files

## 0.4.0

- Better clj-kondo integration performance (to make it even faster tune the AutoReparse Delay Option in Preferences > Code Editing)

## 0.3.0

- Add support to clj-kondo inspections
- Evaluate forms asynchronously to avoid UI Freezes
- Code cleanup

## 0.1.0

- Better nREPL session detection (multiple REPLs support)
- Evaluate forms in the context of its namespace (current file)
- Show evaluated results as syntax highlighted hints (pretty!)

## 0.0.9
- Add support to Intellij IDEA 2021.3

## 0.0.7
- Published to marketplace

## 0.0.6
- Initial public version
