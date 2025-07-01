package org.example.project.data.repository

import EvaluationData
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Implementação concreta da interface [EvaluationRepository].
 * Esta classe é responsável por lidar com a persistência dos dados de avaliação.
 *
 * @param dataStore Uma instância de [DataStore<Preferences>] injetada, usada para
 * armazenar dados de preferência de forma assíncrona e persistente.
 */
class EvaluationRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : EvaluationRepository {

    // Define uma chave de preferência para armazenar a última avaliação.
    // Para um histórico de avaliações, seria necessário armazenar uma lista serializada
    // ou usar uma abordagem de banco de dados (ex: SQLite).
    private val LATEST_EVALUATION_KEY = stringPreferencesKey("latest_evaluation")

    /**
     * Salva uma avaliação no DataStore.
     *
     * @param evaluation O objeto [EvaluationData] a ser salvo.
     */
    override suspend fun saveEvaluation(evaluation: EvaluationData) {
        // Usa o bloco 'edit' do DataStore para transacionar e atualizar as preferências.
        dataStore.edit { preferences ->
            // Serializa o objeto EvaluationData para uma string JSON.
            // É necessário adicionar a dependência 'kotlinx-serialization-json'
            // ao seu build.gradle.kts se ainda não o fez.
            val jsonString = Json.encodeToString(evaluation)
            // Armazena a string JSON na chave definida.
            preferences[LATEST_EVALUATION_KEY] = jsonString
        }
    }

    /**
     * Recupera uma avaliação por ID do DataStore.
     *
     * @param id O ID da avaliação a ser recuperada.
     * @return O objeto [EvaluationData] correspondente, ou null se não for encontrado.
     *
     * Nota: A implementação atual do DataStore com Preferences não suporta
     * a recuperação por ID de forma eficiente para múltiplas avaliações.
     * Esta função é um placeholder. Se você precisar de um sistema de banco de dados
     * completo com IDs, considere usar uma solução como o SQLite (Room para Android)
     * ou Firebase Firestore para KMP.
     */
    override suspend fun getEvaluationById(id: Int): EvaluationData? {
        // Como o DataStore Preferences armazena apenas pares chave-valor simples,
        // não é trivial buscar por um "ID" dentro de uma coleção de objetos.
        // Para uma funcionalidade de "histórico de avaliações" completa com busca por ID,
        // você precisaria de uma estrutura de dados mais complexa no DataStore (ex:
        // armazenar uma lista de JSONs e iterar) ou um banco de dados real.
        println("A função getEvaluationById não é totalmente implementada com DataStore Preferences para busca por ID.")
        return null // Retorna null como placeholder
    }
}