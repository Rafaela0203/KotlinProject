package org.example.project.presentation.screens.complementaryInfo.data

// Data class to hold content for each complementary info section
data class ComplementaryInfoSectionData(
    val title: String,
    val description: String? = null, // Some sections might only have a title and image
    val imageUrl: String? = null // Optional image URL or resource path
)

// Define the content for each section based on your screenshots
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
        imageUrl = "url_to_image_ead03b.jpg" // Placeholder URL for image_ead03b.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo argiloso com escore Qe-VESS: 1,5 e 3,5",
        imageUrl = "url_to_image_ead016.jpg" // Placeholder URL for image_ead016.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo argiloso com escore Qe-VESS: 1,5 e 4,0",
        imageUrl = "url_to_image_eacfdd.jpg" // Placeholder URL for image_eacfdd.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo argiloso com escore Qe-VESS 4,5",
        imageUrl = "url_to_image_eacfb9.jpg" // Placeholder URL for image_eacfb9.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostras solo arenoso com escore Qe-VESS 4,5",
        imageUrl = "url_to_image_eacf3d.jpg" // Placeholder URL for image_eacf3d.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo argiloso com escores Qe-VESS 5,0",
        imageUrl = "url_to_image_eacc78.jpg" // Placeholder URL for image_eacc78.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo argiloso com escores Qe-VESS 5,0",
        imageUrl = "url_to_image_eacc78.jpg" // Placeholder URL for image_eacc78.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo arenoso com escores Qe-VESS 5,0",
        imageUrl = "url_to_image_eacc58.jpg" // Placeholder URL for image_eacc58.jpg
    ),
    ComplementaryInfoSectionData(
        title = "Amostra solo arenoso com escores Qe-VESS 5,0",
        imageUrl = "url_to_image_eacc78.jpg" // Placeholder URL for image_eacc78.jpg
    )
)
