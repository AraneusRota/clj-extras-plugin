package com.github.brcosta.cljstuffplugin.extensions

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import cursive.ClojureLanguage
import cursive.highlighter.ClojureSyntaxHighlighter
import cursive.psi.api.ClKeyword

@Suppress("unused", "UnstableApiUsage")
class ClojureAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.language != ClojureLanguage.getInstance()) {
            return
        }

        try {
            when {
                element is ClKeyword -> if (element.namespace != null) {
                    val startOffset = element.textRange.startOffset
                    if (element.text?.contains("/") == true) {
                        val prefixRange = TextRange.from(startOffset, element.namespace.length + 1)
                        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(prefixRange).textAttributes(ClojureColorsAndFontsPageEx.KEYWORD_NAMESPACE)
                            .create()
                    } else {
                        val range = TextRange.from(startOffset, element.text.length)
                        val qualifiedName = element.qualifiedName
                        holder.newAnnotation(HighlightSeverity.INFORMATION, qualifiedName)
                            .range(range)
                            .afterEndOfLine()
                            .tooltip(qualifiedName).create()
                    }
                }
                isNsReaderMacro(element) -> {
                    val range = TextRange.from(element.textRange.startOffset, element.text.length)
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(range).textAttributes(ClojureColorsAndFontsPageEx.KEYWORD_NAMESPACE)
                        .create()
                }
                isReaderNamespacedSymbol(element) -> {
                    val range = TextRange.from(element.textRange.startOffset, element.text.length)
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(range).textAttributes(ClojureColorsAndFontsPageEx.SYMBOL_NAMESPACE)
                        .create()
                }

            }
        } catch (e: Exception) {
            // should not happen
        }
    }

    private fun isNsReaderMacro(element: PsiElement) =
        element.prevSibling != null && element.prevSibling.text == "#:"

    private fun isReaderNamespacedSymbol(element: PsiElement) =
        element.nextSibling != null && (element.nextSibling is LeafPsiElement) && (element.nextSibling as LeafPsiElement).elementType.debugName == "ns separator"

}
