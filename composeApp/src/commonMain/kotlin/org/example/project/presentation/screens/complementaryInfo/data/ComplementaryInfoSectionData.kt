package org.example.project.presentation.screens.complementaryInfo.data

import kotlinproject.composeapp.generated.resources.IC_01
import kotlinproject.composeapp.generated.resources.IC_02
import kotlinproject.composeapp.generated.resources.IC_03
import kotlinproject.composeapp.generated.resources.IC_04
import kotlinproject.composeapp.generated.resources.IC_05
import kotlinproject.composeapp.generated.resources.IC_06
import kotlinproject.composeapp.generated.resources.IC_07
import kotlinproject.composeapp.generated.resources.IC_08
import kotlinproject.composeapp.generated.resources.IC_09
import kotlinproject.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource

data class ComplementaryInfoSectionData(
    val title: String,
    val description: String? = null,
    val imageRes: DrawableResource? = null
)

val complementaryInfoData = listOf(
    ComplementaryInfoSectionData(
        title = "A nota da qualidade estrutural do solo pode ser atribuída entre categorias se a camada apresentar características das duas.",
        description = "Por exemplo, um escore VESS de 1,5 pode ser atribuído se a camada apresentar uma proporção de ≈50% com qualidade estrutural 1, mas também apresentar agregados com qualidade estrutural 2."
    ),
    ComplementaryInfoSectionData(
        title = "As figuras abaixo são exemplos de solos com diferentes escores Qe-VESS para auxiliar o usuário na atribuição da nota da qualidade estrutural do solo.",
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo argiloso com escore Qe-VESS: 1,0 e 2,5",
        imageRes = Res.drawable.IC_01
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo argiloso com escore Qe-VESS: 1,5 e 3,5",
        imageRes = Res.drawable.IC_02

    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo argiloso com escore Qe-VESS: 1,5 e 4,0",
        imageRes = Res.drawable.IC_03
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo argiloso com escore Qe-VESS 4,5",
        imageRes = Res.drawable.IC_04
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo arenoso com escore Qe-VESS 4,5",
        imageRes = Res.drawable.IC_05
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo argiloso com escores Qe-VESS 5,0",
        imageRes = Res.drawable.IC_06
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo argiloso com escores Qe-VESS 5,0",
        imageRes = Res.drawable.IC_07
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo arenoso com escores Qe-VESS 5,0",
        imageRes = Res.drawable.IC_08
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo arenoso com escores Qe-VESS 5,0",
        imageRes = Res.drawable.IC_09
    )
)
