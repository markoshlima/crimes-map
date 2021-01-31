package com.spark.model

import org.apache.spark.sql.types._

//MODEL STRUCT TO MAP CSV BUSINESS FILE
object StructCrimesModel {

  def getStruct() = {
    StructType(Array(
      StructField("ANO_BO", StringType, true),
      StructField("NUM_BO", StringType, true),
      StructField("NUMERO_BOLETIM", StringType, true),
      StructField("BO_INICIADO", StringType, true),
      StructField("BO_EMITIDO", StringType, true),
      StructField("DATAOCORRENCIA", StringType, true),
      StructField("HORAOCORRENCIA", StringType, true),
      StructField("PERIDOOCORRENCIA", StringType, true),
      StructField("DATACOMUNICACAO", StringType, true),
      StructField("DATAELABORACAO", StringType, true),
      StructField("BO_AUTORIA", StringType, true),
      StructField("FLAGRANTE", StringType, true),
      StructField("NUMERO_BOLETIM_PRINCIPAL", StringType, true),
      StructField("LOGRADOURO", StringType, true),
      StructField("NUMERO", StringType, true),
      StructField("BAIRRO", StringType, true),
      StructField("CIDADE", StringType, true),
      StructField("UF", StringType, true),
      StructField("LATITUDE", StringType, true),
      StructField("LONGITUDE", StringType, true),
      StructField("DESCRICAOLOCAL", StringType, true),
      StructField("EXAME", StringType, true),
      StructField("SOLUCAO", StringType, true),
      StructField("DELEGACIA_NOME", StringType, true),
      StructField("DELEGACIA_CIRCUNSCRICAO", StringType, true),
      StructField("ESPECIE", StringType, true),
      StructField("RUBRICA", StringType, true),
      StructField("DESDOBRAMENTO", StringType, true),
      StructField("STATUS", StringType, true),
      StructField("NOMEPESSOA", StringType, true),
      StructField("TIPOPESSOA", StringType, true),
      StructField("VITIMAFATAL", StringType, true),
      StructField("RG", StringType, true),
      StructField("RG_UF", StringType, true),
      StructField("NATURALIDADE", StringType, true),
      StructField("NACIONALIDADE", StringType, true),
      StructField("SEXO", StringType, true),
      StructField("DATANASCIMENTO", StringType, true),
      StructField("IDADE", StringType, true),
      StructField("ESTADOCIVIL", StringType, true),
      StructField("PROFISSAO", StringType, true),
      StructField("GRAUINSTRUCAO", StringType, true),
      StructField("CORCUTIS", StringType, true),
      StructField("NATUREZAVINCULADA", StringType, true),
      StructField("TIPOVINCULO", StringType, true),
      StructField("RELACIONAMENTO", StringType, true),
      StructField("PARENTESCO", StringType, true),
      StructField("PLACA_VEICULO", StringType, true),
      StructField("UF_VEICULO", StringType, true),
      StructField("CIDADE_VEICULO", StringType, true),
      StructField("DESCR_COR_VEICULO", StringType, true),
      StructField("DESCR_MARCA_VEICULO", StringType, true),
      StructField("ANO_FABRICACAO", StringType, true),
      StructField("ANO_MODELO", StringType, true),
      StructField("DESCR_TIPO_VEICULO", StringType, true),
      StructField("QUANT_CELULAR", StringType, true),
      StructField("MARCA_CELULAR", StringType, true))
    )
  }

}
