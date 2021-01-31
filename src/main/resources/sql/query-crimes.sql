SELECT
    DATAOCORRENCIA,
    HORAOCORRENCIA,
    PERIDOOCORRENCIA,
    LOGRADOURO,
    NUMERO,
    BAIRRO,
    CIDADE,
    UF,
    LATITUDE,
    LONGITUDE,
    RUBRICA,
    DESCR_MARCA_VEICULO,
    DESCR_COR_VEICULO,
    ANO_FABRICACAO
FROM
    crimes
WHERE
    upper(CIDADE) == upper('S.PAULO') AND
    (upper(DESCRICAOLOCAL) == upper('Via Pública') OR
        upper(DESCRICAOLOCAL) == upper('Estacionamento Público')) AND
    (upper(RUBRICA) == upper('Furto (art. 155) - VEICULO') OR
        upper(RUBRICA) == upper('Roubo (art. 157) - VEICULO')) AND
    LATITUDE is not null AND
    LONGITUDE is not null AND
    PERIDOOCORRENCIA != 'EM HORA INCERTA' AND
    DESCR_TIPO_VEICULO is not null AND
    DESCR_MARCA_VEICULO is not null